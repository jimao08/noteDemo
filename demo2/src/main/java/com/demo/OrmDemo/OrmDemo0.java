package com.demo.OrmDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * jdbc test
 */
public class OrmDemo0 {

    public static void main(String[] args) throws Exception {

        Connection connection = OrmUtils.createConnection("jdbc:mysql://localhost:3306/test", "root", "123");


        String sql = "select * from dmp_user";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);


        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println(username + ":" + password);
        }

        connection.close();

    }


}
