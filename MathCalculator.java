import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathCalculator {
    public static void main(String[] args) {

        // values as words and digits
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


        Scanner input = new Scanner(System.in);

        System.out.print("Enter your problem: ");
        String problem = input.nextLine();

        problem = problem.toLowerCase();

        // works for five times 5, 4 squared, square root of 55 and 5 * 5
        // add more featurs by adding to th regex and then the switch case
        Pattern pattern = Pattern.compile("^(-?\\w+)?\\s*(to the power of|times|\\*|plus|\\+|minus|-|divided|square root of|cubed root of)\\s*(-?\\w+)$");

        Matcher matcher = pattern.matcher(problem);

        if (matcher.matches()) {
            try {
                String firstPart = matcher.group(1);
                String operator = matcher.group(2);
                String secondPart = matcher.group(3);


                // num1 always has a value
                double num1;
                // num2 assigned to zero so it has a value regardless of the user input
                double num2 = 0.0;

                // For unary operations like "square root of 25"
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
            System.out.println("Invalid input format." + "\n" + "Please try again");
        }
    }

    // Method to parse a number either as a word or as a digit
    private static double parseNumber(String input, HashMap<String, Double> numberMap) {
        if (input == null) {
            throw new IllegalArgumentException("Invalid number input.");
        }
        // deals with negative numbers
        if (input.startsWith("-")) {
            String positivePart = input.substring(1);
            if (numberMap.containsKey(positivePart)) {
                return -numberMap.get(positivePart);
            } else {
                return Double.parseDouble(input);
            }
        } else if (numberMap.containsKey(input)) {
            return numberMap.get(input);
        } else {
            return Double.parseDouble(input);
        }
    }

    // Method performs actual calculations
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
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed :(");
                }
                return num1 / num2;
            case "cubed":
                return Math.pow(num1, 3);
            case "squared":
                return Math.pow(num1, 2);
            case "to the power of":
                return Math.pow(num1, num2);
            case "square root of":
                if (num1 < 0 ) {
                    throw new ArithmeticException("You cant get the square root of a negative number :(" + "\n" + "Please try again ");
                }
                return Math.sqrt(num1);
            case "cubed root of":
                if (num1 < 0) {
                    throw new ArithmeticException("You cant get the cubed root of a negative number :(" + "\n" + "Please try again ");
                }
                return Math.cbrt(num1);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator + "\n" + "Please try again :)");
        }
    }
}
