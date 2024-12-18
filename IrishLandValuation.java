import java.util.Arrays;

public class IrishLandValuation extends LandValuation {
    private final String[] border = {"SLIGO", "DONEGAL", "LEITRIM", "CAVAN", "MONAGHAN", "LOUTH"};
    private final String[] midland = {"LAOIS", "OFFALY", "WESTMEATH", "LONGFORD"};
    private final String[] west = {"MAYO", "GALWAY", "ROSCOMMON"};
    private final String[] southEast = {"WATERFORD", "KILKENNY", "CARLOW", "WEXFORD"};
    private final String dublin = "DUBLIN";
    private final String[] midEast = {"WICKLOW", "KILDARE", "MEATH"};
    private final String[] southWest = {"KERRY", "CORK", "TIPPERARY", "LIMERICK","CLARE"};

    @Override
    protected void initializeRegionPrices() {
        //adding the keys and values of each region to the region prices hashmap
        regionPrices.put("Border", 28330.0);
        regionPrices.put("Midland", 23397.0);
        regionPrices.put("West", 16667.0);
        regionPrices.put("Dublin", 37185.0);
        regionPrices.put("Mid-East", 35578.0);
        regionPrices.put("South-East", 32710.0);
        regionPrices.put("South-West", 24268.0);
    }

    @Override
    public String findRegion(String county) {
        // finding what region the inputted county is in
        county = county.toUpperCase();
        //converting each array to list to use the.contains method
        if (Arrays.asList(border).contains(county)) return "Border";
        if (Arrays.asList(midland).contains(county)) return "Midland";
        if (Arrays.asList(west).contains(county)) return "West";
        if (county.equals(dublin)) return "Dublin";
        if (Arrays.asList(midEast).contains(county)) return "Mid-East";
        if (Arrays.asList(southWest).contains(county)) return "South-West";
        if (Arrays.asList(southEast).contains(county)) return "South-East";
        return "NONE";
    }
}
