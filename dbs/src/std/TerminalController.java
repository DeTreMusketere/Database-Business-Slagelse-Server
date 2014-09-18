package std;

import db.DBTool;
import dbs.Dbs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import networking.NetServer;

/**
 *
 * @author Patrick
 */
public class TerminalController implements Runnable {

    private Thread th;
    private boolean running;
    private NetServer netServer;
    private Dbs dbs;
    private boolean autostart;
    private Properties prop;

    public TerminalController(Dbs dbs, NetServer netServer) {
        this.dbs = dbs;
        this.netServer = netServer;
        getConfig();
        if (prop.containsKey("autostart")) {
            autostart = Boolean.valueOf(prop.getProperty("autostart"));
        } else {
            prop.setProperty("autostart", "false");
            autostart = Boolean.valueOf(prop.getProperty("autostart"));
        }
    }

    private void getConfig() {
        String FILENAME = "config.properties"; // Indikates where config file is
        prop = new Properties(); // Creates a properties type object
        FileInputStream in = null;

        try {
            in = new FileInputStream(FILENAME); // Creates a fileinputstream and loads the config file
            prop.load(in); // Loads the config file into our properties object (to hold the config in memory to use)
            in.close(); // closes the fileinputstream (we dont need anymore)
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), FILENAME + " was not found", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void saveConfig() {
        OutputStream out = null;
        try {
            File f = new File("config.properties");
            out = new FileOutputStream(f);
            prop.store(out, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TerminalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TerminalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(TerminalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setAutostart(boolean value) {
        autostart = value;
        prop.setProperty("autostart", String.valueOf(value));
        saveConfig();
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
        if (autostart) {
            System.out.println("Starting NetServer...");
            netServer.startServer();
            sleep();
        }
        System.out.println(Dbs.TITLE + " - v" + Dbs.VERSION);
        System.out.println("Welcome\nWrite a command, or dont, i dont care...");
        while (running) {
            Scanner scan = new Scanner(System.in, "ISO-8859-1");
            System.out.print("># ");
            String command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "exit":
                    System.out.println("");
                    System.out.println("Server exiting...");
                    if (netServer.getRunning()) {
                        netServer.stopServer();
                    }
                    System.out.println("");
                    sleep();
                    System.exit(0);
                    break;
                case "stop":
                    System.out.println("Stopping NetServer...");
                    netServer.stopServer();
                    sleep();
                    break;
                case "start":
                    System.out.println("Starting NetServer...");
                    netServer.startServer();
                    sleep();
                    break;
                case "status":
                    dbs.status();
                    sleep();
                    break;
                case "help":
                    System.out.println("========================================================================================================================================");
                    System.out.println("status          Server status");
                    System.out.println("reload          Reloads server with data from database. Be carefull with this command. If the registers are in use, an error will occur!");
                    System.out.println("create          Create a new object");
                    System.out.println("show            Show an existing object");
                    System.out.println("setautostart    Sets if the NetServer should autostart on server program run");
                    System.out.println("start           Starts the NetServer");
                    System.out.println("stop            Stops the NetServer");
                    System.out.println("exit            exits the server program");
                    System.out.println("========================================================================================================================================");
                    sleep();
                    break;
                case "setautostart":
                    boolean finish = false;
                    while (!finish) {
                        System.out.print("Enable autostart? [y/n] ># ");
                        command = scan.nextLine();
                        switch (command.toLowerCase()) {
                            case "y":
                                setAutostart(true);
                                System.out.println("Autostart was set to true");
                                finish = true;
                                break;
                            case "n":
                                setAutostart(false);
                                System.out.println("Autostart was set to false");
                                finish = true;
                                break;
                            default:
                                System.out.println("That was a stupid answer, try again...");
                                break;
                        }
                    }
                    break;
                case "reload":
                    dbs.getPictureRegister().load();
                    dbs.getDealerRegister().load();
                    dbs.getStoreRegister().load();
                    dbs.getProductRegister().load();
                    dbs.getSaleRegister().load();
                    dbs.getUserRegister().load();
                    dbs.getTagRegister().load();
                    dbs.getUpdateNumberHandler().reload();
                    DBTool.close();
                    System.out.println("Registers reloaded");
                    break;
                case "create":
                    CreateObjects.create(scan, dbs);
                    break;
                case "show":
                    ShowObjects.show(scan, dbs);
                    break;
                case "":
                    break;
                default:
                    System.out.println("I dont know what that means\nWrite 'help' to get a list of commands");
                    sleep();
                    break;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(TerminalController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
