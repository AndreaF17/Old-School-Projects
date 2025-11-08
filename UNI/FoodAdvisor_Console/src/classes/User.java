package classes;

public class User {
    // Nome utente, 0
    private String nickname;
    // Nome, 1
    private String name;
    // Cognome, 2
    private String surname;
    // Comune di residenza, 3
    private String town;
    // Provincia di residenza, 4
    private String district;
    // Email, 5
    private String email;
    // Password, 6
    private String password;

    // GETTERS AND SETTERS
    public  String GetNickname() {return this.nickname;}
    public  String GetName()     {return this.name;    }
    public  String GetSurname()  {return this.surname; }
    public  String GetTown()     {return this.town;    }
    public  String GetDistrict() {return this.district;}
    public  String GetEmail()    {return this.email;   }
    private String GetPassword() {return this.password;}

    private void SetNickname(String nickname) {this.nickname = nickname;} 
    private void SetName    (String name)     {this.name     = name;    } 
    private void SetSurname (String surname)  {this.surname  = surname; } 
    private void SetTown    (String town)     {this.town     = town;    } 
    private void SetDistrict(String district) {this.district = district;} 
    private void SetEmail   (String email)    {this.email    = email;   } 
    private void SetPassword(String password) {this.password = password;}

    /**
     * Costruttore della classe User
     * @param nickname
     * @param name
     * @param surname
     * @param town
     * @param district
     * @param email
     * @param password */
    public User(String nickname, String name, String surname, String town, String district, String email, String password) {
        SetNickname(nickname);
        SetName    (name);
        SetSurname (surname);
        SetTown    (town);
        SetDistrict(district);
        SetEmail   (email);
        SetPassword(password);
    }

    /** Fornisce informazioni sull'istanza dell'oggetto
     * @return Stringa contenente i dati dell'oggetto */
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s\n",GetNickname(),GetName(),GetSurname(),GetTown(),GetDistrict(),GetEmail(),GetPassword());
    }
}