/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbbroker;

import domain.GeneralDObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



/**
 *
 * @author Maja
 */
public abstract class BrokerBazePodataka {
   public abstract boolean makeConnection();
    public abstract boolean insertRecord(GeneralDObject odo);
    public abstract boolean updateRecord(GeneralDObject odo,GeneralDObject odoold);
    public abstract boolean updateRecord(GeneralDObject odo); 
    public abstract boolean deleteRecord(GeneralDObject odo);
    public abstract GeneralDObject findRecord(GeneralDObject odo);
    public abstract GeneralDObject findRecord1(GeneralDObject odo);
    public abstract List<GeneralDObject> findAllRecords(GeneralDObject odo);
    public abstract List<GeneralDObject> findAllRecords_NoCondition(GeneralDObject odo);
    public abstract Long findMaxRecord(GeneralDObject odo);
    public abstract boolean commitTransation();
    public abstract boolean rollbackTransation();
    public abstract boolean getCounter(GeneralDObject odo,AtomicInteger counter); 
    public abstract boolean increaseCounter(GeneralDObject odo,AtomicInteger counter); 
    public abstract void closeConnection();
    
    
}
