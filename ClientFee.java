public class ClientFee extends LandTax implements PropertyTaxCalculations {
    private final String clientName = "Auction IQ";
    private double clientFee;

    public ClientFee(String localAuthorityName, double propertyValueBand) {

        super( localAuthorityName,propertyValueBand);
        this.clientFee = calculation();
    }
    public double getClientFee(){
        return clientFee;
    }

    @Override
    public double calculation() {
        clientFee = 0.01 * getPropertyValueBand();
        return clientFee;
    }
    public String toString(){
        System.out.println("============================================================");
        return String.format("Client Name: %s%nName of the relevant Authority: %s%nProperty Value band: %.2f%nClient Fee: %.2f€%n",clientName,getLocalAuthorityName(),getPropertyValueBand(), clientFee);
    }
}
