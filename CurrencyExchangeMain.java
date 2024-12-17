import java.util.Scanner;


public class CurrencyExchangeMain {
    public static void main(String[] args) {

//      Instance the API Class
        org.example.CurrencyValueAPI api = new org.example.CurrencyValueAPI();

//      Takes in and processes the users input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please Enter in the Country who's currency you want to know more about:");
        String userInput = scanner.nextLine();
        scanner.close(); // Example user input

//      Searches for the currency rate and displays it
        String result = api.searchCurrency(userInput);
        System.out.println(result);
//      Fetch and Display the exchange rate
        api.performExchange();
    }
}
