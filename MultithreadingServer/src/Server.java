import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int MAX_CLIENTS = 3;
    private static final int a = 100000000;
    private static final int b = 400000000;
    private static int connectedClients = 0;

    public static void main(String[] args) {
        int portNumber = 8888;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Сервер запущен на порту " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress().getHostAddress());

                if (connectedClients < MAX_CLIENTS) {
                    int clientNumber = connectedClients + 1;
                    int h = (b - a) / MAX_CLIENTS;
                    int start = a + (clientNumber - 1) * h;
                    int end = (clientNumber < MAX_CLIENTS) ? (a + clientNumber * h - 1) : b;

                    System.out.println("Клиент " + clientNumber + " подключился. Номер: " + clientNumber + ", Интервал: [" + start + ", " + end + "]");
                    connectedClients++;

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println(clientNumber + "," + start + "," + end);

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine = in.readLine();
                    while (inputLine != null) {
                        System.out.println("От клиента " + clientNumber + ": " + inputLine);
                        inputLine = in.readLine();
                    }
                } else {
                    System.out.println("Клиент без номера и интервала подключился.");

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("0,0,0");

                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
