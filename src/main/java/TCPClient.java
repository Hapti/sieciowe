import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

class TCPClient {

    private static final String HOST = "localhost";
    private static final int PORT = 6789;

    public static void main(String argv[]) throws Exception {
        String request;
        String response;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket clientSocket = new Socket(HOST, PORT);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            try {
                while(true) {
                    request = inFromUser.readLine();
                    outToServer.writeBytes(request + '\n');
                    response = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + response);
                }
            } catch(IOException e){
                System.out.println("IOException caught!");
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }
        } catch (ConnectException e){
            System.out.println("Could not establish connection to: " + HOST + ":" + PORT);
        }
    }
}