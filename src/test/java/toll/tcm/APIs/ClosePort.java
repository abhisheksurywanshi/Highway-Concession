package toll.tcm.APIs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;


public class ClosePort {

    public static void main(String[] args) throws UnknownHostException, IOException {
        int port = 4009; // Replace with the port you want to close
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Dummy server running on port " + port);
            Thread.sleep(3000); // Keep the server running for testing
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        closePort(port);
        String host = "10.10.1.87"; // Replace with the correct host
        

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Socket socket = new Socket("10.10.1.87", port);
        
    }

    public static void closePort(int port) {
        try {
            // Step 1: Find the process using the port
            String findCommand = "netstat -ano | findstr :" + port;
            Process findProcess = Runtime.getRuntime().exec(findCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(findProcess.getInputStream()));
            String line;
            String pid = null;

            while ((line = reader.readLine()) != null) {
                System.out.println("Process Info: " + line);
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length > 4) {
                    pid = tokens[4]; // PID is usually the last column
                    break;
                }
            }

            reader.close();

            if (pid != null) {
                // Step 2: Kill the process by PID
                String killCommand = "taskkill /F /PID " + pid;
                Process killProcess = Runtime.getRuntime().exec(killCommand);
                killProcess.waitFor();
                System.out.println("Port " + port + " closed by terminating process with PID: " + pid);
            } else {
                System.out.println("No process found using port " + port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
