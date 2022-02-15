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
public class KrvnaGrupa implements Serializable {
    public List<String> krvneGrupe = new ArrayList();

    public KrvnaGrupa() {
        krvneGrupe.add("A+");
        krvneGrupe.add("A-");
        krvneGrupe.add("AB+");
        krvneGrupe.add("AB-");
        krvneGrupe.add("B+");
        krvneGrupe.add("B-");
        krvneGrupe.add("O+");
        krvneGrupe.add("O-");
    }
    
    /*public static final String apoz = "A+";
    public static final String aneg = "A-";
    public static final String abpoz = "AB+";
    public static final String abneg = "AB-";
    public static final String bpoz = "B+";
    public static final String bneg = "B-";
    public static final String opoz = "O+";
    public static final String oneg = "O-";*/

    public List<String> getKrvneGrupe() {
        return krvneGrupe;
    }

    

    
   
}
