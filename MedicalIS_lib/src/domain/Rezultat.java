/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Maja
 */
public class Rezultat implements Serializable, GeneralDObject {

    Long sifra_rezultata;
    String rezultat_analize;
    Date datumIzdavanja;
    Laborant laborant;
    Analiza analiza;

    public Rezultat() {
    }

    public Rezultat(Long sifra_rezultata, String rezultat_analize, Date datumIzdavanja, Laborant laborant, Analiza analiza) {
        this.sifra_rezultata = sifra_rezultata;
        this.rezultat_analize = rezultat_analize;
        this.datumIzdavanja = datumIzdavanja;
        this.laborant = laborant;
        this.analiza = analiza;
    }

    public Long getSifra_rezultata() {
        return sifra_rezultata;
    }

    public void setSifra_rezultata(Long sifra_rezultata) {
        this.sifra_rezultata = sifra_rezultata;
    }

    public Analiza getAnaliza() {
        return analiza;
    }

    public void setAnaliza(Analiza analiza) {
        this.analiza = analiza;
    }

    public String getRezultat_analize() {
        return rezultat_analize;
    }

    public void setRezultat_analize(String rezultat_analize) {
        this.rezultat_analize = rezultat_analize;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Laborant getLaborant() {
        return laborant;
    }

    public void setLaborant(Laborant laborant) {
        this.laborant = laborant;
    }

    @Override
    public String getAtrValue() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return sifra_rezultata + ", '" + rezultat_analize + "', '" + formatter.format(datumIzdavanja)
                + "', '" + laborant.getUsername() + "', " + analiza.getSifraAnalize();
    }

    @Override
    public String setAtrValue() {
        return "sifra_rezultata=" + sifra_rezultata + ", rezultat_analize='" + rezultat_analize
                + "', datum_izdavanja='" + datumIzdavanja + "', laborant='" + laborant.getUsername() 
                + "', sifra_analize=" + analiza.getSifraAnalize();
    }

    @Override
    public String getClassName() {
        return "Rezultat";
    }

    @Override
    public String getWhereCondition() {
        return "sifra_analize = " + analiza.getSifraAnalize();
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Rezultat(rs.getLong("sifra_rezultata"), rs.getString("rezultat_analize"),
                rs.getDate("datum_izdavanja"), new Laborant(rs.getString("laborant")),
                new Analiza(rs.getLong("sifra_analize")));
    }

    @Override
    public String getOrderBy() {
        return "sifra_rezultata ASC";
    }

    @Override
    public String getFields() {
        return "sifra_rezultata";
    }
    
    @Override
    public String toString() {
        return "sifra_rezultata=" + sifra_rezultata + ", rezultat_analize='" + rezultat_analize
                + "', datum_izdavanja='" + datumIzdavanja + "";
    }

}
