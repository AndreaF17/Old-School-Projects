/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.enumerators;

import java.util.*;

/**
 * Enum per gestire i qualificatori di indirizzo
 */
public enum QualificatoreIndirizzo {
    VIA("Via"),
    VIALE("Viale"),
    PIAZZA("Piazza");

    private final String value;

    public String getValue() { return value; }

    QualificatoreIndirizzo(String value) {
        this.value = value;
    }

    public static Optional<QualificatoreIndirizzo> valueFromString(String value) {
        return Arrays.stream(values())
        .filter(q -> q.value == value)
        .findFirst();
    }

    public int getSize() {
        return values().length;
    }
}