import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 8888;

        try (Socket socket = new Socket(serverAddress, portNumber);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String serverMessage = in.readLine();
            System.out.println("Получено от сервера: " + serverMessage);

            String[] parts = serverMessage.split(",");
            int clientNumber = Integer.parseInt(parts[0].trim());
            int start = Integer.parseInt(parts[1].trim());
            int end = Integer.parseInt(parts[2].trim());

            System.out.println("Я клиент " + clientNumber + ". Мой интервал: [" + start + ", " + end + "]");

            for (int i = start; i <= end; i++) {
                if (i % (11 * 13 * 17 * 19) == 0) {
                    System.out.println("Найдено число: " + i);

                    out.println(i);
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
