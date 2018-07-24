import java.io.*;

public class Main {

    final static String INPUT_FILE = "input.txt";
    final static String OUTPUT_FILE = "output.txt";


    public static void main(String[] args) {

        // I/O operations
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            while ((line = br.readLine()) != null) {
                writer.write(line);
                writer.newLine();

                //todo For testing, DELETE later
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

