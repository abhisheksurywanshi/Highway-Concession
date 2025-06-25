package toll.tcm.windowsapp;
import javax.swing.*;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import toll.tcm.Database.DatabaseConnectivity;
import toll.tcm.testCases.BaseClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleLoginApp extends BaseClass {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> lanesComboBox;
    
    public SimpleLoginApp() throws SQLException {
        // Set up the frame
        setTitle("Simple Login App");
        setSize(400, 300);
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

        

        JButton runTestButton = new JButton("Run Maven Project");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(runTestButton, gbc);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked();
            }
        });

       
        runTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runPom_xmlClicked();
            }
        });
       
    }
    public void runPom_xmlClicked()
    {
    	String projectDirectory = System.getProperty("user.dir");

        // Specify the goals to execute (e.g., clean install)
        String[] goalsArray = {"clean", "install"};

        // Create an instance of MavenInvoker
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(projectDirectory, "pom.xml"));
        request.setGoals(Arrays.asList(goalsArray));

        Invoker invoker = new DefaultInvoker();

        // Run Maven
        try {
            InvocationResult result = invoker.execute(request);

            // Check the result
            if (result.getExitCode() == 0) {
                System.out.println("Maven build succeeded.");
            } else {
                System.err.println("Maven build failed. Exit code: " + result.getExitCode());
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
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