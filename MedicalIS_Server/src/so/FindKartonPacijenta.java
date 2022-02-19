/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.KartonPacijenta;
import domain.Lekar;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maja
 */
public class FindKartonPacijenta extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    GeneralDObject result;
    Object result1;

    public FindKartonPacijenta() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof KartonPacijenta)) {
            throw new Exception("Niste uneli odgovarajuci parametar");
        }
    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {

        KartonPacijenta k = (KartonPacijenta) param;
        k = (KartonPacijenta) bbp.findRecord(k);

        Lekar l = k.getLekar();
        AbstractSO findLekar = new FindLekar();
        findLekar.executeOperation(l);

        k.setLekar((Lekar) findLekar.getResult());
        //NASLI SMO LEKARA

        //SAD MORAMO NACI UPUTE ZA OVOG PACIJENTA
        /*   AbstractSO findUputi = new FindUputiPacijenta();
        findUputi.execute(k);
        List<GeneralDObject> odoL = findUputi.getLista();
        List<Uput> uputi = new ArrayList<>();
        for(GeneralDObject g : odoL) {
            uputi.add((Uput) g);
        }
        k.setUputi(uputi); */
        //KRAJ NOVIH PROMENA
        k.setUputi(findUputi(k));

        result = k;
    }

    @Override
    protected void comitTransaction() throws Exception {
        bbp.commitTransation();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        bbp.rollbackTransation();
    }

    public GeneralDObject getResult() {
        return result;
    }

    @Override
    public Object getResult1() {
        return super.getResult1(); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Uput> findUputi(KartonPacijenta k) {
        Uput u = new Uput();
        u.setPacijent(k);
        List<GeneralDObject> l = bbp.findAllRecords(u);
        List<Uput> uputi = new ArrayList<>();
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                List<Analiza> analize = new ArrayList<>();

                u = (Uput) odo;
                u.setLekar((Lekar) bbp.findRecord1(new Lekar(u.getLekar().getUsername())));

                u.setAnalize(findAnalize(u));

                uputi.add(u);
            }
        }
        return uputi;
    }

    private List<Analiza> findAnalize(Uput u) {
        Analiza a = new Analiza();
        a.setUput(u);
        List<Analiza> analize = new ArrayList<>();
        List<GeneralDObject> l = bbp.findAllRecords(a);
        for (GeneralDObject gdo : l) {
            analize.add((Analiza) gdo);
        }
        return analize;
    }
}
