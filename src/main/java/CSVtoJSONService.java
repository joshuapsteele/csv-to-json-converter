import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CSVtoJSONService {

public File convertCsvToJson(File csvFile) {
    String csvFilePath = csvFile.toPath().toString();
    String jsonFilePath = csvFilePath.substring(0, csvFilePath.lastIndexOf(".")) + ".json";
    File jsonFile = new File(jsonFilePath);

    try (
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))
            )
    {
        String lineToRead;
        writer.write("[\n");
        while ((lineToRead = reader.readLine()) != null) {
            writer.write(lineToRead + "THIS IS A TEST!");
            writer.newLine();
        }
        writer.write("]");

    } catch (IOException e) {
        e.printStackTrace();
    }

    return jsonFile;
}

}
