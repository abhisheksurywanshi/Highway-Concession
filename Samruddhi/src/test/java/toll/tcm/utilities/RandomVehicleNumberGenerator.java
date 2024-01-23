package toll.tcm.utilities;

import java.util.Random;

public class RandomVehicleNumberGenerator {

    public static void main(String[] args) {
        String randomVehicleNumber = generateRandomVehicleNumber();
        System.out.println("Random Indian Vehicle Number: " + randomVehicleNumber);
    }

    public static String generateRandomVehicleNumber() {
        StringBuilder vehicleNumber = new StringBuilder();

        // Add two random letters (assumed to be state code)
        vehicleNumber.append(generateRandomLetter());
        vehicleNumber.append(generateRandomLetter());

        // Add two random digits
        vehicleNumber.append(generateRandomDigit());
        vehicleNumber.append(generateRandomDigit());

        // Add two more random letters
        vehicleNumber.append(generateRandomLetter());
        vehicleNumber.append(generateRandomLetter());

        // Add four random digits
        for (int i = 0; i < 4; i++) {
            vehicleNumber.append(generateRandomDigit());
        }

        return vehicleNumber.toString();
    }

    private static char generateRandomLetter() {
        Random random = new Random();
        // ASCII values for letters A-Z: 65-90
        return (char) (random.nextInt(26) + 'A');
    }

    private static int generateRandomDigit() {
        Random random = new Random();
        // Random digit between 0 and 9
        return random.nextInt(10);
    }
}

