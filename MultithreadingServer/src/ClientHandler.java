import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final int clientNumber;
    private final int start;
    private final int end;

    public ClientHandler(Socket clientSocket, int clientNumber, int start, int end) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(clientNumber + "," + start + "," + end);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                // System.out.println("От клиента " + clientNumber + ": " + inputLine);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
