package toll.tcm.APIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportGeneratorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Report Generator App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JPanel to hold components
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        // Create a JTextField for date input
        final JTextField dateField = new JTextField();
        panel.add(dateField, BorderLayout.CENTER);

        // Create a JButton to run the report
        JButton runReportButton = new JButton("Run Report");
        runReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = dateField.getText();
                // Call the GetMinAndLaneWaiseReport class with the selected date
//                GetMinAndLaneWaiseTrafficReport.runReport(selectedDate);
            }
        });
        panel.add(runReportButton, BorderLayout.SOUTH);
    }
}
