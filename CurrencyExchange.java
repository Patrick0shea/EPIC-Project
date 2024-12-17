import java.util.HashMap;
import java.util.Map;

// Abstract class defining reusable properties and methods for currency exchange
public abstract class CurrencyExchange implements CurrencyExchangeInterface {
    //  Holds the value of the Country, Currency, Currency Code and Currency Symbol
    private String Country;
    private String Currency;
    private String CurrencyCode;
    private String CurrencySymbol;

    //  Creates a Hashmap - Hashmap stores key-value-pairs to look up linked information
    private static final Map<String, String[]> countryToCurrencyMap = new HashMap<>();

    //  Static block to initialise the hash-mapping
    static {

        // ChatGPT Generated - List of Countries Based on There Currency - Currency Based off the ECB Webpage
        // Countries using the Euro (EUR)
        String[] euroDetails = {"Euro", "EUR", "€"};
        String[] euroCountries = {"austria", "belgium", "cyprus", "estonia", "finland", "france", "germany", "greece", "ireland", "italy", "latvia", "lithuania", "luxembourg", "malta", "netherlands", "portugal", "slovakia", "slovenia", "spain"};
        for (String country : euroCountries) {
            countryToCurrencyMap.put(country, euroDetails);
        }

        // Countries using the US Dollar (USD)
        String[] usdDetails = {"US Dollar", "USD", "$"};
        String[] usdCountries = {"united states", "us", "america", "usa", "united states of america", "ecuador", "el salvador", "east timor", "panama", "zimbabwe"};
        for (String country : usdCountries) {
            countryToCurrencyMap.put(country, usdDetails);
        }

        // Countries using the British Pound (GBP)
        String[] gbpDetails = {"British Pound", "GBP", "£"};
        String[] gbpCountries = {"gb", "united kingdom", "uk", "wales", "scotland", "england", "northern ireland", "guernsey", "jersey", "isle of man"};
        for (String country : gbpCountries) {
            countryToCurrencyMap.put(country, gbpDetails);
        }

        // Countries using the Swiss Franc (CHF)
        String[] chfDetails = {"Swiss Franc", "CHF", "CHF"};
        String[] chfCountries = {"switzerland", "liechtenstein"};
        for (String country : chfCountries) {
            countryToCurrencyMap.put(country, chfDetails);
        }

        // Countries using the Japanese Yen (JPY)
        String[] jpyDetails = {"Japanese Yen", "JPY", "¥"};
        countryToCurrencyMap.put("japan", jpyDetails);

        // Countries using the Chinese Yuan (CNY)
        String[] cnyDetails = {"Chinese Yuan", "CNY", "¥"};
        countryToCurrencyMap.put("china", cnyDetails);

        // Countries using the Australian Dollar (AUD)
        String[] audDetails = {"Australian Dollar", "AUD", "A$"};
        String[] audCountries = {"australia", "kiribati", "nauru", "tuvalu"};
        for (String country : audCountries) {
            countryToCurrencyMap.put(country, audDetails);
        }

        // Countries using the Canadian Dollar (CAD)
        String[] cadDetails = {"Canadian Dollar", "CAD", "C$"};
        countryToCurrencyMap.put("canada", cadDetails);

        // Countries using the Indian Rupee (INR)
        String[] inrDetails = {"Indian Rupee", "INR", "₹"};
        String[] inrCountries = {"india", "bhutan"};
        for (String country : inrCountries) {
            countryToCurrencyMap.put(country, inrDetails);
        }

        // Countries using the South Korean Won (KRW)
        String[] krwDetails = {"South Korean Won", "KRW", "₩"};
        String[] krwCountries = {"South Korea", "korea"};
        for (String country : krwCountries) {
            countryToCurrencyMap.put(country, krwDetails);
        }
        // Countries using the Singapore Dollar (SGD)
        String[] sgdDetails = {"Singapore Dollar", "SGD", "S$"};
        countryToCurrencyMap.put("singapore", sgdDetails);

        // Countries using the New Zealand Dollar (NZD)
        String[] nzdDetails = {"New Zealand Dollar", "NZD", "NZ$"};
        String[] nzdCountries = {"new zealand", "cook islands", " niue", "tokelau", "pitcairn islands", "zealand"};
        for (String country : nzdCountries) {
            countryToCurrencyMap.put(country, nzdDetails);
        }

        // Countries using the Hong Kong Dollar (HKD)
        String[] hkdDetails = {"Hong Kong Dollar", "HKD", "HK$"};
        String[] hkdCountries = {"hong kong", "hong", "kong"};
        for (String country : hkdCountries) {
            countryToCurrencyMap.put(country, hkdDetails);
        }

        // Countries using the Brazilian Real (BRL)
        String[] brlDetails = {"Brazilian Real", "BRL", "R$"};
        countryToCurrencyMap.put("brazil", brlDetails);

        // Countries using the Mexican Peso (MXN)
        String[] mxnDetails = {"Mexican Peso", "MXN", "$"};
        countryToCurrencyMap.put("mexico", mxnDetails);

        // Countries using the Turkish Lira (TRY)
        String[] tryDetails = {"Turkish Lira", "TRY", "₺"};
        countryToCurrencyMap.put("turkey", tryDetails);

        // Countries using the South African Rand (ZAR)
        String[] zarDetails = {"South African Rand", "ZAR", "R"};
        String[] zarCountries = {"south africa", "lesotho", "eswatini", "namibia", "africa"};
        for (String country : zarCountries) {
            countryToCurrencyMap.put(country, zarDetails);
        }

        // Countries using the Swedish Krona (SEK)
        String[] sekDetails = {"Swedish Krona", "SEK", "kr"};
        countryToCurrencyMap.put("sweden", sekDetails);

        // Countries using the Norwegian Krone (NOK)
        String[] nokDetails = {"Norwegian Krone", "NOK", "kr"};
        countryToCurrencyMap.put("norway", nokDetails);

        // Countries using the Danish Krone (DKK)
        String[] dkkDetails = {"Danish Krone", "DKK", "kr"};
        String[] dkkCountries = {"denmark", "greenland", "faroe islands"};
        for (String country : dkkCountries) {
            countryToCurrencyMap.put(country, dkkDetails);
        }

        // Countries using the Polish Zloty (PLN)
        String[] plnDetails = {"Polish Zloty", "PLN", "zł"};
        countryToCurrencyMap.put("poland", plnDetails);


        // Countries using the Thai Baht (THB)
        String[] thbDetails = {"Thai Baht", "THB", "฿"};
        countryToCurrencyMap.put("thailand", thbDetails);

        // Countries using the Malaysian Ringgit (MYR)
        String[] myrDetails = {"Malaysian Ringgit", "MYR", "RM"};
        countryToCurrencyMap.put("malaysia", myrDetails);

        // Countries using the Philippine Peso (PHP)
        String[] phpDetails = {"Philippine Peso", "PHP", "₱"};
        countryToCurrencyMap.put("philippines", phpDetails);

        // Countries using the Indonesian Rupiah (SAR)
        String[] rupDetails = {"Indonesian rupiah", "IDR", "Rp"};
        countryToCurrencyMap.put("indonesia", rupDetails);

        // Countries using the Israeli Shekel (ILS)
        String[] ilsDetails = {"Israeli Shekel", "ILS", "₪"};
        countryToCurrencyMap.put("israel", ilsDetails);

        // Countries using the Hungarian Forint (HUF)
        String[] hufDetails = {"Hungarian Forint", "HUF", "Ft"};
        countryToCurrencyMap.put("hungary", hufDetails);

        // Countries using the Czech Koruna (CZK)
        String[] czkDetails = {"Czech Koruna", "CZK", "Kč"};
        String[] czkCountries = {"czech republic", "czech"};
        for (String country : czkCountries) {
            countryToCurrencyMap.put(country, czkDetails);
        }
        // Countries using the Romanian Leu (RON)
        String[] ronDetails = {"Romanian Leu", "RON", "RON"};
        countryToCurrencyMap.put("romania", ronDetails);

        // Countries using the Bulgarian Lev (BGN)
        String[] bgnDetails = {"Bulgarian Lev", "BGN", "BGN"};
        countryToCurrencyMap.put("bulgaria", bgnDetails);

        // Countries using the Icelandic Krona (ISK)
        String[] iskDetails = {"Icelandic Krona", "ISK", "kr"};
        countryToCurrencyMap.put("iceland", iskDetails);
    }


    //  Default Constructor
    public CurrencyExchange() {}

    //   The implementation of the getters
    @Override
    public String getCountry() {
        return Country;
    }

    @Override
    public String getCurrency() {
        return Currency;
    }

    @Override
    public String getCurrencyCode() {
        return CurrencyCode;
    }

    @Override
    public String getCurrencySymbol() {
        return CurrencySymbol;
    }

    //   Setter method for all the currency information
    @Override
    public void setCurrencyDetails(String Country, String Currency, String CurrencyCode, String CurrencySymbol) {
        this.Country = Country;
        this.Currency = Currency;
        this.CurrencyCode = CurrencyCode;
        this.CurrencySymbol = CurrencySymbol;
    }


    //  Static method to search and display currency details for a given input
    private void setCurrencyDetails(String Country) {
        String[] CurrencyDetails = countryToCurrencyMap.get(Country.toLowerCase());
        if (CurrencyDetails != null) {
            setCurrencyDetails(Country, CurrencyDetails[0], CurrencyDetails[1], CurrencyDetails[2]);
        }
    }

    //   Search through the users input
    @Override
    public String searchCurrency(String input) {
        String[] words = input.toLowerCase().split("\\s+");
        for (String word : words) {
            if (countryToCurrencyMap.containsKey(word)) {
                setCurrencyDetails(word);
                return String.format("Country: %s, Currency: %s, Code: %s, Symbol: %s",
                        getCountry(), getCurrency(), getCurrencyCode(), getCurrencySymbol());
            }
        }

        return "No Matching Country Found";
    }

    //   Abstract method to be implemented for performing exchange
    public abstract void performExchange();

}
