package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JSONBuilder;

/**
 *
 * @author Patrick
 */
public class NetServer implements Runnable {

    private ServerSocket serverSocket;
    private Thread th;
    private boolean running;
    private JSONBuilder jsonBuilder;
    private int port;
    private Socket socket;
    private JellyThreadPool threadPool;

    public NetServer(int port, JSONBuilder jsonBuilder) {
        this.port = port;
        this.jsonBuilder = jsonBuilder;
    }

    public boolean getRunning() {
        return running;
    }

    public void startServer() {
        threadPool = new JellyThreadPool(1000);
        running = true;
        th = new Thread(this);
        th.start();

    }

    public void stopServer() {
        if (running) {
            running = false;
            th = null;
            System.out.println("Server stopped");
        } else {
            System.out.println("Server is already stopped");
        }
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(NetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (running) {
            try {
                System.out.println("Ready for next call");
                System.out.println("");
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + " called in");
                SocketReceiver sr = new SocketReceiver(socket, jsonBuilder);
                threadPool.addToQueue(sr);
            } catch (IOException ex) {
                Logger.getLogger(NetServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
