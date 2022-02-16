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

    Long brojProtokola;
    String rezultat_analize;
    Date datumIzdavanja;
    Laborant laborant;

    public Rezultat() {
    }

    public Rezultat(Long brojProtokola) {
        this.brojProtokola = brojProtokola;
    }

    public Rezultat(Long brojProtokola, String rezultat_analize, Date datumIzdavanja, Laborant laborant) {
        this.brojProtokola = brojProtokola;
        this.rezultat_analize = rezultat_analize;
        this.datumIzdavanja = datumIzdavanja;
        this.laborant = laborant;
        
    }

    public Long getBrojProtokola() {
        return brojProtokola;
    }

    public void setBrojProtokola(Long brojProtokola) {
        this.brojProtokola = brojProtokola;
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
        return brojProtokola + ", '"  + rezultat_analize + "', '" + datumIzdavanja + "', " + laborant.getUsername();
    }

    @Override
    public String setAtrValue() {
        return "broj_protokola=" + brojProtokola + ", rezultat_analize='"  + rezultat_analize + "', datum_izdavanja='" + datumIzdavanja + "', sifra_laboranta=" + laborant.getUsername();
    }

    @Override
    public String getClassName() {
        return "Rezultat";
    }

    @Override
    public String getWhereCondition() {
        return "broj_protokola=" + brojProtokola;
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Rezultat(rs.getLong("broj_protokola"), rs.getString("rezultat_analize"), rs.getDate("datum_izdavanja"), new Laborant(rs.getString("korisnicko_ime"))); }

    @Override
    public String toString() {
        return "broj_protokola=" + brojProtokola + ", rezultat_analize='"  + rezultat_analize + 
                "', datum_izdavanja='" + datumIzdavanja + "'";
        
        
    }

    @Override
    public String getOrderBy() {
        return "broj_protokola DESC";
    }
    
    
          
}
