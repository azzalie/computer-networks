import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static final int PORT = 8888;
    public static final int MAX_LENGTH = 1500;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = getDatagramSocket();

            byte[] bufResponse = new byte[MAX_LENGTH];

            DatagramPacket responsePacket = new DatagramPacket(bufResponse, bufResponse.length);
            socket.receive(responsePacket);

            String received = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(received);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DatagramSocket getDatagramSocket() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        Scanner in = new Scanner(System.in);

        String name = "User";
        String msg = in.nextLine();

        byte[] nameByte = name.getBytes();
        byte[] msgByte = msg.getBytes();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        out.writeBytes(msgByte);

        byte[] buf = out.toByteArray();

        DatagramPacket packetClient = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PORT);
        socket.send(packetClient);
        return socket;
    }
}
