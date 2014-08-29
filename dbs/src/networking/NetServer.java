package networking;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
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

    /**
     * Starts the server in a new thread. Does nothing if it is already running.
     */
    public void startServer() {
        if (!running) {
            try {
                serverSocket = new ServerSocket(port);
                threadPool = new JellyThreadPool(1000);
                running = true;
                th = new Thread(this);
                th.start();
            } catch (BindException e) {
                System.out.println("");
                System.out.println("Port " + port + " is already in use");
                System.out.println("");
            } catch (IOException ex) {
            }

        } else {
            System.out.println("");
            System.out.println("NetServer is already running");
            System.out.println("");
        }

    }

    /**
     * Stops the server. Does nothing if it isn't running.
     */
    public void stopServer() {
        if (running) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
            }
            running = false;
            th = null;
            System.out.println("Server stopped");
        } else {
            System.out.println("Server is already stopped\nDid you look for 'exit'?");
        }
    }

    @Override
    public void run() {

        while (running) {
            try {
                System.out.println("Ready for next call");
                System.out.println("");
                socket = serverSocket.accept();
                System.out.println("");
                System.out.println(socket.getInetAddress() + " called in");
                System.out.println("");
                SocketReceiver sr = new SocketReceiver(socket, jsonBuilder);
                threadPool.addToQueue(sr);
            } catch (IOException ex) {
            }
        }
    }

}
