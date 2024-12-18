public interface CurrencyExchangeInterface {
    //  Methods for information from the CurrencyExchange Class
    String getCountry();
    String getCurrency();
    String getCurrencyCode();
    String getCurrencySymbol();

    //  Method to set all the Information to a single Country
    void setCurrencyDetails(String country, String currency, String currencyCode, String currencySymbol);

    //  Method for the Currency Exchange rate from the CurrencyValueAPI
    String searchCurrency(String input);
}
