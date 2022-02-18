/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import domain.GeneralDObject;
import domain.Laborant;
import domain.Lekar;
import domain.Rezultat;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TableModelKorisnici extends AbstractTableModel {

    List<GeneralDObject> korisnici = new ArrayList<>();
    String[] kolone = {"Korisnicko ime", "Ime i prezime", "Tip korisnika"};

    @Override
    public int getRowCount() {
        return korisnici.size();

    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GeneralDObject o = korisnici.get(rowIndex);
        if (o.getClassName().equals("Lekar")) {
            Lekar l = (Lekar) o;
            switch (columnIndex) {
                case 0:
                    return l.getUsername();
                case 1:
                    return l.getIme() + " " + l.getPrezime();
                case 2:
                    return l.getClassName();
                default:
                    return "N/A";
            }
        }
        else {
            Laborant lb = (Laborant) o;
            switch (columnIndex) {
                case 0:
                    return lb.getUsername();
                case 1:
                    return lb.getIme() + " " + lb.getPrezime();
                case 2:
                    return lb.getClassName();
                default:
                    return "N/A";
            }
        }
    }

    public void setKorisnici(List<GeneralDObject> korisnici) {
        this.korisnici = korisnici;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
}
