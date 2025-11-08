/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

import unidevteam.classes.Cittadino;

/**
 * Serve per gestire gli utenti loggati e quelli guest o non loggati
 */
public class SessionHandler {
    private static Cittadino utente = null;

    /**
     * @return utente, se null, significa che l'utente non è loggato
     */
    public static Cittadino getUtente() { return utente; }
    public static void setUtente(Cittadino c) { utente = c; }

    public static void logout() {
        utente = null;
    }
}
