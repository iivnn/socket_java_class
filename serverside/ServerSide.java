package socket.exercise.serverside;

import java.io.IOException;

/**
 *
 * @author ivan
 */
public class ServerSide {

    private static Server server;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            server = new Server(3333);
            while(true){
                Thread thread = new Thread(new Connection(server.listen()));
                thread.start();
            }      
        } catch (IOException ex) {
            System.err.println("erro: " + ex);
        }
    }

}
