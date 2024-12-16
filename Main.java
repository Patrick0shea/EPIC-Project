
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("====== Financial Calculator ======");
        System.out.println("Please choose an option from the menu below: ");
        boolean continueCalculation = true;
        while(continueCalculation ){
          try{

             System.out.println("-----------------------------------");
             System.out.printf("%nChoose an option:%n1 - Land Tax Calculations%n2 - Checking Client Fee%n0 - Exit%n");
             System.out.print("Your choice: ");
             int userChoice = input.nextInt();



            PropertyTaxCalculations[] data = new PropertyTaxCalculations[3];
            if(userChoice==1 ) {
             System.out.print("Enter name of your Land's relevant Authority: ");
             input.nextLine();
             String localAuthorityName = input.nextLine();

             System.out.print("Enter total range of Valuation of your property:");
             int propertyValueBand = input.nextInt();



            data[0] = new LandTax(localAuthorityName,propertyValueBand);
        }
        else if (userChoice==2) {
            System.out.println("\n--- Client Fee Check ---");
            System.out.print("Enter name of your Land's relevant Authority: ");
            input.nextLine();
            String localAuthorityName = input.nextLine();

            System.out.print("Enter total range of Valuation of your property:");
            int propertyValueBand = input.nextInt();





            data[1] = new ClientFee(localAuthorityName,propertyValueBand);
        }
        else if (userChoice == 0) {
            continueCalculation = false;
            System.out.println("Thank you for using the financial calculator.");

        }
        else{
            System.out.println("Invalid option! Please enter a valid option from the menu.");
        }
        for(PropertyTaxCalculations calculations : data){
            if(calculations != null){
             System.out.println(calculations.calculation());
                System.out.println(calculations.toString());
         }
        }


            }catch(InputMismatchException e){
              System.err.println("Invalid input! please enter a valid input.");
            }catch(Exception e){
              System.err.println("An error occured!");
          }
        }

  input.close();
}
}
