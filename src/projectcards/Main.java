/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * A szerverhez való kapcsolodást, illetve lekapcsolodást végző osztály
     */
    private static final ConnectionHandler conhandler = new ConnectionHandler(conf);
    
    /*
     * A bejelentkező ablak annak minden metodusával
     */
    public static Login loginwindow;
    
    /*
     * A felhasználóval kapcsolatos információkat tároló objektum
     */
    private static UsrData usrdata;
    
    /*
     * A szerverhez való kapcsolódást kezdeményező metódus
     */
    public static void connectToServer(String username, String password) {
        
        usrdata =new UsrData(username,password); //A felhasználó nevének, és jelszavának eltárolólása az erre kijelölt objektumba
        
        try {
            conhandler.connect(usrdata); //Megkéri a kapcsolatkiépítéséért felelős osztály, hogy kezdeményezze meg a kapcsolódást
            isConnecting=true; //A kapcsolat jelenleg él
            /*
             * Amennyiben sikerült kapcsolódni üzen a "Login" osztálynak hogy a folyamatokat jelző dialóguson állítsa sikeresre a kapcsolódást
             */
            loginwindow.connectedToServer();
        } catch (UnknownHostException ex) {
            /*
             * "Ismeretlen hoszt" kivétel kezelése
             * Üzen a "Login" osztálynak hogy az a folyamatokat jelző dialógusán állítsa a kapcsolódást sikertelenre
             */
            loginwindow.exceptionUnkownHost();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            /*
             * "IO" kivétel kezelése
             * Üzen a "Login" osztálynak hogy az a folyamatokat jelző dialógusán állítsa a kapcsolódást sikertelenre
             */
            loginwindow.exceptionIO();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     * A szerverről való lekapcsolodást kezdeményező metódus
     */
    public static void disconnectFromServer(){
        try {
            conhandler.disconnect(); //Megkéri a kapcsolatkiépítéséért felelős osztály, hogy szüntesse meg a fenálló kapcsolatot
            isConnecting=false; //A kapcsolat már nem él
        } catch (IOException ex) {
            /*
             * "IO" kivétel kezelése
             */
            loginwindow.exceptionIO();
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     * A jelenlegi kapcsolat bontását és az újrakapcsolodást elvégző metódus
     */
    public static void reconnectToServer(String username, String password){
       if(isConnecting){
          disconnectFromServer();
          connectToServer(username, password);
       } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println(conf); //Teszt jellegüen kiírja a jelenlegi konfigurációt (IP, port)
        loginwindow = new Login(conf);
    }
}
