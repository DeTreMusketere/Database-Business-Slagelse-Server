package std;

import dbs.Dbs;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.NetServer;

/**
 *
 * @author Patrick
 */
public class STDController implements Runnable {

    private Thread th;
    private boolean running;
    private NetServer netServer;
    private Dbs dbs;

    public STDController(Dbs dbs, NetServer netServer) {
        this.dbs = dbs;
        this.netServer = netServer;
    }

    public void start() {
        running = true;
        th = new Thread(this);
        th.start();
    }

    public void stop() {
        running = false;
        th = null;
    }

    @Override
    public void run() {
        System.out.println(Dbs.TITLE + " - v" + Dbs.VERSION);
        System.out.println("Welcome\nWrite a command, or dont, i dont care...");
        System.out.println("");
        while (running) {
            Scanner scan = new Scanner(System.in);
            System.out.print("># ");
            String command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "stop":
                    System.out.println("Stopping server...");
                    netServer.stopServer();
                    sleep();
                    System.exit(0);
                    break;
                case "start":
                    System.out.println("");
                    System.out.println("Starting server...");
                    netServer.startServer();
                    sleep();
                    break;
                case "status":
                    dbs.status();
                    sleep();
                    break;
                case "help":
                    System.out.println("");
                    System.out.println("status  Server status");
                    System.out.println("start   Starts the NetServer");
                    System.out.println("stop    Stops the NetServer and kills this process");
                    System.out.println("");
                    sleep();
                    break;
                case "":
                    break;
                default:
                    System.out.println("I dont know what that means\nWrite 'help' to get a list of commands");
                    System.out.println("");
                    sleep();
                    break;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(STDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
