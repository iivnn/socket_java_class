package socket.exercise.serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ivan
 */
public class Server {

    private final int PORT;
    private final ServerSocket SERVER_SOCKET;

    public Server(int PORT) throws IOException {
        this.PORT = PORT;
        this.SERVER_SOCKET = new ServerSocket(PORT);
    }

    public Socket listen() throws IOException {
        return SERVER_SOCKET.accept();
    }

}
