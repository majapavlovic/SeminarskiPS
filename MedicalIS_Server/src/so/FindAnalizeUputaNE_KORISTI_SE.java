/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maja
 */
public class FindAnalizeUputaNE_KORISTI_SE extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    Object result;
    List<GeneralDObject> lista;

    public FindAnalizeUputaNE_KORISTI_SE() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();

    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Uput)) {
            throw new Exception("Niste uneli odgovarajuci parametar");
        }
    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {
        Uput u = (Uput) param;
        Analiza a = new Analiza();
        List<Analiza> analize = new ArrayList<>();
        a.setUput(u);
        List<GeneralDObject> l = bbp.findAllRecords(a);
        System.out.println("Lista analiza:");
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                a = (Analiza) odo;
                analize.add(a);
                System.out.println(a);
            }
        }
        // result = analize;
        lista = l;
        result1 = analize;
    }

    @Override
    protected void comitTransaction() throws Exception {
        bbp.commitTransation();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        bbp.rollbackTransation();
    }

}
