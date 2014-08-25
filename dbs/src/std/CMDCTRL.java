package std;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class CMDCTRL {

    private static Controller instance;

    public static Controller getInstance() {
        if (instance != null) {
            return instance;
        } else {
            makeInstance();
            return instance;
        }
    }

    private static void makeInstance() {
        instance = new Controller();
    }

    public static class Controller implements Runnable {

        private LinkedList<String> commands;
        private boolean running;
        private Thread th;
        private Scanner scan;
        
        public Controller() {
            commands = new LinkedList<>();
            scan = new Scanner(System.in);
        }
        
        public void executeCommand(String command) {
            synchronized (commands) {
                commands.addFirst(command);
                commands.notify();
            }
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
            while (running) {
                synchronized (commands) {
                    while(commands.isEmpty()) {
                        try {
                            commands.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CMDCTRL.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        doCommand(commands.removeLast());
                    }
                }
            }
        }
        
        private void doCommand(String command) {
            switch(command) {
                default:
                    System.out.println("WHAT?");
                    break;
            }
        }

    }

}
