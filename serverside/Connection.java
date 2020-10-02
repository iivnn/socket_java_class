package socket.exercise.serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ivan
 */
public class Connection implements Runnable {

    private final Socket SOCKET;
    private final DataOutputStream OUT;
    private final DataInputStream IN;
    public static final String CLOSE = "close();";

    public Connection(Socket socket) throws IOException {
        this.SOCKET = socket;
        this.OUT = new DataOutputStream(socket.getOutputStream());
        this.IN = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        String string;
        System.out.println("New connection with:" + SOCKET.getRemoteSocketAddress());
        while (!(SOCKET.isClosed())) {
            try {
                string = IN.readUTF();
                System.out.println("Message Received: \"" + string + "\" from:" + SOCKET.getRemoteSocketAddress());
                if (string.equals(CLOSE)) {
                    System.out.println("Connection Closed with:" + SOCKET.getRemoteSocketAddress());
                    stop();
                } else {
                    OUT.writeUTF("Message Received: " + string);
                }
            } catch (IOException ex) {
                System.err.println("erro: " + ex.toString());
                stop();
            }
        }
    }

    private void stop() {
        try {
            OUT.close();
            IN.close();
            SOCKET.close();
        } catch (IOException ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

}
