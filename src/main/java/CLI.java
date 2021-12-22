import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class CLI implements UI {

    @Override
    public File getCsvFile() {
        File csvFile;
        try (
                Scanner userInput = new Scanner(System.in)) {
            while (true) {
                System.out.println("Where is the CSV file? (Please enter file path): ");
                String path = userInput.nextLine();
                csvFile = new File(path);
                if (!csvFile.exists()) {
                    System.err.println("File not found. " + path + " does not exist. Please re-enter the filepath.");
                    continue;
                } else if (!csvFile.isFile()) {
                    System.err.println("Problem with file. " + path + " is not a file. Please enter the path to a .csv file.");
                    continue;
                }

                try (BufferedReader reader = Files.newBufferedReader(csvFile.toPath())) {
                    System.out.println("Here are the contents of your CSV file:\n");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.err.println("There was an error displaying the contents of this file. Please try again.");
                    continue;
                }

                break;
            }
        }

        return csvFile;
    }

    @Override
    public void giveJsonFile(File jsonFile) {

        System.out.println("\nYour new JSON file is available at the following path: " + jsonFile.toPath());
        System.out.println("\nHere are the contents of your JSON file:\n");

        try (BufferedReader reader = Files.newBufferedReader(jsonFile.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("There was an error displaying the contents of this file. Please try again.");
        }
    }
}
