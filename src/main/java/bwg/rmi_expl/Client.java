package bwg.rmi_expl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RPI Client
 * @author ssvs
 */
public class Client {

    private Client() {}

    public static void main(String[] args) {
        String localhost    = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        System.setProperty(RMI_HOSTNAME, localhost);
        try {
            Registry registry = LocateRegistry.getRegistry(localhost,27002);
            Message stub = (Message) registry.lookup("Messages");

            String response = stub.getTime();
            System.out.println("server response: " + response);

            response = stub.getDate();
            System.out.println("server response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}