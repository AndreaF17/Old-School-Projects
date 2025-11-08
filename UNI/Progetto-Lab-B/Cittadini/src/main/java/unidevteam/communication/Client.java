/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.communication;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.classes.Cittadino;
import unidevteam.classes.DatiExtraCentroVaccinale;
import unidevteam.enumerators.TipologiaCentroVaccinale;
import unidevteam.interfaces.CittadiniInterfaccia;
import unidevteam.classes.EventoAvverso;

/**
 * Serve per connettersi al server utilizzando RMI
 * Include:
 * <ul>
 * <li>Costruttore per la connession con RMI</li>
 * <li>Registrazione cittadino</li>
 * <li>Un metodo di autenticazione di un utente</li>
 * <li>Un metodo per cercare centri vaccinali</li>
 * <li>Un metodo per inserire un evento avverso</li>
 * <li>Un metodo per controllare se un utente può inserire un dato evento avverso in un centro vaccinale</li>
 * <li>Un metodo per ottenere dati su eventi avversi</li>
 * </ul>
 */
public class Client {
    static final int port = 2099;
    private CittadiniInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CittadiniInterfaccia) registry.lookup("Server_cittadini");

            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }

    /**
     * Permette di registrare un cittadino
     * @param cittadino utente
     * @return il risultato dell'operazione, true se il cittadino si è registrato con successo, altrimenti false
     * @throws Exception
     */
    public boolean registraCittadino(Cittadino cittadino) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCittadino(cittadino);
            }    
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return false;
		}
    }

    /**
     * Permette di autenticare un utente
     * @param email email dell'utente
     * @param plainPassword password dell'utente non hashata
     * @return il cittadino se l'operazione è andata a buon fine, altrimenti false
     */
    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            if(stub != null) {
                return stub.autenticaUtente(email, plainPassword);
            }
		} catch (RemoteException e) {}

        return null;
    }

    /**
     * Permette di cercare un centro vaccinale
     * @param nomeCentro nome del centro vaccinale 
     * @return una lista di centri vacciali che matchano la stringa
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) {
        try {
            if(stub != null) {
                List<CentroVaccinale> res = stub.cercaCentroVaccinale(nomeCentro);
                return res;
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Permette di cercare un centro vaccinale
     * @param comune
     * @param tipologiaCentroVaccinale tipologia del centro vaccinale
     * @return una lista di centri vaccinali
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        try {
            if(stub != null) {
                List<CentroVaccinale> res = stub.cercaCentroVaccinale(comune, tipologiaCentroVaccinale);
                return res;
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Permette l'inserimento di un evento avverso
     * @param eventoAvverso evento avverso
     * @param idVaccinazione id di vaccinazione
     * @param idCentro id del centro
     * @return il risultato dell'operazione
     */
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) {
        try {
            if(stub != null) {
                return stub.inserisciEventoAvverso(eventoAvverso, idVaccinazione, idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Permette di controllare se un vaccinato può scrivere eventi avversi in un dato centro vaccinale
     * @param idVaccinazione id di vaccinazione
     * @param idCentro id del centro vaccinale
     * @return il risutato dell'operazione
     */
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) {
        try {
            if(stub != null) {
                return stub.controlloVaccinatoInCentro(idVaccinazione, idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Permette di ottenere dati statistici sui centri vaccinali {@link cittadini.classes.DatiExtraCentroVaccinale#DatiExtraCentroVaccinale(int, double, cittadini.enumerators.TipoEvento) DatiExtraCentroVaccinale}
     * @param idCentro id del centro
     * @return istanza di DatiExtraCentroVaccinale
     */
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) {
        try {
            if(stub != null) {
                return stub.getDatiSuEventiAvversi(idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
