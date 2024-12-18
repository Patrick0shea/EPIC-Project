import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println("5. Currency Exchange Calculator");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
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
                    currencyExchangeCalculator(scanner);
                    break;
                case 6:
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

        // Continuously prompts the user to enter a math problem until "quit" is entered.
        while (true) {
            System.out.print("\nEnter your problem (e.g., '4 + 5 * (3 - 1)', 'five times two', 'square root of 16', '2 to the power of 3'): ");
            String problem = scanner.nextLine();

            // Check if the user wants to quit the program
            if (problem.equalsIgnoreCase("quit")) {
                break; // Exit loop
            }

            try {
                // Check if the problem contains "to the power of"
                boolean isPowerCalculation = problem.toLowerCase().contains("to the power of");

                // Replace word-based numbers with numbers (e.g., "five" -> "5.0")
                problem = replaceWordNumbers(problem, numberMap);

                // Replace word-based operators with their corresponding symbols (e.g., "times" -> "*")
                problem = replaceWordOperators(problem);

                // Evaluate the expression
                double result = evaluateSimpleExpression(problem);
                // Print a specific message if it's a power calculation
                if (isPowerCalculation && result < 0) {
                    System.out.println("Result: " + (-result));
                } else {
                    System.out.println("Result: " + result);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Replace word-based numbers with digits
    private static String replaceWordNumbers(String expression, HashMap<String, Double> numberMap) {
        // Loop through each entry in the numberMap (word and its numeric value)
        for (Map.Entry<String, Double> entry : numberMap.entrySet()) {

            // Replaces each word-based number in the expression with its numeric equivalent.
            // entry.getKey() = word-based number (e.g., "five")
            // entry.getValue().toString() = numeric value as a string (e.g., "5.0")
            expression = expression.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }
        // Return the updated expression with numbers replaced
        return expression;
    }

    // Replace word-based operators with symbols or JavaScript functions
    private static String replaceWordOperators(String expression) {
        // \\b = word boundary
        // $1 = captured base number
        // $3 = captured exponent number
        // (\\d+(\\.\\d+)?) captures numbers with optional decimals
        expression = expression.replaceAll("\\bsquare root of\\s+(\\d+(\\.\\d+)?)\\b", "Math.sqrt($1)");
        expression = expression.replaceAll("\\b(\\d+(\\.\\d+)?)\\s+to the power of\\s+(\\d+(\\.\\d+)?)\\b", "Math.pow($1, $3)");
        expression = expression.replaceAll("\\btimes\\b", "*");
        expression = expression.replaceAll("\\bplus\\b", "+");
        expression = expression.replaceAll("\\bminus\\b", "-");
        expression = expression.replaceAll("\\bdivided\\b", "/");
        return expression;
    }


    // Evaluate simple expressions with parentheses using the Rhino JavaScript engine
    // The Rhino engine can automatically parse and evaluate these expressions
    // handling the correct precedence and operations
    private static double evaluateSimpleExpression(String expression) {

        // Sets up the environment needed to run JavaScript code within a Java program
        Context context = Context.enter();

        try {
            // scope = JavaScript code can run here
            // Scriptable is an interface in Rhino
            // that represents a collection of objects and properties available to JavaScript code
            // initStandardObjects() creates a standard set of JavaScript objects and functions
            Scriptable scope = context.initStandardObjects();

            // context class evaluates a string as a JavaScript expression
            // "<cmd>" = generic placeholder indicating the command being evaluated
            // 1 = starting line number for the script
            // null means there’s no special security context applied
            // it's an object because the result could be different types
            Object result = context.evaluateString(scope, expression, "<cmd>", 1, null);

            // Convert result which is an object to Number type and then to double
            return ((Number) result).doubleValue();

        } finally {
            Context.exit(); // Exit the Rhino context to free up resources
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
        // create an instance of the IrishLandValuation class
        LandValuation predictor = new IrishLandValuation();

        System.out.println("\n-------Welcome to the Land Price Predictor!-------");
        System.out.println("Ask about land prices (e.g., 'How much would 12 hectares of arable land cost in Wexford?')");

        //wrap in a while true loop until user enters quit
        while (true) {
            System.out.print("\nYour question (or type 'quit' to return to main menu): ");
            String userInput = scanner.nextLine();

            if ("quit".equalsIgnoreCase(userInput)) break;

            try {
                //calling the parseUserInput method to extract the key components of the prompt into an array
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
                //convert the unit value to a double
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
    private static void currencyExchangeCalculator(Scanner scanner) {



//      Instance the API Class
        CurrencyValueAPI api = new CurrencyValueAPI();

        while (true) {

//          Allows the input to repeat until requested to stop via the user


//          Takes in and processes the users input

            System.out.print("Hello! Please Enter the Country the client is from and their Budget ");
            System.out.println("\nExample Input: My client lives in France and has a budget of 250000");
            System.out.println("At anytime enter Quit to exit back to the main menu");
            System.out.print("Your input: ");
            String userInput = scanner.nextLine();

            if ("quit".equalsIgnoreCase(userInput)) break;

//          Searches for the currency rate and displays it
            String result = api.searchCurrency(userInput);
            System.out.println(result);
//          Fetch and Display the exchange rate
            api.performExchange();
            System.out.println("\n");
        }
    }


}

