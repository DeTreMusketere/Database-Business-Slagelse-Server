
package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class NetServer implements Runnable {
    
    private ServerSocket serverSocket;
    private ExecutorService es;
    private Thread th;
    private boolean running;
    
    public NetServer(int port) throws IOException {
        es = Executors.newCachedThreadPool();
        serverSocket = new ServerSocket(port);
    }
    
    public void startServer() {
        running = true;
        th = new Thread(this);
        th.start();
        
    }
    
    public void stopServer() {
        running = false;
        th = null;
    }

    @Override
    public void run() {
        while(running) {
            try {
                System.out.println("># Ready for next call");
                Socket socket = serverSocket.accept();
                System.out.println(">#" + socket.getInetAddress() + " called in");
                SocketReceiver sr = new SocketReceiver(socket);
                es.submit(sr);
            } catch (IOException ex) {
                Logger.getLogger(NetServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
