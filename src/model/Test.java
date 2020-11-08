package model;

import controller.DBConnection;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException, SQLException {
        String sql="select * from dbo.TaiKhoan where userName=? and password=? and role=?";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        prepare.setString(1,"dotu29012000");
        prepare.setString(2,"1234");
        prepare.setString(3,"Quản lý");
        var res=prepare.executeQuery();
        if(res.next()){
            System.out.println(res.getString("role"));
        }else{
            System.out.println("Loi connect");
        }
    }
}
