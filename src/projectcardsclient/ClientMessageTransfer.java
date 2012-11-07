/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.net.Socket;

/**
 *
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
}
