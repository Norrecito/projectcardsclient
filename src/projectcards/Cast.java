/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcards;

/**
 *
 * @author Norrecito
 */
public enum Cast {
    
    /*
     * Az alap osztályok
     */
    ARCHER, KNIGHT, MAGICIAN;
    
    public String getText() {
        switch (this) {
            case ARCHER:
                return "Íjász";
            case KNIGHT:
                return "Lovag";
            default:
                return "Varázsló";
        }
    }
}
