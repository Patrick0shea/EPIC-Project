import java.util.HashMap;
import java.util.Scanner;

public abstract class LandValuation {
    protected final HashMap<String, Double> regionPrices = new HashMap<>();
    protected final HashMap<String, Double> landTypeMultipliers = new HashMap<>();

    private final Conversion unitConversions;

    public LandValuation() {
        //using composition to reuse the unit conversions method in the conversion class
        this.unitConversions = new Conversion() {
            @Override
            protected void initializeConversionFactors() {
                conversionFactor.put("HECTARES", 1.0);
                conversionFactor.put("ACRES", 0.4047);
                conversionFactor.put("SQUARE KILOMETRES", 100.0);
                conversionFactor.put("KILOMETRES SQUARED", 100.0);
                conversionFactor.put("KM^2", 100.0);
                conversionFactor.put("SQUARE METERS", 0.0001);
                conversionFactor.put("M^2", 0.0001);
                conversionFactor.put("SQUARE YARDS", 0.000836);
                conversionFactor.put("YARDS SQUARED", 0.000836);
                conversionFactor.put("SQUARE MILES", 258.999);
                conversionFactor.put("MILES SQUARED", 258.999);
            }
        };
        initializeLandTypeMultipliers();

        initializeRegionPrices();
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void initializeRegionPrices();

    public abstract String findRegion(String county);

    // Default methods with shared behavior
    protected void initializeLandTypeMultipliers() {
        landTypeMultipliers.put("Arable Land", 1.2560690203468685);
        landTypeMultipliers.put("Permanent Grassland", 0.9084773711021467);
        landTypeMultipliers.put("All Land", 1.0);
    }



    public String[] parseUserInput(String input) {
        input = input.toUpperCase();
        String[] result = new String[4]; // [county, landType, size, unit]
        //split each word by space
        String[] words = input.split(" ");

        // Find the county by checking each word
        for (String word : words) {
            String region = findRegion(word);
            if (!region.equals("NONE")) {
                result[0] = region;
                break;
            }
        }

        // Identify land type (modified to work with uppercase input)
        if (input.contains("ARABLE")) {
            result[1] = "Arable Land";
        } else if (input.contains("GRASSLAND") || input.contains("GRASS")) {
            result[1] = "Permanent Grassland";
        }
        else if (input.contains("LAND")){
            result[1] = "All Land";
        }

        // Extract land size and unit
        for (int i = 0; i < words.length; i++) {
            try {
                //convert current word to a string double
                double number = Double.parseDouble(words[i]);
                // get the value of the string double and make it the number of units
                result[2] = String.valueOf(number);
                //if it's not the last word and the next word is a unit then make the next word the unit
                if (i + 1 < words.length && unitConversions.conversionFactor.containsKey(words[i + 1])) {
                    result[3] = words[i + 1];
                }
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return result;
    }






    public double calculatePrice(String region, String landType, double landSize) {
        if (!isValidRegion(region) || !isValidLandType(landType) || landSize <= 0) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }
        double basePrice = regionPrices.get(region);
        double multiplier = landTypeMultipliers.get(landType);
        return basePrice * multiplier * landSize;
    }

    public double convertToHectares(double size, String unit) {
        if (!unitConversions.conversionFactor.containsKey(unit)) {
            throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
        return size * unitConversions.conversionFactor.get(unit);
    }

    public boolean isValidRegion(String region) {
        return regionPrices.containsKey(region);
    }

    public boolean isValidLandType(String landType) {
        return landTypeMultipliers.containsKey(landType);
    }
}
