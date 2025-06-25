package toll.tcm.testCases;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class VirtualSerialPortTest {
    private SerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void connect(String portName) {
        try {
            // Get the port identifier
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            // Check if the port is already in use
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Port is currently in use");
            } else {
                // Open the port with a timeout of 2000ms
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

                // Check if the comm port is a serial port
                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;
                    // Set serial port parameters (baud rate, data bits, stop bits, parity)
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    // Get input and output streams
                    inputStream = serialPort.getInputStream();
                    outputStream = serialPort.getOutputStream();

                    // Start reading or writing data here
                    // Example: write a string to the serial port
                    String dataToSend = "Hello, virtual serial port!";
                    outputStream.write(dataToSend.getBytes());
                    outputStream.flush();

                    System.out.println("Data sent: " + dataToSend);

                    // Example: read data from the serial port
                    byte[] readBuffer = new byte[1024];
                    int numBytes;
                    while ((numBytes = inputStream.read(readBuffer)) > 0) {
                        String receivedData = new String(readBuffer, 0, numBytes);
                        System.out.println("Data received: " + receivedData);
                    }
                } else {
                    System.out.println("Error: Only serial ports are handled.");
                }
            }
        } catch (NoSuchPortException e) {
            System.err.println("No such port: " + portName);
        } catch (PortInUseException e) {
            System.err.println("Port is in use: " + portName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VirtualSerialPortTest test = new VirtualSerialPortTest();
        test.connect("COM3"); // Replace with the appropriate virtual port name
    }
}

