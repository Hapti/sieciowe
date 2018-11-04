import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("bump");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("bump2");

        BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        while (true) {
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            outToClient.writeBytes(clientSentence + "\n");
        }
    }
}