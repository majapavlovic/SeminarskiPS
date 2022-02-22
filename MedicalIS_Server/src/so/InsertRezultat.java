/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka;
import dbbroker.BrokerBazePodataka_impl;
import domain.GeneralDObject;
import domain.Rezultat;

/**
 *
 * @author Maja
 */
public class InsertRezultat extends AbstractSO {

    BrokerBazePodataka_impl bbp;

    public InsertRezultat() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Rezultat)) {
            throw new Exception("Niste uneli odgovarajuci parametar!");
        }
        Rezultat r = (Rezultat) param;
        if (r.getRezultat_analize().isEmpty()) {
            throw new Exception("Morate upisati vrednost rezultata!");
        }
    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {
        Rezultat rez = (Rezultat) param;

        Long max = (Long) bbp.findMaxRecord(rez);
        rez.setSifra_rezultata(++max);

        if (!bbp.insertRecord(rez)) {
            throw new Exception("Neuspesno cuvanje rezultata!");
        }
    }

    @Override
    protected void comitTransaction() throws Exception {
        bbp.commitTransation();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        bbp.rollbackTransation();
    }

    public boolean isResultB() {
        return resultB;
    }

}
