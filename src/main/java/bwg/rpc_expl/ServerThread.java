package bwg.rpc_expl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * RPC Server
 * @author ssvs
 */
class ServerThread extends Thread {
    private Socket socket;

    public static void main(String[] ar) throws IOException {
        ServerSocket ss = new ServerSocket(27003);
        System.err.println("Server ready \nPort: 27003");
        while (true) {
            new ServerThread(ss.accept());
        }
    }

    private ServerThread(Socket socket){
        this.socket = socket;
        this.start();
    }

    public void run() {
        try {
            InputStream is = this.socket.getInputStream();
            OutputStream os = this.socket.getOutputStream();

            byte buf[] = new byte[1024];
            int r = is.read(buf);
            String data = new String(buf, 0, r);

            System.out.println("connect: " + socket.getRemoteSocketAddress() + "\n    receive : " + data);

            switch (data) {
                case "time": {
                    data = getTime();
                    break;
                }
                case "date": {
                    data = getDate();
                    break;
                }
                default: {
                    if (data.startsWith("sum")) {
                        data = sum(data);
                    } else if (data.startsWith("mul")) {
                        data = mul(data);
                    } else if (data.startsWith("div")) {
                        data = div(data);
                    } else if (data.startsWith("min")) {
                        data = min(data);
                    } else
                        data = "n/d";
                    break;
                }
            }

            System.out.println("    send: " + data + "\n---------------");

            os.write(data.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String sum(String data) {
        String[] splits = data.split(" ");
        try {
            return String.valueOf(Integer.parseInt(splits[1]) + Integer.parseInt(splits[2]));
        } catch (Exception ignore) {
            return "0";
        }
    }

    private String min(String data) {
        String[] splits = data.split(" ");
        try {
            return String.valueOf(Integer.parseInt(splits[1]) - Integer.parseInt(splits[2]));
        } catch (Exception ignore) {
            return "0";
        }
    }
    private String div(String data) {
        String[] splits = data.split(" ");
        try {
            return String.valueOf(Integer.parseInt(splits[1]) / Double.parseDouble(splits[2]));
        } catch (Exception ignore) {
            return "0";
        }
    }
    private String mul(String data) {
        String[] splits = data.split(" ");
        try {
            return String.valueOf(Integer.parseInt(splits[1]) * Integer.parseInt(splits[2]));
        } catch (Exception ignore) {
            return "0";
        }
    }

    private String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    private String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(cal.getTime());
    }
}