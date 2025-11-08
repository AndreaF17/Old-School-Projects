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

/**
 * Interfaccia remota per RMI
 * Gestisce quali metodi possono essere lanciati remotamente dal client
 */
public interface CentroVaccinaleInterfaccia extends Remote{
    String getValidId(String columnName, String tableName) throws RemoteException;
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;

    /**
     * Permette di aggiungere centri vaccinali
     * @param centro
     * @return Stringa con id del centro vaccinale
     * @throws Exception
     */
    String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;

    /**
     * Permette di aggiungere vaccinati
     * @param nomeCittadino nome del cittadino 
     * @param cognomeCittadino cognome del cittadino 
     * @param codiceFiscale codice fiscale del cittadino
     * @param dataSomministrazione data somministrazione del vaccino
     * @param typeVaccino tipo di vaccino 
     * @param idCentro
     * @return String con id del vaccinato
     * @throws Exception
     */
    String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException;

    /**
     * Permette di ottenere tutti i centri vaccinali
     * @return Lista di centri vaccinali
     * @throws Exception
     */
    List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException;
}
