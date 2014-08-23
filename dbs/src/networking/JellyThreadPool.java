package networking;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class JellyThreadPool {

    private WorkerThread[] threads;
    private LinkedList<Runnable> taskQueue;

    public JellyThreadPool(int poolSize) {
        taskQueue = new LinkedList<>();
        threads = new WorkerThread[poolSize];
        buildThreads();
    }

    private void buildThreads() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void addToQueue(Runnable r) {
        synchronized (taskQueue) {
            taskQueue.addLast(r);
            taskQueue.notify();
        }
    }

    public int queueLength() {
        synchronized (taskQueue) {
            return taskQueue.size();
        }
    }

    private class WorkerThread extends Thread {

        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException ex) {
                        }
                    }
                    r = taskQueue.removeFirst();
                }
                try {
                    r.run();
                } catch (Exception ex) {
                }
            }
        }
    }

}
