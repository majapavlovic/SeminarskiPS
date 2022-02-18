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
        uzorci.add("Krv");
        uzorci.add("Urin");
        uzorci.add("Feces");
        uzorci.add("Sputum");
        uzorci.add("Bris rane(duboke)");
        uzorci.add("Bris površinske rane");
        
    }

    public List<String> getUzorci() {
        return uzorci;
    }
}
