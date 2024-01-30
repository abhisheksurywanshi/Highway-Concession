package toll.tcm.windowsapp;
import javax.swing.*;

import toll.tcm.Database.DatabaseConnectivity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SimpleLoginApp extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> lanesComboBox;

    public SimpleLoginApp() throws SQLException {
        // Set up the frame
        setTitle("Simple Login App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        ArrayList<String> lanes = new ArrayList<String>();
        lanes = DatabaseConnectivity.getAllLanes();
        String[] lanesArray = new String[lanes.size()];
        lanesArray = lanes.toArray(new String[lanes.size()]);

        lanesComboBox = new JComboBox<String>(lanesArray);
        JButton loginButton = new JButton("Login");
        JButton generatePOMButton = new JButton("Generate POM");
        JButton runMavenInstallButton = new JButton("Run Maven Install");

        // Set layout manager to GridBagLayout for more flexibility
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        // Add components to the frame with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Lanes:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(lanesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(generatePOMButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(runMavenInstallButton, gbc);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked();
            }
        });

        generatePOMButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePOMButtonClicked();
            }
        });

        runMavenInstallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runMavenInstallButtonClicked();
            }
        });
    }

    private void loginButtonClicked() {
        // Get the entered username and password
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String selectedLane = (String) lanesComboBox.getSelectedItem();

        // For demonstration purposes, print the credentials
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Selected Lane: " + selectedLane);

        // Clear the fields after printing (optional)
        usernameField.setText("");
        passwordField.setText("");
        lanesComboBox.setSelectedIndex(0);
    }

    private void generatePOMButtonClicked() {
        try {
            generatePOMFile();
            System.out.println("POM file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error generating POM file.");
        }
    }

    private void runMavenInstallButtonClicked() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("mvn", "install");
            processBuilder.directory(new File(System.getProperty("user.dir"+"pom.xml"))); // Set the working directory
            Process process = processBuilder.start();
            process.waitFor();
            int exitCode = process.exitValue();
            System.out.println("Maven install completed with exit code: " + exitCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generatePOMFile() throws IOException {
        // Get the project directory
        String projectDir = System.getProperty("user.dir");

        // Create POM content
        String pomContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <groupId>your.groupId</groupId>\n" +
                "    <artifactId>your-artifactId</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>1.5</maven.compiler.source>\n" +
                "        <maven.compiler.target>1.5</maven.compiler.target>\n" +
                "    </properties>\n" +
                "</project>";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(projectDir, "pom.xml")));
            writer.write(pomContent);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SimpleLoginApp().setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
