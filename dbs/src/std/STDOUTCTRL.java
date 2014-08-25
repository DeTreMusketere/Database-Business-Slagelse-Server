
package std;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class STDOUTCTRL {
    
    private static Controller instance;
    
    public static Controller getInstance() {
        if(instance != null) {
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
        
        private LinkedList<String> messages;
        private boolean running;
        private Thread th;
        
        public Controller() {
            messages = new LinkedList<>();
        }
        
        public void printMessage(String message) {
            synchronized (messages) {
                messages.addFirst(message);
                messages.notify();
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
            while(running) {
                synchronized (messages) {
                    while(messages.isEmpty()) {
                        try {
                            messages.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(STDOUTCTRL.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(messages.removeLast());
                    }
                }
            }
        }
        
    }

}
