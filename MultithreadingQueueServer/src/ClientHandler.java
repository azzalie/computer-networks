import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final int clientNumber;
    private final Task task;

    public ClientHandler(Socket clientSocket, int clientNumber, Task task) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        this.task = task;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(clientNumber + "," + task.getStart() + "," + task.getEnd());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("От клиента " + clientNumber + ": " + inputLine);

                if (inputLine.equals("DONE")) {
                    break;
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
