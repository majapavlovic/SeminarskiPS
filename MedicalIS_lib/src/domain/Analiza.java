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
public class Analiza implements Serializable {

    public List<String> analize = new ArrayList();

    public Analiza() {
        analize.add("Bakterioloska");
        analize.add("Mikoloska");
        analize.add("Paraziti");
        analize.add("Virusoloska");
        //analize.add("Bakterioloska");
    }

    public List<String> getAnalize() {
        return analize;
    }

}
