import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        UI ui = new CLI();
        CSVtoJSONService service = new CSVtoJSONService();

        File csvFile = ui.getCsvFile();
        File jsonFile = null;
        try {
            jsonFile = service.convertCsvToJson(csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ui.giveJsonFile(jsonFile);

        System.exit(0);
    }

}
