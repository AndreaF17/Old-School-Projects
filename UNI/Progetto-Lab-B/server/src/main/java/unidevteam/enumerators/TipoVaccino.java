/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.enumerators;

/**
 * Enum per gestire i tipi di vaccino
 */
public enum TipoVaccino {
    PFIZER("Pfizer"),
    ASTRAZENECA("Astrazeneca"),
    MODERNA("Moderna"),
    J_AND_J("Johnson&Johnson");

    private final String value;

    public String getValue() { return value; }

    TipoVaccino(String value) {
        this.value = value;
    }

    public static TipoVaccino valueFromString(String value) {
        /*return Arrays.stream(values())
        .filter(q -> q.value == value)
        .findFirst();*/

        switch(value) {
            case "PFIZER": return TipoVaccino.PFIZER;
            case "ASTRAZENECA": return TipoVaccino.ASTRAZENECA;
            case "MODERNA": return TipoVaccino.MODERNA;
            case "JOHNSON&JOHNSON": return TipoVaccino.J_AND_J;
        }

        return null;
    }

    public int getSize() {
        return values().length;
    }
}
