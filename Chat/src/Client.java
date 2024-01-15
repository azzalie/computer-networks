import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static final int PORT = 8888;

    private static void sendMessage(DatagramSocket socket, String name, int type, String message) throws IOException {
        byte[] nameBytes = name.getBytes();
        byte[] messageBytes = message.getBytes();
        byte[] array;

        if (type == 2) {
            array = new byte[3 + nameBytes.length + messageBytes.length];
            array[2 + nameBytes.length] = (byte) messageBytes.length;
            System.arraycopy(messageBytes, 0, array, 3 + nameBytes.length, messageBytes.length);
        } else {
            array = new byte[2 + nameBytes.length];
        }

        array[0] = (byte) type;
        array[1] = (byte) nameBytes.length;
        System.arraycopy(nameBytes, 0, array, 2, nameBytes.length);

        DatagramPacket packetClient = new DatagramPacket(array, array.length, InetAddress.getByName("255.255.255.255"), PORT);
        socket.send(packetClient);
    }

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            Scanner scanner = new Scanner(System.in);

            String name = null;
            boolean inChat = false;

            while (true) {
                if (!inChat) {
                    System.out.println("0 - войти в чат");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 0) {
                        System.out.println("Имя:");
                        name = scanner.nextLine();
                        sendMessage(socket, name, type, "");
                        inChat = true;
                    }
//                    } else if (type == 1) {
//                        if (name != null) {
//                            sendMessage(socket, name, type, "");
//                            name = null;
//                        }
//                    }
                } else {
                    System.out.println("2 - отправить сообщение");
                    System.out.println("1 - покинуть чат");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 2) {
                        System.out.println("Сообщение:");
                        String message;
                        while (true) {
                            message = scanner.nextLine();
                            if (message.equals("1")) {
                                sendMessage(socket, name, 1, "");
                                break;
                            } else {
                                sendMessage(socket, name, 2, message);
                            }
                        }
                        inChat = false;
                    } else if (type == 1) {
                        sendMessage(socket, name, type, "");
                        inChat = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
