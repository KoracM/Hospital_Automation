package UI_Hasta;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sql_islemleri.sql_kullanici_islemleri;

public class UI_Hasta {

    //degiskenler
    private String DB_Kullanici = "hastane";
    public JTextField JTextField_hasta_id, JTextField_hasta_tckn, JTextField_hasta_ad, JTextField_hasta_soyad, JTextField_hasta_sifre;
    public String hasta_id, hasta_tckn, hasta_ad, hasta_soyad, hasta_sifre;
    public JPasswordField jPasswordField_sif;

    //constructor
    public UI_Hasta(String JTextField_hasta_id, JTextField JTextField_hasta_tckn, JTextField JTextField_hasta_ad, JTextField JTextField_hasta_soyad, JTextField JTextField_hasta_sifre) {
        //this.JTextField_hasta_id = JTextField_hasta_id;
        this.JTextField_hasta_tckn = JTextField_hasta_tckn;
        this.JTextField_hasta_ad = JTextField_hasta_ad;
        this.JTextField_hasta_soyad = JTextField_hasta_soyad;
        //this.JTextField_hasta_sifre = JTextField_hasta_sifre;

        //this.hasta_id = JTextField_hasta_id.getText();
        this.hasta_tckn = JTextField_hasta_tckn.getText();
        this.hasta_ad = JTextField_hasta_ad.getText();
        this.hasta_soyad = JTextField_hasta_soyad.getText();
        //this.hasta_sifre = JTextField_hasta_sifre.getText();
    }
    
    //constructor2
    public UI_Hasta( JTextField JTextField_hasta_tckn, JTextField JTextField_hasta_ad, JTextField JTextField_hasta_soyad, JTextField JTextField_hasta_sifre) {
        this.JTextField_hasta_tckn = JTextField_hasta_tckn;
        this.JTextField_hasta_ad = JTextField_hasta_ad;
        this.JTextField_hasta_soyad = JTextField_hasta_soyad;
        //this.JTextField_hasta_sifre = JTextField_hasta_sifre;

        this.hasta_tckn = JTextField_hasta_tckn.getText();
        this.hasta_ad = JTextField_hasta_ad.getText();
        this.hasta_soyad = JTextField_hasta_soyad.getText();
        //this.hasta_sifre = JTextField_hasta_sifre.getText();
    }

    //constructor3
    public UI_Hasta( JTextField JTextField_hasta_tckn, JTextField JTextField_hasta_ad, JTextField JTextField_hasta_soyad, JPasswordField jPasswordField) {
        //this.JTextField_hasta_id = JTextField_hasta_id;
        this.JTextField_hasta_tckn = JTextField_hasta_tckn;
        this.JTextField_hasta_ad = JTextField_hasta_ad;
        this.JTextField_hasta_soyad = JTextField_hasta_soyad;
        this.jPasswordField_sif=jPasswordField;

        
        //this.hasta_id = JTextField_hasta_id.getText();
        this.hasta_tckn = JTextField_hasta_tckn.getText();
        this.hasta_ad = JTextField_hasta_ad.getText();
        this.hasta_soyad = JTextField_hasta_soyad.getText();
        this.hasta_sifre=new String(jPasswordField_sif.getPassword());
        //jPasswordField_sif.getPassword().toString() olmuyor 
    }
    public void hasta_tanimla() {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Hasta hasta = new UI_Hasta(null, JTextField_hasta_tckn, JTextField_hasta_ad, JTextField_hasta_soyad, JTextField_hasta_sifre);
        sqlKullaniciİslemleri.hasta_kayit(hasta);
    }
    
    public void hasta_tanimla_pass() {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Hasta hasta = new UI_Hasta(null, JTextField_hasta_tckn, JTextField_hasta_ad, JTextField_hasta_soyad, jPasswordField_sif);
        sqlKullaniciİslemleri.hasta_kayit(hasta);
    }

    public void hasta_guncelle(int temp_tckn) {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Hasta hasta = new UI_Hasta(null, JTextField_hasta_tckn, JTextField_hasta_ad, JTextField_hasta_soyad, JTextField_hasta_sifre);
        sqlKullaniciİslemleri.hasta_guncelle(hasta,temp_tckn);
    }
    
    public void hasta_guncelle2(int temp_tckn) {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Hasta hasta = new UI_Hasta(JTextField_hasta_tckn, JTextField_hasta_ad, JTextField_hasta_soyad, jPasswordField_sif);
        sqlKullaniciİslemleri.hasta_guncelle(hasta,temp_tckn);
    }

    public boolean bos_alan_kontrol() {
        //boolean hasta_id_kontrol = JTextField_hasta_id.getText().trim().isEmpty();
        boolean hasta_tckn_kontrol = JTextField_hasta_tckn.getText().trim().isEmpty();
        boolean hasta_ad_kontrol = JTextField_hasta_ad.getText().trim().isEmpty();
        boolean hasta_soyad_kontrol = JTextField_hasta_soyad.getText().trim().isEmpty();
        boolean hasta_sifre_kontrol = JTextField_hasta_sifre.getText().trim().isEmpty();

        if ( hasta_tckn_kontrol || hasta_ad_kontrol || hasta_soyad_kontrol || hasta_sifre_kontrol) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean bos_alan_kontrol_pass() {
        //boolean hasta_id_kontrol = JTextField_hasta_id.getText().trim().isEmpty();
        System.out.println("\nHastaKayit bos_alan_kontrol_pass: \nHasta Id: "+this.hasta_id+"\nHasta Tckn: "+this.hasta_tckn+"\nHasta ad: "+this.hasta_ad+"\nHasta soyad: "+this.hasta_soyad+"\nHasta sifre: "+this.hasta_sifre);
        boolean hasta_tckn_kontrol = JTextField_hasta_tckn.getText().trim().isEmpty();
        boolean hasta_ad_kontrol = JTextField_hasta_ad.getText().trim().isEmpty();
        boolean hasta_soyad_kontrol = JTextField_hasta_soyad.getText().trim().isEmpty();
        boolean hasta_sifre_kontrol = new String(jPasswordField_sif.getPassword()).trim().isEmpty();
        System.out.println("bos alan kontrol sifre: "+hasta_sifre_kontrol);
            
        if ( hasta_tckn_kontrol || hasta_ad_kontrol || hasta_soyad_kontrol || hasta_sifre_kontrol) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean bos_alan_kontrol_guncelle() {
        boolean hasta_tckn_kontrol = JTextField_hasta_tckn.getText().trim().isEmpty();
        boolean hasta_ad_kontrol = JTextField_hasta_ad.getText().trim().isEmpty();
        boolean hasta_soyad_kontrol = JTextField_hasta_soyad.getText().trim().isEmpty();
        boolean hasta_sifre_kontrol = new String(jPasswordField_sif.getPassword()).trim().isEmpty();

        if ( hasta_tckn_kontrol || hasta_ad_kontrol || hasta_soyad_kontrol || hasta_sifre_kontrol) {
            return false;
        } else {
            return true;
        }
    }
}
