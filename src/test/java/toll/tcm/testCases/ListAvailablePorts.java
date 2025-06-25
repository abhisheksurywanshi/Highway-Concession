package toll.tcm.testCases;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

public class ListAvailablePorts {
    public static void main(String[] args) {
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = portList.nextElement();
            System.out.println("Port: " + portId.getName());
        }
    }
}

