package Object;

import java.io.Serializable;

public class Person implements Serializable {
    // Version UID
    private static final long serialVersioUID = 1;

    // Attributes:
    private String firstName;
    private String lastName;

    // Constructors:
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter & Setters:
    
    public static long getSerialversiouid() {
        return serialVersioUID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
