  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Maja
 */
public class Operations implements Serializable{
    public static final int LOGIN_LEKAR = 1;
    public static final int LOGIN_LAB = 2;
    public static final int SHUTDOWN = 3;
    public static final int GET_PACIJENT = 4;
    public static final int GET_REZULTAT = 5;
    public static final int INSERT_PACIJENT = 6;
}
