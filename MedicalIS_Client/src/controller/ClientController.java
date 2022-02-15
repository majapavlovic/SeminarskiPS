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
import domain.KartonPacijenta;
import domain.Lekar;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;
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
    
    private ClientController() {
        pacijenti = new ArrayList<>();
    }
    
    public static ClientController getInstance() {
        if(instance==null) instance=new ClientController();
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

    public void loginLekarResponse(Response response) throws Exception {
        frmLogin.login(response);
        
    }

    public void getKartonPacijenta(Long jmbg) throws Exception {
        Request request = new Request(Operations.GET_PACIJENT, jmbg);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void showKartonPacijenta(Response response) {
        KartonPacijenta k = (KartonPacijenta) response.getResponse();
        System.out.println(k.getIme());
        frmLekar.showKartonPacijenta(response);
    }

    public void getRezultat(Uput u) throws Exception {
        Request request = new Request(Operations.GET_REZULTAT,  u);
        new Sender(clientThread.getSocket()).send(request);
    }

    public void showRezultat(Response response) {
        frmLekar.showRezultat(response);
    }

    public void insertNoviKarton(KartonPacijenta k) throws Exception {
        Request request = new Request(Operations.INSERT_PACIJENT, k);
        new Sender(clientThread.getSocket()).send(request);
    }

    
    
}
