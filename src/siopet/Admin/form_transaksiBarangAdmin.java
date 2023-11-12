
package siopet.Admin;

import siopet.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class form_transaksiBarangAdmin extends javax.swing.JFrame {

    public boolean klikEdit;
    private int harga;
    private int kembalian;
    private static final String character = "0123456789";
    private static final int codeLenght = 7;
    
    public form_transaksiBarangAdmin() {
        initComponents();
        datatable1();
        datatabel2();
        cmbData_pegawai();
        popUp_keluar2.setVisible(false);
        txtf_idTransaksi.setEnabled(false);
        txtf_idTransaksi.setText("TRB" +randomCode());
//        datatabel3();
        
        //TABLE KERANJANG
        spTable.setVerticalScrollBar(scrollBarCustom1);
//        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        //TABLE BARANG
        spTable_barang.setVerticalScrollBar(scrollBarCustom2);
        spTable_barang.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable_barang.getViewport().setBackground(Color.WHITE);
        
        //TABLE PELANGGAN
        spTable_pelanggan.setVerticalScrollBar(scrollBarCustom3);
        spTable_pelanggan.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable_pelanggan.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        
        //SETTING TEXTIELD
        
        
        //==========SETTING PANEL==========//
        panel_dataBarang.setVisible(false);
        panel_dataPelanggan.setVisible(false);
        panel_tambahPelanggan.setVisible(false);
        panel_editPelanggan.setVisible(false);
        popUp_berhasilDisimpan.setVisible(false);
        popUp_berhasilEdit.setVisible(false);
        popUp_Konfirmasihapus.setVisible(false);
        popUp_berhasilHapus.setVisible(false);
        popUp_berhasilmskKeranjang.setVisible(false);
        popUp_stokKurang.setVisible(false);
        popUp_hpsDataKeranjang.setVisible(false);
        popUp_trankberhasilDisimpan.setVisible(false);
        
        panel_dataBarang.setBackground(new Color(0,0,0,200));
        panel_dataPelanggan.setBackground(new Color(0,0,0,200)); 
    }

    public static String randomCode(){
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < codeLenght; i++){
            int index = random.nextInt(character.length());
            char randomChar = character.charAt(index);
            code.append(randomChar);
        }
        return code.toString();
    }
    
    //FORMAT DECIMAL
    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }
    
     //UNTUK MENGHILANGKAN TITIK 
     public static String reFormat(String val){
         return val.replaceAll("\\.", "");
     }
     
    public void datatabel2(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID BARANG");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Kategori");
        dtm.addColumn("Merk");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        dtm.addColumn("Harga Beli");
        tbl_dataBarang.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `tb_barang`");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_hewan"),
                    rs.getString("kategori"),
                    rs.getString("merk"),
                    rs.getString("stok"),
                    rs.getString("harga_jual"),
                    rs.getString("harga_beli")
                });
                tbl_dataBarang.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    
    public void datatable1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Pelanggan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Alamat");
        dtm.addColumn("No HP");
        tbl_dataPelanggan.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_pelanggan");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("alamat_pelanggan"),
                    rs.getString("nomor_hp")
                });
                tbl_dataPelanggan.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariDataBarang(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID BARANG");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Jenis Hewan");
        dtm.addColumn("Kategori");
        dtm.addColumn("Merk");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga Jual");
        dtm.addColumn("Harga Beli");
        
        
        tbl_dataBarang.setModel(dtm);
        
        String cari = txtf_cari.getText();
        
        try{
            String sql = "select id_barang, nama_barang, jenis_hewan, kategori, merk, stok, harga_jual, harga_beli from tb_barang where nama_barang like'%"+cari+"%'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                dtm.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("jenis_hewan"),
                    rs.getString("kategori"),
                    rs.getString("merk"),
                    rs.getString("stok"),
                    rs.getString("harga_jual"),
                    rs.getString("harga_beli")
                  
                    
                });
                tbl_dataBarang.setModel(dtm);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void cariDataPelanggan(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID Pelanggan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Alamat");
        dtm.addColumn("No HP");
        tbl_dataPelanggan.setModel(dtm);
        
        String cari = txtf_cari1.getText();
        
        try{
            String sql = "select id_pelanggan, nama_pelanggan, alamat_pelanggan, nomor_hp from tb_pelanggan where nama_pelanggan like'%"+cari+"%'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                dtm.addRow(new Object[]{
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("alamat_pelanggan"),
                    rs.getString("nomor_hp")
                });
                tbl_dataPelanggan.setModel(dtm);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void kasirBarang(){
        int rowCount = tbl_keranjang.getRowCount();
        int total = 0;
        for (int i = 0; i < rowCount; i++) {
            int value = Integer.parseInt(tbl_keranjang.getValueAt(i, 2).toString());
            int value1 = Integer.parseInt(tbl_keranjang.getValueAt(i, 3).toString());
            total += value * value1;
        }
        txtf_total.setText(String.valueOf(formatDec(total)));
    }
    
    public void cmbData_pegawai(){
        try {
            String sql = "SELECT id_pegawai FROM tb_pegawai";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery(sql);
            
            while(res.next()){
                cmb_namaPegawai.addItem(res.getString("id_pegawai"));
            }
            
            res.last();
            int jumlah_data = res.getRow();
            
            res.first();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void matchBarcodeWithData(String barcode) {
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/siopet", "root", "");

            // Mengambil data dari database berdasarkan barcode
            String query = "SELECT id_barang, nama_barang, harga_jual FROM tb_barang WHERE id_barang = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, barcode);
            ResultSet resultSet = statement.executeQuery();
            
            // Menambahkan data ke dalam JTable
            while (resultSet.next()) {
                String data1 = resultSet.getString("id_barang");
                String data2 = resultSet.getString("nama_barang");
                String data3 = resultSet.getString("harga_jual");
                String data4 = "1";
                int data5 = Integer.parseInt(data3) * Integer.parseInt(data4);
                tbl_keranjang.addRow(new Object[]{data1, data2, data3, data4, data5});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp_keluar2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bg_keluar2 = new javax.swing.JLabel();
        panel_dataBarang = new javax.swing.JPanel();
        txtf_cari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbl_close = new javax.swing.JLabel();
        txtf_jumlahBarang = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        spTable_barang = new javax.swing.JScrollPane();
        tbl_dataBarang = new com.raven.swing.Table();
        scrollBarCustom2 = new scrollbar.custom.ScrollBarCustom();
        bg_dataBarang = new javax.swing.JLabel();
        panel_dataPelanggan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        spTable_pelanggan = new javax.swing.JScrollPane();
        tbl_dataPelanggan = new com.raven.swing.Table();
        scrollBarCustom3 = new scrollbar.custom.ScrollBarCustom();
        txtf_cari1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbl_close1 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JLabel();
        btn_editPelanggan = new javax.swing.JLabel();
        btn_tambahPelanggan = new javax.swing.JLabel();
        txtf_idPelanggan = new javax.swing.JTextField();
        bg_dataBarang1 = new javax.swing.JLabel();
        popUp_berhasilDisimpan = new javax.swing.JPanel();
        lbl_oke = new javax.swing.JLabel();
        bg_inputbarang3 = new javax.swing.JLabel();
        popUp_trankberhasilDisimpan = new javax.swing.JPanel();
        lbl_oke5 = new javax.swing.JLabel();
        bg_inputbarang10 = new javax.swing.JLabel();
        popUp_hpsDataKeranjang = new javax.swing.JPanel();
        btn_hapus2 = new javax.swing.JLabel();
        btn_tidak1 = new javax.swing.JLabel();
        bg_inputbarang9 = new javax.swing.JLabel();
        popUp_stokKurang = new javax.swing.JPanel();
        lbl_oke4 = new javax.swing.JLabel();
        bg_inputbarang8 = new javax.swing.JLabel();
        popUp_berhasilmskKeranjang = new javax.swing.JPanel();
        lbl_oke3 = new javax.swing.JLabel();
        bg_inputbarang7 = new javax.swing.JLabel();
        popUp_Konfirmasihapus = new javax.swing.JPanel();
        btn_hapus1 = new javax.swing.JLabel();
        btn_tidak = new javax.swing.JLabel();
        bg_inputbarang4 = new javax.swing.JLabel();
        popUp_berhasilEdit = new javax.swing.JPanel();
        lbl_oke2 = new javax.swing.JLabel();
        bg_inputbarang5 = new javax.swing.JLabel();
        panel_editPelanggan = new javax.swing.JPanel();
        txtf_idPel1 = new javax.swing.JTextField();
        lbl_editPel1 = new javax.swing.JLabel();
        lbl_closee1 = new javax.swing.JLabel();
        txtf_namaPel1 = new javax.swing.JTextField();
        txtf_notelpPell1 = new javax.swing.JTextField();
        txtf_alamatPel1 = new javax.swing.JTextField();
        bg_inputPelnggn1 = new javax.swing.JLabel();
        panel_tambahPelanggan = new javax.swing.JPanel();
        txtf_idPel = new javax.swing.JTextField();
        lbl_tambahPel = new javax.swing.JLabel();
        lbl_closee = new javax.swing.JLabel();
        txtf_namaPel = new javax.swing.JTextField();
        txtf_notelpPell = new javax.swing.JTextField();
        txtf_alamatPel = new javax.swing.JTextField();
        bg_inputPelnggn = new javax.swing.JLabel();
        popUp_berhasilHapus = new javax.swing.JPanel();
        lbl_oke1 = new javax.swing.JLabel();
        bg_inputbarang6 = new javax.swing.JLabel();
        lbl_dataPelanggan = new javax.swing.JLabel();
        lbl_cariBarang = new javax.swing.JLabel();
        txtf_idTransaksi = new javax.swing.JTextField();
        cmb_namaPegawai = new javax.swing.JComboBox<>();
        txtf_idPelanggan1 = new javax.swing.JTextField();
        txtf_namaPelanggan = new javax.swing.JTextField();
        txtf_total = new javax.swing.JTextField();
        txtf_bayar = new javax.swing.JTextField();
        txtf_kembalian = new javax.swing.JTextField();
        roundedPanel1 = new panelRounded.RoundedPanel();
        spTable = new javax.swing.JScrollPane();
        tbl_keranjang = new com.raven.swing.Table();
        scrollBarCustom1 = new scrollbar.custom.ScrollBarCustom();
        lbl_tranksaksiBarang = new javax.swing.JLabel();
        lbl_trankPenitipan = new javax.swing.JLabel();
        lbl_trankGromming = new javax.swing.JLabel();
        lbl_batalBarang = new javax.swing.JLabel();
        lbl_scan = new javax.swing.JLabel();
        lbl_tambah = new javax.swing.JLabel();
        lbl_formDashboard1 = new javax.swing.JLabel();
        lbl_formStok1 = new javax.swing.JLabel();
        lbl_formLayanan1 = new javax.swing.JLabel();
        lbl_formTransaksi1 = new javax.swing.JLabel();
        lbl_formLaporan1 = new javax.swing.JLabel();
        Logout2 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        txtf_barcode = new javax.swing.JTextField();
        txtf_editjumlahBrng = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        panel_dataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_dataBarangMouseEntered(evt);
            }
        });
        panel_dataBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtf_cari.setBackground(new Color(0, 0, 0, 0));
        txtf_cari.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_cari.setBorder(null);
        txtf_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariKeyReleased(evt);
            }
        });
        panel_dataBarang.add(txtf_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 320, 40));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        panel_dataBarang.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 750, 150, 40));

        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
        });
        panel_dataBarang.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 230, 40, 40));

        txtf_jumlahBarang.setBackground(new Color(0, 0, 0, 0));
        txtf_jumlahBarang.setBorder(null);
        panel_dataBarang.add(txtf_jumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 700, 110, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable_barang.setBorder(null);

        tbl_dataBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_dataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dataBarangMouseClicked(evt);
            }
        });
        spTable_barang.setViewportView(tbl_dataBarang);

        jPanel1.add(spTable_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1240, 270));
        jPanel1.add(scrollBarCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 270, -1, 80));

        panel_dataBarang.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 1280, 310));
        panel_dataBarang.add(txtf_idBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 710, 120, -1));

        bg_dataBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Data Barang.png"))); // NOI18N
        panel_dataBarang.add(bg_dataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 1350, 600));

        getContentPane().add(panel_dataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        panel_dataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_dataPelangganMouseEntered(evt);
            }
        });
        panel_dataPelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable_pelanggan.setBorder(null);

        tbl_dataPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_dataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dataPelangganMouseClicked(evt);
            }
        });
        spTable_pelanggan.setViewportView(tbl_dataPelanggan);

        jPanel2.add(spTable_pelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1260, 260));
        jPanel2.add(scrollBarCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 430, 10, 70));

        panel_dataPelanggan.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 1300, 330));

        txtf_cari1.setBackground(new Color(0, 0, 0, 0));
        txtf_cari1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_cari1.setBorder(null);
        txtf_cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_cari1ActionPerformed(evt);
            }
        });
        txtf_cari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cari1KeyReleased(evt);
            }
        });
        panel_dataPelanggan.add(txtf_cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 350, 40));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panel_dataPelanggan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 760, 160, 40));

        lbl_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close1MouseClicked(evt);
            }
        });
        panel_dataPelanggan.add(lbl_close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 230, 40, 50));

        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
        });
        panel_dataPelanggan.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 330, 70, 50));

        btn_editPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editPelangganMouseClicked(evt);
            }
        });
        panel_dataPelanggan.add(btn_editPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 330, 70, 50));

        btn_tambahPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahPelangganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tambahPelangganMouseEntered(evt);
            }
        });
        panel_dataPelanggan.add(btn_tambahPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 330, 60, 50));

        txtf_idPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_idPelangganActionPerformed(evt);
            }
        });
        panel_dataPelanggan.add(txtf_idPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 750, 160, -1));

        bg_dataBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Data Pelanggan.png"))); // NOI18N
        panel_dataPelanggan.add(bg_dataBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 1350, 610));

        getContentPane().add(panel_dataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_berhasilDisimpan.setBackground(new Color(0, 0, 0, 200));
        popUp_berhasilDisimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_berhasilDisimpanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_berhasilDisimpanMouseEntered(evt);
            }
        });
        popUp_berhasilDisimpan.setLayout(null);

        lbl_oke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_okeMouseClicked(evt);
            }
        });
        popUp_berhasilDisimpan.add(lbl_oke);
        lbl_oke.setBounds(930, 610, 170, 40);

        bg_inputbarang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup data berhasil disimpan.png"))); // NOI18N
        bg_inputbarang3.setToolTipText("");
        bg_inputbarang3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_berhasilDisimpan.add(bg_inputbarang3);
        bg_inputbarang3.setBounds(680, 390, 680, 310);

        getContentPane().add(popUp_berhasilDisimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_trankberhasilDisimpan.setBackground(new Color(0, 0, 0, 200));
        popUp_trankberhasilDisimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_trankberhasilDisimpanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_trankberhasilDisimpanMouseEntered(evt);
            }
        });
        popUp_trankberhasilDisimpan.setLayout(null);

        lbl_oke5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_oke5MouseClicked(evt);
            }
        });
        popUp_trankberhasilDisimpan.add(lbl_oke5);
        lbl_oke5.setBounds(940, 610, 170, 40);

        bg_inputbarang10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup transaksi berhasil disimpan.png"))); // NOI18N
        bg_inputbarang10.setToolTipText("");
        bg_inputbarang10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_trankberhasilDisimpan.add(bg_inputbarang10);
        bg_inputbarang10.setBounds(680, 390, 680, 310);

        getContentPane().add(popUp_trankberhasilDisimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_hpsDataKeranjang.setBackground(new Color(0,0,0,200));
        popUp_hpsDataKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_hpsDataKeranjangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_hpsDataKeranjangMouseEntered(evt);
            }
        });
        popUp_hpsDataKeranjang.setLayout(null);

        btn_hapus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapus2MouseClicked(evt);
            }
        });
        popUp_hpsDataKeranjang.add(btn_hapus2);
        btn_hapus2.setBounds(1010, 620, 160, 40);

        btn_tidak1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tidak1MouseClicked(evt);
            }
        });
        popUp_hpsDataKeranjang.add(btn_tidak1);
        btn_tidak1.setBounds(800, 620, 170, 40);

        bg_inputbarang9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup hapus data keranjang.png"))); // NOI18N
        bg_inputbarang9.setToolTipText("");
        bg_inputbarang9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_hpsDataKeranjang.add(bg_inputbarang9);
        bg_inputbarang9.setBounds(650, 400, 680, 310);

        getContentPane().add(popUp_hpsDataKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_stokKurang.setBackground(new Color(0, 0, 0, 200));
        popUp_stokKurang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_stokKurangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_stokKurangMouseEntered(evt);
            }
        });
        popUp_stokKurang.setLayout(null);

        lbl_oke4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_oke4MouseClicked(evt);
            }
        });
        popUp_stokKurang.add(lbl_oke4);
        lbl_oke4.setBounds(940, 610, 170, 40);

        bg_inputbarang8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup stok barang tidak cukup.png"))); // NOI18N
        bg_inputbarang8.setToolTipText("");
        bg_inputbarang8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_stokKurang.add(bg_inputbarang8);
        bg_inputbarang8.setBounds(660, 390, 720, 310);

        getContentPane().add(popUp_stokKurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_berhasilmskKeranjang.setBackground(new Color(0, 0, 0, 200));
        popUp_berhasilmskKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_berhasilmskKeranjangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_berhasilmskKeranjangMouseEntered(evt);
            }
        });
        popUp_berhasilmskKeranjang.setLayout(null);

        lbl_oke3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_oke3MouseClicked(evt);
            }
        });
        popUp_berhasilmskKeranjang.add(lbl_oke3);
        lbl_oke3.setBounds(940, 610, 170, 40);

        bg_inputbarang7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup data berhasil dimasukkan ke keranjang.png"))); // NOI18N
        bg_inputbarang7.setToolTipText("");
        bg_inputbarang7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_berhasilmskKeranjang.add(bg_inputbarang7);
        bg_inputbarang7.setBounds(660, 390, 720, 310);

        getContentPane().add(popUp_berhasilmskKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_Konfirmasihapus.setBackground(new Color(0,0,0,200));
        popUp_Konfirmasihapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_KonfirmasihapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_KonfirmasihapusMouseEntered(evt);
            }
        });
        popUp_Konfirmasihapus.setLayout(null);

        btn_hapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapus1MouseClicked(evt);
            }
        });
        popUp_Konfirmasihapus.add(btn_hapus1);
        btn_hapus1.setBounds(1010, 620, 160, 40);

        btn_tidak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tidakMouseClicked(evt);
            }
        });
        popUp_Konfirmasihapus.add(btn_tidak);
        btn_tidak.setBounds(800, 620, 170, 40);

        bg_inputbarang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup konfirmasi hapus.png"))); // NOI18N
        bg_inputbarang4.setToolTipText("");
        bg_inputbarang4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_Konfirmasihapus.add(bg_inputbarang4);
        bg_inputbarang4.setBounds(650, 400, 680, 310);

        getContentPane().add(popUp_Konfirmasihapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_berhasilEdit.setBackground(new Color(0, 0, 0, 200));
        popUp_berhasilEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_berhasilEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_berhasilEditMouseEntered(evt);
            }
        });
        popUp_berhasilEdit.setLayout(null);

        lbl_oke2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_oke2MouseClicked(evt);
            }
        });
        popUp_berhasilEdit.add(lbl_oke2);
        lbl_oke2.setBounds(910, 620, 160, 40);

        bg_inputbarang5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup berhasil diedit.png"))); // NOI18N
        bg_inputbarang5.setToolTipText("");
        bg_inputbarang5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_berhasilEdit.add(bg_inputbarang5);
        bg_inputbarang5.setBounds(650, 400, 680, 300);

        getContentPane().add(popUp_berhasilEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        panel_editPelanggan.setBackground(new Color(0, 0, 0, 200));
        panel_editPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_editPelangganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_editPelangganMouseEntered(evt);
            }
        });
        panel_editPelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtf_idPel1.setBackground(new Color(0, 0, 0, 0));
        txtf_idPel1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_idPel1.setBorder(null);
        txtf_idPel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_idPel1ActionPerformed(evt);
            }
        });
        panel_editPelanggan.add(txtf_idPel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, 310, 30));

        lbl_editPel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_editPel1MouseClicked(evt);
            }
        });
        panel_editPelanggan.add(lbl_editPel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 640, 160, 40));

        lbl_closee1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closee1MouseClicked(evt);
            }
        });
        panel_editPelanggan.add(lbl_closee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 310, 50, 40));

        txtf_namaPel1.setBackground(new Color(0, 0, 0, 0));
        txtf_namaPel1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_namaPel1.setBorder(null);
        panel_editPelanggan.add(txtf_namaPel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 450, 320, 30));

        txtf_notelpPell1.setBackground(new Color(0, 0, 0, 0));
        txtf_notelpPell1.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N
        txtf_notelpPell1.setBorder(null);
        panel_editPelanggan.add(txtf_notelpPell1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 540, 320, 30));

        txtf_alamatPel1.setBackground(new Color(0, 0, 0, 0));
        txtf_alamatPel1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_alamatPel1.setBorder(null);
        panel_editPelanggan.add(txtf_alamatPel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, 310, 30));

        bg_inputPelnggn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit Data Pelanngan.png"))); // NOI18N
        panel_editPelanggan.add(bg_inputPelnggn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 980, 420));

        getContentPane().add(panel_editPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        panel_tambahPelanggan.setBackground(new Color(0, 0, 0, 200));
        panel_tambahPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_tambahPelangganMouseEntered(evt);
            }
        });
        panel_tambahPelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtf_idPel.setBackground(new Color(0, 0, 0, 0));
        txtf_idPel.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_idPel.setBorder(null);
        txtf_idPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_idPelActionPerformed(evt);
            }
        });
        panel_tambahPelanggan.add(txtf_idPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 320, 40));

        lbl_tambahPel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tambahPelMouseClicked(evt);
            }
        });
        panel_tambahPelanggan.add(lbl_tambahPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 640, 160, 30));

        lbl_closee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeeMouseClicked(evt);
            }
        });
        panel_tambahPelanggan.add(lbl_closee, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 310, 50, 40));

        txtf_namaPel.setBackground(new Color(0, 0, 0, 0));
        txtf_namaPel.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_namaPel.setBorder(null);
        panel_tambahPelanggan.add(txtf_namaPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 440, 320, 40));

        txtf_notelpPell.setBackground(new Color(0, 0, 0, 0));
        txtf_notelpPell.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_notelpPell.setBorder(null);
        panel_tambahPelanggan.add(txtf_notelpPell, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 530, 320, 40));

        txtf_alamatPel.setBackground(new Color(0, 0, 0, 0));
        txtf_alamatPel.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_alamatPel.setBorder(null);
        panel_tambahPelanggan.add(txtf_alamatPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 320, 40));

        bg_inputPelnggn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Input Data Pelanggan.png"))); // NOI18N
        panel_tambahPelanggan.add(bg_inputPelnggn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 980, 410));

        getContentPane().add(panel_tambahPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_berhasilHapus.setBackground(new Color(0, 0, 0, 200));
        popUp_berhasilHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_berhasilHapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_berhasilHapusMouseEntered(evt);
            }
        });
        popUp_berhasilHapus.setLayout(null);

        lbl_oke1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_oke1MouseClicked(evt);
            }
        });
        popUp_berhasilHapus.add(lbl_oke1);
        lbl_oke1.setBounds(940, 610, 160, 40);

        bg_inputbarang6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup data berhasil dihapus.png"))); // NOI18N
        bg_inputbarang6.setToolTipText("");
        bg_inputbarang6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popUp_berhasilHapus.add(bg_inputbarang6);
        bg_inputbarang6.setBounds(680, 390, 680, 310);

        getContentPane().add(popUp_berhasilHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        lbl_dataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dataPelangganMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_dataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 160, 250, 40));

        lbl_cariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cariBarangMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 160, 180, 40));

        txtf_idTransaksi.setBackground(new Color(0,0,0,1));
        txtf_idTransaksi.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N
        txtf_idTransaksi.setBorder(null);
        getContentPane().add(txtf_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 700, 400, 40));

        cmb_namaPegawai.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        cmb_namaPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<--- Nama Pegawai --->" }));
        cmb_namaPegawai.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_namaPegawaiPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmb_namaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_namaPegawaiActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_namaPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 780, 400, 40));

        txtf_idPelanggan1.setBackground(new Color(0,0,0,1));
        txtf_idPelanggan1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_idPelanggan1.setBorder(null);
        txtf_idPelanggan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_idPelanggan1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_idPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 780, 400, 40));

        txtf_namaPelanggan.setBackground(new Color(0,0,0,1));
        txtf_namaPelanggan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_namaPelanggan.setBorder(null);
        getContentPane().add(txtf_namaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 780, 390, 40));

        txtf_total.setBackground(new Color(0,0,0,1));
        txtf_total.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_total.setBorder(null);
        txtf_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_totalMouseClicked(evt);
            }
        });
        txtf_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_totalKeyReleased(evt);
            }
        });
        getContentPane().add(txtf_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 890, 400, 40));

        txtf_bayar.setBackground(new Color(0,0,0,1));
        txtf_bayar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_bayar.setBorder(null);
        txtf_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_bayarActionPerformed(evt);
            }
        });
        txtf_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txtf_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 890, 400, 40));

        txtf_kembalian.setBackground(new Color(0,0,0,1));
        txtf_kembalian.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_kembalian.setBorder(null);
        getContentPane().add(txtf_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 890, 390, 40));

        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable.setBorder(null);

        tbl_keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
        ));
        tbl_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_keranjangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_keranjangMouseEntered(evt);
            }
        });
        tbl_keranjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_keranjangKeyReleased(evt);
            }
        });
        spTable.setViewportView(tbl_keranjang);

        roundedPanel1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1410, 390));
        roundedPanel1.add(scrollBarCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 160, 10, 180));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 1450, 430));
        getContentPane().add(lbl_tranksaksiBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 180, 40));

        lbl_trankPenitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_trankPenitipanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_trankPenitipan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 110, 40));

        lbl_trankGromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_trankGrommingMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_trankGromming, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 110, 40));

        lbl_batalBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_batalBarangMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_batalBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 970, 230, 60));

        lbl_scan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_scanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 160, 170, 40));

        lbl_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 970, 240, 60));

        lbl_formDashboard1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formDashboard1MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formDashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 340, 50));

        lbl_formStok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStok1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formStok1MouseEntered(evt);
            }
        });
        getContentPane().add(lbl_formStok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 340, 50));

        lbl_formLayanan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLayanan1MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLayanan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 50));

        lbl_formTransaksi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formTransaksi1MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formTransaksi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 340, 50));

        lbl_formLaporan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLaporan1MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLaporan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 340, 50));

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        getContentPane().add(Logout2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 970, 340, 60));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/transaksi - barangAdmin.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txtf_barcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_barcodeMouseClicked(evt);
            }
        });
        txtf_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtf_barcodeKeyPressed(evt);
            }
        });
        getContentPane().add(txtf_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 160, -1));

        txtf_editjumlahBrng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtf_editjumlahBrngKeyPressed(evt);
            }
        });
        getContentPane().add(txtf_editjumlahBrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 150, 70, -1));

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_transaksiBarangAdmin.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void txtf_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_bayarActionPerformed

    private void lbl_cariBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cariBarangMouseClicked
        panel_dataBarang.setVisible(true);
    }//GEN-LAST:event_lbl_cariBarangMouseClicked

    private void lbl_dataPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dataPelangganMouseClicked
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_dataPelangganMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        txtf_idPelanggan1.getText();
        txtf_namaPelanggan.getText();
        panel_dataPelanggan.setVisible(false);
//        try{
//            String sql = "INSERT INTO tb_transaksi VALUES ('"+txtf_idPelanggan.getText()+"')";
//            java.sql.Connection conn = (Connection)config.configDB();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.execute();
//            
//            JOptionPane.showMessageDialog(null, "DATA BERHASIL DITAMBAHKAN");
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtf_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariKeyReleased
        cariDataBarang();
    }//GEN-LAST:event_txtf_cariKeyReleased

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        panel_dataBarang.setVisible(false);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void panel_dataBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dataBarangMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_dataBarangMouseEntered

    private void lbl_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close1MouseClicked
        panel_dataPelanggan.setVisible(false);
    }//GEN-LAST:event_lbl_close1MouseClicked

    private void tbl_dataPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dataPelangganMouseClicked
        int baris = tbl_dataPelanggan.rowAtPoint(evt.getPoint());
//        String id_barang = tbl_stokMakanan.getValueAt(baris, 0).toString();
//        form_stokMakanan.idBarang = id_barang;
//        panel_edit.setVisible(true);
        if (baris != -1 ) {
            txtf_idPel1.disable();
            txtf_idPel1.setText(tbl_dataPelanggan.getValueAt(baris, 0).toString());
            txtf_namaPel1.setText(tbl_dataPelanggan.getValueAt(baris, 1).toString());
            txtf_alamatPel1.setText(tbl_dataPelanggan.getValueAt(baris, 2).toString());
            txtf_notelpPell1.setText(tbl_dataPelanggan.getValueAt(baris, 3).toString());
            
            txtf_idPelanggan.setText(tbl_dataPelanggan.getValueAt(baris, 0).toString());
            txtf_idPelanggan1.setText(tbl_dataPelanggan.getValueAt(baris, 0).toString());
            txtf_namaPelanggan.setText(tbl_dataPelanggan.getValueAt(baris, 1).toString());
        }
    }//GEN-LAST:event_tbl_dataPelangganMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        TableModel model1 = tbl_dataBarang.getModel();
        int ik = tbl_dataBarang.getSelectedRow();
        String id = model1.getValueAt(ik, 0).toString();
        String namaBarang = model1.getValueAt(ik, 1).toString();
        String harga = model1.getValueAt(ik, 6).toString();

        DefaultTableModel model2 = (DefaultTableModel) tbl_keranjang.getModel();
        String data2 = txtf_jumlahBarang.getText();
        
        int jumlahStok = Integer.parseInt((String) tbl_dataBarang.getValueAt(ik, 5));
        int jumlahBarang = Integer.parseInt(txtf_jumlahBarang.getText());
        
        txtf_total.disable();

        int total1 = Integer.parseInt(harga) * Integer.parseInt(data2);
        
        if(jumlahStok > jumlahBarang){
            popUp_berhasilmskKeranjang.setVisible(true);
            
            model2.addRow(new Object[]{
            id,
            namaBarang,
            harga,
            data2,
            total1
            });
            
        }else{ 
            popUp_stokKurang.setVisible(true);
            panel_dataBarang.setVisible(true);
        }
        
//        TableModel model1 = tbl_dataBarang.getModel();
//        int[] indexs = tbl_dataBarang.getSelectedRows();
//        Object[] row = new Object[5];
//        DefaultTableModel model2 = (DefaultTableModel) tbl_keranjang.getModel();
//        String data2 = txtf_jumlahBarang.getText();
//        int ik = tbl_dataBarang.getSelectedRow();
//        String harga = model1.getValueAt(ik, 6).toString();
//        int total = Integer.parseInt(harga) * Integer.parseInt(data2);
//        txtf_total.disable();
//        
//        for(int i = 0; i < indexs.length; i++){
//            row[0] = model1.getValueAt(indexs[i], 0);
//            row[1] = model1.getValueAt(indexs[i], 1);
//            row[2] = model1.getValueAt(indexs[i], 6);
//            row[3] = data2;
//            row[4] = total;
//            model2.addRow(row);
//            
//            
//        }
        panel_dataBarang.setVisible(false);
        kasirBarang();
        datatabel2();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtf_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_bayarKeyReleased
        txtf_kembalian.disable();
        int bayar = Integer.valueOf(reFormat(txtf_bayar.getText()));
        int total = Integer.valueOf(reFormat(txtf_total.getText()));
        
        txtf_bayar.setText(formatDec(bayar));
        
        kembalian = bayar - total;
        
        txtf_kembalian.setText(String.valueOf(formatDec(kembalian)));
    }//GEN-LAST:event_txtf_bayarKeyReleased

    private void cmb_namaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_namaPegawaiActionPerformed

    }//GEN-LAST:event_cmb_namaPegawaiActionPerformed

    private void cmb_namaPegawaiPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_namaPegawaiPopupMenuWillBecomeInvisible
        
    }//GEN-LAST:event_cmb_namaPegawaiPopupMenuWillBecomeInvisible

    private void lbl_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tambahMouseClicked
        int rows = tbl_keranjang.getRowCount();
        try{
            //INSERT DATA TRANSAKSI
            String sql = "INSERT INTO tb_transaksi_barang VALUES ('"+txtf_idTransaksi.getText()+"', CURRENT_TIMESTAMP , '"+cmb_namaPegawai.getSelectedItem()+"', '"+txtf_idPelanggan1.getText()+"', "
                    + " '"+reFormat(txtf_total.getText())+"', '"+reFormat(txtf_bayar.getText())+"', '"+reFormat(txtf_kembalian.getText())+"')";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            
            for(int i = 0; i < rows; i++){
                String sql1 = "INSERT INTO detail_transaksi_barang VALUES ('"+txtf_idTransaksi.getText()+"', '"+tbl_keranjang.getValueAt(i, 0).toString()+"',"
                        + " '"+txtf_idPelanggan.getText()+"', '"+tbl_keranjang.getValueAt(i, 3).toString()+"', '"+tbl_keranjang.getValueAt(i, 2).toString()+"', "
                        + "'"+tbl_keranjang.getValueAt(i, 4).toString()+"',CURRENT_TIMESTAMP)";
                java.sql.Connection conn1 = (Connection)config.configDB();
                java.sql.PreparedStatement pst1 = conn1.prepareStatement(sql1);
                pst1.execute();
            }
            
            for(int j = 0; j < rows; j++){
                String sql1 = "INSERT INTO tb_history_transaksi VALUES ('"+txtf_idTransaksi.getText()+"', '"+tbl_keranjang.getValueAt(j, 1).toString()+"',"
                        + " '"+tbl_keranjang.getValueAt(j, 3).toString()+"',"
                        + "'"+tbl_keranjang.getValueAt(j, 4).toString()+"',CURRENT_TIMESTAMP)";
                java.sql.Connection conn1 = (Connection)config.configDB();
                java.sql.PreparedStatement pst1 = conn1.prepareStatement(sql1);
                pst1.execute();
            }
//            try{
//                    for (int i = 0; i< rows; i++){
//                        String query = "INSERT INTO detail_transaksi_barang VALUES ('"+txtf_idTransaksi.getText()+"', '"+tbl_keranjang.getValueAt(i, 0).toString()+"', '"+txtf_idPelanggan1.getText()+"',"
//                                + " '"+tbl_dataPelanggan.getValueAt(i, 3).toString()+"', '"+tbl_keranjang.getValueAt(i, 2).toString()+"')";
//                        java.sql.PreparedStatement statement = conn.prepareStatement(query);
//                        statement.execute();
//                    }
//            
//            }catch (Exception eRollback){
//                eRollback.printStackTrace();
////                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
//            
        popUp_trankberhasilDisimpan.setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_lbl_tambahMouseClicked

    private void txtf_idPelanggan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_idPelanggan1ActionPerformed
        
    }//GEN-LAST:event_txtf_idPelanggan1ActionPerformed

    private void txtf_idPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_idPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_idPelangganActionPerformed

    private void panel_dataPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dataPelangganMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_dataPelangganMouseEntered

    private void btn_tambahPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahPelangganMouseClicked
        panel_dataPelanggan.setVisible(false);
        panel_tambahPelanggan.setVisible(true);
    }//GEN-LAST:event_btn_tambahPelangganMouseClicked

    private void btn_tambahPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahPelangganMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahPelangganMouseEntered

    private void txtf_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_barcodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String barcode = txtf_barcode.getText();
            matchBarcodeWithData(barcode);
            txtf_barcode.setText("");  // Mengosongkan JTextField setelah pemindaian
        }
    }//GEN-LAST:event_txtf_barcodeKeyPressed

    private void txtf_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_totalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_totalMouseClicked

    private void txtf_totalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_totalKeyReleased
        kasirBarang();
        
    }//GEN-LAST:event_txtf_totalKeyReleased

    private void tbl_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_keranjangMouseClicked
        int baris = tbl_keranjang.rowAtPoint(evt.getPoint());
        if (baris != -1 ) {
            txtf_editjumlahBrng.setText(tbl_keranjang.getValueAt(baris, 3).toString());
        }
       
    }//GEN-LAST:event_tbl_keranjangMouseClicked

    private void tbl_keranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_keranjangMouseEntered
        kasirBarang();
    }//GEN-LAST:event_tbl_keranjangMouseEntered

    private void txtf_barcodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_barcodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_barcodeMouseClicked

    private void tbl_keranjangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_keranjangKeyReleased
        
    }//GEN-LAST:event_tbl_keranjangKeyReleased

    private void lbl_scanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_scanMouseClicked
        txtf_barcode.requestFocus();
    }//GEN-LAST:event_lbl_scanMouseClicked

    private void txtf_idPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_idPelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_idPelActionPerformed

    private void lbl_closeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeeMouseClicked
        panel_tambahPelanggan.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_closeeMouseClicked

    private void panel_tambahPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_tambahPelangganMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_tambahPelangganMouseEntered

    private void txtf_idPel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_idPel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_idPel1ActionPerformed

    private void lbl_closee1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closee1MouseClicked
        panel_editPelanggan.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_closee1MouseClicked

    private void panel_editPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_editPelangganMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_editPelangganMouseEntered

    private void btn_editPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editPelangganMouseClicked
        panel_dataPelanggan.setVisible(false);
        panel_editPelanggan.setVisible(true);
    }//GEN-LAST:event_btn_editPelangganMouseClicked

    private void panel_editPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_editPelangganMouseClicked
        
    }//GEN-LAST:event_panel_editPelangganMouseClicked

    private void lbl_tambahPelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tambahPelMouseClicked
        try{
            String sql = "INSERT INTO tb_pelanggan VALUES ('"+txtf_idPel.getText()+"', '"+txtf_namaPel.getText()+"',"
                    + " '"+txtf_alamatPel.getText()+"', '"+txtf_notelpPell.getText()+"')";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            
            datatable1();
            panel_tambahPelanggan.setVisible(false);
            popUp_berhasilDisimpan.setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lbl_tambahPelMouseClicked

    private void lbl_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_okeMouseClicked
        popUp_berhasilDisimpan.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_okeMouseClicked

    private void popUp_berhasilDisimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilDisimpanMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_popUp_berhasilDisimpanMouseClicked

    private void popUp_berhasilDisimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilDisimpanMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_berhasilDisimpanMouseEntered

    private void lbl_editPel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_editPel1MouseClicked
        try {
            String sql = "UPDATE tb_pelanggan SET nama_pelanggan = '"+txtf_namaPel1.getText()+"', alamat_pelanggan = '"+txtf_alamatPel1.getText()+"', "
            + " nomor_hp = '"+txtf_notelpPell1.getText()+"'"
            + "WHERE tb_pelanggan.id_pelanggan = '"+txtf_idPel1.getText()+"'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            datatable1();
            popUp_berhasilEdit.setVisible(true);
            panel_editPelanggan.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lbl_editPel1MouseClicked

    private void lbl_oke2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_oke2MouseClicked
        popUp_berhasilEdit.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_oke2MouseClicked

    private void popUp_berhasilEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilEditMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_popUp_berhasilEditMouseClicked

    private void popUp_berhasilEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilEditMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_berhasilEditMouseEntered

    private void txtf_cari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cari1KeyReleased
        cariDataPelanggan();
    }//GEN-LAST:event_txtf_cari1KeyReleased

    private void txtf_cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_cari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cari1ActionPerformed

    private void lbl_trankGrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_trankGrommingMouseClicked
        this.dispose();
        new form_transaksiGrommingAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_trankGrommingMouseClicked

    private void lbl_trankPenitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_trankPenitipanMouseClicked
        this.dispose();
        new form_transaksiPenitipanAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_trankPenitipanMouseClicked

    private void tbl_dataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dataBarangMouseClicked
        int baris = tbl_dataBarang.rowAtPoint(evt.getPoint());
        if (baris != -1 ) {
            txtf_idBarang.setText(tbl_dataBarang.getValueAt(baris, 0).toString());
        }
    }//GEN-LAST:event_tbl_dataBarangMouseClicked

    private void lbl_batalBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_batalBarangMouseClicked
        popUp_hpsDataKeranjang.setVisible(true);
    }//GEN-LAST:event_lbl_batalBarangMouseClicked

    private void txtf_editjumlahBrngKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_editjumlahBrngKeyPressed
        DefaultTableModel model = (DefaultTableModel) tbl_keranjang.getModel();
        int selectedRow = tbl_keranjang.getSelectedRow();
        
        if (selectedRow != -1) { // Pastikan ada baris yang dipilih
            // Dapatkan nilai-nilai yang ingin diedit dari pengguna
            String editjmlh = txtf_editjumlahBrng.getText(); // Contoh nilai dari JTextField
            // ...

            // Ubah nilai-nilai yang sesuai pada model tabel
            model.setValueAt(tbl_keranjang, selectedRow, 3); // Mengubah nilai pada kolom 0 (misalnya)
            // ...

            // Perbarui tampilan tabel
            model.fireTableDataChanged();
        }    
    }//GEN-LAST:event_txtf_editjumlahBrngKeyPressed

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        popUp_Konfirmasihapus.setVisible(true);
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void btn_hapus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus1MouseClicked
        try{
            int row = tbl_dataPelanggan.getSelectedRow();
            String id_pelangga = tbl_dataPelanggan.getModel().getValueAt (row, 0).toString();
            String resultSet = "DELETE FROM tb_pelanggan WHERE id_pelanggan ='"+ id_pelangga +"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
            pst.execute();

            datatable1();
            panel_dataPelanggan.setVisible(false);
            popUp_Konfirmasihapus.setVisible(false);
            popUp_berhasilHapus.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btn_hapus1MouseClicked

    private void btn_tidakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tidakMouseClicked
        popUp_Konfirmasihapus.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_btn_tidakMouseClicked

    private void popUp_KonfirmasihapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_KonfirmasihapusMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_popUp_KonfirmasihapusMouseClicked

    private void popUp_KonfirmasihapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_KonfirmasihapusMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_KonfirmasihapusMouseEntered

    private void lbl_oke1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_oke1MouseClicked
        popUp_berhasilHapus.setVisible(false);
        panel_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_oke1MouseClicked

    private void popUp_berhasilHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilHapusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_berhasilHapusMouseClicked

    private void popUp_berhasilHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilHapusMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_berhasilHapusMouseEntered

    private void lbl_oke3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_oke3MouseClicked
        popUp_berhasilmskKeranjang.setVisible(false);
    }//GEN-LAST:event_lbl_oke3MouseClicked

    private void popUp_berhasilmskKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilmskKeranjangMouseClicked

    }//GEN-LAST:event_popUp_berhasilmskKeranjangMouseClicked

    private void popUp_berhasilmskKeranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_berhasilmskKeranjangMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_berhasilmskKeranjangMouseEntered

    private void lbl_oke4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_oke4MouseClicked
        popUp_stokKurang.setVisible(false);
        panel_dataBarang.setVisible(true);
    }//GEN-LAST:event_lbl_oke4MouseClicked

    private void popUp_stokKurangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_stokKurangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_stokKurangMouseClicked

    private void popUp_stokKurangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_stokKurangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_stokKurangMouseEntered

    private void btn_hapus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus2MouseClicked
        DefaultTableModel model = (DefaultTableModel) tbl_keranjang.getModel();
        int selectedRow = tbl_keranjang.getSelectedRow();

        if (selectedRow != -1) { // Pastikan ada baris yang dipilih
            model.removeRow(selectedRow);
            model.fireTableDataChanged();
            txtf_total.setText("0");
        }
        popUp_hpsDataKeranjang.setVisible(false);
    }//GEN-LAST:event_btn_hapus2MouseClicked

    private void btn_tidak1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tidak1MouseClicked
       popUp_hpsDataKeranjang.setVisible(false);
    }//GEN-LAST:event_btn_tidak1MouseClicked

    private void popUp_hpsDataKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_hpsDataKeranjangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_hpsDataKeranjangMouseClicked

    private void popUp_hpsDataKeranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_hpsDataKeranjangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_hpsDataKeranjangMouseEntered

    private void lbl_oke5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_oke5MouseClicked
        popUp_trankberhasilDisimpan.setVisible(false);
        try {
            String report = ("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\siopet\\src\\iReport\\nota_barang.jrxml");
        java.sql.Connection conn = (Connection)config.configDB();
                HashMap hash = new HashMap();
                hash.put("kode", txtf_idTransaksi.getText());
                JasperReport jrpt = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jrpt, hash, conn);
                JasperViewer.viewReport(jp, false);
//                JasperPrintManager.printReport(jp, false); 
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_lbl_oke5MouseClicked

    private void popUp_trankberhasilDisimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_trankberhasilDisimpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_trankberhasilDisimpanMouseClicked

    private void popUp_trankberhasilDisimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_trankberhasilDisimpanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_trankberhasilDisimpanMouseEntered

    private void lbl_formDashboard1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboard1MouseClicked
        this.setVisible(false);
        new form_dashboardAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formDashboard1MouseClicked

    private void lbl_formStok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStok1MouseClicked
        this.setVisible(false);
        new form_stokAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formStok1MouseClicked

    private void lbl_formStok1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStok1MouseEntered

    }//GEN-LAST:event_lbl_formStok1MouseEntered

    private void lbl_formLayanan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLayanan1MouseClicked
        this.setVisible(false);
        new form_layananAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formLayanan1MouseClicked

    private void lbl_formTransaksi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formTransaksi1MouseClicked
        this.setVisible(false);
        new form_transaksiBarangAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formTransaksi1MouseClicked

    private void lbl_formLaporan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLaporan1MouseClicked
        this.setVisible(false);
        new form_laporanBerlangsungAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formLaporan1MouseClicked

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
            java.util.logging.Logger.getLogger(form_transaksiBarangAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_transaksiBarangAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_transaksiBarangAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_transaksiBarangAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_transaksiBarangAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout2;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dataBarang;
    private javax.swing.JLabel bg_dataBarang1;
    private javax.swing.JLabel bg_inputPelnggn;
    private javax.swing.JLabel bg_inputPelnggn1;
    private javax.swing.JLabel bg_inputbarang10;
    private javax.swing.JLabel bg_inputbarang3;
    private javax.swing.JLabel bg_inputbarang4;
    private javax.swing.JLabel bg_inputbarang5;
    private javax.swing.JLabel bg_inputbarang6;
    private javax.swing.JLabel bg_inputbarang7;
    private javax.swing.JLabel bg_inputbarang8;
    private javax.swing.JLabel bg_inputbarang9;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel btn_editPelanggan;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JLabel btn_hapus1;
    private javax.swing.JLabel btn_hapus2;
    private javax.swing.JLabel btn_tambahPelanggan;
    private javax.swing.JLabel btn_tidak;
    private javax.swing.JLabel btn_tidak1;
    private javax.swing.JComboBox<String> cmb_namaPegawai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_batalBarang;
    private javax.swing.JLabel lbl_cariBarang;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_close1;
    private javax.swing.JLabel lbl_closee;
    private javax.swing.JLabel lbl_closee1;
    private javax.swing.JLabel lbl_dataPelanggan;
    private javax.swing.JLabel lbl_editPel1;
    private javax.swing.JLabel lbl_formDashboard1;
    private javax.swing.JLabel lbl_formLaporan1;
    private javax.swing.JLabel lbl_formLayanan1;
    private javax.swing.JLabel lbl_formStok1;
    private javax.swing.JLabel lbl_formTransaksi1;
    private javax.swing.JLabel lbl_oke;
    private javax.swing.JLabel lbl_oke1;
    private javax.swing.JLabel lbl_oke2;
    private javax.swing.JLabel lbl_oke3;
    private javax.swing.JLabel lbl_oke4;
    private javax.swing.JLabel lbl_oke5;
    private javax.swing.JLabel lbl_scan;
    private javax.swing.JLabel lbl_tambah;
    private javax.swing.JLabel lbl_tambahPel;
    private javax.swing.JLabel lbl_trankGromming;
    private javax.swing.JLabel lbl_trankPenitipan;
    private javax.swing.JLabel lbl_tranksaksiBarang;
    private javax.swing.JPanel panel_dataBarang;
    private javax.swing.JPanel panel_dataPelanggan;
    private javax.swing.JPanel panel_editPelanggan;
    private javax.swing.JPanel panel_tambahPelanggan;
    private javax.swing.JPanel popUp_Konfirmasihapus;
    private javax.swing.JPanel popUp_berhasilDisimpan;
    private javax.swing.JPanel popUp_berhasilEdit;
    private javax.swing.JPanel popUp_berhasilHapus;
    private javax.swing.JPanel popUp_berhasilmskKeranjang;
    private javax.swing.JPanel popUp_hpsDataKeranjang;
    private javax.swing.JPanel popUp_keluar2;
    private javax.swing.JPanel popUp_stokKurang;
    private javax.swing.JPanel popUp_trankberhasilDisimpan;
    private panelRounded.RoundedPanel roundedPanel1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom2;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom3;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable_barang;
    private javax.swing.JScrollPane spTable_pelanggan;
    private com.raven.swing.Table tbl_dataBarang;
    private com.raven.swing.Table tbl_dataPelanggan;
    public static com.raven.swing.Table tbl_keranjang;
    private javax.swing.JTextField txtf_alamatPel;
    private javax.swing.JTextField txtf_alamatPel1;
    private javax.swing.JTextField txtf_barcode;
    private javax.swing.JTextField txtf_bayar;
    private javax.swing.JTextField txtf_cari;
    private javax.swing.JTextField txtf_cari1;
    private javax.swing.JTextField txtf_editjumlahBrng;
    public static final javax.swing.JTextField txtf_idBarang = new javax.swing.JTextField();
    private javax.swing.JTextField txtf_idPel;
    private javax.swing.JTextField txtf_idPel1;
    private javax.swing.JTextField txtf_idPelanggan;
    private javax.swing.JTextField txtf_idPelanggan1;
    private javax.swing.JTextField txtf_idTransaksi;
    private javax.swing.JTextField txtf_jumlahBarang;
    private javax.swing.JTextField txtf_kembalian;
    private javax.swing.JTextField txtf_namaPel;
    private javax.swing.JTextField txtf_namaPel1;
    private javax.swing.JTextField txtf_namaPelanggan;
    private javax.swing.JTextField txtf_notelpPell;
    private javax.swing.JTextField txtf_notelpPell1;
    private javax.swing.JTextField txtf_total;
    // End of variables declaration//GEN-END:variables
}



//DefaultTableModel model1 = new DefaultTableModel();
////        model1.addColumn("Kolom 1");
////        model1.addColumn("Kolom 2");
////        model1.addColumn("Kolom 7");
////        tbl_dataBarang.setModel(model1);

//        DefaultTableModel model2 = new DefaultTableModel();
//        model2.addColumn("Kolom 1");
//        model2.addColumn("Kolom 2");
//        model2.addColumn("Kolom 7");D
//        tbl_keranjang.setModel(model2);
//        int selectedRow = tbl_dataBarang.getSelectedRow();
//        if(selectedRow >= 0) {
//            String[] rowData = new String[3];
//            rowData[0] = tbl_dataBarang.getValueAt(selectedRow, 0).toString();
//            rowData[1] = tbl_dataBarang.getValueAt(selectedRow, 1).toString();
//            rowData[2] = tbl_dataBarang.getValueAt(selectedRow, 6).toString();
//            model2.addRow(rowData);
//        }

//try{
////            conn.setAutoCommit(false);
////            java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
//            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            
//            // Query SQL untuk menyisipkan data
//            while(rs.next()){
//                for (int i = 0; i< rows; i++){
//                    String query = "INSERT INTO detail_transaksi_barang VALUES ('"+txtf_idTransaksi.getText()+"', '"+tbl_keranjang.getValueAt(i, 0).toString()+"', '"+txtf_nama.getText()+"',"
//                            + " '"+tbl_dataPelanggan.getValueAt(i, 3).toString()+"', '"+tbl_keranjang.getValueAt(i, 2).toString()+"')";
//                    java.sql.PreparedStatement statement = conn.prepareStatement(query);
//                    statement.execute();
//                }
//            }
//            
//            }catch (Exception eRollback){
//                eRollback.printStackTrace();
////                JOptionPane.showMessageDialog(this, e.getMessage());
//            }