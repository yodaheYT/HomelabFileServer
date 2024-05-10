package Server.WebServer;

import Server.API.Users.LogIn;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import Server.API.Users.CreateUser;

public class WebServer {
    public void start() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(2069), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.createContext("/api/createUser", new CreateUser());
        server.createContext("/api/login", new LogIn());
        server.createContext("/static", new StaticServer());

        server.setExecutor(null);
        server.start();
    }
}
