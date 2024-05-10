package Server.WebServer;

import java.io.*;
import java.net.*;
import java.nio.Buffer;

public class TCP {
    public ServerSocket serverSocket;
    public ServerSocket openSocket(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            serverSocket = ss;
            return ss;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendData(String data) {
        DataInputStream ds = null;
    }

    public void waitforconnection() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String input = in.readLine();
                if (input.equals("getFiles")) {
                    out.println("listing files");
                }
            } catch(IOException e) {
                System.out.println(e);
            }
        }
    }
}
