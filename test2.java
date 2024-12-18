import java.util.*;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class test2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mathCalculator(scanner);
    }

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
            System.out.print("\n" + "Enter your problem (e.g., '4 + 5 * (3 - 1)' or 'five times two'): ");
            String problem = scanner.nextLine();

            // Check if the user wants to quit the program
            if (problem.equalsIgnoreCase("Quit")) {
                break; // Exit loop
            }

            try {
                // // Replace word-based numbers with numbers(five - 5.0)
                problem = replaceWordNumbers(problem, numberMap);
                // Replace word-based operators with their corresponding symbols(times - *)
                problem = replaceWordOperators(problem);
                // Evaluate the expression
                double result = evaluateSimpleExpression(problem);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Replace word-based numbers with digits
    private static String replaceWordNumbers(String expression, HashMap<String, Double> numberMap) {
        // Loop through each entry in the numberMap (word and its numeric value)
        for (Map.Entry<String, Double> entry : numberMap.entrySet()) {

            // Replace occurrences of the word with its numeric value in the expression
            expression = expression.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }
        // updated
        return expression;
    }



    private static String replaceWordOperators(String expression) {
        // \\b = word boundary
        expression = expression.replaceAll("\\btimes\\b", "*");
        expression = expression.replaceAll("\\bplus\\b", "+");
        expression = expression.replaceAll("\\bminus\\b", "-");
        expression = expression.replaceAll("\\bdivided\\b", "/");
        return expression;
    }


    // Evaluate simple expressions with parentheses using the built-in JavaScript engine
    // The Rhino engine can automatically parse and evaluate these expressions
    // handling the correct precedence and operations
    private static double evaluateSimpleExpression(String expression) {
        Context context = Context.enter(); // Enter the Rhino context
        try {
            Scriptable scope = context.initStandardObjects(); // Initialize a standard Rhino scope

            // evaluate the mathematical expresion as a string
            Object result = context.evaluateString(scope, expression, "<cmd>", 1, null);

            // convert result which is an object to a double
            return ((Number) result).doubleValue();
        } finally {
            Context.exit(); // Exit the Rhino context to free up resources
        }
    }
}




