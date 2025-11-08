package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Object.Page;

public interface PageInterface extends Remote {
    Page getPage(String URL) throws RemoteException;
}
