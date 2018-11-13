package bwg.rmi_expl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * RPI Server
 * @author ssvs
 */
public class Server implements Message {

    private Server() {}

    public String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        print("Server", "Get Time - " + time);
        return time;
    }
    public String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(cal.getTime());

        print("Server", "Get Time - " + date);
        return date;
    }
    public static void main(String args[]) {
        String localhost    = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        System.setProperty(RMI_HOSTNAME, localhost);
        try {
            Server obj = new Server();
            Message stub = (Message) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            LocateRegistry.createRegistry(27002);
            Registry registry = LocateRegistry.getRegistry(localhost, 27002);
            registry.rebind("Messages", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private void print(String tag, String msg) {
        System.out.print(tag + ":" + msg);
    }
}
