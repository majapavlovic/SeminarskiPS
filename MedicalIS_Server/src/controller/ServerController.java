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
import threads.ServerThread;

/**
 *
 * @author Maja
 */
public class ServerController {

    private ServerThread serverThread;
    private static ServerController instance;
    BrokerBazePodataka bbp;

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

    public KartonPacijenta sendKartonPacijenta(Request request) {
        KartonPacijenta p = new KartonPacijenta();
        p.setJmbg((Long) request.getArgument());
        p = (KartonPacijenta) bbp.findRecord(p);
        Uput u = new Uput();
        u.setPacijent(p);
        List<GeneralDObject> l = bbp.findAllRecords(u);
        List<Uput> uputi = new ArrayList<>();
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                System.out.println(odo);
                u = (Uput) odo;
                System.out.println(u.getSifraUputa());
                uputi.add((Uput) odo);
            }
            p.setUputi(uputi);
        }
        return p;
    }

    public Rezultat getRezultat(Request request) {
        Uput u = (Uput) request.getArgument();

        Rezultat r = new Rezultat();
        r.setBrojProtokola(u.getSifraUputa());
        return (Rezultat) bbp.findRecord(r);

    }

    public Response insertKartonPacijetna(Request request) {
        KartonPacijenta k = (KartonPacijenta) request.getArgument();
        System.out.println(k.toString());
        Response response = new Response();
        response.setOperation(Operations.INSERT_PACIJENT);

        boolean b = bbp.insertRecord(k);
        if (b) {
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
        u.setSifraUputa(max + 1);
        System.out.println(u);
        if (bbp.insertRecord(u)) {
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

    public Response sendAllUputi() {
        Response response = new Response();
        response.setOperation(Operations.GET_ALL_UPUT);
        Uput u = new Uput();
        List<GeneralDObject> odoUputi = bbp.findAllRecords_NoCondition(u);
        List<Uput> uputi = new ArrayList<>();
        for (GeneralDObject odo : odoUputi) {
            uputi.add((Uput) odo);
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
            rezultati.add((Rezultat) odo);
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

}
