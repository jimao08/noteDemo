package com.demo.OrmDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * jdbc test
 * PreparedStatement
 */
public class OrmDemo3 {

    public static void main(String[] args) throws Exception {

        Connection connection = OrmUtils.createConnection("jdbc:mysql://localhost:3306/test", "root", "123");


        String sql = "SELECT * FROM dmp_test WHERE report_date=" + "'2018-04-13'";

//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, "2018-04-13");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String uid = resultSet.getString("uid");
            String name = resultSet.getString("name");
            System.out.println(id + ":" + uid + ">" + name);
        }

        connection.close();

    }


}
