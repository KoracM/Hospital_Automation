package sayfalar;

import Kullanicilar.doktor;
import Kullanicilar.hasta;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sql_islemleri.sql_kullanici_islemleri;
import tablolar.Tablo;

public class anasayfa extends javax.swing.JFrame {

    //degiskenler
    private String DB_Kullanici = "hastane";
    hasta h_kullanici;
    doktor d_kullanici;
    String durum = "";
    String isim, d_ad, h_ad;
    int tckn,d_id;
    String temp_status;
    int table_secilen_tckn;
    String jtable_doktor_adi,jtable_doktor_soyadi,jtable_tarih;
    int doktor_id;

    //eklenen classlar
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    Tablo HastaTablo;
    Tablo DoktorTablo;
    sql_kullanici_islemleri sql_islemleri = new sql_kullanici_islemleri(DB_Kullanici);
    LinkedList<hasta> linkedlist_hasta = new LinkedList<>();
    LinkedList<doktor> linkedlist_doktor = new LinkedList<>();

    /**
     * Creates new form sayfa1
     */
    //default constructor
    public anasayfa() {
        initComponents();
        //tablo = new Tablo(jTable_hasta_randevu);
        //ekrani tam ortaya ayarladik
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    //sonradan tanimlanan constructor
    public anasayfa(hasta kullanici) throws SQLException //kullaniciyi buraya aktardik
    {
        h_kullanici = kullanici;
        initComponents();
        /*
        javax.swing.JLabel jLabel_randevu_sil;
        jLabel_randevu_sil= new javax.swing.JLabel();
        jLabel_randevu_sil.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\delete_128px.png"));
        jLabel_randevu_sil.setLocation(30, 30);
        jLabel_randevu_sil.setVisible(true);
        */
        this.jButton_doktor_guncelle.setVisible(false);
        this.jLabel_doktor_guncelle.setVisible(false);
        this.jButton_doktor_kayit_et.setVisible(false);
        this.jLabel_doktor_kayit_et.setVisible(false);
        this.jButton_hasta_guncelle.setVisible(false);
        this.jLabel_hasta_guncelle.setVisible(false);
        
        tckn = kullanici.getTckn();
        HastaTablo = new Tablo(jTable_hasta_randevu);
        //ekrani tam ortaya ayarladik
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        this.jLabel_UserName.setText("Hosgeldiniz " + kullanici.getH_ad());
        isim = kullanici.getH_ad();
        //h_ad = kullanici.getH_ad();
        Date current_date = new Date();

        //tarih kismi
        LocalDate dt = LocalDate.parse("2023/05/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        int ay = dt.getMonthValue();
        int gun = dt.getDayOfMonth();
        this.jLabel_CurrentDate.setText("Tarih: " + gun + "/" + ay);

        //butonlari gizleme vs 
        this.jButton_doktor_kayit_et.setEnabled(false);
        this.jButton_doktor_kayit_et.setVisible(false);
        //hasta kayit getirme
        try {
            sql_islemleri.hasta_kayit_getir(tckn);
        } catch (SQLException ex) {
            Logger.getLogger(anasayfa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hata: " + ex);
        }

        linkedlist_hasta = sql_islemleri.hasta_kayit_getir(kullanici.getTckn());
        Integer sayac = linkedlist_hasta.size();
            this.setSize(547, 568+(sayac*16)); //her bir kayit 16px 552 baslangic
            System.out.println("sayac: "+sayac);
        //bu if else kapatilabilir
        if (sayac == 0) {
            JOptionPane.showMessageDialog(this, "randevu bulunamadi");
        } else {
            for (int i = 0; i < sayac; i++) {
                HastaTablo.HastaTabloDoldur(linkedlist_hasta.get(i));
            }
        }
        /*
        for (int i = 0; i < sayac; i++) {
                HastaTablo.HastaTabloDoldur(linkedlist_hasta.get(i));
            }
        */
    }

    //sonradan tanimlanan constructor
    public anasayfa(doktor kullanici) throws SQLException //kullaniciyi buraya aktardik
    {   
       
        temp_status="doktor";
        d_kullanici = kullanici;
        d_id=kullanici.getD_id();
         this.doktor_id=d_id;
        initComponents();
        DoktorTablo = new Tablo(jTable_hasta_randevu);
        this.jButton_doktor_kayit_et.setVisible(true);
        this.jButton_hasta_kayit_et.setVisible(true);
        this.jButton_randevu_al.setVisible(false);
        this.jLabel_randevu_al.setVisible(false);
        this.jLabel_randevu_sil.setVisible(false);
        this.jButton_randevu_sil.setVisible(false);
        
        //ekrani tam ortaya ayarladik
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.jLabel_UserName.setText("Hosgeldiniz Dr " + kullanici.getD_ad());
        //d_ad = kullanici.getD_ad();
        isim = kullanici.getD_ad();
       
        
        LocalDate dt = LocalDate.parse("2023/05/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        int ay = dt.getMonthValue();
        int gun = dt.getDayOfMonth();
        this.jLabel_CurrentDate.setText("Tarih: " + gun + "/" + ay);

        jLabel_User.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\doctors.png"));
        
        
        
        //linkedlist_doktor = sql_islemleri.doktor_kayit_getir(kullanici.getD_id());
        linkedlist_doktor = sql_islemleri.doktor_kayit_getir2(kullanici.getD_id());
        Integer sayac = linkedlist_doktor.size();
        
        this.setSize(547, 751+(sayac*16)); //her bir kayit 16px 751 baslangic
        /*
        if (sayac == 0) {
            JOptionPane.showMessageDialog(this, "randevu bulunamadi");
        } else {
            for (int i = 0; i < sayac; i++) {
                DoktorTablo.DoktorRandevuTabloDuzenle();
                DoktorTablo.DoktorRandevuTabloDoldur(linkedlist_doktor.get(i));
            }
        }
        */
        for (int i = 0; i < sayac; i++) {
                DoktorTablo.DoktorRandevuTabloDuzenle();
                DoktorTablo.DoktorRandevuTabloDoldur(linkedlist_doktor.get(i));
            }

        
    }

    public anasayfa(boolean status, String isim, int Tckn,int d_id) throws SQLException {
        //tablo= new Tablo(jTable_hasta_randevu);
        this.isim = isim;
        this.tckn=Tckn; //can caused conflicted !
        //doktor ise
        if (status) {
            //System.out.println("anasayfa boolean true");
            temp_status="doktor";
            initComponents();
            this.jButton_doktor_kayit_et.setVisible(true);
             this.jButton_hasta_kayit_et.setVisible(true);
             this.jButton_randevu_al.setVisible(false);
             this.jLabel_randevu_al.setVisible(false);
             this.jLabel_randevu_sil.setVisible(false);
        this.jButton_randevu_sil.setVisible(false);
             
            //ekrani tam ortaya ayarladik
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            //tarih ayarlama
            Date current_date = new Date();
            LocalDate dt = LocalDate.parse("2023/05/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            int ay = dt.getMonthValue();
            int gun = dt.getDayOfMonth();
            this.jLabel_CurrentDate.setText("Tarih: " + gun + "/" + ay);
            
            //icon, buton, text vs ayarlama
            this.jLabel_UserName.setText("Hosgeldiniz Dr " + isim);
            jLabel_User.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\doctors.png"));
            //this.jButton_hasta_kayit_et.setEnabled(false);
            //this.jButton_hasta_kayit_et.setVisible(false);
            DoktorTablo=new Tablo(jTable_hasta_randevu);
        linkedlist_doktor = sql_islemleri.doktor_kayit_getir(d_id);
        Integer sayac = linkedlist_doktor.size();
        this.doktor_id=d_id;
            System.out.println("doktor_id: "+doktor_id+"\td_id: "+d_id+"\tlistDoktorSize: "+sayac);
        this.setSize(547, 800+(sayac*2)); //her bir kayit 16px 552 baslangic
        /*
        if (sayac == 0) {
            JOptionPane.showMessageDialog(this, "randevu bulunamadi");
        } else {
            for (int i = 0; i < sayac; i++) {
                DoktorTablo.DoktorRandevuTabloDuzenle();
                DoktorTablo.DoktorRandevuTabloDoldur(linkedlist_doktor.get(i));
            }
        }
        */
        for (int i = 0; i < sayac; i++) {
                DoktorTablo.DoktorRandevuTabloDuzenle();
                DoktorTablo.DoktorRandevuTabloDoldur(linkedlist_doktor.get(i));
            }
        
        } 
        //hasta ise
        else {

            initComponents();
            //ekrani tam ortaya ayarladik
            this.jButton_doktor_kayit_et.setVisible(false);
            this.jLabel_doktor_kayit_et.setVisible(false);
            this.jButton_hasta_guncelle.setVisible(false);
            this.jLabel_hasta_guncelle.setVisible(false);
            this.jButton_doktor_guncelle.setVisible(false);
            this.jLabel_doktor_guncelle.setVisible(false);
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            
            
            this.jLabel_UserName.setText("Hosgeldiniz " + isim);
            Date current_date = new Date();
            //this.jLabel_CurrentDate.setText(current_date.toString());
            //this.jLabel_CurrentDate.setText(new Date().toString()); tek satir bu sekilde de yapilabilir
            LocalDate dt = LocalDate.parse("2023/05/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            int ay = dt.getMonthValue();
            int gun = dt.getDayOfMonth();
            this.jLabel_CurrentDate.setText("Tarih: " + gun + "/" + ay);
            this.jButton_doktor_kayit_et.setEnabled(false);
            this.jButton_doktor_kayit_et.setVisible(false);
            
            HastaTablo = new Tablo(jTable_hasta_randevu);
            //Integer sayac = 0;
            //TCKN sifirlaniyor
            linkedlist_hasta = sql_islemleri.hasta_kayit_getir(Tckn);
            Integer sayac = linkedlist_hasta.size();
            this.setSize(547, 568+(sayac*16)); //her bir kayit 16px 552 baslangic
            //hata burda sayac 0 oluyor
            //bu if else kapatilabilir
            if (sayac == 0) {
                JOptionPane.showMessageDialog(this, "randevu bulunamadi!");
            } else {
                for (int i = 0; i < sayac; i++) {
                    HastaTablo.HastaTabloDoldur(linkedlist_hasta.get(i));
                }
            }
            
            /*
            for (int i = 0; i < sayac; i++) {
                HastaTablo.HastaTabloDoldur(linkedlist_hasta.get(i));
            }
            */

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

        jPanel1 = new javax.swing.JPanel();
        jLabel_CurrentDate = new javax.swing.JLabel();
        jLabel_UserName = new javax.swing.JLabel();
        jLabel_User = new javax.swing.JLabel();
        jLabel_Pill = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_hasta_randevu = new javax.swing.JTable();
        jButton_hasta_kayit_et = new javax.swing.JButton();
        jButton_hasta_guncelle = new javax.swing.JButton();
        jButton_doktor_guncelle = new javax.swing.JButton();
        jLabel_doktor_guncelle = new javax.swing.JLabel();
        jLabel_hasta_kayit_et = new javax.swing.JLabel();
        jLabel_hasta_guncelle = new javax.swing.JLabel();
        jLabel_randevu_al = new javax.swing.JLabel();
        jButton_randevu_al = new javax.swing.JButton();
        jButton_doktor_kayit_et = new javax.swing.JButton();
        jLabel_doktor_kayit_et = new javax.swing.JLabel();
        jLabel_randevu_sil = new javax.swing.JLabel();
        jButton_randevu_sil = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(62, 62, 62));
        jPanel1.setForeground(new java.awt.Color(62, 62, 62));

        jLabel_CurrentDate.setForeground(new java.awt.Color(245, 229, 193));
        jLabel_CurrentDate.setText("Current Date");

        jLabel_UserName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_UserName.setForeground(new java.awt.Color(244, 114, 43));
        jLabel_UserName.setText("Merhaba isim !");

        jLabel_User.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\man.png")); // NOI18N

        jLabel_Pill.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\pills.png")); // NOI18N

        jTable_hasta_randevu.setBackground(new java.awt.Color(60, 63, 65));
        jTable_hasta_randevu.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_hasta_randevu.setGridColor(new java.awt.Color(60, 63, 65));
        jTable_hasta_randevu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_hasta_randevuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_hasta_randevu);

        jButton_hasta_kayit_et.setBackground(new java.awt.Color(244, 114, 43));
        jButton_hasta_kayit_et.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_hasta_kayit_et.setForeground(new java.awt.Color(245, 229, 193));
        jButton_hasta_kayit_et.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_hasta_kayit_et.setText("Hasta Kayıt Et");
        jButton_hasta_kayit_et.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_hasta_kayit_et.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_hasta_kayit_et.setIconTextGap(15);
        jButton_hasta_kayit_et.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_hasta_kayit_et.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_hasta_kayit_etActionPerformed(evt);
            }
        });

        jButton_hasta_guncelle.setBackground(new java.awt.Color(244, 114, 43));
        jButton_hasta_guncelle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_hasta_guncelle.setForeground(new java.awt.Color(245, 229, 193));
        jButton_hasta_guncelle.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_hasta_guncelle.setText("Hasta Guncelle veya Sil");
        jButton_hasta_guncelle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_hasta_guncelle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_hasta_guncelle.setIconTextGap(15);
        jButton_hasta_guncelle.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_hasta_guncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_hasta_guncelleActionPerformed(evt);
            }
        });

        jButton_doktor_guncelle.setBackground(new java.awt.Color(244, 114, 43));
        jButton_doktor_guncelle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_doktor_guncelle.setForeground(new java.awt.Color(245, 229, 193));
        jButton_doktor_guncelle.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_doktor_guncelle.setText("Doktor Guncelle veya Sil");
        jButton_doktor_guncelle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_doktor_guncelle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_doktor_guncelle.setIconTextGap(15);
        jButton_doktor_guncelle.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_doktor_guncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_doktor_guncelleActionPerformed(evt);
            }
        });

        jLabel_doktor_guncelle.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\update_doctor.png")); // NOI18N

        jLabel_hasta_kayit_et.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\patient.png")); // NOI18N

        jLabel_hasta_guncelle.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\update_patient.png")); // NOI18N

        jLabel_randevu_al.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\register_1.png")); // NOI18N

        jButton_randevu_al.setBackground(new java.awt.Color(244, 114, 43));
        jButton_randevu_al.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_randevu_al.setForeground(new java.awt.Color(245, 229, 193));
        jButton_randevu_al.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_randevu_al.setText("Randevu al");
        jButton_randevu_al.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_randevu_al.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_randevu_al.setIconTextGap(15);
        jButton_randevu_al.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_randevu_al.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_randevu_alActionPerformed(evt);
            }
        });

        jButton_doktor_kayit_et.setBackground(new java.awt.Color(244, 114, 43));
        jButton_doktor_kayit_et.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_doktor_kayit_et.setForeground(new java.awt.Color(245, 229, 193));
        jButton_doktor_kayit_et.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_doktor_kayit_et.setText("Doktor Kayıt Et");
        jButton_doktor_kayit_et.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_doktor_kayit_et.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_doktor_kayit_et.setIconTextGap(15);
        jButton_doktor_kayit_et.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_doktor_kayit_et.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_doktor_kayit_etActionPerformed(evt);
            }
        });

        jLabel_doktor_kayit_et.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\doctor_1.png")); // NOI18N

        jLabel_randevu_sil.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\delete_128px.png")); // NOI18N

        jButton_randevu_sil.setBackground(new java.awt.Color(244, 114, 43));
        jButton_randevu_sil.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_randevu_sil.setForeground(new java.awt.Color(245, 229, 193));
        jButton_randevu_sil.setIcon(new javax.swing.ImageIcon("C:\\Users\\Korac\\Documents\\NetBeansProjects\\hastane_otomasyon_v02\\src\\main\\java\\images\\login.png")); // NOI18N
        jButton_randevu_sil.setText("Randevu Sil");
        jButton_randevu_sil.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 62, 62), 3, true));
        jButton_randevu_sil.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_randevu_sil.setIconTextGap(15);
        jButton_randevu_sil.setPreferredSize(new java.awt.Dimension(109, 33));
        jButton_randevu_sil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_randevu_silActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel_doktor_kayit_et)
                            .addComponent(jButton_doktor_kayit_et, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hasta_guncelle)
                            .addComponent(jButton_hasta_guncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_randevu_al)
                            .addComponent(jButton_randevu_al, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel_randevu_sil)
                                .addComponent(jButton_randevu_sil, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton_hasta_kayit_et, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel_doktor_guncelle)
                                .addComponent(jButton_doktor_guncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_Pill)
                                .addComponent(jLabel_hasta_kayit_et))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel_User, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel_UserName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel_CurrentDate)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_CurrentDate)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel_UserName))
                    .addComponent(jLabel_User, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_Pill)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel_doktor_guncelle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_doktor_guncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jButton_doktor_kayit_et, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_doktor_kayit_et)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_randevu_sil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_randevu_sil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel_hasta_guncelle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_hasta_guncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_randevu_al)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_randevu_al, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_hasta_kayit_et)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_hasta_kayit_et, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_hasta_kayit_etActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_hasta_kayit_etActionPerformed
        if(temp_status=="doktor"){
            hasta_kayit h_kayit = new hasta_kayit(isim, 1);
        this.setVisible(false);
        h_kayit.setVisible(true);
        }
        else{
            hasta_kayit h_kayit = new hasta_kayit(isim, tckn);
        this.setVisible(false);
        h_kayit.setVisible(true);
        }
    }//GEN-LAST:event_jButton_hasta_kayit_etActionPerformed

    private void jButton_doktor_kayit_etActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_doktor_kayit_etActionPerformed
        doktor_kayit d_kayit = new doktor_kayit(isim,d_id);
        this.setVisible(false);
        d_kayit.setVisible(true);

    }//GEN-LAST:event_jButton_doktor_kayit_etActionPerformed

    private void jButton_hasta_guncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_hasta_guncelleActionPerformed
        hasta_guncelle h_guncelle = null;
        try {
            h_guncelle = new hasta_guncelle(isim, tckn);
        } catch (SQLException ex) {
            Logger.getLogger(anasayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        h_guncelle.setVisible(true);
    }//GEN-LAST:event_jButton_hasta_guncelleActionPerformed

    private void jButton_doktor_guncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_doktor_guncelleActionPerformed
        doktor_guncelle d_guncelle=null;
        try{
            d_guncelle = new doktor_guncelle(isim, tckn);
        }catch(SQLException ex){
            Logger.getLogger(anasayfa.class.getName()).log(Level.SEVERE,null,ex);
        }
        this.setVisible(false);
        d_guncelle.setVisible(true);
    }//GEN-LAST:event_jButton_doktor_guncelleActionPerformed

    private void jButton_randevu_alActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_randevu_alActionPerformed
        randevu r;
        try {
            r = new randevu(tckn,isim);
             this.setVisible(false);
        r.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(anasayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }//GEN-LAST:event_jButton_randevu_alActionPerformed

    private void jButton_randevu_silActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_randevu_silActionPerformed
        int id_tut;
        try {
            id_tut=sql_islemleri.tckn_to_id(table_secilen_tckn);
            System.out.println("id tut: "+id_tut);
            if(sql_islemleri.randevu_sil(id_tut,jtable_doktor_adi,jtable_doktor_soyadi,jtable_tarih)){
                JOptionPane.showMessageDialog(this, "randevu silindi");
                HastaTablo = new Tablo(jTable_hasta_randevu);
            //Integer sayac = 0;
            //TCKN sifirlaniyor
            linkedlist_hasta = sql_islemleri.hasta_kayit_getir(tckn);
            Integer sayac = linkedlist_hasta.size();
            this.setSize(547, 568+(sayac*16)); //her bir kayit 16px 552 baslangic
            //hata burda sayac 0 oluyor
            //bu if else kapatilabilir
            
                for (int i = 0; i < sayac; i++) {
                    HastaTablo.HastaTabloDoldur(linkedlist_hasta.get(i));
                }
            
            }
            else{
                JOptionPane.showMessageDialog(this, "randevu silinemedi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(anasayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton_randevu_silActionPerformed

    private void jTable_hasta_randevuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_hasta_randevuMouseClicked
        
        int secilen_satir=jTable_hasta_randevu.getSelectedRow();
        int tckn=(int) jTable_hasta_randevu.getValueAt(secilen_satir, 0);
        String tarih=(String) jTable_hasta_randevu.getValueAt(secilen_satir, 1);
        String doktor_adi=(String) jTable_hasta_randevu.getValueAt(secilen_satir, 2);
        String doktor_soyadi=(String) jTable_hasta_randevu.getValueAt(secilen_satir, 3);
        this.table_secilen_tckn=tckn;
        this.jtable_doktor_adi=doktor_adi;
        this.jtable_tarih=tarih;
        this.jtable_doktor_soyadi=doktor_soyadi;
        
    }//GEN-LAST:event_jTable_hasta_randevuMouseClicked

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
            java.util.logging.Logger.getLogger(anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(anasayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new anasayfa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_doktor_guncelle;
    public javax.swing.JButton jButton_doktor_kayit_et;
    public javax.swing.JButton jButton_hasta_guncelle;
    public javax.swing.JButton jButton_hasta_kayit_et;
    public javax.swing.JButton jButton_randevu_al;
    public javax.swing.JButton jButton_randevu_sil;
    private javax.swing.JLabel jLabel_CurrentDate;
    private javax.swing.JLabel jLabel_Pill;
    private javax.swing.JLabel jLabel_User;
    private javax.swing.JLabel jLabel_UserName;
    private javax.swing.JLabel jLabel_doktor_guncelle;
    private javax.swing.JLabel jLabel_doktor_kayit_et;
    private javax.swing.JLabel jLabel_hasta_guncelle;
    private javax.swing.JLabel jLabel_hasta_kayit_et;
    private javax.swing.JLabel jLabel_randevu_al;
    private javax.swing.JLabel jLabel_randevu_sil;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_hasta_randevu;
    // End of variables declaration//GEN-END:variables
}
