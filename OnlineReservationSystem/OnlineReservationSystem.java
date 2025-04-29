import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.awt.GridLayout;
import java.awt.FlowLayout;



public class OnlineReservationSystem {
    private static Map<String, String> users = new HashMap<>();
    private static List<String> reservations = new ArrayList<>();

    public static void main(String[] args) {
        users.put("admin", "admin123"); // Default user
        showLoginForm();
    }

    private static void showLoginForm() {
        JFrame frame = new JFrame("Login");
        JLabel l1 = new JLabel("Username:");
        JLabel l2 = new JLabel("Password:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        l1.setBounds(30, 30, 100, 30);
        userField.setBounds(140, 30, 150, 30);
        l2.setBounds(30, 80, 100, 30);
        passField.setBounds(140, 80, 150, 30);
        loginBtn.setBounds(100, 130, 100, 30);

        frame.add(l1); frame.add(userField);
        frame.add(l2); frame.add(passField);
        frame.add(loginBtn);

        frame.setLayout(null);
        frame.setSize(350, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (users.containsKey(user) && users.get(user).equals(pass)) {
                frame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials");
            }
        });
    }

    private static void showMainMenu() {
        JFrame menu = new JFrame("Online Reservation System");
        JButton reserveBtn = new JButton("Reserve Ticket");
        JButton cancelBtn = new JButton("Cancel Ticket");

        reserveBtn.setBounds(50, 50, 200, 40);
        cancelBtn.setBounds(50, 110, 200, 40);

        menu.add(reserveBtn);
        menu.add(cancelBtn);
        menu.setSize(300, 250);
        menu.setLayout(null);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        reserveBtn.addActionListener(e -> showReservationForm());
        cancelBtn.addActionListener(e -> showCancellationForm());
    }

    private static void showReservationForm() {
        JFrame form = new JFrame("Reservation Form");
        JTextField nameField = new JTextField();
        JTextField trainNumberField = new JTextField();
        JComboBox<String> trainNameBox = new JComboBox<>(new String[]{"Express", "Intercity", "Rajdhani"});
        JComboBox<String> classBox = new JComboBox<>(new String[]{"Sleeper", "AC", "General"});
        JTextField dateField = new JTextField();
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JButton insertBtn = new JButton("Insert");

        form.setLayout(new GridLayout(8, 2));
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Train Number:"));
        form.add(trainNumberField);
        form.add(new JLabel("Train Name:"));
        form.add(trainNameBox);
        form.add(new JLabel("Class Type:"));
        form.add(classBox);
        form.add(new JLabel("Date of Journey (dd/mm/yyyy):"));
        form.add(dateField);
        form.add(new JLabel("From:"));
        form.add(fromField);
        form.add(new JLabel("To:"));
        form.add(toField);
        form.add(new JLabel(""));
        form.add(insertBtn);

        form.setSize(400, 300);
        form.setVisible(true);

        insertBtn.addActionListener(e -> {
            String pnr = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String data = "PNR: " + pnr + ", Name: " + nameField.getText() + ", Train No: " + trainNumberField.getText();
            reservations.add(pnr + "|" + data);
            JOptionPane.showMessageDialog(form, "Reservation Successful! PNR: " + pnr);
            form.dispose();
        });
    }

    private static void showCancellationForm() {
        JFrame frame = new JFrame("Cancel Ticket");
        JLabel label = new JLabel("Enter PNR Number:");
        JTextField pnrField = new JTextField();
        JButton okBtn = new JButton("OK");

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(pnrField);
        frame.add(okBtn);

        frame.setSize(300, 150);
        frame.setVisible(true);

        okBtn.addActionListener(e -> {
            String pnrInput = pnrField.getText().toUpperCase();
            boolean found = false;

            for (String record : reservations) {
                if (record.startsWith(pnrInput)) {
                    found = true;
                    int confirm = JOptionPane.showConfirmDialog(frame, "Reservation Found:\n" + record.split("\\|")[1] + "\nDo you want to cancel?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        reservations.remove(record);
                        JOptionPane.showMessageDialog(frame, "Reservation Cancelled");
                    }
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(frame, "PNR not found");
            }

            frame.dispose();
        });
    }
}
