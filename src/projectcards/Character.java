/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

import java.awt.Image;

/**
 * Az általános karakter osztály
 * 
 * @author Norrecito
 */
public class Character {
    
    /*
     * A karakter neve
     */
    private String name;
    
    /*
     * A karakter szintje
     */
    private int lvl;
    
    /*
     * A karakter jelenlegi osztálya
     * szándékosan van egy "s"-el írva...
     */
    private String clas;
    
    /*
     * A karakterhez tartozó avatar
     */
    private Image avatar;
    
    /*
     * A jelenlegi aktuális szinthez tartozó megszerzett tapasztalatpont
     */
    private int xp;
    
    /*
     * Az összes megszerzett tapasztalatpont
     */
    private int totalxp;
    
    /*
     * A statisztikáhóz szükséges alapvető változók deklarálása
     * - STR: Strenght     = Erő
     * - INT: Intelligence = Intelligencia
     * - DEX: Dexterity    = Ügyesség
     * - VIT: Vitality     = Életerő
     */
    private int STR, INT, DEX, VIT;
}
