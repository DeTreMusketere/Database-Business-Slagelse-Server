package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JSONBuilder;
import model.data.UpdateNumberHandler;
import org.json.JSONArray;

/**
 *
 * @author Patrick
 */
public class SocketReceiver implements Runnable {

    private Socket socket;
    private String order;
    private CommTool comm;
    private JSONBuilder jsonBuilder;

    public SocketReceiver(Socket socket, JSONBuilder jsonBuilder) {
        this.jsonBuilder = jsonBuilder;
        this.socket = socket;
        try {
            comm = new CommTool(socket);
        } catch (IOException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        order = comm.receiveStringMessage();
        switch (order) {
            case "getun":
                System.out.println("Client wants currents updatenumber, sending it");
                int un = UpdateNumberHandler.getUpdateNumber();
                comm.sendMessage(String.valueOf(un));
                close();
                break;
            case "getobjs":
                System.out.println("Client wants objects, sending confirm");
                comm.sendMessage("ok");
                int receiveUpdateNumber = Integer.valueOf(comm.receiveStringMessage());
                System.out.println("Received updateNumber, sending objects");
                JSONArray array = jsonBuilder.buildJSONArray(receiveUpdateNumber);
                comm.sendMessage(array.toString());
                close();
                break;
            default:
                System.out.println("Client is saying weird shit");
                close();
                break;
        }

    }

    private void close() {
        try {
            System.out.println("># Saying bye to " + socket.getInetAddress());
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
