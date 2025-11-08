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

/**
 * Interfaccia remota per RMI
 * Gestisce quali metodi possono essere lanciati remotamente dal client
 */
public interface CittadiniInterfaccia extends Remote {
    
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException;
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro) throws RemoteException;
    // public ? visualizzaInfoCentroVaccinale(String idCentro) throws RemoteException;
    public Cittadino autenticaUtente(String email, String plainPassword) throws RemoteException;
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException;
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) throws RemoteException;
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) throws RemoteException;
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) throws RemoteException;
}
