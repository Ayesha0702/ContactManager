import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ContactManager {

    // Contact model
    static class Contact {
        private final int id;
        private final String name;
        private final String phone;
        private final String email;

        public Contact(int id, String name, String phone, String email) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        @Override
        public String toString() {
            return String.format("%d: %s | %s | %s", id, name, phone, email);
        }
    }

    // Database operations
    static class DatabaseHelper {
        private static final String URL = "jdbc:mysql://localhost:3306/contact_db";
        private static final String USER = "root";
        private static final String PASSWORD = "Ayesha@2005";

        public static void addContact(String name, String phone, String email) {
            String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Add contact error: " + e.getMessage());
            }
        }

        public static List<Contact> getAllContacts() {
            List<Contact> contacts = new ArrayList<>();
            String sql = "SELECT * FROM contacts";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    contacts.add(new Contact(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email")
                    ));
                }
            } catch (SQLException e) {
                System.err.println("Get contacts error: " + e.getMessage());
            }
            return contacts;
        }

        public static void deleteContact(int id) {
            String sql = "DELETE FROM contacts WHERE id = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Delete contact error: " + e.getMessage());
            }
        }
    }

    // GUI
    static class ContactManagerGUI {
        private final JFrame frame;
        private final JTextField nameField, phoneField, emailField, deleteIdField;
        private final JTextArea displayArea;

        public ContactManagerGUI() {
            frame = new JFrame("Contact Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(new FlowLayout());

            nameField = new JTextField(20);
            phoneField = new JTextField(20);
            emailField = new JTextField(20);
            deleteIdField = new JTextField(5);
            displayArea = new JTextArea(15, 40);
            displayArea.setEditable(false);

            JButton addBtn = new JButton("Add Contact");
            JButton viewBtn = new JButton("View Contacts");
            JButton deleteBtn = new JButton("Delete Contact");

            frame.add(new JLabel("Name:"));
            frame.add(nameField);
            frame.add(new JLabel("Phone:"));
            frame.add(phoneField);
            frame.add(new JLabel("Email:"));
            frame.add(emailField);
            frame.add(addBtn);

            frame.add(new JLabel("Delete ID:"));
            frame.add(deleteIdField);
            frame.add(deleteBtn);

            frame.add(viewBtn);
            frame.add(new JScrollPane(displayArea));

            addBtn.addActionListener(e -> {
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    DatabaseHelper.addContact(name, phone, email);
                    JOptionPane.showMessageDialog(frame, "Contact added.");
                    nameField.setText("");
                    phoneField.setText("");
                    emailField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "All fields are required.");
                }
            });

            viewBtn.addActionListener(e -> {
                List<Contact> contacts = DatabaseHelper.getAllContacts();
                displayArea.setText("");
                for (Contact c : contacts) {
                    displayArea.append(c + "\n");
                }
            });

            deleteBtn.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(deleteIdField.getText().trim());
                    DatabaseHelper.deleteContact(id);
                    JOptionPane.showMessageDialog(frame, "Contact deleted.");
                    deleteIdField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID.");
                }
            });

            frame.setVisible(true);
        }
    }

    // ✅ Main Method (Only ONE!)
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            return;
        }

        SwingUtilities.invokeLater(ContactManagerGUI::new);
    }
}

