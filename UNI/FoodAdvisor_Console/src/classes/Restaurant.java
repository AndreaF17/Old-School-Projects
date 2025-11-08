package classes;

import java.net.URL;

public class Restaurant {
    public enum TypeRestaurant{
        INSTANCE(4), Italiano(0),Etnico(1), Fusion(2);
        private final int value;
       // GETTER
        public int GetValue(){return value;}
        /** 
         * Costruttore dell' enum TypeRestaurant
         * @param value */
        private TypeRestaurant(int value){this.value=value;}
    }
    private int id;
    private String name;
    private Address address;
    private long phone_number;
    private URL website;
    private TypeRestaurant type;

    // GETTERS AND SETTERS
    public int            GetId         () {return this.id;          }
    public String         GetName       () {return this.name;        }
    public Address        GetAddress    () {return this.address;     }
    public Long           GetPhoneNumber() {return this.phone_number;}
    public URL            GetURL        () {return this.website;     }
    public TypeRestaurant GetType       () {return this.type;        }

    private void SetTypeId     (int id             ) {this.id           = id;          }
    private void SetName       (String name        ) {this.name         = name;        }
    private void SetAddress    (Address address    ) {this.address      = address;     }
    private void SetPhoneNumber(long phone_number  ) {this.phone_number = phone_number;}
    private void SetURL        (URL website        ) {this.website      = website;     }
    private void SetType       (TypeRestaurant type) {this.type         = type;        }
 
    /**
     * Costruttore della classe Address
     * @param id
     * @param name
     * @param address
     * @param phone_number
     * @param website
     * @param type */
    public Restaurant(int id, String name, Address address, long phone_number, URL website, TypeRestaurant type){
        SetTypeId(id);
        SetName(name);
        SetAddress(address);
        SetPhoneNumber(phone_number);
        SetURL(website);
        SetType(type);
    }
     /** Fornisce informazioni sull'istanza dell'oggetto
     * @return Stringa contenente i dati dell'oggetto */
    public String toString() {
        return String.format("%d|%s|%s|%d|%s|%s\n", GetId(), GetName(), GetAddress().toString(), GetPhoneNumber(), GetURL(), GetType());
    }
}