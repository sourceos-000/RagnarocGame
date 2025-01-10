/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gamerpg;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author RIZKI ABD RAHMAN
 */
public class TeamBattle extends javax.swing.JFrame {

    private static final String username = "root";
    private static final String password = "Lann@098123";
    private static final String dataConn = "jdbc:mysql://localhost:3306/gamerpg?useSSL=false";

    Connection sqlConn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form NewJFrame
     */
    public TeamBattle() {

        //this.playerTeam = playerTeam;
        initComponents();

        // Ambil data dari database dan set ke textfield
        loadDataFromDatabase();
        TextPlayerHeroes1.setEnabled(false);
        TextPlayerHeroes2.setEnabled(false);
        TextPlayerHeroes3.setEnabled(false);
        TextPlayerWeapon1.setEnabled(false);
        TextPlayerWeapon2.setEnabled(false);
        TextPlayerWeapon3.setEnabled(false);
        HpPlayerHero1.setEnabled(false);
        HpPlayerHero2.setEnabled(false);
        HpPlayerHero3.setEnabled(false);

        TextEnemyHero1.setEnabled(false);
        TextEnemyHero2.setEnabled(false);
        TextEnemyHero3.setEnabled(false);
        TextEnemyWeapon1.setEnabled(false);
        TextEnemyWeapon2.setEnabled(false);
        TextEnemyWeapon3.setEnabled(false);
        HpEnemyHero1.setEnabled(false);
        HpEnemyHero2.setEnabled(false);
        HpEnemyHero3.setEnabled(false);

    }

    private List<Hero> playerTeam;
    private List<Hero> enemyTeam;

    private void loadDataFromDatabase() {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            sqlConn = DriverManager.getConnection(dataConn, username, password);

            // Ambil data heroes dan weapon sesuai role
            pst = sqlConn.prepareStatement(
                    "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Attacker' "
                    + "ORDER BY RAND() LIMIT 1) "
                    + "UNION "
                    + "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Tank' "
                    + "ORDER BY RAND() LIMIT 1) "
                    + "UNION "
                    + "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Healer' "
                    + "ORDER BY RAND() LIMIT 1)"
            );
            rs = pst.executeQuery();

            // Inisialisasi playerTeam
            playerTeam = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name_heroes");
                int health = rs.getInt("health");
                int attackPower = rs.getInt("attack_power");
                String weaponName = rs.getString("name_weapon");
                int weaponDamage = rs.getInt("damage");
                WeaponHero weapon = new WeaponHero(weaponName, weaponDamage);
                Hero hero = new Hero(name, health, attackPower, weapon);
                playerTeam.add(hero);
            }

            // Set data heroes, health, dan weapon ke textfield player
            if (playerTeam.size() >= 1) {
                TextPlayerHeroes1.setText(playerTeam.get(0).getName());
                TextPlayerWeapon1.setText(playerTeam.get(0).getWeapon().getName());
                HpPlayerHero1.setText(String.valueOf(playerTeam.get(0).getHealth()));
            }
            if (playerTeam.size() >= 2) {
                TextPlayerHeroes2.setText(playerTeam.get(1).getName());
                TextPlayerWeapon2.setText(playerTeam.get(1).getWeapon().getName());
                HpPlayerHero2.setText(String.valueOf(playerTeam.get(1).getHealth()));
            }
            if (playerTeam.size() >= 3) {
                TextPlayerHeroes3.setText(playerTeam.get(2).getName());
                TextPlayerWeapon3.setText(playerTeam.get(2).getWeapon().getName());
                HpPlayerHero3.setText(String.valueOf(playerTeam.get(2).getHealth()));
            } else {
                JOptionPane.showMessageDialog(this, "Tidak ada data heroes yang ditemukan!");
            }

            //ENEMY
            // Ambil data heroes dan weapon untuk enemy sesuai role
            pst = sqlConn.prepareStatement(
                    "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Attacker' "
                    + "ORDER BY RAND() LIMIT 1) "
                    + "UNION "
                    + "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Tank' "
                    + "ORDER BY RAND() LIMIT 1) "
                    + "UNION "
                    + "(SELECT h.name_heroes, h.role, h.health, h.attack_power, w.name_weapon, w.damage "
                    + "FROM heroes h "
                    + "JOIN weapon w ON h.role = w.role "
                    + "WHERE h.role = 'Healer' "
                    + "ORDER BY RAND() LIMIT 1)"
            );
            rs = pst.executeQuery();

            // Inisialisasi enemyTeam
            enemyTeam = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name_heroes");
                int health = rs.getInt("health");
                int attackPower = rs.getInt("attack_power");
                String weaponName = rs.getString("name_weapon");
                int weaponDamage = rs.getInt("damage");
                WeaponHero weapon = new WeaponHero(weaponName, weaponDamage);
                Hero hero = new Hero(name, health, attackPower, weapon);
                enemyTeam.add(hero);
            }

            // Set data heroes, health, dan weapon ke textfield player
            if (enemyTeam.size() >= 1) {
                TextEnemyHero1.setText(enemyTeam.get(0).getName());
                HpEnemyHero1.setText(String.valueOf(playerTeam.get(0).getHealth()));
                TextEnemyWeapon1.setText(enemyTeam.get(0).getWeapon().getName());
            }
            if (enemyTeam.size() >= 2) {
                TextEnemyHero2.setText(enemyTeam.get(1).getName());
                HpEnemyHero2.setText(String.valueOf(playerTeam.get(1).getHealth()));
                TextEnemyWeapon2.setText(enemyTeam.get(1).getWeapon().getName());
            }
            if (enemyTeam.size() >= 3) {
                TextEnemyHero3.setText(enemyTeam.get(2).getName());
                HpEnemyHero3.setText(String.valueOf(playerTeam.get(2).getHealth()));
                TextEnemyWeapon3.setText(enemyTeam.get(2).getWeapon().getName());
            } else {
                JOptionPane.showMessageDialog(this, "Tidak ada data heroes yang ditemukan!");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            // Tutup koneksi
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
                JOptionPane.showMessageDialog(this, "Error saat menutup koneksi: " + ex.getMessage());
            }
        }
    }

    private void startBattle() {
        // Jalankan battle di thread terpisah agar GUI tetap responsif
        new Thread(() -> {
            // Ambil data tim pemain dan musuh dari database atau objek yang sudah ada
            if (playerTeam == null || enemyTeam == null) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Tim pemain atau musuh belum diinisialisasi!");
                });
                return;
            }

            boolean battleEnded = false;

            while (!battleEnded) {
                // Tim pemain menyerang terlebih dahulu
                for (Hero playerHero : playerTeam) {
                    if (playerHero.getHealth() > 0) { // Cek apakah hero masih hidup
                        Hero enemyHero = getRandomAliveHero(enemyTeam); // Ambil hero musuh yang masih hidup
                        attack(playerHero, enemyHero); // Serang hero musuh
                        if (isTeamDefeated(enemyTeam)) { // Cek apakah tim musuh sudah kalah
                            battleEnded = true;
                            SwingUtilities.invokeLater(() -> {
                                TextResult.append("Pertempuran Selesai!");
                                JOptionPane.showMessageDialog(this, "Selamat! Anda menang!");
                            });
                            loadDataFromDatabase();
                            break;
                        }
                    }
                    // Delay 2 detik sebelum iterasi berikutnya
                    try {
                        Thread.sleep(1000); // Jeda 2 detik
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (battleEnded) {
                    break;
                }

                // Tim musuh menyerang balik
                for (Hero enemyHero : enemyTeam) {
                    if (enemyHero.getHealth() > 0) { // Cek apakah hero musuh masih hidup
                        Hero playerHero = getRandomAliveHero(playerTeam); // Ambil hero pemain yang masih hidup
                        attack(enemyHero, playerHero); // Serang hero pemain
                        if (isTeamDefeated(playerTeam)) { // Cek apakah tim pemain sudah kalah
                            battleEnded = true;
                            SwingUtilities.invokeLater(() -> {
                                TextResult.append("Pertempuran Selesai!");
                                JOptionPane.showMessageDialog(this, "Anda kalah! Coba lagi.");
                            });
                            loadDataFromDatabase();
                            break;
                        }
                    }
                    // Delay 2 detik sebelum iterasi berikutnya
                    try {
                        Thread.sleep(1000); // Jeda 2 detik
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start(); // Jalankan thread
    }

    private Hero getRandomAliveHero(List<Hero> team) {
        List<Hero> aliveHeroes = new ArrayList<>();
        for (Hero hero : team) {
            if (hero.getHealth() > 0) {
                aliveHeroes.add(hero);
            }
        }
        if (!aliveHeroes.isEmpty()) {
            Random random = new Random();
            return aliveHeroes.get(random.nextInt(aliveHeroes.size()));
        }
        return null; // Jika tidak ada hero yang masih hidup
    }

    private boolean isTeamDefeated(List<Hero> team) {
        for (Hero hero : team) {
            if (hero.getHealth() > 0) {
                return false; // Masih ada hero yang hidup
            }
        }
        return true; // Semua hero sudah kalah
    }

    private void attack(Hero attacker, Hero defender) {
        int damage = attacker.getAttackPower(); // Ambil attack power dari penyerang
        defender.takeDamage(damage); // Kurangi HP defender
        // Update battle log di EDT
        SwingUtilities.invokeLater(() -> {
            TextResult.append(attacker.getName() + " menyerang " + defender.getName() + " dan mengurangi " + damage + " HP!\n");
            if (defender.getHealth() <= 0) {
                TextResult.append(defender.getName() + " kalah!\n");
            }
            TextResult.append("\n");
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PlayerHero3 = new javax.swing.JLabel();
        PlayerWeapon3 = new javax.swing.JLabel();
        TextPlayerHeroes3 = new javax.swing.JTextField();
        TextPlayerWeapon3 = new javax.swing.JTextField();
        PlayerWeapon2 = new javax.swing.JLabel();
        PlayerHero2 = new javax.swing.JLabel();
        TextPlayerHeroes2 = new javax.swing.JTextField();
        TextPlayerWeapon2 = new javax.swing.JTextField();
        Team = new javax.swing.JLabel();
        PlayerHero1 = new javax.swing.JLabel();
        TextPlayerHeroes1 = new javax.swing.JTextField();
        PlayerWeapon1 = new javax.swing.JLabel();
        TextPlayerWeapon1 = new javax.swing.JTextField();
        HpPlayerHero1 = new javax.swing.JTextField();
        HpPlayerHero2 = new javax.swing.JTextField();
        HpPlayerHero3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        EnemyHero3 = new javax.swing.JLabel();
        EnemyWeapon3 = new javax.swing.JLabel();
        TextEnemyHero3 = new javax.swing.JTextField();
        TextEnemyWeapon3 = new javax.swing.JTextField();
        EnemyWeapon2 = new javax.swing.JLabel();
        EnemyHero2 = new javax.swing.JLabel();
        TextEnemyHero2 = new javax.swing.JTextField();
        TextEnemyWeapon2 = new javax.swing.JTextField();
        Team1 = new javax.swing.JLabel();
        EnemyHero1 = new javax.swing.JLabel();
        TextEnemyHero1 = new javax.swing.JTextField();
        EnemyWeapon1 = new javax.swing.JLabel();
        TextEnemyWeapon1 = new javax.swing.JTextField();
        HpEnemyHero1 = new javax.swing.JTextField();
        HpEnemyHero2 = new javax.swing.JTextField();
        HpEnemyHero3 = new javax.swing.JTextField();
        Result = new javax.swing.JLabel();
        ButtonMenu = new javax.swing.JButton();
        ButtonBattle = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextResult = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 700));
        setPreferredSize(new java.awt.Dimension(800, 850));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0, 90));
        jPanel4.setForeground(new java.awt.Color(0, 51, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 800));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("BATTLE START!");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255, 20));

        PlayerHero3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/HeroMedusa1.png"))); // NOI18N

        PlayerWeapon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/TongkatSihir1.png"))); // NOI18N

        TextPlayerHeroes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPlayerHeroes3ActionPerformed(evt);
            }
        });

        TextPlayerWeapon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPlayerWeapon3ActionPerformed(evt);
            }
        });

        PlayerWeapon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/Shield1png.png"))); // NOI18N

        PlayerHero2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/HeroPoseidon1png.png"))); // NOI18N

        TextPlayerHeroes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPlayerHeroes2ActionPerformed(evt);
            }
        });

        Team.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Team.setForeground(new java.awt.Color(255, 255, 255));
        Team.setText("Team");

        PlayerHero1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/HeroZeus.png"))); // NOI18N
        PlayerHero1.setFocusable(false);

        TextPlayerHeroes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPlayerHeroes1ActionPerformed(evt);
            }
        });

        PlayerWeapon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/Sword1.png"))); // NOI18N

        HpPlayerHero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HpPlayerHero3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextPlayerHeroes1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerWeapon1)
                    .addComponent(TextPlayerWeapon1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerHero1)
                    .addComponent(HpPlayerHero1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PlayerHero2)
                    .addComponent(TextPlayerHeroes2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPlayerWeapon2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerWeapon2)
                    .addComponent(Team)
                    .addComponent(HpPlayerHero2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PlayerHero3)
                    .addComponent(TextPlayerHeroes3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PlayerWeapon3)
                    .addComponent(TextPlayerWeapon3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpPlayerHero3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(Team)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PlayerHero1)
                    .addComponent(PlayerHero2)
                    .addComponent(PlayerHero3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextPlayerHeroes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPlayerHeroes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPlayerHeroes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PlayerWeapon3)
                    .addComponent(PlayerWeapon2)
                    .addComponent(PlayerWeapon1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextPlayerWeapon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPlayerWeapon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPlayerWeapon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(HpPlayerHero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpPlayerHero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpPlayerHero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255, 20));

        EnemyHero3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/HeroCewe2-0.png"))); // NOI18N

        EnemyWeapon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/Tongkat2.png"))); // NOI18N

        TextEnemyHero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEnemyHero3ActionPerformed(evt);
            }
        });

        TextEnemyWeapon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEnemyWeapon3ActionPerformed(evt);
            }
        });

        EnemyWeapon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/Shield2.png"))); // NOI18N

        EnemyHero2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/Hero2.png"))); // NOI18N

        TextEnemyHero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEnemyHero2ActionPerformed(evt);
            }
        });

        Team1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Team1.setForeground(new java.awt.Color(255, 255, 255));
        Team1.setText("Enemy");

        EnemyHero1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/HeroKratos1-3.png"))); // NOI18N

        TextEnemyHero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEnemyHero1ActionPerformed(evt);
            }
        });

        EnemyWeapon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/WeaponKratospng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextEnemyHero1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnemyWeapon1)
                    .addComponent(TextEnemyWeapon1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnemyHero1)
                    .addComponent(HpEnemyHero1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(EnemyHero2)
                    .addComponent(TextEnemyHero2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEnemyWeapon2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnemyWeapon2)
                    .addComponent(Team1)
                    .addComponent(HpEnemyHero2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(EnemyHero3)
                    .addComponent(TextEnemyHero3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnemyWeapon3)
                    .addComponent(TextEnemyWeapon3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpEnemyHero3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(Team1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(EnemyHero3)
                    .addComponent(EnemyHero2)
                    .addComponent(EnemyHero1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextEnemyHero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEnemyHero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEnemyHero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(EnemyWeapon3)
                    .addComponent(EnemyWeapon2)
                    .addComponent(EnemyWeapon1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TextEnemyWeapon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEnemyWeapon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEnemyWeapon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HpEnemyHero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpEnemyHero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HpEnemyHero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        Result.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Result.setForeground(new java.awt.Color(255, 255, 255));
        Result.setText("Battle Logs :");

        ButtonMenu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonMenu.setText("Menu");
        ButtonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMenuActionPerformed(evt);
            }
        });

        ButtonBattle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonBattle.setText("Battle");
        ButtonBattle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBattleActionPerformed(evt);
            }
        });

        TextResult.setColumns(20);
        TextResult.setRows(5);
        jScrollPane1.setViewportView(TextResult);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Result)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ButtonBattle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Result)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(ButtonBattle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonMenu))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 700, 790));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gamerpg/image/bg3.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 800, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TextPlayerHeroes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPlayerHeroes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPlayerHeroes1ActionPerformed

    private void TextPlayerHeroes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPlayerHeroes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPlayerHeroes2ActionPerformed

    private void TextPlayerHeroes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPlayerHeroes3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPlayerHeroes3ActionPerformed

    private void TextPlayerWeapon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPlayerWeapon3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPlayerWeapon3ActionPerformed

    private void ButtonBattleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBattleActionPerformed
        // TODO add your handling code here:
        startBattle(); // Memulai pertempuran saat tombol Battle diklik
    }//GEN-LAST:event_ButtonBattleActionPerformed

    private void ButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMenuActionPerformed
        Start Start = new Start();
        Start.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ButtonMenuActionPerformed

    private void TextEnemyHero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEnemyHero3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEnemyHero3ActionPerformed

    private void TextEnemyWeapon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEnemyWeapon3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEnemyWeapon3ActionPerformed

    private void TextEnemyHero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEnemyHero2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEnemyHero2ActionPerformed

    private void TextEnemyHero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEnemyHero1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEnemyHero1ActionPerformed

    private void HpPlayerHero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HpPlayerHero3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HpPlayerHero3ActionPerformed

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
            java.util.logging.Logger.getLogger(TeamBattle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeamBattle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeamBattle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeamBattle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeamBattle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBattle;
    private javax.swing.JButton ButtonMenu;
    private javax.swing.JLabel EnemyHero1;
    private javax.swing.JLabel EnemyHero2;
    private javax.swing.JLabel EnemyHero3;
    private javax.swing.JLabel EnemyWeapon1;
    private javax.swing.JLabel EnemyWeapon2;
    private javax.swing.JLabel EnemyWeapon3;
    private javax.swing.JTextField HpEnemyHero1;
    private javax.swing.JTextField HpEnemyHero2;
    private javax.swing.JTextField HpEnemyHero3;
    private javax.swing.JTextField HpPlayerHero1;
    private javax.swing.JTextField HpPlayerHero2;
    private javax.swing.JTextField HpPlayerHero3;
    private javax.swing.JLabel PlayerHero1;
    private javax.swing.JLabel PlayerHero2;
    private javax.swing.JLabel PlayerHero3;
    private javax.swing.JLabel PlayerWeapon1;
    private javax.swing.JLabel PlayerWeapon2;
    private javax.swing.JLabel PlayerWeapon3;
    private javax.swing.JLabel Result;
    private javax.swing.JLabel Team;
    private javax.swing.JLabel Team1;
    private javax.swing.JTextField TextEnemyHero1;
    private javax.swing.JTextField TextEnemyHero2;
    private javax.swing.JTextField TextEnemyHero3;
    private javax.swing.JTextField TextEnemyWeapon1;
    private javax.swing.JTextField TextEnemyWeapon2;
    private javax.swing.JTextField TextEnemyWeapon3;
    private javax.swing.JTextField TextPlayerHeroes1;
    private javax.swing.JTextField TextPlayerHeroes2;
    private javax.swing.JTextField TextPlayerHeroes3;
    private javax.swing.JTextField TextPlayerWeapon1;
    private javax.swing.JTextField TextPlayerWeapon2;
    private javax.swing.JTextField TextPlayerWeapon3;
    private javax.swing.JTextArea TextResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
