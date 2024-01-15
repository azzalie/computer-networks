import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadingServer {
    private static final int MAX_CLIENTS = 3;
    private static final int a = 0;
    private static final int b = 999999999;
    private static int connectedClients = 0;

    public static void main(String[] args) {
        int portNumber = 8888;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Сервер запущен на порту " + portNumber);

            while (connectedClients < MAX_CLIENTS) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress().getHostAddress());

//                if (connectedClients < MAX_CLIENTS) {
//                    int clientNumber = connectedClients + 1;
//                    int h = (b - a) / MAX_CLIENTS; // Рассчитываем размер интервала
//                    int start = a + (clientNumber - 1) * h;
//                    int end = (clientNumber < MAX_CLIENTS) ? (a + clientNumber * h - 1) : b;
//
//                    System.out.println("Клиент " + clientNumber + " подключился. Номер: " + clientNumber + ", Интервал: [" + start + ", " + end + "]");
//                    connectedClients++;
//
//                    // Создаем новый поток для обработки клиента
//                    Thread clientThread = new Thread(new ClientHandler(clientSocket, clientNumber, start, end));
//                    clientThread.start();
//                } else {
//                    // Если превышено максимальное количество клиентов
//                    System.out.println("Клиент без номера и интервала подключился.");
//
//                    // Уведомляем клиента, что интервал не выделен
//                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                    out.println("0,0,0");
//
//                    // Закрываем соединение с клиентом
//                    clientSocket.close();
//                }
                int clientNumber = connectedClients + 1;
                int h = (b - a) / MAX_CLIENTS;
                int start = a + (clientNumber - 1) * h;
                int end = (clientNumber < MAX_CLIENTS) ? (a + clientNumber * h - 1) : b;

                System.out.println("Клиент " + clientNumber + " подключился. Номер: " + clientNumber + ", Интервал: [" + start + ", " + end + "]");
                connectedClients++;

                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientNumber, start, end));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
