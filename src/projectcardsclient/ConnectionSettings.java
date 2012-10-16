/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A kapcsolatbeállítások ablak
 * @author Norrecito
 */
public class ConnectionSettings extends JDialog {
   
    /*
     * A konfiguráció
     */
    private Config conf;
    
    /*
     * Az IP cím validálására (helyeségének ellenőrzésére) használt reguláris kifejezés
     */
    private static final Pattern ptAddress = Pattern.compile("^[a-z\\d]{1}[\\w\\.\\d]{0,18}[a-z\\d]{1}$", Pattern.CASE_INSENSITIVE);
    
    /*
     * A port validilására használt reguláris kifejezés
     */
    private static final Pattern ptPort = Pattern.compile("^[\\d]{1,5}$", Pattern.CASE_INSENSITIVE);
    
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
    private final Button btSave = new Button("Mentés") {
        {
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                   if(checkConfig()) saveConfig();
                }
            });
        }
    };
    
    /*
     * Ezzel a gombal tudjuk visszaállítani a "gyári" beállításokat
     */
    private final Button btDefault = new Button("Visszaállítás") {
        {
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    restoreConfig();
                }
            });
        }
    };
    
    /*
     * "Elrejti" az ablakot miután rákattintottunk
     */
    private final Button btClose = new Button("Bezárás"){
        {
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    hideDialog();
                }
            });
        }
    };
    
    /*
     * Az ablakhóz tartozó "állapotsor"
     */
    private final StatusBar statusbar = new StatusBar();
    
    /*
     * A beállításokhóz tartozó címkéket, és szövegmezőket megjelenítő panel
     */
    private final JPanel pnSettings = new JPanel(){
        {
           setLayout(new GridBagLayout()); //Elrendezés beállítása
           GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
           
           //Font fText = new Font();
           
           JLabel lbText = new JLabel("Kapcsolatbeállítások"); //Az IP-hez tartozó címke
           JLabel lbIP = new JLabel("IP cím vagy hoszt:"); //Az IP-hez tartozó címke
           JLabel lbPort = new JLabel("Port:"); //A Porthoz tartozó címke
           
           lbIP.setPreferredSize(new Dimension(120,25)); //Az IP címkéjének átméretezése
           lbPort.setPreferredSize(new Dimension(120,25)); //A port címkéjének átméretezése
           
           //[0,0]
           c.gridx=0; //oszlop
           c.gridy=0; //sor
           add(lbIP,c);
           
           //[0,1]
           c.gridy=1;
           add(tfIP,c);
           
           //[1,0]
           c.gridx=1;
           c.gridy=0;
           add(lbPort,c);
           
           //[1,1]
           c.gridy=1;
           add(tfPort,c);
           
           
        }
    };
    
    /*
     * A gombokat megjelenítő panel
     */
    private final JPanel pnButtons = new JPanel(){
        {
          setLayout(new GridBagLayout()); //Panel elrendezésének beállítása
          GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
          
          c.gridwidth = 1;
          //[0,0]
          c.gridx=0; //oszlop
          c.gridy=0; //sor
          add(btSave,c); //A "Mentés" gomb hozzáadása
          
          //[1,0]
          c.gridx=1;
          add(btClose,c); //A "Bezárás" gomb hozzáadása
          
          //[2,0]
          c.gridx=2;
          add(btDefault,c); //A "Visszaállítás" gomb hozzáadása
        }
    };
    
    public ConnectionSettings(Window owner, Config conf) {
       
       super(owner, "Kapcsolatbeállítások"); //A "gazda" és az ablak címkéjének beállítása
       this.conf = conf; //Beállítja az aktuális konfigurációt
       initComponents(); //Komponensek inicialízálása
       initDialog();     //Ablak inicialízálása
       
   }
   
   private void initComponents() {
     
       tfIP.setPreferredSize(new Dimension(120,25)); //Az "IP" szövegmező átméretezése
       tfPort.setPreferredSize(new Dimension(120,25)); //A "Port" szövegmező átméretezése
       
   }
   private void initDialog() {
       
       setTitle("Kapcsolatbeállítások"); //A dialógus cimkéjének beállítása
       setIconImage(RM.getIconImage()); //A dialógus ikonképének beállítása
       setLayout(new BorderLayout()); //Az elrendezés beállítása
       
       add(pnSettings, BorderLayout.NORTH);
       add(pnButtons, BorderLayout.CENTER);
       add(statusbar, BorderLayout.SOUTH);
       
       setDefaultCloseOperation(HIDE_ON_CLOSE); //Az "X"-re kattintva elrejtődik az ablak
       setModalityType(ModalityType.APPLICATION_MODAL); //A dialogús ablak módális lesz
       pack(); //Tömörítés a komponensek méretétől fűggöen
       setLocationRelativeTo(null); //Az ablakot a képernyő közepére helyezi
       setResizable(false); //A dialogús ablak átméretezésének letíltása
   }
   
    /*
     * Megjeleníti a dialogus ablakot, és betölti az aktuális konfigurációt
     */
    @Override
    public void setVisible(boolean b) {
        if (b && !isVisible()) loadConfig();
        if (b) toFront();
        super.setVisible(b);
    }
   
   /*
    * Elrejti a dialogús ablakot
    */
   private void hideDialog() {
       setVisible(false);
   }
   
   /*
    * Betölti az aktuális konfigurációt
    */
   private void loadConfig() {
     tfIP.setText(conf.getIP());
     tfPort.setText(Integer.toString(conf.getPort()));
   }
   
   /*
    * Elmenti az aktuális konfigurációt amennyiben teljesülnek a kritériumok
    */
   private void saveConfig() {
      
         conf.setIP(tfIP.getText());
         conf.setPort(Integer.parseInt(tfPort.getText()));
         if(Config.save(conf)) {
            statusbar.setText("A beállítások elmentésre kerültek"); //Beállítja a statusbar feliratát 
         } else {
            statusbar.setText("A beállítások elmentése nem sikerült!"); //Beállítja a statusbar feliratát  
         }
         
       }
   
   /*
    * Ellenőrzi helyes-e a konfiguráció
    */
   private boolean checkConfig() {
       if(!isConfigValid(tfIP.getText(), tfPort.getText())) {
         statusbar.setText("A beállítások nem megfelelőek!"); //Beállítja a statusbar feliratát
         return false;
       }
       return true;
   }
   
   /*
    * Visszaállítja az alapértelmezett konfigurációt
    */
   private void restoreConfig(){
     tfIP.setText(conf.getDefaultIP()); //Visszaállítja a szövegmezőbe az eredeti IP címet
     tfPort.setText(Integer.toString(conf.getDefaultPort())); //Visszaállítja a szövegmezőbe az eredeti portot
     
     saveConfig();
     
     statusbar.setText("Alapértelmezett beállítások visszaállítva"); //Beállítja a statusbar feliratát
   }
   
   /*
    * Ellenörzi hogy a reguláris kifejezések alapján helyes-e a konfiguráció
    */
   private static boolean isConfigValid(String IP, String port) {
     return ptAddress.matcher(IP).matches() && ptPort.matcher(port).matches();  
   }
}
