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

            // replaces each word-based number in the expression with its numeric equivalent. For
            // .getKey() = word-based number
            // entry.getValue().toString()
            // Converts the numeric value associated with the word-based number to a string.
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

        // Sets up the environment needed to run JavaScript code within a Java program
        Context context = Context.enter();

        try {
            // scope = javascript code can run here
            // Scriptable is an interface in Rhino
            // that represents a collection of objects and properties available to JavaScript code
            // initStandardObjects() creates a standard set of JavaScript objects and functions
            Scriptable scope = context.initStandardObjects();

            // context class evaluates a string as a JavaScript expression
            //<cmd> = generic placeholder indicating the command being evaluated.
            // 1 = starting line number for the script.
            // null means there’s no special security context applied.
            // its an object because the result could be different types
            Object result = context.evaluateString(scope, expression, "<cmd>", 1, null);

            // convert result which is an object to number type and then number type to double
            return ((Number) result).doubleValue();

        } finally {
            Context.exit(); // Exit the Rhino context to free up resources
        }
    }
}




