/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.io.*;

/**
 * A klienshez tartozó konfigurációs beállításokat visszaadó osztály (Szerver IP címe, Port)
 * -A konfigurációt egy fájlból olvassa ki, ami a program gyökérében található
 * -A változtatásokat ugyanebbe a fájlba elmenti ha arra szükség van 
 * 
 * @author Norrecito
 */
public class Config implements Serializable {
   
    /*
    * Az alapértelmezett beállítások deklarálása
    */
    private int Port =6789;
    private String IP ="localhost";
    
    /*
     * A fájl ahova mentésre kerülnek a beállítások
     */
    private static final File CFile = new File("Config.ini");
    
    /*
     * Sortörés jel lekérése és egy változóba deklarálása
     */
    private static final String LS = System.getProperty("line.separator");
    
    /*
     * Az alapértelmezett konfiguráció
     */
    private static final Config defaultConfig = new Config();
    
    /*
     * Visszaadja a portot
     */
    public int getPort() {
        return Port;
    }
    
    /*
     * Visszaadja az IP címet
     */
    public String getIP() {
        return IP;
    }
    
    /*
     * Visszaadja az alapértelmezett portot
     */
    public int getDefaultPort() {
        return defaultConfig.getPort();
    }
    
    /*
     * Visszaadja az alapértelmezett IP címet
     */
    public String getDefaultIP() {
        return defaultConfig.getIP();
    }
    
    /*
     * Beállítja a portot
     */
    public void setPort(int Port) {
        this.Port = Port;
    }
    
    /*
     * Beállítja az IP címet
     */
    public void setIP (String IP) {
        this.IP = IP;
    }
    
    @Override
    public String toString (){
        return "server address: " + IP + LS +
                "server port: " + Port;
    }
    
    /*
     * Elmenti az aktuális konfigurációt a kijelölt fájlba
     */
    public static boolean save(Config conf) {
        try{
              FileOutputStream fos = new FileOutputStream(CFile, false);
              ObjectOutputStream oos = new ObjectOutputStream(fos);
              oos.writeObject(conf);
              oos.flush();
              oos.close();
              fos.close();
              return true;  
        }
        catch (Exception ex){
              return false;  
        }
        
    }
    /*
     * Fájlból beolvassa és létrehozza a konfigurációs objektumot
     */
    public static Config getInstance() {
      try {
        if(CFile.isFile()) {
            FileInputStream fis = new FileInputStream(CFile);
            ObjectInputStream oin = new ObjectInputStream(fis);
            Config config;
            try {
                    config = (Config) oin.readObject();
                }
                catch (Exception ex) {
                    config = new Config();
                    save(config);
                }
                oin.close();
                fis.close();
                return config;
        } 
        else {
            return new Config();
        }
        } 
      catch (Exception ex) {
           return new Config(); 
        }
    }
}
