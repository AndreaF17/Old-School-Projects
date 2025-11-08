import java.net.*;
import java.io.*;

/**
 * Scritto in questo modo il server può ricevere al massimo una connessione
 * da parte di un client, per poi scollegarsi.
 * 
 * Per tenere collegato il server basta aprire un while(true) prima dell'accettazione
 * del server socket, chiudere il socket a fine ciclo e infine aggiungere una condizione
 * di uscita dal ciclo per evitare problemi legati a processi che continuano a runnare in background  
 */


public class Server {
    public static final int PORT = 9890;

    public Server() throws IOException, ClassNotFoundException {

        // Creazione server socket
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Server is up and running on port " + PORT);

        //Il server aspetta un collegamento da parte di un client
        Socket socket = serverSocket.accept();

        // IO
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Otteniamo i pacchetti per controllare se equivale a "Hello!"
        Packet recvPacket = (Packet) inStream.readObject();
        System.out.println("Received: " + recvPacket.getMessage());

        if (recvPacket.getMessage().equals("Hello!")) {
            Packet sendPacket = new Packet("Message obtained");
            outStream.writeObject(sendPacket);
        } else{

            // Else necessario, in quanto evita crush nella classe cliente, che si aspetta una risposta 
            Packet sendPacket = new Packet("Command not found");
            outStream.writeObject(sendPacket);
        }

        // Chiusura di socket e stream
        outStream.close();
        inStream.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server();
    }
}
