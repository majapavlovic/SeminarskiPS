/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import dbbroker.BrokerBazePodataka;
import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.VrsteAnaliza;
import domain.GeneralDObject;
import domain.KartonPacijenta;
import domain.Laborant;
import domain.Lekar;
import domain.Rezultat;
import domain.Uput;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import so.AbstractSO;
import so.AddKartonPacijenta;
import so.FindAllUputi;
import so.FindKartonPacijenta;
import so.FindRezultatAnalize;
import so.InsertRezultat;
import so.InsertUput;
import so.LoginLaborant;
import so.LoginLekar;
import so.UpdateKartonPacijenta;
import threads.ClientHandle;
import threads.ServerThread;
import view.FrmMain;

/**
 *
 * @author Maja
 */
public class ServerController {

    private ServerThread serverThread;
    private static ServerController instance;
    BrokerBazePodataka bbp;
    FrmMain form;

    private ServerController() {
        bbp = new BrokerBazePodataka_impl();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void startServer() {
        if (serverThread == null || !serverThread.isAlive()) {
            try {
                serverThread = new ServerThread();
                serverThread.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public void stopServer() {
        try {
            serverThread.stopServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Lekar loginLekar(Lekar reqL) throws Exception {
        //bbp.makeConnection();
        //return (Lekar) bbp.findRecord(reqL);

        AbstractSO loginLekar = new LoginLekar();
        loginLekar.execute(reqL);
        return (Lekar) loginLekar.getResult();
    }

    public KartonPacijenta sendKartonPacijenta(Request request, Lekar lekar) {
        KartonPacijenta p = (KartonPacijenta) request.getArgument();
        p = (KartonPacijenta) bbp.findRecord(p);
        p.setLekar(lekar);
        Uput u = new Uput();
        u.setPacijent(p);
        List<GeneralDObject> l = bbp.findAllRecords(u);
        List<Uput> uputi = new ArrayList<>();
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                u = (Uput) odo;
                u.setLekar((Lekar) bbp.findRecord1(new Lekar(u.getLekar().getUsername())));
                u.setAnalize(getAllAnalize(u));
                uputi.add((Uput) odo);
            }
            p.setUputi(uputi);
        }
        return p;
    }

    public List<Analiza> getAllAnalize(Uput u) {
        Analiza a = new Analiza();
        List<Analiza> analize = new ArrayList<>();
        a.setUput(u);
        List<GeneralDObject> l = bbp.findAllRecords(a);
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                a = (Analiza) odo;
                analize.add((Analiza) odo);
            }
        }
        return analize;
    }

    public Response getAllAnalize() {
        Response response = new Response();
        response.setOperation(Operations.GET_ALL_ANALIZA);
        List<Analiza> analize = new ArrayList<>();
        List<GeneralDObject> l = bbp.findAllRecords(new Analiza());
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                analize.add((Analiza) odo);
            }
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(analize);
        } else {
            response.setException(new Exception("Nisu pronadjene analize"));
            response.setResponseType(ResponseType.ERROR);
        }
        return response;
    }

    public List<Rezultat> getListaRezultata(Request request) throws Exception {
        /* List<Analiza> analize = (List<Analiza>) request.getArgument();
        List<Rezultat> rezultati = new ArrayList<>();
        for (Analiza a : analize) {
            System.out.println("Analiza: " + a);
            rezultati.add(getRezultat(a));
        }
        return rezultati;*/

        List<Analiza> analize = (List<Analiza>) request.getArgument();
        List<Rezultat> rezultati = new ArrayList<>();
        AbstractSO findRez = new FindRezultatAnalize();
        for (Analiza a : analize) {
            findRez.execute(a);
            Rezultat r = (Rezultat) findRez.getResult();
            rezultati.add(r);
        }
        return rezultati;
    }

    public Rezultat getRezultat(Analiza a) {
        Rezultat r = new Rezultat();
        r.setAnaliza(a);
        Rezultat rez = (Rezultat) bbp.findRecord(r);
        System.out.println("Rezultat: " + rez);
        return rez;
    }

    public Response insertKartonPacijetna(Request request) {

        Response response = new Response();
        response.setOperation(Operations.INSERT_PACIJENT);
        KartonPacijenta k = (KartonPacijenta) request.getArgument();
        AbstractSO addPacijentSO = new AddKartonPacijenta();
        try {
            addPacijentSO.execute(k);

            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
            ex.printStackTrace();
        }
        return response;
    }

    public Response insertUput(Request request) {

        Response response = new Response();
        response.setOperation(Operations.INSERT_UPUT);

        AbstractSO insertUput = new InsertUput();
        Uput u = (Uput) request.getArgument();
        try {
            insertUput.execute(u);

            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(u);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            response.setException(new Exception("Neuspesan unos uputa."));
            response.setResponseType(ResponseType.ERROR);
        }
        return response;
    }

    public List<Uput> sendAllUputi() throws Exception {     //KORISTI SE

        AbstractSO findUputi = new FindAllUputi();
        findUputi.execute(new Uput());
        return (List<Uput>) findUputi.getResult1();
    }

    public Response sendAllRezultati() {
        Response response = new Response();
        response.setOperation(Operations.GET_ALL_REZULTAT);
        Rezultat r = new Rezultat();
        List<GeneralDObject> odoUputi = bbp.findAllRecords_NoCondition(r);
        List<Rezultat> rezultati = new ArrayList<>();
        for (GeneralDObject odo : odoUputi) {
            r = (Rezultat) odo;
            r.setLaborant((Laborant) bbp.findRecord1(r.getLaborant()));
            rezultati.add(r);
        }
        if (rezultati != null) {
            response.setResponse(rezultati);
            response.setResponseType(ResponseType.SUCCESS);
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Nema rezultata"));
        }
        return response;
    }

    public Laborant loginLaborant(Laborant lab) throws Exception {

        AbstractSO loginLab = new LoginLaborant();
        loginLab.execute(lab);
        return (Laborant) loginLab.getResult();

    }

    public void insertRezultat(Rezultat rez) throws Exception { //KORISTI SE

        AbstractSO insertRez = new InsertRezultat();
        insertRez.execute(rez);

    }

    public Response updatePacijent(Request request) { //NOVA

        Response response = new Response();
        response.setOperation(Operations.UPDATE_PACIJENT);
        KartonPacijenta p = (KartonPacijenta) request.getArgument();
        AbstractSO updatePacijent = new UpdateKartonPacijenta();
        try {
            updatePacijent.execute(p);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Neuspesno azuriranje kartona pacijenta\n" + ex.getMessage()));
        }
        return response;
    }

    public void sendRefreshToAll() {
        serverThread.sendRefreshToAll();
    }

    public void setForm(FrmMain frm) {
        form = frm;
    }

    public void showLoggedUsers(GeneralDObject odo) {
        form.showKorisnici(odo);
    }

    public KartonPacijenta findKartonPacijenta(Request request) throws Exception {      //NOVA !!
        KartonPacijenta k = (KartonPacijenta) request.getArgument();
        System.out.println(k.getJmbg().toString());
        AbstractSO findKP = new FindKartonPacijenta();
        findKP.execute(k);
        System.out.println("Get result = " + findKP.getResult());
        return (KartonPacijenta) findKP.getResult();
    }

    public void logoutLekar(Request request) {
        Lekar lekar = (Lekar) request.getArgument();
        form.logout(lekar);
        serverThread.logoutLekar(lekar);
    }

    public void logoutLab(Request request) {
        Laborant laborant = (Laborant) request.getArgument();
        form.logout(laborant);
        serverThread.logoutLaborant(laborant);
    }

}
