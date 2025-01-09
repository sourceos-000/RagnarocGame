/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gamerpg;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Surya
 */
public class HeroesData extends javax.swing.JFrame {

    private static final String username = "root";
    private static final String password = "m4t4h4r1";
    private static final String dataConn = "jdbc:mysql://localhost:3306/gamerpg?useSSL=false";

    Connection sqlConn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int q, i, id, deleteItem;

    /**
     * Creates new form database_animals
     */
    public HeroesData() {
        initComponents();
        upDateDB();

        // Listener untuk tombol Delete
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        // Listener untuk tombol Update
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

// Listener untuk tabel jTable1
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    jButtonDelete.setEnabled(true);
                    jButtonUpdate.setEnabled(true);
                } else {
                    jButtonDelete.setEnabled(false);
                    jButtonUpdate.setEnabled(false);
                }
            }
        });

        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);

    }

    public void upDateDB() {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            sqlConn = DriverManager.getConnection(dataConn, username, password);

            // Buat prepared statement untuk mengambil data dari tabel heroes
            pst = sqlConn.prepareStatement("SELECT * FROM heroes");

            // Eksekusi query
            rs = pst.executeQuery();

            // Ambil metadata dari ResultSet
            ResultSetMetaData stData = rs.getMetaData();

            // Ambil jumlah kolom
            int q = stData.getColumnCount();

            // Ambil model tabel dari jTable1
            DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();
            RecordTable.setRowCount(0); // Bersihkan data sebelumnya

            // Loop melalui setiap baris di ResultSet
            while (rs.next()) {
                Vector<String> columnData = new Vector<>();

                // Tambahkan data dari setiap kolom ke dalam Vector
                columnData.add(rs.getString("id_character")); // ID Character
                columnData.add(rs.getString("name_heroes"));  // Nama Hero
                columnData.add(rs.getString("role"));         // Role
                columnData.add(rs.getString("health"));       // Health
                columnData.add(rs.getString("attack_power")); // Attack Power
                columnData.add(rs.getString("level"));        // Level
                columnData.add(rs.getString("exp"));          // EXP

                // Tambahkan baris ke dalam model tabel
                RecordTable.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "JDBC Driver tidak ditemukan: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        } finally {
            // Tutup koneksi dan statement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (sqlConn != null) {
                    sqlConn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error saat menutup koneksi: " + ex.getMessage());
            }
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

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextHealth = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextAttackPower = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextLevel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextCharacterID = new javax.swing.JTextField();
        jTextName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextEXP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Add = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jLabel3.setText("Role");

        jLabel4.setText("Health");

        jLabel5.setText("Attack Power");

        jLabel6.setText("Level");

        jLabel1.setText("Character Code");

        jLabel2.setText("Name");

        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Attacker", "Tank", "Healer" }));
        jComboBoxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRoleActionPerformed(evt);
            }
        });

        jLabel8.setText("Exp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextHealth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextAttackPower, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextLevel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextCharacterID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextEXP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextCharacterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextAttackPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextEXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(284, 284, 284))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Heroes", "Name", "Role", "Health", "AttackPower", "Level", "EXP"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DATABASE HEROES");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Add)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdate)
                .addGap(18, 18, 18)
                .addComponent(jButtonDelete)
                .addGap(18, 18, 18)
                .addComponent(jButtonReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            sqlConn = DriverManager.getConnection(dataConn, username, password);

            // Buat prepared statement untuk menambahkan data ke tabel heroes
            pst = sqlConn.prepareStatement("INSERT INTO heroes (id_character, name_heroes, role, health, attack_power, level, exp) VALUES (?, ?, ?, ?, ?, ?, ?)");

            // Set nilai parameter dari komponen GUI
            pst.setString(1, jTextCharacterID.getText()); // id_character
            pst.setString(2, jTextName.getText());         // name_heroes
            pst.setString(3, (String) jComboBoxRole.getSelectedItem()); // role
            pst.setInt(4, Integer.parseInt(jTextHealth.getText()));     // health
            pst.setInt(5, Integer.parseInt(jTextAttackPower.getText())); // attack_power
            pst.setInt(6, Integer.parseInt(jTextLevel.getText()));       // level
            pst.setInt(7, Integer.parseInt(jTextEXP.getText())); // exp default (0 karena baru ditambahkan)

            // Eksekusi query
            pst.executeUpdate();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

            // Perbarui tampilan tabel
            upDateDB();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "JDBC Driver tidak ditemukan: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input angka tidak valid: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            // Tutup koneksi dan statement
            try {
                if (pst != null) {
                    pst.close();
                }
                if (sqlConn != null) {
                    sqlConn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saat menutup koneksi: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_AddActionPerformed


    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            // Ambil nilai id_character dari baris yang dipilih
            String id = jTable1.getValueAt(selectedRow, 0).toString(); // Kolom pertama adalah id_character

            // Tampilkan dialog konfirmasi
            int option = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                try {
                    // Load JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Buat koneksi ke database
                    sqlConn = DriverManager.getConnection(dataConn, username, password);

                    // Buat prepared statement untuk menghapus data
                    pst = sqlConn.prepareStatement("DELETE FROM heroes WHERE id_character = ?");
                    pst.setString(1, id); // Set nilai id_character sebagai parameter

                    // Eksekusi query
                    pst.executeUpdate();

                    // Tampilkan pesan sukses
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");

                    // Perbarui tampilan tabel
                    upDateDB();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Error: JDBC Driver tidak ditemukan.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error menghapus data: " + ex.getMessage());
                } finally {
                    // Tutup koneksi dan statement
                    try {
                        if (pst != null) {
                            pst.close();
                        }
                        if (sqlConn != null) {
                            sqlConn.close();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error saat menutup koneksi: " + ex.getMessage());
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus.");
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("update heroes set id_character = ?, name_heroes = ?,role = ?,health = ?,"
                    + "attack_power = ?, level = ?, exp  = ? where id_character = ?");

            pst.setString(1, jTextCharacterID.getText()); // id_character
            pst.setString(2, jTextName.getText());         // name_heroes
            pst.setString(3, (String) jComboBoxRole.getSelectedItem()); // role
            pst.setInt(4, Integer.parseInt(jTextHealth.getText()));     // health
            pst.setInt(5, Integer.parseInt(jTextAttackPower.getText())); // attack_power
            pst.setInt(6, Integer.parseInt(jTextLevel.getText()));       // level
            pst.setInt(7, Integer.parseInt(jTextEXP.getText()));
            pst.setString(8, jTextCharacterID.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            upDateDB();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "JDBC Driver tidak ditemukan: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error SQL: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input angka tidak valid: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            // Tutup koneksi dan statement
            try {
                if (pst != null) {
                    pst.close();
                }
                if (sqlConn != null) {
                    sqlConn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saat menutup koneksi: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        jTextCharacterID.setText("");
        jTextName.setText("");
        jComboBoxRole.setSelectedIndex(0);
        jTextHealth.setText("");
        jTextAttackPower.setText("");
        jTextLevel.setText("");
        jTextEXP.setText("");
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jComboBoxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRoleActionPerformed


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            // Ambil model tabel dari jTable1
            DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();

            // Ambil baris yang dipilih
            int SelectedRows = jTable1.getSelectedRow();

            // Set nilai ke komponen form
            jTextCharacterID.setText(RecordTable.getValueAt(SelectedRows, 0).toString()); // id_character (kolom 0)
            jTextName.setText(RecordTable.getValueAt(SelectedRows, 1).toString());        // name_heroes (kolom 1)
            jComboBoxRole.setSelectedItem(RecordTable.getValueAt(SelectedRows, 2).toString()); // role (kolom 2)
            jTextHealth.setText(RecordTable.getValueAt(SelectedRows, 3).toString());      // health (kolom 3)
            jTextAttackPower.setText(RecordTable.getValueAt(SelectedRows, 4).toString()); // attack_power (kolom 4)
            jTextLevel.setText(RecordTable.getValueAt(SelectedRows, 5).toString());       // level (kolom 5)
            jTextEXP.setText(RecordTable.getValueAt(SelectedRows, 6).toString());

            // Jika ada kolom exp, bisa ditambahkan:
            // jTextExp.setText(RecordTable.getValueAt(SelectedRows, 6).toString()); // exp (kolom 6)
        } catch (Exception e) {
            // Tampilkan pesan error jika terjadi exception
            JOptionPane.showMessageDialog(this, "Error selecting row: " + e.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(HeroesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HeroesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HeroesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HeroesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HeroesData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextAttackPower;
    private javax.swing.JTextField jTextCharacterID;
    private javax.swing.JTextField jTextEXP;
    private javax.swing.JTextField jTextHealth;
    private javax.swing.JTextField jTextLevel;
    private javax.swing.JTextField jTextName;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
