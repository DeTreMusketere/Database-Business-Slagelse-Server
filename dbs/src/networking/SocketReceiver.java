package networking;


import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.UpdateNumberHandler;

/**
 *
 * @author Patrick
 */
public class SocketReceiver implements Runnable {

    private Socket socket;
    private String order;
    private CommTool comm;

    public SocketReceiver(Socket socket) {
        this.socket = socket;
        try {
            comm = new CommTool(socket);
        } catch (IOException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        order = comm.receiveMessage();

        switch (order) {
            case "updateme":
                int un = UpdateNumberHandler.getUpdateNumber();
                comm.sendMessage(String.valueOf(un));
                close();
                break;
            default:
                comm.sendMessage("Not implementet yet");
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
