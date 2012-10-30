/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A szerverhez való kapcsolodást elvégző osztály
 * @author Norrecito
 */
public class ConnectionHandler {
  
    /*
     * A konfiguráció
     */
    private Config conf;
    
    /*
     * Segédváltozók
     */
    String sentence; //kliens üzeni...
    String modifiedSentence; //szerver válasza
    
    /*
     * Van-e kapcsolat jelenleg
     */
    private boolean connected=false;
    
    /*
     * Konstruktor
     */
    public ConnectionHandler(Config conf) {
        this.conf = conf;
    }
    
    /*
     * A szerverhez való kapcsolódást elvégző metódus
     * egyenlőre csak erőssen teszt jellegű
     */
    public void connect(String username, String password) throws UnknownHostException, IOException {
        
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
       
       /*
        * Socket létrehozása, és az aktuális konguráció alapján az IP és a port beállítása hozzá
        */
        Socket clientSocket = new Socket(conf.getIP(), conf.getPort());
        
        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.print("Üzenet beolvasása: ");
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("Szerver üzenete: " + modifiedSentence);
        
        connected=true;
        
        clientSocket.close();
        
        
        
     }
    
    /*
     * A szerverről való lekapcsolódást elvégző metódus
     */
    public void disconnect() {
        connected=false;
    }
    
    /*
     * Visszaadja van-e kiépítve jelenleg kapcsolat a kliens és a szerver között
     */
    public boolean isConnected() {
        return connected;
    }
}   
