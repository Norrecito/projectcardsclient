/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Az erőforrás kezelő osztály
 * @author Norrecito
 */
public class RM {
   
    /*
     * Visszaadja a program ikonját
     */
    public static Image getIconImage() {
        return getImage("icon.png");
        }
    
    /*
     * Visszaadja a program logoját
     */
    public static Icon getLogoImage() {
        return getIcon("logo.png");
        }
    
    /*
     * Visszaadja a "Felhasználó" ikonját
     */
    public static Icon getUsernameIcon() {
        return getIcon("username.png");
    }
    
    /*
     * Visszaadja a "Jelszó" ikonját
     */
    public static Icon getPasswordIcon() {
        return getIcon("password.png");
    }
    
    /*
     * A képfájlok beolvasását szolgáló metódus, ezután "Image" formában lesznek
     */
    private static Image getImage(String name) {
        try {
            return Toolkit.getDefaultToolkit().getImage("resources/"+name);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /*
     * Képek beolvasását végző metódús, ami ezek után "Icon" formába teszi őket
     */
    private static Icon getIcon(String name) {
        return new ImageIcon("resources/"+name);
    }
}
