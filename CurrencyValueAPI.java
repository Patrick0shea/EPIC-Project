import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CurrencyValueAPI extends CurrencyExchange {
    public CurrencyValueAPI() {
        super();
    }

    @Override
    public void performExchange() {
        fetchExchangeRate();
    }

    //  fetches the Exchange rate from the ECB webpage
    public void fetchExchangeRate() {
        try {
//          Creates a HTTP Client
            HttpClient client = HttpClient.newHttpClient();
//          Request to fetch the ECB webpage
            HttpRequest request = HttpRequest.newBuilder()
//                  Link to the European Centeral Bank Webpage - subpage Exchange rates
                    .uri(URI.create("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html"))
                    .build();

//          Send the request and recieves back in the form of the HTML's code
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

//          Extract the exchange rate via regex (Regular Expression - used to search for specific <td> and/or <span> elements
            String currencyCode = getCurrencyCode();
            if (currencyCode == null || currencyCode.isEmpty()) {
                System.out.println("No currency code available.");
                return;
            }

            String regex = "<td id=\"" + currencyCode + "\".*?<span class=\"rate\">(\\d+\\.\\d+)</span>";
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(html);

            if (matcher.find()) {
                String rate = matcher.group(1);
                System.out.println("Exchange Rate: " + rate);
            } else {
                System.out.println("Currency " + currencyCode + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
