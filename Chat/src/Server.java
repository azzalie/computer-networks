import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Server {
    public static final int PORT = 8888;
    public static final int MAX_LENGTH = 1500;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            byte[] b = new byte[MAX_LENGTH];

            while (true) {
                try {
                    DatagramPacket packetClient = new DatagramPacket(b, b.length);
                    socket.receive(packetClient);

                    byte[] data = packetClient.getData();

                    int type = data[0];
                    int nameLength = data[1];
                    String name = new String(data, 2, nameLength, StandardCharsets.UTF_8);

                    if (type == 0) {
                        System.out.println(name + " вошел в чат");

                    } else if (type == 1) {
                        System.out.println(name + " вышел из чата");

                    } else if (type == 2) {
                        int messageLength = Byte.toUnsignedInt(data[2 + nameLength]);
                        String msg = new String(data, 3 + nameLength, messageLength, StandardCharsets.UTF_8);
                        System.out.println(name + ": " + msg);

                    } else {
                        System.out.println("Ошибка");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
