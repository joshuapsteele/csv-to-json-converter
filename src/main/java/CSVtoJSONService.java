import java.io.*;
import java.util.Arrays;

public class CSVtoJSONService {

    public File convertCsvToJson(File csvFile) throws IOException {

        String csvFilePath = csvFile.toPath().toString();
        String jsonFilePath = csvFilePath.substring(0, csvFilePath.lastIndexOf(".")) + ".json";
        File jsonFile = new File(jsonFilePath);

        try (
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))
        ) {
            String lineToRead;
            writer.write("[\n  {\n");
            int numberOfIndentsLastLine = 1;

            boolean isFirstLine = true;
            while ((lineToRead = reader.readLine()) != null) {
                int numberOfIndentsThisLine = 1;
                String[] lineItems = lineToRead.split(",");
                if (lineItems.length == 0) {
                    writer.write("\n  },\n  {\n");
                    isFirstLine = true;
                    continue;
                }
                for (int i = 0; i < lineItems.length; i++) {

                    if (lineItems[i].isEmpty()) {
                        numberOfIndentsThisLine++;
                        continue;
                    }

                    if (numberOfIndentsThisLine < numberOfIndentsLastLine) {
                        writer.newLine();
                        for (int j = 0; j < numberOfIndentsLastLine; j++) {
                            writer.write("  ");
                        }
                        writer.write("},\n");
                    } else if (numberOfIndentsThisLine > numberOfIndentsLastLine) {
                        writer.write("{\n");
                    } else if (isFirstLine) {
                        isFirstLine = false;
                    } else {
                        writer.write(",\n");
                    }

                    for (int k = 0; k <= numberOfIndentsThisLine; k++) {
                        writer.write("  ");
                    }

                    if (!lineItems[i].isEmpty() && i == lineItems.length - 1) {
                        writer.write("\"" + lineItems[i] + "\": ");
                        break;
                    } else if (!lineItems[i].isEmpty() && lineItems[i + 1].contains("^")) {
                        String[] lineItemSubArray = lineItems[i + 1].split("\\^");
                        writer.write("\"" + lineItems[i] + "\": " + Arrays.toString(lineItemSubArray) + "\"");
                        break;
                    } else if (!lineItems[i].isEmpty() && !lineItems[i + 1].isEmpty()) {

                        boolean isNumber = true;
                        try {
                            int valueOfItem = Integer.parseInt(lineItems[i + 1]);
                        } catch (NumberFormatException e) {
                            isNumber = false;
                        }

                        if (isNumber) {
                            writer.write("\"" + lineItems[i] + "\": " + lineItems[i + 1]);

                        } else {
                            writer.write("\"" + lineItems[i] + "\": \"" + lineItems[i + 1] + "\"");
                        }
                        break;
                    }

                }
                numberOfIndentsLastLine = numberOfIndentsThisLine;
            }
            writer.write("\n  }\n]");
        }
        return jsonFile;
    }
}
