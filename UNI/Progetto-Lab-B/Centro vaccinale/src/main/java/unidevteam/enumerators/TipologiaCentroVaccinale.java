/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.enumerators;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum per gestire la tipologia del centro vaccinale
 */
public enum TipologiaCentroVaccinale {
    OSPEDALIERO("Ospedaliero"),
    AZIENDALE("Aziendale"),
    HUB("HUB");
    
    private final String value;

    public String getValue() { return value; }

    TipologiaCentroVaccinale(String value) {
        this.value = value;
    }

    public static Optional<TipologiaCentroVaccinale> valueFromString(String value) {
        return Arrays.stream(values())
        .filter(q -> q.value == value)
        .findFirst();
    }

    public int getSize() {
        return values().length;
    }
}
