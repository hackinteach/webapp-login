package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3307/login?useSSL=false";
    private static final String dbUser = "ooc";
    private static final String dbPassword = "muic";
    private static final String dbTable = "credentials";
    private static Connection connection = null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private Connection getConnection() {
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

    public void emptyDb(){
        try {
            Statement statement = getConnection().createStatement();
            String query = "truncate "+dbTable;
            statement.addBatch(query);
            int[] result = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //@TODO Check user exists.

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "select username, password from " + dbTable;
            preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public boolean authenticate(String username, String password) {

        try {
            String sql = "select password from "+ dbTable+" where username=?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                String pwdFromDB = resultSet.getString("password");
                return pwdFromDB.equals(password);
            } else {
                System.out.println("Error querying database.");
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

    public boolean createUser(String username, String password){
        try{
            String sql = "insert into " + dbTable + "(username,password) values (?,?);";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserbyUsername(String username){

        try{
            String sql = "select username, password from "+dbTable+" where username = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
