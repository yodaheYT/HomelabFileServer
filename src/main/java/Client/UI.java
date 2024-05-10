package Client;

import Client.UIApp.FileMenu;
import Client.UIApp.MainWindow;
import Client.UIApp.SharesMenu;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Client.Main.socket;
import static Client.Main.tcp;

public class UI {
    public UI() {

    }

    public static void ConnectDialog() {
        JFrame frame = new JFrame("Connect to HLFS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./Icon.png").getImage());
        JLabel label1 = new JLabel("Please enter the following details to start.");

        JTextField host = new JTextField("127.0.0.1       ", 16);
        JTextField port = new JTextField("19235", 5);

        JPanel Bottom = new JPanel();
        Bottom.setLayout(new FlowLayout());

        JButton start = new JButton("Connect");

        Bottom.add(start);

        frame.getContentPane().add(BorderLayout.PAGE_START, label1);
        frame.getContentPane().add(BorderLayout.LINE_START, host);
        frame.getContentPane().add(BorderLayout.CENTER, port);
        frame.getContentPane().add(BorderLayout.PAGE_END, Bottom);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 125);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //JOptionPane.showMessageDialog(null, "Welcome to Homelab File Server. \nThis Application allows you to connect to any device running the HLFS server application. Please enter the following details to start.", "Welcome!", JOptionPane.INFORMATION_MESSAGE);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String HOST = host.getText().replaceAll("\\s+", "");
                String USE_HOST = HOST == null
                        ? "127.0.0.1"
                        : HOST;
                String PORT = port.getText().replaceAll("\\s+", "");
                String USE_PORT = PORT == null
                        ? "19235"
                        : PORT;
                socket = tcp.openSocket(USE_HOST, USE_PORT);
                frame.dispose();
            }
        });
    }
    public static void mainWindow() {
        MainWindow frame = new MainWindow();

        SharesMenu sharesMenu = new SharesMenu();
        FileMenu fileMenu = new FileMenu();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        frame.setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.gridx = 0;
        frame.getContentPane().add(sharesMenu, gridBagConstraints);
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.gridx = 1;
        frame.getContentPane().add(fileMenu, gridBagConstraints);

        frame.provoke();
        frame.visible();
    }
}
