/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;
import unidevteam.util.DBManager;

/**
 * Classe per gestire le funzionalità del server per quanto riguarda i centri vaccinali
 * Estende UnicastRemoteObject in quanto usiamo questa classe per creare il collegamento con l'RMI
 * Implementa l'interfaccia remota {@link server.interfaces.CentroVaccinaleInterfaccia CentroVaccinaleInterfaccia}
 */
public class ServerCentroVaccinale extends UnicastRemoteObject implements CentroVaccinaleInterfaccia {
    static final int port = 1099;
    Registry registry;

    /**
     * Creazione collegamento tramite RMI
     * @throws RemoteException
     */
    public ServerCentroVaccinale() throws RemoteException {
        super();
        System.out.println("RMI Server Starting");
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server_centroVaccinale", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch(ExportException e) {
            registry = LocateRegistry.getRegistry(port);
            registry.rebind("Server_centroVaccinale", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ottiene un nuovo ID univoco
     * @param columnName
     * @param tableName
     * @return Id generato
     * @throws RemoteException
     */
    @Override
    public String getValidId(String columnName, String tableName) throws RemoteException {
        try {
            return DBManager.getInstance().getValidId(columnName, tableName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Ottiene l'oggetto CentroVaccinale con id uguale a quello passato come parametro
     * @param id id del centro vaccinale
     * @return Oggetto CentroVaccinale se la ricerca va a buon fine, null altrimenti
     * @throws RemoteException 
     */
    @Override
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException {
        try {
            return DBManager.getInstance().getCentroVaccinaleById(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Registra un nuovo centro vaccinale e ritorna l'id generato
     * @param centro oggetto centro vaccinale da registrare
     * @return ID del centro appena registrato
     * @throws RemoteException
     */
    @Override
	public String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        try {
            String token = DBManager.getInstance().getValidId("id", "centrivaccinali");
            centro.setId(token);
            return DBManager.getInstance().addCentroVaccinale(centro);
        } catch (Exception e) {
            return null;
        }
	}

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
    @Override
    public String addVaccinato(String nomeCittadino, String cognomeCittadino,
            String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException {
        try {
            String token = DBManager.getInstance().getValidId("id", "vaccinati");
            return DBManager.getInstance().addVaccinato(token, nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, typeVaccino, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ottiene una lista di tutti i centri vaccinali registrati a sistema
     * @return lista di oggetti CentroVaccinale
     * @throws RemoteException
     */
    @Override
	public List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException {
        try {
			return DBManager.getInstance().getAllCentriVaccinali();
		} catch (Exception e) {
			return null;
		}
	}
    
    /**
     * Chiude il registry RMI
     * @throws RemoteException
     */
    public void exit() throws RemoteException {
    try{
        registry.unbind("Server_centroVaccinale");
        UnicastRemoteObject.unexportObject(this, true);
        System.out.println("RMI Server: exiting.");
    } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
