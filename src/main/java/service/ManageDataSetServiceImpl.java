package service;

import bean.DataSetBean;
import bean.UploadResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import util.SimilarityUtil;

public class ManageDataSetServiceImpl implements ManageDataSetService {

    @Override
    public UploadResult uploadDataSet(DataSetBean bean) {
        String fp = SimilarityUtil.fingerprint(bean.getDataset());
        int best = 0;
        String bestName = null;

        try (Connection con = DBConnection.getConnection()) {
            // Step 1: exact duplicate? (fingerprint lookup, no text comparison needed)
            try (PreparedStatement ps = con.prepareStatement("SELECT dataset_name FROM files WHERE fingerprint=?")) {
                ps.setString(1, fp);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        log(con, bean, 100, false);
                        return new UploadResult(false, 100, rs.getString(1),
                                "Exact duplicate of '" + rs.getString(1) + "' - not uploaded.");
                    }
                }
            }
            // Step 2: near duplicate? compare against stored data
            try (PreparedStatement ps = con.prepareStatement("SELECT dataset_name, dataset FROM files");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int s = SimilarityUtil.similarityPercent(bean.getDataset(), rs.getString(2));
                    if (s > best) { best = s; bestName = rs.getString(1); }
                }
            }
            if (best >= UploadResult.THRESHOLD) {
                log(con, bean, best, false);
                return new UploadResult(false, best, bestName,
                        "Content is " + best + "% similar to '" + bestName + "' (limit "
                                + UploadResult.THRESHOLD + "%) - not uploaded to avoid redundancy.");
            }
            // Step 3: unique -> store
            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO files(from_user,dataset_name,dataset,fingerprint) VALUES(?,?,?,?)")) {
                ps.setString(1, bean.getFrom_user());
                ps.setString(2, bean.getDataset_name());
                ps.setString(3, bean.getDataset());
                ps.setString(4, fp);
                ps.executeUpdate();
            }
            log(con, bean, best, true);
            return new UploadResult(true, best, bestName,
                    "Unique content (highest similarity " + best + "%) - uploaded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            return new UploadResult(false, 0, null, "Upload failed: database error.");
        }
    }

    private void log(Connection con, DataSetBean b, int score, boolean stored) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO similarity_log(from_user,dataset_name,score,stored) VALUES(?,?,?,?)")) {
            ps.setString(1, b.getFrom_user());
            ps.setString(2, b.getDataset_name());
            ps.setInt(3, score);
            ps.setBoolean(4, stored);
            ps.executeUpdate();
        }
    }

    @Override
    public List<DataSetBean> getMyPicked(String uname) {
        List<DataSetBean> list = new ArrayList<>();
        String sql = "SELECT id,from_user,dataset_name,dataset,uploaded_on FROM files WHERE from_user=? ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uname);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DataSetBean d = new DataSetBean();
                    d.setId(rs.getInt("id"));
                    d.setFrom_user(rs.getString("from_user"));
                    d.setDataset_name(rs.getString("dataset_name"));
                    d.setDataset(rs.getString("dataset"));
                    d.setUploaded_on(String.valueOf(rs.getTimestamp("uploaded_on")));
                    list.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
