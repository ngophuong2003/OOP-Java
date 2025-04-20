package btl_oop.dao;

import java.sql.DriverManager;
import java.sql.Connection;

public abstract class DAO {
    protected Connection con ;
    public DAO (){
        if(con == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/quanlysinhvien?autoReconnect=true&useSSL=false";
                String username = "root";
                String password = "123456";
                con = DriverManager.getConnection(url,username,password);
 //               System.out.println("sucessfully");
            }
            catch(Exception e){
                System.out.println("FAIL");
            }
        }
    }
    
    public static void main(String[] args) {
        DAO d = new DAO() {
            @Override
            public Object getById(int Record_id) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean addObject(Object object) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean updateObject(Object object) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public boolean deleteObject(int objectId) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }
    public abstract Object getById(int Record_id);
    public abstract boolean addObject(Object object);
    public abstract boolean updateObject(Object object);
    public abstract boolean deleteObject(int objectId);
   
}

