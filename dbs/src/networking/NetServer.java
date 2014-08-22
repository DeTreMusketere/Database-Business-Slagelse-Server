package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JSONBuilder;

/**
 *
 * @author Patrick
 */
public class NetServer implements Runnable {

    private ServerSocket serverSocket;
    private ExecutorService es;
    private Thread th;
    private boolean running;
    private JSONBuilder jsonBuilder;

    public NetServer(int port, JSONBuilder jsonBuilder) throws IOException {
        this.jsonBuilder = jsonBuilder;
        es = Executors.newCachedThreadPool();
        serverSocket = new ServerSocket(port);
    }

    public boolean getRunning() {
        return running;
    }

    public void startServer() {
        running = true;
        th = new Thread(this);
        th.start();

    }

    public void stopServer() {
        if (running) {
            running = false;
            th = null;
            System.out.println("># Server stopped");
        } else {
            System.out.println("># Server is already stopped");
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("># Ready for next call");
                Socket socket = serverSocket.accept();
                System.out.println(">#" + socket.getInetAddress() + " called in");
                SocketReceiver sr = new SocketReceiver(socket, jsonBuilder);
                es.submit(sr);
            } catch (IOException ex) {
                Logger.getLogger(NetServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
