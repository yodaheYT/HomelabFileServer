package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {
    public static UI ui = new UI();

    public static TCP tcp = new TCP();
    public static Socket socket = null;
    public static void main(String[] args) {
        ui.ConnectDialog();
        int cycle = 0;
        while (socket == null) {
            cycle++;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Connected successfully after " + cycle + " cycles.");

        UI.mainWindow();
    }
}