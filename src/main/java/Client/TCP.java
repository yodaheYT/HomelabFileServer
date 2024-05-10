package Client;

import java.io.*;
import java.net.Socket;

public class TCP {
    public String host;
    public String port;
    public Socket socket;
    public Socket openSocket(String hostT, String portT) {
        host = hostT;
        port = portT;
        try {
            socket = new Socket(hostT, Integer.parseInt(portT));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return socket;
    }

    public String sendReq(String data) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println(data);
        String resp = in.readLine();
        System.out.println("Response: " + resp);
        return resp;
    }
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
