/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siopet;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrator
 */
public class form_pegawai extends javax.swing.JFrame {
    String filename;
    String link_gambar;
    public form_pegawai() {
        initComponents();
        tabel_pegawai();
        clear();
        clear1();
        
        //TABLE PEGAWAI
        spTable.setVerticalScrollBar(scrollBarCustom1);
//        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        //Setting Panel
        Inputpegawai.setVisible(false);
        editpegawai.setVisible(false);
        popup_konfirmasiHapus.setVisible(false);
        popup_berhasilsimpan.setVisible(false);
        popup_berhasilHapus.setVisible(false);
        panel_cari.setVisible(false);
//        popup_validasiedit.setVisible(false);
        Detailpegawai.setVisible(false);
        popup_berhasilEdit.setVisible(false);
        popUp_keluar2.setVisible(false);

        Inputpegawai.setBackground(new Color(0, 0, 0, 200));
        editpegawai.setBackground(new Color(0, 0, 0, 200));
        popup_konfirmasiHapus.setBackground(new Color(0, 0, 0, 200));
        popup_berhasilsimpan.setBackground(new Color(0, 0, 0, 200));
        popup_berhasilHapus.setBackground(new Color(0, 0, 0, 200));
        panel_cari.setBackground(new Color(0, 0, 0, 1));
//        popup_validasiedit.setBackground(new Color (0,0,0,200));
        Detailpegawai.setBackground(new Color(0,0,0,200));

        //Setting Komponen
        txt_id.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_nama.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_ttl.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_no.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_alamat.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_pass.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_pin.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_cari.setBackground(new java.awt.Color(0, 0, 0, 1));

        txt_id1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_nama1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_ttl1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_no1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_alamat1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_pass1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_pin1.setBackground(new java.awt.Color(0, 0, 0, 1));

    }

    private void detailPegawai(){
        int i = tabel_pegawai.getSelectedRow();
        TableModel tbl = tabel_pegawai.getModel();
             
        String id = tbl.getValueAt(i, 0).toString();
        String nama = tbl.getValueAt(i, 1).toString();
//        String jenis_kelamin = tbl.getValueAt(i, 2).toString();
        String jabatan = tbl.getValueAt(i, 3).toString();
        String nomer_hp = tbl.getValueAt(i, 4).toString();
        String alamat = tbl.getValueAt(i, 5).toString();
        String tanggal = tbl.getValueAt(i, 6).toString();
        String pin = tbl.getValueAt(i, 7).toString();
//        String pass = tbl.getValueAt(i, 8).toString();
       
        //id
        if (id == null) {
            lbl_id.setText("");
        } else{
            lbl_id.setText(id);
        }
        //nama
        if (nama == null) {
            lbl_nama.setText("");
        } else{
            lbl_nama.setText(nama);
        }
        //jabatan
        if (jabatan == null) {
            lbl_jabatan.setText("");
        } else{
            lbl_jabatan.setText(jabatan);
        }
        // ukuran
        if (alamat==null) {
            lbl_alamat.setText("");
        } else{
            lbl_alamat.setText(alamat);
        }
        // tanggal
        if (tanggal==null) {
            lbl_ttl.setText("");
        } else{
            lbl_ttl.setText(tanggal);
        }
        // pin
        if (pin==null) {
            lbl_pin.setText("");
        } else{
            lbl_pin.setText(pin);
        }
        // pass
        if (nomer_hp==null) {
            lbl_nohp.setText("");
        } else{
            lbl_nohp.setText(nomer_hp);
        }
  
        try{          
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("SELECT link_img FROM tb_pegawai where id_pegawai = '"+id+"'");

      
            if (result.next()){
                String query = result.getString("link_img");
                ImageIcon icon = new ImageIcon(query);
                Image image = icon.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon ic = new ImageIcon(image);
                foto.setIcon(ic);
            } else {
               JOptionPane.showMessageDialog(null, "Gagal menampilkan gambar");
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan (catch)"+ex);
        }
    }
    
    private void tabel_pegawai() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pegawai");
        tbl.addColumn("Nama Pegawai");
        tbl.addColumn("Jenis Kelamin");
        tbl.addColumn("Jabatan");
        tbl.addColumn("No HP Pegawai");
        tbl.addColumn("Alamat Pegawai");

        try
        {
            String sql = "SELECT * FROM tb_pegawai";
            java.sql.Connection con = (Connection) config.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while (rs.next())
            {
                tbl.addRow(new Object[]
                {
                    rs.getString("id_pegawai"),
                    rs.getString("nama_pegawai"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("jabatan"),
                    rs.getString("nomor_hp_pegawai"),
                    rs.getString("alamat_pegawai"),

                });
                tabel_pegawai.setModel(tbl);
            }
//        tabel_pegawai.setModel(tbl);
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void clear() {
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_alamat.setText(null);
        txt_no.setText(null);
        txt_pass.setText(null);
        txt_pin.setText(null);
        cmb_jabatan.setSelectedIndex(0);
        cmb_jenis.setSelectedIndex(0);
        tanggal_masuk1.setDate(null);
        txt_ttl.setText(null);
    }

    private void clear1() {
        txt_id1.setText(null);
        txt_nama1.setText(null);
        txt_alamat1.setText(null);
        txt_no1.setText(null);
        txt_pass1.setText(null);
        txt_pin1.setText(null);
        cmb_jabatan1.setSelectedIndex(0);
        cmb_jenis1.setSelectedIndex(0);
        tanggal_masuk1.setDate(null);
        txt_ttl1.setText(null);
    }

    private void cari_data() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pegawai");
        tbl.addColumn("Nama Pegawai");
        tbl.addColumn("Jenis Kelamin");
        tbl.addColumn("Jabatan");
        tbl.addColumn("No HP Pegawai");
        tbl.addColumn("Alamat Pegawai");
        tbl.addColumn("Tanggal Masuk");
        tbl.addColumn("PIN");
        tbl.addColumn("TTL");
        tbl.addColumn("Password");

        tabel_pegawai.setModel(tbl);

        String cari = txt_cari.getText();

        try
        {
            String sql = ("SELECT * FROM tb_pegawai where nama_pegawai like'%" + cari + "%'");
            Connection con = (Connection) config.configDB();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next())
            {
                tbl.addRow(new Object[]
                {
                    rs.getString("id_pegawai"),
                    rs.getString("nama_pegawai"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("jabatan"),
                    rs.getString("nomor_hp_pegawai"),
                    rs.getString("alamat_pegawai"),
                    rs.getDate("tanggal_masuk"),
                    rs.getInt("pin"),
                    rs.getString("TTL"),
                    rs.getString("pass")
                });

            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
//    private void filter(String selectedFilter){
//        DefaultTableModel tbl = (DefaultTableModel).getModel();
//        TableRowSorter sorter = new TableRowSorter(tbl);
//        tabel_pegawai.setRowSorter(sorter);
//        if (selectedFilter.equals("Semua")){
//            sorter.setRowFilter(null);
//        }else{
//            RowFilter rowFilter = RowFilter.regexFilter(selectedFilter, 3);
//            sorter.setRowFilter(rowFilter);
//        }
//    }
    
//    private void filter2(String selectedFilter){
//        JComboBox cmb = new JComboBox();
//        cmb.addItem("Semua");
//        cmb.addItem("Dokter");
//        cmb.addItem("Kasir");
//        cmb.addItem("Satpam");
//        
//        cmb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               String selectedFilter = (String)cmb.getSelectedItem();
//               filter(selectedFilter);
//            }
//        });
//}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editpegawai = new javax.swing.JPanel();
        btn_upload_edit = new javax.swing.JLabel();
        edit_foto = new javax.swing.JLabel();
        cmb_jabatan1 = new javax.swing.JComboBox<>();
        cmb_jenis1 = new javax.swing.JComboBox<>();
        txt_pass1 = new javax.swing.JTextField();
        txt_no1 = new javax.swing.JTextField();
        txt_pin1 = new javax.swing.JTextField();
        txt_alamat1 = new javax.swing.JTextField();
        txt_ttl1 = new javax.swing.JTextField();
        txt_nama1 = new javax.swing.JTextField();
        txt_id1 = new javax.swing.JTextField();
        txt_rfid1 = new javax.swing.JTextField();
        tanggal_masuk2 = new com.toedter.calendar.JDateChooser();
        btn_tambahedit = new javax.swing.JLabel();
        exitedit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Inputpegawai = new javax.swing.JPanel();
        btn_upload = new javax.swing.JLabel();
        input_foto = new javax.swing.JLabel();
        cmb_jabatan = new javax.swing.JComboBox<>();
        cmb_jenis = new javax.swing.JComboBox<>();
        txt_pass = new javax.swing.JTextField();
        txt_pin = new javax.swing.JTextField();
        txt_no = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_ttl = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        tanggal_masuk1 = new com.toedter.calendar.JDateChooser();
        txt_id = new javax.swing.JTextField();
        txt_rfid = new javax.swing.JTextField();
        exit = new javax.swing.JLabel();
        tambahinput = new javax.swing.JLabel();
        bginput = new javax.swing.JLabel();
        popUp_keluar2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bg_keluar2 = new javax.swing.JLabel();
        Detailpegawai = new javax.swing.JPanel();
        btn_oke_pegawai = new javax.swing.JLabel();
        lbl_password = new javax.swing.JLabel();
        lbl_jenis_kelamin = new javax.swing.JLabel();
        lbl_tanggal_masuk = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        lbl_pin = new javax.swing.JLabel();
        lbl_ttl = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        lbl_nohp = new javax.swing.JLabel();
        lbl_jabatan = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        btn_okedetail = new javax.swing.JLabel();
        bgdetaipegawai = new javax.swing.JLabel();
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
        txt_cari = new javax.swing.JTextField();
        bg_cari = new javax.swing.JLabel();
        cmb_filter = new javax.swing.JComboBox<>();
        roundedPanel1 = new panelRounded.RoundedPanel();
        spTable = new javax.swing.JScrollPane();
        tabel_pegawai = new com.raven.swing.Table();
        scrollBarCustom1 = new scrollbar.custom.ScrollBarCustom();
        btn_cari = new javax.swing.JLabel();
        btm_detail = new javax.swing.JLabel();
        btm_hapus = new javax.swing.JLabel();
        btm_edit = new javax.swing.JLabel();
        btm_tambah = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
        lbl_formPegawai = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        Logout2 = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pegawai1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editpegawai.setMaximumSize(new java.awt.Dimension(1366, 768));
        editpegawai.setMinimumSize(new java.awt.Dimension(1366, 768));
        editpegawai.setPreferredSize(new java.awt.Dimension(1366, 768));
        editpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editpegawaiMouseEntered(evt);
            }
        });
        editpegawai.setLayout(null);
        editpegawai.add(btn_upload_edit);
        btn_upload_edit.setBounds(640, 620, 160, 30);
        editpegawai.add(edit_foto);
        edit_foto.setBounds(610, 320, 220, 290);

        cmb_jabatan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----PILIH JABATAN-----", "Admin", "Owner", "Dokter Hewan", "Staff" }));
        editpegawai.add(cmb_jabatan1);
        cmb_jabatan1.setBounds(1260, 510, 300, 40);

        cmb_jenis1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------JENIS KELAMIN-----", "Laki-Laki", "Perempuan" }));
        editpegawai.add(cmb_jenis1);
        cmb_jenis1.setBounds(1270, 420, 300, 40);

        txt_pass1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_pass1.setBorder(null);
        editpegawai.add(txt_pass1);
        txt_pass1.setBounds(1270, 700, 280, 30);

        txt_no1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_no1.setBorder(null);
        editpegawai.add(txt_no1);
        txt_no1.setBounds(940, 700, 260, 30);

        txt_pin1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_pin1.setBorder(null);
        editpegawai.add(txt_pin1);
        txt_pin1.setBounds(1270, 610, 270, 30);

        txt_alamat1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txt_alamat1.setBorder(null);
        txt_alamat1.setVerifyInputWhenFocusTarget(false);
        editpegawai.add(txt_alamat1);
        txt_alamat1.setBounds(940, 520, 260, 30);

        txt_ttl1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_ttl1.setBorder(null);
        editpegawai.add(txt_ttl1);
        txt_ttl1.setBounds(940, 430, 260, 30);

        txt_nama1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_nama1.setBorder(null);
        editpegawai.add(txt_nama1);
        txt_nama1.setBounds(1270, 330, 270, 30);

        txt_id1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 16)); // NOI18N
        txt_id1.setBorder(null);
        editpegawai.add(txt_id1);
        txt_id1.setBounds(940, 330, 260, 30);

        txt_rfid1.setBackground(new Color(0,0,0,0));
        txt_rfid1.setBorder(null);
        txt_rfid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rfid1ActionPerformed(evt);
            }
        });
        editpegawai.add(txt_rfid1);
        txt_rfid1.setBounds(1110, 790, 280, 40);
        editpegawai.add(tanggal_masuk2);
        tanggal_masuk2.setBounds(930, 610, 310, 30);

        btn_tambahedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambaheditMouseClicked(evt);
            }
        });
        editpegawai.add(btn_tambahedit);
        btn_tambahedit.setBounds(990, 860, 160, 40);

        exitedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exiteditMouseClicked(evt);
            }
        });
        editpegawai.add(exitedit);
        exitedit.setBounds(1570, 230, 50, 50);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Pegawai.png"))); // NOI18N
        editpegawai.add(jLabel2);
        jLabel2.setBounds(500, 200, 1140, 750);

        getContentPane().add(editpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        Inputpegawai.setMaximumSize(new java.awt.Dimension(1366, 768));
        Inputpegawai.setMinimumSize(new java.awt.Dimension(1366, 768));
        Inputpegawai.setPreferredSize(new java.awt.Dimension(1366, 768));
        Inputpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InputpegawaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InputpegawaiMouseEntered(evt);
            }
        });
        Inputpegawai.setLayout(null);

        btn_upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_uploadMouseClicked(evt);
            }
        });
        Inputpegawai.add(btn_upload);
        btn_upload.setBounds(640, 580, 150, 30);
        Inputpegawai.add(input_foto);
        input_foto.setBounds(600, 280, 220, 290);

        cmb_jabatan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        cmb_jabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----PILIH JABATAN-----", "Admin", "Owner", "Dokter Hewan", "Staff" }));
        Inputpegawai.add(cmb_jabatan);
        cmb_jabatan.setBounds(1260, 470, 310, 40);

        cmb_jenis.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        cmb_jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----PILIH JENIS KELAMIN-----", "Laki-Laki", "Perempuan" }));
        Inputpegawai.add(cmb_jenis);
        cmb_jenis.setBounds(1260, 380, 310, 40);

        txt_pass.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_pass.setBorder(null);
        txt_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passActionPerformed(evt);
            }
        });
        Inputpegawai.add(txt_pass);
        txt_pass.setBounds(1270, 660, 290, 30);

        txt_pin.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_pin.setBorder(null);
        txt_pin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pinActionPerformed(evt);
            }
        });
        Inputpegawai.add(txt_pin);
        txt_pin.setBounds(1270, 570, 290, 30);

        txt_no.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_no.setBorder(null);
        txt_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noActionPerformed(evt);
            }
        });
        Inputpegawai.add(txt_no);
        txt_no.setBounds(930, 660, 290, 30);

        txt_alamat.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_alamat.setBorder(null);
        Inputpegawai.add(txt_alamat);
        txt_alamat.setBounds(930, 470, 290, 30);

        txt_ttl.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_ttl.setBorder(null);
        Inputpegawai.add(txt_ttl);
        txt_ttl.setBounds(930, 380, 300, 30);

        txt_nama.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        Inputpegawai.add(txt_nama);
        txt_nama.setBounds(1270, 290, 290, 30);

        tanggal_masuk1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Inputpegawai.add(tanggal_masuk1);
        tanggal_masuk1.setBounds(920, 560, 320, 40);

        txt_id.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        txt_id.setBorder(null);
        Inputpegawai.add(txt_id);
        txt_id.setBounds(930, 290, 290, 30);

        txt_rfid.setBackground(new Color(0,0,0,0));
        txt_rfid.setFont(new java.awt.Font("Microsoft Tai Le", 0, 14)); // NOI18N
        txt_rfid.setBorder(null);
        Inputpegawai.add(txt_rfid);
        txt_rfid.setBounds(1110, 752, 280, 40);

        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        Inputpegawai.add(exit);
        exit.setBounds(1560, 170, 50, 50);

        tambahinput.setMaximumSize(new java.awt.Dimension(1366, 768));
        tambahinput.setMinimumSize(new java.awt.Dimension(1366, 768));
        tambahinput.setPreferredSize(new java.awt.Dimension(1366, 768));
        tambahinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahinputMouseClicked(evt);
            }
        });
        Inputpegawai.add(tambahinput);
        tambahinput.setBounds(980, 830, 170, 40);

        bginput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Pegawai.png"))); // NOI18N
        Inputpegawai.add(bginput);
        bginput.setBounds(500, 160, 1150, 750);

        getContentPane().add(Inputpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

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

        getContentPane().add(popUp_keluar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        Detailpegawai.setMaximumSize(new java.awt.Dimension(1366, 768));
        Detailpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DetailpegawaiMouseEntered(evt);
            }
        });
        Detailpegawai.setLayout(null);

        btn_oke_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke_pegawaiMouseClicked(evt);
            }
        });
        Detailpegawai.add(btn_oke_pegawai);
        btn_oke_pegawai.setBounds(890, 810, 160, 30);
        Detailpegawai.add(lbl_password);
        lbl_password.setBounds(1060, 720, 220, 30);

        lbl_jenis_kelamin.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_jenis_kelamin);
        lbl_jenis_kelamin.setBounds(1060, 450, 220, 30);

        lbl_tanggal_masuk.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_tanggal_masuk);
        lbl_tanggal_masuk.setBounds(1060, 630, 220, 30);
        Detailpegawai.add(foto);
        foto.setBounds(560, 360, 240, 310);

        lbl_pin.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_pin);
        lbl_pin.setBounds(1060, 680, 220, 30);

        lbl_ttl.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_ttl);
        lbl_ttl.setBounds(1060, 410, 220, 30);

        lbl_alamat.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_alamat);
        lbl_alamat.setBounds(1060, 590, 220, 30);

        lbl_nohp.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_nohp);
        lbl_nohp.setBounds(1060, 540, 220, 30);

        lbl_jabatan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_jabatan);
        lbl_jabatan.setBounds(1060, 500, 220, 30);

        lbl_id.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_id);
        lbl_id.setBounds(1060, 320, 220, 30);

        lbl_nama.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        Detailpegawai.add(lbl_nama);
        lbl_nama.setBounds(1060, 370, 220, 30);

        btn_okedetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_okedetailMouseClicked(evt);
            }
        });
        Detailpegawai.add(btn_okedetail);
        btn_okedetail.setBounds(870, 840, 200, 40);

        bgdetaipegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Detail Pegawai.png"))); // NOI18N
        Detailpegawai.add(bgdetaipegawai);
        bgdetaipegawai.setBounds(500, 140, 940, 740);

        getContentPane().add(Detailpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -1, 1920, 1080));

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

        getContentPane().add(popup_berhasilEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popup_konfirmasiHapus.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_konfirmasiHapus.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_konfirmasiHapus.setPreferredSize(new java.awt.Dimension(1366, 768));
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

        getContentPane().add(popup_konfirmasiHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popup_berhasilHapus.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilHapus.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilHapus.setPreferredSize(new java.awt.Dimension(1366, 768));
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

        getContentPane().add(popup_berhasilHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popup_berhasilsimpan.setMaximumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilsimpan.setMinimumSize(new java.awt.Dimension(1366, 768));
        popup_berhasilsimpan.setPreferredSize(new java.awt.Dimension(1366, 768));
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

        getContentPane().add(popup_berhasilsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        panel_cari.setForeground(new java.awt.Color(0,0,0,0));
        panel_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_cariMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_cariMouseEntered(evt);
            }
        });
        panel_cari.setLayout(null);

        txt_cari.setBorder(null);
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        panel_cari.add(txt_cari);
        txt_cari.setBounds(20, 20, 250, 30);

        bg_cari.setForeground(new java.awt.Color(0,0,0,0));
        bg_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btn_search_clicked.png"))); // NOI18N
        bg_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel_cari.add(bg_cari);
        bg_cari.setBounds(5, -10, 340, 80);

        getContentPane().add(panel_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 130, 340, 70));

        cmb_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Owner", "Admin Kasir", "Dokter Hewan", "Karyawan" }));
        cmb_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_filterActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 170, 40));

        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable.setBorder(null);

        tabel_pegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
        ));
        tabel_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pegawaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_pegawaiMouseEntered(evt);
            }
        });
        tabel_pegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabel_pegawaiKeyReleased(evt);
            }
        });
        spTable.setViewportView(tabel_pegawai);

        roundedPanel1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1410, 760));
        roundedPanel1.add(scrollBarCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 160, 10, 180));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 1450, 810));

        btn_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariMouseClicked(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 130, 60, 60));

        btm_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btm_detailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btm_detailMouseEntered(evt);
            }
        });
        getContentPane().add(btm_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 130, 60, 60));

        btm_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btm_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(btm_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 130, 60, 60));

        btm_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btm_editMouseClicked(evt);
            }
        });
        getContentPane().add(btm_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 130, 60, 60));

        btm_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btm_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(btm_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1820, 130, 60, 60));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 100, 30, 30));

        lbl_formStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 340, 40));

        lbl_formDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 340, 50));

        lbl_formLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLayananMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 50));

        lbl_formPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formPegawaiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 350, 50));

        lbl_formTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 340, 70));

        lbl_formLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLaporanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 340, 60));

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        getContentPane().add(Logout2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 970, 340, 60));

        BG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pegawai.png"))); // NOI18N
        BG.setAutoscrolls(true);
        BG.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 24)); // NOI18N
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1366, 768));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        tabel_pegawai1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tabel_pegawai1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama Pegawai", "Jenis Kelamin", "Jabatan", "No HP Pegawai", "Alamat Pegawai", "Tanggal Masuk", "PIN", "Password"
            }
        ));
        tabel_pegawai1.setRowHeight(30);
        tabel_pegawai1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabel_pegawai1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabel_pegawai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pegawai1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pegawai1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 1450, 780));

        setSize(new java.awt.Dimension(1920, 1042));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btm_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btm_tambahMouseClicked
        Inputpegawai.setVisible(true);
        tabel_pegawai();
//        Forminput fi = new Forminput();
//        fi.setSize(1366, 768);
//        this.add(fi,new JLayeredPane());
//        this.setComponentZOrder(fi,0);
//        this.revalidate(); //untuk mengubah tata letak sebuah panel yang telah ditambahkan
//        this.repaint(); //mengubah tata posisi gambar 
    }//GEN-LAST:event_btm_tambahMouseClicked


    private void tabel_pegawai1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabel_pegawai1AncestorAdded

    }//GEN-LAST:event_tabel_pegawai1AncestorAdded

    private void tabel_pegawai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pegawai1MouseClicked
//        // TODO add your handling code here:
//        DefaultTableModel dtm = (DefaultTableModel) tabel_pegawai.getModel();
//        int baris = tabel_pegawai.rowAtPoint(evt.getPoint());
//        String id_pegawai = tabel_pegawai.getValueAt(baris, 0).toString();
//        txt_id1.setText(id_pegawai);
//        String nama_pegawai = tabel_pegawai.getValueAt(baris, 1).toString();
//        txt_nama1.setText(nama_pegawai);
//        String jenis_kelamin = tabel_pegawai.getValueAt(baris, 2).toString();
//        cmb_jenis1.setSelectedItem(jenis_kelamin);
//        String jabatan = tabel_pegawai.getValueAt(baris, 3).toString();
//        cmb_jabatan1.setSelectedItem(jabatan);
//        String no_hp = tabel_pegawai.getValueAt(baris, 4).toString();
//        txt_no1.setText(no_hp);
//        String alamat = tabel_pegawai.getValueAt(baris, 5).toString();
//        txt_alamat1.setText(alamat);
//
//        try
//        {
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tabel_pegawai.getValueAt(baris, 6).toString());
//            tanggal_masuk1.setDate(date);
//        } catch (ParseException ex)
//        {
//            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
//            System.out.println(ex.getMessage());
//        }
//
//        String pin = tabel_pegawai.getValueAt(baris, 7).toString();
//        txt_pin1.setText(pin);
//        String ttl = tabel_pegawai.getValueAt(baris, 8).toString();
//        txt_ttl1.setText(ttl);
//        String pass = tabel_pegawai.getValueAt(baris, 9).toString();
//        txt_pass1.setText(pass);

    }//GEN-LAST:event_tabel_pegawai1MouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        Inputpegawai.setVisible(false);
    }//GEN-LAST:event_exitMouseClicked

    private void tambahinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahinputMouseClicked
        // TODO add your handling code here:
        java.util.Date date = tanggal_masuk1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalmasuk = sdf.format(date);

        try
        {
            String sql = "INSERT INTO tb_pegawai(id_pegawai, nama_pegawai, jenis_kelamin, jabatan, nomor_hp_pegawai, alamat_pegawai, tanggal_masuk, pin, TTL, pass, rfid, link_img) VALUES ("
                    + "'" + txt_id.getText() + "', '"
                    + txt_nama.getText() + "', '"
                    + cmb_jenis.getSelectedItem() + "', '"
                    + cmb_jabatan.getSelectedItem() + "', '"
                    + txt_no.getText() + "', '"
                    + txt_alamat.getText() + "', '"
                    + tanggalmasuk + "', "
                    + txt_pin.getText() + ", '"
                    + txt_ttl.getText() + "', '"
                    + txt_pass.getText() + "', '"
                    + txt_rfid.getText() + "', '"
                    + link_gambar + "')"; 

            Connection con = (Connection) config.configDB();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            Inputpegawai.setVisible(false);
            popup_berhasilsimpan.setVisible(true);
            link_gambar = null;
        
        popup_berhasilsimpan.setVisible(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data gagal Disimpan" + e);
        }
        tabel_pegawai();
        clear();
    }//GEN-LAST:event_tambahinputMouseClicked

    private void btm_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btm_editMouseClicked
        
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int i = tabel_pegawai.getSelectedRow();
        TableModel tbl = tabel_pegawai.getModel();
             
        String id = tbl.getValueAt(i, 0).toString();
        try{
            
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select * from tb_pegawai where id_pegawai = '"+id+"'");
            
            DefaultTableModel model = (DefaultTableModel) tabel_pegawai.getModel();
            //reset
            model.setRowCount(0);
            while (result.next()) {
                String id_pegawai = result.getString("id_pegawai");
                String nama = result.getString("nama_pegawai");
                String jenis = result.getString("jenis_kelamin");
                String jabatan = result.getString("jabatan");
                String nomor = result.getString("nomor_hp_pegawai");
                String alamat = result.getString("alamat_pegawai");
                String tanggal_masuk = result.getString("tanggal_masuk");
                String pin = result.getString("pin");
                String ttl = result.getString("TTL");
                String pass = result.getString("pass");
                String rfid = result.getString("rfid");
                    
                txt_id1.setText(id_pegawai);
                txt_nama1.setText(nama);
                //jenis kelamin
                if (jenis.equals("Laki-Laki")) {
                    cmb_jenis1.setSelectedIndex(1);
                }else if(jenis.equals("Perempuan")){
                    cmb_jenis1.setSelectedIndex(2);
                }else{
                    JOptionPane.showMessageDialog(null, "Error cmb jenis kelamin");
                }
                //jabatan
                if (jabatan.equals("Owner")) {
                    cmb_jabatan1.setSelectedIndex(1);
                }else if(jabatan.equals("Admin")){
                    cmb_jabatan1.setSelectedIndex(2);
                }else if(jabatan.equals("Dokter Hewan")){
                    cmb_jabatan1.setSelectedIndex(3);
                }else if(jabatan.equals("Staff")){
                    cmb_jabatan1.setSelectedIndex(4);
                }else{
                      JOptionPane.showMessageDialog(null, "Error cmb jabatan");  
                }
                txt_no1.setText(nomor);
                txt_alamat1.setText(alamat);
                try {
                    Date date = dateFormat.parse(tanggal_masuk);
                    tanggal_masuk2.setDate(date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Gagal mengurai tanggal: " + ex.getMessage());
                }
                txt_pin1.setText(pin);
                txt_ttl1.setText(ttl);
                txt_pass1.setText(pass);
                txt_rfid1.setText(rfid);
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
       
  
        try{          
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("SELECT link_img FROM tb_pegawai where id_pegawai = '"+id+"'");

      
            if (result.next()){
                String query = result.getString("link_img");
                ImageIcon icon = new ImageIcon(query);
                Image image = icon.getImage().getScaledInstance(edit_foto.getWidth(), edit_foto.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon ic = new ImageIcon(image);
                edit_foto.setIcon(ic);
            } else {
               JOptionPane.showMessageDialog(null, "Gagal menampilkan gambar");
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan (catch)"+ex);
        }
        editpegawai.setVisible(true);
        tabel_pegawai();
    }//GEN-LAST:event_btm_editMouseClicked

    private void exiteditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiteditMouseClicked
        // TODO add your handling code here:
        editpegawai.setVisible(false);
    }//GEN-LAST:event_exiteditMouseClicked

    private void btn_tambaheditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambaheditMouseClicked
        // TODO add your handling code here:
        java.util.Date tgl = tanggal_masuk2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalmasuk = sdf.format(tgl);
        try
        {
            String sql
                    = "UPDATE tb_pegawai SET "
                    + "nama_pegawai ='" + txt_nama1.getText() + "',"
                    + "jenis_kelamin = '" + cmb_jenis1.getSelectedItem() + "',"
                    + "jabatan ='" + cmb_jabatan1.getSelectedItem() + "',"
                    + "nomor_hp_pegawai = '" + txt_no1.getText() + "',"
                    + "alamat_pegawai = '" + txt_alamat1.getText() + "',"
                    + "tanggal_masuk = '" + tanggalmasuk + "',"
                    + "pin = '" + txt_pin1.getText() + "',"
                    + "TTL = '" + txt_ttl1.getText() + "',"
                    + "pass = '" + txt_pass1.getText() + "',"
                    + "rfid = '"+txt_rfid1.getText()+"'"
                    + "WHERE tb_pegawai.id_pegawai = '" + txt_id1.getText() + "'";
            Connection con = (Connection) config.configDB();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
            editpegawai.setVisible(false);
        popup_berhasilEdit.setVisible(true);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e.getMessage());
        }
        tabel_pegawai();
        clear();
    }//GEN-LAST:event_btn_tambaheditMouseClicked

    private void btm_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btm_hapusMouseClicked
        // TODO add your handling code here:
       
            popup_konfirmasiHapus.setVisible(true);
            popup_berhasilHapus.setVisible(false);

    }//GEN-LAST:event_btm_hapusMouseClicked

    private void bg_popupsimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_popupsimpanMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_bg_popupsimpanMouseEntered

    private void oke_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_simpanMouseClicked
        // TODO add your handling code here:
        popup_berhasilsimpan.setVisible(false);
    }//GEN-LAST:event_oke_simpanMouseClicked

    private void tidak_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tidak_hapusMouseClicked
        // TODO add your handling code here:
        popup_konfirmasiHapus.setVisible(false);
    }//GEN-LAST:event_tidak_hapusMouseClicked

    private void popup_konfirmasiHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_konfirmasiHapusMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_popup_konfirmasiHapusMouseEntered

    private void popup_konfirmasiHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_konfirmasiHapusMouseClicked
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_popup_konfirmasiHapusMouseClicked

    private void oke_terhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_terhapusMouseClicked
        // TODO add your handling code here:
        popup_berhasilHapus.setVisible(false);
        jScrollPane1.setVisible(true);
    }//GEN-LAST:event_oke_terhapusMouseClicked

    private void ya_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ya_hapusMouseClicked
        // TODO add your handling code here:
        try
        {
            int baris = tabel_pegawai.getSelectedRow();
            String id_pegawai = tabel_pegawai.getModel().getValueAt(baris, 0).toString();
            String resultSet = "DELETE FROM tb_pegawai where id_pegawai='" + id_pegawai + "'";

            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
            pst.execute();
            
            tabel_pegawai();
            popup_konfirmasiHapus.setVisible(false);
            popup_berhasilHapus.setVisible(true);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ya_hapusMouseClicked

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void editpegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editpegawaiMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_editpegawaiMouseEntered

    private void popup_berhasilHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilHapusMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popup_berhasilHapusMouseEntered

    private void popup_berhasilsimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilsimpanMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popup_berhasilsimpanMouseEntered

    private void InputpegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InputpegawaiMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_InputpegawaiMouseEntered

    private void btn_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariMouseClicked
        // TODO add your handling code here:
        panel_cari.setVisible(true);
    }//GEN-LAST:event_btn_cariMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        cari_data();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void panel_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseClicked
        // TODO add your handling code here:
        panel_cari.setVisible(false);
    }//GEN-LAST:event_panel_cariMouseClicked

    private void panel_cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_cariMouseEntered

    private void InputpegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InputpegawaiMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_InputpegawaiMouseClicked

    private void btm_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btm_detailMouseClicked
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int i = tabel_pegawai.getSelectedRow();
        TableModel tbl = tabel_pegawai.getModel();
             
        String id = tbl.getValueAt(i, 0).toString();
        try{
            
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select * from tb_pegawai where id_pegawai = '"+id+"'");
            
            DefaultTableModel model = (DefaultTableModel) tabel_pegawai.getModel();
            //reset
            model.setRowCount(0);
            while (result.next()) {
                String id_pegawai = result.getString("id_pegawai");
                String nama = result.getString("nama_pegawai");
                String jenis = result.getString("jenis_kelamin");
                String jabatan = result.getString("jabatan");
                String nomor = result.getString("nomor_hp_pegawai");
                String alamat = result.getString("alamat_pegawai");
                String tanggal_masuk = result.getString("tanggal_masuk");
                String pin = result.getString("pin");
                String ttl = result.getString("TTL");
                String pass = result.getString("pass");
                    
                lbl_id.setText(id_pegawai);
                lbl_nama.setText(nama);
                lbl_jenis_kelamin.setText(jenis);
                lbl_jabatan.setText(jabatan);
                lbl_nohp.setText(nomor);
                lbl_alamat.setText(alamat);
                lbl_tanggal_masuk.setText(tanggal_masuk);
                lbl_pin.setText(pin);
                lbl_ttl.setText(ttl);
                lbl_password.setText(pass);
                
                
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
       
  
        try{          
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("SELECT link_img FROM tb_pegawai where id_pegawai = '"+id+"'");

      
            if (result.next()){
                String query = result.getString("link_img");
                ImageIcon icon = new ImageIcon(query);
                Image image = icon.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon ic = new ImageIcon(image);
                foto.setIcon(ic);
            } else {
               JOptionPane.showMessageDialog(null, "Gagal menampilkan gambar");
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan (catch)"+ex);
        }
        Detailpegawai.setVisible(true);
        tabel_pegawai();
        
    }//GEN-LAST:event_btm_detailMouseClicked

    private void btn_okedetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okedetailMouseClicked
        // TODO add your handling code here:
        Detailpegawai.setVisible(false);
    }//GEN-LAST:event_btn_okedetailMouseClicked

    private void btm_detailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btm_detailMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_btm_detailMouseEntered

    private void DetailpegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DetailpegawaiMouseEntered
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_DetailpegawaiMouseEntered

    private void btn_uploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_uploadMouseClicked
        try{ 
            JFileChooser chooser = new JFileChooser(); 
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.toString());
            Image image = icon.getImage().getScaledInstance(input_foto.getWidth(), input_foto.getHeight(), Image.SCALE_DEFAULT);
            ImageIcon ic = new ImageIcon(image);
            input_foto.setIcon(ic);
            
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

    private void txt_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noActionPerformed

    private void txt_pinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pinActionPerformed

    private void txt_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passActionPerformed

    private void oke_simpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oke_simpan1MouseClicked
        popup_berhasilEdit.setVisible(false);
    }//GEN-LAST:event_oke_simpan1MouseClicked

    private void bg_popupsimpan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_popupsimpan1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_popupsimpan1MouseEntered

    private void popup_berhasilEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popup_berhasilEditMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popup_berhasilEditMouseEntered

    private void btn_oke_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke_pegawaiMouseClicked
        Detailpegawai.setVisible(false);
    }//GEN-LAST:event_btn_oke_pegawaiMouseClicked

    private void tabel_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pegawaiMouseClicked

    }//GEN-LAST:event_tabel_pegawaiMouseClicked

    private void tabel_pegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pegawaiMouseEntered

    }//GEN-LAST:event_tabel_pegawaiMouseEntered

    private void tabel_pegawaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_pegawaiKeyReleased

    }//GEN-LAST:event_tabel_pegawaiKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_pegawai.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void lbl_formStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseClicked
        this.setVisible(false);
        new form_stok().setVisible(true);
    }//GEN-LAST:event_lbl_formStokMouseClicked

    private void lbl_formDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboardMouseClicked
        this.setVisible(false);
        new form_dashboard().setVisible(true);
    }//GEN-LAST:event_lbl_formDashboardMouseClicked

    private void lbl_formLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLayananMouseClicked
        this.setVisible(false);
        new form_layanan().setVisible(true);
    }//GEN-LAST:event_lbl_formLayananMouseClicked

    private void lbl_formPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formPegawaiMouseClicked
        this.setVisible(false);
        new form_pegawai().setVisible(true);
    }//GEN-LAST:event_lbl_formPegawaiMouseClicked

    private void lbl_formTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formTransaksiMouseClicked
        this.setVisible(false);
        new form_transaksiBarang().setVisible(true);
    }//GEN-LAST:event_lbl_formTransaksiMouseClicked

    private void lbl_formLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLaporanMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsung().setVisible(true);
    }//GEN-LAST:event_lbl_formLaporanMouseClicked

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
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void cmb_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_filterActionPerformed
            try{

            int filter = cmb_filter.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();

            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM `tb_pegawai` GROUP BY id_pegawai ORDER BY id_pegawai";
            }
            else if (filter == 1){
                msql = "SELECT * FROM `tb_pegawai` WHERE jabatan LIKE 'owner' GROUP BY id_pegawai";
            }
            else if (filter == 2){
                msql = "SELECT * FROM `tb_pegawai` WHERE jabatan LIKE 'admin kasir' GROUP BY id_pegawai";
            }
            else if (filter == 3){
                msql = "SELECT * FROM `tb_pegawai` WHERE jabatan LIKE 'dokter hewan' GROUP BY id_pegawai";
            }
            else if (filter == 4){
                msql = "SELECT * FROM `tb_pegawai` WHERE jabatan LIKE 'staff' GROUP BY id_pegawai";
            }
            else{
                msql = "SELECT * FROM tb_barang GROUP BY id_barang ORDER BY id_barang ASC";

            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Pegawai");
            tbl.addColumn("Nama Pegawai");
            tbl.addColumn("Jenis Kelamin");
            tbl.addColumn("Jabatan");
            tbl.addColumn("No HP Pegawai");
            tbl.addColumn("Alamat Pegawai");

            tabel_pegawai.setModel(tbl);

            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_pegawai"),
                    result.getString("nama_pegawai"),
                    result.getString("jenis_kelamin"),
                    result.getString("jabatan"),
                    result.getString("nomor_hp_pegawai"),
                    result.getString("alamat_pegawai"),
                });
                tabel_pegawai.setModel(tbl);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_cmb_filterActionPerformed

    private void txt_rfid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rfid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rfid1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(form_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(form_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(form_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(form_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_pegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JPanel Detailpegawai;
    private javax.swing.JPanel Inputpegawai;
    private javax.swing.JLabel Logout2;
    private javax.swing.JLabel bg_cari;
    private javax.swing.JLabel bg_hapus;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel bg_popupsimpan;
    private javax.swing.JLabel bg_popupsimpan1;
    private javax.swing.JLabel bg_terhapus;
    private javax.swing.JLabel bgdetaipegawai;
    private javax.swing.JLabel bginput;
    private javax.swing.JLabel btm_detail;
    private javax.swing.JLabel btm_edit;
    private javax.swing.JLabel btm_hapus;
    private javax.swing.JLabel btm_tambah;
    private javax.swing.JLabel btn_cari;
    private javax.swing.JLabel btn_oke_pegawai;
    private javax.swing.JLabel btn_okedetail;
    private javax.swing.JLabel btn_tambahedit;
    private javax.swing.JLabel btn_upload;
    private javax.swing.JLabel btn_upload_edit;
    private javax.swing.JComboBox<String> cmb_filter;
    private javax.swing.JComboBox<String> cmb_jabatan;
    private javax.swing.JComboBox<String> cmb_jabatan1;
    private javax.swing.JComboBox<String> cmb_jenis;
    private javax.swing.JComboBox<String> cmb_jenis1;
    private javax.swing.JLabel edit_foto;
    private javax.swing.JPanel editpegawai;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel exitedit;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel input_foto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formPegawai;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_jabatan;
    private javax.swing.JLabel lbl_jenis_kelamin;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_nohp;
    private javax.swing.JLabel lbl_password;
    private javax.swing.JLabel lbl_pin;
    private javax.swing.JLabel lbl_tanggal_masuk;
    private javax.swing.JLabel lbl_ttl;
    private javax.swing.JLabel oke_simpan;
    private javax.swing.JLabel oke_simpan1;
    private javax.swing.JLabel oke_terhapus;
    private javax.swing.JPanel panel_cari;
    private javax.swing.JPanel popUp_keluar2;
    private javax.swing.JPanel popup_berhasilEdit;
    private javax.swing.JPanel popup_berhasilHapus;
    private javax.swing.JPanel popup_berhasilsimpan;
    private javax.swing.JPanel popup_konfirmasiHapus;
    private panelRounded.RoundedPanel roundedPanel1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table tabel_pegawai;
    private javax.swing.JTable tabel_pegawai1;
    private javax.swing.JLabel tambahinput;
    private com.toedter.calendar.JDateChooser tanggal_masuk1;
    private com.toedter.calendar.JDateChooser tanggal_masuk2;
    private javax.swing.JLabel tidak_hapus;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_alamat1;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_id1;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nama1;
    private javax.swing.JTextField txt_no;
    private javax.swing.JTextField txt_no1;
    private javax.swing.JTextField txt_pass;
    private javax.swing.JTextField txt_pass1;
    private javax.swing.JTextField txt_pin;
    private javax.swing.JTextField txt_pin1;
    private javax.swing.JTextField txt_rfid;
    private javax.swing.JTextField txt_rfid1;
    private javax.swing.JTextField txt_ttl;
    private javax.swing.JTextField txt_ttl1;
    private javax.swing.JLabel ya_hapus;
    // End of variables declaration//GEN-END:variables

}
