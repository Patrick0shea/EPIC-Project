import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class AuctionIQ {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display main menu
            System.out.println("\n======= Main Menu =======");
            System.out.println("1. Land Conversion");
            System.out.println("2. Math Calculator");
            System.out.println("3. Land Price Prediction");
            System.out.println("4. Financial Calculator");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    landConversion(scanner);
                    break;
                case 2:
                    mathCalculator(scanner);
                    break;
                case 3:
                    landPricePrediction(scanner);
                    break;
                case 4:
                    financialCalculator(scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }

        scanner.close();
    }

    // Method for Land Conversion
    private static void landConversion(Scanner scanner) {
        LandConversion1 landConversion = new LandConversion1() {};

        System.out.println("\nWelcome to the Land Conversion Tool!");
        System.out.println("Type 'quit' anytime to return to the main menu.");

        while (true) {
            System.out.print("\nEnter your desired conversion (e.g., '10 acres to hectares'): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break; // Return to main menu
            }

            // Regex to match "number unit to unit"
            Pattern pattern = Pattern.compile("^(\\d+(\\.\\d+)?)\\s+(.+)\\s+to\\s+(.+)$");
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                try {
                    double quantity = Double.parseDouble(matcher.group(1));
                    String fromUnit = matcher.group(3).toLowerCase().trim();
                    String targetUnit = matcher.group(4).toLowerCase().trim();

                    double result = landConversion.convert(quantity, fromUnit, targetUnit);
                    double rounded = Math.round(result * 10000000000.0) / 10000000000.0;

                    System.out.println(quantity + " " + fromUnit + " to " + targetUnit + " is " + rounded + " " + targetUnit);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\nPlease Try Again.");
                }
            } else {
                System.out.println("Invalid input format. Please use 'number unit to unit'.");
            }
        }
    }

    // Method for Math Calculator
    private static void mathCalculator(Scanner scanner) {
        HashMap<String, Double> numberMap = new HashMap<>();
        numberMap.put("zero", 0.0);
        numberMap.put("one", 1.0);
        numberMap.put("two", 2.0);
        numberMap.put("three", 3.0);
        numberMap.put("four", 4.0);
        numberMap.put("five", 5.0);
        numberMap.put("six", 6.0);
        numberMap.put("seven", 7.0);
        numberMap.put("eight", 8.0);
        numberMap.put("nine", 9.0);
        numberMap.put("ten", 10.0);

        System.out.println("\nWelcome to the Math Calculator!");
        System.out.println("Type 'quit' anytime to return to the main menu.");

        while (true) {
            System.out.print("\nEnter your problem (e.g., '5 times 5' or 'square root of 25'): ");
            String problem = scanner.nextLine();

            if (problem.equalsIgnoreCase("quit")) {
                break; // Return to main menu
            }

            Pattern pattern = Pattern.compile("^(-?\\w+)?\\s*(to the power of|times|\\*|plus|\\+|minus|-|divided|square root of|cubed root of)\\s*(-?\\w+)?$");
            Matcher matcher = pattern.matcher(problem);

            if (matcher.matches()) {
                try {
                    String firstPart = matcher.group(1);
                    String operator = matcher.group(2);
                    String secondPart = matcher.group(3);

                    double num1;
                    double num2 = 0;

                    if (operator.equals("square root of") || operator.equals("cubed root of")) {
                        num1 = parseNumber(firstPart, numberMap);
                    } else {
                        num1 = parseNumber(firstPart, numberMap);
                        num2 = parseNumber(secondPart, numberMap);
                    }

                    double result = performCalculation(num1, operator, num2);

                    System.out.println("Result: " + result);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid input format.\nPlease try again.");
            }
        }
    }

    private static double parseNumber(String input, HashMap<String, Double> numberMap) {
        if (input == null) throw new IllegalArgumentException("Invalid number input.");

        if (input.startsWith("-")) {
            String positivePart = input.substring(1);
            return -numberMap.getOrDefault(positivePart, Double.parseDouble(input));
        }

        return numberMap.getOrDefault(input, Double.parseDouble(input));
    }

    private static double performCalculation(double num1, String operator, double num2) {
        switch (operator) {
            case "times":
            case "*":
                return num1 * num2;
            case "plus":
            case "+":
                return num1 + num2;
            case "minus":
            case "-":
                return num1 - num2;
            case "divided":
            case "/":
                if (num2 == 0) throw new ArithmeticException("Division by zero is not allowed.");
                return num1 / num2;
            case "to the power of":
                return Math.pow(num1, num2);
            case "square root of":
                if (num1 < 0) throw new ArithmeticException("Cannot calculate square root of a negative number.");
                return Math.sqrt(num1);
            case "cubed root of":
                return Math.cbrt(num1);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
    private static void financialCalculator(Scanner scanner) {
        System.out.println("\n====== Financial Calculator ======");
        System.out.println("Please choose an option from the menu below: ");
        boolean continueCalculation = true;

        while (continueCalculation) {
            try {
                System.out.println("-----------------------------------");
                System.out.printf("%nChoose an option:%n1 - Land Tax Calculations%n2 - Checking Client Fee%n0 - Return to Main Menu%n");
                System.out.print("Your choice: ");
                int userChoice = Integer.parseInt(scanner.nextLine());

                PropertyTaxCalculations[] data = new PropertyTaxCalculations[2];

                if (userChoice == 1) {
                    System.out.print("Enter name of your Land's relevant Authority: ");
                    String localAuthorityName = scanner.nextLine();

                    System.out.print("Enter total range of Valuation of your property: ");
                    int propertyValueBand = Integer.parseInt(scanner.nextLine());

                    data[0] = new LandTax(localAuthorityName, propertyValueBand);

                } else if (userChoice == 2) {
                    System.out.println("\n--- Client Fee Check ---");
                    System.out.print("Enter name of your Land's relevant Authority: ");
                    String localAuthorityName = scanner.nextLine();

                    System.out.print("Enter total range of Valuation of your property: ");
                    int propertyValueBand = Integer.parseInt(scanner.nextLine());

                    data[1] = new ClientFee(localAuthorityName, propertyValueBand);

                } else if (userChoice == 0) {
                    continueCalculation = false;
                    System.out.println("Returning to the main menu...");
                } else {
                    System.out.println("Invalid option! Please enter a valid option from the menu.");
                }

                // Display calculations
                for (PropertyTaxCalculations calculations : data) {
                    if (calculations != null) {
                        System.out.println(calculations.toString());
                    }
                }

            } catch (InputMismatchException e) {
                System.err.println("Invalid input! Please enter a valid input.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.err.println("An error occurred!" + e.getMessage());
            }
        }
    }



    // Method for Land Price Prediction remains unchanged
    private static void landPricePrediction(Scanner scanner) {
        LandValuation predictor = new IrishLandValuation();

        System.out.println("\n-------Welcome to the Land Price Predictor!-------");
        System.out.println("Ask about land prices (e.g., 'How much would 12 hectares of arable land cost in Wexford?')");


        while (true) {
            System.out.print("\nYour question (or type 'quit' to return to main menu): ");
            String userInput = scanner.nextLine();

            if ("quit".equalsIgnoreCase(userInput)) break;

            try {
                String[] parsedInput = predictor.parseUserInput(userInput);


                // Validate all required inputs are present
                if (parsedInput[0].equals("NONE") || parsedInput[1] == null ||
                        parsedInput[2] == null || parsedInput[3] == null) {
                    System.out.println("Please provide all required information:");
                    System.out.println("- County name (e.g., Wexford, Dublin)");
                    System.out.println("- Land type (Arable Land or Permanent Grassland)");
                    System.out.println("- Size and unit (e.g., 10 HECTARE)");
                    continue;
                }

                // Get the region from the county
                String region = parsedInput[0];
                String landType = parsedInput[1];
                double landSize = Double.parseDouble(parsedInput[2]);
                double hectares = predictor.convertToHectares(landSize, parsedInput[3]);

                double price = predictor.calculatePrice(region, landType, hectares);
                System.out.printf("Estimated price: €%.2f%n", price);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again with valid inputs.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
    }
}
