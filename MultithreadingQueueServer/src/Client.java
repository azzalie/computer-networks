import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 8080;

        while (true) {
            try (Socket socket = new Socket(serverAddress, portNumber);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String serverMessage = in.readLine();

                if (serverMessage == null) {
                    System.out.println("Сервер отключен. Завершаем работу клиента.");
                    break;
                }

                System.out.println("Получено от сервера: " + serverMessage);

                String[] parts = serverMessage.split(",");
                int clientNumber = Integer.parseInt(parts[0].trim());
                int start = Integer.parseInt(parts[1].trim());
                int end = Integer.parseInt(parts[2].trim());

                System.out.println("Я клиент " + clientNumber + ". Мой интервал: [" + start + ", " + end + "]");

                for (int i = start; i <= end; i++) {
                    if (i % (11 * 13 * 17 * 19) == 0) {
                        System.out.println(i);
//                        out.println(i);
                    }
                }

                out.println("DONE");

            } catch (SocketException e) {
                System.out.println("Сервер отключен. Завершаем работу клиента.");
                break;
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
