/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import domain.Rezultat;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TableModelUputi extends AbstractTableModel {

    List<Uput> uputi = new ArrayList<>();
    List<Rezultat> rezultati = new ArrayList<>();
    String[] kolone = {"Uput", "Uputna dijagnoza", "Datum", "Rezultat"};
    String[] koloneLab = {"Datum", "Analiza", "Vrsta uzorka", "Rezultat"};
    boolean lab = false;

    public TableModelUputi(boolean lab) {
        this.lab = lab;
    }

    @Override
    public int getRowCount() {
        return uputi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uput u = uputi.get(rowIndex);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez.getBrojProtokola().equals(u.getSifraUputa())) {
                rezultat = rez;
                break;
            }
        }
        if (lab == false) {
            switch (columnIndex) {
                case 0:
                    return u.getSifraUputa();
                case 1:
                    return u.getUputnaDijagnoza();
                case 2:
                    return u.getDatumUputa();
                case 3:
                    if (rezultat.getBrojProtokola() != null) {
                        return rezultat.getRezultat_analize();
                    } else {
                        return "Rezultat nije spreman";
                    }
                default:
                    return "N/A";
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return u.getDatumUputa();
                case 1:
                    return u.getAnaliza();
                case 2:
                    return u.getVrstaUzorka();
                case 3:
                    if (rezultat.getBrojProtokola() != null) {
                        return rezultat.getRezultat_analize();
                    } else {
                        return "Rezultat nije spreman";
                    }
                default:
                    return "N/A";
            }
        }
    }

    @Override
    public String getColumnName(int column) {
       if(lab==false) return kolone[column];
       return koloneLab[column];
    }

    public void setUputi(List<Uput> uputi) {
        this.uputi = uputi;

        fireTableDataChanged();
    }

    public void setRezultati(List<Rezultat> rezultati) {
        this.rezultati = rezultati;

        fireTableDataChanged();
    }

    public List<Uput> getUputi() {
        return uputi;
    }

    public List<Rezultat> getRezultati() {
        return rezultati;
    }
    

    public String getUputRezultat(int row) {
        Uput u = uputi.get(row);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez.getBrojProtokola().equals(u.getSifraUputa())) {
                rezultat = rez;
                break;
            }
        }
        String rezultatIspis;
        if (rezultat.getBrojProtokola() != null) {
            rezultatIspis = "Sifra: " + rezultat.getBrojProtokola() + "        REZULTAT: " + rezultat.getRezultat_analize() + "        Datum: " + rezultat.getDatumIzdavanja();
        } else {
            rezultatIspis = "Rezultat jos uvek nije spreman.";
        }
        return "Sifra: " + u.getSifraUputa() + "           UPUT : " + u.getUputnaDijagnoza() + "     Datum: " + u.getDatumUputa()
                + "\nVrsta uzorka: " + u.getVrstaUzorka() + ",  Analiza: " + u.getAnaliza()
                + "\n-------------------------------------------------------------------\n"
                + rezultatIspis;
    }

    public Uput getUput(int row) {
        return uputi.get(row);
    }

    public Rezultat getRezultat(int row) {
        Uput u = uputi.get(row);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez.getBrojProtokola().equals(u.getSifraUputa())) {
                rezultat = rez;
                break;
            }
        }
        return rezultat;
    }

}
