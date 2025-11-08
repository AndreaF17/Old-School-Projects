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
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.classes.Cittadino;
import unidevteam.classes.DatiExtraCentroVaccinale;
import unidevteam.classes.EventoAvverso;
import unidevteam.enumerators.TipologiaCentroVaccinale;
import unidevteam.interfaces.CittadiniInterfaccia;
import unidevteam.util.DBManager;

/**
 * Classe per gestire le funzionalità del server per quanto riguarda i cittadini
 * Estende UnicastRemoteObject in quanto usiamo questa classe per creare il collegamento con l'RMI.
 * 
 * Implementa l'interfaccia remota {@link server.interfaces.CittadiniInterfaccia CittadiniInterfaccia}
 */
public class ServerCittadino extends UnicastRemoteObject implements CittadiniInterfaccia {
    static final int port = 2099;
    Registry registry;

    /**
     * Costruttore server cittadino
     * @throws RemoteException
     */
    public ServerCittadino() throws RemoteException {
        super();
        System.out.println("RMI Server Starting");
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server_cittadini", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch(ExportException e) {
            registry = LocateRegistry.getRegistry(port);
            registry.rebind("Server_cittadini", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ricerca di centri vaccinali per nome
     * @param nomeCentro valore nome centro inserito come ricerca
     * @return lista di oggetti CentroVaccinale con parametri che corrisponodo al valore ricercato
     * @throws RemoteException
     */
    @Override
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException {
        try {
            if(!nomeCentro.equals(""))
                return DBManager.getInstance().getCentriVaccinaliByNome(nomeCentro);
            else
                return DBManager.getInstance().getAllCentriVaccinali();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ricerca di centri vaccinali per comune e tipologia
     * @param comune 
     * @param tipologiaCentro
     * @return lista di oggetti CentroVaccinale con parametri che corrispono ai valori ricercati
     * @throws RemoteException
     */
    @Override
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro)
            throws RemoteException {
        try {
            if(!comune.equals("") && !tipologiaCentro.name().toUpperCase().equals(""))
                return DBManager.getInstance().getCentriVaccinaliByComuneETipologiaCentro(comune, tipologiaCentro);
            else
                return DBManager.getInstance().getAllCentriVaccinali();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Registrazione di un cittadino a sistema
     * @param cittadino 
     * @return esito dell'operazione di registrazione
     * @throws RemoteException
     */
    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        try {
            DBManager.getInstance().addCittadino(cittadino);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Inserimento di un evento avverso
     * @param eventoAvverso oggetto evento avverso che si desidera inserire
     * @param idVaccinazione id vaccinazione del cittadino che sta inserendo l'evento avverso
     * @param idCentro id del centro vaccinale in cui si sta inserendo l'evento avverso
     * @return esito dell'operazione di inserimento
     * @throws RemoteException
     */
    @Override
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().inserisciEventoAvverso(eventoAvverso, idVaccinazione, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Chiusura del registry RMI
     * @throws RemoteException
     */
    public void exit() throws RemoteException {
        try{
            registry.unbind("Server_cittadini");
            UnicastRemoteObject.unexportObject(this, true);
            System.out.println("RMI Server: exiting.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Autenticazione di un utente
     * @param email
     * @param plainPassword password in chiaro
     * @return oggetto cittadino con tutti i dati se l'autenticazione va a buon fine, null altrimenti
     */
    @Override
    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            return DBManager.getInstance().autenticaUtente(email, plainPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Controllo se l'id vaccinazione dato e' presente tra quelli di un centro vaccinale
     * @param idVaccinazione id vaccinazione da controllare
     * @param idCentro id centro vaccinale in cui controllare
     * @return esito dell'operazione di controllo
     */
    @Override
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().controlloVaccinatoInCentro(idVaccinazione, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ottenimento dei dati sugli eventi avversi di un dato centro vaccinale
     * @param idCentro id del centro vaccinale di cui si vogliono ottenere i dati riguardo gli eventi avversi
     * @return oggetto DatiExtraCentroVaccinale contenente le informazioni sugli eventi avversi registrati
     * @throws RemoteException
     */
    @Override
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().getDatiSuEventiAvversi(idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
