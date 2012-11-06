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
    private String sentence; //kliens üzeni...
    private String modifiedSentence; //szerver válasza
    
    /*
     * A Socket
     */
    private Socket clientSocket;
    
    /*
     * A ki és bemeneti csatorna 
     */
    ObjectOutputStream outToServer;
    ObjectInputStream inFromServer;
    
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
        clientSocket = createSocket(conf.getIP(),conf.getPort());
        /*
         * A ki és bemeneti adatcsatorna létrehozása
         */
        outToServer = createOutPutStream();
        inFromServer = createInPutStream();
        System.out.print("Üzenet beolvasása: ");
        sentence = inFromUser.readLine();
        outToServer.writeUTF(sentence + '\n');
        
        modifiedSentence = inFromServer.readUTF();
        System.out.println("Szerver üzenete: " + modifiedSentence);
        
         //IDE JÖN AZ A RÉSZ, AMI NEKEM A RUN-BAN VAN
        
        connected=true;
        
        //clientSocket.close(); //kapcsolat lezárása
        
        
        
     }
    
    /*
     * A Socket létrehozását elvégző metódus
     */
    private Socket createSocket(String ip, int port) throws UnknownHostException, IOException{
        return new Socket(ip, port);
    }
    
    /*
     * A kimeneti adatcsatorna létrehozása
     */
    private ObjectOutputStream createOutPutStream() throws IOException {
        return new ObjectOutputStream(clientSocket.getOutputStream());
    }
    
    /*
     * A bemeneti adatcsatorna létrehozása
     */
    private ObjectInputStream createInPutStream() throws IOException {
        return new ObjectInputStream(clientSocket.getInputStream());
    }
    
    /*
     * A szerverről való lekapcsolódást elvégző metódus
     */
    public void disconnect() throws IOException {
        clientSocket.close();
        connected=false;
    }
    
    /*
     * Visszaadja van-e kiépítve jelenleg kapcsolat a kliens és a szerver között
     */
    public boolean isConnected() {
        return connected;
    }
}   
