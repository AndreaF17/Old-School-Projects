/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.classes;

import java.io.Serializable;

import unidevteam.enumerators.QualificatoreIndirizzo;
import unidevteam.enumerators.TipologiaCentroVaccinale;

/**
 * Classe per la gestione del centro vaccinale
 * Possiede getter e setter per impostare i valori chiave della classe
 * Serializzabile in quanto utilizzata assieme all'RMI
 */
public class CentroVaccinale implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private String id;
    private String nome;
    private QualificatoreIndirizzo qualificatoreIndirizzo;
    private String nomeIndirizzo;
    private String numeroCivico;
    private String comune;
    private String provincia;
    private String CAP;
    private TipologiaCentroVaccinale tipologiaCentroVaccinale;

    /**
     * 
     * @param nome nome centro vaccinale
     * @param qualificatoreIndirizzo tipo di indirizzo {@link centrivaccinali.enumerators.QualificatoreIndirizzo#QualificatoreIndirizzo()}
     * @param nomeIndirizzo indirizzo
     * @param numeroCivico numero civico
     * @param comune 
     * @param provincia
     * @param cap
     * @param tipologiaCentroVaccinale {@link centrivaccinali.enumerators.TipologiaCentroVaccinale#TipologiaCentroVaccinale()}
     */
    public CentroVaccinale(String nome, QualificatoreIndirizzo qualificatoreIndirizzo, String nomeIndirizzo,
            String numeroCivico, String comune, String provincia, String cap, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        this.nome = nome;
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.numeroCivico = numeroCivico;
        this.comune = comune;
        this.provincia = provincia;
        this.CAP = cap;
        this.tipologiaCentroVaccinale = tipologiaCentroVaccinale;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
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
     * @return qualificatore indirizzo
     */
    public QualificatoreIndirizzo getQualificatoreIndirizzo() {
        return qualificatoreIndirizzo;
    }

    /**
     * @param qualificatoreIndirizzo
     */
    public void setQualificatoreIndirizzo(QualificatoreIndirizzo qualificatoreIndirizzo) {
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
    }

    /**
     * 
     * @return nome indirizzo
     */
    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    /**
     * 
     * @param nomeIndirizzo
     */
    public void setNomeIndirizzo(String nomeIndirizzo) {
        this.nomeIndirizzo = nomeIndirizzo;
    }

    /**
     * 
     * @return numero civico
     */
    public String getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * 
     * @param numeroCivico
     */
    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    /**
     * 
     * @return comune
     */
    public String getComune() {
        return comune;
    }

    /**
     * 
     * @param comune
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * 
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * 
     * @param provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * 
     * @return CAP
     */
    public String getCAP() {
        return CAP;
    }

    /**
     * 
     * @param cAP
     */
    public void setCAP(String cAP) {
        this.CAP = cAP;
    }

    /**
     * 
     * @return tipologia centro vaccinale {@link centrivaccinali.enumerators.TipologiaCentroVaccinale#TipologiaCentroVaccinale()}
     */
    public TipologiaCentroVaccinale getTipologiaCentroVaccinale() {
        return tipologiaCentroVaccinale;
    }

    /**
     * 
     * @param tipologiaCentroVaccinale
     */
    public void setTipologiaCentroVaccinale(TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        this.tipologiaCentroVaccinale = tipologiaCentroVaccinale;
    }

    @Override
    public String toString() {
        return this.nome + " Indirizzo: " + this.qualificatoreIndirizzo.getValue()  + " " + this.nomeIndirizzo + ", " +  this.numeroCivico + ", " + this.provincia + ", " + this.CAP;
    }

    
}
