/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import com.sun.source.tree.NewArrayTree;
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
public class FindUputiPacijenta extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    Object result1;
    List<GeneralDObject> lista;

    public FindUputiPacijenta() {
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
        Uput u = new Uput();
        u.setPacijent((KartonPacijenta) param);
        List<GeneralDObject> l = bbp.findAllRecords(u);
        List<Uput> uputi = new ArrayList<>();
        if (l != null && !l.isEmpty()) {
            for (GeneralDObject odo : l) {
                List<Analiza> analize = new ArrayList<>();

                u = (Uput) odo;
                u.setLekar((Lekar) bbp.findRecord1(new Lekar(u.getLekar().getUsername())));

                AbstractSO findAnalize = new FindAnalizeUputa();
                findAnalize.execute(u);
                //System.out.println("Poziv find analize: " + findAnalize.getResult());
                //u.setAnalize((List<Analiza>) findAnalize.getResult());
                //List<GeneralDObject> odoL = findAnalize.getLista();
                List<GeneralDObject> odoL = (List<GeneralDObject>) findAnalize.getResult1();
                if (odoL != null) {
                    for (GeneralDObject gdo : odoL) {
                        Analiza a = (Analiza) gdo;
                        analize.add(a);
                    }
                    u.setAnalize(analize);
                    uputi.add(u);
                }
            }
        }
        l = new ArrayList<>();
        for(Uput up : uputi) {
            l.add(up);
        }
        lista = l;
     
        //result = uputi;
        //System.out.println("Moze li result uzeti listu");
        //System.out.println("Ispis result-a: " + result);
        
        
        
        

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
