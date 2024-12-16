public class ClientFee extends LandTax implements PropertyTaxCalculations {
    private String clientName = "Auction IQ";
    private double clientFee;

    public ClientFee(String localAuthorityName, int propertyValueBand) {

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
        return String.format("Client Name: %s%nName of the relevant Authority: %s%nProperty Value band: %d%nClient Fee: %.2f€%n",clientName,getLocalAuthorityName(),getPropertyValueBand(), clientFee);
    }
}
