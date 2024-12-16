import java.util.HashMap;

public class LandTax implements PropertyTaxCalculations {

    private String localAuthorityName;
    private int propertyValueBand;
    private double lptCharge;

//    HashMap for additional tax value according to the local authority

    private static final HashMap<String, Double> localAuthorityRate = new HashMap<String, Double>();
    static{
        localAuthorityRate.put("CARLOW", -0.15);
        localAuthorityRate.put("CAVAN", 0.15);
        localAuthorityRate.put("CLARE", 0.15);
        localAuthorityRate.put("CORK CITY", 0.12);
        localAuthorityRate.put("CORK COUNTY", 0.10);
        localAuthorityRate.put("DUBLIN", -0.15);
        localAuthorityRate.put("DUN LAOGHAIRE RATHDOWN", -0.15);
        localAuthorityRate.put("FINGAL", -0.075);
        localAuthorityRate.put("GALWAY", 0.15);
        localAuthorityRate.put("KERRY", 0.10);
        localAuthorityRate.put("KILDARE", 0.10);
        localAuthorityRate.put("KILKENNY", 0.15);
        localAuthorityRate.put("LAOISE", 0.15);
        localAuthorityRate.put("LEITRIM", 0.15);
        localAuthorityRate.put("LIMERICK", 0.15);
        localAuthorityRate.put("LANGFORD", 0.15);
        localAuthorityRate.put("LOUTH", 0.0);
        localAuthorityRate.put("MAYO", 0.10);
        localAuthorityRate.put("MEATH", 0.0);
        localAuthorityRate.put("MONAGHAN", 0.15);
        localAuthorityRate.put("TIPPERARRY", 0.15);
        localAuthorityRate.put("OFFALY", 0.15);
        localAuthorityRate.put("ROSCOMMON", 0.15);
        localAuthorityRate.put("SLIGO", 0.15);
        localAuthorityRate.put("SOUTH DUBLIN", -0.15);
        localAuthorityRate.put("WATERFORD", 0.15);
        localAuthorityRate.put("WESTMEATH", 0.15);
        localAuthorityRate.put("WEXFORD", 0.15);
        localAuthorityRate.put("WICKLOW", 0.06);
    }

    public LandTax(String localAuthorityName, int propertyValueBand) {
    if(localAuthorityName == null || localAuthorityName.trim().isEmpty()){
        throw new IllegalArgumentException("Local Authority name cannot be null or empty.");
    }
    if (propertyValueBand < 0){
        throw new IllegalArgumentException("Property value band cannot be negative.");
    }


        this.localAuthorityName = localAuthorityName.toUpperCase();

        this.propertyValueBand = propertyValueBand;

     try {
         this.lptCharge = calculation();
     } catch (Exception e) {
         System.err.println("Error calculating the LPT tax: "+ e.getMessage());
         this.lptCharge = 0;
     }

    }
    public String getLocalAuthorityName() {
        return localAuthorityName;
    }

    public int getPropertyValueBand() {
        return propertyValueBand;
    }


    public double getLptCharge() {
        return lptCharge;
    }
//  Overridden Interface method for overall calculation
    @Override
    public double calculation() {
        double basicTax = basicValueBandTax(propertyValueBand);
        double localAuthorityAdjustment = localAuthorityRate.getOrDefault(localAuthorityName,0.0);
        return basicTax * (1 + localAuthorityAdjustment);

    }
//    Method for calculating both below 17.5 Million property value band
//    and above 17.5 Million property value band

    public double basicValueBandTax(int propertyValueBand){
        if(propertyValueBand <= 1750000){
            return lowValueBandTax(propertyValueBand);
        }else{
            return highValueBand(propertyValueBand);
        }
    }
//    Method for basic tax under 17.5 Million property value band
    private double lowValueBandTax(int propertyValueBand){
        if (propertyValueBand > 0 && propertyValueBand <= 200000) {
            return  90;
        } else if (propertyValueBand > 200000 && propertyValueBand <= 262500) {

            return  225;
        } else if (propertyValueBand > 262500 && propertyValueBand <= 350000) {

            return  315;
        } else if (propertyValueBand > 350000 && propertyValueBand <= 437500) {

            return  405;
        } else if (propertyValueBand > 437500 && propertyValueBand <= 525000) {

            return 495;
        } else if (propertyValueBand > 525000 && propertyValueBand <= 612500) {

            return 585;
        } else if (propertyValueBand > 612500 && propertyValueBand <= 700000) {

            return 675;
        } else if (propertyValueBand > 700000 && propertyValueBand <= 787500) {

            return 765;
        } else if (propertyValueBand > 787500 && propertyValueBand <= 875000) {

            return 855;
        } else if (propertyValueBand > 875000 && propertyValueBand <= 962500) {

            return 945;
        } else if (propertyValueBand > 962500 && propertyValueBand <= 1050000) {

            return 1035;
        } else if (propertyValueBand > 1050000 && propertyValueBand <= 1137500) {

            return 1189;
        } else if (propertyValueBand > 1137500 && propertyValueBand <= 1225000) {

            return 1408;
        } else if (propertyValueBand > 1225000 && propertyValueBand <= 1312500) {

            return 1627;
        } else if (propertyValueBand > 1312500 && propertyValueBand <= 1400000) {

            return 1846;
        } else if (propertyValueBand > 1400000 && propertyValueBand <= 1487500) {

            return 2064;
        } else if (propertyValueBand > 1487500 && propertyValueBand <= 1575000) {

            return 2283;
        } else if (propertyValueBand > 1575000 && propertyValueBand <= 1662500) {

            return 2502;
        } else if (propertyValueBand > 1662500 && propertyValueBand <= 1750000) {

            return 2721;
        }else{
            return 0;
        }
    }

    // Method for the properties which have property value band over 17.5 Million
    private double highValueBand(double propertyValueBand) {
        double lptCharge = 0;
        // Charges on the first €1.05 million at 0.1029%
        lptCharge += 0.001029 * 1050000;
        // Charges on the portion between €1.05 million and €1.75 million at 0.25%
        lptCharge += 0.0025 * (1750000 - 1050000);
        // Additional charges if the value exceeds €1.75 million
        if (propertyValueBand > 1750000) {
            lptCharge += 0.003 * (propertyValueBand - 1750000);
        }
        return lptCharge;
    }

    public String toString() {
//        variable to get the local authority increase or decrease
        double incrementOrReduction = localAuthorityRate.getOrDefault(localAuthorityName.toUpperCase(),0.0);
        return String.format("Name of the County: %s%nProperty Valuation Band: %d€%nLocal Authority Increase/Decrease: %.2f%%%nTotal Local Property Tax including local authority increase: %.2f€%n", getLocalAuthorityName(), getPropertyValueBand(),incrementOrReduction * 100, getLptCharge());
    }

}
