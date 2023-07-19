package Kullanicilar;

public class hasta {
    //degiskenler
    private Integer tckn,id;
    private String h_ad,h_soyad,h_sifre;
    
    //bu kisim sorun cikarticak mi bakalim
    private String h_randevu_tarihi,h_doktoru_adi,h_doktoru_soyadi,h_bolum;
    
    //constructor

    public hasta(Integer id, Integer tckn, String h_ad, String h_soyad, String h_sifre, String h_randevu_tarihi, String h_doktoru_adi, String h_doktoru_soyadi, String h_bolum) {
        this.id = id;
        this.tckn = tckn;
        this.h_ad = h_ad;
        this.h_soyad = h_soyad;
        this.h_sifre = h_sifre;
        this.h_randevu_tarihi = h_randevu_tarihi;
        this.h_doktoru_adi = h_doktoru_adi;
        this.h_doktoru_soyadi = h_doktoru_soyadi;
        this.h_bolum = h_bolum;
    }
   
    
    //getters
    public Integer getId() {
        return id;
    }
  
    public Integer getTckn() {
        return tckn;
    }

    public String getH_ad() {
        return h_ad;
    }

    public String getH_soyad() {
        return h_soyad;
    }

    public String getH_sifre() {
        return h_sifre;
    }

    public String getH_randevu_tarihi() {
        return h_randevu_tarihi;
    }

    public String getH_doktoru_adi() {
        return h_doktoru_adi;
    }

    public String getH_doktoru_soyadi() {
        return h_doktoru_soyadi;
    }

    public String getH_bolum() {
        return h_bolum;
    }
    
    
}
