/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import communication.Response;
import communication.ResponseType;
import controller.ClientController;
import domain.Laborant;
import domain.Lekar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.Client;

/**
 *
 * @author Maja
 */
public class FrmLogin extends javax.swing.JFrame {

    Lekar lekar;
    Laborant laborant;

    /**
     * Creates new form FrmLogin
     */
    public FrmLogin() {
        initComponents();
        ClientController.getInstance().setFrmLogin(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLekarLogin = new javax.swing.JButton();
        btnLaborantLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Korisnicko ime:");

        jLabel2.setText("Sifra:");

        txtUsername.setText("majpav");

        txtPassword.setText("12345");

        btnLekarLogin.setText("Uloguj se kao lekar");
        btnLekarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLekarLoginActionPerformed(evt);
            }
        });

        btnLaborantLogin.setText("Uloguj se kao laborant");
        btnLaborantLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaborantLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLaborantLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLekarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLekarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLaborantLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaborantLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaborantLoginActionPerformed
         try {
            validateForm();
            Laborant l = new Laborant();
            l.setUsername(txtUsername.getText());
            l.setPassword(String.valueOf(txtPassword.getPassword()));
            ClientController.getInstance().loginLaborant(l);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnLaborantLoginActionPerformed

    private void btnLekarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLekarLoginActionPerformed
        try {
            validateForm();
            Lekar l = new Lekar();
            l.setUsername(txtUsername.getText());
            l.setPassword(String.valueOf(txtPassword.getPassword()));
            ClientController.getInstance().loginLekar(l);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnLekarLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaborantLogin;
    private javax.swing.JButton btnLekarLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void validateForm() throws Exception {
        String error = "";
        if (txtUsername.getText().trim().isEmpty()) {
            error += "Korisnicko ime ne moze biti prazno!\n";
        }
        if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            error += "Sifra ne moze biti prazna!\n";
        }
        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    public void login(Response response) {
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            lekar = (Lekar) response.getResponse();
            JOptionPane.showMessageDialog(this, "Uspesno logovanje", "Zdravo, " + lekar.getIme(), JOptionPane.INFORMATION_MESSAGE);
            new FrmMain_Lekar(lekar).setVisible(true);
            this.dispose();
        } else
        {
            JOptionPane.showMessageDialog(this, response.getException(), "Greska u logovanju", JOptionPane.ERROR_MESSAGE);
        }

    }
     public void loginLab(Response response) {
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            laborant =  (Laborant) response.getResponse();
            JOptionPane.showMessageDialog(this, "Uspesno logovanje", "Zdravo, " + laborant.getIme(), JOptionPane.INFORMATION_MESSAGE);
            new FrmMain_Laborant(laborant).setVisible(true);
            this.dispose();
        } else
        {
            JOptionPane.showMessageDialog(this, response.getException(), "Greska u logovanju", JOptionPane.ERROR_MESSAGE);

        }

    }
}
