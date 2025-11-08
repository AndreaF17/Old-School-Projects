import java.io.*;
import java.net.*;

public class Client {
    public Client() throws Exception {
        Socket socket = new Socket("127.0.0.1", Server.PORT);   // Connessione a server

        // IO
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Creazione pacchetti da mandare / ricevere
        Packet packet1 = new Packet("Hello");
        outStream.writeObject(packet1);

        Packet recvPacket = (Packet) inStream.readObject();
        System.out.println(recvPacket.getMessage());

        // Chiusura di socket e stream
        outStream.close();
        inStream.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        new Client();
    }
}
