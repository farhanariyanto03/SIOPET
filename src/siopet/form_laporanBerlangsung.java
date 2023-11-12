
package siopet;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import notification.MainForm;


public class form_laporanBerlangsung extends javax.swing.JFrame {

    
        
    public form_laporanBerlangsung() {
        initComponents();
        //LAPORAN SEDANG BERJALAN//
        datatable1();
        dataPelanggan();
        barangTerlaris();
        grommingTerlaris();
        penitipanTerlaris();
        hitungPenjumlahan();
        hitungPenjumlahan1();
        
        //LAPORAN SELESAI
        laporanSelesai();
        dataPelanggan1();
        barangTerlaris1();
        grommingTerlaris1();
        penitipanTerlaris1();
        popUp_keluar2.setVisible(false);
                
        //LAPORAN SEDANG BERJALAN
        //TABLE TRANSAKSI
        spTable.setVerticalScrollBar(scrollBarCustom1);
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        //TABLE BARANG
        spTableStatistik1.setVerticalScrollBar(scrollBarCustom2);
        spTableStatistik1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik1.getViewport().setBackground(Color.WHITE);
        
        //TABLE GROMMING
        spTableStatistik2.setVerticalScrollBar(scrollBarCustom3);
        spTableStatistik2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik2.getViewport().setBackground(Color.WHITE);
        
        //TABLE PENITIPAN
        spTableStatistik3.setVerticalScrollBar(scrollBarCustom4);
        spTableStatistik3.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik3.getViewport().setBackground(Color.WHITE);
        
        //TABLE PELANGGAN
        spTabledataPelanggan.setVerticalScrollBar(scrollBarCustom5);
        spTabledataPelanggan.getVerticalScrollBar().setBackground(Color.WHITE);
        spTabledataPelanggan.getViewport().setBackground(Color.WHITE);
        
        //----PANEL LAPORAN SEDANG BERJALAN----//
        popUp_filterTanggal.setVisible(false);
        popUp_dataStatisBarang.setVisible(false);
        popUp_dataStatisGromming.setVisible(false);
        popUp_dataStatistikPenitipan.setVisible(false);
        panel_cari.setVisible(false);
        popUp_dataPelanggan.setVisible(false);
        
        
        //-----LAPORAN SELESAI----//
        //TABLE PELANGGAN
        spTable1.setVerticalScrollBar(scrollBarCustom6);
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        
        //TABLE BARANG
        spTableStatistik5.setVerticalScrollBar(scrollBarCustom8);
        spTableStatistik5.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik5.getViewport().setBackground(Color.WHITE);
        
        //TABLE GROMMING
        spTableStatistik6.setVerticalScrollBar(scrollBarCustom9);
        spTableStatistik6.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik6.getViewport().setBackground(Color.WHITE);
        
        //TABLE PENITIPAN8
        spTableStatistik4.setVerticalScrollBar(scrollBarCustom7);
        spTableStatistik4.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableStatistik4.getViewport().setBackground(Color.WHITE);
        
        //TABLE PELANGGAN
        spTabledataPelanggan1.setVerticalScrollBar(scrollBarCustom10);
        spTabledataPelanggan1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTabledataPelanggan1.getViewport().setBackground(Color.WHITE);
        
        //-----LAPORAN SELESAI-----//
        panel_laporanSelesai.setVisible(false);
        popUp_filterTanggal1.setVisible(false);
        popUp_dataStatisBarang1.setVisible(false);
        popUp_dataStatisGromming1.setVisible(false);
        popUp_dataStatistikPenitipan1.setVisible(false);
        popUp_dataPelanggan1.setVisible(false);
        panel_cari1.setVisible(false);
    }
    
    //-----KOMPONEN LAPORAN SEDANG BERJALAN-----//
    public void dataPelanggan(){
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
    
    public void cariDataPelanggan(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID Pelanggan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Alamat");
        dtm.addColumn("No HP");
        tbl_dataPelanggan.setModel(dtm);
        
        String cari = txtf_cariPelanggan.getText();
        
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
    
    public void datatable1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Jenis Layanan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Bayar");
        dtm.addColumn("Waktu Transaksi");
        dtm.addColumn("Status");
        tbl_trankSedangberjalan.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT\n" +
"                    detail_transaksi_gromming.id_transaksi_gromming AS id, tb_gromming.nama_gromming AS nama_layanan, tb_pelanggan.nama_pelanggan, tb_transaksi_gromming.bayar AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi, tb_transaksi_gromming.status AS status\n" +
"                    FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
"                    ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
"                    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
"                    JOIN tb_pelanggan ON detail_transaksi_gromming.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
"                    WHERE tb_transaksi_gromming.status = \"BELUM DIAMBIL\"\n" +
"                    UNION ALL\n" +
            "SELECT \n" +
"                    tb_transaksi_penitipan.id_transaksi_penitipan AS id, tb_penitipan.nama_penitipan AS nama, tb_pelanggan.nama_pelanggan, tb_transaksi_penitipan.bayar AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi, tb_transaksi_penitipan.status AS status\n" +
"                    FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
"                    ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan \n" +
"                    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
"                    JOIN tb_pelanggan ON detail_transaksi_penitipan.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
"                    WHERE tb_transaksi_penitipan.status = \"BELUM DIAMBIL\"\n" +
"                    ORDER BY tanggal_transaksi ASC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama_layanan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("harga"),
                    rs.getString("tanggal_transaksi"),
                    rs.getString("status")
                });
                tbl_trankSedangberjalan.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariTranksaksi(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Jenis Layanan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Bayar");
        dtm.addColumn("Waktu Transaksi");
        dtm.addColumn("Status");
        tbl_trankSedangberjalan.setModel(dtm);
        
        String cari = txtf_cari.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT\n" +
"                    detail_transaksi_gromming.id_transaksi_gromming AS id, tb_gromming.nama_gromming AS nama_layanan, tb_pelanggan.nama_pelanggan, tb_transaksi_gromming.bayar AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi, tb_transaksi_gromming.status AS status\n" +
"                    FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
"                    ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
"                    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
"                    JOIN tb_pelanggan ON detail_transaksi_gromming.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
"                    WHERE tb_transaksi_gromming.status = 'BELUM DIAMBIL' AND tb_gromming.nama_gromming LIKE '%" + cari + "%'\n" +
"                    UNION ALL\n" +
            "SELECT \n" +
"                    tb_transaksi_penitipan.id_transaksi_penitipan AS id, tb_penitipan.nama_penitipan AS nama_layanan, tb_pelanggan.nama_pelanggan, tb_transaksi_penitipan.bayar AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi, tb_transaksi_penitipan.status AS status\n" +
"                    FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
"                    ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan \n" +
"                    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
"                    JOIN tb_pelanggan ON detail_transaksi_penitipan.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
"                    WHERE tb_transaksi_penitipan.status = 'BELUM DIAMBIL' AND tb_penitipan.nama_penitipan LIKE '%" + cari + "%'\n" +
"                    ORDER BY tanggal_transaksi ASC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama_layanan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("harga"),
                    rs.getString("tanggal_transaksi"),
                    rs.getString("status")
                });
                tbl_trankSedangberjalan.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void barangTerlaris(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Terjual");
        tbl_barangTerlaris.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_barang.id_barang AS id, tb_barang.nama_barang AS nama, tb_barang.kategori AS kategori,\n" +
"                        SUM(detail_transaksi_barang.qty) AS terjual \n" +
"                        FROM tb_barang INNER JOIN detail_transaksi_barang\n" +
"                        ON tb_barang.id_barang = detail_transaksi_barang.id_barang\n" +
"                        GROUP BY nama_barang \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getString("terjual"),
                });
                tbl_barangTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariBarang(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Terjual");
        tbl_barangTerlaris.setModel(dtm);
        
        String cari = txtf_cariBarang.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_barang.id_barang AS id, tb_barang.nama_barang AS nama, tb_barang.kategori AS kategori,\n" +
"                        SUM(detail_transaksi_barang.qty) AS terjual \n" +
"                        FROM tb_barang INNER JOIN detail_transaksi_barang\n" +
"                        ON tb_barang.id_barang = detail_transaksi_barang.id_barang\n" +
"                        where nama_barang like'%"+cari+"%' \n" +
"                        GROUP BY nama_barang \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getString("terjual"),
                });
                tbl_barangTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void grommingTerlaris(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Gromming");
        dtm.addColumn("Terjual");
        tbl_grommingTerlaris.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_gromming.id_gromming AS id, tb_gromming.nama_gromming AS nama, \n" +
"                        SUM(detail_transaksi_gromming.qty) AS terjual \n" +
"                        FROM tb_gromming INNER JOIN detail_transaksi_gromming\n" +
"                        ON tb_gromming.id_gromming = detail_transaksi_gromming.id_gromming\n" +
"                        GROUP BY nama_gromming \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_grommingTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariGromming(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Gromming");
        dtm.addColumn("Terjual");
        tbl_grommingTerlaris.setModel(dtm);
        
        String cari = txtf_cariGromming.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_gromming.id_gromming AS id, tb_gromming.nama_gromming AS nama, \n" +
"                        SUM(detail_transaksi_gromming.qty) AS terjual \n" +
"                        FROM tb_gromming INNER JOIN detail_transaksi_gromming\n" +
"                        ON tb_gromming.id_gromming = detail_transaksi_gromming.id_gromming\n" +
"                        where nama_gromming like'%"+cari+"%' \n" +
"                        GROUP BY nama_gromming \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_grommingTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void penitipanTerlaris(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Penitipan");
        dtm.addColumn("Terjual");
        tbl_penitipanTerlaris.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_penitipan.id_penitipan AS id, tb_penitipan.nama_penitipan AS nama, \n" +
"                        SUM(detail_transaksi_penitipan.qty) AS terjual \n" +
"                        FROM tb_penitipan INNER JOIN detail_transaksi_penitipan\n" +
"                        ON tb_penitipan.id_penitipan = detail_transaksi_penitipan.id_penitipan\n" +
"                        GROUP BY nama_penitipan \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_penitipanTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariPenitipan(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Penitipan");
        dtm.addColumn("Terjual");
        tbl_penitipanTerlaris.setModel(dtm);
        
        String cari = txtf_cariPenitipan.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_penitipan.id_penitipan AS id, tb_penitipan.nama_penitipan AS nama, \n" +
"                        SUM(detail_transaksi_penitipan.qty) AS terjual \n" +
"                        FROM tb_penitipan INNER JOIN detail_transaksi_penitipan\n" +
"                        ON tb_penitipan.id_penitipan = detail_transaksi_penitipan.id_penitipan\n" +
"                        where nama_penitipan like'%"+cari+"%' \n" +
"                        GROUP BY nama_penitipan \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_penitipanTerlaris.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void hitungPenjumlahan(){
        try {
            String sql = "SELECT SUM(total) AS total_harga FROM (\n" +
                        "    SELECT SUM(tb_transaksi_gromming.bayar) AS total\n" +
                        "    FROM detail_transaksi_gromming\n" +
                        "    INNER JOIN tb_gromming ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
                        "    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
                        "    JOIN tb_pelanggan ON detail_transaksi_gromming.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
                        "    WHERE tb_transaksi_gromming.status = 'BELUM DIAMBIL'\n" +
                        "    UNION ALL\n" +
                        "    SELECT SUM(tb_transaksi_penitipan.bayar) AS total\n" +
                        "    FROM detail_transaksi_penitipan\n" +
                        "    INNER JOIN tb_penitipan ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan\n" +
                        "    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
                        "    JOIN tb_pelanggan ON detail_transaksi_penitipan.id_pelanggan = tb_pelanggan.id_pelanggan\n" +
                        "    WHERE tb_transaksi_penitipan.status = 'BELUM DIAMBIL'\n" +
                        ") AS total_harga_combined";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
            int total = rs.getInt("total_harga");
            lbl_pendapatanBerlangsung.setText("Jumlah Pendapatan Berlangsung : Rp. " + total );
        }
        rs.close();
        stm.close();
        conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    //-----KOMPIENEN LAPORAN SELESAI-----//
    public void laporanSelesai(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Nama Transaksi");
        dtm.addColumn("Jumlah");
        dtm.addColumn("Total Harga");
        dtm.addColumn("Waktu  Transaksi");
        tbl_laporanTransak.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT\n" +
"                    detail_transaksi_barang.id_transaksi_barang AS id,'Barang' AS sumber, tb_barang.nama_barang AS nama, tb_transaksi_barang.total AS harga, tb_transaksi_barang.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_barang INNER JOIN tb_barang \n" +
"                    ON detail_transaksi_barang.id_barang = tb_barang.id_barang\n" +
"                    JOIN tb_transaksi_barang ON detail_transaksi_barang.id_transaksi_barang = tb_transaksi_barang.id_transaksi_barang\n" +
"                    UNION ALL\n" +
"                    SELECT\n" +
"                    detail_transaksi_gromming.id_transaksi_gromming AS id, 'Gromming' AS sumber, tb_gromming.nama_gromming AS nama, tb_transaksi_gromming.total AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
"                    ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
"                    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
"                    WHERE tb_transaksi_gromming.status = 'SUDAH DIAMBIL' \n"+
"                    UNION ALL\n" +
"                    SELECT \n" +
"                    tb_transaksi_penitipan.id_transaksi_penitipan AS id, 'Penitipan' AS sumber, tb_penitipan.nama_penitipan AS nama, tb_transaksi_penitipan.total AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
"                    ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan \n" +
"                    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
"                     WHERE tb_transaksi_penitipan.status = 'SUDAH DIAMBIL' \n"+
"                    ORDER BY tanggal_transaksi DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("sumber"),
                    rs.getString("nama"),
                    rs.getString("harga"),
                    rs.getString("tanggal_transaksi")
                });
                tbl_laporanTransak.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void CarilaporanSelesai(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false,
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Nama Transaksi");
        dtm.addColumn("Jumlah");
        dtm.addColumn("Total Harga");
        dtm.addColumn("Waktu  Transaksi");
        tbl_laporanTransak.setModel(dtm);
        
        String cari = txtf_cari1.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT\n" +
"                    detail_transaksi_barang.id_transaksi_barang AS id,'Barang' AS sumber, tb_barang.nama_barang AS nama, tb_transaksi_barang.total AS harga, tb_transaksi_barang.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_barang INNER JOIN tb_barang \n" +
"                    ON detail_transaksi_barang.id_barang = tb_barang.id_barang\n" +
"                    JOIN tb_transaksi_barang ON detail_transaksi_barang.id_transaksi_barang = tb_transaksi_barang.id_transaksi_barang\n" +
"                    UNION ALL\n" +
"                    SELECT\n" +
"                    detail_transaksi_gromming.id_transaksi_gromming AS id, 'Gromming' AS sumber, tb_gromming.nama_gromming AS nama, tb_transaksi_gromming.total AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
"                    ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
"                    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
"                    WHERE tb_transaksi_gromming.status = 'SUDAH DIAMBIL' AND tb_gromming.nama_gromming LIKE '%" + cari + "%'\n"+
"                    UNION ALL\n" +
"                    SELECT \n" +
"                    tb_transaksi_penitipan.id_transaksi_penitipan AS id, 'Penitipan' AS sumber, tb_penitipan.nama_penitipan AS nama, tb_transaksi_penitipan.total AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi\n" +
"                    FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
"                    ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan \n" +
"                    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
"                     WHERE tb_transaksi_penitipan.status = 'SUDAH DIAMBIL' AND tb_penitipan.nama_penitipan LIKE '%" + cari + "%'\n"+
"                    ORDER BY tanggal_transaksi DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("sumber"),
                    rs.getString("nama"),
                    rs.getString("harga"),
                    rs.getString("tanggal_transaksi")
                });
                tbl_laporanTransak.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void dataPelanggan1(){
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
        tbl_dataPelanggan1.setModel(dtm);
        
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
                tbl_dataPelanggan1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void barangTerlaris1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Terjual");
        tbl_barangTerlaris1.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_barang.id_barang AS id, tb_barang.nama_barang AS nama, tb_barang.kategori AS kategori,\n" +
"                        SUM(detail_transaksi_barang.qty) AS terjual \n" +
"                        FROM tb_barang INNER JOIN detail_transaksi_barang\n" +
"                        ON tb_barang.id_barang = detail_transaksi_barang.id_barang\n" +
"                        GROUP BY nama_barang \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getString("terjual"),
                });
                tbl_barangTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariBarang1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Terjual");
        tbl_barangTerlaris1.setModel(dtm);
        
        String cari = txtf_cariBarang1.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_barang.id_barang AS id, tb_barang.nama_barang AS nama, tb_barang.kategori AS kategori,\n" +
"                        SUM(detail_transaksi_barang.qty) AS terjual \n" +
"                        FROM tb_barang INNER JOIN detail_transaksi_barang\n" +
"                        ON tb_barang.id_barang = detail_transaksi_barang.id_barang\n" +
"                        where nama_barang like'%"+cari+"%' \n" +
"                        GROUP BY nama_barang \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getString("terjual"),
                });
                tbl_barangTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariDataPelanggan1(){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID Pelanggan");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Alamat");
        dtm.addColumn("No HP");
        tbl_dataPelanggan1.setModel(dtm);
        
        String cari = txtf_cariPelanggan1.getText();
        
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
                tbl_dataPelanggan1.setModel(dtm);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void grommingTerlaris1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Gromming");
        dtm.addColumn("Terjual");
        tbl_grommingTerlaris1.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_gromming.id_gromming AS id, tb_gromming.nama_gromming AS nama, \n" +
"                        SUM(detail_transaksi_gromming.qty) AS terjual \n" +
"                        FROM tb_gromming INNER JOIN detail_transaksi_gromming\n" +
"                        ON tb_gromming.id_gromming = detail_transaksi_gromming.id_gromming\n" +
"                        GROUP BY nama_gromming \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_grommingTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariGromming1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Gromming");
        dtm.addColumn("Terjual");
        tbl_grommingTerlaris1.setModel(dtm);
        
        String cari = txtf_cariGromming1.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_gromming.id_gromming AS id, tb_gromming.nama_gromming AS nama, \n" +
"                        SUM(detail_transaksi_gromming.qty) AS terjual \n" +
"                        FROM tb_gromming INNER JOIN detail_transaksi_gromming\n" +
"                        ON tb_gromming.id_gromming = detail_transaksi_gromming.id_gromming\n" +
"                        where nama_gromming like'%"+cari+"%' \n" +
"                        GROUP BY nama_gromming \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_grommingTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void penitipanTerlaris1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Penitipan");
        dtm.addColumn("Terjual");
        tbl_penitipanTerlaris1.setModel(dtm);
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_penitipan.id_penitipan AS id, tb_penitipan.nama_penitipan AS nama, \n" +
"                        SUM(detail_transaksi_penitipan.qty) AS terjual \n" +
"                        FROM tb_penitipan INNER JOIN detail_transaksi_penitipan\n" +
"                        ON tb_penitipan.id_penitipan = detail_transaksi_penitipan.id_penitipan\n" +
"                        GROUP BY nama_penitipan \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_penitipanTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cariPenitipan1(){
        DefaultTableModel dtm = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, 
        };
        
        @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        dtm.addColumn("ID Gromming");
        dtm.addColumn("Nama Penitipan");
        dtm.addColumn("Terjual");
        tbl_penitipanTerlaris1.setModel(dtm);
        
        String cari = txtf_cariPenitipan1.getText();
        
        try{
            Statement st = (Statement)config.configDB().createStatement();
            ResultSet rs = st.executeQuery(
            "SELECT tb_penitipan.id_penitipan AS id, tb_penitipan.nama_penitipan AS nama, \n" +
"                        SUM(detail_transaksi_penitipan.qty) AS terjual \n" +
"                        FROM tb_penitipan INNER JOIN detail_transaksi_penitipan\n" +
"                        ON tb_penitipan.id_penitipan = detail_transaksi_penitipan.id_penitipan\n" +
"                        where nama_penitipan like'%"+cari+"%' \n" +
"                        GROUP BY nama_penitipan \n" +
"                        ORDER BY terjual DESC");
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("terjual")
                });
                tbl_penitipanTerlaris1.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void hitungPenjumlahan1(){
        try {
            String query = "SELECT SUM(harga) AS total_harga FROM (" +
                        "SELECT " +
                        "detail_transaksi_barang.id_transaksi_barang AS id,'Barang' AS sumber, tb_barang.nama_barang AS nama, tb_transaksi_barang.total AS harga, tb_transaksi_barang.tanggal_transaksi AS tanggal_transaksi " +
                        "FROM detail_transaksi_barang INNER JOIN tb_barang " +
                        "ON detail_transaksi_barang.id_barang = tb_barang.id_barang " +
                        "JOIN tb_transaksi_barang ON detail_transaksi_barang.id_transaksi_barang = tb_transaksi_barang.id_transaksi_barang " +
                        "UNION ALL " +
                        "SELECT " +
                        "detail_transaksi_gromming.id_transaksi_gromming AS id, 'Gromming' AS sumber, tb_gromming.nama_gromming AS nama, tb_transaksi_gromming.total AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi " +
                        "FROM detail_transaksi_gromming INNER JOIN tb_gromming " +
                        "ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming " +
                        "JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming " +
                        "WHERE tb_transaksi_gromming.status = 'SUDAH DIAMBIL' " +
                        "UNION ALL " +
                        "SELECT " +
                        "tb_transaksi_penitipan.id_transaksi_penitipan AS id, 'Penitipan' AS sumber, tb_penitipan.nama_penitipan AS nama, tb_transaksi_penitipan.total AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi " +
                        "FROM detail_transaksi_penitipan INNER JOIN tb_penitipan " +
                        "ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan " +
                        "JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan " +
                        "WHERE tb_transaksi_penitipan.status = 'SUDAH DIAMBIL' " +
                        "ORDER BY tanggal_transaksi DESC) AS subquery";
                    Connection conn = config.configDB();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        int totalHarga = rs.getInt("total_harga");
                        // Lakukan operasi yang diperlukan dengan nilai totalHarga
                        // Misalnya, tampilkan di label
                        lbl_totalPendapatanSelesai.setText("Jumlah Pendapatan Selesai : Rp. " + totalHarga);
                    }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp_filterTanggal = new javax.swing.JPanel();
        lbl_filter = new javax.swing.JLabel();
        tggl_akhir = new com.toedter.calendar.JCalendar();
        txtf_tgglakhir = new javax.swing.JTextField();
        txtf_tgglAwal = new javax.swing.JTextField();
        tggl_awal = new com.toedter.calendar.JCalendar();
        lbl_close = new javax.swing.JLabel();
        bg_filterTanggal = new javax.swing.JLabel();
        popUp_keluar2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bg_keluar2 = new javax.swing.JLabel();
        popUp_dataPelanggan = new javax.swing.JPanel();
        spTabledataPelanggan = new javax.swing.JScrollPane();
        tbl_dataPelanggan = new com.raven.swing.Table();
        scrollBarCustom5 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariPelanggan = new javax.swing.JTextField();
        lbl_close4 = new javax.swing.JLabel();
        lbl_statiskPenitipan3 = new javax.swing.JLabel();
        lbl_statiskGromming3 = new javax.swing.JLabel();
        lbl_statiskBarang3 = new javax.swing.JLabel();
        bg_filterTanggal4 = new javax.swing.JLabel();
        popUp_dataStatistikPenitipan = new javax.swing.JPanel();
        spTableStatistik3 = new javax.swing.JScrollPane();
        tbl_penitipanTerlaris = new com.raven.swing.Table();
        scrollBarCustom4 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariPenitipan = new javax.swing.JTextField();
        lbl_close3 = new javax.swing.JLabel();
        lbl_statiskPenitipan2 = new javax.swing.JLabel();
        lbl_statiskGromming2 = new javax.swing.JLabel();
        lbl_statiskBarang2 = new javax.swing.JLabel();
        bg_filterTanggal3 = new javax.swing.JLabel();
        popUp_dataStatisBarang = new javax.swing.JPanel();
        spTableStatistik1 = new javax.swing.JScrollPane();
        tbl_barangTerlaris = new com.raven.swing.Table();
        scrollBarCustom2 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariBarang = new javax.swing.JTextField();
        lbl_close1 = new javax.swing.JLabel();
        lbl_statiskPenitipan1 = new javax.swing.JLabel();
        lbl_statiskGromming1 = new javax.swing.JLabel();
        lbl_statiskBarang1 = new javax.swing.JLabel();
        bg_statistikBarang = new javax.swing.JLabel();
        popUp_dataStatisGromming = new javax.swing.JPanel();
        spTableStatistik2 = new javax.swing.JScrollPane();
        tbl_grommingTerlaris = new com.raven.swing.Table();
        scrollBarCustom3 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariGromming = new javax.swing.JTextField();
        lbl_close2 = new javax.swing.JLabel();
        lbl_statiskPenitipan = new javax.swing.JLabel();
        lbl_statiskGromming = new javax.swing.JLabel();
        lbl_statiskBarang = new javax.swing.JLabel();
        bg_filterTanggal2 = new javax.swing.JLabel();
        panel_cari = new javax.swing.JPanel();
        txtf_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panel_laporanSelesai = new javax.swing.JPanel();
        roundedPanel2 = new panelRounded.RoundedPanel();
        spTable1 = new javax.swing.JScrollPane();
        tbl_laporanTransak = new com.raven.swing.Table();
        scrollBarCustom6 = new scrollbar.custom.ScrollBarCustom();
        lbl_totalPendapatanSelesai = new javax.swing.JLabel();
        lbl_Data1 = new javax.swing.JLabel();
        lbl_filterTangga1 = new javax.swing.JLabel();
        lbl_cari1 = new javax.swing.JLabel();
        lbl_statistik1 = new javax.swing.JLabel();
        laporanBerlangsung = new javax.swing.JLabel();
        bg1 = new javax.swing.JLabel();
        popUp_dataStatistikPenitipan1 = new javax.swing.JPanel();
        spTableStatistik4 = new javax.swing.JScrollPane();
        tbl_penitipanTerlaris1 = new com.raven.swing.Table();
        scrollBarCustom7 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariPenitipan1 = new javax.swing.JTextField();
        lbl_close6 = new javax.swing.JLabel();
        lbl_statiskPenitipan4 = new javax.swing.JLabel();
        lbl_statiskGromming4 = new javax.swing.JLabel();
        lbl_statiskBarang4 = new javax.swing.JLabel();
        bg_filterTanggal5 = new javax.swing.JLabel();
        popUp_dataPelanggan1 = new javax.swing.JPanel();
        spTabledataPelanggan1 = new javax.swing.JScrollPane();
        tbl_dataPelanggan1 = new com.raven.swing.Table();
        scrollBarCustom10 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariPelanggan1 = new javax.swing.JTextField();
        lbl_close9 = new javax.swing.JLabel();
        lbl_statiskPenitipan7 = new javax.swing.JLabel();
        lbl_statiskGromming7 = new javax.swing.JLabel();
        lbl_statiskBarang7 = new javax.swing.JLabel();
        bg_filterTanggal7 = new javax.swing.JLabel();
        popUp_dataStatisGromming1 = new javax.swing.JPanel();
        spTableStatistik6 = new javax.swing.JScrollPane();
        tbl_grommingTerlaris1 = new com.raven.swing.Table();
        scrollBarCustom9 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariGromming1 = new javax.swing.JTextField();
        lbl_close8 = new javax.swing.JLabel();
        lbl_statiskPenitipan6 = new javax.swing.JLabel();
        lbl_statiskGromming6 = new javax.swing.JLabel();
        lbl_statiskBarang6 = new javax.swing.JLabel();
        bg_filterTanggal6 = new javax.swing.JLabel();
        popUp_dataStatisBarang1 = new javax.swing.JPanel();
        spTableStatistik5 = new javax.swing.JScrollPane();
        tbl_barangTerlaris1 = new com.raven.swing.Table();
        scrollBarCustom8 = new scrollbar.custom.ScrollBarCustom();
        txtf_cariBarang1 = new javax.swing.JTextField();
        lbl_close7 = new javax.swing.JLabel();
        lbl_statiskPenitipan5 = new javax.swing.JLabel();
        lbl_statiskGromming5 = new javax.swing.JLabel();
        lbl_statiskBarang5 = new javax.swing.JLabel();
        bg_statistikBarang1 = new javax.swing.JLabel();
        popUp_filterTanggal1 = new javax.swing.JPanel();
        lbl_filter1 = new javax.swing.JLabel();
        tggl_akhir1 = new com.toedter.calendar.JCalendar();
        txtf_tgglakhir1 = new javax.swing.JTextField();
        txtf_tgglAwal1 = new javax.swing.JTextField();
        tggl_awal1 = new com.toedter.calendar.JCalendar();
        lbl_close5 = new javax.swing.JLabel();
        bg_filterTanggal1 = new javax.swing.JLabel();
        panel_cari1 = new javax.swing.JPanel();
        txtf_cari1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        roundedPanel1 = new panelRounded.RoundedPanel();
        spTable = new javax.swing.JScrollPane();
        tbl_trankSedangberjalan = new com.raven.swing.Table();
        scrollBarCustom1 = new scrollbar.custom.ScrollBarCustom();
        lbl_pendapatanBerlangsung = new javax.swing.JLabel();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
        lbl_formPegawai = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        form_laporanSelesai = new javax.swing.JLabel();
        lbl_cari = new javax.swing.JLabel();
        lbl_Data = new javax.swing.JLabel();
        lbl_statistik = new javax.swing.JLabel();
        lbl_filterTangga = new javax.swing.JLabel();
        Logout2 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        popUp_filterTanggal.setBackground(new Color(0,0,0,200));
        popUp_filterTanggal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_filterTanggalMouseEntered(evt);
            }
        });
        popUp_filterTanggal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterMouseClicked(evt);
            }
        });
        popUp_filterTanggal.add(lbl_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 740, 170, 30));

        tggl_akhir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tggl_akhirMouseClicked(evt);
            }
        });
        tggl_akhir.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tggl_akhirPropertyChange(evt);
            }
        });
        popUp_filterTanggal.add(tggl_akhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 460, 320, 250));

        txtf_tgglakhir.setBackground(new Color(0,0,0,0));
        txtf_tgglakhir.setBorder(null);
        popUp_filterTanggal.add(txtf_tgglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 420, 220, -1));

        txtf_tgglAwal.setBackground(new Color(0,0,0,0));
        txtf_tgglAwal.setBorder(null);
        popUp_filterTanggal.add(txtf_tgglAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 220, -1));

        tggl_awal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tggl_awalMouseClicked(evt);
            }
        });
        tggl_awal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tggl_awalPropertyChange(evt);
            }
        });
        popUp_filterTanggal.add(tggl_awal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 320, 250));

        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
        });
        popUp_filterTanggal.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 270, 40, 40));

        bg_filterTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Filter Tanggal.png"))); // NOI18N
        popUp_filterTanggal.add(bg_filterTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 830, 550));

        getContentPane().add(popUp_filterTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

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

        popUp_dataPelanggan.setBackground(new Color(0,0,0,200));
        popUp_dataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataPelangganMouseEntered(evt);
            }
        });
        popUp_dataPelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTabledataPelanggan.setBorder(null);

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
        spTabledataPelanggan.setViewportView(tbl_dataPelanggan);

        popUp_dataPelanggan.add(spTabledataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 300));
        popUp_dataPelanggan.add(scrollBarCustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariPelanggan.setBackground(new Color(0,0,0,0));
        txtf_cariPelanggan.setBorder(null);
        txtf_cariPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariPelangganMouseClicked(evt);
            }
        });
        txtf_cariPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariPelangganKeyReleased(evt);
            }
        });
        popUp_dataPelanggan.add(txtf_cariPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close4MouseClicked(evt);
            }
        });
        popUp_dataPelanggan.add(lbl_close4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));
        popUp_dataPelanggan.add(lbl_statiskPenitipan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming3MouseClicked(evt);
            }
        });
        popUp_dataPelanggan.add(lbl_statiskGromming3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang3MouseClicked(evt);
            }
        });
        popUp_dataPelanggan.add(lbl_statiskBarang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporan data pelanggan.png"))); // NOI18N
        popUp_dataPelanggan.add(bg_filterTanggal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        getContentPane().add(popUp_dataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataStatistikPenitipan.setBackground(new Color(0,0,0,200));
        popUp_dataStatistikPenitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatistikPenitipanMouseEntered(evt);
            }
        });
        popUp_dataStatistikPenitipan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik3.setBorder(null);

        tbl_penitipanTerlaris.setModel(new javax.swing.table.DefaultTableModel(
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
        spTableStatistik3.setViewportView(tbl_penitipanTerlaris);

        popUp_dataStatistikPenitipan.add(spTableStatistik3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatistikPenitipan.add(scrollBarCustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariPenitipan.setBackground(new Color(0,0,0,0));
        txtf_cariPenitipan.setBorder(null);
        txtf_cariPenitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariPenitipanMouseClicked(evt);
            }
        });
        txtf_cariPenitipan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariPenitipanKeyReleased(evt);
            }
        });
        popUp_dataStatistikPenitipan.add(txtf_cariPenitipan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close3MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan.add(lbl_close3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));
        popUp_dataStatistikPenitipan.add(lbl_statiskPenitipan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming2MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan.add(lbl_statiskGromming2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang2MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan.add(lbl_statiskBarang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik penitipan.png"))); // NOI18N
        popUp_dataStatistikPenitipan.add(bg_filterTanggal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        getContentPane().add(popUp_dataStatistikPenitipan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataStatisBarang.setBackground(new Color(0,0,0,200));
        popUp_dataStatisBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatisBarangMouseEntered(evt);
            }
        });
        popUp_dataStatisBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik1.setBorder(null);

        tbl_barangTerlaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Kategori", "Terjual"
            }
        ));
        spTableStatistik1.setViewportView(tbl_barangTerlaris);

        popUp_dataStatisBarang.add(spTableStatistik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatisBarang.add(scrollBarCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariBarang.setBackground(new Color(0,0,0,0));
        txtf_cariBarang.setBorder(null);
        txtf_cariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariBarangMouseClicked(evt);
            }
        });
        txtf_cariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariBarangKeyReleased(evt);
            }
        });
        popUp_dataStatisBarang.add(txtf_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close1MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang.add(lbl_close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));

        lbl_statiskPenitipan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskPenitipan1MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang.add(lbl_statiskPenitipan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming1MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang.add(lbl_statiskGromming1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang1MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang.add(lbl_statiskBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_statistikBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik barang.png"))); // NOI18N
        popUp_dataStatisBarang.add(bg_statistikBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        getContentPane().add(popUp_dataStatisBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataStatisGromming.setBackground(new Color(0,0,0,200));
        popUp_dataStatisGromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatisGrommingMouseEntered(evt);
            }
        });
        popUp_dataStatisGromming.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik2.setBorder(null);

        tbl_grommingTerlaris.setModel(new javax.swing.table.DefaultTableModel(
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
        spTableStatistik2.setViewportView(tbl_grommingTerlaris);

        popUp_dataStatisGromming.add(spTableStatistik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatisGromming.add(scrollBarCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariGromming.setBackground(new Color(0,0,0,0));
        txtf_cariGromming.setBorder(null);
        txtf_cariGromming.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariGrommingMouseClicked(evt);
            }
        });
        txtf_cariGromming.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariGrommingKeyReleased(evt);
            }
        });
        popUp_dataStatisGromming.add(txtf_cariGromming, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close2MouseClicked(evt);
            }
        });
        popUp_dataStatisGromming.add(lbl_close2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));

        lbl_statiskPenitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskPenitipanMouseClicked(evt);
            }
        });
        popUp_dataStatisGromming.add(lbl_statiskPenitipan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));
        popUp_dataStatisGromming.add(lbl_statiskGromming, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarangMouseClicked(evt);
            }
        });
        popUp_dataStatisGromming.add(lbl_statiskBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik gromming.png"))); // NOI18N
        popUp_dataStatisGromming.add(bg_filterTanggal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        getContentPane().add(popUp_dataStatisGromming, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

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

        getContentPane().add(panel_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 130, 350, 62));

        panel_laporanSelesai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable1.setBorder(null);

        tbl_laporanTransak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Nama Transaksi", "Tanggal Transaksi", "Total"
            }
        ));
        tbl_laporanTransak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_laporanTransakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_laporanTransakMouseEntered(evt);
            }
        });
        tbl_laporanTransak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_laporanTransakKeyReleased(evt);
            }
        });
        spTable1.setViewportView(tbl_laporanTransak);

        roundedPanel2.add(spTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1370, 680));
        roundedPanel2.add(scrollBarCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 160, 10, 180));

        lbl_totalPendapatanSelesai.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_totalPendapatanSelesai.setText(".");
        roundedPanel2.add(lbl_totalPendapatanSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 710, 470, 60));

        panel_laporanSelesai.add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 1410, 780));

        lbl_Data1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Data1MouseClicked(evt);
            }
        });
        panel_laporanSelesai.add(lbl_Data1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1820, 130, 60, 60));

        lbl_filterTangga1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterTangga1MouseClicked(evt);
            }
        });
        panel_laporanSelesai.add(lbl_filterTangga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 130, 60, 60));

        lbl_cari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cari1MouseClicked(evt);
            }
        });
        panel_laporanSelesai.add(lbl_cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 130, 60, 60));

        lbl_statistik1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statistik1MouseClicked(evt);
            }
        });
        panel_laporanSelesai.add(lbl_statistik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 130, 70, 60));

        laporanBerlangsung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanBerlangsungMouseClicked(evt);
            }
        });
        panel_laporanSelesai.add(laporanBerlangsung, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 180, 40));

        bg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporan-1.png"))); // NOI18N
        panel_laporanSelesai.add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        popUp_dataStatistikPenitipan1.setBackground(new Color(0,0,0,200));
        popUp_dataStatistikPenitipan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatistikPenitipan1MouseEntered(evt);
            }
        });
        popUp_dataStatistikPenitipan1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik4.setBorder(null);

        tbl_penitipanTerlaris1.setModel(new javax.swing.table.DefaultTableModel(
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
        spTableStatistik4.setViewportView(tbl_penitipanTerlaris1);

        popUp_dataStatistikPenitipan1.add(spTableStatistik4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatistikPenitipan1.add(scrollBarCustom7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariPenitipan1.setBackground(new Color(0,0,0,0));
        txtf_cariPenitipan1.setBorder(null);
        txtf_cariPenitipan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariPenitipan1MouseClicked(evt);
            }
        });
        txtf_cariPenitipan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariPenitipan1KeyReleased(evt);
            }
        });
        popUp_dataStatistikPenitipan1.add(txtf_cariPenitipan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close6MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan1.add(lbl_close6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));
        popUp_dataStatistikPenitipan1.add(lbl_statiskPenitipan4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming4MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan1.add(lbl_statiskGromming4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang4MouseClicked(evt);
            }
        });
        popUp_dataStatistikPenitipan1.add(lbl_statiskBarang4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik penitipan.png"))); // NOI18N
        popUp_dataStatistikPenitipan1.add(bg_filterTanggal5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        panel_laporanSelesai.add(popUp_dataStatistikPenitipan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataPelanggan1.setBackground(new Color(0,0,0,200));
        popUp_dataPelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataPelanggan1MouseEntered(evt);
            }
        });
        popUp_dataPelanggan1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTabledataPelanggan1.setBorder(null);

        tbl_dataPelanggan1.setModel(new javax.swing.table.DefaultTableModel(
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
        spTabledataPelanggan1.setViewportView(tbl_dataPelanggan1);

        popUp_dataPelanggan1.add(spTabledataPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 300));
        popUp_dataPelanggan1.add(scrollBarCustom10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariPelanggan1.setBackground(new Color(0,0,0,0));
        txtf_cariPelanggan1.setBorder(null);
        txtf_cariPelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariPelanggan1MouseClicked(evt);
            }
        });
        txtf_cariPelanggan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariPelanggan1KeyReleased(evt);
            }
        });
        popUp_dataPelanggan1.add(txtf_cariPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close9MouseClicked(evt);
            }
        });
        popUp_dataPelanggan1.add(lbl_close9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));
        popUp_dataPelanggan1.add(lbl_statiskPenitipan7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming7MouseClicked(evt);
            }
        });
        popUp_dataPelanggan1.add(lbl_statiskGromming7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang7MouseClicked(evt);
            }
        });
        popUp_dataPelanggan1.add(lbl_statiskBarang7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporan data pelanggan.png"))); // NOI18N
        popUp_dataPelanggan1.add(bg_filterTanggal7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        panel_laporanSelesai.add(popUp_dataPelanggan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataStatisGromming1.setBackground(new Color(0,0,0,200));
        popUp_dataStatisGromming1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatisGromming1MouseEntered(evt);
            }
        });
        popUp_dataStatisGromming1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik6.setBorder(null);

        tbl_grommingTerlaris1.setModel(new javax.swing.table.DefaultTableModel(
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
        spTableStatistik6.setViewportView(tbl_grommingTerlaris1);

        popUp_dataStatisGromming1.add(spTableStatistik6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatisGromming1.add(scrollBarCustom9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariGromming1.setBackground(new Color(0,0,0,0));
        txtf_cariGromming1.setBorder(null);
        txtf_cariGromming1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariGromming1MouseClicked(evt);
            }
        });
        txtf_cariGromming1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariGromming1KeyReleased(evt);
            }
        });
        popUp_dataStatisGromming1.add(txtf_cariGromming1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close8MouseClicked(evt);
            }
        });
        popUp_dataStatisGromming1.add(lbl_close8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));

        lbl_statiskPenitipan6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskPenitipan6MouseClicked(evt);
            }
        });
        popUp_dataStatisGromming1.add(lbl_statiskPenitipan6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));
        popUp_dataStatisGromming1.add(lbl_statiskGromming6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang6MouseClicked(evt);
            }
        });
        popUp_dataStatisGromming1.add(lbl_statiskBarang6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_filterTanggal6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik gromming.png"))); // NOI18N
        popUp_dataStatisGromming1.add(bg_filterTanggal6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        panel_laporanSelesai.add(popUp_dataStatisGromming1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_dataStatisBarang1.setBackground(new Color(0,0,0,200));
        popUp_dataStatisBarang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_dataStatisBarang1MouseEntered(evt);
            }
        });
        popUp_dataStatisBarang1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTableStatistik5.setBorder(null);

        tbl_barangTerlaris1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Kategori", "Terjual"
            }
        ));
        spTableStatistik5.setViewportView(tbl_barangTerlaris1);

        popUp_dataStatisBarang1.add(spTableStatistik5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 1340, 560));
        popUp_dataStatisBarang1.add(scrollBarCustom8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 700, 10, 180));

        txtf_cariBarang1.setBackground(new Color(0,0,0,0));
        txtf_cariBarang1.setBorder(null);
        txtf_cariBarang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_cariBarang1MouseClicked(evt);
            }
        });
        txtf_cariBarang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cariBarang1KeyReleased(evt);
            }
        });
        popUp_dataStatisBarang1.add(txtf_cariBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 260, 330, 40));

        lbl_close7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close7MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang1.add(lbl_close7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 160, 50, 50));

        lbl_statiskPenitipan5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskPenitipan5MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang1.add(lbl_statiskPenitipan5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 110, 30));

        lbl_statiskGromming5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskGromming5MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang1.add(lbl_statiskGromming5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 30));

        lbl_statiskBarang5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statiskBarang5MouseClicked(evt);
            }
        });
        popUp_dataStatisBarang1.add(lbl_statiskBarang5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, 30));

        bg_statistikBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data statistik barang.png"))); // NOI18N
        popUp_dataStatisBarang1.add(bg_statistikBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 1460, 820));

        panel_laporanSelesai.add(popUp_dataStatisBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        popUp_filterTanggal1.setBackground(new Color(0,0,0,200));
        popUp_filterTanggal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popUp_filterTanggal1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popUp_filterTanggal1MouseEntered(evt);
            }
        });
        popUp_filterTanggal1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_filter1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filter1MouseClicked(evt);
            }
        });
        popUp_filterTanggal1.add(lbl_filter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 740, 170, 30));

        tggl_akhir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tggl_akhir1MouseClicked(evt);
            }
        });
        tggl_akhir1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tggl_akhir1PropertyChange(evt);
            }
        });
        popUp_filterTanggal1.add(tggl_akhir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 460, 320, 250));

        txtf_tgglakhir1.setBackground(new Color(0,0,0,0));
        txtf_tgglakhir1.setBorder(null);
        popUp_filterTanggal1.add(txtf_tgglakhir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 420, 220, -1));

        txtf_tgglAwal1.setBackground(new Color(0,0,0,0));
        txtf_tgglAwal1.setBorder(null);
        popUp_filterTanggal1.add(txtf_tgglAwal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 220, -1));

        tggl_awal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tggl_awal1MouseClicked(evt);
            }
        });
        tggl_awal1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tggl_awal1PropertyChange(evt);
            }
        });
        popUp_filterTanggal1.add(tggl_awal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 320, 250));

        lbl_close5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close5MouseClicked(evt);
            }
        });
        popUp_filterTanggal1.add(lbl_close5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 270, 40, 40));

        bg_filterTanggal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Filter Tanggal.png"))); // NOI18N
        popUp_filterTanggal1.add(bg_filterTanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 830, 550));

        panel_laporanSelesai.add(popUp_filterTanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        panel_cari1.setBackground(new Color(0,0,0,0));
        panel_cari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_cari1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_cari1MouseEntered(evt);
            }
        });
        panel_cari1.setLayout(null);

        txtf_cari1.setBackground(new Color(0,0,0,0));
        txtf_cari1.setBorder(null);
        txtf_cari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_cari1KeyReleased(evt);
            }
        });
        panel_cari1.add(txtf_cari1);
        txtf_cari1.setBounds(30, 10, 250, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btn_search_clicked.png"))); // NOI18N
        panel_cari1.add(jLabel2);
        jLabel2.setBounds(16, 0, 331, 62);

        panel_laporanSelesai.add(panel_cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 130, 350, 62));

        getContentPane().add(panel_laporanSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable.setBorder(null);

        tbl_trankSedangberjalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Nama Transaksi", "Tanggal Transaksi", "Total"
            }
        ));
        tbl_trankSedangberjalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_trankSedangberjalanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_trankSedangberjalanMouseEntered(evt);
            }
        });
        tbl_trankSedangberjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_trankSedangberjalanKeyReleased(evt);
            }
        });
        spTable.setViewportView(tbl_trankSedangberjalan);

        roundedPanel1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1370, 640));
        roundedPanel1.add(scrollBarCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 160, 10, 180));

        lbl_pendapatanBerlangsung.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_pendapatanBerlangsung.setText(".");
        roundedPanel1.add(lbl_pendapatanBerlangsung, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 580, 60));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 1410, 770));

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

        form_laporanSelesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                form_laporanSelesaiMouseClicked(evt);
            }
        });
        getContentPane().add(form_laporanSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, 180, 40));

        lbl_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cariMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 130, 60, 60));

        lbl_Data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DataMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(1820, 130, 60, 60));

        lbl_statistik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statistikMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_statistik, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 130, 70, 60));

        lbl_filterTangga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_filterTanggaMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_filterTangga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 130, 60, 60));

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        getContentPane().add(Logout2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 970, 340, 60));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporan.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_laporanBerlangsung.MAXIMIZED_BOTH);
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

    private void form_laporanSelesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_form_laporanSelesaiMouseClicked
//        this.setVisible(false);
//        new form_laporanSelesai().setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_form_laporanSelesaiMouseClicked

    private void lbl_filterTanggaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterTanggaMouseClicked
        popUp_filterTanggal.setVisible(true);
    }//GEN-LAST:event_lbl_filterTanggaMouseClicked

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        popUp_filterTanggal.setVisible(false);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void popUp_filterTanggalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_filterTanggalMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_filterTanggalMouseEntered

    private void lbl_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterMouseClicked
        try {
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            Statement stm2 = conn.createStatement();

            String format_tgl = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(format_tgl);

            DefaultTableModel model = (DefaultTableModel) tbl_trankSedangberjalan.getModel();
            model.setRowCount(0);

            if (tggl_awal.getDate() != null && tggl_akhir.getDate() != null) {
                String tanggal1 = fm.format(tggl_awal.getDate());
                String tanggal2 = fm.format(tggl_akhir.getDate());

                String query = "SELECT id, nama_layanan, nama_pelanggan, harga, tanggal_transaksi, status " +
                               "FROM (" +
                               "    SELECT " +
                               "        detail_transaksi_gromming.id_transaksi_gromming AS id, " +
                               "        tb_gromming.nama_gromming AS nama_layanan, " +
                               "        tb_pelanggan.nama_pelanggan, " +
                               "        tb_transaksi_gromming.bayar AS harga, " +
                               "        tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi, " +
                               "        tb_transaksi_gromming.status AS status " +
                               "    FROM " +
                               "        detail_transaksi_gromming " +
                               "    INNER JOIN tb_gromming ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming " +
                               "    JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming " +
                               "    JOIN tb_pelanggan ON detail_transaksi_gromming.id_pelanggan = tb_pelanggan.id_pelanggan " +
                               "    WHERE " +
                               "    tb_transaksi_gromming.status = 'BELUM DIAMBIL' AND " +
                               "        tb_transaksi_gromming.tanggal_transaksi >= '" + tanggal1 + "' AND tb_transaksi_gromming.tanggal_transaksi <='" + tanggal2 + "' " +
                               "    UNION ALL " +
                               "    SELECT " +
                               "        tb_transaksi_penitipan.id_transaksi_penitipan AS id, " +
                               "        tb_penitipan.nama_penitipan AS nama_layanan, " +
                               "        tb_pelanggan.nama_pelanggan, " +
                               "        tb_transaksi_penitipan.bayar AS harga, " +
                               "        tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi, " +
                               "        tb_transaksi_penitipan.status AS status " +
                               "    FROM " +
                               "        detail_transaksi_penitipan " +
                               "    INNER JOIN tb_penitipan ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan " +
                               "    JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan " +
                               "    JOIN tb_pelanggan ON detail_transaksi_penitipan.id_pelanggan = tb_pelanggan.id_pelanggan " +
                               "    WHERE " +
                               "    tb_transaksi_penitipan.status = 'BELUM DIAMBIL' AND " +
                               "        tb_transaksi_penitipan.tanggal_transaksi >= '" + tanggal1 + "' AND  tb_transaksi_penitipan.tanggal_transaksi <='" + tanggal2 + "'" +
                               ") AS subquery " +
                               "ORDER BY tanggal_transaksi ASC";

                ResultSet result = stm.executeQuery(query);

                while (result.next()) {
                    // Ambil data dari ResultSet dan tambahkan ke model tabel
                    String id = result.getString("id");
                    String nama_layanan = result.getString("nama_layanan");
                    String nama_pelanggan = result.getString("nama_pelanggan");
                    String harga = result.getString("harga");
                    String tanggal_transaksi = result.getString("tanggal_transaksi");
                    String status = result.getString("status");

                    model.addRow(new Object[]{id, nama_layanan, nama_pelanggan, harga, tanggal_transaksi, status});
                }

                popUp_filterTanggal.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_lbl_filterMouseClicked

    private void tggl_awalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tggl_awalMouseClicked
        
    }//GEN-LAST:event_tggl_awalMouseClicked

    private void tggl_awalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tggl_awalPropertyChange
        Date selectedDate = tggl_awal.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(selectedDate);
            txtf_tgglAwal.setText(dateString);
            
            
    }//GEN-LAST:event_tggl_awalPropertyChange

    private void tggl_akhirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tggl_akhirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tggl_akhirMouseClicked

    private void tggl_akhirPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tggl_akhirPropertyChange
        Date selectedDate = tggl_akhir.getDate();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat1.format(selectedDate);
            txtf_tgglakhir.setText(dateString);
    }//GEN-LAST:event_tggl_akhirPropertyChange

    private void popUp_dataStatisBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatisBarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatisBarangMouseEntered

    private void lbl_statistikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statistikMouseClicked
        popUp_dataStatisBarang.setVisible(true);
    }//GEN-LAST:event_lbl_statistikMouseClicked

    private void lbl_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close1MouseClicked
        popUp_dataStatisBarang.setVisible(false);
    }//GEN-LAST:event_lbl_close1MouseClicked

    private void txtf_cariBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariBarangMouseClicked
        
    }//GEN-LAST:event_txtf_cariBarangMouseClicked

    private void txtf_cariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariBarangKeyReleased
        cariBarang();
    }//GEN-LAST:event_txtf_cariBarangKeyReleased

    private void txtf_cariGrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariGrommingMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariGrommingMouseClicked

    private void txtf_cariGrommingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariGrommingKeyReleased
        cariGromming();
    }//GEN-LAST:event_txtf_cariGrommingKeyReleased

    private void lbl_close2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close2MouseClicked
        popUp_dataStatisGromming.setVisible(false);
    }//GEN-LAST:event_lbl_close2MouseClicked

    private void popUp_dataStatisGrommingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatisGrommingMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatisGrommingMouseEntered

    private void lbl_statiskBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarangMouseClicked
        popUp_dataStatisBarang.setVisible(true);
        popUp_dataStatisGromming.setVisible(false);
    }//GEN-LAST:event_lbl_statiskBarangMouseClicked

    private void lbl_statiskBarang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskBarang1MouseClicked

    private void lbl_statiskGromming1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming1MouseClicked
        popUp_dataStatisBarang.setVisible(false);
        popUp_dataStatisGromming.setVisible(true);
    }//GEN-LAST:event_lbl_statiskGromming1MouseClicked

    private void txtf_cariPenitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariPenitipanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariPenitipanMouseClicked

    private void txtf_cariPenitipanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariPenitipanKeyReleased
        cariPenitipan();
    }//GEN-LAST:event_txtf_cariPenitipanKeyReleased

    private void lbl_close3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close3MouseClicked
        popUp_dataStatistikPenitipan.setVisible(false);
    }//GEN-LAST:event_lbl_close3MouseClicked

    private void lbl_statiskBarang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang2MouseClicked
        popUp_dataStatisBarang.setVisible(true);
        popUp_dataStatistikPenitipan.setVisible(false);
    }//GEN-LAST:event_lbl_statiskBarang2MouseClicked

    private void popUp_dataStatistikPenitipanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatistikPenitipanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatistikPenitipanMouseEntered

    private void lbl_statiskGromming2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming2MouseClicked
        popUp_dataStatisGromming.setVisible(true);
        popUp_dataStatistikPenitipan.setVisible(false);
    }//GEN-LAST:event_lbl_statiskGromming2MouseClicked

    private void lbl_statiskPenitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskPenitipanMouseClicked
        popUp_dataStatistikPenitipan.setVisible(true);
        popUp_dataStatisGromming.setVisible(false);
    }//GEN-LAST:event_lbl_statiskPenitipanMouseClicked

    private void lbl_statiskPenitipan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskPenitipan1MouseClicked
        popUp_dataStatisBarang.setVisible(false);
        popUp_dataStatistikPenitipan.setVisible(true);
    }//GEN-LAST:event_lbl_statiskPenitipan1MouseClicked

    private void txtf_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariKeyReleased
        cariTranksaksi();
    }//GEN-LAST:event_txtf_cariKeyReleased

    private void panel_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseClicked
        panel_cari.setVisible(false);
    }//GEN-LAST:event_panel_cariMouseClicked

    private void panel_cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cariMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_panel_cariMouseEntered

    private void lbl_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cariMouseClicked
        panel_cari.setVisible(true);
    }//GEN-LAST:event_lbl_cariMouseClicked

    private void txtf_cariPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariPelangganMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariPelangganMouseClicked

    private void txtf_cariPelangganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariPelangganKeyReleased
        cariDataPelanggan();
    }//GEN-LAST:event_txtf_cariPelangganKeyReleased

    private void lbl_close4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close4MouseClicked
        popUp_dataPelanggan.setVisible(false);
    }//GEN-LAST:event_lbl_close4MouseClicked

    private void lbl_statiskGromming3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskGromming3MouseClicked

    private void lbl_statiskBarang3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskBarang3MouseClicked

    private void popUp_dataPelangganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataPelangganMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataPelangganMouseEntered

    private void lbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DataMouseClicked
        popUp_dataPelanggan.setVisible(true);
    }//GEN-LAST:event_lbl_DataMouseClicked

    private void tbl_laporanTransakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_laporanTransakMouseClicked

    }//GEN-LAST:event_tbl_laporanTransakMouseClicked

    private void tbl_laporanTransakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_laporanTransakMouseEntered

    }//GEN-LAST:event_tbl_laporanTransakMouseEntered

    private void tbl_laporanTransakKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_laporanTransakKeyReleased

    }//GEN-LAST:event_tbl_laporanTransakKeyReleased

    private void lbl_filterTangga1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filterTangga1MouseClicked
        popUp_filterTanggal1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_filterTangga1MouseClicked

    private void lbl_filter1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_filter1MouseClicked
        try {
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();

            String format_tgl = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(format_tgl);

            DefaultTableModel model = (DefaultTableModel) tbl_laporanTransak.getModel();
            model.setRowCount(0);

            if (tggl_awal1.getDate() != null && tggl_akhir1.getDate() != null) {
                String tanggal1 = fm.format(tggl_awal1.getDate());
                String tanggal2 = fm.format(tggl_akhir1.getDate());

                String query = "SELECT " +
                "detail_transaksi_barang.id_transaksi_barang AS id, 'Barang' AS sumber, tb_barang.nama_barang AS nama, tb_transaksi_barang.total AS harga, tb_transaksi_barang.tanggal_transaksi AS tanggal_transaksi " +
                "FROM detail_transaksi_barang " +
                "INNER JOIN tb_barang ON detail_transaksi_barang.id_barang = tb_barang.id_barang " +
                "JOIN tb_transaksi_barang ON detail_transaksi_barang.id_transaksi_barang = tb_transaksi_barang.id_transaksi_barang " +
                "WHERE tb_transaksi_barang.tanggal_transaksi >= '" + tanggal1 + "' AND tb_transaksi_barang.tanggal_transaksi <= '" + tanggal2 + "' " +
                "UNION ALL " +
                "SELECT " +
                "detail_transaksi_gromming.id_transaksi_gromming AS id, 'Gromming' AS sumber, tb_gromming.nama_gromming AS nama, tb_transaksi_gromming.total AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi " +
                "FROM detail_transaksi_gromming " +
                "INNER JOIN tb_gromming ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming " +
                "JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming " +
                "WHERE tb_transaksi_gromming.tanggal_transaksi >= '" + tanggal1 + "' AND tb_transaksi_gromming.tanggal_transaksi <= '" + tanggal2 + "' AND tb_transaksi_gromming.status = 'SUDAH DIAMBIL'" +
                "UNION ALL " +
                "SELECT " +
                "tb_transaksi_penitipan.id_transaksi_penitipan AS id, 'Penitipan' AS sumber, tb_penitipan.nama_penitipan AS nama, tb_transaksi_penitipan.total AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi " +
                "FROM detail_transaksi_penitipan " +
                "INNER JOIN tb_penitipan ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan " +
                "JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan " +
                "WHERE tb_transaksi_penitipan.tanggal_transaksi >= '" + tanggal1 + "' AND tb_transaksi_penitipan.tanggal_transaksi <= '" + tanggal2 + "' AND tb_transaksi_penitipan.status = 'SUDAH DIAMBIL'" +
                "ORDER BY tanggal_transaksi DESC";

                ResultSet result = stm.executeQuery(query);

                while (result.next()) {
                    // Ambil data dari ResultSet dan tambahkan ke model tabel
                    String id = result.getString("id");
                    String sumber = result.getString("sumber");
                    String nama = result.getString("nama");
                    String harga = result.getString("harga");
                    String tanggal_transaksi = result.getString("tanggal_transaksi");

                    model.addRow(new Object[]{id, sumber, nama, harga, tanggal_transaksi});
                }

                popUp_filterTanggal1.setVisible(false);
                panel_laporanSelesai.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lbl_filter1MouseClicked

    private void tggl_akhir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tggl_akhir1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tggl_akhir1MouseClicked

    private void tggl_akhir1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tggl_akhir1PropertyChange
        Date selectedDate = tggl_akhir1.getDate();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat1.format(selectedDate);
        txtf_tgglakhir1.setText(dateString);
    }//GEN-LAST:event_tggl_akhir1PropertyChange

    private void tggl_awal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tggl_awal1MouseClicked

    }//GEN-LAST:event_tggl_awal1MouseClicked

    private void tggl_awal1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tggl_awal1PropertyChange
        Date selectedDate = tggl_awal1.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(selectedDate);
        txtf_tgglAwal1.setText(dateString);
    }//GEN-LAST:event_tggl_awal1PropertyChange

    private void lbl_close5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close5MouseClicked
        popUp_filterTanggal1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_close5MouseClicked

    private void popUp_filterTanggal1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_filterTanggal1MouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_popUp_filterTanggal1MouseEntered

    private void popUp_filterTanggal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_filterTanggal1MouseClicked
        
    }//GEN-LAST:event_popUp_filterTanggal1MouseClicked

    private void txtf_cariPenitipan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariPenitipan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariPenitipan1MouseClicked

    private void txtf_cariPenitipan1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariPenitipan1KeyReleased
        cariPenitipan1();
    }//GEN-LAST:event_txtf_cariPenitipan1KeyReleased

    private void lbl_close6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close6MouseClicked
        popUp_dataStatistikPenitipan1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_close6MouseClicked

    private void lbl_statiskGromming4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming4MouseClicked
        popUp_dataStatisGromming1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
        popUp_dataStatistikPenitipan1.setVisible(false);
    }//GEN-LAST:event_lbl_statiskGromming4MouseClicked

    private void lbl_statiskBarang4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang4MouseClicked
        popUp_dataStatisBarang1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
        popUp_dataStatistikPenitipan1.setVisible(false);
    }//GEN-LAST:event_lbl_statiskBarang4MouseClicked

    private void popUp_dataStatistikPenitipan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatistikPenitipan1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatistikPenitipan1MouseEntered

    private void txtf_cariBarang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariBarang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariBarang1MouseClicked

    private void txtf_cariBarang1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariBarang1KeyReleased
        cariBarang1();
    }//GEN-LAST:event_txtf_cariBarang1KeyReleased

    private void lbl_close7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close7MouseClicked
        popUp_dataStatisBarang1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_close7MouseClicked

    private void lbl_statiskPenitipan5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskPenitipan5MouseClicked
        popUp_dataStatistikPenitipan1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
        popUp_dataStatisBarang1.setVisible(false);
    }//GEN-LAST:event_lbl_statiskPenitipan5MouseClicked

    private void lbl_statiskGromming5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming5MouseClicked
        popUp_dataStatisGromming1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
        popUp_dataStatisBarang1.setVisible(false);
    }//GEN-LAST:event_lbl_statiskGromming5MouseClicked

    private void lbl_statiskBarang5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskBarang5MouseClicked

    private void popUp_dataStatisBarang1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatisBarang1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatisBarang1MouseEntered

    private void txtf_cariGromming1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariGromming1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariGromming1MouseClicked

    private void txtf_cariGromming1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariGromming1KeyReleased
        cariGromming1();
    }//GEN-LAST:event_txtf_cariGromming1KeyReleased

    private void lbl_close8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close8MouseClicked
        popUp_dataStatisGromming1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_close8MouseClicked

    private void lbl_statiskPenitipan6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskPenitipan6MouseClicked
        popUp_dataStatisGromming1.setVisible(false);
        popUp_dataStatistikPenitipan1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_statiskPenitipan6MouseClicked

    private void lbl_statiskBarang6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang6MouseClicked
        popUp_dataStatisGromming1.setVisible(false);
        popUp_dataStatisBarang1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_statiskBarang6MouseClicked

    private void popUp_dataStatisGromming1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataStatisGromming1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataStatisGromming1MouseEntered

    private void txtf_cariPelanggan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_cariPelanggan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_cariPelanggan1MouseClicked

    private void txtf_cariPelanggan1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cariPelanggan1KeyReleased
        cariDataPelanggan1();
    }//GEN-LAST:event_txtf_cariPelanggan1KeyReleased

    private void lbl_close9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close9MouseClicked
        popUp_dataPelanggan1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_close9MouseClicked

    private void lbl_statiskGromming7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskGromming7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskGromming7MouseClicked

    private void lbl_statiskBarang7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statiskBarang7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statiskBarang7MouseClicked

    private void popUp_dataPelanggan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUp_dataPelanggan1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_popUp_dataPelanggan1MouseEntered

    private void lbl_Data1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Data1MouseClicked
        popUp_dataPelanggan1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_Data1MouseClicked

    private void lbl_statistik1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statistik1MouseClicked
        popUp_dataStatisBarang1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_statistik1MouseClicked

    private void laporanBerlangsungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanBerlangsungMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsung().setVisible(true);
    }//GEN-LAST:event_laporanBerlangsungMouseClicked

    private void txtf_cari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_cari1KeyReleased
        CarilaporanSelesai();
    }//GEN-LAST:event_txtf_cari1KeyReleased

    private void panel_cari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cari1MouseClicked
        panel_cari1.setVisible(false);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_panel_cari1MouseClicked

    private void panel_cari1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cari1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_cari1MouseEntered

    private void lbl_cari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cari1MouseClicked
        panel_cari1.setVisible(true);
        panel_laporanSelesai.setVisible(true);
    }//GEN-LAST:event_lbl_cari1MouseClicked

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

    private void tbl_trankSedangberjalanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_trankSedangberjalanKeyReleased

    }//GEN-LAST:event_tbl_trankSedangberjalanKeyReleased

    private void tbl_trankSedangberjalanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_trankSedangberjalanMouseEntered

    }//GEN-LAST:event_tbl_trankSedangberjalanMouseEntered

    private void tbl_trankSedangberjalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_trankSedangberjalanMouseClicked

    }//GEN-LAST:event_tbl_trankSedangberjalanMouseClicked

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
            java.util.logging.Logger.getLogger(form_laporanBerlangsung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_laporanBerlangsung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_laporanBerlangsung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_laporanBerlangsung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_laporanBerlangsung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout2;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg1;
    private javax.swing.JLabel bg_filterTanggal;
    private javax.swing.JLabel bg_filterTanggal1;
    private javax.swing.JLabel bg_filterTanggal2;
    private javax.swing.JLabel bg_filterTanggal3;
    private javax.swing.JLabel bg_filterTanggal4;
    private javax.swing.JLabel bg_filterTanggal5;
    private javax.swing.JLabel bg_filterTanggal6;
    private javax.swing.JLabel bg_filterTanggal7;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel bg_statistikBarang;
    private javax.swing.JLabel bg_statistikBarang1;
    private javax.swing.JLabel form_laporanSelesai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel laporanBerlangsung;
    private javax.swing.JLabel lbl_Data;
    private javax.swing.JLabel lbl_Data1;
    private javax.swing.JLabel lbl_cari;
    private javax.swing.JLabel lbl_cari1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_close1;
    private javax.swing.JLabel lbl_close2;
    private javax.swing.JLabel lbl_close3;
    private javax.swing.JLabel lbl_close4;
    private javax.swing.JLabel lbl_close5;
    private javax.swing.JLabel lbl_close6;
    private javax.swing.JLabel lbl_close7;
    private javax.swing.JLabel lbl_close8;
    private javax.swing.JLabel lbl_close9;
    private javax.swing.JLabel lbl_filter;
    private javax.swing.JLabel lbl_filter1;
    private javax.swing.JLabel lbl_filterTangga;
    private javax.swing.JLabel lbl_filterTangga1;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formPegawai;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lbl_pendapatanBerlangsung;
    private javax.swing.JLabel lbl_statiskBarang;
    private javax.swing.JLabel lbl_statiskBarang1;
    private javax.swing.JLabel lbl_statiskBarang2;
    private javax.swing.JLabel lbl_statiskBarang3;
    private javax.swing.JLabel lbl_statiskBarang4;
    private javax.swing.JLabel lbl_statiskBarang5;
    private javax.swing.JLabel lbl_statiskBarang6;
    private javax.swing.JLabel lbl_statiskBarang7;
    private javax.swing.JLabel lbl_statiskGromming;
    private javax.swing.JLabel lbl_statiskGromming1;
    private javax.swing.JLabel lbl_statiskGromming2;
    private javax.swing.JLabel lbl_statiskGromming3;
    private javax.swing.JLabel lbl_statiskGromming4;
    private javax.swing.JLabel lbl_statiskGromming5;
    private javax.swing.JLabel lbl_statiskGromming6;
    private javax.swing.JLabel lbl_statiskGromming7;
    private javax.swing.JLabel lbl_statiskPenitipan;
    private javax.swing.JLabel lbl_statiskPenitipan1;
    private javax.swing.JLabel lbl_statiskPenitipan2;
    private javax.swing.JLabel lbl_statiskPenitipan3;
    private javax.swing.JLabel lbl_statiskPenitipan4;
    private javax.swing.JLabel lbl_statiskPenitipan5;
    private javax.swing.JLabel lbl_statiskPenitipan6;
    private javax.swing.JLabel lbl_statiskPenitipan7;
    private javax.swing.JLabel lbl_statistik;
    private javax.swing.JLabel lbl_statistik1;
    private javax.swing.JLabel lbl_totalPendapatanSelesai;
    private javax.swing.JPanel panel_cari;
    private javax.swing.JPanel panel_cari1;
    private javax.swing.JPanel panel_laporanSelesai;
    private javax.swing.JPanel popUp_dataPelanggan;
    private javax.swing.JPanel popUp_dataPelanggan1;
    private javax.swing.JPanel popUp_dataStatisBarang;
    private javax.swing.JPanel popUp_dataStatisBarang1;
    private javax.swing.JPanel popUp_dataStatisGromming;
    private javax.swing.JPanel popUp_dataStatisGromming1;
    private javax.swing.JPanel popUp_dataStatistikPenitipan;
    private javax.swing.JPanel popUp_dataStatistikPenitipan1;
    private javax.swing.JPanel popUp_filterTanggal;
    private javax.swing.JPanel popUp_filterTanggal1;
    private javax.swing.JPanel popUp_keluar2;
    private panelRounded.RoundedPanel roundedPanel1;
    private panelRounded.RoundedPanel roundedPanel2;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom10;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom2;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom3;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom4;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom5;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom6;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom7;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom8;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom9;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private javax.swing.JScrollPane spTableStatistik1;
    private javax.swing.JScrollPane spTableStatistik2;
    private javax.swing.JScrollPane spTableStatistik3;
    private javax.swing.JScrollPane spTableStatistik4;
    private javax.swing.JScrollPane spTableStatistik5;
    private javax.swing.JScrollPane spTableStatistik6;
    private javax.swing.JScrollPane spTabledataPelanggan;
    private javax.swing.JScrollPane spTabledataPelanggan1;
    private com.raven.swing.Table tbl_barangTerlaris;
    private com.raven.swing.Table tbl_barangTerlaris1;
    private com.raven.swing.Table tbl_dataPelanggan;
    private com.raven.swing.Table tbl_dataPelanggan1;
    private com.raven.swing.Table tbl_grommingTerlaris;
    private com.raven.swing.Table tbl_grommingTerlaris1;
    private com.raven.swing.Table tbl_laporanTransak;
    private com.raven.swing.Table tbl_penitipanTerlaris;
    private com.raven.swing.Table tbl_penitipanTerlaris1;
    private com.raven.swing.Table tbl_trankSedangberjalan;
    private com.toedter.calendar.JCalendar tggl_akhir;
    private com.toedter.calendar.JCalendar tggl_akhir1;
    private com.toedter.calendar.JCalendar tggl_awal;
    private com.toedter.calendar.JCalendar tggl_awal1;
    private javax.swing.JTextField txtf_cari;
    private javax.swing.JTextField txtf_cari1;
    private javax.swing.JTextField txtf_cariBarang;
    private javax.swing.JTextField txtf_cariBarang1;
    private javax.swing.JTextField txtf_cariGromming;
    private javax.swing.JTextField txtf_cariGromming1;
    private javax.swing.JTextField txtf_cariPelanggan;
    private javax.swing.JTextField txtf_cariPelanggan1;
    private javax.swing.JTextField txtf_cariPenitipan;
    private javax.swing.JTextField txtf_cariPenitipan1;
    private javax.swing.JTextField txtf_tgglAwal;
    private javax.swing.JTextField txtf_tgglAwal1;
    private javax.swing.JTextField txtf_tgglakhir;
    private javax.swing.JTextField txtf_tgglakhir1;
    // End of variables declaration//GEN-END:variables
}
