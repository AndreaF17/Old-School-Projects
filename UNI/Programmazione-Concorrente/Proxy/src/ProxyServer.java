import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.ServerInterface;

public class ProxyServer implements ServerInterface {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // connessione:
    
    public ProxyServer() throws Exception {
        InetAddress addr = InetAddress.getByName(null);
        System.out.println("addr = " + addr);
        socket = new Socket(addr, ServerInterface.PORT);
        // crea gli stream di input/output
        System.out.println("socket = " + socket);
        in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(
        new OutputStreamWriter(socket.getOutputStream())), true);
        }

        public int sum(int s) throws IOException {
            out.println("<sum> "+Integer.toString(s));
            String strResult = in.readLine();
            return Integer.parseInt(strResult);
        }

        public int reset() throws IOException {
            out.println("<reset>");
            String strResult = in.readLine();
            return Integer.parseInt(strResult);
        }

        public int increment() throws IOException {
            out.println("<incr>");
            String strResult = in.readLine();
            return Integer.parseInt(strResult);
        }

        public void close() throws IOException {
            System.out.println("closing...");
            out.println("<end>");
            socket.close();
        }
}
