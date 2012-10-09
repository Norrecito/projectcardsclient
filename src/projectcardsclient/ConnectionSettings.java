/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import javax.swing.*;

/**
 * A kapcsolatbeállítások ablak
 * @author Norrecito
 */
public class ConnectionSettings extends JDialog {
   
    /*
     * Az IP cím szövegmezője
     */
    private final JTextField tfIP = new JTextField();
    
    /*
     * A Port szövegmezője
     */
    private final JTextField tfPort = new JTextField();
    
    /*
     * A beállítások elmentését szolgáló gomb
     */
    private final JButton btSave = new JButton();
    
    /*
     * Ezzel a gombal tudjuk visszaállítani a "gyári" beállításokat
     */
    private final JButton btDefault = new JButton();
    
    /*
     * A beállításokhóz tartozó címkéket, és szövegmezőket megjelenítő panel
     */
    private final JPanel pnSettings = new JPanel(){
        {
           setLayout(new GridBagLayout()); //Elrendezés beállítása
           GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
           
           //Font fText = new Font();
           
           JLabel lbText = new JLabel("Kapcsolatbeállítások"); //Az IP-hez tartozó címke
           JLabel lbIP = new JLabel("IP cím:"); //Az IP-hez tartozó címke
           JLabel lbPort = new JLabel("Port:"); //A Porthoz tartozó címke
           
           lbIP.setPreferredSize(new Dimension(120,25)); //Az IP címkéjének átméretezése
           lbPort.setPreferredSize(new Dimension(120,25)); //A port címkéjének átméretezése
           
           //[0,0]
           c.gridx=0; //oszlop
           c.gridy=0; //sor
           add(lbIP);
           
           //[1,0]
           c.gridx=1;
           add(lbPort);
        }
    };
    
    public ConnectionSettings(Window owner) {
       
       super(owner, "Kapcsolatbeállítások"); //A "gazda" és az ablak címkéjének beállítása
       initComponents(); //Komponensek inicialízálása
       initDialog();     //Ablak inicialízálása
       
   }
   
   private void initComponents() {
     
       tfIP.setPreferredSize(new Dimension(120,25)); //Az "IP" szövegmező átméretezése
       tfPort.setPreferredSize(new Dimension(120,25)); //A "Port" szövegmező átméretezése
   }
   private void initDialog() {
     
       setDefaultCloseOperation(HIDE_ON_CLOSE); //Az "X"-re kattintva elrejtődik az ablak
       setModalityType(ModalityType.APPLICATION_MODAL); //A dialogús ablak módális lesz
       pack(); //Tömörítés a komponensek méretétől fűggöen
       setLocationRelativeTo(null); //Az ablakot a képernyő közepére helyezi
       setResizable(false); //A dialogús ablak átméretezésének letíltása
   }
}
