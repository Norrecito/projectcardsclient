/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

/**
 * Az általános karakter osztály
 * 
 * @author Norrecito
 */
public class Char {
    
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
    private Cast cast;
    
    /*
     * A karakterhez tartozó avatar
     */
    private Avatar avatar;
    
    /*
     * A jelenlegi aktuális szinthez tartozó megszerzett tapasztalatpont
     */
    private int xp;
    
    /*
     * Az összes megszerzett tapasztalatpont
     */
    private int totalxp;
    
    /*
     * A statisztikáhóz szükséges alapvető változók:
     * @param STR: Strenght     = Erő
     * @param INT: Intelligence = Intelligencia
     * @param DEX: Dexterity    = Ügyesség
     * @param VIT: Vitality     = Életerő
     */
    private int STR, INT, DEX, VIT;
}
