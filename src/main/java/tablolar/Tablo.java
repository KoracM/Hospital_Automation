package tablolar;

import Kullanicilar.doktor;
import Kullanicilar.hasta;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tablo {

    private JTable jTable;
    private DefaultTableModel tabloModel;
    private DefaultTableCellRenderer tabloSutun;
    private Color turuncu = new Color(244, 114, 43);
    private Color dark_grey = new Color(60, 63, 65);
    private Color white = new Color(255, 255, 255);
    private Font fontArialBold_16 = new Font("Arial", Font.BOLD, 16);

    public Tablo(JTable jTable) {
        this.jTable = jTable;
        this.tabloModel = (DefaultTableModel) this.jTable.getModel();
        this.tabloSutun = new DefaultTableCellRenderer();
        HastaTabloDuzenle();
        //jTable.setEnabled(false);
        jTable.setCellSelectionEnabled(true);
    }

    protected void HastaTabloDuzenle() {

        tabloModel.setColumnCount(0);
        tabloModel.setRowCount(0);

        tabloModel.addColumn("TCKN");
        tabloModel.addColumn("Tarih");
        tabloModel.addColumn("Doktor adi");
        tabloModel.addColumn("Doktor soyadi");
        tabloModel.addColumn("Bolum");

        int tabloSutunSayisi = this.jTable.getColumnCount();
        this.jTable.getTableHeader().setFont(fontArialBold_16);
        this.jTable.getTableHeader().setForeground(turuncu);
        this.jTable.setBackground(dark_grey);
        this.jTable.getTableHeader().setBackground(dark_grey);
        this.jTable.setForeground(white);
        for (int i = 0; i < tabloSutunSayisi; i++) {
            this.jTable.getColumnModel().getColumn(i).setCellRenderer(this.tabloSutun);
        }

    }

    public void HastaTabloDoldur(hasta Hasta) {
        tabloModel.insertRow(tabloModel.getRowCount(), new Object[]{
            Hasta.getTckn(),
            Hasta.getH_randevu_tarihi(),
            Hasta.getH_doktoru_adi(),
            Hasta.getH_doktoru_soyadi(),
            Hasta.getH_bolum()
        });
    }

    public void TumHastalarTabloDuzenle() {
        tabloModel.setColumnCount(0);
        tabloModel.setRowCount(0);

        tabloModel.addColumn("TCKN");
        tabloModel.addColumn("Ad");
        tabloModel.addColumn("Soyad");

        int tabloSutunSayisi = this.jTable.getColumnCount();
        this.jTable.getTableHeader().setFont(fontArialBold_16);
        this.jTable.getTableHeader().setForeground(turuncu);
        this.jTable.setBackground(dark_grey);
        this.jTable.getTableHeader().setBackground(dark_grey);
        this.jTable.setForeground(white);
        for (int i = 0; i < tabloSutunSayisi; i++) {
            this.jTable.getColumnModel().getColumn(i).setCellRenderer(this.tabloSutun);
        }
    }

    public void TumDoktorlarTabloDuzenle() {
        tabloModel.setColumnCount(0);
        tabloModel.setRowCount(0);

        tabloModel.addColumn("Id");
        tabloModel.addColumn("Ad");
        tabloModel.addColumn("Soyad");
        tabloModel.addColumn("Bolum");

        int tabloSutunSayisi = this.jTable.getColumnCount();
        this.jTable.getTableHeader().setFont(fontArialBold_16);
        this.jTable.getTableHeader().setForeground(turuncu);
        this.jTable.setBackground(dark_grey);
        this.jTable.getTableHeader().setBackground(dark_grey);
        this.jTable.setForeground(white);
        for (int i = 0; i < tabloSutunSayisi; i++) {
            this.jTable.getColumnModel().getColumn(i).setCellRenderer(this.tabloSutun);
        }
    }

    public void TumHastalarTabloDoldur(hasta Hasta) {
        //tum hastalar geliyor fakat en son hastayi giriyor sadece
        tabloModel.insertRow(tabloModel.getRowCount(), new Object[]{
            Hasta.getTckn(),
            Hasta.getH_ad(),
            Hasta.getH_soyad()
        });
    }

    public void TumDoktorlarTabloDoldur(doktor Doktor) {
        //tum hastalar geliyor fakat en son hastayi giriyor sadece
        tabloModel.insertRow(tabloModel.getRowCount(), new Object[]{
            Doktor.getD_id(),
            Doktor.getD_ad(),
            Doktor.getD_soyad(),
            Doktor.getD_bolum()
        });
    }

    public void DoktorTabloDuzenle() {

        tabloModel.setColumnCount(0);
        tabloModel.setRowCount(0);

        tabloModel.addColumn("Doktor id");
        tabloModel.addColumn("Doktor ad");
        tabloModel.addColumn("Doktor Soyad");
        tabloModel.addColumn("Bolum");

        int tabloSutunSayisi = this.jTable.getColumnCount();
        this.jTable.getTableHeader().setFont(fontArialBold_16);
        this.jTable.getTableHeader().setForeground(turuncu);
        this.jTable.setBackground(dark_grey);
        this.jTable.getTableHeader().setBackground(dark_grey);
        this.jTable.setForeground(white);
        for (int i = 0; i < tabloSutunSayisi; i++) {
            this.jTable.getColumnModel().getColumn(i).setCellRenderer(this.tabloSutun);
        }

    }

    public void DoktorTabloDoldur(doktor Doktor) {
        tabloModel.insertRow(tabloModel.getRowCount(), new Object[]{
            Doktor.getD_id(),
            Doktor.getD_ad(),
            Doktor.getD_soyad(),
            Doktor.getD_bolum(),
                Doktor.getH_ad()
        });
    }
    
    public void DoktorRandevuTabloDuzenle() {

        tabloModel.setColumnCount(0);
        tabloModel.setRowCount(0);

        tabloModel.addColumn("Randevu tarihi");
        tabloModel.addColumn("Hasta adi");
        tabloModel.addColumn("Hasta soyadi");

        int tabloSutunSayisi = this.jTable.getColumnCount();
        this.jTable.getTableHeader().setFont(fontArialBold_16);
        this.jTable.getTableHeader().setForeground(turuncu);
        this.jTable.setBackground(dark_grey);
        this.jTable.getTableHeader().setBackground(dark_grey);
        this.jTable.setForeground(white);
        for (int i = 0; i < tabloSutunSayisi; i++) {
            this.jTable.getColumnModel().getColumn(i).setCellRenderer(this.tabloSutun);
        }

    }

    public void DoktorRandevuTabloDoldur(doktor Doktor) {
        tabloModel.insertRow(tabloModel.getRowCount(), new Object[]{
            Doktor.getKayit(),
            Doktor.getH_ad(),
            Doktor.getH_soyad()
        });
    }

}
