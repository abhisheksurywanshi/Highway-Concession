package toll.tcm.SpringBoot;

import java.util.HashMap;
import java.util.Map;

public class ASCII {

	public ASCII() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Map<Integer, Character> asciiMap = new HashMap<>();

        // Populate the map with ASCII integer values and their corresponding characters
        for (int i = 0; i <= 255; i++) {
            asciiMap.put(i, (char) i);
        }

        // Example conversion
        int asciiValue = 221; // ASCII value of F12

        // Convert ASCII integer value to character and print
        if (asciiMap.containsKey(asciiValue)) {
            System.out.println("ASCII value " + asciiValue + " corresponds to character '" + asciiMap.get(asciiValue) + "'");
        } else {
            System.out.println("No character found for ASCII value " + asciiValue);
        }

	}

}
