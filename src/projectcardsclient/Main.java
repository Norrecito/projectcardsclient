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
    
    /*
     * A logikai érték ami jelző van-e folyamatban kapcsolódás
     */
    private static boolean isConnecting=false;
    
    /*
     * A szerverhez való kapcsolódást elvégző metódus
     */
    public static void connectToServer(String username, String password) {
        
    }
    
    /*
     * A szerverről való lekapcsolodást elvégző metódus
     */
    public static void disconnectFromServer(){
        
    }
    
    /*
     * A jelenlegi kapcsolat bontását és az újrakapcsolodást elvégző metódus
     */
    public static void reconnectToServer(){
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(conf);
        Login loginwindow = new Login(conf);
    }
}
