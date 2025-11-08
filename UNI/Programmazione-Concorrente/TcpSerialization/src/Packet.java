import java.io.Serializable;

public class Packet implements Serializable {

    private String message;

    public Packet(String message){
        this.message = message;
    }

    // Getter (usiamo il costruttore come setter quindi non necessario)
    public String getMessage() {
        return message;
    }

}
