import javax.swing.*;

public class LoginScreen {
    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("SecurePass Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 20, 80, 25);
        frame.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(110, 20, 150, 25);
        frame.add(username);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 60, 80, 25);
        frame.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(110, 60, 150, 25);
        frame.add(password);

        JButton login = new JButton("Login");
        login.setBounds(30, 100, 100, 25);
        frame.add(login);

        JButton register = new JButton("Register");
        register.setBounds(160, 100, 100, 25);
        frame.add(register);

        login.addActionListener(e -> {
            if (UserStore.login(username.getText(), new String(password.getPassword()))) {
                frame.dispose();
                new Dashboard().launch();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        register.addActionListener(e -> {
            boolean success = UserStore.register(username.getText(), new String(password.getPassword()));
            JOptionPane.showMessageDialog(frame, success ? "Registered!" : "User already exists.");
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
