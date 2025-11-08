package classes;



public class Address {
    public enum TypeAddress{
        INSTANCE(4),Via(0), Piazza(1);
        private final int value;
        // GETTER
        public int GetValue() {return value;}
        /**
         * Costruttore della classe Address
         * @param value */
        private TypeAddress(int value) {this.value = value;}
    }

    // Tipo, 0
    private TypeAddress type;
    // Nome, 1
    private String name;
    // numero civico, 2
    private int street_number;
    // Citta, 3
    private String town;
    // Provincia, 4
    private String district;
    // CAP, 5
    private int zip_code;

    // GETTERS AND SETTERS
    public TypeAddress GetType ()        {return this.type;         }
    public String      GetStreetName  () {return this.name;         }
    public int         GetStreetNumber() {return this.street_number;}
    public String      GetTownName    () {return this.town;         }
    public String      GetDistrict    () {return this.district;     }
    public int         GetZipCode     () {return this.zip_code;     }

    private void SetTypeAddress (TypeAddress  type) {this.type = type;                  }
    private void SetStreetName  (String       name) {this.name = name;                  }
    private void SetStreetNumber(int street_number) {this.street_number = street_number;} 
    private void SetTownName    (String       town) {this.town = town;                  }
    private void SetDistrict    (String   district) {this.district = district;          }
    private void SetZipCode     (int      zip_code) {this.zip_code = zip_code;          }

    /**
     * Costruttore della classe Address
     * @param type
     * @param name
     * @param street_number
     * @param town
     * @param district
     * @param zip_code */
    public Address(TypeAddress type, String name, int street_number, String town, String district, int zip_code) {
        SetTypeAddress (type);
        SetStreetName  (name);
        SetStreetNumber(street_number);
        SetTownName    (town);
        SetDistrict    (district);
        SetZipCode     (zip_code);
    }
    

    /** Fornisce informazioni sull'istanza dell'oggetto
     * @return Stringa contenente i dati dell'oggetto */
    public String toString() {
        return String.format("%s|%s|%d|%s|%s|%d",GetType(),GetStreetName(),GetStreetNumber(),GetTownName(),GetDistrict(),GetZipCode());
    }
}

