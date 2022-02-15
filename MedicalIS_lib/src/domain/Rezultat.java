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
public class Rezultat implements Serializable, GeneralDObject {

    Long sifraRezultata;
    String rezultat_analize;
    Date datumIzdavanja;
    Laborant laborant;
    Uput uput;

    public Rezultat() {
    }

    public Rezultat(Long sifraRezultata) {
        this.sifraRezultata = sifraRezultata;
    }

    public Rezultat(Long sifraRezultata, String rezultat_analize, Date datumIzdavanja, Laborant laborant, Uput uput) {
        this.sifraRezultata = sifraRezultata;
        this.rezultat_analize = rezultat_analize;
        this.datumIzdavanja = datumIzdavanja;
        this.laborant = laborant;
        this.uput = uput;
    }

    public Long getSifraRezultata() {
        return sifraRezultata;
    }

    public void setSifraRezultata(Long sifraRezultata) {
        this.sifraRezultata = sifraRezultata;
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

    public Uput getUput() {
        return uput;
    }

    public void setUput(Uput uput) {
        this.uput = uput;
    }
    
    
    @Override
    public String getAtrValue() {
        return sifraRezultata + ", '"  + rezultat_analize + "', '" + datumIzdavanja + "', " + laborant.getSifraLaboranta() + ", " + uput.getSifraUputa();
    }

    @Override
    public String setAtrValue() {
        return "sifra_rezultata=" + sifraRezultata + ", rezultat_analize='"  + rezultat_analize + "', datum_izdavanja='" + datumIzdavanja + "', sifra_laboranta=" + laborant.getSifraLaboranta() + ", sifra_uputa=" + uput.getSifraUputa();
    }

    @Override
    public String getClassName() {
        return "Rezultat";
    }

    @Override
    public String getWhereCondition() {
        return "sifra_uputa=" + uput.getSifraUputa();
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Rezultat(rs.getLong("sifra_rezultata"), rs.getString("rezultat_analize"), rs.getDate("datum_izdavanja"), new Laborant(rs.getLong("sifra_laboranta")), new Uput(rs.getLong("sifra_uputa"))); }

    @Override
    public String toString() {
        return "sifra_rezultata=" + sifraRezultata + ", rezultat_analize='"  + rezultat_analize + 
                "', datum_izdavanja='" + datumIzdavanja 
                + ", sifra_uputa=" + uput.getSifraUputa();
        
        
    }
    
    
          
}
