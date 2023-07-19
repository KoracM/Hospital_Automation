package sql_islemleri;

import Kullanicilar.doktor;
import Kullanicilar.hasta;
import UI_Hasta.UI_Doktor;
import UI_Hasta.UI_Hasta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//sifreleme kismi
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;



public class sql_kullanici_islemleri extends sql_baglanti {

    //degiskenler
    private final String HASTA_ADI_SIFRESI = "SELECT * FROM hastalar WHERE tckn=? AND h_sifre=?";//soru isareti sonradan dolduruluyor
    private final String DOKTOR_ADI_SIFRESI = "SELECT * FROM doktorlar WHERE d_ad=? AND d_sifre=?";
    private final String BUTUN_HASTALAR = "select * from hastalar ";
    private final String BUTUN_DOKTORLAR = "select * from doktorlar ";
    //private final String HASTA_KAYIT_ET = "INSERT INTO hastalar values(?,?,?,?,?)";
    private final String HASTA_KAYIT_ET = "INSERT INTO hastalar(tckn,h_ad,h_soyad,h_sifre) values(?,?,?,?)";
    private final String DOKTOR_KAYIT_ET = "INSERT INTO doktorlar values(?,?,?,?,?)";
    private final String HASTA_KAYIT_GETIR = "select h.h_id,h.tckn,h.h_ad,h.h_soyad,h.h_sifre,k.tarih,d.d_ad,d.d_soyad,d.d_bolum from kayit as k "
            + "inner JOIN hastalar as h on k.F_h_id=h.h_id "
            + "inner JOIN doktorlar as d on k.F_d_id=d.d_id "
            + "inner JOIN hastaneler as ha on k.F_hastane_id=ha.hastane_id "
            + "WHERE h.tckn=? ";
    private final String DOKTOR_KAYIT_GETIR = "select d.d_id,d.d_ad,d.d_soyad,d.d_sifre,d.d_bolum,h.h_ad,h.h_soyad,k.tarih "
            + "from kayit as k "
            + "inner JOIN hastalar as h on k.F_h_id=h.h_id "
            + "inner JOIN doktorlar as d on k.F_d_id=d.d_id "
            + "inner JOIN hastaneler as ha on k.F_hastane_id=ha.hastane_id "
            + "WHERE d_id=?";
    private final String DOKTOR_KAYIT_GETIR2="SELECT k.tarih,h.h_ad,h.h_soyad from kayit as k inner JOIN hastalar as h on k.F_h_id=h.h_id where k.F_d_id=?";

    private final String HASTA_GUNCELLE = "UPDATE hastalar SET  tckn = ? , h_ad = ?, h_soyad = ? , h_sifre = ? WHERE hastalar.tckn = ? ";
    private final String HASTA_SIL = "DELETE FROM hastalar where tckn=?";

    private final String DOKTOR_GUNCELLE = "UPDATE doktorlar SET d_id = ? , d_ad= ? , d_soyad= ?, d_sifre= ? , d_bolum= ? WHERE doktorlar.d_id= ? ";
    private final String DOKTOR_SIL = "DELETE FROM doktorlar where d_id=?";
    //private final String RANDEVU_AL="INSERT into kayit VALUES(?,?,?,?,?)";
    private final String TUM_DOKTORLARI_AL="select d.d_ad,d.d_soyad from doktorlar as d ";
    private final String TUM_HASTANELERI_AL="select h.hastane_ismi from hastaneler as h ";
    private final String SEHRE_GORE_HASTANE_GETIR="select h.hastane_ismi from hastaneler as h where h.il= ?";
    private final String RANDEVU_KAYIT=" INSERT into hastaneler(il,hastane_ismi,konum,tel,bolumler) VALUES (?,?,?,?,?)" ;
    private final String HASTANEYE_GORE_BOLUM_GETIR="SELECT bolumler from hastaneler where hastane_ismi=?";
    private final String BOLUME_GORE_DOKTOR_GETIR="select d_ad,d_soyad from doktorlar where d_bolum=?";
    private final String RANDEVU_AL="insert into kayit(tarih,F_h_id,F_d_id,F_hastane_id) values(?,?,?,?)";
    private final String RANDEVU_SIL="delete from kayit where F_h_id=? and F_d_id=? and tarih=?";
    
    
    
    //constuctor
    public sql_kullanici_islemleri(String DBIsmi) {
        super(DBIsmi);
    }
    
    //sifreleme icin sabitler
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "YourSecretKey"; // Şifreleme için kullanılan gizli anahtar
    
    //sifreleme fonksiyonlari
    public static String encrypt(String input) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String decrypt(String encrypted) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  
  
    
    public void randevu_kayit(String tarih,int h_id,int d_id,int hastane_id) throws SQLException{       boolean status=false;
        //tarih,hasta_id,doktor_id,hastane_id 
        komutTamamlayici=baglanti.prepareStatement(RANDEVU_AL);
         komutTamamlayici.setString(1,tarih);
         komutTamamlayici.setInt(2, h_id);
         komutTamamlayici.setInt(3, d_id);
         komutTamamlayici.setInt(4, hastane_id);
        //executeUpdate returns count of effected rows
         int etkilenen_satirlar=komutTamamlayici.executeUpdate();
        if(etkilenen_satirlar>0){
            JOptionPane.showMessageDialog(null, "Randevu basariyla alindi!    ", "Uyari!", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Randevu alinamadi!    ", "Uyari!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int hastane_ismi_to_id(String hastane_ismi) throws SQLException, SQLException, SQLException{
        int id = 0;
        String sorgu="Select hastane_id from hastaneler where hastane_ismi=?";
        komutTamamlayici=baglanti.prepareStatement(sorgu);
        komutTamamlayici.setString(1, hastane_ismi);
        ResultSet sonuc=komutTamamlayici.executeQuery();
        while(sonuc.next())
            id=sonuc.getInt("hastane_id");
        return id;
    }
    
    public int doktor_ismi_to_id(String doktor_ismi, String doktor_soyismi) throws SQLException{
        int id=0;
        String sorgu="Select d_id from doktorlar where d_ad=? and d_soyad=?";
        komutTamamlayici=baglanti.prepareStatement(sorgu);
        komutTamamlayici.setString(1, doktor_ismi);
        komutTamamlayici.setString(2, doktor_soyismi);
        ResultSet sonuc=komutTamamlayici.executeQuery();
        while(sonuc.next())
            id=sonuc.getInt("d_id");
        return id;
    }
    
   public int tckn_to_id(int tckn) throws SQLException{
       int id=0;
        String sorgu="Select h_id from hastalar where tckn=?";
        komutTamamlayici=baglanti.prepareStatement(sorgu);
        komutTamamlayici.setInt(1, tckn);
        ResultSet sonuc=komutTamamlayici.executeQuery();
        while(sonuc.next())
            id=sonuc.getInt("h_id");
        return id;
   }
    
    public LinkedList<String> tum_hastaneleri_getir(String il) throws SQLException{
        LinkedList<String> hastanelerList=new LinkedList<>();
        komutTamamlayici=baglanti.prepareStatement(SEHRE_GORE_HASTANE_GETIR);
        komutTamamlayici.setString(1, il);
        ResultSet sonuc=komutTamamlayici.executeQuery();
        while(sonuc.next()){
            hastanelerList.add(sonuc.getString("hastane_ismi"));
        }
        return hastanelerList;
    }
    
    public LinkedList<String> tum_bolumleri_getir(String hastane_ismi) throws SQLException{
        LinkedList<String> bolumlerList=new LinkedList<>();
        komutTamamlayici=baglanti.prepareStatement(HASTANEYE_GORE_BOLUM_GETIR);
        komutTamamlayici.setString(1, hastane_ismi);
        ResultSet sonuc=komutTamamlayici.executeQuery();
        while(sonuc.next()){
            bolumlerList.add(sonuc.getString("bolumler"));
            
            
        }
        System.out.println("bolumler: \n"+bolumlerList);
        return bolumlerList;
    }
    
    public LinkedList<doktor> doktor_getir(String bolum) throws SQLException{
        doktor dr=null;
        LinkedList<doktor> doktorlarList=new LinkedList<>();
        komutTamamlayici=baglanti.prepareStatement(BOLUME_GORE_DOKTOR_GETIR);
        komutTamamlayici.setString(1, bolum);
        ResultSet sonuc= komutTamamlayici.executeQuery();
        while(sonuc.next()){
            dr=new doktor(
                    null,
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    null,
                    null,
                    null,
                    null,
                    null
            );
            doktorlarList.add(dr);
        }
        return doktorlarList;
    }
    
    public LinkedList<doktor> tum_doktorlari_getir() throws SQLException{
        doktor dr=null;
        LinkedList<doktor> doktorlarList=new LinkedList<>();
        komutTamamlayici=baglanti.prepareStatement(TUM_DOKTORLARI_AL);
        ResultSet sonuc= komutTamamlayici.executeQuery();
        while(sonuc.next()){
            dr=new doktor(
                    null,
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    null,
                    null,
                    null,
                    null,
                    null
            );
            doktorlarList.add(dr);
        }
        return doktorlarList;
    }
    
    public boolean doktor_sil(int d_id) {
        boolean sonuc = false;
        try {
            komutTamamlayici = baglanti.prepareStatement(DOKTOR_SIL);
            komutTamamlayici.setInt(1, d_id);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("doktor silindi");
                sonuc = true;
            } else {
                System.out.println("doktor silinemedi !");
                sonuc = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return sonuc;
    }

    public UI_Doktor doktor_guncelle(UI_Doktor kullanici, int temp_id) {
        try {
            String sifrelenmis= encrypt(kullanici.doktor_sifresi);
            //temp_id ilk secilen buraya gelmesi lazim
            komutTamamlayici = baglanti.prepareStatement(DOKTOR_GUNCELLE);
            komutTamamlayici.setInt(1, Integer.parseInt(kullanici.doktor_id));
            komutTamamlayici.setString(2, kullanici.doktor_adi);
            komutTamamlayici.setString(3, kullanici.doktor_soyadi);
            komutTamamlayici.setString(4, sifrelenmis);
            komutTamamlayici.setString(5, kullanici.bolum_ismi);
            komutTamamlayici.setInt(6, temp_id);
            System.out.println("\nd_id: "+kullanici.doktor_id+
                    "\nd_ad: "+kullanici.doktor_adi+
                    "\nd_soyad: "+kullanici.doktor_soyadi+
                    "\nd_sifresi: "+kullanici.doktor_sifresi+
                    "\nbolum: "+kullanici.bolum_ismi+
                    "\ntemp_id: "+temp_id
            );
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("doktor guncellendi");
            } else {
                System.out.println("doktor guncellenemedi !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return kullanici;
    }
    
    public LinkedList<hasta> tum_hastalari_listele() throws SQLException {
        hasta kullanici = null;
        LinkedList<hasta> hasta_kayit_getir = new LinkedList<>();
        komutTamamlayici = baglanti.prepareStatement(BUTUN_HASTALAR);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            kullanici = new hasta(
                    sonuc.getInt("h_id"),
                    sonuc.getInt("tckn"),
                    sonuc.getString("h_ad"),
                    sonuc.getString("h_soyad"),
                    null,
                    null,
                    null,
                    null,
                    null
            );
            hasta_kayit_getir.add(kullanici);
        }
        return hasta_kayit_getir;
    }

    public LinkedList<doktor> tum_doktorlari_listele() throws SQLException {
        doktor kullanici = null;
        LinkedList<doktor> doktor_kayit_getir = new LinkedList<>();
        komutTamamlayici = baglanti.prepareStatement(BUTUN_DOKTORLAR);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            kullanici = new doktor(
                    sonuc.getInt("d_id"),
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    sonuc.getString("d_sifre"),
                    sonuc.getString("d_bolum"),
                    null,
                    null,
                    null
            );
            doktor_kayit_getir.add(kullanici);
        }
        return doktor_kayit_getir;
    }

    public boolean randevu_sil(int h_id,String doktor_adi,String doktor_soyadi,String tarih) throws SQLException{
        //silme sarti: d_id,h_id ve tarih kesisimi
        //doktor adindan d_id ye ulasilir
        //h_id zaten var
        //tarih alindi
        int dr_id=doktor_ismi_to_id(doktor_adi,doktor_soyadi);
        boolean RETURN;
        komutTamamlayici=baglanti.prepareStatement(RANDEVU_SIL);
        komutTamamlayici.setInt(1, h_id);
        komutTamamlayici.setInt(2, dr_id);
        komutTamamlayici.setString(3, tarih);
        int sonuc=komutTamamlayici.executeUpdate();
        
        if(sonuc>0){
            System.out.println("randevu silindi");
            RETURN=true;
        }
        else{
            System.out.println("randevu silinemedi!");
            RETURN=false;
        }
        return RETURN;
    }
    
    public boolean hasta_sil(int tckn) {
        boolean sonuc = false;
        try {
            komutTamamlayici = baglanti.prepareStatement(HASTA_SIL);
            komutTamamlayici.setInt(1, tckn);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("hasta silindi");
                sonuc = true;
            } else {
                System.out.println("hasta silinemedi !");
                sonuc = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return sonuc;
    }

    public UI_Hasta hasta_guncelle(UI_Hasta kullanici, int temp_tckn) {
        try {
            //tckn ilk secilen buraya gelmesi lazim
            String sifrelenmis=encrypt(kullanici.hasta_sifre);
            
            komutTamamlayici = baglanti.prepareStatement(HASTA_GUNCELLE);
            komutTamamlayici.setInt(1, Integer.parseInt(kullanici.hasta_tckn));
            komutTamamlayici.setString(2, kullanici.hasta_ad);
            komutTamamlayici.setString(3, kullanici.hasta_soyad);
            komutTamamlayici.setString(4, sifrelenmis);
            komutTamamlayici.setInt(5, temp_tckn);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("hasta guncellendi");
            } else {
                System.out.println("hasta guncellenemedi !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return kullanici;
    }

    public UI_Hasta hasta_kayit(UI_Hasta kullanici) {

          String sifrelenmis=encrypt(kullanici.hasta_sifre);
          
        try {
            komutTamamlayici = baglanti.prepareStatement(HASTA_KAYIT_ET);
            //komutTamamlayici.setInt(1, Integer.parseInt(kullanici.hasta_id));
            komutTamamlayici.setInt(1, Integer.parseInt(kullanici.hasta_tckn));
            komutTamamlayici.setString(2, kullanici.hasta_ad);
            komutTamamlayici.setString(3, kullanici.hasta_soyad);
            //komutTamamlayici.setString(5, kullanici.hasta_sifre);
            komutTamamlayici.setString(4, sifrelenmis);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("hasta eklendi");
            } else {
                System.out.println("hasta eklenemedi !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return kullanici;
    }

    public UI_Doktor doktor_kayit(UI_Doktor kullanici) {
        String sifrelenmis=encrypt(kullanici.doktor_sifresi);
        try {
            komutTamamlayici = baglanti.prepareStatement(DOKTOR_KAYIT_ET);
            komutTamamlayici.setInt(1, Integer.parseInt(kullanici.doktor_id));
            komutTamamlayici.setString(2, kullanici.doktor_adi);
            komutTamamlayici.setString(3, kullanici.doktor_soyadi);
            komutTamamlayici.setString(4, sifrelenmis);
            komutTamamlayici.setString(5, kullanici.bolum_ismi);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("doktor eklendi");
            } else {
                System.out.println("doktor eklenemedi !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return kullanici;
    }

    public hasta hasta_bul(int hasta_tckn, String girilenSifre) throws SQLException {
        //bu bilgiler dbden gelcek
        hasta kullanici = null;
        String sifrelenmis=encrypt(girilenSifre);
        komutTamamlayici = baglanti.prepareStatement(HASTA_ADI_SIFRESI);
        komutTamamlayici.setInt(1, hasta_tckn);
        //1.soru isareti yerine yani h_ad=? yerine korac yazilirsa h_ad="korac" olacak
        //komutTamamlayici.setString(2, girilenSifre);
        komutTamamlayici.setString(2, sifrelenmis);
        
        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            String cozulmus = decrypt(sonuc.getString("h_sifre"));
            kullanici = new hasta(
                    sonuc.getInt("h_id"),
                    sonuc.getInt("tckn"),
                    sonuc.getString("h_ad"),
                    sonuc.getString("h_soyad"),
                    cozulmus,
//sonuc.getString("h_sifre"),
                    null,
                    null,
                    null,
                    null
            );
            System.out.println("\nhasta id: " + sonuc.getInt("h_id"));
            System.out.println("hasta tckn: " + sonuc.getInt("tckn"));
            System.out.println("hasta ismi: " + sonuc.getString("h_ad"));
            System.out.println("hasta soyismi: " + sonuc.getString("h_soyad"));
            System.out.println("hasta sifresi: " + sonuc.getString("h_sifre") + "\n");

        }
        return kullanici;
    }

    public doktor doktor_bul(String d_ad, String d_sifre) throws SQLException {
        doktor kullanici = null;
        String sifrelenmis=encrypt(d_sifre);
        komutTamamlayici = baglanti.prepareStatement(DOKTOR_ADI_SIFRESI);
        komutTamamlayici.setString(1, d_ad);
        komutTamamlayici.setString(2, sifrelenmis);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            String cozulmus = decrypt(sonuc.getString("d_sifre"));
            kullanici = new doktor(
                    sonuc.getInt("d_id"),
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    cozulmus,
                    sonuc.getString("d_bolum"),
                    null, null, null
            );
            System.out.println("\ndoktor id: " + sonuc.getInt("d_id"));
            System.out.println("doktor ad: " + sonuc.getString("d_ad"));
            System.out.println("doktor soyad: " + sonuc.getString("d_soyad"));
            System.out.println("doktor sifre: " + sonuc.getString("d_sifre"));
            System.out.println("doktor bolum: " + sonuc.getString("d_bolum") + "\n");
        }
        return kullanici;
    }

    public LinkedList<hasta> hasta_kayit_getir(int tckn) throws SQLException {

        hasta kullanici = null;
        LinkedList<hasta> hasta_kayit_getir = new LinkedList<>();
        komutTamamlayici = baglanti.prepareStatement(HASTA_KAYIT_GETIR);
        komutTamamlayici.setInt(1, tckn);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            kullanici = new hasta(
                    sonuc.getInt("h_id"),
                    sonuc.getInt("tckn"),
                    sonuc.getString("h_ad"),
                    sonuc.getString("h_soyad"),
                    sonuc.getString("h_sifre"),
                    sonuc.getString("tarih"),
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    sonuc.getString("d_bolum")
            );
            hasta_kayit_getir.add(kullanici);
        }
        return hasta_kayit_getir;
    }

    public LinkedList<doktor> doktor_kayit_getir(int d_id) throws SQLException {
        doktor kullanici = null;
        LinkedList<doktor> doktor_kayit_getir = new LinkedList<>();

        komutTamamlayici = baglanti.prepareStatement(DOKTOR_KAYIT_GETIR);
        komutTamamlayici.setInt(1, d_id);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            kullanici = new doktor(
                    sonuc.getInt("d_id"),
                    sonuc.getString("d_ad"),
                    sonuc.getString("d_soyad"),
                    sonuc.getString("d_sifre"),
                    sonuc.getString("d_bolum"),
                    sonuc.getString("h_ad"),
                    sonuc.getString("h_soyad"),
                    sonuc.getString("tarih")
            );
            doktor_kayit_getir.add(kullanici);
        }
        return doktor_kayit_getir;
    }
    
    public LinkedList<doktor> doktor_kayit_getir2(int d_id) throws SQLException {
        doktor kullanici = null;
        LinkedList<doktor> doktor_kayit_getir = new LinkedList<>();

        komutTamamlayici = baglanti.prepareStatement(DOKTOR_KAYIT_GETIR2);
        komutTamamlayici.setInt(1, d_id);

        ResultSet sonuc = komutTamamlayici.executeQuery();

        while (sonuc.next()) {
            kullanici = new doktor(
                    null,
                    null,
                    null,
                    null,
                    null,
                    sonuc.getString("h_ad"), //
                    sonuc.getString("h_soyad"), //
                    sonuc.getString("tarih") //
            );
            doktor_kayit_getir.add(kullanici);
        }
        return doktor_kayit_getir;
    }
    
    
    
    
    public boolean randevu_al(int h_id){
        //private final String RANDEVU_AL="INSERT into kayit VALUES(?,?,?,?,?)";
        boolean sonuc = false;
        try {
            komutTamamlayici = baglanti.prepareStatement(DOKTOR_SIL);
            komutTamamlayici.setInt(1, h_id);
            komutTamamlayici.setString(2, HOST);
            int ex_sonuc = komutTamamlayici.executeUpdate();

            if (ex_sonuc > 0) {
                System.out.println("doktor silindi");
                sonuc = true;
            } else {
                System.out.println("doktor silinemedi !");
                sonuc = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(sql_kullanici_islemleri.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return sonuc;
                
    }
}
