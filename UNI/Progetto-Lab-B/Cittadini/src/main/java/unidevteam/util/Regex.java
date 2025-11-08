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
     * @param s stringa input
     * @param regex regex da utilizzare
     * @return true se il controllo va a buon fine, altrimenti false.
     */
    public static Boolean check(String s, String regex) {
        return s.matches(regex);
    }

    /**
     * Funziona come il metodo {@link Regex#check(String, String) check()} ma con una regex default per la gestione di password
     * @param s stringa input
     * @return true se il controllo va a buon fine, altrimenti false.
     */
    public static Boolean checkPsw(String s){
        // aggiungere regex default per pass
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,40}$";
        return s.matches(regex);
    }
}