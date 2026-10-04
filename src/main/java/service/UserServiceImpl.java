package service;

import bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;
import util.PasswordUtil;

public class UserServiceImpl implements UserService {

    @Override
    public boolean register(UserBean u) {
        String check = "SELECT 1 FROM register WHERE name=? OR email=?";
        String insert = "INSERT INTO register(name,email,password,mobile,gender,address) VALUES(?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(check)) {
                ps.setString(1, u.getName());
                ps.setString(2, u.getEmail());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return false;
                }
            }
            try (PreparedStatement ps = con.prepareStatement(insert)) {
                ps.setString(1, u.getName());
                ps.setString(2, u.getEmail());
                ps.setString(3, PasswordUtil.hash(u.getPassword()));
                ps.setString(4, u.getMobile());
                ps.setString(5, u.getGender());
                ps.setString(6, u.getAddress());
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserBean login(String id, String password) {
        String sql = "SELECT id,name,email,password,mobile,gender,address FROM register WHERE name=? OR email=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verify(password, rs.getString("password"))) {
                    UserBean u = new UserBean();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setMobile(rs.getString("mobile"));
                    u.setGender(rs.getString("gender"));
                    u.setAddress(rs.getString("address"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
