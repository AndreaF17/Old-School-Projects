/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

import unidevteam.classes.*;

/**
 * Classe per gestire la registrazione
 */
public class RegistrationHandler {

    private static Cittadino cittadino;

    /**
     * @return cittadino
     */
    public static Cittadino getCittadino() { return cittadino; }

    /**
     * @param nome
     * @param cognome
     * @param codiceFiscale
     * @param email
     */
    public static void setData1(String nome, String cognome, String codiceFiscale, String email) {
        cittadino = new Cittadino();

        cittadino.setNome(nome);
        cittadino.setCognome(cognome);
        cittadino.setCodiceFiscale(codiceFiscale);
        cittadino.setEmail(email);
    }

    /**
     * @param idVaccinazione id di vaccinazione
     * @param password
     */
    public static void setData2(String idVaccinazione, String password) {
        cittadino.setIdVaccinazione(idVaccinazione);

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashedPassword);
        cittadino.setHashedPassword(hashedPassword);
    }
}
