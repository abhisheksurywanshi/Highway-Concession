package toll.tcm.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageToBase64 {

    public static void main(String[] args) {
        // Path to the image file
        
    }

    // Method to encode image to Base64 string
    public static String encodeImageToBase64(String imagePath) throws IOException {
        // Read the image file into a byte array
      
        
        // Convert image to Base64 string
        try {
            String base64String = encodeImageToBase64(imagePath);
            System.out.println("Base64 String: " + base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(imagePath);
        byte[] imageBytes = Files.readAllBytes(file.toPath());

        // Encode the byte array to a Base64 string
        String base64String = Base64.getEncoder().encodeToString(imageBytes);
        return base64String;
    }
}
