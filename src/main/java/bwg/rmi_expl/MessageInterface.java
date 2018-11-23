package bwg.rmi_expl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RPI Server
 * @author ssvs
 */

public interface MessageInterface extends Remote {
    String getTime() throws RemoteException;
    String getDate() throws RemoteException;
}