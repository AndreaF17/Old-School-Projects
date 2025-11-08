/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;

public interface CentroVaccinaleInterfaccia extends Remote{

    /**
     * Ottiene un nuovo ID univoco
     * @param columnName
     * @param tableName
     * @return Id generato
     * @throws RemoteException
     */
    String getValidId(String columnName, String tableName) throws RemoteException;

    /**
     * Ottiene l'oggetto CentroVaccinale con id uguale a quello passato come parametro
     * @param id id del centro vaccinale
     * @return Oggetto CentroVaccinale se la ricerca va a buon fine, null altrimenti
     * @throws RemoteException 
     */
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;

    /**
     * Registra un nuovo centro vaccinale e ritorna l'id generato
     * @param centro oggetto centro vaccinale da registrare
     * @return ID del centro appena registrato
     * @throws RemoteException
     */
    String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;

        /**
     * Registra un nuovo vaccinato
     * @param nomeCittadino nome del vaccinato
     * @param cognomeCittadino cognome del vaccinato
     * @param codiceFiscale codice fiscale del vaccinato
     * @param dataSomministrazione data di somministrazione del vaccino
     * @param typeVaccino tipologia di vaccino
     * @param idCentro id del centro in cui si sta registrando il vaccino
     * @return id vaccinazione, da fornire al cittadino appena vaccinato
     * @throws RemoteException
     */
    String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException;

    /**
     * Ottiene una lista di tutti i centri vaccinali registrati a sistema
     * @return lista di oggetti CentroVaccinale
     * @throws RemoteException
     */
    List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException;
}
