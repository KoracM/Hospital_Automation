package Kullanicilar;

public class doktor {

    private Integer d_id;
    private String d_ad, d_soyad, d_sifre, d_bolum;

    //sonradan tanimlananlar
    private String h_ad, h_soyad, kayit;

    //constructor
    public doktor(Integer d_id, String d_ad, String d_soyad, String d_sifre, String d_bolum, String h_ad, String h_soyad, String kayit) {
        this.d_id = d_id;
        this.d_ad = d_ad;
        this.d_soyad = d_soyad;
        this.d_sifre = d_sifre;
        this.d_bolum = d_bolum;
        this.h_ad = h_ad;
        this.h_soyad = h_soyad;
        this.kayit = kayit;
    }

    //getters
    public String getH_ad() {
        return h_ad;
    }

    public String getH_soyad() {
        return h_soyad;
    }

    public String getKayit() {
        return kayit;
    }

    public Integer getD_id() {
        return d_id;
    }

    public String getD_ad() {
        return d_ad;
    }

    public String getD_soyad() {
        return d_soyad;
    }

    public String getD_sifre() {
        return d_sifre;
    }

    public String getD_bolum() {
        return d_bolum;
    }
}
