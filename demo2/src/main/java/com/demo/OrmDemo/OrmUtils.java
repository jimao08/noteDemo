package com.demo.OrmDemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class OrmUtils {


    public static Connection createConnection(String url, String user, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(true);
        return connection;
    }
}
