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
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

/**
 * Serve per connettersi al server utilizzando RMI
 * Include:
 * <ul>
 * <li>Costruttore per la connession con RMI</li>
 * <li>Aggiunta del centro vaccinale</li>
 * <li>Un metodo per ottenere tutti i centri vaccinali</li>
 * <li>Un metodo per aggiungere vaccinati</li>
 * </ul>
 */
public class Client {
    static final int port = 1099;
    private CentroVaccinaleInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CentroVaccinaleInterfaccia) registry.lookup("Server_centroVaccinale");
            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
    
    /**
     * Permette di aggiungere centri vaccinali
     * @param centro
     * @return Stringa con id del centro vaccinale
     * @throws Exception
     */
    public String addCentroVaccinale(CentroVaccinale centro) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCentroVaccinale(centro);
            }
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return null;
		}
    }

    /**
     * Permette di ottenere tutti i centri vaccinali
     * @return Lista di centri vaccinali
     * @throws Exception
     */
    public List<CentroVaccinale> getAllCentriVaccinali() throws Exception {
        try {
            if(stub != null) {
                return stub.getAllCentriVaccinali();
            }    
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return null;
		}
    }
    
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
    public String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws Exception {
        try{
            if(stub != null) {
                return stub.addVaccinato(nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, typeVaccino, idCentro);
            }
            throw new Exception("Impossibile connettersi al server");
        } catch (RemoteException e) {
            return null;
        }
        
    }
}
