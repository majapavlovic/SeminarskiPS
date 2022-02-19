/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.GeneralDObject;
import java.util.List;

/**
 *
 * @author Maja
 */
public abstract class AbstractSO {

    GeneralDObject result;
    Object result1;
    boolean resultB;
    List<GeneralDObject> lista;

    public void execute(GeneralDObject param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            executeOperation(param);
            comitTransaction();
            System.out.println("Uspesno izvrsena operacija!");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Greska!");
            rollbackTransaction();
            throw new Exception(exception);
        }
    }

    protected abstract void precondition(GeneralDObject param) throws Exception;

    protected abstract void executeOperation(GeneralDObject param) throws Exception;

    private void startTransaction() {
    }

    protected void comitTransaction() throws Exception {
    }

    protected void rollbackTransaction() throws Exception {
    }

    public GeneralDObject getResult() {
        return result;
    }


    public boolean isResultB() {
        return resultB;
    }

    public List<GeneralDObject> getLista() {
        return lista;
    }

    public Object getResult1() {
        return result1;
    }
    

    
}
