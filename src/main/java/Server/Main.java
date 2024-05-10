package Server;

import Server.Auth.Hash;
import Server.Database.SQLite;
import Server.Logging.LogHandler;
import Server.WebServer.TCP;
import Server.WebServer.WebServer;

import java.io.IOException;
import java.util.Set;

public class Main {
    public static WebServer webServer;
    public static LogHandler logHandler;
    public static SQLite sqLite;
    public static TCP tcp;
    public static void main(String[] args) {
        SetUp.createFiles();
        SetUp.createDataBase();
        webServer = new WebServer();
        logHandler = new LogHandler();
        sqLite = new SQLite();
        tcp = new TCP();

        webServer.start();
        sqLite.connect();
        sqLite.initApp();
        tcp.openSocket(19235);
        tcp.waitforconnection();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logHandler.info("Closing Program");
            try {
                logHandler.logFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "Shutdown-thread"));
    }
}