/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.Laborant;
import domain.Rezultat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maja
 */
public class FindRezultatAnalize extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    GeneralDObject result;

    public FindRezultatAnalize() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Analiza)) {
            throw new Exception("Niste uneli odgovarajuci parametar");
        }
    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {
        Analiza a = (Analiza) param;
        Rezultat r = new Rezultat();
        r.setAnaliza(a);
        r = (Rezultat) bbp.findRecord(r);

        if (r != null) {
            Laborant l = r.getLaborant();

            AbstractSO findLab = new FindLaborant();
            findLab.execute(l);
            Laborant lab = (Laborant)findLab.getResult();
            System.out.println("----------LABORANT: " + lab.getIme() + " " + lab.getPrezime() + " " + lab.getUsername());
            
            r.setLaborant(lab);
        }

        result = r;
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

}
