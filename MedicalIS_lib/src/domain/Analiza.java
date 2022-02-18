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
public class Analiza implements GeneralDObject, Serializable {

    Long sifraAnalize;
    String vrstaAnalize, vrstaUzorka;
    Uput uput;

    public Analiza(Long sifraAnalize) {
        this.sifraAnalize = sifraAnalize;
    }

    public Analiza(Long sifraAnalize, String vrstaAnalize, String vrstaUzorka, Uput uput) {
        this.sifraAnalize = sifraAnalize;
        this.vrstaAnalize = vrstaAnalize;
        this.vrstaUzorka = vrstaUzorka;
        this.uput = uput;
    }

    public Analiza() {
    }

    public Long getSifraAnalize() {
        return sifraAnalize;
    }

    public void setSifraAnalize(Long sifraAnalize) {
        this.sifraAnalize = sifraAnalize;
    }

    public String getVrstaAnalize() {
        return vrstaAnalize;
    }

    public void setVrstaAnalize(String vrstaAnalize) {
        this.vrstaAnalize = vrstaAnalize;
    }

    public String getVrstaUzorka() {
        return vrstaUzorka;
    }

    public void setVrstaUzorka(String vrstaUzorka) {
        this.vrstaUzorka = vrstaUzorka;
    }

    public Uput getUput() {
        return uput;
    }

    public void setUput(Uput uput) {
        this.uput = uput;
    }

    @Override
    public String getAtrValue() {
        return sifraAnalize + ", '" + vrstaAnalize + "', '" + vrstaUzorka + "', " + uput.getSifraUputa();
    }

    @Override
    public String setAtrValue() {
        return "sifra_analize = " + sifraAnalize + ", vrsta_analize='" + vrstaAnalize + "', vrsta_uzorka='" + vrstaUzorka + "', sifra_uputa=" + uput.getSifraUputa();
    }

    @Override
    public String getClassName() {
        return "Analiza";
    }

    @Override
    public String getWhereCondition() {
        return "sifra_uputa = " + uput.getSifraUputa();
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Analiza(
                rs.getLong("sifra_analize"),
                rs.getString("vrsta_analize"),
                rs.getString("vrsta_uzorka"),
                new Uput(rs.getLong("sifra_uputa")));
    }

    @Override
    public String getFields() {
        return "sifra_analize";
    }

    @Override
    public String toString() {
        return "sifra_analize = " + sifraAnalize + ", vrsta_analize='" + vrstaAnalize + "', vrsta_uzorka='" + vrstaUzorka + "'";
    }

}
