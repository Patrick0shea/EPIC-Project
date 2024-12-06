import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LandConversion {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize HashMap with conversion factors
        Map<String, Double> conversionFactors = new HashMap<>();
        initializeConversionFactors(conversionFactors);
        System.out.println("Pick the number corresponding to your desired units to start with:");
        System.out.println("");
        System.out.println("1: Metres Squared");
        System.out.println("2: Kilometres Squared");
        System.out.println("3: Miles Squared");
        System.out.println("4: Feet Squared");
        System.out.println("5: Yards Squared");
        System.out.println("6: Acres");
        System.out.println("7: Hectares");
        System.out.print("Your Selection: ");
        int initialUnit = input.nextInt();

        System.out.print("Enter the value to convert: ");
        double value = input.nextDouble();

        System.out.println("Enter the number corresponding to your desired units you want to convert to:");
        System.out.println("1: Metres Squared");
        System.out.println("2: Kilometres Squared");
        System.out.println("3: Miles Squared");
        System.out.println("4: Feet Squared");
        System.out.println("5: Yards Squared");
        System.out.println("6: Acres");
        System.out.println("7: Hectares");
        System.out.print("Your Selection: ");
        int finalUnit = input.nextInt();


        if (initialUnit < 1 || initialUnit > 7 || finalUnit < 1 || finalUnit > 7) {
            System.out.println("Invalid selection. Please enter a number between 1 and 7.");
        } else {

            String conversionKey = getUnitName(initialUnit) + " to " + getUnitName(finalUnit);


            if (conversionFactors.containsKey(conversionKey)) {
                double conversionFactor = conversionFactors.get(conversionKey);
                double convertedValue = value * conversionFactor;


                System.out.println(value + " " + getUnitName(initialUnit) + " is " + convertedValue + " " + getUnitName(finalUnit) + ".");

            } else {
                System.out.println("Sorry, Conversion not supported.");
            }
    }
    }


    private static void initializeConversionFactors(Map<String, Double> conversionFactors) {
            // Metres Squared conversions
            conversionFactors.put("Metres Squared to Kilometres Squared", 1e-6);
            conversionFactors.put("Metres Squared to Miles Squared", 3.861e-7);
            conversionFactors.put("Metres Squared to Feet Squared", 10.7639);
            conversionFactors.put("Metres Squared to Yards Squared", 1.19599);
            conversionFactors.put("Metres Squared to Acres", 0.000247105);
            conversionFactors.put("Metres Squared to Hectares", 0.0001);

            // Kilometres Squared conversions
            conversionFactors.put("Kilometres Squared to Metres Squared", 1e6);
            conversionFactors.put("Kilometres Squared to Miles Squared", 0.3861);
            conversionFactors.put("Kilometres Squared to Feet Squared", 1.076e7);
            conversionFactors.put("Kilometres Squared to Yards Squared", 1.196e6);
            conversionFactors.put("Kilometres Squared to Acres", 247.105);
            conversionFactors.put("Kilometres Squared to Hectares", 100.0);

            // Miles Squared conversions
            conversionFactors.put("Miles Squared to Metres Squared", 2.59e6);
            conversionFactors.put("Miles Squared to Kilometres Squared", 2.58999);
            conversionFactors.put("Miles Squared to Feet Squared", 2.788e7);
            conversionFactors.put("Miles Squared to Yards Squared", 3.098e6);
            conversionFactors.put("Miles Squared to Acres", 640.0);
            conversionFactors.put("Miles Squared to Hectares", 258.998811);

            // Feet Squared conversions
            conversionFactors.put("Feet Squared to Metres Squared", 0.092903);
            conversionFactors.put("Feet Squared to Kilometres Squared", 9.2903e-8);
            conversionFactors.put("Feet Squared to Miles Squared", 3.587e-8);
            conversionFactors.put("Feet Squared to Yards Squared", 0.111111);
            conversionFactors.put("Feet Squared to Acres", 2.2957e-5);
            conversionFactors.put("Feet Squared to Hectares", 9.2903e-6);

            // Yards Squared conversions
            conversionFactors.put("Yards Squared to Metres Squared", 0.836127);
            conversionFactors.put("Yards Squared to Kilometres Squared", 8.3613e-7);
            conversionFactors.put("Yards Squared to Miles Squared", 3.2283e-7);
            conversionFactors.put("Yards Squared to Feet Squared", 9.0);
            conversionFactors.put("Yards Squared to Acres", 0.000206611);
            conversionFactors.put("Yards Squared to Hectares", 8.3613e-5);

            // Acres conversions
            conversionFactors.put("Acres to Metres Squared", 4046.86);
            conversionFactors.put("Acres to Kilometres Squared", 0.00404686);
            conversionFactors.put("Acres to Miles Squared", 0.0015625);
            conversionFactors.put("Acres to Feet Squared", 43560.0);
            conversionFactors.put("Acres to Yards Squared", 4840.0);
            conversionFactors.put("Acres to Hectares", 0.404686);

            // Hectares conversions
            conversionFactors.put("Hectares to Metres Squared", 1e4);
            conversionFactors.put("Hectares to Kilometres Squared", 0.01);
            conversionFactors.put("Hectares to Miles Squared", 0.003861);
            conversionFactors.put("Hectares to Feet Squared", 107639.104);
            conversionFactors.put("Hectares to Yards Squared", 11959.9);
            conversionFactors.put("Hectares to Acres", 2.47105);
        }



        private static String getUnitName(int unitNumber) {
        switch (unitNumber) {
            case 1: return "Metres Squared";
            case 2: return "Kilometres Squared";
            case 3: return "Miles Squared";
            case 4: return "Feet Squared";
            case 5: return "Yards Squared";
            case 6: return "Acres";
            case 7: return "Hectares";
            default: return "Unknown Unit";
        }
    }
}