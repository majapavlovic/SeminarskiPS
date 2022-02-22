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
public class VrsteAnaliza implements Serializable {

    public List<String> analize = new ArrayList();

    public VrsteAnaliza() {
        analize.add("Bakterioloska");
        analize.add("Mikoloska");
        analize.add("Parazitoloska");
        analize.add("Virusoloska");
        analize.add("Mikobakterioloska");
    }

    public List<String> getAnalize() {
        return analize;
    }

}
