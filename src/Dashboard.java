import javax.swing.*;
import java.util.List;

public class Dashboard {
    public void launch() {
        JFrame frame = new JFrame("SecurePass - Dashboard");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel websiteLabel = new JLabel("Website:");
        websiteLabel.setBounds(30, 20, 80, 25);
        frame.add(websiteLabel);

        JTextField websiteField = new JTextField();
        websiteField.setBounds(110, 20, 200, 25);
        frame.add(websiteField);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 60, 80, 25);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(110, 60, 200, 25);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 100, 80, 25);
        frame.add(passLabel);

        JTextField passField = new JTextField();
        passField.setBounds(110, 100, 200, 25);
        frame.add(passField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(330, 60, 100, 25);
        frame.add(saveBtn);

        JTextArea displayArea = new JTextArea();
        displayArea.setBounds(30, 150, 400, 180);
        displayArea.setEditable(false);
        frame.add(displayArea);

        saveBtn.addActionListener(e -> {
            try {
                PasswordManager.save(websiteField.getText(), userField.getText(), passField.getText());
                displayEntries(displayArea);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving data.");
            }
        });

        displayEntries(displayArea);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void displayEntries(JTextArea area) {
        try {
            List<String[]> all = PasswordManager.readAll();
            StringBuilder sb = new StringBuilder("Saved Credentials:\n");
            for (String[] entry : all) {
                sb.append("Site: ").append(entry[0]).append(", User: ").append(entry[1]).append(", Pass: ").append(entry[2]).append("\n");
            }
            area.setText(sb.toString());
        } catch (Exception e) {
            area.setText("Error reading entries.");
        }
    }
}
