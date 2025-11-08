/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.classes;

import java.io.Serializable;

import unidevteam.enumerators.TipoEvento;

/**
 * Serve per ottenere dati statistici dai centri vaccinali come per esempio:
 * <ul>
 * <li>Il numero degli eventi avversi</li>
 * <li>Il grado di severità medio</li>
 * <li>L'evento avverso più frequente</li>
 * </ul>
 * Possiede getter e setter per impostare i valori chiave della classe
 * Serializzabile in quanto utilizzata assieme all'RMI
 */
public class DatiExtraCentroVaccinale implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private int numeroEventiAvversi;
    private double gradoSeveritaMedio;
    private TipoEvento eventoPiuFrequente;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getNumeroEventiAvversi() {
        return numeroEventiAvversi;
    }
    
    public void setNumeroEventiAvversi(int numeroEventiAvversi) {
        this.numeroEventiAvversi = numeroEventiAvversi;
    }

    public double getGradoSeveritaMedio() {
        return gradoSeveritaMedio;
    }

    public void setGradoSeveritaMedio(double gradoSeveritaMedio) {
        this.gradoSeveritaMedio = gradoSeveritaMedio;
    }

    public TipoEvento getEventoPiuFrequente() {
        return eventoPiuFrequente;
    }

    public void setEventoPiuFrequente(TipoEvento eventoPiuFrequente) {
        this.eventoPiuFrequente = eventoPiuFrequente;
    }

    /**
     * @param numeroEventiAvversi
     * @param gradoSeveritaMedio
     * @param eventoPiuFrequente
     */
    public DatiExtraCentroVaccinale(int numeroEventiAvversi, double gradoSeveritaMedio, TipoEvento eventoPiuFrequente) {
        this.numeroEventiAvversi = numeroEventiAvversi;
        this.gradoSeveritaMedio = gradoSeveritaMedio;
        this.eventoPiuFrequente = eventoPiuFrequente;
    }

    @Override
    public String toString() {
        return "DatiExtraCentroVaccinale [eventoPiuFrequente=" + eventoPiuFrequente + ", gradoSeveritaMedio="
                + gradoSeveritaMedio + ", numeroEventiAvversi=" + numeroEventiAvversi + "]";
    }
}
