package sayfalar;

import Kullanicilar.doktor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import sql_islemleri.sql_kullanici_islemleri;

public class randevu extends javax.swing.JFrame {

    //degiskenler
    private String DB_Kullanici = "hastane";
    public static int h_id;
    public String temp_doktor_ad,temp_doktor_soyad;
    public static int tckn;
    public static String isim;

    String combobox_bolum, combobox_il, combobox_hastane, combobox_doktorlar, FormattedTextField;

    //classlar
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    ImageIcon icon = new ImageIcon("src\\main\\java\\images\\checked.png");
    sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
    randevu Randevu;
    LinkedList<doktor> doktorlarList = new LinkedList<>();
    LinkedList<String> hastanelerList = new LinkedList<>();
    LinkedList<String> bolumlerList = new LinkedList<>();
    LinkedList<randevu> randevuList = new LinkedList<>();

    //buraya isim de gelmeli
    //constructor
    public randevu(int tckn,String isim) throws SQLException {
        initComponents();
        jComboBox_hastane.setEnabled(false);
        jComboBox_bolum.setEnabled(false);
        jComboBox_doktorlar.setEnabled(false);
        //ekrani tam ortaya ayarladik
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.tckn=tckn;
        this.isim=isim;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_bolum = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_il = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jComboBox_doktorlar = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox_hastane = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField_tarih = new javax.swing.JFormattedTextField();
        jButton_randevu_al_kayit = new javax.swing.JButton();
        jButton_home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(62, 62, 62));

        jPanel2.setBackground(new java.awt.Color(62, 62, 62));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(244, 114, 43));
        jLabel3.setText("Randevu Kayit");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(244, 114, 43));
        jLabel1.setText("Bolum:");

        jComboBox_bolum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genel Cerrahi", "Cocuk", "Dis Hekimligi", "Cildiye", "Goz Hastalıkları", "Dahiliye", "Kulak burun bogaz", "Noroloji", "Radyoloji" }));
        jComboBox_bolum.setSelectedItem("Bolum seciniz");
        jComboBox_bolum.setToolTipText("");
        jComboBox_bolum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_bolumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_bolum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_bolum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(244, 114, 43));
        jLabel2.setText("Sehir:");

        jComboBox_il.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Antalya", "Izmir", "Konya" }));
        jComboBox_il.setSelectedItem("Bolum seciniz");
        jComboBox_il.setToolTipText("");
        jComboBox_il.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBox_il, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_il, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jComboBox_doktorlar.setSelectedItem("Bolum seciniz");
        jComboBox_doktorlar.setToolTipText("");
        jComboBox_doktorlar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_doktorlarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(244, 114, 43));
        jLabel4.setText("Doktor:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_doktorlar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_doktorlar)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jComboBox_hastane.setSelectedItem("Bolum seciniz");
        jComboBox_hastane.setToolTipText("");
        jComboBox_hastane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_hastaneActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(244, 114, 43));
        jLabel5.setText("Hastane:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(68, 68, 68))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_hastane, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_hastane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(244, 114, 43));
        jLabel6.setText("Tarih:");

        jFormattedTextField_tarih.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        jFormattedTextField_tarih.setText("00/00/0000");
        jFormattedTextField_tarih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_tarihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jFormattedTextField_tarih, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(40, 40, 40))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_tarih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton_randevu_al_kayit.setBackground(new java.awt.Color(244, 114, 43));
        jButton_randevu_al_kayit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_randevu_al_kayit.setForeground(new java.awt.Color(245, 229, 193));
        jButton_randevu_al_kayit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_randevu_al_kayit.setText("Randevu al");
        jButton_randevu_al_kayit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_randevu_al_kayit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_randevu_al_kayit.setIconTextGap(15);
        jButton_randevu_al_kayit.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_randevu_al_kayit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_randevu_al_kayitActionPerformed(evt);
            }
        });

        jButton_home.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\home.png")); // NOI18N
        jButton_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_homeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_home)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_randevu_al_kayit, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_home))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_randevu_al_kayit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_ilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ilActionPerformed
        combobox_il = (String) jComboBox_il.getSelectedItem();
        this.jComboBox_hastane.removeAllItems();
        

        try {
            hastanelerList = sqlKullaniciİslemleri.tum_hastaneleri_getir(combobox_il);
            for (String i : hastanelerList) {
                jComboBox_hastane.addItem(i);
                this.jComboBox_hastane.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(randevu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jComboBox_ilActionPerformed

    private void jComboBox_doktorlarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_doktorlarActionPerformed
        combobox_doktorlar = (String) jComboBox_doktorlar.getSelectedItem();
    }//GEN-LAST:event_jComboBox_doktorlarActionPerformed

    private void jComboBox_hastaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_hastaneActionPerformed
        combobox_hastane = (String) jComboBox_hastane.getSelectedItem();
        this.jComboBox_bolum.setEnabled(true);
        /*
        this.jComboBox_bolum.removeAllItems();
        try{
            bolumlerList=sqlKullaniciİslemleri.tum_bolumleri_getir(combobox_hastane);
            for(String i:bolumlerList){
                jComboBox_bolum.addItem(i);
                this.jComboBox_bolum.setEnabled(true);
            }
        }catch(SQLException e){
            Logger.getLogger(randevu.class.getName()).log(Level.SEVERE,null,e);
        }
        */
    }//GEN-LAST:event_jComboBox_hastaneActionPerformed

    private void jComboBox_bolumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_bolumActionPerformed
        combobox_bolum = (String) jComboBox_bolum.getSelectedItem();
        this.jComboBox_doktorlar.removeAllItems();
        try{
            //sql islemlerden veri cekilip comboboxa ata (hastane ve doktorlar)
        doktorlarList = sqlKullaniciİslemleri.doktor_getir(combobox_bolum);
            
        if(doktorlarList.isEmpty())
            JOptionPane.showMessageDialog(this, "Doktor bulunamadi !    ", "Uyari!", JOptionPane.ERROR_MESSAGE);
        for (doktor i : doktorlarList) {
            jComboBox_doktorlar.addItem("Dr. " + i.getD_ad().toString() + " " + i.getD_soyad().toString());
            temp_doktor_ad=i.getD_ad();
            temp_doktor_soyad=i.getD_soyad();
            this.jComboBox_doktorlar.setEnabled(true);
        }
        }catch(SQLException e){
            Logger.getLogger(randevu.class.getName()).log(Level.SEVERE,null,e);
        }
        
         

        
    }//GEN-LAST:event_jComboBox_bolumActionPerformed

    private void jFormattedTextField_tarihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_tarihActionPerformed
        FormattedTextField = (String) jFormattedTextField_tarih.getValue();
    }//GEN-LAST:event_jFormattedTextField_tarihActionPerformed

    private void jButton_randevu_al_kayitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_randevu_al_kayitActionPerformed
        
       int temp_id;
        try {
            int hastane_id = sqlKullaniciİslemleri.hastane_ismi_to_id(jComboBox_hastane.getSelectedItem().toString());
            int doktor_id= sqlKullaniciİslemleri.doktor_ismi_to_id(temp_doktor_ad,temp_doktor_soyad);
            int hasta_id=sqlKullaniciİslemleri.tckn_to_id(tckn);
            String tarih=jFormattedTextField_tarih.getText();
            sqlKullaniciİslemleri.randevu_kayit(tarih, hasta_id, doktor_id, hastane_id);
        } catch (SQLException ex) {
            Logger.getLogger(randevu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton_randevu_al_kayitActionPerformed

    private void jButton_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_homeActionPerformed
        // TODO add your handling code here:
        anasayfa ana_sayfa=null;
        this.setVisible(false);
        try {
            ana_sayfa=new anasayfa(false, isim, tckn, doktor_kayit.d_id);
            System.out.println("\nrandevudan giden tckn: "+tckn);
            ana_sayfa.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(randevu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton_homeActionPerformed

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
            java.util.logging.Logger.getLogger(randevu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(randevu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(randevu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(randevu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new randevu(tckn,isim).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(randevu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_home;
    public javax.swing.JButton jButton_randevu_al_kayit;
    private javax.swing.JComboBox<String> jComboBox_bolum;
    private javax.swing.JComboBox<String> jComboBox_doktorlar;
    private javax.swing.JComboBox<String> jComboBox_hastane;
    private javax.swing.JComboBox<String> jComboBox_il;
    private javax.swing.JFormattedTextField jFormattedTextField_tarih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
