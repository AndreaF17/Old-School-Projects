/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.classes;

import java.io.Serializable;

/**
 * Struttura dati per cittadini
 * Possiede getter e setter per impostarei valori chiave della classe
 * Serializzabile in quanto utilizzata assieme all'RMI
 */
public class Cittadino implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String idVaccinazione;
    private String hashedPassword;

    /**
     * 
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * 
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * 
     * @return codice fiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return id di vaccinazione
     */
    public String getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * 
     * @param idVaccinazione
     */
    public void setIdVaccinazione(String idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * 
     * @return password hashata
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * 
     * @param password
     */
    public void setHashedPassword(String password) {
        this.hashedPassword = password;
    }

    public Cittadino() {}

    /**
     * @param nome nome del cittadino 
     * @param cognome cognome del cittadino
     * @param codiceFiscale codice fiscale 
     * @param email email del citadino 
     * @param idVaccinazione id della vaccinazione effettuata dal cittadino
     */
    public Cittadino(String nome, String cognome, String codiceFiscale, String email, String idVaccinazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.idVaccinazione = idVaccinazione;
    }

    @Override
    public String toString() {
        return "Cittadino [codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", email=" + email
                + ", idVaccinazione=" + idVaccinazione + ", nome=" + nome + "]";
    }
}