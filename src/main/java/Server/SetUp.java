package Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetUp {
    public static void createFiles() {
        Path path = Paths.get("data/");
        if (Files.exists(path)) {
            System.out.println("/data exists");
        } else {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        path = Paths.get("data/logs/");
        if (Files.exists(path)) {
            System.out.println("/data/logs/ exists");
        } else {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createDataBase() {

    }
}
