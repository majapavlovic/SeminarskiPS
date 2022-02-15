/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Request;
import communication.Response;
import dbbroker.BrokerBazePodataka;
import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.KartonPacijenta;
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
        if(l!=null && !l.isEmpty()) {
            for(GeneralDObject odo : l) {
                System.out.println(odo);
                u=(Uput)odo;
                System.out.println(u.getSifraUputa());
                uputi.add((Uput)odo);
            }
            p.setUputi(uputi);
        }
        return p;
    }

    public Rezultat getRezultat(Request request) {
        Uput u = (Uput) request.getArgument();
        
        Rezultat r = new Rezultat();
        r.setUput(u);
        
        return (Rezultat) bbp.findRecord(r);
    }



}
