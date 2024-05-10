package Client.UIApp;

import javax.swing.*;

import static Client.Main.tcp;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("HLFS - " + tcp.host + ":" + tcp.port);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("./Icon.png").getImage());
    }

    public void provoke() {
        this.pack();
    }

    public void visible() {
        this.setVisible(true);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
    }
}
