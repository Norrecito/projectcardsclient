/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A hitelesítés ablak
 * @author Norrecito
 */
public class ServerAuthentication extends JDialog {
    
    /*
     * A "Szerver hitelesítés" felirat
     */
    private JLabel lbText = new JLabel("Szerver hitelesítés");
    
    /*
     * A szerverhez való kapcsolódás állapotát megjelenítő felirat
     */
    private JLabel lbCon = new JLabel("Kapcsolodás a szerverhez...");
    private JLabel lbConImage = new JLabel(); //A folyamat állapotát mutató ikon
    
    /*
     * A felhasználónév és jelszó hitelesítésének állapotát megjelíntő felirat
     */
    private JLabel lbAuth = new JLabel("Adatok hitelesítése..."); 
    private JLabel lbAuthImage = new JLabel(); //A folyamat állapotát mutató ikon
    
    /*
     * A karakterlista betöltésének állapotát megjelenítő felirat
     */
    private JLabel lbChar = new JLabel("Karakterlista betöltése...");
    private JLabel lbCharImage = new JLabel(); //A folyamat állapotát mutató ikon
    
    /*
     * A "Mégse" gomb
     */
    private final Button btCancel = new Button("Mégse"){
        {
            setEnabled(false); //Alapértelmezetten le van tiltva a gomb
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    hideDialog();
                    resetDialog();
                    
                }
            });
        }
    };
    
    /*
     * A "Szerver hitelesítés" feliratot megjelenítő panel
     */
    private final JPanel pnText = new JPanel(){
        {
          setLayout(new FlowLayout()); //A panel elrendezésének beállítása      
          
          add(lbText); //A "Szerver hitelesítés" felirat hozzáadása
        }
    };
    
    /*
     * A hitelesítéssel kapcsolatos folyamatok állásának megjelenítésére szolgáló panel
     */
    private final JPanel pnAuth = new JPanel(){
        {
            setLayout(new GridBagLayout()); //Elrendezés beállítása
            GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
            
           //[0,0]
           c.gridx=0; //oszlop
           c.gridy=0; //sor
           add(lbCon, c);
           
           //[1,0]
           c.gridx=1;
           add(lbConImage,c);
           
           //[0,1]
           c.gridx=0;
           c.gridy=1;
           add(lbAuth,c);
           
           //[1,1]
           c.gridx=1;
           add(lbAuthImage,c);
           
           //[0,2]
           c.gridx=0;
           c.gridy=2;
           add(lbChar,c);
           
           //[1,2]
           c.gridx=1;
           add(lbCharImage, c);
        }
    };
    
    /*
     * A "Mégse" gombot tartalmazó panel
     */
    private final JPanel pnButton = new JPanel(){
        {
            setLayout(new FlowLayout()); //Az elrendezés beállítása
            add(btCancel); //A "Mégse" gomb hozzáadása
        }
    };
    
    public ServerAuthentication(Window owner) {
       
       super(owner, "Hitelesítés"); //A "gazda" és az ablak címkéjének beállítása
       initComponents(); //Komponensek inicialízálása
       initDialog();     //Ablak inicialízálása
       
   }
    private void initComponents() {
      //Komponensek méreteinek beállítása
      lbCon.setPreferredSize(new Dimension(250,25));   
      lbAuth.setPreferredSize(new Dimension(250,25));  
      lbChar.setPreferredSize(new Dimension(250,25));
      
      //A hozzájuk tartozó felirat alapértelmezett ikonjainak beállítása
      lbText.setIcon(RM.getCheckIcon());
      lbConImage.setIcon(RM.getLoadingIcon());
      lbAuthImage.setIcon(RM.getLoadingIcon());
      lbCharImage.setIcon(RM.getLoadingIcon());
      
    }
    private void initDialog() {
        
      setIconImage(RM.getServerImage()); //Beállítja a dialógus ikonképét
      setLayout(new BorderLayout()); //A dialógus elrendezésének beállítása
      
      add(pnText, BorderLayout.NORTH);
      add(pnAuth, BorderLayout.CENTER);
      add(pnButton, BorderLayout.SOUTH);
      
      setDefaultCloseOperation(HIDE_ON_CLOSE); //Az "X"-re kattintva elrejtődik az ablak
      //setModalityType(ModalityType.APPLICATION_MODAL); //A dialogús ablak módális lesz
      pack(); //Tömörítés a komponensek méretétől fűggöen
      setLocationRelativeTo(null); //Az ablakot a képernyő közepére helyezi
      setResizable(false); //A dialogús ablak átméretezésének letíltása
    }
    
    /*
     * Amennyiben sikerült csatlakozni a szerverhez
     */
    public void connectionSucceed() {
        
        lbCon.setText("Kapcsolodva a szerverhez"); //A kapcsolodás állapotát jelző felirat beállítása
        lbConImage.setIcon(RM.getSucceedIcon());  //A hozzá tartozó felirat ikonjának átálítása
    }
    
    /*
     * Amennyiben nem sikerült csatlakozni a szerverhez
     */
    public void connectionFailed() {
        
        lbCon.setText("Nem sikerült kapcsolodni a szerverhez!"); //A kapcsolodás állapotát jelző felirat beállítása
        lbConImage.setIcon(RM.getFailedIcon()); //A hozzá tartozó felirat ikonjának átálítása
        btCancel.setEnabled(true); //A "Mégse" gomb engedélyezése
    }
    
    /*
     * Amennyiben a beírt jelszó és felhasználónév megfelelő
     */
    public void dataCorrect() {
       
       lbAuth.setText("Adatok hitelesítve"); //Az adatok hitelesítésének állapotát jelző felirat beállítása
       lbAuthImage.setIcon(RM.getSucceedIcon());  //A hozzá tartozó felirat ikonjának átálítása
    }
    
    /*
     * Amennyiben a beírt jelszó és felhasználónév nem megfelelő (Azaz a hitelesítés alapján nem jó)
     */
    public void dataIncorrect() {
      
      lbAuth.setText("Az adatok nem megfelelőek!"); //Az adatok hitelesítésének állapotát jelző felirat beállítása
      lbAuthImage.setIcon(RM.getFailedIcon());  //A hozzá tartozó felirat ikonjának átálítása
      btCancel.setEnabled(true); //A "Mégse" gomb engedélyezése
    }
    
    /*
     * Amennyiben a karakterlista betöltése sikerült
     */
    public void charlistLoadingSucceed() {
        
        lbChar.setText("Karakterlista betöltve"); //A karakterlista betöltésének állapotát jelző felirat beállítása
        lbCharImage.setIcon(RM.getSucceedIcon());  //A hozzá tartozó felirat ikonjának átálítása
    }
    
    /*
     * Amennyiben a karakterlista betöltése nem sikerült
     */
    public void charlistLoadingFailed() {
        
        lbChar.setText("Karakterlista betöltése nem sikerült!"); //A karakterlista betöltésének állapotát jelző felirat beállítása
        lbCharImage.setIcon(RM.getFailedIcon());  //A hozzá tartozó felirat ikonjának átálítása
        btCancel.setEnabled(true); //A "Mégse" gomb engedélyezése
    }
    
    /*
     * Visszaállítja a dialógus ablakot az alap állapotába
     */
    private void resetDialog(){
        
        //Feliratok szövegeinek visszaállítás az alapértelmezetre
        lbCon.setText("Kapcsolodás a szerverhez...");
        lbAuth.setText("Adatok hitelesítése...");
        lbChar.setText("Karakterlista betöltése...");
        
        //A hozzájuk tartozó feliratok alapértelmezett ikonjainak visszaállítása
        lbConImage.setIcon(RM.getLoadingIcon());
        lbAuthImage.setIcon(RM.getLoadingIcon());
        lbCharImage.setIcon(RM.getLoadingIcon());
    }
    
    /*
     * A dialogús elrejtését végző metódus
     */
    private void hideDialog(){
        setVisible(false);
    }
}
