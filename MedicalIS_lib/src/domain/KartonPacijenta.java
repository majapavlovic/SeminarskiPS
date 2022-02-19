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
import java.util.List;

/**
 *
 * @author Maja
 */
public class KartonPacijenta implements Serializable, GeneralDObject {

    Long jmbg, lbo;
    String ime;
    String prezime;
    String pol;
    Date datumRodjenja;
    String adresa;
    String kontaktTelefon;
    String krvnaGrupa;
    String hronicneDijagnoze;
    Lekar lekar;

    List<Uput> uputi;

    public KartonPacijenta() {
    }

    public KartonPacijenta(Long jmbg, Long lbo, String ime, String prezime, String pol, Date datumRodjenja, String adresa, String kontaktTelefon, String krvnaGrupa, String hronicneDijagnoze, Lekar lekar) {
        this.jmbg = jmbg;
        this.lbo = lbo;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.kontaktTelefon = kontaktTelefon;
        this.krvnaGrupa = krvnaGrupa;
        this.hronicneDijagnoze = hronicneDijagnoze;
        this.lekar = lekar;
    }

    public KartonPacijenta(Long jmbg) {
        this.jmbg = jmbg;
    }

    public Long getJmbg() {
        return jmbg;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public Long getLbo() {
        return lbo;
    }

    public void setLbo(Long lbo) {
        this.lbo = lbo;
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

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(String krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }

    public String getHronicneDijagnoze() {
        return hronicneDijagnoze;
    }

    public void setHronicneDijagnoze(String hronicneDijagnoze) {
        this.hronicneDijagnoze = hronicneDijagnoze;
    }

    public List<Uput> getUputi() {
        return uputi;
    }

    public void setUputi(List<Uput> uputi) {
        this.uputi = uputi;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    @Override
    public String getAtrValue() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return jmbg.toString() + ", " + lbo.toString() + ", '" + ime + "', '" + prezime + "', '" + formatter.format(datumRodjenja) + "', '" + pol.toString()
                + "', '" + adresa + "', '" + kontaktTelefon + "', '" + krvnaGrupa.toString() + "', '" + hronicneDijagnoze + "', '" + lekar.getUsername() + "'";
    }

    @Override
    public String setAtrValue() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return "jmbg= " + jmbg + ", lbo=" + lbo + ", ime='" + ime
                + "', prezime='" + prezime + "', pol='" + pol.toString()
                + "', datumrodjenja='" + formatter.format(datumRodjenja)
                + "', adresa='" + adresa + "', kontakt_telefon='"
                + kontaktTelefon + "', krvna_grupa='" + krvnaGrupa.toString()
                + "', hronicne_dijagnoze='" + hronicneDijagnoze
                + "', lekar='" + lekar.getUsername() + "'";
    }

    @Override
    public String getClassName() {
        return "KartonPacijenta";
    }

    @Override
    public String getWhereCondition() {
        return "jmbg = " + jmbg;
    }

    @Override
    public String getNameByColumn(int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new KartonPacijenta(
                rs.getLong("jmbg"), rs.getLong("lbo"), rs.getString("ime"),
                rs.getString("prezime"), rs.getString("pol"),
                rs.getDate("datumrodjenja"), rs.getString("adresa"),
                rs.getString("kontakt_telefon"), rs.getString("krvna_grupa"),
                rs.getString("hronicne_dijagnoze"), new Lekar(rs.getString("lekar"))
        );
    }

    @Override
    public String toString() {
        return "jmbg= " + jmbg + ", lbo=" + lbo + ", ime='" + ime
                + "', prezime='" + prezime + "', pol='" + ((pol==null)?"":pol.toString())
                + "', datumrodjenja='" + ((datumRodjenja==null)?"":datumRodjenja.toString())
                + "', adresa='" + adresa + "', kontakt_telefon='"
                + kontaktTelefon + "', krvna_grupa='" + krvnaGrupa.toString()
                + "', hronicne_dijagnoze'" + hronicneDijagnoze + "', lekar=" + lekar.getIme() + " " + lekar.getPrezime();
    }

    @Override
    public String getWhereCondition1() {
        return "jmbg = " + jmbg + " AND lekar='" + lekar.getUsername() + "'";
    }

}
