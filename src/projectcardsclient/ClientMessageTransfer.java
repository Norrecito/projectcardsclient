/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.net.Socket;

/**
 * A klienshez tartozó üzenetek küldését, és fogadását végző osztály
 * @author Norrecito
 */
public class ClientMessageTransfer extends MsgTransfer {
   
    /*
     * Konstruktor
     */
    public ClientMessageTransfer(Socket clientsocket){
            super(clientsocket);  
        }
    
    /*
     * Üzenet esetén
     */
    @Override
    protected void onMessage(Object o){
        System.out.println(o.toString());  
    }
    
    /*
     * Indulás esetén
     */
    @Override
    protected void onStart(){
       sendMessage("kliens üzeni, hogy blah blah"); 
    }
}
