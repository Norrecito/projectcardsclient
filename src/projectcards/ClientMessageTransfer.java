/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import java.net.Socket;

/**
 * A klienshez tartozó üzenetek küldését, és fogadását végző osztály
 * @author Norrecito
 */
public class ClientMessageTransfer extends MsgTransfer {
    
    /*
     * A klienshez tartozó felhasználói adatok
     */
    UsrData usrdata;
    
    /*
     * Konstruktor
     */
    public ClientMessageTransfer(Socket clientsocket){
            super(clientsocket);  
        }
    
    /*
     * Beállítja a felhasználói adatokat
     */
    public void setUsrData(UsrData usrdata){
        this.usrdata = usrdata;
    }
    
    /*
     * Üzenet esetén
     */
    @Override
    protected void onMessage(Object o){
         if(o instanceof String){
             //Amenyiben az üzenet String, kiírja a konzolba
             System.out.println(o.toString());    
         } else if (o instanceof UsrData){
             //Amennyiben az üzenet egy "játékos adatok" objektum, frissíti a megfelelő karaktert
         } 
    }
    
    /*
     * Indulás esetén
     */
    @Override
    protected void onStart(){
       sendMessage("<Tesztüzenet a klienstől az indulásról>");
       sendMessage(usrdata); //A felhasználói adatok elküldése hitelesítésre
    }
}
