import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        UI ui = new CLI();
        CSVtoJSONService service = new CSVtoJSONService();

        while (true) {
            File csvFile = ui.getCsvFile();
            File jsonFile;
            try {
                jsonFile = service.convertCsvToJson(csvFile);
            } catch (IOException e) {
                System.err.println("\nERROR: Unable to write new JSON file. Please check the formatting of your CSV file and try again.\n");
                continue;
            }
            ui.giveJsonFile(jsonFile);
            break;
        }
        System.exit(0);
    }

}
