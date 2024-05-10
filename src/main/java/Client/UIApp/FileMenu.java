package Client.UIApp;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FileMenu extends JPanel {
    public GridBagConstraints gbc;
    public FileMenu() {
        super();
        this.setBorder(new CompoundBorder(new TitledBorder("Files"), new EmptyBorder(4, 4, 4, 4)));
        this.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
    }
}
