/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cak-ust
 */
public class DaftarMenu extends javax.swing.JFrame {

    Connection koneksi = null;

    /**
     * Creates new form DaftarMenu
     */
    public DaftarMenu() {
        initComponents();

        try {
            Class.forName("org.postgresql.Driver");
            koneksi = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/restoran", "test", "test");

            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int row = jTable1.getSelectedRow();

                    if (row >= 0) {
                        txtKode.setText(jTable1.getValueAt(row, 0).toString());
                        txtNama.setText(jTable1.getValueAt(row, 1).toString());
                        txtKeterangan.setText(jTable1.getValueAt(row, 2).toString());
                        txtKode.setEnabled(false);
                    } else {
                        baru();
                    }
                }
            });

            tampilkanData();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Tidak ada Class!");
            Logger.getLogger(DaftarMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "koneksi gagal!\n" + ex.getMessage());
            Logger.getLogger(DaftarMenu.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 310, 190);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel1.setText("Keterangan");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 64, 70, 15);

        jLabel2.setText("Kode");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 19, 60, 15);

        jLabel3.setText("Nama");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 39, 70, 15);

        txtKode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtKode);
        txtKode.setBounds(105, 20, 75, 16);

        txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtNama);
        txtNama.setBounds(105, 45, 240, 16);

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        txtKeterangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtKeterangan);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(105, 70, 240, 75);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(335, 10, 355, 160);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(608, 175, 85, 23);

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus);
        btnHapus.setBounds(495, 175, 95, 23);

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(335, 175, 90, 23);

        setBounds(0, 0, 722, 314);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

        try {
            if (txtKode.isEnabled()==true) {
                String sql = "insert into m_menu(kode, nama, keterangan) values\n"
                        + "(?, ?, ?)";

                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, txtKode.getText());
                ps.setString(2, txtNama.getText());
                ps.setString(3, txtKeterangan.getText());

                ps.executeUpdate();
                ps.close();
                tampilkanData();
                JOptionPane.showMessageDialog(this, "Data tersimpan");
            }else{
                String sql = "update m_menu set keterangan=?, nama=? where kode=?";

                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, txtKeterangan.getText());
                ps.setString(2, txtNama.getText());
                ps.setString(3, txtKode.getText());

                ps.executeUpdate();
                ps.close();
                tampilkanData();
                JOptionPane.showMessageDialog(this, "Data tersimpan");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal simpan " + ex.getMessage());
            Logger.getLogger(DaftarMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (txtKode.getText().equalsIgnoreCase("")) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "anda yakin untuk menghapus menu '" + txtNama.getText() + "'?", "Hapus", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                int i = koneksi.createStatement().executeUpdate("delete from m_menu where kode='" + txtKode.getText() + "'");
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Hapus data sukses!");
                    tampilkanData();
                }
            } catch (SQLException se) {

            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        baru();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DaftarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DaftarMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables

    private void tampilkanData() {
        try {
            ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
            ResultSet rs = koneksi.createStatement().executeQuery("select * from m_menu");
            while (rs.next()) {
                ((DefaultTableModel) jTable1.getModel()).addRow(new Object[]{
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getString("keterangan"),});
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaftarMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void baru() {
        txtKode.setEnabled(true);

        txtKode.setText("");
        txtNama.setText("");
        txtKeterangan.setText("");
        txtKode.requestFocus();
    }
}
