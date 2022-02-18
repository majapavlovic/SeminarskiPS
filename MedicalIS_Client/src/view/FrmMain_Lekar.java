/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import communication.Response;
import communication.ResponseType;
import component.TableModelAnalize;
import component.TableModelUputi;
import controller.ClientController;
import domain.Analiza;
import domain.KartonPacijenta;
import domain.KrvnaGrupa;
import domain.Lekar;
import domain.Rezultat;
import domain.Uput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Maja
 */
public class FrmMain_Lekar extends javax.swing.JFrame {

    private Lekar lekar;
    KartonPacijenta trenutni_pacijent;
    TableModelUputi tblModelUputi = new TableModelUputi();
    TableModelAnalize tblModelAnalize = new TableModelAnalize();
    List<Uput> uputi;
    List<Analiza> analize;
    List<Rezultat> rezultati;

    /**
     * Creates new form FrmMain_Lekar
     */
    public FrmMain_Lekar(Lekar lekar) {
        initComponents();
        this.lekar = lekar;
        prepareForm();
        ClientController.getInstance().setFrmLekar(this);
        uputi = new ArrayList<>();
        analize = new ArrayList<>();
        rezultati = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTrenutniLekar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtJMBGnadji = new javax.swing.JTextField();
        btnNadji = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtJMBG = new javax.swing.JTextField();
        txtLBO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAdresa = new javax.swing.JTextField();
        txtKontakt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHronicneDijagnoze = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        btnNoviUput = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblUputi = new javax.swing.JTable();
        cbPol = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        btnNoviKarton = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        datumRodjenja = new org.jdatepicker.JDatePicker();
        cbKrvnaGrupa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtLekar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnalize = new javax.swing.JTable();
        btnOsvezi = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MedicalIS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 14), new java.awt.Color(51, 153, 255))); // NOI18N

        lblTrenutniLekar.setText("Ime lekara");

        jLabel1.setText("Pretrazi karton pacijenta");

        txtJMBGnadji.setText("1111111111111");

        btnNadji.setText("Nadji");
        btnNadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNadjiActionPerformed(evt);
            }
        });

        jLabel2.setText("JMBG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Podaci o pacijentu"));

        jLabel6.setText("JMBG");

        jLabel7.setText("LBO");

        jLabel8.setText("Ime");

        jLabel9.setText("Datum rodjenja");

        jLabel10.setText("Adresa");

        jLabel11.setText("Kontakt telefon");

        jLabel12.setText("Krvna grupa");

        jLabel13.setText("Hronicne dijagnoze");

        txtHronicneDijagnoze.setColumns(20);
        txtHronicneDijagnoze.setRows(5);
        jScrollPane2.setViewportView(txtHronicneDijagnoze);

        jLabel14.setText("Lista uputa");

        btnNoviUput.setText("Kreiraj novi uput");
        btnNoviUput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoviUputActionPerformed(evt);
            }
        });

        tblUputi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblUputi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUputi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUputiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblUputi);

        cbPol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Pol");

        jLabel16.setText("Prezime");

        btnNoviKarton.setText("Kreiraj karton novog pacijenta");
        btnNoviKarton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoviKartonActionPerformed(evt);
            }
        });

        btnUpdate.setText("Zameni");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        cbKrvnaGrupa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Izabrani lekar");

        jLabel3.setText("Analize i rezultati");

        tblAnalize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAnalize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnalizeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAnalize);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel8)
                                .addGap(130, 130, 130)
                                .addComponent(jLabel16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel6)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrezime))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLBO, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(datumRodjenja, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbKrvnaGrupa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(59, 59, 59)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel10)
                                .addGap(95, 95, 95)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtLekar)
                            .addComponent(cbPol, 0, 148, Short.MAX_VALUE))))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNoviKarton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(btnNoviUput, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLBO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel16)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLekar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(datumRodjenja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbKrvnaGrupa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNoviKarton)
                    .addComponent(btnUpdate)
                    .addComponent(btnNoviUput))
                .addContainerGap())
        );

        btnOsvezi.setText("Osveži");
        btnOsvezi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsveziActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtJMBGnadji, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(btnNadji, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTrenutniLekar)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnOsvezi, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTrenutniLekar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtJMBGnadji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnNadji, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOsvezi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNoviUputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoviUputActionPerformed
        Long jmbg;
        try {
            jmbg = Long.valueOf(txtJMBG.getText());
            new FrmKreiranjeUputa(this, true, trenutni_pacijent, lekar).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Morate prvo odabrati pacijenta za kojeg kreirate uput");
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnNoviUputActionPerformed

    private void btnNadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNadjiActionPerformed
        nadji();
    }//GEN-LAST:event_btnNadjiActionPerformed

    private void tblUputiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUputiMouseClicked

        int row = tblUputi.rowAtPoint(evt.getPoint());
        
        Uput u = tblModelUputi.getUput(row);
        analize = u.getAnalize();
        
        try {
            ClientController.getInstance().getRezultat(analize);
        } catch (Exception ex) {
            Logger.getLogger(FrmMain_Lekar.class.getName()).log(Level.SEVERE, null, ex);

        }

        //JOptionPane.showMessageDialog(this, tblModelUputi.getUput(row));
        //tblModelAnalize.setAnalize(tblModelUputi.getUput(row).getAnalize());

    }//GEN-LAST:event_tblUputiMouseClicked

    private void btnNoviKartonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoviKartonActionPerformed
        try {

            validateForm();
            KartonPacijenta k = getPacijentData();
            ClientController.getInstance().insertNoviKarton(k);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ovde je greska " + ex.getMessage());
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnNoviKartonActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            validateForm();
            KartonPacijenta k = getPacijentData();
            ClientController.getInstance().updatePacijent(k);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnOsveziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsveziActionPerformed

        if (rezultati != null) {
            rezultati.removeAll(rezultati);
        }
        if (uputi != null) {
            uputi.removeAll(uputi);
        }

        tblModelUputi.setUputi(uputi);

        txtJMBG.setText("");
        txtHronicneDijagnoze.setText("");
        txtAdresa.setText("");
        txtLBO.setText("");
        txtIme.setText("");
        txtPrezime.setText("");
        cbKrvnaGrupa.setSelectedItem("A+");
        txtKontakt.setText("");
        cbPol.setSelectedItem("Muski");
        txtLekar.setText("");
    }//GEN-LAST:event_btnOsveziActionPerformed

    private void tblAnalizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAnalizeMouseClicked
      //   tblModelAnalize.get
    }//GEN-LAST:event_tblAnalizeMouseClicked

    private void prepareForm() {
        List<String> pol = new ArrayList<>();
        pol.add("Muski");
        pol.add("Zenski");
        pol.add("Nije odredjeno");

        cbPol.setModel(new DefaultComboBoxModel(pol.toArray()));

        tblUputi.setModel(tblModelUputi);
        tblAnalize.setModel(tblModelAnalize);

        KrvnaGrupa kg = new KrvnaGrupa();

        cbKrvnaGrupa.setModel(new DefaultComboBoxModel(kg.getKrvneGrupe().toArray()));

        lblTrenutniLekar.setText(lekar.getIme() + " " + lekar.getPrezime() + " (" + lekar.getUsername() + ")");

    }

    public void showKartonPacijenta(Response response) {
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            KartonPacijenta pacijent = (KartonPacijenta) response.getResponse();
            trenutni_pacijent = pacijent;
            txtJMBG.setText(pacijent.getJmbg().toString());
            if (pacijent.getLbo() != 0l) {
                txtLBO.setText(pacijent.getLbo().toString());
            }
            txtAdresa.setText(pacijent.getAdresa());
            txtIme.setText(pacijent.getIme());
            txtPrezime.setText(pacijent.getPrezime());
            cbKrvnaGrupa.setSelectedItem(pacijent.getKrvnaGrupa());
            txtKontakt.setText(pacijent.getKontaktTelefon());
            cbPol.setSelectedItem(pacijent.getPol().toString());
            txtHronicneDijagnoze.setText(pacijent.getHronicneDijagnoze());

            uputi = pacijent.getUputi();

            tblModelUputi.setUputi(uputi);

            txtLekar.setText(pacijent.getLekar().getIme() + " " + pacijent.getLekar().getPrezime());

            Date datumR = pacijent.getDatumRodjenja();
            System.out.println(datumR.toString());
            String datum[] = datumR.toString().split("-");
            int godina = Integer.valueOf(datum[0]);
            int mesec = Integer.valueOf(datum[1]) - 1;
            int dan = Integer.valueOf(datum[2]);
            datumRodjenja.getModel().setDate(godina, mesec, dan);
            datumRodjenja.getModel().setSelected(true);

        }
    }

    public void showRezultati(Response response) {
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            rezultati =  (List<Rezultat>) response.getResponse();
          

            System.out.println("Rezultati: ");
            for(Rezultat r : rezultati) {
                System.out.println(r);
            }
            
            
            tblModelAnalize.setAnalizeRezultati(analize, rezultati);
        }

    }

    private void validateForm() throws Exception {
        String error = "";
        if (txtJMBG.getText().isEmpty()) {
            error += "Niste upisali JMBG!\n";
        } else {
            try {
                Long jmbg = Long.valueOf(txtJMBG.getText());
            } catch (Exception e) {
                error += "Neispravan format JMBG-a!\n";
            }
        }
        if (!txtLBO.getText().equals("")) {
            try {
                Long lbo = Long.valueOf(txtLBO.getText());
            } catch (Exception e) {
                error += "Neispravan format LBO-a!\n";
            }
        }
        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    private KartonPacijenta getPacijentData() throws Exception {
        KartonPacijenta k = new KartonPacijenta();
        k.setJmbg(Long.valueOf(txtJMBG.getText()));
        if (!txtLBO.getText().equals("")) {
            k.setLbo(Long.valueOf(txtLBO.getText()));
        } else {
            k.setLbo(0l);
        }
        k.setAdresa(txtAdresa.getText());
        k.setIme(txtIme.getText());
        k.setPrezime(txtPrezime.getText());
        k.setKrvnaGrupa(cbKrvnaGrupa.getSelectedItem().toString());
        k.setKontaktTelefon(txtKontakt.getText());
        k.setPol(cbPol.getSelectedItem().toString());
        k.setHronicneDijagnoze(txtHronicneDijagnoze.getText());
        k.setLekar(lekar);

        int dan = datumRodjenja.getModel().getDay();
        int mesec = datumRodjenja.getModel().getMonth();
        int god = datumRodjenja.getModel().getYear();

        Date date = new Date(god - 1900, mesec, dan);

        k.setDatumRodjenja(date);

        return k;

    }

    public void notifyUser(String message) {
        JOptionPane.showMessageDialog(this, message);
        try {
            ClientController.getInstance().notifyOthers();
        } catch (Exception ex) {
            Logger.getLogger(FrmMain_Lekar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refresh() {
        // nadji();
    }

    private void nadji() {
        String strJmbg = txtJMBGnadji.getText();

        if (!strJmbg.isEmpty()) {
            try {

                ClientController.getInstance().getKartonPacijenta(Long.valueOf(strJmbg), lekar.getUsername());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Neodgovarajuca vrednost JMBG-a!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Morate uneti kriterijum za pretragu!");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNadji;
    private javax.swing.JButton btnNoviKarton;
    private javax.swing.JButton btnNoviUput;
    private javax.swing.JButton btnOsvezi;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbKrvnaGrupa;
    private javax.swing.JComboBox<String> cbPol;
    private org.jdatepicker.JDatePicker datumRodjenja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTrenutniLekar;
    private javax.swing.JTable tblAnalize;
    private javax.swing.JTable tblUputi;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextArea txtHronicneDijagnoze;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtJMBG;
    private javax.swing.JTextField txtJMBGnadji;
    private javax.swing.JTextField txtKontakt;
    private javax.swing.JTextField txtLBO;
    private javax.swing.JTextField txtLekar;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables

}
