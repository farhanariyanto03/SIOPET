
package siopet.Admin;

import siopet.*;
import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class form_trankPenitipanPengambilanHwnAdmin extends javax.swing.JFrame {

    
    private static final String character = "0123456789";
    private static final int codeLenght = 7;
    
    public form_trankPenitipanPengambilanHwnAdmin() {
        initComponents();
//        panel_pengambilanHewan ph = new panel_pengambilanHewan();
//        setContentPane(ph);
//        datatabel3();
        
        //TABLE KERANJANG
        spTable_pengambilan.setVerticalScrollBar(scrollBarCustom1);
//        spTable.setVerticalScrollBar(new ScrollBar());
        spTable_pengambilan.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable_pengambilan.getViewport().setBackground(Color.WHITE);
        
        popUp_keluar2.setVisible(false);
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
    
    
    private void matchBarcodeWithData(String barcode) {
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/siopet", "root", "");

            // Mengambil data dari database berdasarkan barcode
            String query = "SELECT tb_transaksi_gromming.id_transaksi_gromming, tb_pelanggan.nama_pelanggan, tb_hewan.nama_hewan, tb_transaksi_gromming.bayar, tb_transaksi_gromming.status \n" +
                            "from tb_transaksi_gromming \n" +
                            "JOIN tb_pelanggan ON tb_pelanggan.id_pelanggan = tb_transaksi_gromming.id_pelanggan \n" +
                            "JOIN tb_hewan ON tb_hewan.id_hewan = tb_transaksi_gromming.id_hewan \n" +
                            "WHERE tb_transaksi_gromming.id_transaksi_gromming =  ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, barcode);
            ResultSet resultSet = statement.executeQuery();
            
            // Menambahkan data ke dalam JTable
            while (resultSet.next()) {
                String data1 = resultSet.getString("id_transaksi_gromming");
                String data2 = resultSet.getString("nama_pelanggan");
                String data3 = resultSet.getString("nama_hewan");
                String data4 = resultSet.getString("bayar");
                String data5 = "SUDAH DIAMBIL";
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
        lbl_dataPelanggan = new javax.swing.JLabel();
        lbl_scan = new javax.swing.JLabel();
        lbl_cariBarang = new javax.swing.JLabel();
        roundedPanel1 = new panelRounded.RoundedPanel();
        spTable_pengambilan = new javax.swing.JScrollPane();
        tbl_keranjang = new com.raven.swing.Table();
        scrollBarCustom1 = new scrollbar.custom.ScrollBarCustom();
        lbl_pengambilan = new javax.swing.JLabel();
        lbl_transaksi = new javax.swing.JLabel();
        lbl_pengambilan1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_tranksaksiBarang = new javax.swing.JLabel();
        lbl_trankPenitipan = new javax.swing.JLabel();
        lbl_trankGromming = new javax.swing.JLabel();
        txtf_barcode = new javax.swing.JTextField();
        lbl_formStok = new javax.swing.JLabel();
        lbl_formTransaksi = new javax.swing.JLabel();
        lbl_formLaporan = new javax.swing.JLabel();
        lbl_formDashboard = new javax.swing.JLabel();
        lbl_formLayanan = new javax.swing.JLabel();
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

        lbl_dataPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dataPelangganMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_dataPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 160, 250, 40));

        lbl_scan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_scanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 160, 170, 40));

        lbl_cariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cariBarangMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 160, 180, 40));

        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spTable_pengambilan.setBorder(null);

        tbl_keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Nama Pelanggan", "Nama Hewan", "Nama Hewan", "Status"
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
        spTable_pengambilan.setViewportView(tbl_keranjang);

        roundedPanel1.add(spTable_pengambilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1410, 760));
        roundedPanel1.add(scrollBarCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 160, 10, 180));

        lbl_pengambilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pengambilanMouseClicked(evt);
            }
        });
        roundedPanel1.add(lbl_pengambilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 170, 40));

        lbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_transaksiMouseClicked(evt);
            }
        });
        roundedPanel1.add(lbl_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 180, 40));

        lbl_pengambilan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hover btn pengambilan.png"))); // NOI18N
        lbl_pengambilan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pengambilan1MouseClicked(evt);
            }
        });
        roundedPanel1.add(lbl_pengambilan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 190, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btn opsi transaksi.png"))); // NOI18N
        roundedPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 6, 380, 50));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 1450, 840));

        lbl_tranksaksiBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_tranksaksiBarangMouseClicked(evt);
            }
        });
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

        txtf_barcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtf_barcodeMouseClicked(evt);
            }
        });
        txtf_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_barcodeActionPerformed(evt);
            }
        });
        txtf_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtf_barcodeKeyPressed(evt);
            }
        });
        getContentPane().add(txtf_barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 160, -1));

        lbl_formStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_formStokMouseEntered(evt);
            }
        });
        getContentPane().add(lbl_formStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 340, 50));

        lbl_formTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 340, 50));

        lbl_formLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_formLaporanMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_formLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 340, 50));

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

        Logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout2MouseClicked(evt);
            }
        });
        getContentPane().add(Logout2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 980, 330, 50));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/transaksi - penitipanAdmin.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_trankPenitipanPengambilanHwnAdmin.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void lbl_cariBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cariBarangMouseClicked
        
    }//GEN-LAST:event_lbl_cariBarangMouseClicked

    private void lbl_dataPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dataPelangganMouseClicked
       
    }//GEN-LAST:event_lbl_dataPelangganMouseClicked

    private void tbl_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_keranjangMouseClicked
       
    }//GEN-LAST:event_tbl_keranjangMouseClicked

    private void tbl_keranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_keranjangMouseEntered

    }//GEN-LAST:event_tbl_keranjangMouseEntered

    private void tbl_keranjangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_keranjangKeyReleased
        
    }//GEN-LAST:event_tbl_keranjangKeyReleased

    private void lbl_scanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_scanMouseClicked
        txtf_barcode.requestFocus();
    }//GEN-LAST:event_lbl_scanMouseClicked

    private void lbl_trankGrommingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_trankGrommingMouseClicked
        this.dispose();
        new form_transaksiGrommingAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_trankGrommingMouseClicked

    private void lbl_trankPenitipanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_trankPenitipanMouseClicked
        this.dispose();
        new form_transaksiPenitipanAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_trankPenitipanMouseClicked

    private void lbl_pengambilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pengambilanMouseClicked
        
    }//GEN-LAST:event_lbl_pengambilanMouseClicked

    private void lbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_transaksiMouseClicked
        this.setVisible(false);
        new form_transaksiPenitipanAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_transaksiMouseClicked

    private void lbl_pengambilan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pengambilan1MouseClicked
        //        panel_pengambilanHewan pph = new panel_pengambilanHewan();
        //        form_transaksiGromming ftg = new form_transaksiGromming();
        //        ftg.getContentPane().add(pph);
    }//GEN-LAST:event_lbl_pengambilan1MouseClicked

    private void txtf_barcodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtf_barcodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_barcodeMouseClicked

    private void txtf_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_barcodeActionPerformed

    private void txtf_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_barcodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String barcode = txtf_barcode.getText();
            matchBarcodeWithData(barcode);

            int rows = tbl_keranjang.getRowCount();
            for(int i = 0; i < rows; i++){
                try {
                    String sql = "UPDATE tb_transaksi_gromming SET status = '"+tbl_keranjang.getValueAt(i, 4)+"' "
                    + "WHERE tb_transaksi_gromming.id_transaksi_gromming = '"+txtf_barcode.getText()+"'";
                    java.sql.Connection conn = (Connection)config.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "STATUS SUDAH BERUBAH");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                txtf_barcode.setText("");  // Mengosongkan JTextField setelah pemindaian
            }
        }
    }//GEN-LAST:event_txtf_barcodeKeyPressed

    private void lbl_tranksaksiBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_tranksaksiBarangMouseClicked
        this.setVisible(false);
        new form_transaksiBarangAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_tranksaksiBarangMouseClicked

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

    private void lbl_formStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseClicked
        this.setVisible(false);
        new form_stok().setVisible(true);
    }//GEN-LAST:event_lbl_formStokMouseClicked

    private void lbl_formStokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formStokMouseEntered

    }//GEN-LAST:event_lbl_formStokMouseEntered

    private void lbl_formTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formTransaksiMouseClicked
        this.setVisible(false);
        new form_transaksiBarangAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formTransaksiMouseClicked

    private void lbl_formLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLaporanMouseClicked
        this.setVisible(false);
        new form_laporanBerlangsungAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formLaporanMouseClicked

    private void lbl_formDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formDashboardMouseClicked
        this.setVisible(false);
        new form_dashboardAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formDashboardMouseClicked

    private void lbl_formLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_formLayananMouseClicked
        this.setVisible(false);
        new form_layananAdmin().setVisible(true);
    }//GEN-LAST:event_lbl_formLayananMouseClicked

    private void Logout2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout2MouseClicked
        popUp_keluar2.setVisible(true);
    }//GEN-LAST:event_Logout2MouseClicked

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
            java.util.logging.Logger.getLogger(form_trankPenitipanPengambilanHwnAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_trankPenitipanPengambilanHwnAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_trankPenitipanPengambilanHwnAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_trankPenitipanPengambilanHwnAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new form_trankPenitipanPengambilanHwnAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout2;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_keluar2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl_cariBarang;
    private javax.swing.JLabel lbl_dataPelanggan;
    private javax.swing.JLabel lbl_formDashboard;
    private javax.swing.JLabel lbl_formLaporan;
    private javax.swing.JLabel lbl_formLayanan;
    private javax.swing.JLabel lbl_formStok;
    private javax.swing.JLabel lbl_formTransaksi;
    private javax.swing.JLabel lbl_pengambilan;
    private javax.swing.JLabel lbl_pengambilan1;
    private javax.swing.JLabel lbl_scan;
    private javax.swing.JLabel lbl_trankGromming;
    private javax.swing.JLabel lbl_trankPenitipan;
    private javax.swing.JLabel lbl_tranksaksiBarang;
    private javax.swing.JLabel lbl_transaksi;
    private javax.swing.JPanel popUp_keluar2;
    private panelRounded.RoundedPanel roundedPanel1;
    private scrollbar.custom.ScrollBarCustom scrollBarCustom1;
    private javax.swing.JScrollPane spTable_pengambilan;
    private com.raven.swing.Table tbl_keranjang;
    private javax.swing.JTextField txtf_barcode;
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