import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatasetCalculations {

    public static void main(String[] args) {
        String csvFile = "LandValuationDataset.csv";
        String line;
        String csvSplitBy = ",";

        Map<String, Double> totalValues = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);
                String landType = fields[2];
                double value = Double.parseDouble(fields[4]);

                totalValues.put(landType, totalValues.getOrDefault(landType, 0.0) + value);
                counts.put(landType, counts.getOrDefault(landType, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avgAllLandTypes = totalValues.get("All Land Types") / counts.get("All Land Types");
        double avgArableLand = totalValues.get("Arable Land") / counts.get("Arable Land");
        double avgPermanentGrassland = totalValues.get("Permanent Grassland") / counts.get("Permanent Grassland");

        double conversionRateToArable = avgArableLand / avgAllLandTypes;
        double conversionRateToGrassland = avgPermanentGrassland / avgAllLandTypes;

        System.out.println("Average Conversion Rate from All Land Types to Arable Land: " + conversionRateToArable);
        System.out.println("Average Conversion Rate from All Land Types to Permanent Grassland: " + conversionRateToGrassland);
    }
}
