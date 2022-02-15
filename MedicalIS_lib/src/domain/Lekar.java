/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maja
 */
public class Lekar implements Serializable, GeneralDObject{
    Long SifraLekara;
    String ime;
    String prezime;
    String username;
    String password;

    public Lekar() {
    }

    public Lekar(Long SifraLekara, String ime, String prezime, String username, String password) {
        this.SifraLekara = SifraLekara;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Lekar(Long SifraLekara) {
        this.SifraLekara = SifraLekara;
    }
        
    public Long getSifraLekara() {
        return SifraLekara;
    }

    public void setSifraLekara(Long SifraLekara) {
        this.SifraLekara = SifraLekara;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String getAtrValue() {
        return SifraLekara + ", '" + ime + "', '" + prezime + "', '" + username + "', '" + password + "'";
    }

    @Override
    public String setAtrValue() {
        return "sifra_lekara = " + SifraLekara + ", ime='" + ime + "', prezime='" + prezime + "', korisnicko_ime'" + username + "', korisnicka_sifra='" + password + "'";
    }

    @Override
    public String getClassName() {
        return "Lekar";
    }

    @Override
    public String getWhereCondition() {
        return "korisnicko_ime='" + username + "' AND korisnicka_sifra='" + password + "'";
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Lekar(rs.getLong("sifra_lekara"), rs.getString("ime"), rs.getString("prezime"), 
                rs.getString("korisnicko_ime"), rs.getString("korisnicka_sifra"));
    }
    
    
}
