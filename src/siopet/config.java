
package siopet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class config {
    public static Connection mysqlconfig;
    public static Connection configDB()throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/siopet";//url database
            String user = "root";//user database
            String pass = "";//password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
        } catch (Exception e){
            System.err.println("Koneksi Gagal "+e.getMessage());
            //perintah meanmpilkan error pada koneksi
        }
        return mysqlconfig;
    }
}
