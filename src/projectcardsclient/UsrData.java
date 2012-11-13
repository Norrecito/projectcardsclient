/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.io.Serializable;

/**
 *
 * @author Norrecito
 * A felhasználóhóz tartozó információkat tároló osztály
 */
public class UsrData implements Serializable {
    
    /*
     * A jelszó és a felhasználónévhez tartozó szövegek deklarálása 
     */
    private String Username;
    private String Password;

    /*
     * Az első konstruktor
     * Minden értéke alapértelmezetten null
     * a szerver számára
     */
    public UsrData(){
        
    }
    
    /*
     * A második konstruktor
     * Egy felhasználónevet és egy jelszót kér ami alapján beállítja az ezekhez tartozó változókat
     * a kliens számára
     */
    public UsrData(String Username, String Password){
      this.Username = Username;
      this.Password = Password;
    }
    
    /*
     * Jelszó lekérése
     */
    public String getPassword() {
        return Password;
    }
    
    /*
     * Jelszó beállítása
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    /*
     * Felhasználónév lekérése
     */
    public String getUsername() {
        return Username;
    }
    
    /*
     * Felhasználónév beállítása
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }
    
    
}
