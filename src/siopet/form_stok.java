    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package siopet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;


public class form_stok extends javax.swing.JFrame {

    
    private boolean dataEdit = false;
    String filename;
    String link_gambar;
    
    public form_stok() {
        initComponents();
        getDataStokSemua();
        getDataStokMakanan();
        getDataStokAksesoris();
        getDataStokPerlengkapan();
        
        //SCROLL BAR//
        spTable.setVerticalScrollBar(scrollBarCustom);
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        spTable1.setVerticalScrollBar(scrollBarCustom3);
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        
        spTable2.setVerticalScrollBar(scrollBarCustom2);
        spTable2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable2.getViewport().setBackground(Color.WHITE);
        
        spTable3.setVerticalScrollBar(scrollBarCustom1);
        spTable3.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable3.getViewport().setBackground(Color.WHITE);
        
        //PANEL STOK//
        panel_stok_semua.setVisible(true);
        panel_stok_makanan.setVisible(false);
        panel_stok_aksesoris.setVisible(false);
        panel_stok_perlengkapan.setVisible(false);
        popUp_keluar2.setVisible(false);
        panel_cari.setVisible(false);
        popup_berhasilEdit.setVisible(false);
        popup_berhasilHapus.setVisible(false);
        popup_berhasilsimpan.setVisible(false);
        popup_konfirmasiHapus.setVisible(false);
        
        input_stok.setVisible(false);
        edit_stok.setVisible(false);
        detail_stok.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input_stok = new javax.swing.JPanel();
        btn_tambah_data = new javax.swing.JLabel();
        close_inputstok = new javax.swing.JLabel();
        btn_upload = new javax.swing.JLabel();
        img_inputbarang = new javax.swing.JLabel();
        cmb_kategori = new javax.swing.JComboBox<>();
        cmb_jenis_hewan = new javax.swing.JComboBox<>();
        tf_inputbarang_hargajual = new javax.swing.JTextField();
        tf_inputbarang_hargabeli = new javax.swing.JTextField();
        tf_inputbarang_stokbarang = new javax.swing.JTextField();
        tf_inputbarang_merkbarang = new javax.swing.JTextField();
        tf_inputbarang_namabarang = new javax.swing.JTextField();
        tf_inputbarang_idbarang = new javax.swing.JTextField();
        bg_edit_barang1 = new javax.swing.JLabel();
        edit_stok = new javax.swing.JPanel();
        btn_edit_data = new javax.swing.JLabel();
        close_inputstok1 = new javax.swing.JLabel();
        btn_upload1 = new javax.swing.JLabel();
        img_editbarang = new javax.swing.JLabel();
        cmb_kategori1 = new javax.swing.JComboBox<>();
        cmb_jenis_hewan1 = new javax.swing.JComboBox<>();
        tf_editbarang_hargajual = new javax.swing.JTextField();
        tf_editbarang_hargabeli = new javax.swing.JTextField();
        tf_editbarang_stokbarang = new javax.swing.JTextField();
        tf_editbarang_merkbarang = new javax.swing.JTextField();
        tf_editbarang_namabarang = new javax.swing.JTextField();
        tf_editbarang_idbarang = new javax.swing.JTextField();
        bg_edit_barang2 = new javax.swing.JLabel();
        detail_stok = new javax.swing.JPanel();
        detail_gambar = new javax.swing.JLabel();
        btn_oke_detail = new javax.swing.JLabel();
        detail_hargabeli = new javax.swing.JLabel();
        detail_hargajual = new javax.swing.JLabel();
        detail_stok_barang = new javax.swing.JLabel();
        detail_kategori = new javax.swing.JLabel();
        detail_jenis_hewan = new javax.swing.JLabel();
        detail_merk_barang = new javax.swing.JLabel();
        detail_id_barang = new javax.swing.JLabel();
        detail_nama_barang = new javax.swing.JLabel();
        bg_edit_barang3 = new javax.swing.JLabel();
        popup_berhasilEdit = new javax.swing.JPanel();
        oke_simpan1 = new javax.swing.JLabel();
        bg_popupsimpan1 = new javax.swing.JLabel();
        popup_konfirmasiHapus = new javax.swing.JPanel();
        tidak_hapus = new javax.swing.JLabel();
        ya_hapus = new javax.swing.JLabel();
        bg_hapus = new javax.swing.JLabel();
        popup_berhasilHapus = new javax.swing.JPanel();
        oke_terhapus = new javax.swing.JLabel();
        bg_terhapus = new javax.swing.JLabel();
        popup_berhasilsimpan = new javax.swing.JPanel();
        oke_simpan = new javax.swing.JLabel();
        bg_popupsimpan = new javax.swing.JLabel();
        panel_cari = new javax.swing.JPanel();
        txtf_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        popUp_keluar2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bg_keluar2 = new javax.swing.JLabel();
        btn_cari = new javax.swing.JLabel();
        lbl_stokPerlengkapan4 = new javax.swing.JLabel();
        lbl_stokAksesoris4 = new javax.swing.JLabel();
        lbl_stokMakanan4 = new javax.swing.JLabel();
        lbl_semuaStok4 = new javax.swing.JLabel();
        lbl_tambah = new javax.swing.JLabel();
        panel_stok_semua = new javax.swing.JPanel();
        lbl_hapus = new javax.swing.JLabel();
        lbl_detail = new javax.swing.JLabel();
        lbl_edit1 = new javax.swing.JLabel();
        cbfilter = new swing.comboBox.Combobox();
        roundedPanel1 = new panelRounded.RoundedPanel();
        spTable = new javax.swing.JScrollPane();
        tabel_stok_semua = new com.raven.swing.Table();
        scrollBarCustom = new scrollbar.custom.ScrollBarCustom();
        bg_stoksemua = new javax.swing.JLabel();
        panel_stok_makanan = new javax.swing.JPanel();
        lbl_hapus3 = new javax.swing.JLabel();
        lbl_detail3 = new javax.swing.JLabel();
        lbl_edit4 = new javax.swing.JLabel();
        cbfilter1 = new swing.comboBox.Combobox();
        roundedPanel4 = new panelRounded.RoundedPanel();
        spTable1 = new javax.swing.JScrollPane();
        tabel_stok_makanan = new com.raven.swing.Table();
        scrollBarCustom3 = new scrollbar.custom.ScrollBarCustom();
        bg_stoksemua1 = new javax.swing.JLabel();
        panel_stok_aksesoris = new javax.swing.JPanel();
        lbl_hapus2 = new javax.swing.JLabel();
        lbl_detail2 = new javax.swing.JLabel();
        lbl_edit3 = new javax.swing.JLabel();
        cbfilter2 = new swing.comboBox.Combobox();
        roundedPanel3 = new panelRounded.RoundedPanel();
        spTable2 = new javax.swing.JScrollPane();
        tabel_stok_aksesoris = new com.raven.swing.Table();
        scrollBarCustom2 = new scrollbar.custom.ScrollBarCustom();
        bg_stoksemua2 = new javax.swing.JLabel();
        panel_stok_perlengkapan = new javax.swing.JPanel();
        lbl_hapus1 = new javax.swing.JLabel();
        lbl_detail1 = new javax.swing.JLabel();
        lbl_edit2 = new javax.swing.JLabel();
        cbfilter3 = new swing.comboBox.Combobox();
        roundedPanel2 = new panelRounded.RoundedPanel();
        spTable3 = new javax.swing.JScrollPane();
        tabel_stok_perlengkapan = new com.raven.swing.Table();
        scrollBarCustom1 = new scrollbar.custom.ScrollBarCustom();
        bg_stoksemua3 = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        lbl_formPegawai = new javax.swing.JLabel();
        Logout2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        input_stok.setBackground(new Color(0,0,0,150));
        input_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                input_stokMouseEntered(evt);
            }
        });
        input_stok.setLayout(null);

        btn_tambah_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambah_dataMouseClicked(evt);
            }
        });
        input_stok.add(btn_tambah_data);
        btn_tambah_data.setBounds(930, 720, 160, 30);

        close_inputstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_inputstokMouseClicked(evt);
            }
        });
        input_stok.add(close_inputstok);
        close_inputstok.setBounds(1490, 240, 50, 40);

        btn_upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_uploadMouseClicked(evt);
            }
        });
        input_stok.add(btn_upload);
        btn_upload.setBounds(1290, 650, 150, 20);
        input_stok.add(img_inputbarang);
        img_inputbarang.setBounds(1260, 360, 210, 270);

        cmb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Aksesoris", "Perlengkapan" }));
        input_stok.add(cmb_kategori);
        cmb_kategori.setBounds(880, 450, 290, 40);

        cmb_jenis_hewan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kucing", "Anjing", "Domestik" }));
        input_stok.add(cmb_jenis_hewan);
        cmb_jenis_hewan.setBounds(560, 450, 300, 40);

        tf_inputbarang_hargajual.setBackground(new Color(0,0,0,0));
        tf_inputbarang_hargajual.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_hargajual);
        tf_inputbarang_hargajual.setBounds(900, 620, 260, 40);

        tf_inputbarang_hargabeli.setBackground(new Color(0,0,0,0));
        tf_inputbarang_hargabeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_hargabeli);
        tf_inputbarang_hargabeli.setBounds(570, 620, 260, 40);

        tf_inputbarang_stokbarang.setBackground(new Color(0,0,0,0));
        tf_inputbarang_stokbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_stokbarang);
        tf_inputbarang_stokbarang.setBounds(900, 530, 260, 40);

        tf_inputbarang_merkbarang.setBackground(new Color(0,0,0,0));
        tf_inputbarang_merkbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_merkbarang);
        tf_inputbarang_merkbarang.setBounds(570, 530, 260, 40);

        tf_inputbarang_namabarang.setBackground(new Color(0,0,0,0));
        tf_inputbarang_namabarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_namabarang);
        tf_inputbarang_namabarang.setBounds(900, 350, 260, 40);

        tf_inputbarang_idbarang.setBackground(new Color(0,0,0,0));
        tf_inputbarang_idbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        input_stok.add(tf_inputbarang_idbarang);
        tf_inputbarang_idbarang.setBounds(570, 350, 260, 40);

        bg_edit_barang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Barang.png"))); // NOI18N
        bg_edit_barang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg_edit_barang1MouseClicked(evt);
            }
        });
        input_stok.add(bg_edit_barang1);
        bg_edit_barang1.setBounds(460, 230, 1101, 565);

        getContentPane().add(input_stok);
        input_stok.setBounds(0, 0, 1920, 1080);

        edit_stok.setBackground(new Color(0,0,0,150));
        edit_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_stokMouseEntered(evt);
            }
        });
        edit_stok.setLayout(null);

        btn_edit_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_dataMouseClicked(evt);
            }
        });
        edit_stok.add(btn_edit_data);
        btn_edit_data.setBounds(930, 720, 160, 40);

        close_inputstok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_inputstok1MouseClicked(evt);
            }
        });
        edit_stok.add(close_inputstok1);
        close_inputstok1.setBounds(1500, 240, 40, 40);

        btn_upload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_upload1MouseClicked(evt);
            }
        });
        edit_stok.add(btn_upload1);
        btn_upload1.setBounds(1300, 640, 140, 30);
        edit_stok.add(img_editbarang);
        img_editbarang.setBounds(1260, 350, 210, 280);

        cmb_kategori1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Aksesoris", "Perlengkapan" }));
        edit_stok.add(cmb_kategori1);
        cmb_kategori1.setBounds(890, 450, 290, 40);

        cmb_jenis_hewan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kucing", "Anjing", "Domestik" }));
        edit_stok.add(cmb_jenis_hewan1);
        cmb_jenis_hewan1.setBounds(560, 450, 290, 40);

        tf_editbarang_hargajual.setBackground(new Color(0,0,0,0));
        tf_editbarang_hargajual.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_hargajual);
        tf_editbarang_hargajual.setBounds(900, 620, 260, 40);

        tf_editbarang_hargabeli.setBackground(new Color(0,0,0,0));
        tf_editbarang_hargabeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_hargabeli);
        tf_editbarang_hargabeli.setBounds(570, 620, 260, 40);

        tf_editbarang_stokbarang.setBackground(new Color(0,0,0,0));
        tf_editbarang_stokbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_stokbarang);
        tf_editbarang_stokbarang.setBounds(900, 530, 260, 40);

        tf_editbarang_merkbarang.setBackground(new Color(0,0,0,0));
        tf_editbarang_merkbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_merkbarang);
        tf_editbarang_merkbarang.setBounds(570, 530, 260, 40);

        tf_editbarang_namabarang.setBackground(new Color(0,0,0,0));
        tf_editbarang_namabarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_namabarang);
        tf_editbarang_namabarang.setBounds(900, 350, 260, 40);

        tf_editbarang_idbarang.setBackground(new Color(0,0,0,0));
        tf_editbarang_idbarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        edit_stok.add(tf_editbarang_idbarang);
        tf_editbarang_idbarang.setBounds(570, 350, 260, 40);

        bg_edit_barang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Barang.png"))); // NOI18N
        edit_stok.add(bg_edit_barang2);
        bg_edit_barang2.setBounds(460, 220, 1110, 590);

        getContentPane().add(edit_stok);
        edit_stok.setBounds(0, 0, 1920, 1080);

        detail_stok.setBackground(new Color(0,0,0,150));
        detail_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detail_stokMouseEntered(evt);
            }
        });
        detail_stok.setLayout(null);
        detail_stok.add(detail_gambar);
        detail_gambar.setBounds(530, 390, 240, 300);

        btn_oke_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke_detailMouseClicked(evt);
            }
        });
        detail_stok.add(btn_oke_detail);
        btn_oke_detail.setBounds(860, 796, 160, 40);
        detail_stok.add(detail_hargabeli);
        detail_hargabeli.setBounds(1030, 690, 200, 30);
        detail_stok.add(detail_hargajual);
        detail_hargajual.setBounds(1030, 640, 200, 30);
        detail_stok.add(detail_stok_barang);
        detail_stok_barang.setBounds(1030, 600, 200, 30);
        detail_stok.add(detail_kategori);
        detail_kategori.setBounds(1030, 550, 200, 30);
        detail_stok.add(detail_jenis_hewan);
        detail_jenis_hewan.setBounds(1030, 510, 200, 30);
        detail_stok.add(detail_merk_barang);
        detail_merk_barang.setBounds(1030, 460, 200, 30);
        detail_stok.add(detail_id_barang);
        detail_id_barang.setBounds(1030, 420, 200, 30);
        detail_stok.add(detail_nama_barang);
        detail_nama_barang.setBounds(1030, 370, 200, 30);

        bg_edit_barang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Detail Barang.png"))); // NOI18N
        bg_edit_barang3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg_edit_barang3MouseClicked(evt);
            }
        });
        detail_stok.add(bg_edit_barang3);
        bg_edit_barang3.setBounds(470, 190, 940, 710);

        getContentPane().add(detail_stok);
        detail_stok.setBounds(0, 0, 1920, 1080);

        popup_berhasilEdit.setBackground(new Color(0,0,0,200));
        popup_berhasilEdit.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilEdit.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popup_berhasilEditMouseEntered(evt);
            }
        });
        popup_berhasilEdit.setLayout(null);

        oke_simpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oke_simpan1MouseClicked(evt);
            }
        });
        popup_berhasilEdit.add(oke_simpan1);
        oke_simpan1.setBounds(920, 610, 160, 40);

        bg_popupsimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup berhasil diedit.png"))); // NOI18N
        bg_popupsimpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bg_popupsimpan1MouseEntered(evt);
            }
        });
        popup_berhasilEdit.add(bg_popupsimpan1);
        bg_popupsimpan1.setBounds(660, 390, 680, 310);

        getContentPane().add(popup_berhasilEdit);
        popup_berhasilEdit.setBounds(0, 0, 1920, 1080);

        popup_konfirmasiHapus.setBackground(new Color(0,0,0,200));
        popup_konfirmasiHapus.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_konfirmasiHapus.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_konfirmasiHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popup_konfirmasiHapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popup_konfirmasiHapusMouseEntered(evt);
            }
        });
        popup_konfirmasiHapus.setLayout(null);

        tidak_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tidak_hapusMouseClicked(evt);
            }
        });
        popup_konfirmasiHapus.add(tidak_hapus);
        tidak_hapus.setBounds(820, 610, 160, 40);

        ya_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ya_hapusMouseClicked(evt);
            }
        });
        popup_konfirmasiHapus.add(ya_hapus);
        ya_hapus.setBounds(1020, 610, 160, 40);

        bg_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup konfirmasi hapus.png"))); // NOI18N
        bg_hapus.setToolTipText("");
        popup_konfirmasiHapus.add(bg_hapus);
        bg_hapus.setBounds(660, 390, 680, 310);

        getContentPane().add(popup_konfirmasiHapus);
        popup_konfirmasiHapus.setBounds(0, 0, 1920, 1080);

        popup_berhasilHapus.setBackground(new Color(0,0,0,200));
        popup_berhasilHapus.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilHapus.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popup_berhasilHapusMouseEntered(evt);
            }
        });
        popup_berhasilHapus.setLayout(null);

        oke_terhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oke_terhapusMouseClicked(evt);
            }
        });
        popup_berhasilHapus.add(oke_terhapus);
        oke_terhapus.setBounds(920, 610, 160, 40);

        bg_terhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup data berhasil dihapus.png"))); // NOI18N
        popup_berhasilHapus.add(bg_terhapus);
        bg_terhapus.setBounds(660, 390, 680, 310);

        getContentPane().add(popup_berhasilHapus);
        popup_berhasilHapus.setBounds(0, 0, 1920, 1080);

        popup_berhasilsimpan.setBackground(new Color(0,0,0,200));
        popup_berhasilsimpan.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilsimpan.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popup_berhasilsimpanMouseEntered(evt);
            }
        });
        popup_berhasilsimpan.setLayout(null);

        oke_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oke_simpanMouseClicked(evt);
            }
        });
        popup_berhasilsimpan.add(oke_simpan);
        oke_simpan.setBounds(920, 610, 160, 40);

        bg_popupsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup data berhasil disimpan.png"))); // NOI18N
        bg_popupsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bg_popupsimpanMouseEntered(evt);
            }
        });
        popup_berhasilsimpan.add(bg_popupsimpan);
        bg_popupsimpan.setBounds(660, 390, 680, 310);

        getContentPane().add(popup_berhasilsimpan);
        popup_berhasilsimpan.setBounds(0, 0, 1920, 1080);

        panel_cari.setBackground(new Color(0,0,0,0));
        panel_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_cariMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_cariMouseEntered(evt);
            }
        });
        panel_cari.setLayout(null);

        txtf_cari.setBackground(new Color(0,0,0,0));
        txtf_cari.setBorder(null);
        txtf_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariKeyReleased(evt);
            }
        });
        panel_cari.add(txtf_cari);
        txtf_cari.setBounds(30, 10, 250, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btn_search_clicked.png"))); // NOI18N
        panel_cari.add(jLabel1);
        jLabel1.setBounds(16, 0, 331, 62);

        getContentPane().add(panel_cari);
        panel_cari.setBounds(1200, 130, 350, 60);

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

        getContentPane().add(popUp_keluar2);
        popUp_keluar2.setBounds(0, 0, 1920, 1080);

        btn_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cariMouseEntered(evt);
            }
        });
        getContentPane().add(btn_cari);
        btn_cari.setBounds(1490, 130, 60, 60);

        lbl_stokPerlengkapan4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_stokPerlengkapan4MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_stokPerlengkapan4);
        lbl_stokPerlengkapan4.setBounds(980, 160, 210, 30);

        lbl_stokAksesoris4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_stokAksesoris4MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_stokAksesoris4);
        lbl_stokAksesoris4.setBounds(800, 160, 180, 30);

        lbl_stokMakanan4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_stokMakanan4MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_stokMakanan4);
        lbl_stokMakanan4.setBounds(600, 160, 200, 30);

        lbl_semuaStok4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_semuaStok4MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_semuaStok4);
        lbl_semuaStok4.setBounds(410, 160, 190, 30);

        lbl_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_tambah);
        lbl_tambah.setBounds(1820, 130, 60, 60);

        panel_stok_semua.setPreferredSize(new java.awt.Dimension(1920, 1080));
        panel_stok_semua.setLayout(null);

        lbl_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hapusMouseClicked(evt);
            }
        });
        panel_stok_semua.add(lbl_hapus);
        lbl_hapus.setBounds(1650, 130, 60, 60);

        lbl_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_detailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_detailMouseEntered(evt);
            }
        });
        panel_stok_semua.add(lbl_detail);
        lbl_detail.setBounds(1570, 130, 60, 60);

        lbl_edit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_edit1MouseClicked(evt);
            }
        });
        panel_stok_semua.add(lbl_edit1);
        lbl_edit1.setBounds(1730, 130, 60, 60);

        cbfilter.setBorder(null);
        cbfilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Stok Paling--", "Banyak", "Sedikit" }));
        cbfilter.setToolTipText("");
        cbfilter.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        cbfilter.setLabeTaxt("");
        cbfilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfilterActionPerformed(evt);
            }
        });
        panel_stok_semua.add(cbfilter);
        cbfilter.setBounds(410, 220, 220, 40);

        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable.setBorder(null);
        spTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spTableMouseClicked(evt);
            }
        });

        tabel_stok_semua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_stok_semua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_stok_semuaMouseClicked(evt);
            }
        });
        spTable.setViewportView(tabel_stok_semua);

        roundedPanel1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1430, 700));
        roundedPanel1.add(scrollBarCustom, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, 110));

        panel_stok_semua.add(roundedPanel1);
        roundedPanel1.setBounds(410, 280, 1490, 740);

        bg_stoksemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stock - semua.png"))); // NOI18N
        panel_stok_semua.add(bg_stoksemua);
        bg_stoksemua.setBounds(0, 0, 1920, 1080);

        getContentPane().add(panel_stok_semua);
        panel_stok_semua.setBounds(0, 0, 1920, 1080);

        panel_stok_makanan.setPreferredSize(new java.awt.Dimension(1920, 1080));
        panel_stok_makanan.setLayout(null);

        lbl_hapus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hapus3MouseClicked(evt);
            }
        });
        panel_stok_makanan.add(lbl_hapus3);
        lbl_hapus3.setBounds(1650, 130, 60, 60);

        lbl_detail3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_detail3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_detail3MouseEntered(evt);
            }
        });
        panel_stok_makanan.add(lbl_detail3);
        lbl_detail3.setBounds(1570, 130, 60, 60);

        lbl_edit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_edit4MouseClicked(evt);
            }
        });
        panel_stok_makanan.add(lbl_edit4);
        lbl_edit4.setBounds(1730, 130, 60, 60);

        cbfilter1.setBorder(null);
        cbfilter1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Stok Paling--", "Banyak", "Sedikit" }));
        cbfilter1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        cbfilter1.setLabeTaxt("");
        cbfilter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfilter1ActionPerformed(evt);
            }
        });
        panel_stok_makanan.add(cbfilter1);
        cbfilter1.setBounds(410, 220, 220, 40);

        roundedPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable1.setBorder(null);

        tabel_stok_makanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_stok_makanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_stok_makananMouseClicked(evt);
            }
        });
        spTable1.setViewportView(tabel_stok_makanan);

        roundedPanel4.add(spTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1410, 710));
        roundedPanel4.add(scrollBarCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, 110));

        panel_stok_makanan.add(roundedPanel4);
        roundedPanel4.setBounds(410, 280, 1490, 740);

        bg_stoksemua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stock - makanan.png"))); // NOI18N
        panel_stok_makanan.add(bg_stoksemua1);
        bg_stoksemua1.setBounds(0, 0, 1920, 1080);

        getContentPane().add(panel_stok_makanan);
        panel_stok_makanan.setBounds(0, 0, 1920, 1080);

        panel_stok_aksesoris.setPreferredSize(new java.awt.Dimension(1920, 1080));
        panel_stok_aksesoris.setLayout(null);

        lbl_hapus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hapus2MouseClicked(evt);
            }
        });
        panel_stok_aksesoris.add(lbl_hapus2);
        lbl_hapus2.setBounds(1650, 130, 60, 60);

        lbl_detail2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_detail2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_detail2MouseEntered(evt);
            }
        });
        panel_stok_aksesoris.add(lbl_detail2);
        lbl_detail2.setBounds(1570, 130, 60, 60);

        lbl_edit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_edit3MouseClicked(evt);
            }
        });
        panel_stok_aksesoris.add(lbl_edit3);
        lbl_edit3.setBounds(1730, 130, 60, 60);

        cbfilter2.setBorder(null);
        cbfilter2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Stok Paling--", "Banyak", "Sedikit" }));
        cbfilter2.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        cbfilter2.setLabeTaxt("");
        cbfilter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfilter2ActionPerformed(evt);
            }
        });
        panel_stok_aksesoris.add(cbfilter2);
        cbfilter2.setBounds(410, 220, 220, 40);

        roundedPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable2.setBorder(null);

        tabel_stok_aksesoris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_stok_aksesoris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_stok_aksesorisMouseClicked(evt);
            }
        });
        spTable2.setViewportView(tabel_stok_aksesoris);

        roundedPanel3.add(spTable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1410, 730));
        roundedPanel3.add(scrollBarCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, 110));

        panel_stok_aksesoris.add(roundedPanel3);
        roundedPanel3.setBounds(410, 280, 1490, 740);

        bg_stoksemua2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stock - aksesoris.png"))); // NOI18N
        panel_stok_aksesoris.add(bg_stoksemua2);
        bg_stoksemua2.setBounds(0, 0, 1920, 1080);

        getContentPane().add(panel_stok_aksesoris);
        panel_stok_aksesoris.setBounds(0, 0, 1920, 1080);

        panel_stok_perlengkapan.setPreferredSize(new java.awt.Dimension(1920, 1080));
        panel_stok_perlengkapan.setLayout(null);

        lbl_hapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hapus1MouseClicked(evt);
            }
        });
        panel_stok_perlengkapan.add(lbl_hapus1);
        lbl_hapus1.setBounds(1650, 130, 60, 60);

        lbl_detail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_detail1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_detail1MouseEntered(evt);
            }
        });
        panel_stok_perlengkapan.add(lbl_detail1);
        lbl_detail1.setBounds(1570, 130, 60, 60);

        lbl_edit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_edit2MouseClicked(evt);
            }
        });
        panel_stok_perlengkapan.add(lbl_edit2);
        lbl_edit2.setBounds(1730, 130, 60, 60);

        cbfilter3.setBorder(null);
        cbfilter3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Stok Paling--", "Banyak", "Sedikit" }));
        cbfilter3.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        cbfilter3.setLabeTaxt("");
        cbfilter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfilter3ActionPerformed(evt);
            }
        });
        panel_stok_perlengkapan.add(cbfilter3);
        cbfilter3.setBounds(410, 220, 220, 40);

        roundedPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable3.setBorder(null);

        tabel_stok_perlengkapan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_stok_perlengkapan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_stok_perlengkapanMouseClicked(evt);
            }
        });
        spTable3.setViewportView(tabel_stok_perlengkapan);

        roundedPanel2.add(spTable3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1410, 730));
        roundedPanel2.add(scrollBarCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 10, 110));

        panel_stok_perlengkapan.add(roundedPanel2);
        roundedPanel2.setBounds(410, 280, 1490, 740);

        bg_stoksemua3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stock - perlengkapan.png"))); // NOI18N
        panel_stok_perlengkapan.add(bg_stoksemua3);
        bg_stoksemua3.setBounds(0, 0, 1920, 1080);

        getContentPane().add(panel_stok_perlengkapan);
        panel_stok_perlengkapan.setBounds(0, 0, 1920, 1080);

        lbl_formDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formDashboard);
        lbl_formDashboard.setBounds(0, 220, 340, 40);

        lbl_formStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseEntered(evt);
            }
        });
        getContentPane().add(lbl_formStok);
        lbl_formStok.setBounds(0, 280, 340, 60);

        lbl_formLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLayananMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLayanan);
        lbl_formLayanan.setBounds(0, 360, 340, 50);

        lbl_formTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formTransaksi);
        lbl_formTransaksi.setBounds(0, 420, 340, 70);

        lbl_formLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLaporanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLaporan);
        lbl_formLaporan.setBounds(0, 500, 340, 60);

        lbl_formPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formPegawaiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formPegawai);
        lbl_formPegawai.setBounds(0, 590, 340, 50);

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        getContentPane().add(Logout2);
        Logout2.setBounds(0, 970, 340, 60);

        setBounds(0, 0, 1920, 1042);
    }// </editor-fold>//GEN-END:initComponents

    public void getDataStokSemua(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        tabel_stok_semua.setModel(dtm);
        
        String cari = txtf_cari.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `tb_barang` WHERE tb_barang.nama_barang LIKE '%" + cari + "%'");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getString("jenis_hewan"),
                    rs.getString("stok"),
                    rs.getString("harga_jual"),
                });
                tabel_stok_semua.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
     
     public void getDataStokMakanan(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        tabel_stok_makanan.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `tb_barang` where kategori = 'Makanan'");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_hewan"),
                    rs.getString("stok"),
                    rs.getString("harga_jual")
                });
                tabel_stok_makanan.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
     
     public void getDataStokAksesoris(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        tabel_stok_aksesoris.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `tb_barang` where kategori = 'Aksesoris'");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_hewan"),
                    rs.getString("stok"),
                    rs.getString("harga_jual")
                });
                tabel_stok_aksesoris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
     
     public void getDataStokPerlengkapan(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        tabel_stok_perlengkapan.setModel(dtm);
       
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `tb_barang` where kategori = 'Perlengkapan'");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_hewan"),
                    rs.getString("stok"),
                    rs.getString("harga_jual")
                });
                tabel_stok_perlengkapan.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
     
    
    private void btn_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariMouseClicked
        panel_cari.setVisible(true);
    }//GEN-LAST:event_btn_cariMouseClicked

    private void btn_cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariMouseEntered
        //        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/siopet/btn_search_clicked.png")));
    }//GEN-LAST:event_btn_cariMouseEntered

    private void lbl_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tambahMouseClicked
        dataEdit = false;
        input_stok.setVisible(true);
    }//GEN-LAST:event_lbl_tambahMouseClicked

    private void lbl_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hapusMouseClicked
        try{
            int i =tabel_stok_semua.getSelectedRow();
            TableModel tbl = tabel_stok_semua.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            String sql = "DELETE FROM tb_barang WHERE id_barang = '"+no+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            link_gambar = null;
            
            popup_berhasilHapus.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal dihapus");
        }
    }//GEN-LAST:event_lbl_hapusMouseClicked

    private void lbl_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detailMouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_semua.getSelectedRow();
            TableModel tbl = tabel_stok_semua.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            detail_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    detail_nama_barang.setText(rs.getString("id_barang"));
                    detail_id_barang.setText(rs.getString("nama_barang"));
                    detail_merk_barang.setText(rs.getString("merk"));
                    detail_jenis_hewan.setText(rs.getString("jenis_hewan"));
                    detail_kategori.setText(rs.getString("kategori"));
                    detail_stok_barang.setText(rs.getString("stok"));
                    detail_hargajual.setText(rs.getString("harga_jual"));
                    detail_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(detail_gambar.getWidth(), detail_gambar.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    detail_gambar.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_detailMouseClicked

    private void lbl_detailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detailMouseEntered
        //        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/siopet/btn_search_clicked.png")));
    }//GEN-LAST:event_lbl_detailMouseEntered

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

    private void lbl_formPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formPegawaiMouseClicked
        this.setVisible(false);
        new form_pegawai().setVisible(true);
    }//GEN-LAST:event_lbl_formPegawaiMouseClicked

    private void lbl_formLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLaporanMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsung().setVisible(true);
    }//GEN-LAST:event_lbl_formLaporanMouseClicked

    private void tabel_stok_makananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_stok_makananMouseClicked
        dataEdit = true;
    }//GEN-LAST:event_tabel_stok_makananMouseClicked

    private void tabel_stok_aksesorisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_stok_aksesorisMouseClicked
        dataEdit = true;
    }//GEN-LAST:event_tabel_stok_aksesorisMouseClicked

    private void tabel_stok_perlengkapanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_stok_perlengkapanMouseClicked
        dataEdit = true;
    }//GEN-LAST:event_tabel_stok_perlengkapanMouseClicked

    private void lbl_stokPerlengkapan4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_stokPerlengkapan4MouseClicked
        panel_stok_semua.setVisible(false);
        panel_stok_makanan.setVisible(false);
        panel_stok_aksesoris.setVisible(false);
        panel_stok_perlengkapan.setVisible(true);
    }//GEN-LAST:event_lbl_stokPerlengkapan4MouseClicked

    private void lbl_stokAksesoris4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_stokAksesoris4MouseClicked
        panel_stok_semua.setVisible(false);
        panel_stok_makanan.setVisible(false);
        panel_stok_aksesoris.setVisible(true);
        panel_stok_perlengkapan.setVisible(false);
    }//GEN-LAST:event_lbl_stokAksesoris4MouseClicked

    private void lbl_stokMakanan4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_stokMakanan4MouseClicked
        panel_stok_semua.setVisible(false);
        panel_stok_makanan.setVisible(true);
        panel_stok_aksesoris.setVisible(false);
        panel_stok_perlengkapan.setVisible(false);
    }//GEN-LAST:event_lbl_stokMakanan4MouseClicked

    private void lbl_semuaStok4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_semuaStok4MouseClicked
        panel_stok_semua.setVisible(true);
        panel_stok_makanan.setVisible(false);
        panel_stok_aksesoris.setVisible(false);
        panel_stok_perlengkapan.setVisible(false);
    }//GEN-LAST:event_lbl_semuaStok4MouseClicked

    private void close_inputstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_inputstokMouseClicked
        input_stok.setVisible(false);
    }//GEN-LAST:event_close_inputstokMouseClicked

    private void input_stokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_input_stokMouseEntered
        input_stok.setVisible(true);
        cbfilter1.setEnabled(false);
        cbfilter2.setEnabled(false);
        cbfilter3.setEnabled(false);
        this.setVisible(true);
    }//GEN-LAST:event_input_stokMouseEntered

    private void close_inputstok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_inputstok1MouseClicked
        edit_stok.setVisible(false);
    }//GEN-LAST:event_close_inputstok1MouseClicked

    private void edit_stokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_stokMouseEntered
        
    }//GEN-LAST:event_edit_stokMouseEntered

    private void btn_tambah_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah_dataMouseClicked
        try{
            String sql = "INSERT INTO tb_barang(`id_barang`, `nama_barang`, `kategori`, `jenis_hewan`, `merk`, `stok`, `harga_jual`, `harga_beli`, `img`) VALUES"
                    + "('"+tf_inputbarang_idbarang.getText()+"','"
                    +tf_inputbarang_namabarang.getText()+"','"
                    +cmb_kategori.getSelectedItem()+"','"
                    +cmb_jenis_hewan.getSelectedItem()+"','"
                    +tf_inputbarang_merkbarang.getText()+"','"
                    +tf_inputbarang_stokbarang.getText()+"','"
                    +tf_inputbarang_hargabeli.getText()+"','"
                    +tf_inputbarang_hargajual.getText()+"','"
                    +link_gambar+"')";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            input_stok.setVisible(false);
            link_gambar = null;
            
            popup_berhasilsimpan.setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_tambah_dataMouseClicked

    private void lbl_edit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_edit1MouseClicked
       if (dataEdit == true) {
            int i =tabel_stok_semua.getSelectedRow();
            TableModel tbl = tabel_stok_semua.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            edit_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    tf_editbarang_namabarang.setText(rs.getString("nama_barang"));
                    tf_editbarang_idbarang.setText(rs.getString("id_barang"));
                    tf_editbarang_merkbarang.setText(rs.getString("merk"));
                    cmb_jenis_hewan.setSelectedItem((rs.getString("jenis_hewan")));
                    cmb_kategori.setSelectedItem((rs.getString("kategori")));
                    tf_editbarang_stokbarang.setText(rs.getString("stok"));
                    tf_editbarang_hargajual.setText(rs.getString("harga_jual"));
                    tf_editbarang_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(img_editbarang.getWidth(), img_editbarang.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    img_editbarang.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_edit1MouseClicked

    private void lbl_edit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_edit2MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_perlengkapan.getSelectedRow();
            TableModel tbl = tabel_stok_perlengkapan.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            edit_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    tf_editbarang_namabarang.setText(rs.getString("nama_barang"));
                    tf_editbarang_idbarang.setText(rs.getString("id_barang"));
                    tf_editbarang_merkbarang.setText(rs.getString("merk"));
                    cmb_jenis_hewan.setSelectedItem((rs.getString("jenis_hewan")));
                    cmb_kategori.setSelectedItem((rs.getString("kategori")));
                    tf_editbarang_stokbarang.setText(rs.getString("stok"));
                    tf_editbarang_hargajual.setText(rs.getString("harga_jual"));
                    tf_editbarang_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(img_editbarang.getWidth(), img_editbarang.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    img_editbarang.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_edit2MouseClicked

    private void lbl_edit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_edit3MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_aksesoris.getSelectedRow();
            TableModel tbl = tabel_stok_aksesoris.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            edit_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    tf_editbarang_namabarang.setText(rs.getString("nama_barang"));
                    tf_editbarang_idbarang.setText(rs.getString("id_barang"));
                    tf_editbarang_merkbarang.setText(rs.getString("merk"));
                    cmb_jenis_hewan.setSelectedItem((rs.getString("jenis_hewan")));
                    cmb_kategori.setSelectedItem((rs.getString("kategori")));
                    tf_editbarang_stokbarang.setText(rs.getString("stok"));
                    tf_editbarang_hargajual.setText(rs.getString("harga_jual"));
                    tf_editbarang_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(img_editbarang.getWidth(), img_editbarang.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    img_editbarang.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_edit3MouseClicked

    private void lbl_edit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_edit4MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_makanan.getSelectedRow();
            TableModel tbl = tabel_stok_makanan.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            edit_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    tf_editbarang_namabarang.setText(rs.getString("nama_barang"));
                    tf_editbarang_idbarang.setText(rs.getString("id_barang"));
                    tf_editbarang_merkbarang.setText(rs.getString("merk"));
                    cmb_jenis_hewan.setSelectedItem((rs.getString("jenis_hewan")));
                    cmb_kategori.setSelectedItem((rs.getString("kategori")));
                    tf_editbarang_stokbarang.setText(rs.getString("stok"));
                    tf_editbarang_hargajual.setText(rs.getString("harga_jual"));
                    tf_editbarang_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(img_editbarang.getWidth(), img_editbarang.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    img_editbarang.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_edit4MouseClicked

    private void btn_upload1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_upload1MouseClicked
        try{ 
            JFileChooser chooser = new JFileChooser(); 
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.toString());
            Image image = icon.getImage().getScaledInstance(img_editbarang.getWidth(), img_editbarang.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon ic = new ImageIcon(image);
            img_editbarang.setIcon(ic);
            
            filename = f.getAbsolutePath();
            String newpath = "src/upload/img"; 
            File directory = new File(newpath);
            if(!directory.exists()){
                directory.mkdir();
            }
            
            File sourceFile = null;
            File destinationFile = null;
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            sourceFile = new File(filename);
            Date tanggal_update = new Date();
            String tampilan = "yyyyMMddhhmmss";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(tanggal_update));
            destinationFile = new File(newpath+"/img" + tanggal+"."+extension);
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
            String link_img = newpath + "/img" + tanggal+"."+extension;
            link_gambar = link_img;

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errors"); 
        }
    }//GEN-LAST:event_btn_upload1MouseClicked

    private void btn_uploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_uploadMouseClicked
       try{ 
            JFileChooser chooser = new JFileChooser(); 
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.toString());
            Image image = icon.getImage().getScaledInstance(img_inputbarang.getWidth(), img_inputbarang.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon ic = new ImageIcon(image);
            img_inputbarang.setIcon(ic);
            
            filename = f.getAbsolutePath();
            String newpath = "src/upload/img"; 
            File directory = new File(newpath);
            if(!directory.exists()){
                directory.mkdir();
            }
            
            File sourceFile = null;
            File destinationFile = null;
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            sourceFile = new File(filename);
            Date tanggal_update = new Date();
            String tampilan = "yyyyMMddhhmmss";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(tanggal_update));
            destinationFile = new File(newpath+"/img" + tanggal+"."+extension);
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
            String link_img = newpath + "/img" + tanggal+"."+extension;
            link_gambar = link_img;

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errors"); 
        }
    }//GEN-LAST:event_btn_uploadMouseClicked

    private void bg_edit_barang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_edit_barang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_edit_barang1MouseClicked

    private void btn_edit_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_dataMouseClicked
        try{
            String id = tf_editbarang_idbarang.getText();
            String nama = tf_editbarang_namabarang.getText();
            String merk = tf_editbarang_merkbarang.getText();
            String stok = tf_editbarang_stokbarang.getText();
            String harga_beli = tf_editbarang_hargabeli.getText();
            String harga_jual =  tf_editbarang_hargajual.getText();
            String sql = "UPDATE tb_barang SET nama_barang='" + nama + "', "
                        + "kategori='" + cmb_kategori1.getSelectedItem() + "', "
                        + "jenis_hewan='" + cmb_jenis_hewan1.getSelectedItem() + "', "
                        + "merk='" + merk + "', "
                        + "stok='" + stok + "', "
                        + "harga_jual='" + harga_jual + "', "
                        + "harga_beli='" + harga_beli + "', "
                        + "img='" + link_gambar + "' "
                        + "WHERE id_barang='" + id + "'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            edit_stok.setVisible(false);
            
            popup_berhasilEdit.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btn_edit_dataMouseClicked

    private void lbl_detail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail1MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_perlengkapan.getSelectedRow();
            TableModel tbl = tabel_stok_perlengkapan.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            detail_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    detail_nama_barang.setText(rs.getString("nama_barang"));
                    detail_id_barang.setText(rs.getString("id_barang"));
                    detail_merk_barang.setText(rs.getString("merk"));
                    detail_jenis_hewan.setText(rs.getString("jenis_hewan"));
                    detail_kategori.setText(rs.getString("kategori"));
                    detail_stok_barang.setText(rs.getString("stok"));
                    detail_hargajual.setText(rs.getString("harga_jual"));
                    detail_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(detail_gambar.getWidth(), detail_gambar.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    detail_gambar.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_detail1MouseClicked

    private void lbl_detail1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_detail1MouseEntered

    private void lbl_detail2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail2MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_aksesoris.getSelectedRow();
            TableModel tbl = tabel_stok_aksesoris.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            detail_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    detail_nama_barang.setText(rs.getString("nama_barang"));
                    detail_id_barang.setText(rs.getString("id_barang"));
                    detail_merk_barang.setText(rs.getString("merk"));
                    detail_jenis_hewan.setText(rs.getString("jenis_hewan"));
                    detail_kategori.setText(rs.getString("kategori"));
                    detail_stok_barang.setText(rs.getString("stok"));
                    detail_hargajual.setText(rs.getString("harga_jual"));
                    detail_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(detail_gambar.getWidth(), detail_gambar.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    detail_gambar.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_detail2MouseClicked

    private void lbl_detail2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_detail2MouseEntered

    private void lbl_detail3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail3MouseClicked
        if (dataEdit == true) {
            int i =tabel_stok_makanan.getSelectedRow();
            TableModel tbl = tabel_stok_makanan.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            
            detail_stok.setVisible(true);
            try{
                Statement st = (Statement)config.configDB().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tb_barang where id_barang = '"+no+"'");
                while(rs.next()){
                    detail_nama_barang.setText(rs.getString("nama_barang"));
                    detail_id_barang.setText(rs.getString("id_barang"));
                    detail_merk_barang.setText(rs.getString("merk"));
                    detail_jenis_hewan.setText(rs.getString("jenis_hewan"));
                    detail_kategori.setText(rs.getString("kategori"));
                    detail_stok_barang.setText(rs.getString("stok"));
                    detail_hargajual.setText(rs.getString("harga_jual"));
                    detail_hargabeli.setText(rs.getString("harga_beli"));
                    String query = rs.getString("img");
                    ImageIcon icon = new ImageIcon(query);
                    Image image = icon.getImage().getScaledInstance(detail_gambar.getWidth(), detail_gambar.getHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic = new ImageIcon(image);
                    detail_gambar.setIcon(ic);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dataEdit = false;
        }else{
            JOptionPane.showMessageDialog(null, "pilih data");
        }
    }//GEN-LAST:event_lbl_detail3MouseClicked

    private void lbl_detail3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_detail3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_detail3MouseEntered

    private void bg_edit_barang3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_edit_barang3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_edit_barang3MouseClicked

    private void detail_stokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail_stokMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_detail_stokMouseEntered

    private void btn_oke_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke_detailMouseClicked
        detail_stok.setVisible(false);
    }//GEN-LAST:event_btn_oke_detailMouseClicked

    private void lbl_hapus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hapus1MouseClicked
        try{
            int i = tabel_stok_perlengkapan.getSelectedRow();
            TableModel tbl = tabel_stok_perlengkapan.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            String sql = "DELETE FROM tb_barang WHERE kategori = 'Perlengkapan' AND id_barang = '"+no+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            link_gambar = null;
        popup_berhasilHapus.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal dihapus");
        }
    }//GEN-LAST:event_lbl_hapus1MouseClicked

    private void lbl_hapus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hapus2MouseClicked
        try{
            int i = tabel_stok_aksesoris.getSelectedRow();
            TableModel tbl = tabel_stok_aksesoris.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            String sql = "DELETE FROM tb_barang WHERE kategori = 'Aksesoris' AND id_barang = '"+no+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            link_gambar = null;
        popup_berhasilHapus.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal dihapus");
        }
    }//GEN-LAST:event_lbl_hapus2MouseClicked

    private void lbl_hapus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hapus3MouseClicked
        try{
            int i = tabel_stok_aksesoris.getSelectedRow();
            TableModel tbl = tabel_stok_aksesoris.getModel();
            String no = tbl.getValueAt(i, 0).toString();
            String sql = "DELETE FROM tb_barang WHERE kategori = 'Makanan' AND id_barang = '"+no+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            getDataStokSemua();
            getDataStokMakanan();
            getDataStokAksesoris();
            getDataStokPerlengkapan();
            link_gambar = null;
        popup_berhasilHapus.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal dihapus");
        }
    }//GEN-LAST:event_lbl_hapus3MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_stok.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void spTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spTableMouseClicked
        dataEdit = true;
    }//GEN-LAST:event_spTableMouseClicked

    private void tabel_stok_semuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_stok_semuaMouseClicked
        dataEdit = true;
    }//GEN-LAST:event_tabel_stok_semuaMouseClicked

    private void Logout2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout2MouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_Logout2MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        popUp_keluar2.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txtf_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariKeyReleased
        getDataStokSemua();
    }//GEN-LAST:event_txtf_cariKeyReleased

    private void panel_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseClicked
        panel_cari.setVisible(false);
    }//GEN-LAST:event_panel_cariMouseClicked

    private void panel_cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_cariMouseEntered

    private void cbfilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfilterActionPerformed
        try{

            int filter = cbfilter.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();

            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM tb_barang GROUP BY id_barang ORDER BY id_barang ASC";
            }
            else if (filter == 1){
                msql = "SELECT * FROM tb_barang GROUP BY id_barang ORDER BY stok DESC";
            }
            else if (filter == 2){
                msql = "SELECT * FROM tb_barang GROUP BY id_barang ORDER BY stok ASC";
            }
            else{
                msql = "SELECT * FROM tb_barang GROUP BY id_barang ORDER BY id_barang ASC";

            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jenis Hewan");
            tbl.addColumn("Kategori");
            tbl.addColumn("stok");
            tbl.addColumn("Harga Jual");

            tabel_stok_semua.setModel(tbl);

            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_barang"),
                    result.getString("nama_barang"),
                    result.getString("jenis_hewan"),
                    result.getString("kategori"),
                    result.getString("stok"),
                    result.getString("harga_jual"),
                });
                tabel_stok_semua.setModel(tbl);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_cbfilterActionPerformed

    private void cbfilter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfilter1ActionPerformed
try{

            int filter = cbfilter1.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();

            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM tb_barang where kategori = 'Makanan' GROUP BY id_barang ORDER BY id_barang ASC";
            }
            else if (filter == 1){
                msql = "SELECT * FROM tb_barang where kategori = 'Makanan' GROUP BY id_barang ORDER BY stok DESC";
            }
            else if (filter == 2){
                msql = "SELECT * FROM tb_barang where kategori = 'Makanan' GROUP BY id_barang ORDER BY stok ASC";
            }
            else{
                msql = "SELECT * FROM tb_barang where kategori = 'Makanan' GROUP BY id_barang ORDER BY id_barang ASC";

            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jenis Hewan");
            tbl.addColumn("stok");
            tbl.addColumn("Harga Jual");

            tabel_stok_makanan.setModel(tbl);

            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_barang"),
                    result.getString("nama_barang"),
                    result.getString("jenis_hewan"),
                    result.getString("stok"),
                    result.getString("harga_jual"),
                });
                tabel_stok_makanan.setModel(tbl);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_cbfilter1ActionPerformed

    private void cbfilter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfilter2ActionPerformed
        try{

            int filter = cbfilter2.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();

            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM tb_barang where kategori = 'Aksesoris' GROUP BY id_barang ORDER BY id_barang ASC";
            }
            else if (filter == 1){
                msql = "SELECT * FROM tb_barang where kategori = 'Aksesoris' GROUP BY id_barang ORDER BY stok DESC";
            }
            else if (filter == 2){
                msql = "SELECT * FROM tb_barang where kategori = 'Aksesoris' GROUP BY id_barang ORDER BY stok ASC";
            }
            else{
                msql = "SELECT * FROM tb_barang where kategori = 'Aksesoris' GROUP BY id_barang ORDER BY id_barang ASC";

            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jenis Hewan");
            tbl.addColumn("stok");
            tbl.addColumn("Harga Jual");

            tabel_stok_aksesoris.setModel(tbl);

            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_barang"),
                    result.getString("nama_barang"),
                    result.getString("jenis_hewan"),
                    result.getString("stok"),
                    result.getString("harga_jual"),
                });
                tabel_stok_aksesoris.setModel(tbl);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_cbfilter2ActionPerformed

    private void cbfilter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfilter3ActionPerformed
        try{

            int filter = cbfilter3.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();

            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM tb_barang where kategori = 'Perlengkapan' GROUP BY id_barang ORDER BY id_barang ASC";
            }
            else if (filter == 1){
                msql = "SELECT * FROM tb_barang where kategori = 'Perlengkapan' GROUP BY id_barang ORDER BY stok DESC";
            }
            else if (filter == 2){
                msql = "SELECT * FROM tb_barang where kategori = 'Perlengkapan' GROUP BY id_barang ORDER BY stok ASC";
            }
            else{
                msql = "SELECT * FROM tb_barang where kategori = 'Perlengkapan' GROUP BY id_barang ORDER BY id_barang ASC";

            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Barang");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jenis Hewan");
            tbl.addColumn("stok");
            tbl.addColumn("Harga Jual");

            tabel_stok_perlengkapan.setModel(tbl);

            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_barang"),
                    result.getString("nama_barang"),
                    result.getString("jenis_hewan"),
                    result.getString("stok"),
                    result.getString("harga_jual"),
                });
                tabel_stok_perlengkapan.setModel(tbl);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_cbfilter3ActionPerformed

    private void oke_simpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_simpan1MouseClicked
        popup_berhasilEdit.setVisible(false);
    }//GEN-LAST:event_oke_simpan1MouseClicked

    private void bg_popupsimpan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_popupsimpan1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_popupsimpan1MouseEntered

    private void popup_berhasilEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilEditMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popup_berhasilEditMouseEntered

    private void tidak_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tidak_hapusMouseClicked
        // TODO add your handling code here:
        popup_konfirmasiHapus.setVisible(false);
    }//GEN-LAST:event_tidak_hapusMouseClicked

    private void ya_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ya_hapusMouseClicked
//        // TODO add your handling code here:
//        try
//        {
//            int baris = tabel_pegawai.getSelectedRow();
//            String id_pegawai = tabel_pegawai.getModel().getValueAt(baris, 0).toString();
//            String resultSet = "DELETE FROM tb_pegawai where id_pegawai='" + id_pegawai + "'";
//
//            java.sql.Connection conn = (Connection) config.configDB();
//            java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
//            pst.execute();
//
//            tabel_;
//            popup_konfirmasiHapus.setVisible(false);
//            popup_berhasilHapus.setVisible(true);
//        } catch (Exception e)
//        {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }//GEN-LAST:event_ya_hapusMouseClicked

    private void popup_konfirmasiHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_konfirmasiHapusMouseClicked
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_popup_konfirmasiHapusMouseClicked

    private void popup_konfirmasiHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_konfirmasiHapusMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_popup_konfirmasiHapusMouseEntered

    private void oke_terhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_terhapusMouseClicked
        // TODO add your handling code here:
        popup_berhasilHapus.setVisible(false);
    }//GEN-LAST:event_oke_terhapusMouseClicked

    private void popup_berhasilHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilHapusMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popup_berhasilHapusMouseEntered

    private void oke_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_simpanMouseClicked
        // TODO add your handling code here:
        popup_berhasilsimpan.setVisible(false);
    }//GEN-LAST:event_oke_simpanMouseClicked

    private void bg_popupsimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_popupsimpanMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_bg_popupsimpanMouseEntered

    private void popup_berhasilsimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilsimpanMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popup_berhasilsimpanMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_stok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_stok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_stok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_stok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_stok().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout2;
    private javax.swing.JLabel bg_edit_barang1;
    private javax.swing.JLabel bg_edit_barang2;
    private javax.swing.JLabel bg_edit_barang3;
    private javax.swing.JLabel bg_hapus;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel bg_popupsimpan;
    private javax.swing.JLabel bg_popupsimpan1;
    private javax.swing.JLabel bg_stoksemua;
    private javax.swing.JLabel bg_stoksemua1;
    private javax.swing.JLabel bg_stoksemua2;
    private javax.swing.JLabel bg_stoksemua3;
    private javax.swing.JLabel bg_terhapus;
    private javax.swing.JLabel btn_cari;
    private javax.swing.JLabel btn_edit_data;
    private javax.swing.JLabel btn_oke_detail;
    private javax.swing.JLabel btn_tambah_data;
    private javax.swing.JLabel btn_upload;
    private javax.swing.JLabel btn_upload1;
    private swing.comboBox.Combobox cbfilter;
    private swing.comboBox.Combobox cbfilter1;
    private swing.comboBox.Combobox cbfilter2;
    private swing.comboBox.Combobox cbfilter3;
    private javax.swing.JLabel close_inputstok;
    private javax.swing.JLabel close_inputstok1;
    private javax.swing.JComboBox<String> cmb_jenis_hewan;
    private javax.swing.JComboBox<String> cmb_jenis_hewan1;
    private javax.swing.JComboBox<String> cmb_kategori;
    private javax.swing.JComboBox<String> cmb_kategori1;
    private javax.swing.JLabel detail_gambar;
    private javax.swing.JLabel detail_hargabeli;
    private javax.swing.JLabel detail_hargajual;
    private javax.swing.JLabel detail_id_barang;
    private javax.swing.JLabel detail_jenis_hewan;
    private javax.swing.JLabel detail_kategori;
    private javax.swing.JLabel detail_merk_barang;
    private javax.swing.JLabel detail_nama_barang;
    private javax.swing.JPanel detail_stok;
    private javax.swing.JLabel detail_stok_barang;
    private javax.swing.JPanel edit_stok;
    private javax.swing.JLabel img_editbarang;
    private javax.swing.JLabel img_inputbarang;
    private javax.swing.JPanel input_stok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel lbl_detail;
    private javax.swing.JLabel lbl_detail1;
    private javax.swing.JLabel lbl_detail2;
    private javax.swing.JLabel lbl_detail3;
    private javax.swing.JLabel lbl_edit1;
    private javax.swing.JLabel lbl_edit2;
    private javax.swing.JLabel lbl_edit3;
    private javax.swing.JLabel lbl_edit4;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formPegawai;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lbl_hapus;
    private javax.swing.JLabel lbl_hapus1;
    private javax.swing.JLabel lbl_hapus2;
    private javax.swing.JLabel lbl_hapus3;
    private javax.swing.JLabel lbl_semuaStok4;
    private javax.swing.JLabel lbl_stokAksesoris4;
    private javax.swing.JLabel lbl_stokMakanan4;
    private javax.swing.JLabel lbl_stokPerlengkapan4;
    private javax.swing.JLabel lbl_tambah;
    private javax.swing.JLabel oke_simpan;
    private javax.swing.JLabel oke_simpan1;
    private javax.swing.JLabel oke_terhapus;
    private javax.swing.JPanel panel_cari;
    private javax.swing.JPanel panel_stok_aksesoris;
    private javax.swing.JPanel panel_stok_makanan;
    private javax.swing.JPanel panel_stok_perlengkapan;
    private javax.swing.JPanel panel_stok_semua;
    private javax.swing.JPanel popUp_keluar2;
    private javax.swing.JPanel popup_berhasilEdit;
    private javax.swing.JPanel popup_berhasilHapus;
    private javax.swing.JPanel popup_berhasilsimpan;
    private javax.swing.JPanel popup_konfirmasiHapus;
    private panelRounded.RoundedPanel roundedPanel1;
    private panelRounded.RoundedPanel roundedPanel2;
    private panelRounded.RoundedPanel roundedPanel3;
    private panelRounded.RoundedPanel roundedPanel4;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom2;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom3;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private javax.swing.JScrollPane spTable2;
    private javax.swing.JScrollPane spTable3;
    private com.raven.swing.Table tabel_stok_aksesoris;
    private com.raven.swing.Table tabel_stok_makanan;
    private com.raven.swing.Table tabel_stok_perlengkapan;
    private com.raven.swing.Table tabel_stok_semua;
    private javax.swing.JTextField tf_editbarang_hargabeli;
    private javax.swing.JTextField tf_editbarang_hargajual;
    private javax.swing.JTextField tf_editbarang_idbarang;
    private javax.swing.JTextField tf_editbarang_merkbarang;
    private javax.swing.JTextField tf_editbarang_namabarang;
    private javax.swing.JTextField tf_editbarang_stokbarang;
    private javax.swing.JTextField tf_inputbarang_hargabeli;
    private javax.swing.JTextField tf_inputbarang_hargajual;
    private javax.swing.JTextField tf_inputbarang_idbarang;
    private javax.swing.JTextField tf_inputbarang_merkbarang;
    private javax.swing.JTextField tf_inputbarang_namabarang;
    private javax.swing.JTextField tf_inputbarang_stokbarang;
    private javax.swing.JLabel tidak_hapus;
    private javax.swing.JTextField txtf_cari;
    private javax.swing.JLabel ya_hapus;
    // End of variables declaration//GEN-END:variables
}
