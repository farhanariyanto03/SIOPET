/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siopet;

import java.awt.Dimension;
import java.sql.Connection;
import javax.swing.JPanel;
import static notification.MainForm.lbl_jumlah_notif;

/**
 *
 * @author ASUS
 */
public class notifikasi extends javax.swing.JPanel {

    /**
     * Creates new form notifikasi
     */
    public notifikasi() {
        initComponents();
        tampilNotif();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notifikasi = new javax.swing.JPanel();
        btn_laporan = new javax.swing.JLabel();
        btn_semua = new javax.swing.JLabel();
        btn_penitipan = new javax.swing.JLabel();
        btn_stok = new javax.swing.JLabel();
        tab_laporan_click = new javax.swing.JLabel();
        tab_penitipan_click = new javax.swing.JLabel();
        tab_stok_click = new javax.swing.JLabel();
        tab_semua_click = new javax.swing.JLabel();
        tab_laporan = new javax.swing.JLabel();
        tab_penitipan = new javax.swing.JLabel();
        tab_stok = new javax.swing.JLabel();
        tab_semua = new javax.swing.JLabel();
        ScrollPaneNotif = new javax.swing.JScrollPane();
        bg = new javax.swing.JLabel();

        setLayout(null);

        notifikasi.setBackground(new java.awt.Color(235, 237, 241));
        notifikasi.setLayout(null);

        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });
        notifikasi.add(btn_laporan);
        btn_laporan.setBounds(290, 60, 70, 20);

        btn_semua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_semuaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_semuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_semuaMouseExited(evt);
            }
        });
        notifikasi.add(btn_semua);
        btn_semua.setBounds(50, 60, 70, 20);

        btn_penitipan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_penitipanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_penitipanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_penitipanMouseExited(evt);
            }
        });
        notifikasi.add(btn_penitipan);
        btn_penitipan.setBounds(210, 60, 70, 20);

        btn_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_stokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_stokMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_stokMouseExited(evt);
            }
        });
        notifikasi.add(btn_stok);
        btn_stok.setBounds(130, 60, 70, 20);
        notifikasi.add(tab_laporan_click);
        tab_laporan_click.setBounds(290, 56, 0, 0);
        notifikasi.add(tab_penitipan_click);
        tab_penitipan_click.setBounds(210, 56, 0, 0);
        notifikasi.add(tab_stok_click);
        tab_stok_click.setBounds(130, 56, 0, 0);

        tab_semua_click.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_semua_clickMouseClicked(evt);
            }
        });
        notifikasi.add(tab_semua_click);
        tab_semua_click.setBounds(51, 56, 0, 0);
        notifikasi.add(tab_laporan);
        tab_laporan.setBounds(290, 56, 0, 0);
        notifikasi.add(tab_penitipan);
        tab_penitipan.setBounds(210, 56, 0, 0);
        notifikasi.add(tab_stok);
        tab_stok.setBounds(130, 56, 0, 0);

        tab_semua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_semuaMouseClicked(evt);
            }
        });
        notifikasi.add(tab_semua);
        tab_semua.setBounds(51, 56, 0, 0);

        ScrollPaneNotif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        notifikasi.add(ScrollPaneNotif);
        ScrollPaneNotif.setBounds(13, 90, 381, 400);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/notifikasi (2).png"))); // NOI18N
        notifikasi.add(bg);
        bg.setBounds(0, -20, 410, 540);

        add(notifikasi);
        notifikasi.setBounds(0, 0, 408, 500);
    }// </editor-fold>//GEN-END:initComponents
    
    public void tampilNotif(){
        JPanel container = new JPanel();
        container.setLayout(null);
//        container.addMouseListener(this);
        try {
            String sql2 = "select * from tb_barang where stok = 0";
            String sql3 = "select * from tb_barang where stok <= 5 and stok > 0";
            java.sql.Connection conn=(Connection)notification.config.configDB();
            java.sql.Statement stm2=conn.createStatement();
            java.sql.Statement stm3=conn.createStatement();
            java.sql.ResultSet res2=stm2.executeQuery(sql2);
            java.sql.ResultSet res3=stm3.executeQuery(sql3);
            int baris1 = 0;
            int baris2 = 0;


                while (res3.next()){
                    String nama_barang = res3.getString(2);
                    String stok_barang = res3.getString(6);
                    notification.item_notif pn = new notification.item_notif("Stok "+nama_barang+" Hampir Habis","Stok "+nama_barang+" Anda sebanyak "+stok_barang+", segera restock agar produk tidak kehabisan ");
                    pn.setSize(381,84);
                    pn.addMouseListener(new notification.PanelClikLisneter(pn));
//                    JOptionPane.showMessageDialog(null, nama_barang);
                    pn.setLocation(0, baris1* 80); 
                    baris1++;
                    container.add(pn);  
                }
                while (res2.next()){
                    String nama_barang = res2.getString(2);
                    String stok_barang = res2.getString(6);
                    notification.item_notif pn = new notification.item_notif(""+nama_barang+" Telah Habis","Stok "+nama_barang+" habis, segera restock agar dapat dijual kembali");
                    pn.setSize(381,84);
                    pn.addMouseListener(new notification.PanelClikLisneter(pn));
                    container.add(pn);
                    pn.setLocation(0, (baris1 + baris2) * 80); 
                    baris2++;
                    container.add(pn);
                }

            container.setPreferredSize(new Dimension(381,(baris1 + baris2) * 80));
            ScrollPaneNotif.setViewportView(container); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        tab_laporan_click.setVisible(true);
        tab_semua_click.setVisible(false);
        tab_stok_click.setVisible(false);
        tab_penitipan_click.setVisible(false);
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        tab_laporan.setVisible(true);
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        tab_laporan.setVisible(false);
    }//GEN-LAST:event_btn_laporanMouseExited

    private void btn_semuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_semuaMouseClicked
        tab_semua_click.setVisible(true);
        tab_stok_click.setVisible(false);
        tab_penitipan_click.setVisible(false);
        tab_laporan_click.setVisible(false);
    }//GEN-LAST:event_btn_semuaMouseClicked

    private void btn_semuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_semuaMouseEntered
        tab_semua.setVisible(true);
    }//GEN-LAST:event_btn_semuaMouseEntered

    private void btn_semuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_semuaMouseExited
        tab_semua.setVisible(false);
    }//GEN-LAST:event_btn_semuaMouseExited

    private void btn_penitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penitipanMouseClicked
        tab_penitipan_click.setVisible(true);
        tab_semua_click.setVisible(false);
        tab_stok_click.setVisible(false);
        tab_laporan_click.setVisible(false);
    }//GEN-LAST:event_btn_penitipanMouseClicked

    private void btn_penitipanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penitipanMouseEntered
        tab_penitipan.setVisible(true);
    }//GEN-LAST:event_btn_penitipanMouseEntered

    private void btn_penitipanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penitipanMouseExited
        tab_penitipan.setVisible(false);
    }//GEN-LAST:event_btn_penitipanMouseExited

    private void btn_stokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stokMouseClicked
        tab_stok_click.setVisible(true);
        tab_semua_click.setVisible(false);
        tab_penitipan_click.setVisible(false);
        tab_laporan_click.setVisible(false);
    }//GEN-LAST:event_btn_stokMouseClicked

    private void btn_stokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stokMouseEntered
        tab_stok.setVisible(true);
    }//GEN-LAST:event_btn_stokMouseEntered

    private void btn_stokMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stokMouseExited
        tab_stok.setVisible(false);
    }//GEN-LAST:event_btn_stokMouseExited

    private void tab_semua_clickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_semua_clickMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tab_semua_clickMouseClicked

    private void tab_semuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_semuaMouseClicked

    }//GEN-LAST:event_tab_semuaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneNotif;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel btn_laporan;
    private javax.swing.JLabel btn_penitipan;
    private javax.swing.JLabel btn_semua;
    private javax.swing.JLabel btn_stok;
    private javax.swing.JPanel notifikasi;
    private javax.swing.JLabel tab_laporan;
    private javax.swing.JLabel tab_laporan_click;
    private javax.swing.JLabel tab_penitipan;
    private javax.swing.JLabel tab_penitipan_click;
    private javax.swing.JLabel tab_semua;
    private javax.swing.JLabel tab_semua_click;
    private javax.swing.JLabel tab_stok;
    private javax.swing.JLabel tab_stok_click;
    // End of variables declaration//GEN-END:variables
}
