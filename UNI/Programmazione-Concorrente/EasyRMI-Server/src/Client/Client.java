package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfaces.Hello;

public class Client {
    public static void startMain() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
