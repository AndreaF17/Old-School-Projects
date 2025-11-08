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
     * @param nome nome centro vaccinale
     * @param qualificatoreIndirizzo tipo di indirizzo {@link cittadini.enumerators.QualificatoreIndirizzo#QualificatoreIndirizzo() QualficatoreIndirizzo}
     * @param nomeIndirizzo indirizzo
     * @param numeroCivico numero civico
     * @param comune 
     * @param provincia
     * @param cap
     * @param tipologiaCentroVaccinale tipologia del centro vaccinale {@link cittadini.enumerators.TipologiaCentroVaccinale#TipologiaCentroVaccinale() TipologiaCentroVaccinale}
     */
    public CentroVaccinale(String id, String nome, QualificatoreIndirizzo qualificatoreIndirizzo, String nomeIndirizzo,
            String numeroCivico, String comune, String provincia, String cap, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        this.id = id;
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
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
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
     * 
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
     * @param CAP
     */
    public void setCAP(String cAP) {
        CAP = cAP;
    }

    /**
     * 
     * @return tipologia centro vaccinale
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
        return "CentroVaccinale [CAP=" + CAP + ", comune=" + comune + ", nome=" + nome + ", nomeIndirizzo="
                + nomeIndirizzo + ", numeroCivico=" + numeroCivico + ", qualificatoreIndirizzo="
                + qualificatoreIndirizzo + ", tipologiaCentroVaccinale=" + tipologiaCentroVaccinale + "]";
    }
}
