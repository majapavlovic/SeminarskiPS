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
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TableModelUputi extends AbstractTableModel {

    List<Uput> uputi = new ArrayList<>();
    String[] kolone = {"Uput", "Uputna dijagnoza", "Datum", "Lekar"};

    @Override
    public int getRowCount() {
        if (uputi != null) {
            return uputi.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uput u = uputi.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return u.getSifraUputa();
            case 1:
                return u.getUputnaDijagnoza();
            case 2:
                return u.getDatumUputa();
            case 3:
                return u.getLekar().getIme() + " " + u.getLekar().getPrezime();
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

    public List<Uput> getUputi() {
        return uputi;
    }

    public String getUputString(int row) {
        Uput u = uputi.get(row);

        String rezultatIspis;

        return "Sifra: " + u.getSifraUputa() + "            Datum: " + u.getDatumUputa()
                + "\n\nUputna dijagnoza: " + u.getUputnaDijagnoza()
                + "\n\n                                               " + u.getLekar().getIme() + " " + u.getLekar().getPrezime();

    }
    public Uput getUput(int row) {
        return uputi.get(row);
    }

}
