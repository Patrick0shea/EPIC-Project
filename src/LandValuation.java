import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class LandValuation {
    private final HashMap<String, Double> regionPrices;
    private final HashMap<String, Double> landTypeMultipliers;
    private final String[] midlandCounties = new String[]{"LAOIS", "OFFALY", "WESTMEATH", "LONGFORD"};
    private final String[] midWest = new String[]{"CLARE", "LIMERICK", "NORTH"};
    private final String[] west = new String[]{"MAYO", "GALWAY", "ROSCOMMON"};
    private final String[] southEast = new String[]{"SOUTH", "WATERFORD", "KILKENNY", "CARLOW", "WEXFORD"};
    private final String dublin = "DUBLIN";
    private final String[] midEast = new String[]{"WICKLOW", "KILDARE", "MEATH"};
    private final String[] southWest = new String[]{"KERRY", "CORK"};
    private final String[] border = new String[]{"SLIGO", "DONEGAL", "LEITRIM", "CAVAN", "MONAGHAN", "LOUTH"};
    private final HashMap<String, Double> unitConversions = new HashMap<>();

    public LandValuation() {
        regionPrices = new HashMap<>();
        landTypeMultipliers = new HashMap<>();
        initializeRegionPrices();
        initializeLandTypeMultipliers();

        initializeUnitConversions();
    }

    private void initializeUnitConversions() {
        unitConversions.put("HECTARE", 1.0);
        unitConversions.put("HECTARES", 1.0);
        unitConversions.put("HA", 1.0);
        unitConversions.put("ACRE", 0.4047);
        unitConversions.put("ACRES", 0.4047);
        unitConversions.put("AC", 0.4047);
        unitConversions.put("SQUARE KILOMETER", 100.0);
        unitConversions.put("SQUARE KILOMETERS", 100.0);
        unitConversions.put("KM2", 100.0);
        unitConversions.put("SQUARE METRE", 0.0001);
        unitConversions.put("SQUARE METERS", 0.0001);
        unitConversions.put("M2", 0.0001);
        unitConversions.put("SQUARE YARD", 0.000836);
        unitConversions.put("SQUARE YARDS", 0.000836);
        unitConversions.put("YD2", 0.000836);
        unitConversions.put("SQUARE MILE", 258.999);
        unitConversions.put("SQUARE MILES", 258.999);
        unitConversions.put("MI2", 258.999);
    }

    public String[] parseUserInput(String input) {
        input = input.toUpperCase();
        String[] result = new String[4]; // [county, landType, size, unit   ]

        // Look for county
        for (String county : border) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }
        for (String county : midlandCounties) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }
        for (String county : west) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }
        for (String county : southEast) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }


        if (input.contains(dublin)) {
            result[0] = dublin;
        }
        for (String county : midEast) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }
        for (String county : midWest) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }
        for (String county : southWest) {
            if (input.contains(county)) {
                result[0] = county;
                break;
            }
        }

        // Look for land type
        if (input.contains("ARABLE")) {
            result[1] = "Arable Land";
        } else if (input.contains("GRASSLAND") || input.contains("GRASS")) {
            result[1] = "Permanent Grassland";
        }

        // Look for number of hectares
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            try {
                double number = Double.parseDouble(words[i]);
                result[2] = String.valueOf(number);

                for (int j = i + 1; j < Math.min(i + 3, words.length); j++) {
                    String potentialUnit = words[j];
                    if (unitConversions.containsKey(potentialUnit)) {
                        result[3] = potentialUnit;
                        break;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return result;
    }

    public String findArrayContainingCounty(String county) {
        county = county.toUpperCase();

        if (Arrays.asList(border).contains(county)) {
            return "Border";
        } else if (Arrays.asList(midlandCounties).contains(county)) {
            return "Midland";
        } else if (Arrays.asList(west).contains(county)) {
            return "West";
        } else if (county.equals(dublin)) {
            return "Dublin";
        } else if (Arrays.asList(midEast).contains(county)) {
            return "Mid-East";
        } else if (Arrays.asList(midWest).contains(county)) {
            return "Mid-West";
        } else if (Arrays.asList(southEast).contains(county)) {
            return "South-East";
        } else if (Arrays.asList(southWest).contains(county)) {
            return "South-West";
        }
        return "NONE";
    }

    public String getValidatedRegion(Scanner scanner) {
        String county;
        String region;

        do {
            System.out.print("Enter county: ");
            county = scanner.nextLine().trim();
            region = findArrayContainingCounty(county);
            if (region.equals("NONE")) {
                System.out.println("Invalid county. Please try again.");
            }
        } while (region.equals("NONE"));

        return region;
    }

    public String getValidatedLandType(Scanner scanner) {
        String landType;
        System.out.println("Available land types: " + Arrays.toString(getAvailableLandTypes()));
        do {
            System.out.print("Enter land type: ");
            landType = scanner.nextLine().trim();
            if (!isValidLandType(landType)) {
                System.out.println("Invalid land type. Please try again.");
            }
        } while (!isValidLandType(landType));
        return landType;
    }

    // getter for land size
    public double getValidatedLandSize(Scanner scanner) {
        double landSize = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter land size in hectares: ");
            try {
                landSize = Double.parseDouble(scanner.nextLine().trim());
                if (landSize <= 0) {
                    System.out.println("Land size must be greater than 0.");
                    continue;
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return landSize;
    }

    public double calculatePrice(String region, String landType, double landSize) {
        if (!isValidRegion(region) || !isValidLandType(landType) || landSize <= 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        double basePrice = regionPrices.get(region);
        double multiplier = landTypeMultipliers.get(landType);
        return basePrice * multiplier * landSize;
    }

    public boolean isValidRegion(String region) {
        return regionPrices.containsKey(region);
    }

    public boolean isValidLandType(String landType) {
        return landTypeMultipliers.containsKey(landType);
    }

    public String[] getAvailableLandTypes() {
        return landTypeMultipliers.keySet().toArray(new String[0]);
    }

    private void initializeRegionPrices() {
        regionPrices.put("Border", 6965.0);
        regionPrices.put("Midland", 9469.0);
        regionPrices.put("West", 6745.0);
        regionPrices.put("Dublin", 15048.0);
        regionPrices.put("Mid-East", 14398.0);
        regionPrices.put("Mid-West", 10023.0);
        regionPrices.put("South-East", 13237.0);
        regionPrices.put("South-West", 9821.0);
    }

    private void initializeLandTypeMultipliers() {
        // Using 2023 data ratios
        landTypeMultipliers.put("Arable Land", 1.79); // 16275/9084 ≈ 1.79
        landTypeMultipliers.put("Permanent Grassland", 0.98); // 8887/9084 ≈ 0.98
    }

    public double convertToHectares(double size, String unit) {
        if (!unitConversions.containsKey(unit)) {
            throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
        return size * unitConversions.get(unit);
    }
}
