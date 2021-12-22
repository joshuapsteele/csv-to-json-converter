import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CLI implements UI {

    @Override
    public File getCsvFile() {
        File csvFile;
        try (
                Scanner userInput = new Scanner(System.in)) {
            while (true) {
                System.out.println("**************************************************************");
                System.out.println("                     CSV TO JSON CONVERTER                    ");
                System.out.println("**************************************************************");
                System.out.println("NOTE: To exit the program, type EXIT");
                System.out.println("\nWhere is the CSV file? Please enter the file path: ");
                String path = userInput.nextLine();
                if (path.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                csvFile = new File(path);
                if (!csvFile.exists()) {
                    System.err.println("File not found. Please re-enter the filepath.");
                    continue;
                } else if (!csvFile.isFile()) {
                    System.err.println("Problem with file. Please enter the path to a .csv file.");
                    continue;
                }

                try (BufferedReader reader = Files.newBufferedReader(csvFile.toPath())) {
                    System.out.println("\n**************************************************************");
                    System.out.println("          HERE ARE THE CONTENTS OF YOUR CSV FILE");
                    System.out.println("**************************************************************\n");
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

        System.out.println("\n**************************************************************");
        System.out.println("         HERE ARE THE CONTENTS OF YOUR JSON FILE");
        System.out.println("**************************************************************\n");

        try (BufferedReader reader = Files.newBufferedReader(jsonFile.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("\nYour new JSON file is available at the following path: " + jsonFile.toPath());
            System.out.println("**************************************************************");
        } catch (IOException e) {
            System.err.println("There was an error displaying the contents of this file. Please try again.");
        }
    }
}
