package bwg.rpc_expl;

import java.net.InetAddress;
import java.net.Socket;

/**
 * RPC Client
 * @author ssvs
 */
public class Client {
    private static String SERVER_IP = "127.0.0.1";
    private final static int SERVER_PORT = 27003;

    public static void main(String args[]) {
        SERVER_IP = args.length > 0 ? args[0] : SERVER_IP;

        //------GET TIME---------------------------------------------------------------
        System.out.println("server response: " + Sender("time"));

        //------GET DATE---------------------------------------------------------------
        System.out.println("server response: " + Sender("date"));

        //------GET SUM---------------------------------------------------------------
        System.out.println("server response: " + Sender("sum 5 15"));

        //------GET MIN---------------------------------------------------------------
        System.out.println("server response: " + Sender("min 27 15"));

        //------GET MUL---------------------------------------------------------------
        System.out.println("server response: " + Sender("mul 5 10"));

        //------GET DIV---------------------------------------------------------------
        System.out.println("server response: " + Sender("div 60 12"));
    }

    private static String Sender(String msg) {
        System.out.println("\n send msg: " + msg);

        String data;
        try {
            Socket socket = new Socket(
                    InetAddress.getByName(SERVER_IP),
                    SERVER_PORT);

            socket.getOutputStream().write(msg.getBytes());
            byte buf[] = new byte[1024];
            int r = socket.getInputStream().read(buf);
            data = new String(buf, 0, r);
            socket.close();
        } catch (Exception ignore) {
            //ignore.printStackTrace();
            return "error!";
        }
        return data;
    }
}