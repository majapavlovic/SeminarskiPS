/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import domain.Analiza;
import domain.Rezultat;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maja
 */
public class TableModelAnalize extends DefaultTableModel {

    List<Uput> uputi = new ArrayList<>();
    List<Analiza> analize = new ArrayList<>();
    List<Rezultat> rezultati = new ArrayList<>();
    String[] kolone = {"Sifra", "Vrsta uzorka", "Analiza", "Rezultat"};
    String[] koloneLab = {"Datum", "Sifra analize", "Rezultat"};
    boolean lab = false;

    public TableModelAnalize(boolean lab) {
        this.lab=lab;
    }
    
    @Override
    public int getRowCount() {
        if (analize != null) {
            return analize.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Analiza a = analize.get(rowIndex);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez != null && rez.getAnaliza().getSifraAnalize().equals(a.getSifraAnalize())) {
                rezultat = rez;
                break;
            }
        }
        if (!lab) {
            {
                switch (columnIndex) {
                    case 0:
                        return a.getSifraAnalize();
                    case 1:
                        return a.getVrstaUzorka();
                    case 2:
                        return a.getVrstaAnalize();
                    case 3:
                        if (rezultat.getSifra_rezultata() != null) {
                            return rezultat.getRezultat_analize();
                        } else 
                            return "Rezultat nije spreman";                     
                    default:
                        return "N/A";
                }
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return a.getSifraAnalize();
                case 1:
                    return a.getVrstaUzorka();
                case 2:
                    return a.getVrstaAnalize();
                case 3:
                    if (rezultat.getSifra_rezultata() != null) {
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
    public String getColumnName(int column
    ) {
        return kolone[column];

    }

    public void setAnalize(List<Analiza> analize) {
        this.analize = analize;
        fireTableDataChanged();
    }

    public void setRezultati(List<Rezultat> rezultati) {
        this.rezultati = rezultati;
        fireTableDataChanged();
    }

    public List<Analiza> getAnalize() {
        return analize;

    }

    public List<Rezultat> getRezultati() {
        return rezultati;
    }

    public String getUputRezultat(int row) {
        Analiza a = analize.get(row);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez.getSifra_rezultata().equals(a.getSifraAnalize())) {
                rezultat = rez;
                break;
            }
        }
        String rezultatIspis;
        if (rezultat.getSifra_rezultata() != null) {
            rezultatIspis = "Sifra: " + rezultat.getSifra_rezultata() + "            Datum: " + rezultat.getDatumIzdavanja()
                    + "\n\nRezultat:\n" + rezultat.getRezultat_analize();
        } else {
            rezultatIspis = "\nRezultat jos uvek nije spreman.";
        }
        return "Sifra analize: "
                + a.getSifraAnalize()
                + "\nVrsta uzorka: " + a.getVrstaUzorka() + ",         Analiza: " + a.getVrstaAnalize()
                + "\n-------------------------------------------------------------------\n"
                + rezultatIspis;
    }

    public Rezultat getRezultat(int row) {
        Analiza a = analize.get(row);
        Rezultat rezultat = new Rezultat();
        for (Rezultat rez : rezultati) {
            if (rez.getSifra_rezultata().equals(a.getSifraAnalize())) {
                rezultat = rez;
                break;
            }
        }
        return rezultat;
    }

    public void setAnalizeRezultati(List<Analiza> analize, List<Rezultat> rezultati) {
        this.analize = analize;
        this.rezultati = rezultati;

        fireTableDataChanged();
    }

}
