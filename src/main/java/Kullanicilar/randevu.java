package Kullanicilar;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class randevu {

    //degiskenler
    int hastane_id,h_id,d_id;
    String il, hastane_ismi, konum, tel, bolum,tarih;
    //constructor

    public randevu(int hastane_id, int h_id, int d_id, String il, String hastane_ismi, String konum, String tel, String bolum, String tarih) {
        this.hastane_id = hastane_id;
        this.h_id = h_id;
        this.d_id = d_id;
        this.il = il;
        this.hastane_ismi = hastane_ismi;
        this.konum = konum;
        this.tel = tel;
        this.bolum = bolum;
        this.tarih = tarih;
    }
    
}
