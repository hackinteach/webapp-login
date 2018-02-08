package io.muic.cs.ooc.webapp.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostname = "localhost";
        String port = "3307";
        String dbName = "login";
        String username = "ooc";
        String password = "muic";
        return getMySQLConnection(hostname,port,username,password,dbName);
    }

    public static Connection getMySQLConnection(String hostName,String port,String username, String password, String dbName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        /* MYSQL url connection */
        String connUrl = "jdbc:mysql://"+hostName+":"+port+"/"+dbName;
        Connection conn = DriverManager.getConnection(connUrl,username,password);
        return conn;
    }
}
