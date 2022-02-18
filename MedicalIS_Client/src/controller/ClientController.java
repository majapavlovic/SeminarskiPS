/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import domain.Analiza;
import domain.KartonPacijenta;
import domain.Laborant;
import domain.Lekar;
import domain.Rezultat;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SpringLayout;
import thread.ClientThread;
import view.FrmKreiranjeUputa;
import view.FrmLogin;
import view.FrmMain_Laborant;
import view.FrmMain_Lekar;

/**
 *
 * @author Maja
 */
public class ClientController {

    private static ClientController instance;

    private ClientThread clientThread;
    private List<KartonPacijenta> pacijenti;

    FrmLogin frmLogin;
    FrmMain_Lekar frmLekar;
    FrmMain_Laborant frmLaborant;
    FrmKreiranjeUputa frmUput;

    private ClientController() {
        pacijenti = new ArrayList<>();
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public void loginLekar(Lekar l) throws Exception {
        Request request = new Request(Operations.LOGIN_LEKAR, l);
        clientThread = new ClientThread();
        clientThread.start();
        
        new Sender(clientThread.getSocket()).send(request);
    }

    public void setFrmLogin(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }

    public void setFrmLekar(FrmMain_Lekar frmLekar) {
        this.frmLekar = frmLekar;
    }

    public void setFrmLaborant(FrmMain_Laborant frmLaborant) {
        this.frmLaborant = frmLaborant;
    }

    public void setFrmUput(FrmKreiranjeUputa frmUput) {
        this.frmUput = frmUput;
    }

    public void loginLekarResponse(Response response) throws Exception {
        frmLogin.login(response);

    }

    public void getKartonPacijenta(Long jmbg, String lekar) throws Exception {
        Lekar l = new Lekar(lekar);
        KartonPacijenta kp = new KartonPacijenta(jmbg);
        kp.setLekar(l);
        Request request = new Request(Operations.GET_PACIJENT, kp);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void showKartonPacijenta(Response response) {
        KartonPacijenta k = (KartonPacijenta) response.getResponse();
        System.out.println(k.getIme());
        frmLekar.showKartonPacijenta(response);
    }

    public void getRezultat(List<Analiza> a) throws Exception {
        Request request = new Request(Operations.GET_REZULTATI, a);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void showRezultati(Response response) {
        frmLekar.showRezultati(response);
    }

    public void insertNoviKarton(KartonPacijenta k) throws Exception {
        Request request = new Request(Operations.INSERT_PACIJENT, k);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void notifyInsertPacijent(Response response) {
        String message;
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            message = "Uspesno unet karton novog pacijenta.";
        } else {
            message = response.getException().getMessage();
        }
        frmLekar.notifyUser(message);
    }

    public void shutdown() {
        if(frmLekar!=null) frmLekar.dispose();
        if(frmLaborant!=null) frmLaborant.dispose();
        clientThread.interrupt();
        System.exit(1);
    }

    public void insertUput(Uput u) throws Exception {
        Request request = new Request(Operations.INSERT_UPUT, u);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void notifyInsertUput(Response response) {
        String message;
        Uput u = (Uput) response.getResponse();
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            message = "Uspesno unet uput. Sifra uputa: " + u.getSifraUputa();
        } else {
            message = response.getException().getMessage();
        }
        frmUput.notifyInsert(message, u);
    }

    public void getUputi() throws Exception {
        Request request = new Request(Operations.GET_ALL_UPUT, null);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void getRezultati() throws Exception {
        Request request = new Request(Operations.GET_ALL_REZULTAT, null);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void setUputi(Response response) {
        System.out.println(response);
        System.out.println(response.getResponse());
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            frmLaborant.setUputi(response);
        }
    }

    public void setRezultati(Response response) {
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            frmLaborant.setRezultati(response);
        }
    }

    public void loginLaborant(Laborant l) throws Exception {
        Request request = new Request(Operations.LOGIN_LAB, l);
        clientThread = new ClientThread();
        clientThread.start();

        new Sender(clientThread.getSocket()).send(request);
    }

    public void loginLaborantResponse(Response response) {
        frmLogin.loginLab(response);
    }

    public void insertRezultat(Rezultat rez) throws Exception {
        Request request = new Request(Operations.INSERT_REZULTAT, rez);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void notifyInsertRezultat(Response response) {
        String message;
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            message = "Uspesno unet rezultat.";
        } else {
            message = response.getException().getMessage();
        }
        frmLaborant.notifyInsert(message);
    }

    public void updatePacijent(KartonPacijenta k) throws Exception {
        Request request = new Request(Operations.UPDATE_PACIJENT, k);
        new Sender(clientThread.getSocket()).send(request);

    }

    public void notifyUpdatePacijent(Response response) {
        String message;
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            message = "Uspesno azuriran karton pacijenta.";
        } else {
            message = response.getException().getMessage();
        }
        frmLekar.notifyUser(message);
    }

    public void notifyOthers() throws Exception {
        Request request = new Request(Operations.REFRESH, null); 
        new Sender(clientThread.getSocket()).send(request);
        
    }

    public void refresh() {
        if(frmLaborant!=null) frmLaborant.refresh();
        if(frmLekar!=null) frmLekar.refresh();
    }

    public void getAnalize() throws Exception {
        Request request = new Request(Operations.GET_ALL_ANALIZA, null);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void showAnalize(Response response) {
        frmLaborant.showAnalize(response);
    }


}
