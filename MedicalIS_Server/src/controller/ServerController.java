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

    public Lekar loginLekar(Lekar reqL) {
        bbp.makeConnection();
        return (Lekar) bbp.findRecord(reqL);
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

    public List<Rezultat> getListaRezultata(Request request) {
        List<Analiza> analize = (List<Analiza>) request.getArgument();
        List<Rezultat> rezultati = new ArrayList<>();
        for (Analiza a : analize) {
            System.out.println("Analiza: " + a);
            rezultati.add(getRezultat(a));
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
        KartonPacijenta k = (KartonPacijenta) request.getArgument();
        Response response = new Response();
        response.setOperation(Operations.INSERT_PACIJENT);

        if (bbp.insertRecord(k)) {
            bbp.commitTransation();
            response.setResponseType(ResponseType.SUCCESS);
        } else {
            bbp.rollbackTransation();
            response.setException(new Exception("Neuspesan upis novog pacijenta"));
            response.setResponseType(ResponseType.ERROR);

        }
        return response;
    }

    public Response insertUput(Request request) {
        Response response = new Response();
        response.setOperation(Operations.INSERT_UPUT);

        Uput u = (Uput) request.getArgument();

        Long max = (Long) bbp.findMaxRecord(u);
        List<Analiza> lista = setSifreAnaliza(u, u.getAnalize());

        u.setAnalize(lista);
        u.setSifraUputa(max + 1);
        if (bbp.insertRecord(u) && saveAnalize(lista)) {
            bbp.commitTransation();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(u);
        } else {
            bbp.rollbackTransation();
            response.setException(new Exception("Neuspesan unos uputa."));
            response.setResponseType(ResponseType.ERROR);
        }
        return response;
    }

    public List<Analiza> setSifreAnaliza(Uput uput, List<Analiza> analize) {
        Long maxAnaliza = (Long) bbp.findMaxRecord(new Analiza());
        for (Analiza a : analize) {
            a.setSifraAnalize(++maxAnaliza);
            a.setUput(uput);
        }
        return analize;
    }

    public boolean saveAnalize(List<Analiza> analize) {
        boolean b = false;
        for (Analiza a : analize) {
            b = bbp.insertRecord(a);
        }
        return b;
    }

    public Response sendAllUputi() {
        Response response = new Response();
        response.setOperation(Operations.GET_ALL_UPUT);
        Uput u = new Uput();
        List<GeneralDObject> odoUputi = bbp.findAllRecords_NoCondition(u);
        List<Uput> uputi = new ArrayList<>();
        for (GeneralDObject odo : odoUputi) {
            u = (Uput) odo;
            u.setLekar((Lekar) bbp.findRecord1(u.getLekar()));
            u.setAnalize(getAllAnalize(u));
            uputi.add(u);
        }
        if (uputi != null) {
            response.setResponse(uputi);
            response.setResponseType(ResponseType.SUCCESS);
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Nema uputa"));
        }
        return response;
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

    public Laborant loginLaborant(Laborant lab) {
        bbp.makeConnection();
        return (Laborant) bbp.findRecord(lab);
    }

    public Response insertRezultat(Request request) {
        Response response = new Response();
        response.setOperation(Operations.INSERT_REZULTAT);

        Rezultat rez = (Rezultat) request.getArgument();

        Long max = bbp.findMaxRecord(rez);
        rez.setSifra_rezultata(max+1);

        if (bbp.insertRecord(rez)) {
            response.setResponseType(ResponseType.SUCCESS);
            bbp.commitTransation();
        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Neuspesan unos rezultata"));
            bbp.rollbackTransation();
        }
        return response;

    }

    public Response updatePacijent(Request request) {
        KartonPacijenta p = (KartonPacijenta) request.getArgument();
        Response response = new Response();
        response.setOperation(Operations.UPDATE_PACIJENT);
        if (bbp.updateRecord(p)) {
            response.setResponseType(ResponseType.SUCCESS);
            bbp.commitTransation();

        } else {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception("Neuspesno azuriranje kartona pacijenta"));
            bbp.rollbackTransation();
        }
        return response;
    }

    public void sendRefreshToAll() {
        serverThread.sendRefreshToAll();
    }

    public void setForm(FrmMain frm) {
        form = frm;
    }

    public void showLoggedUsers(List<ClientHandle> clients) {
        List<GeneralDObject> korisnici = new ArrayList<>();
        for (ClientHandle client : clients) {
            if (client.getLekar() != null) {
                korisnici.add(client.getLekar());
            } else if (client.getLaborant() != null) {
                korisnici.add(client.getLaborant());
            }
        }
        form.showKorisnici(korisnici);
    }

}
