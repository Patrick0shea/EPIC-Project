
import java.util.Scanner;


public class LandPrice{
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            LandValuation predictor = new LandValuation();
            System.out.println("=======Land Valuation Calculator======");
            System.out.println("Welcome to the Land Price Predictor!");
            System.out.println("Ask me about land prices (e.g., 'How much would 12 hectares of arable land cost in Wexford?')");
            System.out.println("Supported units: hectares, acres, square kilometers, square metres, square yards, square miles");
            System.out.println("Or enter 'quit' to exit.");

            while (true) {
                System.out.print("\nYour question: ");
                String userInput = scanner.nextLine();

                if (userInput.toLowerCase().equals("quit")) {
                    break;
                }

                String[] parsedInput = predictor.parseUserInput(userInput);

                if (parsedInput[0] == null || parsedInput[1] == null ||
                        parsedInput[2] == null || parsedInput[3] == null) {
                    System.out.println("I couldn't understand all parts of your question. Let's try step by step:");
                    String region = predictor.getValidatedRegion(scanner);
                    String landType = predictor.getValidatedLandType(scanner);
                    double landSize = predictor.getValidatedLandSize(scanner);
                    double price = predictor.calculatePrice(region, landType, landSize);
                    System.out.printf("Estimated price: €%.2f%n", price);
                } else {
                    String region = predictor.findArrayContainingCounty(parsedInput[0]);
                    String landType = parsedInput[1];
                    double landSize = Double.parseDouble(parsedInput[2]);
                    double hectares = predictor.convertToHectares(landSize, parsedInput[3]);

                    double price = predictor.calculatePrice(region, landType, hectares);
                    System.out.printf("Estimated price: €%.2f%n", price);
                }
            }


        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

