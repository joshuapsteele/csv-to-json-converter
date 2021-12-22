import java.io.File;

public class Application {

    public static void main(String[] args) {
        UI ui = new CLI();
        CSVtoJSONService service = new CSVtoJSONService();

        File csvFile = ui.getCsvFile();
        File jsonFile = service.convertCsvToJson(csvFile);

        ui.giveJsonFile(jsonFile);

        System.exit(0);
    }

}
