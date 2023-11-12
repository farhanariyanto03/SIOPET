/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siopet;

import Logic.LogicLogin;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import static notification.MainForm.lbl_jumlah_notif;

/**
 *
 * @author ASUS
 */
public class form_dashboard extends javax.swing.JFrame {

    /**
     * Creates new form form_dashboard1
     */
    notifikasi notif; 
    public form_dashboard() {
        initComponents();
        load_data();
        setIconInitMonth();
        setInitRankLayanan();
        setInitRankBarang();
        setInitNewTransaksi();
        setJMLpegawai();
        popUp_keluar.setVisible(false);
        notif = new notifikasi();
        pn_notif.add(notif);
        pn_notif.setVisible(false);
        jumlahNotif();
    }

    public void jumlahNotif(){
        JPanel container = new JPanel();
        container.setLayout(null);
        
        int jumlahNotifBaru = 0;
        try {
            String sql2 = "select * from tb_barang where stok = 0";
            String sql3 = "select * from tb_barang where stok <= 5 and stok > 0";
            java.sql.Connection conn=(Connection)notification.config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.Statement stm2=conn.createStatement();
            java.sql.Statement stm3=conn.createStatement();
            java.sql.ResultSet res2=stm2.executeQuery(sql2);
            java.sql.ResultSet res3=stm3.executeQuery(sql3);
      
            if (res3.next()) {
                do {
                    jumlahNotifBaru++;  
                } while (res3.next());
            }
            if (res2.next()) {
                do {
                    jumlahNotifBaru++; 
                } while (res2.next());
            }
            
        } catch (Exception e) {
        }
        
        jumlah_notif.setText(Integer.toString(jumlahNotifBaru));
    }
    
    private void load_data(){
        lblnama.setText(LogicLogin.nama);
    }
    
    private void setJMLpegawai(){
        Statement st = null;
        ResultSet rs = null;
        try {
            java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
            //pegawai
            rs = st.executeQuery(
                    "SELECT COUNT(DISTINCT id_pegawai) as jml_pegawai FROM tb_pegawai"
            );
            int pegawai = rs.next() ? rs.getInt("jml_pegawai") : 0;
            txt_jmlPegawai.setText(Integer.toString(pegawai));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void setIconInitAll(){
        Statement st = null;
        ResultSet rs = null;
        try {
            java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
            //iconClient
            rs = st.executeQuery(
                    "SELECT \n" +
                    "barang + gromming + penitipan AS client\n" +
                    "FROM\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang) AS tb_barang,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming) AS tb_gromming,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan) AS tb_penitipan"
            );
            int client = rs.next() ? rs.getInt("client") : 0;
            txt_client.setText(Integer.toString(client));
            //iconTransaksi
            rs = st.executeQuery(
                    "SELECT SUM(total_rows) as transaksi FROM\n" +
                    "(SELECT COUNT(*) as total_rows FROM tb_transaksi_barang union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan) as total_counts;"
                );
            int transaksi = rs.next() ? rs.getInt("transaksi") : 0;
            txt_transaksi.setText((Integer.toString(transaksi)));
            //iconJumlahPendapatan
            rs = st.executeQuery(
                    "SELECT\n" +
                        "barang + gromming + penitipan as pendapatan \n" +
                    "FROM\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS barang FROM tb_transaksi_barang) AS tb_barang,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS gromming FROM tb_transaksi_gromming) AS tb_gromming,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS penitipan FROM tb_transaksi_penitipan) AS tb_penitipan"
            );
            int Pendapatan = rs.next() ? rs.getInt("pendapatan") : 0;
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String totalFormatted = decimalFormat.format(Pendapatan);
            txt_pendapatan.setText(totalFormatted);
            //iconLaba
//            rs = st.executeQuery(
//                    "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba \n" +
//                    "FROM tb_transaksi_barang\n" +
//                    "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang"
//            );
//            double laba = rs.next() ? rs.getDouble("laba") : 0;
//            String labaString = String.format("%.1f", laba); //menampilkan angka desimal hanya dengan 1 digit di belakang koma
//            txt_laba.setText(labaString + "%");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setIconInitDay() { 
        Statement st = null;
        ResultSet rs = null;
        try {
            java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
//            iconClient
            rs = st.executeQuery(
                    "SELECT\n" +
                    "barang + gromming + penitipan AS client_hari_ini\n" +
                    "FROM\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang WHERE DATE(tanggal_transaksi) >= CURDATE()) AS tb_barang,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming WHERE DATE(tanggal_transaksi) >= CURDATE()) AS tb_gromming,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan WHERE DATE(tanggal_transaksi) >= CURDATE()) AS tb_penitipan  "
            );
            int clientHariIni = rs.next() ? rs.getInt("client_hari_ini") : 0;
            rs = st.executeQuery(
                    "SELECT\n" +
                    "barang + gromming + penitipan AS client_kemarin\n" +
                    "FROM\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_barang,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_gromming,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_penitipan"
                    );
            int clientKemarin = rs.next() ? rs.getInt("client_kemarin") : 0;
            if (clientHariIni > clientKemarin) {
                icon1.setVisible(true);
                icon2.setVisible(false);
            } else {
                icon1.setVisible(false);
                icon2.setVisible(true);
            }
            txt_client.setText(Integer.toString(clientHariIni));
            //iconTransaksi
            rs = st.executeQuery(
                    "SELECT SUM(total_rows) as transaksi_hariIni FROM\n" +
                    "(SELECT COUNT(*) as total_rows FROM tb_transaksi_barang WHERE tanggal_transaksi >= CURDATE() union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming WHERE tanggal_transaksi >= CURDATE() union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan WHERE tanggal_transaksi >= CURDATE()) as total_counts;"
                );
            int transaksiHariIni = rs.next() ? rs.getInt("transaksi_hariIni") : 0;
            rs = st.executeQuery(
                    "SELECT SUM(total_rows) as transaksi_kemarin FROM\n" +
                    "(SELECT COUNT(*) as total_rows FROM tb_transaksi_barang WHERE tanggal_transaksi = DATE_SUB(CURDATE(), INTERVAL 1 DAY) union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming WHERE tanggal_transaksi = DATE_SUB(CURDATE(), INTERVAL 1 DAY) union all \n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan WHERE tanggal_transaksi = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) as total_counts;"
            );
            int transaksiKemarin = rs.next() ? rs.getInt("transaksi_kemarin") : 0;
            if (transaksiHariIni > transaksiKemarin) {
                icon3.setVisible(true);
                icon4.setVisible(false);
            } else {
                icon3.setVisible(false);
                icon4.setVisible(true);
            }
            txt_transaksi.setText((Integer.toString(transaksiHariIni)));
            //iconJumlahPendapatan
            rs = st.executeQuery(
                    "SELECT\n" +
                    "     barang + gromming + penitipan as pendapatan_hari_ini \n" +
                    "FROM\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS barang FROM tb_transaksi_barang "
                            + "WHERE DATE(tanggal_transaksi) = CURDATE()) AS tb_barang,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS gromming FROM tb_transaksi_gromming"
                            + " WHERE DATE(tanggal_transaksi) = CURDATE()) AS tb_gromming,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS penitipan FROM tb_transaksi_penitipan "
                            + "WHERE DATE(tanggal_transaksi) = CURDATE()) AS tb_penitipan"
            );
            int PendapatanHariIni = rs.next() ? rs.getInt("pendapatan_hari_ini") : 0;
            rs = st.executeQuery(
                    "SELECT\n" +
                    "     barang + gromming + penitipan as pendapatan_kemarin \n" +
                    "FROM\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS barang FROM tb_transaksi_barang "
                            + "WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_barang,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS gromming FROM tb_transaksi_gromming "
                            + "WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_gromming,\n" +
                    "    (SELECT COALESCE(SUM(total), 0) AS penitipan FROM tb_transaksi_penitipan "
                            + "WHERE DATE(tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS tb_penitipan"
            );
            int PendapatanKemarin = rs.next() ? rs.getInt("pendapatan_kemarin") : 0;
            if (PendapatanHariIni > PendapatanKemarin) {
                icon5.setVisible(true);
                icon6.setVisible(false);
            } else {
                icon5.setVisible(false);
                icon6.setVisible(true);
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String totalFormatted = decimalFormat.format(PendapatanHariIni);
            txt_pendapatan.setText(totalFormatted);
            //iconLaba
//            rs = st.executeQuery(
//                    "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_hari_ini \n" +
//                    "FROM tb_transaksi_barang\n" +
//                    "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                    "WHERE DATE(tb_transaksi_barang.tanggal_transaksi) >= CURDATE();"
//            );
//            double LabaHariIni = rs.next() ? rs.getDouble("laba_hari_ini") : 0;
//            rs = st.executeQuery(
//                    "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_kemarin \n" +
//                    "FROM tb_transaksi_barang\n" +
//                    "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                    "WHERE DATE(tb_transaksi_barang.tanggal_transaksi) = DATE_SUB(CURDATE(), INTERVAL 1 DAY);"
//            );
//            double LabaKemarin = rs.next() ? rs.getDouble("laba_kemarin") : 0;
//            if (LabaHariIni > LabaKemarin){
//                icon7.setVisible(true);
//                icon8.setVisible(false);
//            }else{
//                icon7.setVisible(false);
//                icon8.setVisible(true);
//            }
//            String labaString = String.format("%.1f", LabaHariIni); //menampilkan angka desimal hanya dengan 1 digit di belakang koma
//            txt_laba.setText(labaString + "%");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setIconInitWeek(){
        Statement st = null;
            ResultSet rs = null;
            try {
                java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
                //client
                rs = st.executeQuery(
                        "SELECT\n" +
                        "barang + gromming + penitipan AS client_minggu_ini\n" +
                        "FROM\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang WHERE tanggal_transaksi >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_barang,\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming WHERE tanggal_transaksi >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_gromming,\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan WHERE tanggal_transaksi >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_penitipan"
                );
                int clientMingguIni = rs.next() ? rs.getInt("client_minggu_ini") : 0;
                rs = st.executeQuery(
                        "SELECT\n" +
                        "    barang + gromming + penitipan AS client_last_week\n" +
                        "FROM\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS barang FROM tb_transaksi_barang \n" +
                        "    WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_barang,\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS gromming FROM tb_transaksi_gromming \n" +
                        "    WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_gromming,\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS penitipan FROM tb_transaksi_penitipan \n" +
                        "    WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_penitipan"
                        
//                        "SELECT" +
//                        "barang + gromming + penitipan AS client_last_week" +
//                        "FROM" 
//                        + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"
//                        + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"
//                        + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"
                );
                int clientMingguKemarin = rs.next() ? rs.getInt("client_last_week") : 0;
                if (clientMingguIni > clientMingguKemarin) {
                    icon1.setVisible(true);
                    icon2.setVisible(false);
                } else {
                    icon1.setVisible(false);
                    icon2.setVisible(true);
                }
                txt_client.setText(Integer.toString(clientMingguIni));
                //transaski
                rs = st.executeQuery(
                        "SELECT SUM(total_rows) as transaksi_minggu_ini FROM ("
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) "
                        + "AS total_counts"
                );
                int transaksiMingguIni = rs.next() ? rs.getInt("transaksi_minggu_ini") : 0;
                rs = st.executeQuery(
                        "SELECT SUM(total_rows) as transaksi_last_week FROM("
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang "
                            + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK) UNION ALL "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming "
                            + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK) UNION ALL "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan "
                            + "WHERE DATE(tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) "
                        + "as total_count"
                );
                int transaksiMingguKemarin = rs.next() ? rs.getInt("transaksi_last_week") : 0;
                if (transaksiMingguIni > transaksiMingguKemarin) {
                    icon3.setVisible(true);
                    icon4.setVisible(false);
                } else {
                    icon3.setVisible(false);
                    icon4.setVisible(true);
                }
                txt_transaksi.setText((Integer.toString(transaksiMingguIni)));
                //pendapatan
                rs = st.executeQuery(
                        "SELECT\n" +
                        "     barang + gromming + penitipan as pendapatan_minggu_ini \n" +
                        "FROM\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS barang FROM tb_transaksi_barang "
                                + "WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_barang,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS gromming FROM tb_transaksi_gromming"
                                + " WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_gromming,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS penitipan FROM tb_transaksi_penitipan "
                                + "WHERE DATE(tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)) AS tb_penitipan"
                );
                int PendapatanMingguIni = rs.next() ? rs.getInt("pendapatan_minggu_ini") : 0;
                rs = st.executeQuery(
                        "SELECT\n" +
                        "    barang + gromming + penitipan AS pendapatan_last_week\n" +
                        "FROM\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS barang FROM tb_transaksi_barang \n" +
                        "    WHERE tb_transaksi_barang.tanggal_transaksi > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND tb_transaksi_barang.tanggal_transaksi <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_barang,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS gromming FROM tb_transaksi_gromming \n" +
                        "    WHERE tb_transaksi_gromming.tanggal_transaksi > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND tb_transaksi_gromming.tanggal_transaksi <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_gromming,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS penitipan FROM tb_transaksi_penitipan \n" +
                        "    WHERE tb_transaksi_penitipan.tanggal_transaksi > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND tb_transaksi_penitipan.tanggal_transaksi <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)) AS tb_penitipan"
                );
                int PendapatanMingguKemarin = rs.next() ? rs.getInt("pendapatan_last_week") : 0;
                if (PendapatanMingguIni > PendapatanMingguKemarin) {
                    icon5.setVisible(true);
                    icon6.setVisible(false);
                } else {
                    icon5.setVisible(false);
                    icon6.setVisible(true);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String totalFormatted = decimalFormat.format(PendapatanMingguIni);
                txt_pendapatan.setText(totalFormatted);
                //laba
//                rs = st.executeQuery(
//                        "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_minggu_ini \n" +
//                        "FROM tb_transaksi_barang\n" +
//                        "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                        "WHERE DATE(tb_transaksi_barang.tanggal_transaksi) >= DATE_SUB(CURDATE(), INTERVAL 6 DAY)"
//                );
//                double LabaMingguIni = rs.next() ? rs.getDouble("laba_minggu_ini") : 0;
//                rs = st.executeQuery(
//                        "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_last_week \n" +
//                        "FROM tb_transaksi_barang\n" +
//                        "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                        "WHERE DATE(tb_transaksi_barang.tanggal_transaksi) > DATE_SUB(CURDATE(), INTERVAL 2 WEEK) AND DATE(tanggal_transaksi) <= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"
//                );
//                double LabaMingguKemarin = rs.next() ? rs.getDouble("laba_last_week") : 0;
//                if (LabaMingguIni > LabaMingguKemarin){
//                    icon7.setVisible(true);
//                    icon8.setVisible(false);
//                }else{
//                    icon7.setVisible(false);
//                    icon8.setVisible(true);
//                }
//                String labaString = String.format("%.1f", LabaMingguIni);
//                txt_laba.setText(labaString + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    private void setIconInitMonth(){
        Statement st = null;
            ResultSet rs = null;
            try {
                java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
                //client
                rs = st.executeQuery(
//                        "SELECT COUNT(*) AS client_bulan_ini FROM tb_pelanggan WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE())"
                        "SELECT\n" +
                        "barang + gromming + penitipan AS client_bulan_ini\n" +
                        "FROM\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang "
                                + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE())) AS tb_barang,\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming "
                                + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE())) AS tb_gromming,\n" +
                        "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan "
                                + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE())) AS tb_penitipan"
                );
                int clientBulanIni = rs.next() ? rs.getInt("client_bulan_ini") : 0;
                rs = st.executeQuery(
//                        "SELECT COUNT(*) AS client_last_month FROM tb_pelanggan "
//                        + "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))"
                        "SELECT\n" +
                        "    barang + gromming + penitipan AS client_last_month\n" +
                        "FROM\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS barang FROM tb_transaksi_barang \n" +
                        "    WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_barang,\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS gromming FROM tb_transaksi_gromming \n" +
                        "    WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_gromming,\n" +
                        "    (SELECT COUNT(DISTINCT id_pelanggan) AS penitipan FROM tb_transaksi_penitipan \n" +
                        "    WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_penitipan"
                );
                int clientBulanKemarin = rs.next() ? rs.getInt("client_last_month") : 0;
                if (clientBulanIni > clientBulanKemarin) {
                    icon1.setVisible(true);
                    icon2.setVisible(false);
                } else {
                    icon1.setVisible(false);
                    icon2.setVisible(true);
                }
                txt_client.setText(Integer.toString(clientBulanIni));
                
                //transaski
                rs = st.executeQuery(
                        "SELECT SUM(total_rows) as transaksi_bulan_ini FROM ("
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE()) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE()) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tanggal_transaksi) = MONTH(CURDATE())) "
                        + "AS total_counts"
                );
                int transaksiBulanIni = rs.next() ? rs.getInt("transaksi_bulan_ini") : 0;
                rs = st.executeQuery(
                        "SELECT SUM(total_rows) as transaksi_last_month FROM ("
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) union all "
                        + "SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan "
                            + "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) "
                        + "AS total_counts"
                );
                int transaksiBulanKemarin = rs.next() ? rs.getInt("transaksi_last_month") : 0;
                if (transaksiBulanIni > transaksiBulanKemarin) {
                    icon3.setVisible(true);
                    icon4.setVisible(false);
                } else {
                    icon3.setVisible(false);
                    icon4.setVisible(true);
                }
                txt_transaksi.setText((Integer.toString(transaksiBulanIni)));
                //pendapatan
                rs = st.executeQuery(
                        "SELECT\n" +
                        "(barang + gromming + penitipan) AS pendapatan_bulan_ini\n" +
                        "FROM\n" +
                        "(SELECT COALESCE(SUM(total), 0) AS barang\n" +
                        "FROM tb_transaksi_barang\n" +
                        "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tb_transaksi_barang.tanggal_transaksi) = MONTH(CURDATE())) AS tb_barang,\n" +
                        "(SELECT COALESCE(SUM(total), 0) AS gromming\n" +
                        "FROM tb_transaksi_gromming\n" +
                        "WHERE YEAR(tb_transaksi_gromming.tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tb_transaksi_gromming.tanggal_transaksi) = MONTH(CURDATE())) AS tb_gromming,\n" +
                        "(SELECT COALESCE(SUM(total), 0) AS penitipan\n" +
                        "FROM tb_transaksi_penitipan\n" +
                        "WHERE YEAR(tb_transaksi_penitipan.tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tb_transaksi_penitipan.tanggal_transaksi) = MONTH(CURDATE())) AS tb_penitipan;"
                );
                int PendapatanBulanIni = rs.next() ? rs.getInt("pendapatan_bulan_ini") : 0;
                rs = st.executeQuery(
                        "SELECT\n" +
                        "    (tb_barang.barang + tb_gromming.gromming + tb_penitipan.penitipan) AS pendapatan_last_month\n" +
                        "FROM\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS barang\n" +
                        "    FROM tb_transaksi_barang\n" +
                        "    WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tb_transaksi_barang.tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_barang,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS gromming\n" +
                        "    FROM tb_transaksi_gromming\n" +
                        "    WHERE YEAR(tb_transaksi_gromming.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tb_transaksi_gromming.tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_gromming,\n" +
                        "    (SELECT COALESCE(SUM(total), 0) AS penitipan\n" +
                        "    FROM tb_transaksi_penitipan\n" +
                        "    WHERE YEAR(tb_transaksi_penitipan.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tb_transaksi_penitipan.tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS tb_penitipan;"
                );
                int PendapatanBulanKemarin = rs.next() ? rs.getInt("pendapatan_last_month") : 0;
                if (PendapatanBulanIni > PendapatanBulanKemarin) {
                    icon5.setVisible(true);
                    icon6.setVisible(false);
                } else {
                    icon5.setVisible(false);
                    icon6.setVisible(true);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String totalFormatted = decimalFormat.format(PendapatanBulanIni);
                txt_pendapatan.setText(totalFormatted);
                //laba
//                rs = st.executeQuery(
//                        "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_bulan_ini\n" +
//                        "FROM tb_transaksi_barang\n" +
//                        "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                        "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(CURDATE()) AND MONTH(tb_transaksi_barang.tanggal_transaksi) = MONTH(CURDATE())"
//                );
//                double LabaBulanIni = rs.next() ? rs.getDouble("laba_bulan_ini") : 0;
//                rs = st.executeQuery(
//                        "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_last_month \n" +
//                        "FROM tb_transaksi_barang\n" +
//                        "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                        "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND MONTH(tb_transaksi_barang.tanggal_transaksi) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))"
//                );
//                double LabaBulanKemarin = rs.next() ? rs.getDouble("laba_last_month") : 0;
//                if (LabaBulanIni > LabaBulanKemarin){
//                    icon7.setVisible(true);
//                    icon8.setVisible(false);
//                }else{
//                    icon7.setVisible(false);
//                    icon8.setVisible(true);
//                }
//                String labaString = String.format("%.1f", LabaBulanIni); //menampilkan angka desimal hanya dengan 1 digit di belakang koma
//                txt_laba.setText(labaString + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    private void setIconInitYear(){
        Statement st = null;
        ResultSet rs = null;
        try {
            java.sql.Connection conn = (Connection)config.configDB();
                st = conn.createStatement();
            //client
            rs = st.executeQuery(
//                    "SELECT COUNT(*) AS client_tahun_ini FROM tb_pelanggan WHERE YEAR(date) = YEAR(CURDATE())"
                    "SELECT\n" +
                    "barang + gromming + penitipan AS client_tahun_ini\n" +
                    "FROM\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())) AS tb_barang,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())) AS tb_gromming,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())) AS tb_penitipan  "
            );
            int clientTahunIni = rs.next() ? rs.getInt("client_tahun_ini") : 0;
            rs = st.executeQuery(
//                    "SELECT COUNT(*) AS client_last_year FROM tb_pelanggan WHERE YEAR(date) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))"
                    "SELECT\n" +
                    "barang + gromming + penitipan AS client_last_year\n" +
                    "FROM\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as barang FROM tb_transaksi_barang WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_barang,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as gromming FROM tb_transaksi_gromming WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_gromming,\n" +
                    "(SELECT COUNT(DISTINCT id_pelanggan) as penitipan FROM tb_transaksi_penitipan WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_penitipan  "
            );
            int clientTahunKemarin = rs.next() ? rs.getInt("client_last_year") : 0;
            if (clientTahunIni > clientTahunKemarin) {
                icon1.setVisible(true);
                icon2.setVisible(false);
            } else {
                icon1.setVisible(false);
                icon2.setVisible(true);
            }
            txt_client.setText(Integer.toString(clientTahunIni));

            //transaski
            rs = st.executeQuery(
                    "SELECT SUM(total_rows) as transaksi_tahun_ini FROM (\n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())\n" +
                    "union all SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())\n" +
                    "union all SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(CURDATE())\n" +
                    ") AS total_counts"
            );
            int transaksiTahunIni = rs.next() ? rs.getInt("transaksi_tahun_ini") : 0;
            rs = st.executeQuery(
                    "SELECT SUM(total_rows) as transaksi_last_year FROM (\n" +
                    "SELECT COUNT(*) as total_rows FROM tb_transaksi_barang \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))\n" +
                    "union all SELECT COUNT(*) as total_rows FROM tb_transaksi_gromming \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))\n" +
                    "union all SELECT COUNT(*) as total_rows FROM tb_transaksi_penitipan \n" +
                    "WHERE YEAR(tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))\n" +
                    ") AS total_counts"
            );
            int transaksiTahunKemarin = rs.next() ? rs.getInt("transaksi_last_year") : 0;
            if (transaksiTahunIni > transaksiTahunKemarin) {
                icon3.setVisible(true);
                icon4.setVisible(false);
            } else {
                icon3.setVisible(false);
                icon4.setVisible(true);
            }
            txt_transaksi.setText((Integer.toString(transaksiTahunIni)));
            //pendapatan
            rs = st.executeQuery(
                    "SELECT\n" +
                    "(tb_barang.barang + tb_gromming.gromming + tb_penitipan.penitipan) AS pendapatan_tahun_ini\n" +
                    "FROM\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS barang\n" +
                    "FROM tb_transaksi_barang\n" +
                    "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(CURDATE())) AS tb_barang,\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS gromming\n" +
                    "FROM tb_transaksi_gromming\n" +
                    "WHERE YEAR(tb_transaksi_gromming.tanggal_transaksi) = YEAR(CURDATE())) AS tb_gromming,\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS penitipan\n" +
                    "FROM tb_transaksi_penitipan\n" +
                    "WHERE YEAR(tb_transaksi_penitipan.tanggal_transaksi) = YEAR(CURDATE())) AS tb_penitipan;"
            );
            int PendapatanTahunIni = rs.next() ? rs.getInt("pendapatan_tahun_ini") : 0;
            rs = st.executeQuery(
                    "SELECT\n" +
                    "(tb_barang.barang + tb_gromming.gromming + tb_penitipan.penitipan) AS pendapatan_last_year\n" +
                    "FROM\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS barang\n" +
                    "FROM tb_transaksi_barang\n" +
                    "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_barang,\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS gromming\n" +
                    "FROM tb_transaksi_gromming\n" +
                    "WHERE YEAR(tb_transaksi_gromming.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_gromming,\n" +
                    "(SELECT COALESCE(SUM(total), 0) AS penitipan\n" +
                    "FROM tb_transaksi_penitipan\n" +
                    "WHERE YEAR(tb_transaksi_penitipan.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))) AS tb_penitipan;"
            );
            int PendapatanTahunKemarin = rs.next() ? rs.getInt("pendapatan_last_year") : 0;
            if (PendapatanTahunIni > PendapatanTahunKemarin) {
                icon5.setVisible(true);
                icon6.setVisible(false);
            } else {
                icon5.setVisible(false);
                icon6.setVisible(true);
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String totalFormatted = decimalFormat.format(PendapatanTahunIni);
            txt_pendapatan.setText(totalFormatted);
            //laba
//            rs = st.executeQuery(
//                    "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_tahun_ini \n" +
//                    "FROM tb_transaksi_barang\n" +
//                    "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                    "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(CURDATE())"
//            );
//            double LabaTahunIni = rs.next() ? rs.getDouble("laba_tahun_ini") : 0;
//            rs = st.executeQuery(
//                    "SELECT ((SUM(tb_barang.harga_jual) - SUM(tb_barang.harga_beli)) / SUM(tb_barang.harga_beli)) * 100 AS laba_last_year \n" +
//                    "FROM tb_transaksi_barang\n" +
//                    "JOIN tb_barang ON tb_transaksi_barang.id_barang = tb_barang.id_barang\n" +
//                    "WHERE YEAR(tb_transaksi_barang.tanggal_transaksi) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))"
//            );
//            double LabaTahunKemarin = rs.next() ? rs.getDouble("laba_last_year") : 0;
//            if (LabaTahunIni > LabaTahunKemarin){
//                icon7.setVisible(true);
//                icon8.setVisible(false);
//            }else{
//                icon7.setVisible(false);
//                icon8.setVisible(true);
//            }
//            String labaString = String.format("%.1f", LabaTahunIni); //menampilkan angka desimal hanya dengan 1 digit di belakang koma
//            txt_laba.setText(labaString + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setInitRankLayanan(){
        Statement st = null;
        ResultSet rs = null;
        
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            st = conn.createStatement();
            //rankLayanan
            for(int i = 0; i < 3; i++){
                rs = st.executeQuery(
                        "SELECT 'gromming' AS sumber, tb_gromming.nama_gromming AS nama, detail_transaksi_gromming.id_gromming AS id, \n" +
                        "COUNT(detail_transaksi_gromming.id_transaksi_gromming) AS jml\n" +
                        "FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
                        "ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
                        "GROUP BY detail_transaksi_gromming.id_gromming\n" +
                        "UNION ALL\n" +
                        "SELECT 'penitipan' AS sumber, tb_penitipan.nama_penitipan AS nama, detail_transaksi_penitipan.id_penitipan AS id, \n" +
                        "COUNT(detail_transaksi_penitipan.id_transaksi_penitipan) AS jml\n" +
                        "FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
                        "ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan\n" +
                        "GROUP BY detail_transaksi_penitipan.id_penitipan\n" +
                        "ORDER BY jml DESC\n" +
                        "LIMIT "+ i +",1;"
                );
                
                if(rs.next()){
                    String namaLayanan = rs.getString("sumber");
                    String descLayanan = rs.getString("nama");
                    
                    switch(i){
                        case 0:
                            titleRLayanan1.setText(namaLayanan);
                            descRLayanan1.setText(descLayanan);
                            break;
                        case 1:
                            titleRLayanan2.setText(namaLayanan);
                            descRLayanan2.setText(descLayanan);
                            break;
                        case 2:
                            titleRLayanan3.setText(namaLayanan);
                            descRLayanan3.setText(descLayanan);
                            break;
                    }
                } else {
                    switch(i){
                        case 0:
                            titleRLayanan1.setText("Data Tidak Ada");
                            descRLayanan1.setText("");
                            break;
                        case 1:
                            titleRLayanan2.setText("Data Tidak Ada");
                            descRLayanan2.setText("");
                            break;
                        case 2:
                            titleRLayanan3.setText("Data Tidak Ada");
                            descRLayanan3.setText("");
                            break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setInitRankBarang(){
        Statement st = null;
        ResultSet rs = null;
        
        try {
            java.sql.Connection conn = (Connection)config.configDB();
            st = conn.createStatement();
            
            //set data rank barang
            for(int i = 0; i < 3; i++){
                rs = st.executeQuery(
                        "SELECT tb_barang.nama_barang, tb_barang.kategori,\n" +
                        "SUM(detail_transaksi_barang.qty) AS total_beli \n" +
                        "FROM tb_barang INNER JOIN detail_transaksi_barang\n" +
                        "ON tb_barang.id_barang = detail_transaksi_barang.id_barang\n" +
                        "GROUP BY nama_barang \n" +
                        "ORDER BY total_beli DESC\n" +
                        "LIMIT " + i + ",1"
                );
                if(rs.next()){
                    String namaBarang = rs.getString("nama_barang");
                    String descBarang = rs.getString("kategori");
                    switch(i){
                        case 0:
                            titleRBarang1.setText(namaBarang);
                            descRBarang1.setText(descBarang);
                            break;
                        case 1:
                            titleRBarang2.setText(namaBarang);
                            descRBarang2.setText(descBarang);
                            break;
                        case 2:
                            titleRBarang3.setText(namaBarang);
                            descRBarang3.setText(descBarang);
                            break;
                    }
                } else {
                    switch(i){
                        case 0:
                            titleRBarang1.setText("Data Tidak Ada");
                            descRBarang1.setText("");
                            break;
                        case 1:
                            titleRBarang2.setText("Data Tidak Ada");
                            descRBarang2.setText("");
                            break;
                        case 2:
                            titleRBarang3.setText("Data Tidak Ada");
                            descRBarang3.setText("");
                            break;
                    }
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setInitNewTransaksi(){
        Statement st = null;
        ResultSet rs = null;
        
         try {
            java.sql.Connection conn = (Connection)config.configDB();
            st = conn.createStatement();
            
            for(int i = 0; i < 6; i++){
                rs = st.executeQuery(
                    "SELECT \n" +
                    "'Barang' AS sumber, detail_transaksi_barang.id_transaksi_barang AS id, tb_barang.nama_barang AS nama, tb_transaksi_barang.total AS harga, tb_transaksi_barang.tanggal_transaksi AS tanggal_transaksi\n" +
                    "FROM detail_transaksi_barang INNER JOIN tb_barang \n" +
                    "ON detail_transaksi_barang.id_barang = tb_barang.id_barang\n" +
                    "JOIN tb_transaksi_barang ON detail_transaksi_barang.id_transaksi_barang = tb_transaksi_barang.id_transaksi_barang\n" +
                    "UNION ALL\n" +
                    "SELECT \n" +
                    "'Gromming' AS sumber, detail_transaksi_gromming.id_transaksi_gromming AS id, tb_gromming.nama_gromming AS nama, tb_transaksi_gromming.total AS harga, tb_transaksi_gromming.tanggal_transaksi AS tanggal_transaksi\n" +
                    "FROM detail_transaksi_gromming INNER JOIN tb_gromming \n" +
                    "ON detail_transaksi_gromming.id_gromming = tb_gromming.id_gromming\n" +
                    "JOIN tb_transaksi_gromming ON detail_transaksi_gromming.id_transaksi_gromming = tb_transaksi_gromming.id_transaksi_gromming\n" +
                    "UNION ALL\n" +
                    "SELECT \n" +
                    "'Penitipan' AS sumber, tb_transaksi_penitipan.id_transaksi_penitipan AS id, tb_penitipan.nama_penitipan AS nama, tb_transaksi_penitipan.total AS harga, tb_transaksi_penitipan.tanggal_transaksi AS tanggal_transaksi\n" +
                    "FROM detail_transaksi_penitipan INNER JOIN tb_penitipan \n" +
                    "ON detail_transaksi_penitipan.id_penitipan = tb_penitipan.id_penitipan \n" +
                    "JOIN tb_transaksi_penitipan ON detail_transaksi_penitipan.id_transaksi_penitipan = tb_transaksi_penitipan.id_transaksi_penitipan\n" +
                    "ORDER BY tanggal_transaksi DESC\n" +
                    "LIMIT "+ i +",1"
                );
                if (rs.next()) {
                    String sumb = rs.getString("sumber");
                    String nama = rs.getString("nama");
                    int pric = rs.getInt("harga");
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formatted = decimalFormat.format(pric);
                    
                    switch(i){
                        case 0:
                            sumberNTrsn1.setText(sumb);
                            titleNTrsn1.setText(nama);
                            priceNTrsn1.setText("Rp "+ formatted);
                            break;
                        case 1:
                            sumberNTrsn2.setText(sumb);
                            titleNTrsn2.setText(nama);
                            priceNTrsn2.setText("Rp "+ formatted);
                            break;
                        case 2:
                            sumberNTrsn3.setText(sumb);
                            titleNTrsn3.setText(nama);
                            priceNTrsn3.setText("Rp "+ formatted);
                            break;
                        case 3:
                            sumberNTrsn4.setText(sumb);
                            titleNTrsn4.setText(nama);
                            priceNTrsn4.setText("Rp "+ formatted);
                            break;
                        case 4:
                            sumberNTrsn5.setText(sumb);
                            titleNTrsn5.setText(nama);
                            priceNTrsn5.setText("Rp "+ formatted);
                            break;
                        case 5:
                            sumberNTrsn6.setText(sumb);
                            titleNTrsn6.setText(nama);
                            priceNTrsn6.setText("Rp "+ formatted);
                            break;
                    }
                }
                else {
                    switch(i) {
                        case 0:
                            sumberNTrsn1.setText("Data Tidak Ada");
                            titleNTrsn1.setText("");
                            priceNTrsn1.setText("");
                            break;
                        case 1:
                            sumberNTrsn2.setText("Data Tidak Ada");
                            titleNTrsn2.setText("");
                            priceNTrsn2.setText("");
                            break;
                        case 2:
                            sumberNTrsn3.setText("Data Tidak Ada");
                            titleNTrsn3.setText("");
                            priceNTrsn3.setText("");
                            break;
                        case 3:
                            sumberNTrsn4.setText("Data Tidak Ada");
                            titleNTrsn4.setText("");
                            priceNTrsn4.setText("");
                            break;
                        case 4:
                            sumberNTrsn5.setText("Data Tidak Ada");
                            titleNTrsn5.setText("");
                            priceNTrsn5.setText("");
                            break;
                        case 5:
                            sumberNTrsn6.setText("Data Tidak Ada");
                            titleNTrsn6.setText("");
                            priceNTrsn6.setText("");
                            break;
                    }
                }
            }
         }  catch(Exception e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp_keluar = new javax.swing.JPanel();
        bg_keluar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jumlah_notif = new javax.swing.JLabel();
        circle_notif = new javax.swing.JLabel();
        btn_notif = new javax.swing.JLabel();
        pn_notif = new javax.swing.JPanel();
        filter = new swing.comboBox.Combobox();
        title1 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        title3 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        txt_jmlPegawai = new javax.swing.JLabel();
        txt_pendapatan = new javax.swing.JLabel();
        txt_client = new javax.swing.JLabel();
        txt_transaksi = new javax.swing.JLabel();
        toTransaksi = new javax.swing.JLabel();
        icon1 = new javax.swing.JLabel();
        icon2 = new javax.swing.JLabel();
        icon3 = new javax.swing.JLabel();
        icon4 = new javax.swing.JLabel();
        icon5 = new javax.swing.JLabel();
        icon6 = new javax.swing.JLabel();
        titleRLayanan1 = new javax.swing.JLabel();
        titleRLayanan2 = new javax.swing.JLabel();
        titleRLayanan3 = new javax.swing.JLabel();
        descRLayanan1 = new javax.swing.JLabel();
        descRLayanan2 = new javax.swing.JLabel();
        descRLayanan3 = new javax.swing.JLabel();
        titleRBarang1 = new javax.swing.JLabel();
        titleRBarang2 = new javax.swing.JLabel();
        titleRBarang3 = new javax.swing.JLabel();
        descRBarang1 = new javax.swing.JLabel();
        descRBarang2 = new javax.swing.JLabel();
        descRBarang3 = new javax.swing.JLabel();
        sumberNTrsn1 = new javax.swing.JLabel();
        sumberNTrsn2 = new javax.swing.JLabel();
        sumberNTrsn3 = new javax.swing.JLabel();
        sumberNTrsn4 = new javax.swing.JLabel();
        sumberNTrsn5 = new javax.swing.JLabel();
        sumberNTrsn6 = new javax.swing.JLabel();
        titleNTrsn1 = new javax.swing.JLabel();
        titleNTrsn2 = new javax.swing.JLabel();
        titleNTrsn3 = new javax.swing.JLabel();
        titleNTrsn4 = new javax.swing.JLabel();
        titleNTrsn5 = new javax.swing.JLabel();
        titleNTrsn6 = new javax.swing.JLabel();
        priceNTrsn1 = new javax.swing.JLabel();
        priceNTrsn2 = new javax.swing.JLabel();
        priceNTrsn3 = new javax.swing.JLabel();
        priceNTrsn4 = new javax.swing.JLabel();
        priceNTrsn5 = new javax.swing.JLabel();
        priceNTrsn6 = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        lbl_formPegawai = new javax.swing.JLabel();
        lblnama = new javax.swing.JLabel();
        Logout = new javax.swing.JLabel();
        borderHide = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        popUp_keluar.setBackground(new Color(0, 0, 0, 200));
        popUp_keluar.setLayout(null);

        bg_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Validasi LogOut.png"))); // NOI18N
        popUp_keluar.add(bg_keluar);
        bg_keluar.setBounds(608, 380, 704, 320);

        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        popUp_keluar.add(jLabel1);
        jLabel1.setBounds(778, 597, 164, 38);

        jLabel2.setText("jLabel1");
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        popUp_keluar.add(jLabel2);
        jLabel2.setBounds(978, 597, 164, 38);

        jLabel3.setText("jLabel1");
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        popUp_keluar.add(jLabel3);
        jLabel3.setBounds(1240, 395, 44, 43);

        getContentPane().add(popUp_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        jumlah_notif.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jumlah_notif.setText("1");
        getContentPane().add(jumlah_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1598, 12, -1, -1));

        circle_notif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/circle_notif.png"))); // NOI18N
        getContentPane().add(circle_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 10, 30, 30));

        btn_notif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_notifMouseClicked(evt);
            }
        });
        getContentPane().add(btn_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 10, 50, 60));

        pn_notif.setLayout(new javax.swing.BoxLayout(pn_notif, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(pn_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 70, 413, 502));

        filter.setBorder(null);
        filter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "in Days", "in Weeks", "in Months", "in Years" }));
        filter.setSelectedIndex(3);
        filter.setFont(new java.awt.Font("Microsoft Tai Le", 0, 12)); // NOI18N
        filter.setLabeTaxt("");
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });
        getContentPane().add(filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1690, 120, 140, 30));

        title1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 32)); // NOI18N
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("Client Bulan Ini");
        getContentPane().add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 185, 290, 30));

        title2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 32)); // NOI18N
        title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title2.setText("Transaksi");
        getContentPane().add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 185, 230, 30));

        title3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 32)); // NOI18N
        title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title3.setText("Jumlah Pendapatan");
        getContentPane().add(title3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 185, 390, 30));

        title4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 32)); // NOI18N
        title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title4.setText("Jumlah Pegawai");
        getContentPane().add(title4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 185, 340, 30));

        txt_jmlPegawai.setFont(new java.awt.Font("Microsoft Tai Le", 1, 64)); // NOI18N
        txt_jmlPegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_jmlPegawai.setText("75");
        getContentPane().add(txt_jmlPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 238, 140, 60));

        txt_pendapatan.setFont(new java.awt.Font("Microsoft Tai Le", 1, 64)); // NOI18N
        txt_pendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_pendapatan.setText("0");
        getContentPane().add(txt_pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 238, 290, 60));

        txt_client.setFont(new java.awt.Font("Microsoft Tai Le", 1, 64)); // NOI18N
        txt_client.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_client.setText("0");
        getContentPane().add(txt_client, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 238, 120, 60));

        txt_transaksi.setFont(new java.awt.Font("Microsoft Tai Le", 1, 64)); // NOI18N
        txt_transaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_transaksi.setText("0");
        getContentPane().add(txt_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 238, 150, 60));

        toTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(toTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 1006, 130, 20));

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/up.png"))); // NOI18N
        getContentPane().add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 238, -1, -1));

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/down.png"))); // NOI18N
        getContentPane().add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 238, -1, -1));

        icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/up.png"))); // NOI18N
        getContentPane().add(icon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 238, -1, -1));

        icon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/down.png"))); // NOI18N
        getContentPane().add(icon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 238, -1, -1));

        icon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/up.png"))); // NOI18N
        getContentPane().add(icon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 238, -1, -1));

        icon6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/down.png"))); // NOI18N
        getContentPane().add(icon6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 238, -1, -1));

        titleRLayanan1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRLayanan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRLayanan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 576, 230, 30));

        titleRLayanan2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRLayanan2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRLayanan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 576, 240, 30));

        titleRLayanan3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRLayanan3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRLayanan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 576, 230, 30));

        descRLayanan1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRLayanan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRLayanan1.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRLayanan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 608, 230, 20));

        descRLayanan2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRLayanan2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRLayanan2.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRLayanan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 608, 240, 20));

        descRLayanan3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRLayanan3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRLayanan3.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRLayanan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 608, 230, 20));

        titleRBarang1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRBarang1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 870, 230, 30));

        titleRBarang2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRBarang2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRBarang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 870, 240, 30));

        titleRBarang3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 22)); // NOI18N
        titleRBarang3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleRBarang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 870, 230, 30));

        descRBarang1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRBarang1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRBarang1.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 900, 230, 20));

        descRBarang2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRBarang2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRBarang2.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRBarang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 900, 240, 20));

        descRBarang3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 16)); // NOI18N
        descRBarang3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descRBarang3.setPreferredSize(new java.awt.Dimension(175, 16));
        getContentPane().add(descRBarang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 900, 230, 20));

        sumberNTrsn1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 458, 220, 30));

        sumberNTrsn2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 550, 220, 30));

        sumberNTrsn3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 645, 220, 30));

        sumberNTrsn4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 738, 220, 30));

        sumberNTrsn5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 830, 220, 30));

        sumberNTrsn6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 20)); // NOI18N
        getContentPane().add(sumberNTrsn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 924, 220, 30));

        titleNTrsn1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 488, 220, 20));

        titleNTrsn2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 580, 220, 20));

        titleNTrsn3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 673, 220, 20));

        titleNTrsn4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 765, 220, 20));

        titleNTrsn5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 860, 220, 20));

        titleNTrsn6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        getContentPane().add(titleNTrsn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1448, 952, 220, 20));

        priceNTrsn1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 470, 140, 40));

        priceNTrsn2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 562, 140, 40));

        priceNTrsn3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 655, 140, 40));

        priceNTrsn4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 750, 140, 40));

        priceNTrsn5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 840, 140, 40));

        priceNTrsn6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        priceNTrsn6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(priceNTrsn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 935, 140, 40));

        lbl_formDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/Nav-Beranda.png"))); // NOI18N
        lbl_formDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formDashboardMouseEntered(evt);
            }
        });
        getContentPane().add(lbl_formDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 343, 66));

        lbl_formStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseEntered(evt);
            }
        });
        getContentPane().add(lbl_formStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 340, 50));

        lbl_formLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLayananMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 50));

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

        lbl_formPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formPegawaiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formPegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 350, 50));

        lblnama.setFont(new java.awt.Font("Microsoft Tai Le", 1, 34)); // NOI18N
        lblnama.setText(".");
        getContentPane().add(lblnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 160, 40));

        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        getContentPane().add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 970, 343, 66));

        borderHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard (4).png"))); // NOI18N
        borderHide.setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().add(borderHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        popUp_keluar.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        filter.setOpaque(false);
        popUp_keluar.setVisible(true);
    }//GEN-LAST:event_LogoutMouseClicked

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        // TODO add your handling code here:
        String data = (String) filter.getSelectedItem();
        if (data.equals("All")){
            title1.setText("Client");
            setIconInitAll();
        }else if (data.equals("in Days")){
            title1.setText("Client Hari Ini");
            setIconInitDay();
        } else if (data.equals("in Weeks")) {
            title1.setText("Client Minggu Ini");
            setIconInitWeek();
        } else if (data.equals("in Months")) {
            title1.setText("Client Bulan Ini");
            setIconInitMonth();
        } else if (data.equals("in Years")) {
            title1.setText("Client Tahun Ini");
            setIconInitYear();
        }
    }//GEN-LAST:event_filterActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_dashboard.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void toTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toTransaksiMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsung().setVisible(true);
    }//GEN-LAST:event_toTransaksiMouseClicked

    private void lbl_formStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseClicked
        this.setVisible(false);
        new form_stok().setVisible(true);
    }//GEN-LAST:event_lbl_formStokMouseClicked

    private void lbl_formStokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseEntered
        //        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Sub Navbar.png")));
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

    private void lbl_formDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboardMouseClicked
        this.setVisible(false);
        new form_dashboard().setVisible(true);
    }//GEN-LAST:event_lbl_formDashboardMouseClicked

    private void lbl_formDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboardMouseEntered
//        lbl_formDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/Nav-Beranda.png")));
    }//GEN-LAST:event_lbl_formDashboardMouseEntered
    
    boolean klikk = false;
    private void btn_notifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_notifMouseClicked
        if (klikk == false) {
            pn_notif.setVisible(true);
            klikk = true;
        }else{
             pn_notif.setVisible(false);
             klikk = false;
        }
    }//GEN-LAST:event_btn_notifMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(form_dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new form_dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout;
    private javax.swing.JLabel bg_keluar;
    private javax.swing.JLabel borderHide;
    private javax.swing.JLabel btn_notif;
    private javax.swing.JLabel circle_notif;
    private javax.swing.JLabel descRBarang1;
    private javax.swing.JLabel descRBarang2;
    private javax.swing.JLabel descRBarang3;
    private javax.swing.JLabel descRLayanan1;
    private javax.swing.JLabel descRLayanan2;
    private javax.swing.JLabel descRLayanan3;
    private swing.comboBox.Combobox filter;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JLabel icon4;
    private javax.swing.JLabel icon5;
    private javax.swing.JLabel icon6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jumlah_notif;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formPegawai;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lblnama;
    private javax.swing.JPanel pn_notif;
    private javax.swing.JPanel popUp_keluar;
    private javax.swing.JLabel priceNTrsn1;
    private javax.swing.JLabel priceNTrsn2;
    private javax.swing.JLabel priceNTrsn3;
    private javax.swing.JLabel priceNTrsn4;
    private javax.swing.JLabel priceNTrsn5;
    private javax.swing.JLabel priceNTrsn6;
    private javax.swing.JLabel sumberNTrsn1;
    private javax.swing.JLabel sumberNTrsn2;
    private javax.swing.JLabel sumberNTrsn3;
    private javax.swing.JLabel sumberNTrsn4;
    private javax.swing.JLabel sumberNTrsn5;
    private javax.swing.JLabel sumberNTrsn6;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel titleNTrsn1;
    private javax.swing.JLabel titleNTrsn2;
    private javax.swing.JLabel titleNTrsn3;
    private javax.swing.JLabel titleNTrsn4;
    private javax.swing.JLabel titleNTrsn5;
    private javax.swing.JLabel titleNTrsn6;
    private javax.swing.JLabel titleRBarang1;
    private javax.swing.JLabel titleRBarang2;
    private javax.swing.JLabel titleRBarang3;
    private javax.swing.JLabel titleRLayanan1;
    private javax.swing.JLabel titleRLayanan2;
    private javax.swing.JLabel titleRLayanan3;
    private javax.swing.JLabel toTransaksi;
    private javax.swing.JLabel txt_client;
    private javax.swing.JLabel txt_jmlPegawai;
    private javax.swing.JLabel txt_pendapatan;
    private javax.swing.JLabel txt_transaksi;
    // End of variables declaration//GEN-END:variables
}
