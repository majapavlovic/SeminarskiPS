/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import communication.ResponseType;
import dbbroker.BrokerBazePodataka_impl;
import domain.Analiza;
import domain.GeneralDObject;
import domain.Lekar;
import domain.Uput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maja
 */
public class FindAllUputi extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    Object result1;

    public FindAllUputi() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Uput)) {
            throw new Exception("Greska u preconditions");
        }
    }

    @Override  
    protected void executeOperation(GeneralDObject param) throws Exception {
        Uput u = new Uput();
        List<GeneralDObject> odoUputi = bbp.findAllRecords_NoCondition(u);
        List<Uput> uputi = new ArrayList<>();
        for (GeneralDObject odo : odoUputi) {
            u = (Uput) odo;
            u.setLekar((Lekar) bbp.findRecord1(u.getLekar()));
            u.setAnalize(findAnalize(u));
            uputi.add(u);
        }
        result1=uputi;

    }

    @Override
    protected void comitTransaction() throws Exception {
        bbp.commitTransation();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        bbp.rollbackTransation();
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

    @Override
    public Object getResult1() {
        return result1;
    }

}
