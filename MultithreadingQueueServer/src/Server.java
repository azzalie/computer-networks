import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Сервер запущен на порту 8080");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int connectedClients = 0;

        while (true) {
            System.out.print("Введите значения a, b и step: ");
            String input = scanner.nextLine();

            String[] inputParts = input.split(" ");
            if (inputParts.length != 3) {
                System.out.println("Некорректный ввод.");
                continue;
            }

            int a = Integer.parseInt(inputParts[0]);
            int b = Integer.parseInt(inputParts[1]);
            int step = Integer.parseInt(inputParts[2]);

            List<Task> tasks = createTasks(a, b, step);

            while (!tasks.isEmpty()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Клиент подключился: " + clientSocket.getInetAddress().getHostAddress());

                    connectedClients++;

                    Task clientTask = tasks.removeFirst();

                    System.out.println("Клиент: " + connectedClients + ", таск: [" +
                            clientTask.getStart() + ", " + clientTask.getEnd() + "]");

                    Runnable clientHandler = new ClientHandler(clientSocket, connectedClients, clientTask);
                    executorService.execute(clientHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Все задачи разосланы.");
        }
    }

    private static List<Task> createTasks(int a, int b, int step) {
        List<Task> tasks = new ArrayList<>();

        int h = (b - a) / step;

        for (int i = 0; i < step; i++) {
            int start = a + i * h;
            int end = (i < step - 1) ? (a + (i + 1) * h - 1) : b;
            tasks.add(new Task(start, end));
        }

        return tasks;
    }
}
