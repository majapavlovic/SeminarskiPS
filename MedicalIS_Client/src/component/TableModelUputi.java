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
        for(Rezultat rez : rezultati) {
            System.out.println(rez);
            System.out.println(rez.getSifraRezultata());
          
            if(rez.getUput().getSifraUputa().equals(u.getSifraUputa())) {
            rezultat = rez;
            break;
            }          
        }
        switch(columnIndex) {
            case 0:
                return u.getSifraUputa();
            case 1:
                return u.getUputnaDijagnoza();
            case 2:
                return u.getDatumUputa();
            case 3:
                if(rezultat.getSifraRezultata()!=null) {
                    return rezultat.getRezultat_analize();
                }else return "Rezultat nije spreman";
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setUputi(List<Uput> uputi) {
        this.uputi = uputi;
        
        fireTableDataChanged();
    }

    public void setRezultati(List<Rezultat> rezultati) {
        this.rezultati = rezultati;
        
        fireTableDataChanged();
    }

    public String getUputRezultat(int row) {
        Uput u = uputi.get(row);
        Rezultat rezultat = new Rezultat();
        for(Rezultat rez : rezultati) {
            if(rez.getUput().getSifraUputa().equals(u.getSifraUputa())) {
            rezultat = rez;
            break;
            }          
        }
        String rezultatIspis;
        if(rezultat.getSifraRezultata()!=null) {
            rezultatIspis = "Sifra: " + rezultat.getSifraRezultata() + "        REZULTAT: " + rezultat.getRezultat_analize() + "        Datum: " + rezultat.getDatumIzdavanja();
        }
        else rezultatIspis = "Rezultat jos uvek nije spreman.";
        return "Sifra: " + u.getSifraUputa() + "           UPUT : " + u.getUputnaDijagnoza()  + "     Datum: " + u.getDatumUputa() 
                + "\nVrsta uzorka: " + u.getVrstaUzorka() + ",  Analiza: " + u.getAnaliza() +
                "\n-------------------------------------------------------------------\n"
                + rezultatIspis;
    }

   
   
    
}
