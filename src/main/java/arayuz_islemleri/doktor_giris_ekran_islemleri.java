package arayuz_islemleri;


import java.util.Objects;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import sql_islemleri.sql_kullanici_islemleri;

public class doktor_giris_ekran_islemleri {

    //component degiskenler
    private JTextField jTextField_DoktorAdi;
    
    private JPasswordField jPasswordField;

    private String girilenDoktorAdi;
    private String girilenSifre;

    //degiskenlerimiz - mesaj
    private String sonucMesaj; //en genel mesaj
    
    /* kaldirildi
    private String sonucGiris_BosAlan; //giris ekrani hata alirsak
    private String sonucGiris_Mesaj;
    */
    //constructor
    public doktor_giris_ekran_islemleri(JTextField jTextField_DoktorAdi, JPasswordField jPasswordField) {
        this.jTextField_DoktorAdi = jTextField_DoktorAdi;
        this.jPasswordField = jPasswordField;

        this.girilenDoktorAdi = jTextField_DoktorAdi.getText();
        this.girilenSifre = new String(this.jPasswordField.getPassword());
    }

    //girisYap -> TCKN_ve_Sifre_Kontrol olarak degistirildi!
    /* simdilik silindi
    public boolean TCKN_ve_Sifre_Kontrol(String TCKN, String Sifre) {
        Kullanici kullanici;
        //kullanici=new Kullanici(200, "Aysenur", "Yildizdal", "ay");
        //kullanici adi ve sifresi yanlis hatasi buradan aliyoruz cunku asadgideki nesnemiz olusturuldu
        //TCKN ve Sifre giris ekranindan alinip buradaki fonksiyona yollanildi. 
        System.out.println("\nTCKN: " + TCKN + "\nSifre: " + Sifre);

        boolean bosGirisAlanKontrol = bosGirisKontrol();
        boolean sifreGirisKontrol = sifreKontrol(girilenTCKN,TCKN,girilenSifre,Sifre);
        if (!bosGirisAlanKontrol) {
            this.sonucMesaj = this.sonucGiris_BosAlan;
            return false;
        } else if (!sifreGirisKontrol) {
            this.sonucMesaj = this.sonucGiris_Mesaj;
            return false;
        } else {
            this.sonucMesaj = "Giris Basarili!";
            return true;
        }
    }
    */

    //sonucGiris_BosAlan'i degistiriyor protected->public yapildi!
    public boolean bosGirisKontrol() {

        if (girilenDoktorAdi.equals("") & girilenSifre.equals("")) {
            this.sonucMesaj = "Doktor Adi ve Sifre giriniz!";
            return false;
        } else if (girilenDoktorAdi.equals("")) {
            this.sonucMesaj = "Doktor Adi giriniz!";
            return false;
        } else if (girilenSifre.equals("")) {
            this.sonucMesaj = "Sifre giriniz!";
            return false;
        } else {
            this.sonucMesaj = "Kullanici girisi basarili";
            return true;
        }
    }

    /* kaldirildi simdilik
    protected boolean sifreKontrol(String girilen_tckn, String tckn, String girilen_sifre, String sifre) {
        Kullanici kullanici = null;
        if (girilen_tckn.equals(tckn) & Objects.equals(girilen_sifre, sifre)) {
            this.sonucGiris_Mesaj = "Basarili from sifreKontrol";
            return true;
        } else {
            this.sonucGiris_Mesaj = "Kullanici adiniz veya sifreniz yanlis girildi!";
            return false;
        }
    }
    */
    public String getSonucMesaj() {
        return sonucMesaj;
    }
    
    /* bu ne?
    public String getSonucGiris_Mesaj() {
        return sonucGiris_Mesaj;
    }
    */

}
