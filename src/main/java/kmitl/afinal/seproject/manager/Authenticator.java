package kmitl.afinal.seproject.manager;

import kmitl.afinal.seproject.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {

    public User validate(String username, String password, Connection connection) throws SQLException {
        String sql = "SELECT fname, lname, email, create_time, update_time FROM `user` WHERE username = ? AND password = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();

        User user = null;

        if (rs.next()) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setEmail(rs.getString("email"));
            user.setCreateTime(rs.getString("create_time"));
            user.setUpdateTime(rs.getString("update_time"));
            return user;
        }
        return user;
    }

    public boolean register(User user, Connection connection) throws SQLException {
        String sql = "INSERT INTO user(username, password, fname, lname, email) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user.getUsername());
        stm.setString(2, user.getPassword());
        stm.setString(3, user.getFname());
        stm.setString(4, user.getLname());
        stm.setString(5, user.getEmail());

        int row = stm.executeUpdate();

        return row > 0;
    }

    public boolean update(User user, Connection connection) throws SQLException {
        String sql = "UPDATE user SET password=?, fname=?, lname=?, email=? WHERE username = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user.getPassword());
        stm.setString(2, user.getFname());
        stm.setString(3, user.getLname());
        stm.setString(4, user.getEmail());
        stm.setString(5, user.getUsername());

        int row = stm.executeUpdate();

        return row > 0;
    }

}
