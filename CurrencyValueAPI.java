import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class CurrencyValueAPI extends CurrencyExchange {

    //the standard exchange rate is the users inputs a country that uses the euro
    private double StandardExchangeRate = 1.00;
    //  Amount to be converted, €1 will be converted if no amount is entered by the user
    private double amount = 1.0;

    //  Constuctor to initalise the class and call the super/parent constructor
    public CurrencyValueAPI() {
        super();
    }

    @Override
    public String searchCurrency(String input) {
        String result = super.searchCurrency(input);
//      The amount of money inputted by the user
        findAmount(input);
        return result;
    }

    //    Finds teh amount of money inputted by the user
    private void findAmount(String input) {
//      Expression to match numbers (including decimal/float numbers)
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            try {
                this.amount = Double.parseDouble(matcher.group());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing number: " + e.getMessage());
            }
        }
    }


    //  Performs the currency exchange based on the amount
//  If the currency is the Euro it uses the basic exchange rate of 1 is to 1
//  Otherwise, it fetches the exchange rate from the ECB website
    @Override
    public void performExchange() {
        if ("EUR".equals(getCurrencyCode())) {
            System.out.printf("Amount: %.2f %s\n", amount, getCurrencyCode());
            System.out.printf("Exchange Rate: %.2f (The chosen Country uses the Euro aswell)\n", StandardExchangeRate);
            System.out.printf("Converted Amount: %.2f EUR\n", amount);
        } else {
//          Fetch the exchange rate for the currencies
            fetchExchangeRate();
        }
    }

    public void fetchExchangeRate() {
        try {
            // Creates a HTTP Client
            HttpClient client = HttpClient.newHttpClient();
            // Request to fetch the ECB webpage
            HttpRequest request = HttpRequest.newBuilder()
                    // Link to the European Central Bank Webpage - subpage Exchange rates
                    .uri(URI.create("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html"))
                    .build();

            // Send the request and receives back in the form of the HTML's code
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

            // Extract the exchange rate via regex (Regular Expression - used to search for specific <td> and/or <span> elements
            String currencyCode = getCurrencyCode();
            if (currencyCode == null || currencyCode.isEmpty()) {
                System.out.println("No currency code available.");
                return;
            }

//          Regex to find the exchange rate for the specified currency in the HTML
            String regex = "<td id=\"" + currencyCode + "\".*?<span class=\"rate\">(\\d+\\.\\d+)</span>";
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(html);

            if (matcher.find()) {
//              Parses the matched exchange rate
                String rateStr = matcher.group(1);
                double rate = Double.parseDouble(rateStr);
//              Display the details found/conducted
                System.out.printf("Amount: %.2f %s\n", amount, currencyCode);
                System.out.printf("Exchange Rate: %.4f\n", rate);
                double convertedAmount = amount / rate;
                System.out.printf("Converted Amount: %.2f EUR\n", convertedAmount);
            } else {
                System.out.println("Currency " + currencyCode + " not found.");
            }
        } catch (Exception e) {
//          Handle any exceptions that occurs from the HTTP request/process
            System.out.println("An error occurred while fetching the exchange rate: " + e.getMessage());
        }
    }
}
