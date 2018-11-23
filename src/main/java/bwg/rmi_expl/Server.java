package bwg.rmi_expl;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * RPI Server
 * @author ssvs
 */
public class Server implements MessageInterface {

    private final static int SERVER_PORT = 27002;

    private Server() {}

    /**
     * функция получения времени
     */
    public String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        print("Server", "Get Time - " + time);
        return time;
    }

    /**
     * функция получения даты
     */
    public String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(cal.getTime());

        print("Server", "Get Time - " + date);
        return date;
    }

    public static void main(String args[]) {
        String localhost    = "127.0.0.1";
        //String localhost    = "10.0.0.63";
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        System.setProperty(RMI_HOSTNAME, localhost);
        //System.setSecurityManager(new RMISecurityManager());
        try {
            Server obj = new Server();
            MessageInterface stub = (MessageInterface) UnicastRemoteObject.exportObject(obj, 0);

            // создание реестра обьектов
            LocateRegistry.createRegistry(SERVER_PORT);
            Registry registry = LocateRegistry.getRegistry(localhost, SERVER_PORT);
            // регистрация обьекта в реестре
            registry.rebind("Messages", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private void print(String tag, String msg) {
        System.out.println(tag + ":" + msg);
    }
}
