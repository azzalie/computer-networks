import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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

                    DatagramPacket packetServer = getDatagramPacket(packetClient, b);
                    socket.send(packetServer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static DatagramPacket getDatagramPacket(DatagramPacket packetClient, byte[] b) {
        InetAddress address = packetClient.getAddress();
        int port = packetClient.getPort();
        int dataLen = packetClient.getLength();

        ByteArrayInputStream in = new ByteArrayInputStream(b, 0, dataLen);

        String msg = new String(packetClient.getData(), 0, packetClient.getLength());

        String out = "Received";
        String received = out + ": " + msg;

        byte[] buf = received.getBytes();

        return new DatagramPacket(buf, buf.length, address, port);
    }
}
