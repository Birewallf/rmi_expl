package bwg.rmi_expl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RPC Interface
 * @author ssvs
 */

public interface MessageInterface extends Remote {
    String getTime() throws RemoteException;
    String getDate() throws RemoteException;
    int sum(int a, int b) throws RemoteException;;
    int sub(int a, int b) throws RemoteException;;
    int mul(int a, int b) throws RemoteException;;
    double div(int a, int b) throws RemoteException;;

}