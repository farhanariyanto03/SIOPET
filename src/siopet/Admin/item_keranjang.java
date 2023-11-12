/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package siopet.Admin;

import siopet.*;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author nico flassy
 */

public class item_keranjang extends javax.swing.JPanel {
    public int jumlah = 0;
    
    public item_keranjang(String jenis_layanan, String kategori, int harga, int jumlahBarang) {
        initComponents();
        cart_jenis_layanan.setText(jenis_layanan);
        cart_kategori.setText(kategori);
        cart_harga.setText(String.valueOf("Rp."+harga));
        cart_jumlah.setText(String.valueOf(jumlahBarang));
        
        btn_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jumlah = jumlah - 1;
                cart_jumlah.setText(String.valueOf(jumlah));
            }
        });
        
        btn_plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jumlah = jumlah + 1;
                cart_jumlah.setText(String.valueOf(jumlah));
            }
        });
    }
    
    public interface ItemKeranjangListener {
        void addMouseListener(MouseListener listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_minus = new javax.swing.JLabel();
        btn_plus = new javax.swing.JLabel();
        cart_jumlah = new javax.swing.JLabel();
        cart_harga = new javax.swing.JLabel();
        cart_kategori = new javax.swing.JLabel();
        slash = new javax.swing.JLabel();
        gambar_domestik = new javax.swing.JLabel();
        cart_jenis_layanan = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setLayout(null);

        btn_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_minusMouseClicked(evt);
            }
        });
        add(btn_minus);
        btn_minus.setBounds(310, 40, 30, 30);

        btn_plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_plusMouseClicked(evt);
            }
        });
        add(btn_plus);
        btn_plus.setBounds(426, 40, 30, 30);

        cart_jumlah.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        cart_jumlah.setForeground(new java.awt.Color(51, 51, 51));
        cart_jumlah.setText("1");
        add(cart_jumlah);
        cart_jumlah.setBounds(376, 44, 20, 25);

        cart_harga.setBackground(new java.awt.Color(80, 80, 80));
        cart_harga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cart_harga.setForeground(new java.awt.Color(80, 80, 80));
        cart_harga.setText("Rp 50.000");
        add(cart_harga);
        cart_harga.setBounds(136, 70, 120, 25);

        cart_kategori.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cart_kategori.setForeground(new java.awt.Color(114, 114, 114));
        cart_kategori.setText("Makanan");
        add(cart_kategori);
        cart_kategori.setBounds(180, 46, 120, 25);

        slash.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        slash.setForeground(new java.awt.Color(153, 153, 153));
        slash.setText("|");
        add(slash);
        slash.setBounds(168, 44, 10, 25);

        gambar_domestik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cat.png"))); // NOI18N
        add(gambar_domestik);
        gambar_domestik.setBounds(134, 44, 30, 30);

        cart_jenis_layanan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        cart_jenis_layanan.setForeground(new java.awt.Color(51, 51, 51));
        cart_jenis_layanan.setText("Jenis Layanan");
        add(cart_jenis_layanan);
        cart_jenis_layanan.setBounds(136, 20, 150, 25);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/item keranjang.png"))); // NOI18N
        add(bg);
        bg.setBounds(0, 0, 489, 105);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_plusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_plusMouseClicked

    }//GEN-LAST:event_btn_plusMouseClicked

    private void btn_minusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minusMouseClicked

    }//GEN-LAST:event_btn_minusMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel btn_minus;
    private javax.swing.JLabel btn_plus;
    public static javax.swing.JLabel cart_harga;
    public static javax.swing.JLabel cart_jenis_layanan;
    public static javax.swing.JLabel cart_jumlah;
    public static javax.swing.JLabel cart_kategori;
    public static javax.swing.JLabel gambar_domestik;
    private javax.swing.JLabel slash;
    // End of variables declaration//GEN-END:variables
}
