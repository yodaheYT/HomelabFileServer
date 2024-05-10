package Client.UIApp;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharesMenu extends JPanel {
    public GridBagConstraints gbc;
    public Map<JButton, String> shares = new HashMap<JButton, String>();
    public void AddShare(String name, String path) {
        JButton newJP = new JButton(name);

        shares.put(newJP, path);
    }
    public SharesMenu() {
        super();
        this.setBorder(new CompoundBorder(new TitledBorder("Shares"), new EmptyBorder(4, 4, 4, 4)));
        this.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
    }
}
