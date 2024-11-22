package org.managment.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/machine_managment" ;
    private  static  final String USER = "root";
    private  static  final String PASSWORD = "password123";

    private Database(){}

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}