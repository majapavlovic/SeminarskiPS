/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import domain.Analiza;
import domain.VrstaUzorka;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maja
 */
public class TableModelKreiranjeUputa extends AbstractTableModel {

    Object [][] rowData;
    String[] kolone = {"Vrsta uzorka", "Vrsta analize"};

    @Override
    public int getRowCount() {
        //if (analize != null) {
          //  return analize.size();
       // }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Ovde treba da budu uzorci";
            case 1:
                return "a ovde analize";
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];

    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        rowData[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
