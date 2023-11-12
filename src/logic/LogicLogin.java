/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.sql.Blob;
import siopet.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import siopet.config;


/**
 *
 * @author WINDOWS 10
 */
//method login awal
public class LogicLogin {
    public static String level;
    public static String userId;
    public static String nama;
    
    public boolean log(String username, String password){
        try {
            String query = "SELECT * FROM tb_pegawai WHERE id_pegawai ='"+ username +"' AND pass ='" + password + "'";
            Connection koneksi = (Connection)config.configDB();
            PreparedStatement pst = koneksi.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                String lvl = res.getString("jabatan");
                this.level = res.getString("jabatan");
                this.userId = res.getString("id_pegawai");
                this.nama = res.getString("nama_pegawai");
                return true;
            }else{
                  return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
                return false;
        }
    }
    //method lupa1
    public boolean forgetPassword(String username,String pin ){
        try {
            String query = "SELECT * FROM tb_pegawai WHERE id_pegawai ='"+ username +"' AND pin ='" + pin + "'";
            Connection koneksi = (Connection)config.configDB();
            PreparedStatement pst = koneksi.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            
            if(res.next()){
               this.userId = res.getString("id_pegawai");
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean changePassword(String password){
        try {
            String query = "UPDATE tb_pegawai SET pass ='" + password + "' WHERE id_pegawai ='" + userId + "'";
            Statement st = (Statement) config.configDB().createStatement();
            st.executeUpdate(query);
          return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    //method lupa2
    public boolean konfirmasiLupaPasswordUser(String username,int pin){
        try {
            String query = "SELECT * FROM tb_pegawai WHERE id_pegawai ='"+ username +"' AND pin ='" + pin + "' AND id = '"+userId+"'";
            Connection koneksi = (Connection)config.configDB();
            PreparedStatement pst = koneksi.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            
            if(res.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean ScanRFID(String username){
    try{
          String query = "SELECT * FROM tb_pegawai WHERE rfid ='"+ username +"'";
          Connection koneksi = (Connection)config.configDB();
          PreparedStatement pst = koneksi.prepareStatement(query);
          ResultSet res = pst.executeQuery();
          
          if(res.next()){
              String lvl = res.getString("jabatan");
                this.level = res.getString("jabatan");
                this.userId = res.getString("rfid");
                this.nama = res.getString("nama_pegawai");
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
    }
}
    
        
 
    //method untuk logout
    public void logOut(){
        level = "";
        userId = "";
        nama = "";
        new login().setVisible(true);
    }
}

