package siopet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static siopet.panel_layanan.cmb_edit_jenishewan;
import static siopet.panel_layanan.cmb_editpenitipan_jenishewan;
import static siopet.panel_layanan.edit_gromming;
import static siopet.panel_layanan.edit_penitipan;
import static siopet.panel_layanan.tf_edit_hargagromming;
import static siopet.panel_layanan.tf_edit_hargapenitipan;
import static siopet.panel_layanan.tf_edit_idgromming;
import static siopet.panel_layanan.tf_edit_idpenitipan;
import static siopet.panel_layanan.tf_edit_namagromming;
import static siopet.panel_layanan.tf_edit_namapenitipan;
import static siopet.panel_layanan.tf_editgromming_keterangan1;
import static siopet.panel_layanan.tf_editgromming_keterangan2;
import static siopet.panel_layanan.tf_editgromming_keterangan3;
import static siopet.panel_layanan.tf_editgromming_keterangan4;
import static siopet.panel_layanan.tf_editpenitipan_keterangan1;
import static siopet.panel_layanan.tf_editpenitipan_keterangan2;
import static siopet.panel_layanan.tf_editpenitipan_keterangan3;
import static siopet.panel_layanan.tf_editpenitipan_keterangan4;

/**
 *
 * @author nico flassy
 */
public class CRUD_Layanan {
    
    public static String getComboBoxJenisHewan(int index) {
        String tulisan;
        switch (index) {
            case 1:
                tulisan = "kucing";
                break;    
            case 2:
                tulisan = "anjing";
                break;
            case 3:
                tulisan = "domestik";   
                break;
            default:
                System.out.println("Erorr");
                tulisan = "";
        }
        return tulisan;
    }
    
    public static int setComboBoxJenisHewan(String tulisan) {
        int index;
        switch (tulisan) {
            case "Kucing":
                index = 1;
                break;    
            case "Anjing":
                index = 2;
                break;
            case "Domestik":
                index = 3;   
                break;
            default:
                System.out.println("Erorr");
                index = 0;
        }
        return index;
    }
    
    public static String getComboBoxKategori(int index) {
        String tulisan;
        switch (index) {
            case 1:
                tulisan = "makanan";
                break;    
            case 2:
                tulisan = "minuman";
                break;
            case 3:
                tulisan = "aksesoris";   
                break;
            case 4:
                tulisan = "aksesoris";   
                break;
            default:
                System.out.println("Erorr");
                tulisan = "";
        }
        return tulisan;
    }
    
    public static void add_gromming() {
        String id_gromming = panel_layanan.tf_input_idgromming.getText();
        String nama_gromming = panel_layanan.tf_input_namagromming.getText();
        int index_jenis_hewan = panel_layanan.cmb_inputgromming_jenishewan.getSelectedIndex();
        String jenis_hewan = getComboBoxJenisHewan(index_jenis_hewan);
        String harga = panel_layanan.tf_input_hargagromming.getText();
        String keterangan1 = panel_layanan.tf_input_keterangan1.getText();
        String keterangan2 = panel_layanan.tf_input_keterangan2.getText();
        String keterangan3 = panel_layanan.tf_input_keterangan3.getText();
        String keterangan4 = panel_layanan.tf_input_keterangan4.getText();
        
        try {
          String sql = "INSERT INTO tb_gromming VALUES ('"+id_gromming+"','"+nama_gromming+"','"+jenis_hewan+"','"+harga+"','"+keterangan1+"','"+keterangan2+"','"+keterangan3+"','"+keterangan4+"')";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Gromming Berhasil");
                panel_layanan.input_gromming.setVisible(false);
                panel_layanan pn_layanan = new panel_layanan();
                pn_layanan.getCardGrommingKucing();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    public static void add_penitipan() {
        String id_penitipan = panel_layanan.tf_input_idpenitipan.getText();
        String nama_penitipan = panel_layanan.tf_input_namapenitipan.getText();
        int index_jenis_hewan = panel_layanan.cmb_inputpenitipan_jenishewan.getSelectedIndex();
        String jenis_hewan = getComboBoxJenisHewan(index_jenis_hewan);
        String harga = panel_layanan.tf_input_hargapenitipan.getText();
        String keterangan1 = panel_layanan.tf_inputpenitipan_keterangan1.getText();
        String keterangan2 = panel_layanan.tf_inputpenitipan_keterangan2.getText();
        String keterangan3 = panel_layanan.tf_inputpenitipan_keterangan3.getText();
        String keterangan4 = panel_layanan.tf_inputpenitipan_keterangan4.getText();
        
        try {
          String sql = "INSERT INTO tb_penitipan VALUES ('"+id_penitipan+"','"+nama_penitipan+"','"+jenis_hewan+"','"+harga+"','"+keterangan1+"','"+keterangan2+"','"+keterangan3+"','"+keterangan4+"')";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Penitipan Berhasil");
                panel_layanan.input_penitipan.setVisible(false);
                panel_layanan pn_layanan = new panel_layanan();
                pn_layanan.getCardPenitipanKucing();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }   
    
    public static void edit_gromming() {
        String id_gromming = panel_layanan.tf_edit_idgromming.getText();
        String nama_gromming = panel_layanan.tf_edit_namagromming.getText();
        int index_jenis_hewan = panel_layanan.cmb_edit_jenishewan.getSelectedIndex();
        String jenis_hewan = getComboBoxJenisHewan(index_jenis_hewan);
        String harga = panel_layanan.tf_edit_hargagromming.getText();
        String keterangan1 = panel_layanan.tf_editgromming_keterangan1.getText();
        String keterangan2 = panel_layanan.tf_editgromming_keterangan2.getText();
        String keterangan3 = panel_layanan.tf_editgromming_keterangan3.getText();
        String keterangan4 = panel_layanan.tf_editgromming_keterangan4.getText();
        
        try {
          String sql = "UPDATE tb_gromming SET nama_gromming='"+nama_gromming+"',jenis_hewan='"+jenis_hewan+"',harga_gromming='"+harga+"',keterangan1='"+keterangan1+"',keterangan2='"+keterangan2+"',keterangan3='"+keterangan3+"',keterangan4='"+keterangan4+"' WHERE id_gromming = '"+id_gromming+"'";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Edit data Berhasil");
                panel_layanan.input_penitipan.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    public static void edit_penitipan() {
        String id_gromming = panel_layanan.tf_edit_idpenitipan.getText();
        String nama_gromming = panel_layanan.tf_edit_namapenitipan.getText();
        int index_jenis_hewan = panel_layanan.cmb_editpenitipan_jenishewan.getSelectedIndex();
        String jenis_hewan = getComboBoxJenisHewan(index_jenis_hewan);
        String harga = panel_layanan.tf_edit_hargapenitipan.getText();
        String keterangan1 = panel_layanan.tf_editpenitipan_keterangan1.getText();
        String keterangan2 = panel_layanan.tf_editpenitipan_keterangan2.getText();
        String keterangan3 = panel_layanan.tf_editpenitipan_keterangan3.getText();
        String keterangan4 = panel_layanan.tf_editpenitipan_keterangan4.getText();
        
        try {
          String sql = "UPDATE tb_penitipan SET nama_penitipan='"+nama_gromming+"',jenis_hewan='"+jenis_hewan+"',harga_penitipan='"+harga+"',keterangan1='"+keterangan1+"',keterangan2='"+keterangan2+"',keterangan3='"+keterangan3+"',keterangan4='"+keterangan4+"' WHERE id_penitipan = '"+id_gromming+"'";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Edit data Berhasil");
                panel_layanan.input_penitipan.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    public static void delete_layanan() {
        System.out.println("layanan");
    }
 
    public static void klik_edit_gromming(String id, String jeniss){
             try {
                String sql = "select * from tb_gromming where id_gromming = '"+id+"' and jenis_hewan = '"+jeniss+"'";
                java.sql.Connection conn=(Connection)siopet.config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);

                if (res.next()) {
                    String id_gromming = res.getString(1);
                    String nama_gromming = res.getString(2);
                    String jenis_hewan = res.getString(3);
                    String harga_gromming = res.getString(4);
                    String keterangan1 = res.getString(5);
                    String keterangan2 = res.getString(6);
                    String keterangan3 = res.getString(7);
                    String keterangan4 = res.getString(8);

                    tf_edit_idgromming.setText(id_gromming);
                    tf_edit_namagromming.setText(nama_gromming);
                    cmb_edit_jenishewan.setSelectedIndex(CRUD_Layanan.setComboBoxJenisHewan(jenis_hewan));
                    tf_edit_hargagromming.setText(harga_gromming);
                    tf_editgromming_keterangan1.setText(keterangan1);
                    tf_editgromming_keterangan2.setText(keterangan2);
                    tf_editgromming_keterangan3.setText(keterangan3);
                    tf_editgromming_keterangan4.setText(keterangan4);
                    
                    edit_gromming.setVisible(true);
                    id = null;
                }
            } catch (Exception e) {
            }
    }
    
    public static void klik_edit_penitipan(String id, String jeniss){
            try {
                String sql = "select * from tb_penitipan where id_penitipan = '"+id+"' and jenis_hewan ='"+jeniss+"'";
                java.sql.Connection conn=(Connection)siopet.config.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);

                if (res.next()) {
                    String id_penitipan = res.getString(1);
                    String nama_penitipan = res.getString(2);
                    String jenis_hewan = res.getString(3);
                    String harga_penitipan = res.getString(4);
                    String keterangan1 = res.getString(5);
                    String keterangan2 = res.getString(6);
                    String keterangan3 = res.getString(7);
                    String keterangan4 = res.getString(8);

                    tf_edit_idpenitipan.setText(id_penitipan);
                    tf_edit_namapenitipan.setText(nama_penitipan);
                    cmb_editpenitipan_jenishewan.setSelectedIndex(CRUD_Layanan.setComboBoxJenisHewan(jenis_hewan));
                    tf_edit_hargapenitipan.setText(harga_penitipan);
                    tf_editpenitipan_keterangan1.setText(keterangan1);
                    tf_editpenitipan_keterangan2.setText(keterangan2);
                    tf_editpenitipan_keterangan3.setText(keterangan3);
                    tf_editpenitipan_keterangan4.setText(keterangan4);
                    
                    edit_penitipan.setVisible(true);
                    id = null;
                }
            } catch (Exception e) {
            }
    }
}
