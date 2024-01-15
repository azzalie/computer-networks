public class Starter {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> Server.main(args));
        serverThread.start();

        Thread clientThread = new Thread(() -> Client.main(args));
        clientThread.start();
    }
}
