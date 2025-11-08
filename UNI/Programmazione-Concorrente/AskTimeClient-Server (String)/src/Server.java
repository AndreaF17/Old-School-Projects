import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread{
    private ServerSocket serverSocket;

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                System.out.println("Waiting for client on port " + 
                   serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                
                System.out.println(in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                   + "\n Time: \n " + dateNow());
                server.close();
                
             } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
             } catch (IOException e) {
                e.printStackTrace();
                break;
             }
          }
    }

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on IP: " + serverSocket.getLocalSocketAddress() + " On Port: " + port);
    }

    public String dateNow() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
    public static void main(String[] args) {
        int port = 1234;
      try {
         Thread t = new Server(port);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
    }
}
