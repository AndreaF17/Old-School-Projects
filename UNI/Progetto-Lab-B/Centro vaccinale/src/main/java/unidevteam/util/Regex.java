/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

/**
 * Classe che facilita il controllo tramite espressioni regolari
 */
public class Regex {

    /**
     * 
     * @param s stringa input
     * @param regex regex da utilizzare
     * @return true se il controllo va a buon fine, altrimenti false.
     */
    public static Boolean check(String s, String regex) {
        return s.matches(regex);
    }
}