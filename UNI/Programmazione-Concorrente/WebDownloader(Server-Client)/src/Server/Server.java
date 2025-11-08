package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Object.Page;
import interfaces.PageInterface;

public class Server extends UnicastRemoteObject implements PageInterface{
    static final int port = 1099; 
    
    public Server() throws RemoteException {
        super();
    }

    public static void startMain() {
        System.out.println("Service Starting");
        try {
            Server obj = new Server();
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind("Hello", obj);
            System.out.println("Server ready! On port: " + port);
        } catch (Exception e) {
            System.err.println("Exception: ");
            e.printStackTrace();
        }
    }

    @Override
    public Page getPage(String URL) throws RemoteException {
        return null;
    }
    

    
}
