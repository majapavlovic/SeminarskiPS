/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 *
 * @author Maja
 */
public class Laborant implements Serializable, GeneralDObject {

    String username;
    String password;
    String ime;
    String prezime;

    public Laborant() {
    }

    public Laborant(String username, String password, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Laborant(String username) {
        this.username = username;
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
        return "'" + username + "', '" + password + "', '" + ime + "', '" + prezime + "'";

    }

    @Override
    public String setAtrValue() {
        return "korisnicko_ime='" + username + "', korisnicka_sifra='" +
                password + "', ime='" + ime + "', prezime='" + prezime + "'";
    }

    @Override
    public String getClassName() {
        return "Laborant";
    }

    @Override
    public String getWhereCondition() {
        return "korisnicko_ime='" + username + "' AND korisnicka_sifra='" + password + "'";
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Laborant(rs.getString("korisnicko_ime"), rs.getString("korisnicka_sifra"), 
                rs.getString("ime"), rs.getString("prezime"));
    }

    @Override
    public String getWhereCondition1() {
        return "korisnicko_ime='" + username + "'";
    }

}
