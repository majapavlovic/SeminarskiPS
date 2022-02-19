/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.Uput;
import java.util.List;

/**
 *
 * @author Maja
 */
public class InsertUput extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    Object result;
    boolean resultB = false;

    public InsertUput() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Uput)) {
            throw new Exception("Niste uneli odgovarajuci parametar!");
        }
        Uput u = (Uput) param;
        if (u.getUputnaDijagnoza().isEmpty()) {
            throw new Exception("Morate upisati uputnu dijagnozu!");
        }
        if (u.getAnalize() == null || u.getAnalize().isEmpty()) {
            throw new Exception("Morate uneti barem jednu analizu u uput!");
        }

    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {

        Uput u = (Uput) param;

        Long max = (Long) bbp.findMaxRecord(u);
        u.setSifraUputa(++max);

        List<Analiza> lista = setSifreAnaliza(u, u.getAnalize());

        if (!bbp.insertRecord(u) || !saveAnalize(lista)) {
            System.out.println("Cuva li se");
            
            throw new Exception("Neuspesno cuvanje uputa!");
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
            System.out.println("Analize u save: " + a);
            b = bbp.insertRecord(a);
        }
        return b;
    }

}
