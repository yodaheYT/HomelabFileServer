package Server.Logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHandler {
    public FileWriter logFile;

    public LogHandler() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime now = LocalDateTime.now();
        try {
            logFile = new FileWriter("data/logs/"+dtf.format(now) + " - Log.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void debug(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String payload = "[" + dtf.format(now) + "] [DEBUG] " + message;
        String ANSIPayload = AnsiHandler.InsertAnsi(AnsiHandler.ANSI_CYAN, AnsiHandler.NONE, payload);
        System.out.println(ANSIPayload);
        try {
            logFile.write(payload + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void info(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String payload = "[" + dtf.format(now) + "] [INFO] " + message;
        String ANSIPayload = AnsiHandler.InsertAnsi(AnsiHandler.ANSI_BLUE, AnsiHandler.NONE, payload);
        System.out.println(ANSIPayload);
        try {
            logFile.write(payload + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void warning(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String payload = "[" + dtf.format(now) + "] [WARNING] " + message;
        String ANSIPayload = AnsiHandler.InsertAnsi(AnsiHandler.ANSI_YELLOW, AnsiHandler.NONE, payload);
        System.out.println(ANSIPayload);
        try {
            logFile.write(payload + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void error(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String payload = "[" + dtf.format(now) + "] [ERROR] " + message;
        String ANSIPayload = AnsiHandler.InsertAnsi(AnsiHandler.ANSI_RED, AnsiHandler.NONE, payload);
        System.out.println(ANSIPayload);
        try {
            logFile.write(payload + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
