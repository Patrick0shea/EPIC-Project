import java.util.Scanner;

public class BasicMath {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // User input part
        System.out.println("Enter the the number of your desired operation: ");
        System.out.println("");
        System.out.println("1: Addition ");
        System.out.println("2: Subtraction ");
        System.out.println("3: Multiplication ");
        System.out.println("4: Division ");
        System.out.print("Your desired operation: ");
        int operation = input.nextInt();

        System.out.print("What is your first number? ");
        double firstNumber = input.nextDouble();

        System.out.print("What is your second number? ");
        double secondNumber = input.nextDouble();

        // Maths part
        if(operation == 1){
            System.out.println("The result is " + (firstNumber + secondNumber));
        }
        else if(operation == 2){
            System.out.println("The result is " + (firstNumber - secondNumber));
        }
        else if(operation == 3){
            System.out.println("The result is " + (firstNumber * secondNumber));
        }
        else if(operation == 4){
            if(secondNumber == 0){
                System.out.println("Division by zero is undefined");
            } else {
                System.out.println("The result is " + (firstNumber / secondNumber));
            }
        }
    }
}

