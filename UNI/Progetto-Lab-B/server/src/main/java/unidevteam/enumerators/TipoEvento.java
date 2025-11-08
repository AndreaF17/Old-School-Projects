/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.enumerators;

/**
 * Enum per gestire il tipo di evento
 */
public enum TipoEvento {
    MAL_DI_TESTA("Mal di testa"),
    FEBBRE("Febbre"),
    DOLORI_MUSCOLARI_ARTICOLARI("Dolori muscolari articolari"),
    LINFOADENOPATIA("Linfoadenopatia"),
    TACHICARDIA("Tachicardia"),
    CRISI_IPERTENSIVA("Crisi ipertensiva");

    private final String value;

    public String getValue() { return value; }

    TipoEvento(String value) {
        this.value = value;
    }

    public static TipoEvento valueFromString(String value) {
        switch(value) {
            case "Mal di testa":
            case "MAL_DI_TESTA":
                return TipoEvento.MAL_DI_TESTA;

            case "Febbre":
            case "FEBBRE":
                return TipoEvento.FEBBRE;
            
            case "Dolori muscolari articolari":
            case "DOLORI_MUSCOLARI_ARTICOLARI":
                return TipoEvento.DOLORI_MUSCOLARI_ARTICOLARI;

            case "Linfoadenopatia":
            case "LINFOADENOPATIA":
                return TipoEvento.LINFOADENOPATIA;

            case "Tachicardia":
            case "TACHICARDIA":
                return TipoEvento.TACHICARDIA;

            case "Crisi ipertensiva":
            case "CRISI_IPERTENSIVA":
                return TipoEvento.CRISI_IPERTENSIVA;
        }

        return null;
    }

    public int getSize() {
        return values().length;
    }
}