/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author Norrecito
 */
public class ConnectionSettings extends JDialog {
   public ConnectionSettings(Window owner) {
       
       super(owner, "Kapcsolat beállítások"); //A "gazda" és az ablak címkéjének beállítása
       initComponents(); //Komponensek inicialízálása
       initDialog();     //Ablak inicialízálása
       
   }
   
   private void initComponents() {
       
   }
   private void initDialog() {
       
   }
}
