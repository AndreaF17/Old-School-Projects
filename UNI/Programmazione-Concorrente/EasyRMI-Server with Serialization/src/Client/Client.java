package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfaces.PersonInterface;
import Object.Person;

public class Client {
    public static void startMain() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            PersonInterface stub = (PersonInterface) registry.lookup("Hello");
            Person response = stub.getPerson("Andrea","Ferrario");
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
