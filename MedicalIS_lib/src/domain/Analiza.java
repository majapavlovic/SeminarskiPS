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
public class Analiza implements Serializable, GeneralDObject {
    
    Long sifraAnalize;
    String naziv;
    Uput uput;

    public Analiza() {
    }

    public Analiza(Long sifraAnalize, String naziv, Uput uput) {
        this.sifraAnalize = sifraAnalize;
        this.naziv = naziv;
        this.uput = uput;
    }

    public Long getSifraAnalize() {
        return sifraAnalize;
    }

    public void setSifraAnalize(Long sifraAnalize) {
        this.sifraAnalize = sifraAnalize;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Uput getUput() {
        return uput;
    }

    public void setUput(Uput uput) {
        this.uput = uput;
    }
    
    @Override
    public String getAtrValue() {
        return sifraAnalize + ", " + naziv + ", " + uput.getSifraUputa();
    }

    @Override
    public String setAtrValue() {
        return "sifra_analize= " + sifraAnalize + ", naziv_analize=" + naziv + "sifra_uputa=" + uput.getSifraUputa();
    }

    @Override
    public String getClassName() {
        return "Analiza";
    }

    @Override
    public String getWhereCondition() {
        return "sifra_analize=" + sifraAnalize;
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Analiza();
    }
    
}
