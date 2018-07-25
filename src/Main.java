import java.io.*;

public class Main {

    final static String INPUT_FILE = "input.txt";
    final static String OUTPUT_FILE = "output.txt";


    public static void main(String[] args) {

        // I/O operations
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;        // Line of text with markers
            String cleanLine;   // Line of text w/o markers
            String div = "</div>";

            while ((line = br.readLine()) != null) {

                // Split the string
                String tempArray[] = line.split("=");

                // Clean the string from the marker
                cleanLine = tempArray[1];

                // Check if line has needed marker
                // Add the code accordingly
                switch (tempArray[0]) {
                    case "b": cleanLine = "BBB " + cleanLine + div; break;
                    case "c": cleanLine = "CCC " + cleanLine + div; break;
                }
                // Write result into the output file
                writer.write(cleanLine);
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

