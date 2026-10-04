package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

public class AdminManageServiceImpl implements AdminManageService {

    @Override
    public int[] getSimilarityValue() {
        int[] bands = new int[6];
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT score FROM similarity_log")) {
            while (rs.next()) {
                int s = rs.getInt(1);
                bands[s >= 100 ? 5 : s / 20]++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bands;
    }
}
