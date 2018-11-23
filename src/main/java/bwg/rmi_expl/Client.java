package bwg.rmi_expl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RPI Client
 * @author ssvs
 */
public class Client {
    private static String SERVER_IP    = "127.0.0.1";
    private static final String RMI_HOSTNAME = "java.rmi.server.hostname";
    private final static int SERVER_PORT = 27002;

    private Client() {}

    public static void main(String[] args) {
        //SERVER_IP = args.length > 0 ? args[0] : SERVER_IP;

        System.setProperty(RMI_HOSTNAME, SERVER_IP);
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_IP, SERVER_PORT);
            MessageInterface stub = (MessageInterface) registry.lookup("Messages");

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