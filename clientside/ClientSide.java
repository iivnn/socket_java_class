package socket.exercise.socket_java_class.clientside;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ivan
 */
public class ClientSide {

    private static Client connection;
    private static Scanner scanner;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String message;
        try {
            connection = new Client("127.0.0.1", 3333);
            while (connection.isConnected()) {
                message = scanner.nextLine();
                if (message.equals(Client.CLOSE)) {
                    connection.send(message);
                    System.out.println("Disconected");
                    break;
                } else {
                    connection.send(message);
                    System.out.println(connection.receive());
                }
            }
        } catch (IOException ex) {
            System.err.println("Connection failed: " + ex);
        }
    }
}
