package UI_Hasta;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sql_islemleri.sql_kullanici_islemleri;

public class UI_Doktor {

    //degiskenler
    private String DB_Kullanici = "hastane";
    public JTextField jTextField_doktor_id, jTextField_doktor_adi, jTextField_doktor_soyadi, jTextField_doktor_sifresi, jTextField_bolum_ismi;
    public String doktor_id, doktor_adi, doktor_soyadi, doktor_sifresi, bolum_ismi;
    public JPasswordField jPasswordField_sif;
    
    //constructor
    public UI_Doktor(JTextField jTextField_doktor_id, JTextField jTextField_doktor_adi, JTextField jTextField_doktor_soyadi, JTextField jTextField_doktor_sifresi, JTextField jTextField_bolum_ismi) {
        this.jTextField_doktor_id = jTextField_doktor_id;
        this.jTextField_doktor_adi = jTextField_doktor_adi;
        this.jTextField_doktor_soyadi = jTextField_doktor_soyadi;
        this.jTextField_doktor_sifresi = jTextField_doktor_sifresi;
        this.jTextField_bolum_ismi = jTextField_bolum_ismi;

        this.doktor_id = jTextField_doktor_id.getText();
        this.doktor_adi = jTextField_doktor_adi.getText();
        this.doktor_soyadi = jTextField_doktor_soyadi.getText();
        this.doktor_sifresi = jTextField_doktor_sifresi.getText();
        this.bolum_ismi = jTextField_bolum_ismi.getText();
    }

    //constructor2
    public UI_Doktor(JTextField jTextField_doktor_id, JTextField jTextField_doktor_adi, JTextField jTextField_doktor_soyadi, JPasswordField jPasswordField_doktor_sifresi, JTextField jTextField_bolum_ismi) {
        this.jTextField_doktor_id = jTextField_doktor_id;
        this.jTextField_doktor_adi = jTextField_doktor_adi;
        this.jTextField_doktor_soyadi = jTextField_doktor_soyadi;
        this.jPasswordField_sif = jPasswordField_doktor_sifresi;
        this.jTextField_bolum_ismi = jTextField_bolum_ismi;

        this.doktor_id = jTextField_doktor_id.getText();
        this.doktor_adi = jTextField_doktor_adi.getText();
        this.doktor_soyadi = jTextField_doktor_soyadi.getText();
        this.doktor_sifresi = new String(jPasswordField_sif.getPassword());
        this.bolum_ismi = jTextField_bolum_ismi.getText();
    }
    
    public void doktor_tanimla() {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Doktor doktor = new UI_Doktor(jTextField_doktor_id, jTextField_doktor_adi, jTextField_doktor_soyadi, jTextField_doktor_sifresi, jTextField_bolum_ismi);
        sqlKullaniciİslemleri.doktor_kayit(doktor);
    }

     public void doktor_tanimla_pass() {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Doktor doktor = new UI_Doktor(jTextField_doktor_id, jTextField_doktor_adi, jTextField_doktor_soyadi, jPasswordField_sif, jTextField_bolum_ismi);
        sqlKullaniciİslemleri.doktor_kayit(doktor);
    }
    
    
    public void doktor_guncelle(int temp_id) {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Doktor doktor = new UI_Doktor(jTextField_doktor_id, jTextField_doktor_adi, jTextField_doktor_soyadi, jTextField_doktor_sifresi, jTextField_bolum_ismi);
        sqlKullaniciİslemleri.doktor_guncelle(doktor, temp_id);
    }
    
    public void doktor_guncelle2(int temp_id) {
        sql_kullanici_islemleri sqlKullaniciİslemleri = new sql_kullanici_islemleri(DB_Kullanici);
        UI_Doktor doktor = new UI_Doktor(jTextField_doktor_id, jTextField_doktor_adi, jTextField_doktor_soyadi, jPasswordField_sif, jTextField_bolum_ismi);
        sqlKullaniciİslemleri.doktor_guncelle(doktor, temp_id);
    }

    public boolean bos_alan_kontrol() {
        boolean doktor_id_kontrol = jTextField_doktor_id.getText().trim().isEmpty();
        boolean doktor_ad_kontrol = jTextField_doktor_adi.getText().trim().isEmpty();
        boolean doktor_soyad_kontrol = jTextField_doktor_soyadi.getText().trim().isEmpty();
        boolean doktor_sifre_kontrol = jTextField_doktor_sifresi.getText().trim().isEmpty();
        boolean bolum_kontrol = jTextField_bolum_ismi.getText().trim().isEmpty();

        if (doktor_id_kontrol || doktor_ad_kontrol || doktor_soyad_kontrol || doktor_sifre_kontrol || bolum_kontrol) {
            return false;
        } else {
            return true;
        }
    }
    
     public boolean bos_alan_kontrol_pass() {
        boolean doktor_id_kontrol = jTextField_doktor_id.getText().trim().isEmpty();
        boolean doktor_ad_kontrol = jTextField_doktor_adi.getText().trim().isEmpty();
        boolean doktor_soyad_kontrol = jTextField_doktor_soyadi.getText().trim().isEmpty();
        boolean doktor_sifre_kontrol = new String(jPasswordField_sif.getPassword()).trim().isEmpty();
        boolean bolum_kontrol = jTextField_bolum_ismi.getText().trim().isEmpty();

        if (doktor_id_kontrol || doktor_ad_kontrol || doktor_soyad_kontrol || doktor_sifre_kontrol || bolum_kontrol) {
            return false;
        } else {
            return true;
        }
    }

}
