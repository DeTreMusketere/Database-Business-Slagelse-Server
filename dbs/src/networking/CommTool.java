package networking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Patrick
 */
public class CommTool {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    
    public CommTool(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Sends a message
     * @param message 
     */
    public void sendMessage(String message) {
        writer.println(message);
    }
    
    /**
     * Receives a message as a string
     * @return message (Will be null if nothing received)
     */
    public String receiveStringMessage() {
        String answer = null;
        try {
            answer = reader.readLine();
        } catch (IOException ex) {
            System.out.println("># An error occurred while trying to receive a message from " + socket.getInetAddress());
        }
        return answer;
    }


}
