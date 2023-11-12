/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package siopet.Admin;

import siopet.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import scrollbar.custom.ScrollBarCustom;
import static siopet.CRUD_Layanan.setComboBoxJenisHewan;
import swing.jScrollBar.vertical.ScrollBarCustom1;


/**
 *
 * @author nico flassy
 */
public class panel_layanan extends javax.swing.JPanel {

    String filename;
    public String nomorPanel;
    public ArrayList<ArrayList<String>> arrayUtama;
    public int jumlahPanel = 1;
    JPanel container_gromming = new JPanel();
    JPanel container_penitipan = new JPanel();
    String optionNavbarHewan;
    
    public panel_layanan() {
        initComponents();
        getCardBarang();
        getCardGrommingKucing();
        getCardGrommingAnjing();
        getCardGrommingDomestik();
        getCardPenitipanKucing();
        getCardPenitipanAnjing();
        getCardPenitipanDomestik();
        layanan_gromming.setVisible(true);
        layanan_barang.setVisible(false);
        edit_gromming.setVisible(false);
        input_data_barang1.setVisible(false);
        input_penitipan.setVisible(false);
        input_gromming.setVisible(false);
        edit_data_barang1.setVisible(false);
        edit_penitipan.setVisible(false);    
        keranjang.setVisible(false);
        arrayUtama = new ArrayList();
        add_jumlah.setVisible(false);
        setJumlah();
        tampilJumlahNotif();
        //gromming
        optionhewan_kucing.setVisible(true);
        optionhewan_anjing.setVisible(false);
        optionhewan_domestik.setVisible(false);
        //penitipan
        optionhewan_kucing1.setVisible(true);
        optionhewan_anjing1.setVisible(false);
        optionhewan_domestik1.setVisible(false);

        container_gromming.setLayout(null);
        container_penitipan.setLayout(null);
        popUp_keluar2.setVisible(false);
        
        ScrollPaneBarang.setVerticalScrollBar(new ScrollBarCustom());
        ScrollPaneGromming.setVerticalScrollBar(new ScrollBarCustom());
        ScrollPanePenitipan.setVerticalScrollBar(new ScrollBarCustom());
        ScrollPaneKeranjang.setVerticalScrollBar(new ScrollBarCustom());
    }
    
    
    public void addJumlah(){
        // Data baru yang ingin dimasukkan
        String jumlahh = tf_jumlah.getText();
        int newData = Integer.valueOf(jumlahh);
        // Mencari elemen array dengan ID yang sesuai
        for (ArrayList<String> innerArray : arrayUtama) {
            String id = innerArray.get(0);
            if (id.equals(nomorPanel)) {
                // Menambahkan data baru ke elemen array yang memiliki ID yang sesuai
                innerArray.add(String.valueOf(newData));
                break; // Keluar dari loop setelah menemukan elemen dengan ID yang sesuai
            }
        }
    }
    
    public void removeDataById(String id) {
    // Mencari elemen array dengan ID yang sesuai
    for (ArrayList<String> innerArray : arrayUtama) {
        String arrayId = innerArray.get(0);
        if (arrayId.equals(id)) {
            // Menghapus elemen array yang memiliki ID yang sesuai
            arrayUtama.remove(innerArray);
            break; // Keluar dari loop setelah menghapus elemen dengan ID yang sesuai
        }
    }
}

    
    public void setJumlah(){
        tf_jumlah.setText(String.valueOf(jumlahPanel));
    }
    
    public void getCardBarang(){
        JPanel container = new JPanel();
        container.setLayout(null);
        try {
            String sql = "select * from tb_barang";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            if (res.next()) {
                int baris = 0;
                int kolom = 0;
                int marginKiri = 55;
                int marginAtas = 25;
                do {
                    String id_barang = res.getString(1);
                    String nama_barang = res.getString(2);
                    String kategori = res.getString(4);
                    String harga_barang = res.getString(7);
                    String jenis_hewan = res.getString(3);
                    card_barang pn = new card_barang();
                    pn.setSize(221,257);
                    pn.nama.setText(nama_barang);
                    pn.keterangan.setText(kategori);
                    pn.harga_barang.setText("Rp "+harga_barang);

                    // Pengkondisian
                    if (jenis_hewan.equals("Kucing")) {
                        pn.gambar_kucing.setVisible(true);
                    } else if (jenis_hewan.equals("Anjing")) {
                        pn.gambar_anjing.setVisible(true);
                    } else if(jenis_hewan.equals("Domestik")){
                        pn.gambar_domestik.setVisible(true);
                    }

                    if (kolom <= 4) {
                        pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 280); 
                    } else {
                        baris++;
                        kolom = 0;
                        pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 280); 
                    }
                    pn.addMouseListener(new MouseAdapter() {
                    @Override
                    //mouse clickk ni boss
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        nomorPanel = id_barang;
                        ArrayList<String> array1 = new ArrayList();
                        array1.add(id_barang);
                        array1.add(nama_barang);
                        array1.add(kategori);
                        array1.add(harga_barang);
                        arrayUtama.add(array1); 
                        System.out.println(arrayUtama);
                        add_jumlah.setVisible(true);
                    }
                    });
                    kolom++;
                    container.add(pn);
                } while (res.next());
            }
            
            container.setPreferredSize(new Dimension(300, 100*16));
            ScrollPaneBarang.setViewportView(container); 
        } catch (Exception e) {
        }
    }
    

    public void getCardGrommingKucing(){
        try {
            String sql = "select * from tb_gromming where jenis_hewan = 'Kucing'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_gromming = res.getString(1);
                String nama_gromming = res.getString(2);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_gromming = res.getString(4);
                
                card_gromming pn = new card_gromming();
                pn.setSize(222,309);
                pn.nama_gromming.setText(nama_gromming);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_barang.setText("Rp "+harga_gromming);
                
                pn.bg_gromming_kucing.setVisible(true);
                pn.bg_gromming_anjing.setVisible(false);
                pn.bg_gromming_domestik.setVisible(false);
                
                
                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_gromming.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_gromming;
                    try {
                        nomorPanel = id_gromming;
                        ArrayList<String> array2 = new ArrayList();
                        array2.add(id_gromming);
                        array2.add(nama_gromming);
                        array2.add(harga_gromming);
                        array2.add("1");
                        arrayUtama.add(array2); 
                        System.out.println(arrayUtama);
                            String sql = "INSERT INTO tb_keranjang (id_barang, nama_barang, harga_barang, jumlah) VALUES (?, ?, ?, ?)";
                          java.sql.Connection conn=(Connection)config.configDB();
                          java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                            for (ArrayList<String> dataRow : arrayUtama) {
                                pst.setString(1, dataRow.get(0)); 
                                pst.setString(2, dataRow.get(1)); 
                                pst.setString(3, dataRow.get(3)); 
                                pst.setString(4, dataRow.get(4)); 
                                pst.execute();
                               

                            }
                             new form_transaksiBarang().setVisible(true);
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null, ee.getMessage());
                    }
                }
                });
                
            }
            container_gromming.setPreferredSize(new Dimension(300, 100*16));
            ScrollPaneGromming.setViewportView(container_gromming); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getCardGrommingAnjing(){
        try {
            String sql = "select * from tb_gromming where jenis_hewan = 'Anjing'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_gromming = res.getString(1);
                String nama_gromming = res.getString(2);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_gromming = res.getString(4);
                
                card_gromming pn = new card_gromming();
                pn.setSize(222,309);
                pn.nama_gromming.setText(nama_gromming);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_barang.setText("Rp "+harga_gromming);
                
                pn.bg_gromming_kucing.setVisible(false);
                pn.bg_gromming_anjing.setVisible(true);
                pn.bg_gromming_domestik.setVisible(false);

                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_gromming.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_gromming;
                    JOptionPane.showMessageDialog(null, nomorPanel);
                }
                });
                
            }
            container_gromming.setPreferredSize(new Dimension(300, 100*16));
            ScrollPaneGromming.setViewportView(container_gromming); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getCardGrommingDomestik(){
        try {
            String sql = "select * from tb_gromming where jenis_hewan = 'Domestik'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_gromming = res.getString(1);
                String nama_gromming = res.getString(2);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_gromming = res.getString(4);
                
                card_gromming pn = new card_gromming();
                pn.setSize(222,309);
                pn.nama_gromming.setText(nama_gromming);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_barang.setText("Rp "+harga_gromming);

                pn.bg_gromming_kucing.setVisible(false);
                pn.bg_gromming_anjing.setVisible(false);
                pn.bg_gromming_domestik.setVisible(true);
                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_gromming.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_gromming;
                    JOptionPane.showMessageDialog(null, nomorPanel);
                }
                });
                
            }
            container_gromming.setPreferredSize(new Dimension(300, 100*16));
            ScrollPaneGromming.setViewportView(container_gromming); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getCardPenitipanKucing(){
        try {
            String sql = "select * from tb_penitipan where jenis_hewan = 'Kucing'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_penitipan = res.getString(1);
                String nama_penitipan = res.getString(2);
                String jenis_hewan = res.getString(3);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_penitipan = res.getString(4);
                
                card_penitipan pn = new card_penitipan();
                pn.setSize(222,309);
                card_penitipan.icon1.setVisible(false);
                card_penitipan.icon2.setVisible(false);
                card_penitipan.icon3.setVisible(false);
                pn.nama_penitipan.setText(nama_penitipan);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_penitipan.setText("Rp "+harga_penitipan);
                if (jenis_hewan.equals("Kucing")) {
//                    pn.gambar_kucing.setVisible(true);
//                } else if (jenis_hewan.equals("Anjing")) {
//                    pn.gambar_anjing.setVisible(true);
//                } else if(jenis_hewan.equals("Domestik")){
//                    pn.gambar_domestik.setVisible(true);
//                }
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                }
                });
                }

                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_penitipan.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_penitipan;
                    JOptionPane.showMessageDialog(null, nomorPanel);
                }
                });
            }
            container_penitipan.setPreferredSize(new Dimension(300, 100*16));
            ScrollPanePenitipan.setViewportView(container_penitipan); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getCardPenitipanAnjing(){
        try {
            String sql = "select * from tb_penitipan where jenis_hewan = 'anjing'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_penitipan = res.getString(1);
                String nama_penitipan = res.getString(2);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_penitipan = res.getString(4);
                
                card_penitipan pn = new card_penitipan();
                pn.setSize(222,309);
                card_penitipan.icon1.setVisible(false);
                card_penitipan.icon2.setVisible(false);
                card_penitipan.icon3.setVisible(false);
                pn.nama_penitipan.setText(nama_penitipan);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_penitipan.setText("Rp "+harga_penitipan);

                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                }
                });
//                if (jenis_hewan.equals("Kucing")) {
//                    pn.gambar_kucing.setVisible(true);
//                } else if (jenis_hewan.equals("Anjing")) {
//                    pn.gambar_anjing.setVisible(true);
//                } else if(jenis_hewan.equals("Domestik")){
//                    pn.gambar_domestik.setVisible(true);
//                }

                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_penitipan.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_penitipan;
                    JOptionPane.showMessageDialog(null, nomorPanel);
                }
                });
            }
            container_penitipan.setPreferredSize(new Dimension(300, 100*16));
            ScrollPanePenitipan.setViewportView(container_penitipan); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getCardPenitipanDomestik(){
        try {
            String sql = "select * from tb_penitipan where jenis_hewan = 'domestik'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            int baris = 0;
            int kolom = 0;
            int marginKiri = 55;
            int marginAtas = 25;
     
            while(res.next()){
                String id_penitipan = res.getString(1);
                String nama_penitipan = res.getString(2);
                String keterangan1 = res.getString(5);
                String keterangan2 = res.getString(6);
                String keterangan3 = res.getString(7);
                String keterangan4 = res.getString(8);
                String harga_penitipan = res.getString(4);
                
                card_penitipan pn = new card_penitipan();
                pn.setSize(222,309);
                card_penitipan.icon1.setVisible(false);
                card_penitipan.icon2.setVisible(false);
                card_penitipan.icon3.setVisible(false);
                pn.nama_penitipan.setText(nama_penitipan);
                pn.keterangan1.setText(keterangan1);
                pn.keterangan2.setText(keterangan2);
                pn.keterangan3.setText(keterangan3);
                pn.keterangan4.setText(keterangan4);
                pn.harga_penitipan.setText("Rp "+harga_penitipan);

                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                }
                });
//                if (jenis_hewan.equals("Kucing")) {
//                    pn.gambar_kucing.setVisible(true);
//                } else if (jenis_hewan.equals("Anjing")) {
//                    pn.gambar_anjing.setVisible(true);
//                } else if(jenis_hewan.equals("Domestik")){
//                    pn.gambar_domestik.setVisible(true);
//                }

                if (kolom <= 4) {
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                } else {
                    baris++;
                    kolom = 0;
                    pn.setLocation(marginKiri + kolom * 260, marginAtas + baris * 340); 
                }
                container_penitipan.add(pn);
                kolom++;
                pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    nomorPanel = id_penitipan;
                    JOptionPane.showMessageDialog(null, nomorPanel);
                }
                });
            }
            container_penitipan.setPreferredSize(new Dimension(300, 100*16));
            ScrollPanePenitipan.setViewportView(container_penitipan); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void getPanel(){
//         try {
//            String sql = "select * from tb_barang";
//            java.sql.Connection conn=(Connection)config.configDB();
//            java.sql.Statement stm=conn.createStatement();
//            java.sql.ResultSet res=stm.executeQuery(sql);
//            int baris = 1;
//            int kolom = 0;
//            int marginKiri = 400;
//     
//            while(res.next()){
//                String id_barang = res.getString(1);
//                String nama_barang = res.getString(2);
//                String harga_barang = res.getString(7);
//                String jenis_hewan = res.getString(4);
//                card_barang pn = new card_barang();
//                pn.setSize(221,257);
//                pn.nama.setText(id_barang);
//                pn.nama.setText(nama_barang);
//                pn.harga_barang.setText(harga_barang);
//                if (jenis_hewan.equals("Kucing")) {
//                    pn.gambar_kucing.setVisible(true);
//                } else if (jenis_hewan.equals("Anjing")) {
//                    pn.gambar_anjing.setVisible(true);
//                } else if(jenis_hewan.equals("Domestik")){
//                    pn.gambar_domestik.setVisible(true);
//                }
//                pn.setOpaque(false);
//                this.add(pn, new JLayeredPane());
//                this.setComponentZOrder(pn, 0);
//                this.revalidate();
//                this.repaint();
//                if (kolom <= 4) {
//                    pn.setLocation(marginKiri + kolom * 260, baris * 280); 
//                    baris++;
//                    kolom = 0;
//                    pn.setLocation(marginKiri + kolom * 260, baris * 280); 
//                }
//                kolom++;
//                pn.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    super.mouseClicked(e);
//                    //JOptionPane.showMessageDialog(null, "Panel nomor " + id_barang + " diklik");
//                    //nomor_panel.setText(id_barang);
//                    nomorPanel = id_barang;
//                        JOptionPane.showMessageDialog(null, nomorPanel);
//                }
//                });
//            }
//        } catch (Exception e) {
//        }   
//    }
    
    //keranjangggg
    public void addKeranjang(){
        JPanel container = new JPanel();
        container.setLayout(null);
        int baris = 0;
        for (ArrayList<String> innerArray : arrayUtama) {
            String nama_barang = innerArray.get(1);
            String kategori = innerArray.get(2);
            int harga = Integer.valueOf(innerArray.get(3));
            int jumlah = Integer.valueOf(innerArray.get(4));
            final item_keranjang pn = new item_keranjang(nama_barang, kategori, harga, jumlah);
            pn.setSize(489, 105);
            //pn.addMouseListener(new KeranjangClikLisneter(pn));
            pn.setLocation(0, baris * 100);
            baris++;
            container.add(pn);
        }
        container.setPreferredSize(new Dimension(489, baris * 100));
        ScrollPaneKeranjang.setViewportView(container);
    }
    
    public void tampilJumlahNotif(){
        int jumlah_notif = arrayUtama.size();
        lbl_jumlah_notif.setText(Integer.toString(jumlah_notif));
        if (jumlah_notif == 0) {
            lbl_jumlah_notif.setVisible(false);
            bg_jumlah_notif.setVisible(false);
        }else{
            lbl_jumlah_notif.setVisible(true);
            bg_jumlah_notif.setVisible(true);
        }
    }
    
    public String getItemJenisHewan(int idx){
        switch(idx){
            case 0:
                return "Kucing";
            case 1:
                return "Anjing";
            case 2:
                return "Domestik";
            default:
                return "Error";
        }
        
    }
    
    public int setItemJenisHewan(String text){
        switch(text){
            case "Kucing":
                return 0;
            case "Anjing":
                return 1;
            case "Domestik":
                return 2;
            default:
                return 3;
        } 
    }
    
    public String getItemKategori(int idx){
        switch(idx){
            case 0:
                return "Makanan";
            case 1:
                return "Aksesoris";
            case 2:
                return "Perlengkapan";
            default:
                return "Error";
        }
    }
    
    public int setItemKategori(String text){
        switch(text){
            case "Makanan":
                return 0;
            case "Aksesoris":
                return 1;
            case "Perlengkapan":
                return 2;
            default:
                return 3;
        } 
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp_keluar2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bg_keluar2 = new javax.swing.JLabel();
        nav_penitipan = new javax.swing.JLabel();
        nav_gromming = new javax.swing.JLabel();
        nav_barang = new javax.swing.JLabel();
        add_jumlah = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tambah_jumlah = new javax.swing.JLabel();
        btn_minus = new javax.swing.JLabel();
        btn_plus = new javax.swing.JLabel();
        tf_jumlah = new javax.swing.JLabel();
        bg_jumlah = new javax.swing.JLabel();
        keranjang = new javax.swing.JPanel();
        btn_lanjut = new javax.swing.JLabel();
        ScrollPaneKeranjang = new javax.swing.JScrollPane();
        bg_card = new javax.swing.JLabel();
        layanan_barang = new javax.swing.JPanel();
        edit_data_barang1 = new javax.swing.JPanel();
        tf_idbarang2 = new javax.swing.JTextField();
        tf_namabarang2 = new javax.swing.JTextField();
        cmb_jenishewan2 = new javax.swing.JComboBox<>();
        cmb_kategori2 = new javax.swing.JComboBox<>();
        tf_merkbarang2 = new javax.swing.JTextField();
        tf_stokbarang2 = new javax.swing.JTextField();
        tf_hargabeli2 = new javax.swing.JTextField();
        tf_hargajual2 = new javax.swing.JTextField();
        btn_add2 = new javax.swing.JLabel();
        btn_close2 = new javax.swing.JLabel();
        tf_linkgambar2 = new javax.swing.JTextField();
        img2 = new javax.swing.JLabel();
        bg_inputbarang2 = new javax.swing.JLabel();
        input_data_barang1 = new javax.swing.JPanel();
        tf_idbarang3 = new javax.swing.JTextField();
        tf_namabarang3 = new javax.swing.JTextField();
        cmb_jenishewan3 = new javax.swing.JComboBox<>();
        cmb_kategori3 = new javax.swing.JComboBox<>();
        tf_merkbarang3 = new javax.swing.JTextField();
        tf_stokbarang3 = new javax.swing.JTextField();
        tf_hargabeli3 = new javax.swing.JTextField();
        tf_hargajual3 = new javax.swing.JTextField();
        btn_add3 = new javax.swing.JLabel();
        btn_close3 = new javax.swing.JLabel();
        btn_upload1 = new javax.swing.JLabel();
        img3 = new javax.swing.JLabel();
        tf_linkgambar3 = new javax.swing.JTextField();
        bg_inputbarang3 = new javax.swing.JLabel();
        ScrollPaneBarang = new javax.swing.JScrollPane();
        bg_jumlah_notif = new javax.swing.JLabel();
        lbl_jumlah_notif = new javax.swing.JLabel();
        btn_cart = new javax.swing.JLabel();
        btn_keluar1 = new javax.swing.JLabel();
        bg1 = new javax.swing.JLabel();
        layanan_penitipan = new javax.swing.JPanel();
        edit_penitipan = new javax.swing.JPanel();
        tf_edit_hargapenitipan = new javax.swing.JTextField();
        tf_edit_idpenitipan = new javax.swing.JTextField();
        tf_edit_namapenitipan = new javax.swing.JTextField();
        cmb_editpenitipan_jenishewan = new javax.swing.JComboBox<>();
        tf_editpenitipan_keterangan1 = new javax.swing.JTextField();
        tf_editpenitipan_keterangan2 = new javax.swing.JTextField();
        tf_editpenitipan_keterangan3 = new javax.swing.JTextField();
        tf_editpenitipan_keterangan4 = new javax.swing.JTextField();
        btn_simpan_penitipan = new javax.swing.JLabel();
        btn_close4 = new javax.swing.JLabel();
        bg_inputbarang4 = new javax.swing.JLabel();
        input_penitipan = new javax.swing.JPanel();
        tf_inputpenitipan_keterangan4 = new javax.swing.JTextField();
        tf_inputpenitipan_keterangan3 = new javax.swing.JTextField();
        tf_inputpenitipan_keterangan2 = new javax.swing.JTextField();
        tf_inputpenitipan_keterangan1 = new javax.swing.JTextField();
        tf_input_hargapenitipan = new javax.swing.JTextField();
        tf_input_idpenitipan = new javax.swing.JTextField();
        tf_input_namapenitipan = new javax.swing.JTextField();
        cmb_inputpenitipan_jenishewan = new javax.swing.JComboBox<>();
        btn_close5 = new javax.swing.JLabel();
        btn_tambah_penitipan = new javax.swing.JLabel();
        bg_inputbarang5 = new javax.swing.JLabel();
        nav_optionhewan_domestik1 = new javax.swing.JLabel();
        nav_optionhewan_anjing1 = new javax.swing.JLabel();
        nav_optionhewan_kucing1 = new javax.swing.JLabel();
        optionhewan_domestik1 = new javax.swing.JLabel();
        optionhewan_anjing1 = new javax.swing.JLabel();
        optionhewan_kucing1 = new javax.swing.JLabel();
        btn_tambah2 = new javax.swing.JLabel();
        btn_edit2 = new javax.swing.JLabel();
        btn_delete2 = new javax.swing.JLabel();
        btn_keluar2 = new javax.swing.JLabel();
        ScrollPanePenitipan = new javax.swing.JScrollPane();
        bg2 = new javax.swing.JLabel();
        layanan_gromming = new javax.swing.JPanel();
        input_gromming = new javax.swing.JPanel();
        tf_input_keterangan4 = new javax.swing.JTextField();
        tf_input_keterangan3 = new javax.swing.JTextField();
        tf_input_keterangan2 = new javax.swing.JTextField();
        tf_input_keterangan1 = new javax.swing.JTextField();
        tf_input_hargagromming = new javax.swing.JTextField();
        tf_input_idgromming = new javax.swing.JTextField();
        tf_input_namagromming = new javax.swing.JTextField();
        cmb_inputgromming_jenishewan = new javax.swing.JComboBox<>();
        btn_add_gromming = new javax.swing.JLabel();
        btn_close_inputgromming = new javax.swing.JLabel();
        bg_inputgromming = new javax.swing.JLabel();
        edit_gromming = new javax.swing.JPanel();
        tf_edit_hargagromming = new javax.swing.JTextField();
        tf_edit_idgromming = new javax.swing.JTextField();
        tf_edit_namagromming = new javax.swing.JTextField();
        cmb_edit_jenishewan = new javax.swing.JComboBox<>();
        tf_editgromming_keterangan1 = new javax.swing.JTextField();
        tf_editgromming_keterangan2 = new javax.swing.JTextField();
        tf_editgromming_keterangan3 = new javax.swing.JTextField();
        tf_editgromming_keterangan4 = new javax.swing.JTextField();
        btn_simpan_editgromming = new javax.swing.JLabel();
        btn_close_editgromming = new javax.swing.JLabel();
        bg_inputbarang = new javax.swing.JLabel();
        nav_optionhewan_domestik = new javax.swing.JLabel();
        nav_optionhewan_anjing = new javax.swing.JLabel();
        nav_optionhewan_kucing = new javax.swing.JLabel();
        optionhewan_domestik = new javax.swing.JLabel();
        optionhewan_anjing = new javax.swing.JLabel();
        optionhewan_kucing = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        ScrollPaneGromming = new javax.swing.JScrollPane();
        bg = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        lbl_formPegawai = new javax.swing.JLabel();
        Logout2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 0, 0));
        setLayout(null);

        popUp_keluar2.setBackground(new Color(0, 0, 0, 200));
        popUp_keluar2.setLayout(null);

        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        popUp_keluar2.add(jLabel12);
        jLabel12.setBounds(1240, 390, 50, 50);

        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        popUp_keluar2.add(jLabel10);
        jLabel10.setBounds(980, 590, 160, 50);

        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        popUp_keluar2.add(jLabel11);
        jLabel11.setBounds(780, 590, 160, 50);

        bg_keluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Validasi LogOut.png"))); // NOI18N
        popUp_keluar2.add(bg_keluar2);
        bg_keluar2.setBounds(608, 380, 704, 320);

        add(popUp_keluar2);
        popUp_keluar2.setBounds(0, 0, 1920, 1080);

        nav_penitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_penitipanMouseClicked(evt);
            }
        });
        add(nav_penitipan);
        nav_penitipan.setBounds(790, 160, 190, 30);

        nav_gromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_grommingMouseClicked(evt);
            }
        });
        add(nav_gromming);
        nav_gromming.setBounds(600, 160, 190, 30);

        nav_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_barangMouseClicked(evt);
            }
        });
        add(nav_barang);
        nav_barang.setBounds(410, 160, 190, 30);

        add_jumlah.setBackground(new Color(0,0,0,50));
        add_jumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_jumlahMouseEntered(evt);
            }
        });
        add_jumlah.setLayout(null);

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add_jumlah.add(jLabel1);
        jLabel1.setBounds(1134, 412, 40, 40);

        tambah_jumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambah_jumlahMouseClicked(evt);
            }
        });
        add_jumlah.add(tambah_jumlah);
        tambah_jumlah.setBounds(790, 564, 200, 40);

        btn_minus.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        btn_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_minusMouseClicked(evt);
            }
        });
        add_jumlah.add(btn_minus);
        btn_minus.setBounds(660, 490, 50, 50);

        btn_plus.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        btn_plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_plusMouseClicked(evt);
            }
        });
        add_jumlah.add(btn_plus);
        btn_plus.setBounds(1070, 490, 50, 50);

        tf_jumlah.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        tf_jumlah.setText("1");
        add_jumlah.add(tf_jumlah);
        tf_jumlah.setBounds(880, 490, 20, 50);

        bg_jumlah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/input jumlah.png"))); // NOI18N
        add_jumlah.add(bg_jumlah);
        bg_jumlah.setBounds(590, 400, 600, 220);

        add(add_jumlah);
        add_jumlah.setBounds(0, 0, 1920, 1080);

        keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                keranjangMouseEntered(evt);
            }
        });
        keranjang.setLayout(null);

        btn_lanjut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lanjutMouseClicked(evt);
            }
        });
        keranjang.add(btn_lanjut);
        btn_lanjut.setBounds(160, 570, 170, 40);

        ScrollPaneKeranjang.setBackground(new Color(0,0,0,0));
        keranjang.add(ScrollPaneKeranjang);
        ScrollPaneKeranjang.setBounds(0, 60, 490, 500);

        bg_card.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/keranjang layanan.png"))); // NOI18N
        keranjang.add(bg_card);
        bg_card.setBounds(0, 0, 490, 640);

        add(keranjang);
        keranjang.setBounds(1350, 210, 490, 640);

        layanan_barang.setLayout(null);

        edit_data_barang1.setBackground(new Color(0,0,0,170));
        edit_data_barang1.setLayout(null);

        tf_idbarang2.setBackground(new Color(0,0,0,0));
        tf_idbarang2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_idbarang2);
        tf_idbarang2.setBounds(460, 360, 320, 50);

        tf_namabarang2.setBackground(new Color(0,0,0,0));
        tf_namabarang2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_namabarang2);
        tf_namabarang2.setBounds(820, 360, 320, 50);

        cmb_jenishewan2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        edit_data_barang1.add(cmb_jenishewan2);
        cmb_jenishewan2.setBounds(460, 460, 320, 60);

        cmb_kategori2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Jenis Kategori ---", "Makanan", "Aksesoris", "Perlengkapan" }));
        edit_data_barang1.add(cmb_kategori2);
        cmb_kategori2.setBounds(820, 460, 320, 60);

        tf_merkbarang2.setBackground(new Color(0,0,0,0));
        tf_merkbarang2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_merkbarang2);
        tf_merkbarang2.setBounds(460, 560, 320, 50);

        tf_stokbarang2.setBackground(new Color(0,0,0,0));
        tf_stokbarang2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_stokbarang2);
        tf_stokbarang2.setBounds(820, 560, 320, 50);

        tf_hargabeli2.setBackground(new Color(0,0,0,0));
        tf_hargabeli2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_hargabeli2);
        tf_hargabeli2.setBounds(460, 666, 320, 50);

        tf_hargajual2.setBackground(new Color(0,0,0,0));
        tf_hargajual2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_data_barang1.add(tf_hargajual2);
        tf_hargajual2.setBounds(820, 660, 320, 50);

        btn_add2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add2MouseClicked(evt);
            }
        });
        edit_data_barang1.add(btn_add2);
        btn_add2.setBounds(710, 760, 180, 50);

        btn_close2.setToolTipText("");
        btn_close2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close2MouseClicked(evt);
            }
        });
        edit_data_barang1.add(btn_close2);
        btn_close2.setBounds(1490, 240, 50, 50);
        edit_data_barang1.add(tf_linkgambar2);
        tf_linkgambar2.setBounds(1180, 750, 230, 22);
        edit_data_barang1.add(img2);
        img2.setBounds(1240, 370, 230, 300);

        bg_inputbarang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Barang.png"))); // NOI18N
        edit_data_barang1.add(bg_inputbarang2);
        bg_inputbarang2.setBounds(350, 230, 1220, 630);

        layanan_barang.add(edit_data_barang1);
        edit_data_barang1.setBounds(0, 0, 1920, 1080);

        input_data_barang1.setBackground(new Color(0,0,0,170));
        input_data_barang1.setLayout(null);

        tf_idbarang3.setBackground(new Color(0,0,0,0));
        tf_idbarang3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_idbarang3);
        tf_idbarang3.setBounds(460, 360, 320, 50);

        tf_namabarang3.setBackground(new Color(0,0,0,0));
        tf_namabarang3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_namabarang3);
        tf_namabarang3.setBounds(820, 360, 320, 50);

        cmb_jenishewan3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        input_data_barang1.add(cmb_jenishewan3);
        cmb_jenishewan3.setBounds(460, 460, 320, 60);

        cmb_kategori3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Jenis Kategori ---", "Makanan", "Aksesoris", "Perlengkapan" }));
        input_data_barang1.add(cmb_kategori3);
        cmb_kategori3.setBounds(820, 460, 320, 60);

        tf_merkbarang3.setBackground(new Color(0,0,0,0));
        tf_merkbarang3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_merkbarang3);
        tf_merkbarang3.setBounds(460, 560, 320, 50);

        tf_stokbarang3.setBackground(new Color(0,0,0,0));
        tf_stokbarang3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_stokbarang3);
        tf_stokbarang3.setBounds(820, 560, 320, 50);

        tf_hargabeli3.setBackground(new Color(0,0,0,0));
        tf_hargabeli3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_hargabeli3);
        tf_hargabeli3.setBounds(460, 666, 320, 50);

        tf_hargajual3.setBackground(new Color(0,0,0,0));
        tf_hargajual3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_data_barang1.add(tf_hargajual3);
        tf_hargajual3.setBounds(820, 660, 320, 50);

        btn_add3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add3MouseClicked(evt);
            }
        });
        input_data_barang1.add(btn_add3);
        btn_add3.setBounds(710, 760, 180, 50);

        btn_close3.setToolTipText("");
        btn_close3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close3MouseClicked(evt);
            }
        });
        input_data_barang1.add(btn_close3);
        btn_close3.setBounds(1490, 240, 50, 50);

        btn_upload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_upload1MouseClicked(evt);
            }
        });
        input_data_barang1.add(btn_upload1);
        btn_upload1.setBounds(1280, 690, 150, 30);
        input_data_barang1.add(img3);
        img3.setBounds(1240, 370, 230, 300);
        input_data_barang1.add(tf_linkgambar3);
        tf_linkgambar3.setBounds(1190, 760, 330, 40);

        bg_inputbarang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Barang.png"))); // NOI18N
        input_data_barang1.add(bg_inputbarang3);
        bg_inputbarang3.setBounds(350, 230, 1220, 630);

        layanan_barang.add(input_data_barang1);
        input_data_barang1.setBounds(0, 0, 1920, 1080);

        ScrollPaneBarang.setBackground(new Color(235,237,241));
        ScrollPaneBarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ScrollPaneBarang.setForeground(new java.awt.Color(255, 255, 255));
        layanan_barang.add(ScrollPaneBarang);
        ScrollPaneBarang.setBounds(430, 290, 1400, 680);

        bg_jumlah_notif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/notif-keranjang.png"))); // NOI18N
        layanan_barang.add(bg_jumlah_notif);
        bg_jumlah_notif.setBounds(1850, 120, 40, 40);

        lbl_jumlah_notif.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_jumlah_notif.setText("0");
        layanan_barang.add(lbl_jumlah_notif);
        lbl_jumlah_notif.setBounds(1860, 130, 20, 20);

        btn_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cartMouseClicked(evt);
            }
        });
        layanan_barang.add(btn_cart);
        btn_cart.setBounds(1820, 130, 60, 60);

        btn_keluar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluar1MouseClicked(evt);
            }
        });
        layanan_barang.add(btn_keluar1);
        btn_keluar1.setBounds(50, 980, 180, 50);

        bg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/layanan - barangAdmin.png"))); // NOI18N
        bg1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        layanan_barang.add(bg1);
        bg1.setBounds(0, 0, 1920, 1080);

        add(layanan_barang);
        layanan_barang.setBounds(0, 0, 1920, 1080);

        layanan_penitipan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                layanan_penitipanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        layanan_penitipan.setLayout(null);

        edit_penitipan.setBackground(new Color(0,0,0,170));
        edit_penitipan.setLayout(null);

        tf_edit_hargapenitipan.setBackground(new Color(0,0,0,0));
        tf_edit_hargapenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_edit_hargapenitipan);
        tf_edit_hargapenitipan.setBounds(790, 440, 270, 40);

        tf_edit_idpenitipan.setBackground(new Color(0,0,0,0));
        tf_edit_idpenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_edit_idpenitipan);
        tf_edit_idpenitipan.setBounds(460, 350, 280, 40);

        tf_edit_namapenitipan.setBackground(new Color(0,0,0,0));
        tf_edit_namapenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_edit_namapenitipan);
        tf_edit_namapenitipan.setBounds(790, 350, 270, 40);

        cmb_editpenitipan_jenishewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        edit_penitipan.add(cmb_editpenitipan_jenishewan);
        cmb_editpenitipan_jenishewan.setBounds(460, 440, 280, 40);

        tf_editpenitipan_keterangan1.setBackground(new Color(0,0,0,0));
        tf_editpenitipan_keterangan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_editpenitipan_keterangan1);
        tf_editpenitipan_keterangan1.setBounds(460, 530, 280, 40);

        tf_editpenitipan_keterangan2.setBackground(new Color(0,0,0,0));
        tf_editpenitipan_keterangan2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_editpenitipan_keterangan2);
        tf_editpenitipan_keterangan2.setBounds(790, 530, 270, 40);

        tf_editpenitipan_keterangan3.setBackground(new Color(0,0,0,0));
        tf_editpenitipan_keterangan3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_editpenitipan_keterangan3);
        tf_editpenitipan_keterangan3.setBounds(460, 620, 280, 40);

        tf_editpenitipan_keterangan4.setBackground(new Color(0,0,0,0));
        tf_editpenitipan_keterangan4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_penitipan.add(tf_editpenitipan_keterangan4);
        tf_editpenitipan_keterangan4.setBounds(790, 620, 270, 40);

        btn_simpan_penitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpan_penitipanMouseClicked(evt);
            }
        });
        edit_penitipan.add(btn_simpan_penitipan);
        btn_simpan_penitipan.setBounds(690, 720, 150, 40);

        btn_close4.setToolTipText("");
        btn_close4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close4MouseClicked(evt);
            }
        });
        edit_penitipan.add(btn_close4);
        btn_close4.setBounds(1110, 240, 50, 50);

        bg_inputbarang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Penitipan.png"))); // NOI18N
        edit_penitipan.add(bg_inputbarang4);
        bg_inputbarang4.setBounds(350, 230, 826, 570);

        layanan_penitipan.add(edit_penitipan);
        edit_penitipan.setBounds(0, 0, 1920, 1080);

        input_penitipan.setBackground(new Color(0,0,0,170));
        input_penitipan.setLayout(null);

        tf_inputpenitipan_keterangan4.setBackground(new Color(0,0,0,0));
        tf_inputpenitipan_keterangan4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_inputpenitipan_keterangan4);
        tf_inputpenitipan_keterangan4.setBounds(790, 626, 270, 40);

        tf_inputpenitipan_keterangan3.setBackground(new Color(0,0,0,0));
        tf_inputpenitipan_keterangan3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_inputpenitipan_keterangan3);
        tf_inputpenitipan_keterangan3.setBounds(460, 626, 270, 40);

        tf_inputpenitipan_keterangan2.setBackground(new Color(0,0,0,0));
        tf_inputpenitipan_keterangan2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_inputpenitipan_keterangan2);
        tf_inputpenitipan_keterangan2.setBounds(790, 530, 270, 40);

        tf_inputpenitipan_keterangan1.setBackground(new Color(0,0,0,0));
        tf_inputpenitipan_keterangan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_inputpenitipan_keterangan1);
        tf_inputpenitipan_keterangan1.setBounds(460, 530, 270, 40);

        tf_input_hargapenitipan.setBackground(new Color(0,0,0,0));
        tf_input_hargapenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_input_hargapenitipan);
        tf_input_hargapenitipan.setBounds(790, 450, 270, 40);

        tf_input_idpenitipan.setBackground(new Color(0,0,0,0));
        tf_input_idpenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_input_idpenitipan);
        tf_input_idpenitipan.setBounds(460, 350, 280, 40);

        tf_input_namapenitipan.setBackground(new Color(0,0,0,0));
        tf_input_namapenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_penitipan.add(tf_input_namapenitipan);
        tf_input_namapenitipan.setBounds(790, 350, 270, 40);

        cmb_inputpenitipan_jenishewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        input_penitipan.add(cmb_inputpenitipan_jenishewan);
        cmb_inputpenitipan_jenishewan.setBounds(460, 450, 270, 40);

        btn_close5.setToolTipText("");
        btn_close5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close5MouseClicked(evt);
            }
        });
        input_penitipan.add(btn_close5);
        btn_close5.setBounds(1110, 240, 40, 50);

        btn_tambah_penitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambah_penitipanMouseClicked(evt);
            }
        });
        input_penitipan.add(btn_tambah_penitipan);
        btn_tambah_penitipan.setBounds(680, 720, 160, 40);

        bg_inputbarang5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Penitipan.png"))); // NOI18N
        input_penitipan.add(bg_inputbarang5);
        bg_inputbarang5.setBounds(350, 230, 826, 570);

        layanan_penitipan.add(input_penitipan);
        input_penitipan.setBounds(0, 0, 1920, 1080);

        nav_optionhewan_domestik1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_domestik1MouseClicked(evt);
            }
        });
        layanan_penitipan.add(nav_optionhewan_domestik1);
        nav_optionhewan_domestik1.setBounds(790, 220, 190, 30);

        nav_optionhewan_anjing1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_anjing1MouseClicked(evt);
            }
        });
        layanan_penitipan.add(nav_optionhewan_anjing1);
        nav_optionhewan_anjing1.setBounds(600, 220, 190, 30);

        nav_optionhewan_kucing1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_kucing1MouseClicked(evt);
            }
        });
        layanan_penitipan.add(nav_optionhewan_kucing1);
        nav_optionhewan_kucing1.setBounds(410, 220, 200, 30);

        optionhewan_domestik1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan domestik.png"))); // NOI18N
        layanan_penitipan.add(optionhewan_domestik1);
        optionhewan_domestik1.setBounds(406, 216, 580, 40);

        optionhewan_anjing1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan anjing.png"))); // NOI18N
        layanan_penitipan.add(optionhewan_anjing1);
        optionhewan_anjing1.setBounds(406, 216, 580, 40);

        optionhewan_kucing1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan kucing.png"))); // NOI18N
        layanan_penitipan.add(optionhewan_kucing1);
        optionhewan_kucing1.setBounds(406, 216, 580, 40);

        btn_tambah2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambah2MouseClicked(evt);
            }
        });
        layanan_penitipan.add(btn_tambah2);
        btn_tambah2.setBounds(1816, 132, 60, 60);

        btn_edit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit2MouseClicked(evt);
            }
        });
        layanan_penitipan.add(btn_edit2);
        btn_edit2.setBounds(1734, 132, 60, 60);

        btn_delete2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_delete2MouseClicked(evt);
            }
        });
        layanan_penitipan.add(btn_delete2);
        btn_delete2.setBounds(1650, 130, 60, 60);

        btn_keluar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluar2MouseClicked(evt);
            }
        });
        layanan_penitipan.add(btn_keluar2);
        btn_keluar2.setBounds(50, 980, 180, 50);

        ScrollPanePenitipan.setBackground(new Color(235,237,241));
        ScrollPanePenitipan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ScrollPanePenitipan.setForeground(new java.awt.Color(255, 255, 255));
        layanan_penitipan.add(ScrollPanePenitipan);
        ScrollPanePenitipan.setBounds(430, 290, 1400, 680);

        bg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/layanan - penitipan - kucing.png"))); // NOI18N
        bg2.setPreferredSize(new java.awt.Dimension(1920, 1080));
        layanan_penitipan.add(bg2);
        bg2.setBounds(0, 0, 1920, 1080);

        add(layanan_penitipan);
        layanan_penitipan.setBounds(0, 0, 1920, 1080);

        layanan_gromming.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                layanan_grommingAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        layanan_gromming.setLayout(null);

        input_gromming.setBackground(new Color(0,0,0,170));
        input_gromming.setLayout(null);

        tf_input_keterangan4.setBackground(new Color(0,0,0,0));
        tf_input_keterangan4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_keterangan4);
        tf_input_keterangan4.setBounds(790, 650, 280, 40);

        tf_input_keterangan3.setBackground(new Color(0,0,0,0));
        tf_input_keterangan3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_keterangan3);
        tf_input_keterangan3.setBounds(460, 650, 280, 40);

        tf_input_keterangan2.setBackground(new Color(0,0,0,0));
        tf_input_keterangan2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_keterangan2);
        tf_input_keterangan2.setBounds(790, 560, 280, 40);

        tf_input_keterangan1.setBackground(new Color(0,0,0,0));
        tf_input_keterangan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_keterangan1);
        tf_input_keterangan1.setBounds(460, 560, 280, 40);

        tf_input_hargagromming.setBackground(new Color(0,0,0,0));
        tf_input_hargagromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_hargagromming);
        tf_input_hargagromming.setBounds(790, 480, 280, 40);

        tf_input_idgromming.setBackground(new Color(0,0,0,0));
        tf_input_idgromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_idgromming);
        tf_input_idgromming.setBounds(460, 380, 280, 40);

        tf_input_namagromming.setBackground(new Color(0,0,0,0));
        tf_input_namagromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_gromming.add(tf_input_namagromming);
        tf_input_namagromming.setBounds(790, 380, 280, 40);

        cmb_inputgromming_jenishewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        input_gromming.add(cmb_inputgromming_jenishewan);
        cmb_inputgromming_jenishewan.setBounds(460, 480, 280, 40);

        btn_add_gromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_grommingMouseClicked(evt);
            }
        });
        input_gromming.add(btn_add_gromming);
        btn_add_gromming.setBounds(690, 750, 150, 40);

        btn_close_inputgromming.setToolTipText("");
        btn_close_inputgromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close_inputgrommingMouseClicked(evt);
            }
        });
        input_gromming.add(btn_close_inputgromming);
        btn_close_inputgromming.setBounds(1110, 270, 50, 50);

        bg_inputgromming.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Gromming.png"))); // NOI18N
        input_gromming.add(bg_inputgromming);
        bg_inputgromming.setBounds(350, 260, 830, 570);

        layanan_gromming.add(input_gromming);
        input_gromming.setBounds(0, 0, 1920, 1080);

        edit_gromming.setBackground(new Color(0,0,0,170));
        edit_gromming.setLayout(null);

        tf_edit_hargagromming.setBackground(new Color(0,0,0,0));
        tf_edit_hargagromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_edit_hargagromming);
        tf_edit_hargagromming.setBounds(790, 450, 270, 40);

        tf_edit_idgromming.setBackground(new Color(0,0,0,0));
        tf_edit_idgromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_edit_idgromming);
        tf_edit_idgromming.setBounds(460, 350, 280, 40);

        tf_edit_namagromming.setBackground(new Color(0,0,0,0));
        tf_edit_namagromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_edit_namagromming);
        tf_edit_namagromming.setBounds(790, 350, 270, 40);

        cmb_edit_jenishewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Jenis Hewan ---", "Kucing", "Anjing", "Domestik" }));
        edit_gromming.add(cmb_edit_jenishewan);
        cmb_edit_jenishewan.setBounds(460, 450, 280, 40);

        tf_editgromming_keterangan1.setBackground(new Color(0,0,0,0));
        tf_editgromming_keterangan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_editgromming_keterangan1);
        tf_editgromming_keterangan1.setBounds(460, 530, 280, 40);

        tf_editgromming_keterangan2.setBackground(new Color(0,0,0,0));
        tf_editgromming_keterangan2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_editgromming_keterangan2);
        tf_editgromming_keterangan2.setBounds(790, 530, 270, 40);

        tf_editgromming_keterangan3.setBackground(new Color(0,0,0,0));
        tf_editgromming_keterangan3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_editgromming_keterangan3);
        tf_editgromming_keterangan3.setBounds(460, 620, 280, 40);

        tf_editgromming_keterangan4.setBackground(new Color(0,0,0,0));
        tf_editgromming_keterangan4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_gromming.add(tf_editgromming_keterangan4);
        tf_editgromming_keterangan4.setBounds(790, 620, 270, 40);

        btn_simpan_editgromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpan_editgrommingMouseClicked(evt);
            }
        });
        edit_gromming.add(btn_simpan_editgromming);
        btn_simpan_editgromming.setBounds(690, 720, 150, 40);

        btn_close_editgromming.setToolTipText("");
        btn_close_editgromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close_editgrommingMouseClicked(evt);
            }
        });
        edit_gromming.add(btn_close_editgromming);
        btn_close_editgromming.setBounds(1110, 240, 40, 40);

        bg_inputbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Gromming.png"))); // NOI18N
        edit_gromming.add(bg_inputbarang);
        bg_inputbarang.setBounds(350, 230, 830, 570);

        layanan_gromming.add(edit_gromming);
        edit_gromming.setBounds(0, 0, 1920, 1080);

        nav_optionhewan_domestik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_domestikMouseClicked(evt);
            }
        });
        layanan_gromming.add(nav_optionhewan_domestik);
        nav_optionhewan_domestik.setBounds(790, 220, 190, 30);

        nav_optionhewan_anjing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_anjingMouseClicked(evt);
            }
        });
        layanan_gromming.add(nav_optionhewan_anjing);
        nav_optionhewan_anjing.setBounds(600, 220, 190, 30);

        nav_optionhewan_kucing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nav_optionhewan_kucingMouseClicked(evt);
            }
        });
        layanan_gromming.add(nav_optionhewan_kucing);
        nav_optionhewan_kucing.setBounds(410, 220, 200, 30);

        optionhewan_domestik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan domestik.png"))); // NOI18N
        layanan_gromming.add(optionhewan_domestik);
        optionhewan_domestik.setBounds(406, 216, 580, 40);

        optionhewan_anjing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan anjing.png"))); // NOI18N
        layanan_gromming.add(optionhewan_anjing);
        optionhewan_anjing.setBounds(406, 216, 580, 40);

        optionhewan_kucing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/option hewan kucing.png"))); // NOI18N
        layanan_gromming.add(optionhewan_kucing);
        optionhewan_kucing.setBounds(406, 216, 580, 40);

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        layanan_gromming.add(btn_keluar);
        btn_keluar.setBounds(50, 980, 180, 50);

        ScrollPaneGromming.setBackground(new Color(235,237,241));
        ScrollPaneGromming.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ScrollPaneGromming.setForeground(new java.awt.Color(255, 255, 255));
        layanan_gromming.add(ScrollPaneGromming);
        ScrollPaneGromming.setBounds(430, 290, 1400, 680);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/layanan - gromming - kucingAdmin.png"))); // NOI18N
        bg.setPreferredSize(new java.awt.Dimension(1920, 1080));
        layanan_gromming.add(bg);
        bg.setBounds(0, 0, 1920, 1080);

        add(layanan_gromming);
        layanan_gromming.setBounds(0, 0, 1920, 1080);

        lbl_formDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formDashboardMouseClicked(evt);
            }
        });
        add(lbl_formDashboard);
        lbl_formDashboard.setBounds(0, 220, 340, 40);

        lbl_formStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseEntered(evt);
            }
        });
        add(lbl_formStok);
        lbl_formStok.setBounds(0, 280, 340, 60);

        lbl_formLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLayananMouseClicked(evt);
            }
        });
        add(lbl_formLayanan);
        lbl_formLayanan.setBounds(0, 360, 340, 50);

        lbl_formTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formTransaksiMouseClicked(evt);
            }
        });
        add(lbl_formTransaksi);
        lbl_formTransaksi.setBounds(0, 420, 340, 70);

        lbl_formLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLaporanMouseClicked(evt);
            }
        });
        add(lbl_formLaporan);
        lbl_formLaporan.setBounds(0, 500, 340, 60);

        lbl_formPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formPegawaiMouseClicked(evt);
            }
        });
        add(lbl_formPegawai);
        lbl_formPegawai.setBounds(0, 590, 340, 50);

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        add(Logout2);
        Logout2.setBounds(0, 970, 340, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_close_editgrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close_editgrommingMouseClicked
        nomorPanel = null;
        edit_gromming.setVisible(false);

    }//GEN-LAST:event_btn_close_editgrommingMouseClicked

    private void btn_simpan_editgrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_editgrommingMouseClicked
//        nomorPanel = null;
//        String id_barang = tf_edit_idbarang.getText();
//        String nama_barang = tf_edit_namagromming.getText();
//        int index_jenis_hewan = cmb_edit_jenishewan.getSelectedIndex();
//        String jenis_hewan = getItemJenisHewan(index_jenis_hewan);
//        int index_kategori = cmb_edit_kategori.getSelectedIndex();
//        String kategori = getItemKategori(index_kategori);
//        String merk_barang = tf_edit_merkbarang.getText();
//        String stok_barang = tf_edit_stokbarang.getText();
//        String harga_jual = tf_edit_hargajual.getText();
//        String harga_beli = tf_edit_hargabeli.getText();
//
//        if (id_barang != null && nama_barang != null && jenis_hewan != null && kategori != null && merk_barang != null && stok_barang != null && harga_jual != null && harga_beli != null ) {
//            try {
//                String sql = "INSERT INTO tb_barang VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
//
//                sql = String.format(
//                    sql,
//                    id_barang,
//                    nama_barang,
//                    kategori,
//                    jenis_hewan,
//                    merk_barang,
//                    stok_barang,
//                    harga_jual,
//                    harga_beli
//                );
//
//                java.sql.Connection conn = (Connection)config.configDB();
//                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//
//                pst.execute();
//                JOptionPane.showMessageDialog(null, "Berhasil ditambahkan");
//                edit_gromming.setVisible(false);
//                getPanel();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            }
//        }
    }//GEN-LAST:event_btn_simpan_editgrommingMouseClicked

    private void btn_close_inputgrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close_inputgrommingMouseClicked
        nomorPanel = null;
        input_gromming.setVisible(false);
        this.setComponentZOrder(edit_gromming, 0);
    }//GEN-LAST:event_btn_close_inputgrommingMouseClicked

    private void btn_add_grommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_grommingMouseClicked
        //nomorPanel = null;
        container_gromming.removeAll();
        CRUD_Layanan.add_gromming();
        getCardGrommingKucing();
        getCardGrommingAnjing();
        getCardGrommingDomestik();
        input_gromming.setVisible(false);
    }//GEN-LAST:event_btn_add_grommingMouseClicked

    private void nav_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_barangMouseClicked
        layanan_gromming.setVisible(false);
        layanan_penitipan.setVisible(false);
        layanan_barang.setVisible(true);
    }//GEN-LAST:event_nav_barangMouseClicked

    private void nav_grommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_grommingMouseClicked
        layanan_gromming.setVisible(true);
        layanan_penitipan.setVisible(false);
        layanan_barang.setVisible(false);
    }//GEN-LAST:event_nav_grommingMouseClicked

    private void nav_penitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_penitipanMouseClicked
        layanan_gromming.setVisible(false);
        layanan_barang.setVisible(false);
        layanan_penitipan.setVisible(true);
    }//GEN-LAST:event_nav_penitipanMouseClicked

    private void layanan_grommingAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_layanan_grommingAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_layanan_grommingAncestorAdded

    private void btn_simpan_penitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_penitipanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_penitipanMouseClicked

    private void btn_close4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close4MouseClicked
        edit_penitipan.setVisible(false);
    }//GEN-LAST:event_btn_close4MouseClicked

    private void btn_close5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close5MouseClicked
        input_penitipan.setVisible(false);
    }//GEN-LAST:event_btn_close5MouseClicked

    private void btn_tambah2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah2MouseClicked
        input_penitipan.setVisible(true);
        
    }//GEN-LAST:event_btn_tambah2MouseClicked

    private void btn_edit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit2MouseClicked
        if (nomorPanel == null) {
            JOptionPane.showMessageDialog(null, "Klik data yang mau diedit");
        }else{
            try {
                String sql = "select * from tb_penitipan where id_penitipan = '"+nomorPanel+"'";
                java.sql.Connection conn=(Connection)config.configDB();
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
                    nomorPanel = null;
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btn_edit2MouseClicked

    private void btn_delete2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_delete2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_delete2MouseClicked

    private void btn_keluar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluar2MouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_btn_keluar2MouseClicked

    private void layanan_penitipanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_layanan_penitipanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_layanan_penitipanAncestorAdded

    private boolean klikNotif = false;
    private boolean klikCart = false;
    private void keranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keranjangMouseEntered
        keranjang.setVisible(true);
    }//GEN-LAST:event_keranjangMouseEntered

    private void tambah_jumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambah_jumlahMouseClicked
        addJumlah();
        tampilJumlahNotif();
        jumlahPanel = 1;
        setJumlah();
        add_jumlah.setVisible(false);
        System.out.println(arrayUtama);
        
    }//GEN-LAST:event_tambah_jumlahMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        jumlahPanel = 1;
        add_jumlah.setVisible(false);
        setJumlah();
        removeDataById(nomorPanel);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void add_jumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_jumlahMouseEntered
         add_jumlah.setVisible(true);
    }//GEN-LAST:event_add_jumlahMouseEntered

    private void btn_plusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_plusMouseClicked
        jumlahPanel = jumlahPanel + 1;
        setJumlah();
    }//GEN-LAST:event_btn_plusMouseClicked

    private void btn_minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minusMouseClicked
        jumlahPanel = jumlahPanel - 1;
        setJumlah();
    }//GEN-LAST:event_btn_minusMouseClicked

    private void btn_upload1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_upload1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_upload1MouseClicked

    private void btn_close3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close3MouseClicked

    private void btn_add3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add3MouseClicked

    private void btn_close2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close2MouseClicked

    private void btn_add2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add2MouseClicked

    private void btn_keluar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluar1MouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_btn_keluar1MouseClicked

    private void btn_cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cartMouseClicked
        if (klikCart == false) {
            keranjang.setVisible(true);
            addKeranjang();
            klikCart = true;
        }else {
            keranjang.setVisible(false);
            klikCart = false;
        }
    }//GEN-LAST:event_btn_cartMouseClicked

    private void btn_tambah_penitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah_penitipanMouseClicked
        CRUD_Layanan.add_penitipan();
        container_penitipan.removeAll();
        getCardPenitipanKucing();
        getCardPenitipanAnjing();
        getCardPenitipanDomestik();
        input_penitipan.setVisible(false);
    }//GEN-LAST:event_btn_tambah_penitipanMouseClicked

    private void nav_optionhewan_kucingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_kucingMouseClicked
        optionhewan_kucing.setVisible(true);
        optionhewan_anjing.setVisible(false);
        optionhewan_domestik.setVisible(false);
        
        container_gromming.removeAll();
        getCardGrommingKucing();
    }//GEN-LAST:event_nav_optionhewan_kucingMouseClicked

    private void nav_optionhewan_anjingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_anjingMouseClicked
        optionhewan_kucing.setVisible(false);
        optionhewan_anjing.setVisible(true);
        optionhewan_domestik.setVisible(false);
        
        container_gromming.removeAll();
        getCardGrommingAnjing();
    }//GEN-LAST:event_nav_optionhewan_anjingMouseClicked

    private void nav_optionhewan_domestikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_domestikMouseClicked
        optionhewan_kucing.setVisible(false);
        optionhewan_anjing.setVisible(false);
        optionhewan_domestik.setVisible(true);
        
        container_gromming.removeAll();
        getCardGrommingDomestik();
    }//GEN-LAST:event_nav_optionhewan_domestikMouseClicked

    private void nav_optionhewan_domestik1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_domestik1MouseClicked
        optionhewan_kucing1.setVisible(false);
        optionhewan_anjing1.setVisible(false);
        optionhewan_domestik1.setVisible(true);
        
        container_penitipan.removeAll();
        getCardPenitipanDomestik();
    }//GEN-LAST:event_nav_optionhewan_domestik1MouseClicked

    private void nav_optionhewan_anjing1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_anjing1MouseClicked
        optionhewan_kucing1.setVisible(false);
        optionhewan_anjing1.setVisible(true);
        optionhewan_domestik1.setVisible(false);
        
        container_penitipan.removeAll();
        getCardPenitipanAnjing();
    }//GEN-LAST:event_nav_optionhewan_anjing1MouseClicked

    private void nav_optionhewan_kucing1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_optionhewan_kucing1MouseClicked
        optionhewan_kucing1.setVisible(true);
        optionhewan_anjing1.setVisible(false);
        optionhewan_domestik1.setVisible(false);
        
        container_penitipan.removeAll();
        getCardPenitipanKucing();
    }//GEN-LAST:event_nav_optionhewan_kucing1MouseClicked

    private void btn_lanjutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lanjutMouseClicked
        try {
          String sql = "INSERT INTO tb_keranjang (id_barang, nama_barang, harga_barang, jumlah) VALUES (?, ?, ?, ?)";
                java.sql.Connection conn=(Connection)config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                for (ArrayList<String> dataRow : arrayUtama) {
                    pst.setString(1, dataRow.get(0)); 
                    pst.setString(2, dataRow.get(1)); 
                    pst.setString(3, dataRow.get(3)); 
                    pst.setString(4, dataRow.get(4)); 
                    pst.execute();
                    new form_transaksiBarang().setVisible(true);
                    this.setVisible(false);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btn_lanjutMouseClicked

    private void lbl_formDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboardMouseClicked
        this.setVisible(false);
        new form_dashboard().setVisible(true);
    }//GEN-LAST:event_lbl_formDashboardMouseClicked

    private void lbl_formStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseClicked
        this.setVisible(false);
        new form_stok().setVisible(true);
    }//GEN-LAST:event_lbl_formStokMouseClicked

    private void lbl_formStokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseEntered

    }//GEN-LAST:event_lbl_formStokMouseEntered

    private void lbl_formLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLayananMouseClicked
        this.setVisible(false);
        new form_layanan().setVisible(true);
    }//GEN-LAST:event_lbl_formLayananMouseClicked

    private void lbl_formTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formTransaksiMouseClicked
        this.setVisible(false);
        new form_transaksiBarang().setVisible(true);
    }//GEN-LAST:event_lbl_formTransaksiMouseClicked

    private void lbl_formLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLaporanMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsung().setVisible(true);
    }//GEN-LAST:event_lbl_formLaporanMouseClicked

    private void lbl_formPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formPegawaiMouseClicked
        this.setVisible(false);
        new form_pegawai().setVisible(true);
    }//GEN-LAST:event_lbl_formPegawaiMouseClicked

    private void Logout2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout2MouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_Logout2MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        popUp_keluar2.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout2;
    private javax.swing.JScrollPane ScrollPaneBarang;
    private javax.swing.JScrollPane ScrollPaneGromming;
    private javax.swing.JScrollPane ScrollPaneKeranjang;
    private javax.swing.JScrollPane ScrollPanePenitipan;
    private javax.swing.JPanel add_jumlah;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg1;
    private javax.swing.JLabel bg2;
    private javax.swing.JLabel bg_card;
    private javax.swing.JLabel bg_inputbarang;
    private javax.swing.JLabel bg_inputbarang2;
    private javax.swing.JLabel bg_inputbarang3;
    private javax.swing.JLabel bg_inputbarang4;
    private javax.swing.JLabel bg_inputbarang5;
    private javax.swing.JLabel bg_inputgromming;
    private javax.swing.JLabel bg_jumlah;
    private javax.swing.JLabel bg_jumlah_notif;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel btn_add2;
    private javax.swing.JLabel btn_add3;
    private javax.swing.JLabel btn_add_gromming;
    private javax.swing.JLabel btn_cart;
    private javax.swing.JLabel btn_close2;
    private javax.swing.JLabel btn_close3;
    private javax.swing.JLabel btn_close4;
    private javax.swing.JLabel btn_close5;
    private javax.swing.JLabel btn_close_editgromming;
    private javax.swing.JLabel btn_close_inputgromming;
    private javax.swing.JLabel btn_delete2;
    private javax.swing.JLabel btn_edit2;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_keluar1;
    private javax.swing.JLabel btn_keluar2;
    private javax.swing.JLabel btn_lanjut;
    private javax.swing.JLabel btn_minus;
    private javax.swing.JLabel btn_plus;
    private javax.swing.JLabel btn_simpan_editgromming;
    private javax.swing.JLabel btn_simpan_penitipan;
    private javax.swing.JLabel btn_tambah2;
    private javax.swing.JLabel btn_tambah_penitipan;
    private javax.swing.JLabel btn_upload1;
    public static javax.swing.JComboBox<String> cmb_edit_jenishewan;
    public static javax.swing.JComboBox<String> cmb_editpenitipan_jenishewan;
    public static javax.swing.JComboBox<String> cmb_inputgromming_jenishewan;
    public static javax.swing.JComboBox<String> cmb_inputpenitipan_jenishewan;
    private javax.swing.JComboBox<String> cmb_jenishewan2;
    private javax.swing.JComboBox<String> cmb_jenishewan3;
    private javax.swing.JComboBox<String> cmb_kategori2;
    private javax.swing.JComboBox<String> cmb_kategori3;
    private javax.swing.JPanel edit_data_barang1;
    public static javax.swing.JPanel edit_gromming;
    public static javax.swing.JPanel edit_penitipan;
    private javax.swing.JLabel img2;
    private javax.swing.JLabel img3;
    private javax.swing.JPanel input_data_barang1;
    public static javax.swing.JPanel input_gromming;
    public static javax.swing.JPanel input_penitipan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel keranjang;
    private javax.swing.JPanel layanan_barang;
    private javax.swing.JPanel layanan_gromming;
    private javax.swing.JPanel layanan_penitipan;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formPegawai;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lbl_jumlah_notif;
    private javax.swing.JLabel nav_barang;
    private javax.swing.JLabel nav_gromming;
    private javax.swing.JLabel nav_optionhewan_anjing;
    private javax.swing.JLabel nav_optionhewan_anjing1;
    private javax.swing.JLabel nav_optionhewan_domestik;
    private javax.swing.JLabel nav_optionhewan_domestik1;
    private javax.swing.JLabel nav_optionhewan_kucing;
    private javax.swing.JLabel nav_optionhewan_kucing1;
    private javax.swing.JLabel nav_penitipan;
    private javax.swing.JLabel optionhewan_anjing;
    private javax.swing.JLabel optionhewan_anjing1;
    private javax.swing.JLabel optionhewan_domestik;
    private javax.swing.JLabel optionhewan_domestik1;
    private javax.swing.JLabel optionhewan_kucing;
    private javax.swing.JLabel optionhewan_kucing1;
    private javax.swing.JPanel popUp_keluar2;
    private javax.swing.JLabel tambah_jumlah;
    public static javax.swing.JTextField tf_edit_hargagromming;
    public static javax.swing.JTextField tf_edit_hargapenitipan;
    public static javax.swing.JTextField tf_edit_idgromming;
    public static javax.swing.JTextField tf_edit_idpenitipan;
    public static javax.swing.JTextField tf_edit_namagromming;
    public static javax.swing.JTextField tf_edit_namapenitipan;
    public static javax.swing.JTextField tf_editgromming_keterangan1;
    public static javax.swing.JTextField tf_editgromming_keterangan2;
    public static javax.swing.JTextField tf_editgromming_keterangan3;
    public static javax.swing.JTextField tf_editgromming_keterangan4;
    public static javax.swing.JTextField tf_editpenitipan_keterangan1;
    public static javax.swing.JTextField tf_editpenitipan_keterangan2;
    public static javax.swing.JTextField tf_editpenitipan_keterangan3;
    public static javax.swing.JTextField tf_editpenitipan_keterangan4;
    private javax.swing.JTextField tf_hargabeli2;
    private javax.swing.JTextField tf_hargabeli3;
    private javax.swing.JTextField tf_hargajual2;
    private javax.swing.JTextField tf_hargajual3;
    private javax.swing.JTextField tf_idbarang2;
    private javax.swing.JTextField tf_idbarang3;
    public static javax.swing.JTextField tf_input_hargagromming;
    public static javax.swing.JTextField tf_input_hargapenitipan;
    public static javax.swing.JTextField tf_input_idgromming;
    public static javax.swing.JTextField tf_input_idpenitipan;
    public static javax.swing.JTextField tf_input_keterangan1;
    public static javax.swing.JTextField tf_input_keterangan2;
    public static javax.swing.JTextField tf_input_keterangan3;
    public static javax.swing.JTextField tf_input_keterangan4;
    public static javax.swing.JTextField tf_input_namagromming;
    public static javax.swing.JTextField tf_input_namapenitipan;
    public static javax.swing.JTextField tf_inputpenitipan_keterangan1;
    public static javax.swing.JTextField tf_inputpenitipan_keterangan2;
    public static javax.swing.JTextField tf_inputpenitipan_keterangan3;
    public static javax.swing.JTextField tf_inputpenitipan_keterangan4;
    private javax.swing.JLabel tf_jumlah;
    private javax.swing.JTextField tf_linkgambar2;
    private javax.swing.JTextField tf_linkgambar3;
    private javax.swing.JTextField tf_merkbarang2;
    private javax.swing.JTextField tf_merkbarang3;
    private javax.swing.JTextField tf_namabarang2;
    private javax.swing.JTextField tf_namabarang3;
    private javax.swing.JTextField tf_stokbarang2;
    private javax.swing.JTextField tf_stokbarang3;
    // End of variables declaration//GEN-END:variables

}
