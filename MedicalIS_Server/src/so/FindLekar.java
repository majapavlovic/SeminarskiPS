/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbbroker.BrokerBazePodataka_impl;
import domain.GeneralDObject;
import domain.KartonPacijenta;
import domain.Lekar;

/**
 *
 * @author Maja
 */
public class FindLekar extends AbstractSO {

    BrokerBazePodataka_impl bbp;
    GeneralDObject result;

    public FindLekar() {
        bbp = new BrokerBazePodataka_impl();
        bbp.makeConnection();
    }

    @Override
    protected void precondition(GeneralDObject param) throws Exception {
        if (param == null || !(param instanceof Lekar)) {
            throw new Exception("Niste uneli odgovarajuci parametar");
        }
    }

    @Override
    protected void executeOperation(GeneralDObject param) throws Exception {
        Lekar l = (Lekar) param;
        l = (Lekar) bbp.findRecord1(l);
        result = l;
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
