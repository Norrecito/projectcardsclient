/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

/**
 *
 * @author Norrecito
 */
public class Main {
    
    /*
     * A konfiguráció amit a megfelelő ablakok fognak használni
     */
    private static final Config conf = Config.getInstance();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login loginwindow = new Login(conf);
    }
}
