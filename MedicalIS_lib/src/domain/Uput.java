/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Maja
 */
public class Uput implements Serializable, GeneralDObject {

    Long sifraUputa;
    Date datumUputa;
    String uputnaDijagnoza;
    Lekar lekar;
    KartonPacijenta pacijent;
    String vrstaUzorka;
    String analiza;

    public Uput() {
    }

    public Uput(Long sifraUputa, Date datumUputa, String uputnaDijagnoza, Lekar lekar, KartonPacijenta pacijent, String vrstaUzorka, String analiza) {
        this.sifraUputa = sifraUputa;
        this.datumUputa = datumUputa;
        this.uputnaDijagnoza = uputnaDijagnoza;
        this.lekar = lekar;
        this.pacijent = pacijent;
        this.vrstaUzorka = vrstaUzorka;
        this.analiza = analiza;
    }
     public Uput(Long sifraUputa, Date datumUputa, String uputnaDijagnoza, String vrstaUzorka) {
        this.sifraUputa = sifraUputa;
        this.datumUputa = datumUputa;
        this.uputnaDijagnoza = uputnaDijagnoza;

        this.vrstaUzorka = vrstaUzorka;
    }

    public Uput(Long sifraUputa) {
        this.sifraUputa = sifraUputa;
    }
     
    public Long getSifraUputa() {
        return sifraUputa;
    }

    public void setSifraUputa(Long sifraUputa) {
        this.sifraUputa = sifraUputa;
    }

    public Date getDatumUputa() {
        return datumUputa;
    }

    public void setDatumUputa(Date datumUputa) {
        this.datumUputa = datumUputa;
    }

    public String getUputnaDijagnoza() {
        return uputnaDijagnoza;
    }

    public void setUputnaDijagnoza(String uputnaDijagnoza) {
        this.uputnaDijagnoza = uputnaDijagnoza;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public KartonPacijenta getPacijent() {
        return pacijent;
    }

    public void setPacijent(KartonPacijenta pacijent) {
        this.pacijent = pacijent;
    }

    public String getVrstaUzorka() {
        return vrstaUzorka;
    }

    public void setVrstaUzorka(String vrstaUzorka) {
        this.vrstaUzorka = vrstaUzorka;
    }

    public String getAnaliza() {
        return analiza;
    }

    public void setAnaliza(String analiza) {
        this.analiza = analiza;
    }

    @Override
    public String getAtrValue() {
        return sifraUputa + ", '" + datumUputa + "', '"
                + uputnaDijagnoza + "', " + lekar.getSifraLekara().toString() + ", "
                + pacijent.getJmbg() + ", '" + vrstaUzorka + "', '" + analiza + "'";
    }

    @Override
    public String setAtrValue() {
        return "sifra_uputa=" + sifraUputa + ", datum_uputa='" + datumUputa + "', uputna_dijagnoza='"
                + uputnaDijagnoza + "', sifra_lekara=" + lekar.getSifraLekara().toString() + ", jmbg="
                + pacijent.getJmbg() + ", vrsta_uzorka='" + vrstaUzorka + "', analiza='" + analiza + "'";
    }

    @Override
    public String getClassName() {
        return "Uput";
    }

    @Override
    public String getWhereCondition() {
        return "jmbg = " + pacijent.getJmbg().toString();
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Uput(rs.getLong("sifra_uputa"), rs.getDate("datum_uputa"), 
                rs.getString("uputna_dijagnoza"), new Lekar(rs.getLong("sifra_lekara")), 
                new KartonPacijenta(rs.getLong("jmbg")), rs.getString("vrsta_uzorka"), rs.getString("analiza"));
    }

    @Override
    public String toString() {
        return "sifra_uputa=" + sifraUputa + ", datum_uputa='" + datumUputa + "', uputna_dijagnoza='"
                + uputnaDijagnoza + "', sifra_lekara=" + lekar.getSifraLekara().toString() + ", jmbg="
                + pacijent.getJmbg() + ", vrsta_uzorka='" + vrstaUzorka + "', analiza='" + analiza + "'";
    }
    

}
