package Interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Object.Person;

public interface PersonInterface extends Remote {
    Person getPerson(String firstName, String lastName) throws RemoteException;
}