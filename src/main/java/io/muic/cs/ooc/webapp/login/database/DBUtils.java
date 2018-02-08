package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    public static User findUser(Connection connection, String username, String password) throws SQLException {
        String sql = "select user.username, user.password from User user" +
                " where user.username = ? and user.password = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static User findUser(Connection connection, String username) throws SQLException {
        String sql = "select user.username, user.password from User user" +
                "where user.username = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            String password = resultSet.getString("password");
            user.setPassword(password);
            user.setUsername(username);
            return user;
        }
        return null;
    }
}
