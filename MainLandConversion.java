import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainLandConversion {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LandConversion1 landConversion = new LandConversion1() {};

        System.out.print("Enter your desired conversion: ");
        String input = scanner.nextLine();


        // Regex to match "number unit to unit":
        // ^ = start,
        // (\\d+(\\.\\d+)?) = number,
        // \\s+ = spaces,
        // (.+) = unit,
        // "to" = literal,
        // $ = end.

        Pattern pattern = Pattern.compile("^(\\d+(\\.\\d+)?)\\s+(.+)\\s+to\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);


        // Checks if the entire input string matches the regex pattern.
        if (matcher.matches()) {
            try {
                // Extract the quantity and units
                double quantity = Double.parseDouble(matcher.group(1));
                String fromUnit = matcher.group(3).toLowerCase().trim();
                String targetUnit = matcher.group(4).toLowerCase().trim();

                // Perform the conversion
                double result = landConversion.convert(quantity, fromUnit, targetUnit);

                // Rounded to 10 decimal places
                double rounded = Math.round(result * 10000000000.0) / 10000000000.0;

                // Display the result
                System.out.println(quantity + " " + fromUnit + " to " + targetUnit + " is " + rounded + " " + targetUnit);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n" +"Please Try Again");
            }


        }

        else {
            System.out.println("Invalid input format." + "\n" + "Please use 'number unit to unit'.");
        }

    }


}