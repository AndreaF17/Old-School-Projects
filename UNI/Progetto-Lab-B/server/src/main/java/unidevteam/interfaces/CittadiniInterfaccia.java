/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.interfaces;

import java.rmi.*;
import java.util.List;

import unidevteam.classes.*;
import unidevteam.enumerators.TipologiaCentroVaccinale;

public interface CittadiniInterfaccia extends Remote {
    
    /**
     * Ricerca di centri vaccinali per nome
     * @param nomeCentro valore nome centro inserito come ricerca
     * @return lista di oggetti CentroVaccinale con parametri che corrisponodo al valore ricercato
     * @throws RemoteException
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException;

    /**
     * Ricerca di centri vaccinali per comune e tipologia
     * @param comune 
     * @param tipologiaCentro
     * @return lista di oggetti CentroVaccinale con parametri che corrispono ai valori ricercati
     * @throws RemoteException
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro) throws RemoteException;

    /**
     * Autenticazione di un utente
     * @param email
     * @param plainPassword password in chiaro
     * @return oggetto cittadino con tutti i dati se l'autenticazione va a buon fine, null altrimenti
     */
    public Cittadino autenticaUtente(String email, String plainPassword) throws RemoteException;

    /**
     * Registrazione di un cittadino a sistema
     * @param cittadino 
     * @return esito dell'operazione di registrazione
     * @throws RemoteException
     */
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException;

    /**
     * Inserimento di un evento avverso
     * @param eventoAvverso oggetto evento avverso che si desidera inserire
     * @param idVaccinazione id vaccinazione del cittadino che sta inserendo l'evento avverso
     * @param idCentro id del centro vaccinale in cui si sta inserendo l'evento avverso
     * @return esito dell'operazione di inserimento
     * @throws RemoteException
     */
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) throws RemoteException;

    /**
     * Controllo se l'id vaccinazione dato e' presente tra quelli di un centro vaccinale
     * @param idVaccinazione id vaccinazione da controllare
     * @param idCentro id centro vaccinale in cui controllare
     * @return esito dell'operazione di controllo
     */
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) throws RemoteException;

    /**
     * Ottenimento dei dati sugli eventi avversi di un dato centro vaccinale
     * @param idCentro id del centro vaccinale di cui si vogliono ottenere i dati riguardo gli eventi avversi
     * @return oggetto DatiExtraCentroVaccinale contenente le informazioni sugli eventi avversi registrati
     * @throws RemoteException
     */
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) throws RemoteException;
}
