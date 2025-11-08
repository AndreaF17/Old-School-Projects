import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class CounterServer {
    public static final int PORT = 8888;
    public static void main(String[] args) throws IOException {
        int _counter = 0;
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Started: " + serverSocket);
        while (true) {
            System.out.println("Waiting a connection...");
            Socket socket = serverSocket.accept();
            try {
            
                BufferedReader istream = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
                PrintWriter ostream = new PrintWriter(new
                BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream())), true);

                // ... Interpretazione dei comandi del client
                String myOper;
                while ((myOper = istream.readLine())!= null) {
                    if (myOper.equals("<incr>"))
                        _counter++;
                    else if (myOper.equals("<reset>"))
                        _counter=0;
                    else if (myOper.startsWith("<sum>")) {
                        StringTokenizer st = new StringTokenizer(myOper);
                        String op = st.nextToken();
                        String add = st.nextToken();
                        if (op.equals("<sum>")) {
                            _counter += Integer.parseInt(add);
                        }
                    } else {
                        System.out.println("operation not recognized: " + myOper);
                    }
                    ostream.println(_counter);
                }
            } finally {
            System.out.println("closing...");
            socket.close();
            }
        }
    }
}