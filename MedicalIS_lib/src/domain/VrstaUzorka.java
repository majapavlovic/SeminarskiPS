/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maja
 */
public class VrstaUzorka implements Serializable {

    List<String> uzorci = new ArrayList<>();

    public VrstaUzorka() {
        uzorci.add("Bris grla");
        uzorci.add("Bris nosa");
        uzorci.add("Urin");
        uzorci.add("Feces");
        uzorci.add("Sputum");
        uzorci.add("Bris rane(duboke)");
        uzorci.add("Bris površinske rane");
        uzorci.add("Bris grla");
    }
    
    
    /*
    
    public static final String bris_grla = "Bris grla";
    public static final String bris_nosa = "Bris nosa";
    public static final String urin = "Urin";
    public static final String feces = "Feces";
    public static final String sputum = "Sputum";
    public static final String rane = "Bris rane(duboke)";
    public static final String koza = "Kožne promene, površinske rane"; 
    public static final String koza_nokti = "Koža i nokti, karifikacija na dermatofite"; */

    public List<String> getUzorci() {
        return uzorci;
    }
}
