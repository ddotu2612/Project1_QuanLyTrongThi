package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance=new DBConnection();
    public DBConnection(){

    }
    public static DBConnection getInstance(){
        return instance;
    }

    public Connection getConnection(){
        Connection conn=null;
        String url="jdbc:sqlserver://localhost:1433;DatabaseName=QuanLyTT;user=sa;password=1234;";
        try {
            conn= DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
