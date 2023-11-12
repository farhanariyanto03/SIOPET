/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package siopet;

/**
 *
 * @author nico flassy
 */
import java.awt.Color;
public class card_barang extends javax.swing.JPanel {


    public card_barang() {
        initComponents();
        gambar_kucing.setVisible(false);
        gambar_anjing.setVisible(false);
        gambar_domestik.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gambar_domestik = new javax.swing.JLabel();
        gambar_anjing = new javax.swing.JLabel();
        gambar_kucing = new javax.swing.JLabel();
        harga_barang = new javax.swing.JLabel();
        keterangan = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(214, 217, 224));
        setLayout(null);

        gambar_domestik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/domestic.png"))); // NOI18N
        add(gambar_domestik);
        gambar_domestik.setBounds(176, 120, 30, 30);

        gambar_anjing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dog.png"))); // NOI18N
        add(gambar_anjing);
        gambar_anjing.setBounds(176, 120, 30, 30);

        gambar_kucing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cat.png"))); // NOI18N
        add(gambar_kucing);
        gambar_kucing.setBounds(176, 120, 30, 30);

        harga_barang.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        harga_barang.setForeground(new java.awt.Color(51, 51, 51));
        harga_barang.setText("Rp. 45.000");
        add(harga_barang);
        harga_barang.setBounds(50, 220, 140, 25);

        keterangan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        keterangan.setForeground(new java.awt.Color(51, 51, 51));
        keterangan.setText("Keterangan");
        add(keterangan);
        keterangan.setBounds(30, 180, 120, 25);

        nama.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nama.setText("Nama Barang");
        add(nama);
        nama.setBounds(30, 160, 150, 27);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/card barang.png"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(-70, 0, 290, 260);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel gambar_anjing;
    public static javax.swing.JLabel gambar_domestik;
    public static javax.swing.JLabel gambar_kucing;
    public static javax.swing.JLabel harga_barang;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel keterangan;
    public static javax.swing.JLabel nama;
    // End of variables declaration//GEN-END:variables
}
