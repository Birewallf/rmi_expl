package bwg.rmi_expl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
    String getTime() throws RemoteException;
    String getDate() throws RemoteException;
}