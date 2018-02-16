package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.utils.HashingUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {

//    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3307/login?useSSL=false";
    private static final String dbUrl = "jdbc:mysql://database:3306/login?useSSL=false";
    private static final String dbUser = "ooc";
    private static final String dbPassword = "muic";
    private static final String dbTable = "credentials";
    private static Connection connection = null;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            System.out.println("Connection is NULL!");
        }
        return connection;
    }

    public static void emptyDb() {
        try {
            Statement statement = getConnection().createStatement();
            String query = "truncate " + dbTable;
            statement.addBatch(query);
            int[] result = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateInfo(String id, String column, String update) {
        try {
            String query;
            if (column.equals("password")) {
                String hpassword = HashingUtil.hashPassword(update);
                query = "update " + dbTable + " set `hashpassword`=? where `id`=?;";
                preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1,hpassword);
                preparedStatement.setString(2,id);
            } else {
                query = "update " + dbTable + " set ?=? where `id`=?;";
                preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1,"'"+column+"'");
                preparedStatement.setString(2,"'"+update+"'");
                preparedStatement.setString(2,"'"+id+"'");
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isUserExists(String username) {
        try {
            String query = "select username from " + dbTable + " where username=?;";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailExists(String email) {
        try {
            String query = "select email from " + dbTable + " where email=?;";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "select * from " + dbTable;
            preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setid(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setHashpassword(resultSet.getString("hashpassword"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean authenticate(String username, String password) {

        try {
            String sql = "select hashpassword from " + dbTable + " where username=?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                String hash = resultSet.getString("hashpassword");
                return HashingUtil.verifyPassword(hash, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }

                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean createUser(String username, String password,
                                     String firstname, String lastname,
                                     String email) {
        if (isUserExists(username) || isEmailExists(email)) {
            return false;
        }
        try {
            String hashedPassword = HashingUtil.hashPassword(password);
            String sql = "insert into " + dbTable + "(username,hashpassword,firstname,lastname,email) values (?,?,?,?,?);";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean removeUserbyUsername(String username) {
        if (!isUserExists(username)) {
            return false;
        }
        try {
            String sql = "delete from " + dbTable + " where username = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUserbyUsername(String username) {

        try {
            String sql = "select * from " + dbTable + " where username = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                User user = new User();
                user.setid(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setHashpassword(resultSet.getString("hashpassword"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
