package socket.exercise.socket_java_class.clientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ivan
 */
public class Client {

    private final Socket SOCKET;
    private final String IP_ADRESS;
    private final int PORT;
    private final DataOutputStream OUT;
    private final DataInputStream IN;
    public static final String CLOSE = "close();";

    public Client(String IP_ADRESS, int PORT) throws IOException {
        this.IP_ADRESS = IP_ADRESS;
        this.PORT = PORT;
        this.SOCKET = new Socket(IP_ADRESS, PORT);
        this.OUT = new DataOutputStream(this.SOCKET.getOutputStream());
        this.IN = new DataInputStream(this.SOCKET.getInputStream());
    }

    public void send(String message) throws IOException {
        OUT.writeUTF(message);
    }

    public String receive() throws IOException {
        return IN.readUTF();
    }

    public void close() throws IOException {
        OUT.writeUTF(CLOSE);
        OUT.close();
        IN.close();
        SOCKET.close();
    }

    public boolean isConnected() {
        return !this.SOCKET.isClosed();
    }

}
