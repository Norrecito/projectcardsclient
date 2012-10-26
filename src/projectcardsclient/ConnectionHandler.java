/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    String sentence;
    String modifiedSentence;
    
    /*
     * Konstruktor
     */
    public ConnectionHandler(Config conf) {
        this.conf = conf;
    }
    
    /*
     * A szerverhez való kapcsolódást elvégző metódus
     * egyenlőre csak teszt jellegű
     */
    public void connect() throws UnknownHostException, IOException {
        
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
       
       /*
        * Socket létrehozása, és az aktuális konguráció alapján az IP és a port beállítása hozzá
        */
        Socket clientSocket = new Socket(conf.getIP(), conf.getPort());
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("Szerver üzenete: " + modifiedSentence);
        clientSocket.close();    
        
     }
    
    /*
     * A szerverről való lekapcsolódást elvégző metódus
     */
    public void disconnect() {
        
    }
}   
