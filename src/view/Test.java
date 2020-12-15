package view;

import controller.DBController;
import controller.DataControler;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            if(!new DBController().checkExistTable("GiangVien" + "20191")) {
//                String sheetName="GiangVien20192";
//                FileInputStream fileInputStream=new FileInputStream("C:\\Users\\dovan\\OneDrive\\Documents\\GiangVien.xlsx");
//                XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
//                XSSFSheet sheet=workbook.getSheet(sheetName);
//                if(sheet != null) System.out.println(" haaha");
//                else System.out.println("hihi");
            }
            else {
                System.out.println("sai");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
