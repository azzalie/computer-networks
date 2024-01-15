import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            HashMap<String, String> dict = new HashMap<String, String>();

            while (true) {
                Socket socket = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String message = reader.readLine();
                    if (message == null) break;

                    String[] string = message.split(" ");

                    if (string.length == 2 && string[0].equals("get")) {
                        String key = string[1];

                        if (dict.containsKey(key)) {
                            writer.write(dict.get(key) + "\n");

                        } else {
                            writer.write("Ключ не найден\n");
                        }

                    } else if (string.length == 3 && string[0].equals("put")) {
                        dict.put(string[1], string[2]);
                        writer.write("Сохранено\n");

                    } else {
                        writer.write("Ошибка ввода\n");
                    }
                    writer.flush();
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
