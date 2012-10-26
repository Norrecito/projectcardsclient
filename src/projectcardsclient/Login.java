/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Norrecito
 */
public class Login extends JFrame {
    
    /*
     * A konfiguráció
     */
    private final Config conf;
    
    /*
     * A felhasználónév és jelszó mezőhőz tartozó figyelő
     */
    private KeyListener btEnabler = new KeyAdapter() {
    @Override
            public void keyReleased(KeyEvent e) {
                checkButton();
            }
   };
    
    /*
     * A kapcsolatbeállítás ablak. Átadom neki a konfigurációt
     */
    private ConnectionSettings dialogConSettings;
    
    /*
     * A szerver hitelesítés dialógus
     */
    private ServerAuthentication dialogServAuth = new ServerAuthentication(this);
    
    /*
     * A felhasználónév beírásáhóz szükséges szövegmező
     */
    private final JTextField tfUsername=new JTextField();
    
    /*
     * A jelszó beírásáhóz szükséges jelszómező
     */
    private final JPasswordField pfPassword=new JPasswordField();
    
    /*
     * A bejelentkezéshez szükséges gomb
     */
    private final JButton btLogin = new JButton("Bejelentkezés"){
        {
            setEnabled(false); //Alapértelmezetten a gomb le van tiltva
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogServAuth.setVisible(true);
                    Main.connectToServer(tfUsername.getText(), pfPassword.getPassword().toString());
                }
            });
        }
    };
    
    /*
     * A kapcsolatbeállítás ablakot megnyító gomb
     */
    private final Button btSettings = new Button("Beállítások"){
        {
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogConSettings.setVisible(true); //Kattintásra láthatóvá teszi a kapcsolatbeállítások ablakot
                }
            });
        }
    };
    
    /*
     * A Logo panelje
     */
    private final JPanel pnLogo = new JPanel() {
        {
            JLabel lbLogo = new JLabel(RM.getLogoImage()); //A Logo címkéje
            add(lbLogo); //a Logo hozzadása a panelhez       
        }
        
    };
    
    /*
     * A gombokat tartalmazó panel
     */
    private final JPanel pnButtons = new JPanel(){
        {
            setLayout(new GridBagLayout()); //Elrendezés beállítása
            GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
            
            c.insets = new Insets(5, 5, 5, 5 -btSettings.getPreferredSize().width + 5); //Margó 5x5
            c.gridwidth = 1;
            c.weightx = 1;
            
            c.anchor = GridBagConstraints.LINE_START;
            add(btSettings,c); //A "Kapcsolatbeállítások" gomb hozzáadása
            
            c.weightx = Integer.MAX_VALUE;
            c.anchor = GridBagConstraints.CENTER;
            add(btLogin,c); //A "Bejelentkezés" gomb hozzáadása 
        }
    };
    
    /*
     * A bejelentkezéshez szükséges részt tartalmazó panel
     */
    private final JPanel pnLogin = new JPanel(){
        {
            setLayout(new GridBagLayout()); //Elrendezés beállítása
            GridBagConstraints c = new GridBagConstraints(); //Szükséges konstans
            
            JLabel lbUsername = new JLabel("Felhasználónév"); //A felhasználónév címkéje
            JLabel lbPassword = new JLabel("Jelszó"); //A jelszó címkéje
            
            lbUsername.setIcon(RM.getUsernameIcon()); //"felhasználónév" címke Ikonjának beállítása
            lbPassword.setIcon(RM.getPasswordIcon()); //"Jelszó" címke Ikonjának beállítása
            
            lbUsername.setPreferredSize(new Dimension(120,25)); //Címke átméretezése
            lbPassword.setPreferredSize(new Dimension(120,25)); //Címke átméretezése
            
            //[0,0]
            c.gridx=0; //oszlop
            c.gridy=0; //sor
            add(lbUsername,c); //a "Felhasználónév" címke hozzáadása
            
            //[1,0]
            c.gridx=1;
            add(tfUsername,c); //A "felhasználónév" szövegmező hozzáadása
            
            //[0,1]
            c.gridx=0;
            c.gridy=1;
            add(lbPassword,c); //A "Jelszó" címke hozzáadása
            
            //[1,1]
            c.gridx=1;
            c.gridy=1;
            add(pfPassword, c); //A "jelszó" jelszómező hozzáadása
        }
    };
    
    public Login(Config conf) {
        
        this.conf =conf;  //A konfiguráció beállítása
        dialogConSettings = new ConnectionSettings(this, conf);
        initComponents(); //Komponensek inicialízálása
        initWindow();     //Ablak inicialízálása
    
    }
    
    
    private void initComponents(){
       
       btLogin.setFocusable(false); //Fókusz letiltása
       btSettings.setFocusable(false); //Fókusz letiltása
       
       btLogin.setBackground(Color.lightGray); //A "bejelentkezés" gomb háttérszínének beállítása
       btSettings.setBackground(pnLogin.getBackground()); //A "Beállítások" gomb háttérszínének beállítása
       
       tfUsername.setPreferredSize(new Dimension(120,25)); //Szövegmező átméretezése
       pfPassword.setPreferredSize(new Dimension(120,25)); //Jelszómező átméretezése
       
       tfUsername.addKeyListener(btEnabler); //A figyelő hozzáadása a felhasználónév mezőhőz
       pfPassword.addKeyListener(btEnabler); //A figyelő hozzáadása a jelszó mezőhőz
    }
    
    private void initWindow(){
    
       setTitle("Bejelentkezés"); //Az ablak cimkéjének beállítása    
       setIconImage(RM.getIconImage()); //Az ablak ikonjának beállítása
       setLayout(new BorderLayout()); //Elrendezés beállítása
    
       add(pnLogo, BorderLayout.NORTH); //A Logo panel hozzáadása
       add(pnLogin, BorderLayout.CENTER); //A Login panel hozzáadása
       add(pnButtons, BorderLayout.SOUTH); //A Gombok panel hozzáadása
    
       setVisible(true); //Legyen látható
       setDefaultCloseOperation(EXIT_ON_CLOSE); //Az X-re kattintva bezárodik a program
       pack(); //Tönörítse az ablakot az komponensek méretétől függően
       setLocationRelativeTo(null); //Az ablak középen jelenjen meg
       setResizable(false); //Nem lehet átméretezni
    
    }
    
    /*
     * A "Bejelentkezés" gomb engedélyezését, illetve letíltását végző metódus
     */
    private void checkButton() {
       
       int u = tfUsername.getText().length();
       int p = pfPassword.getPassword().length;
       btLogin.setEnabled(u >= 3 && u <=20 && p >= 6 && p <= 20); 
    
    }
    
    /*
     * Amennyiben "Ismeretlen hoszt" kivétel keletkezik
     */
    public void exceptionUnkownHost() {
      dialogServAuth.connectionFailed();
      JOptionPane.showMessageDialog(this,"Ismeretlen hoszt!");
    }
    
    /*
     * Amennyiben "IO" kivétel keletkezik
     */
    public void exceptionIO() {
      dialogServAuth.connectionFailed();
      JOptionPane.showMessageDialog(this,"IO hiba!");
    }
    
    /*
     * Sikerült kapcsolodni a szerverhez
     */
    public void connectedToServer() {
      dialogServAuth.connectionSucceed();  
    }
}
