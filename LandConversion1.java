public abstract class LandConversion1 extends Conversion {

    @Override
     public void initializeConversionFactors() {


       // hashmap
        conversionFactor.put("acres", 4046.856);
        conversionFactor.put("hectares", 10000.0);
        conversionFactor.put("square feet", 0.092903);
        conversionFactor.put("feet squared", 0.092903);
        conversionFactor.put("square metres", 1.0);
        conversionFactor.put("metres squared", 1.0);
        conversionFactor.put("square centimetres", 0.0001);
        conversionFactor.put("centimetres squared", 0.0001);
        conversionFactor.put("square yards", 0.836127);
        conversionFactor.put("yards squared", 0.836127);
        conversionFactor.put("square miles", 2589988.11);
        conversionFactor.put("miles squared", 2589988.11);
        conversionFactor.put("square kilometres", 1000000.0);
        conversionFactor.put("kilometres squared", 1000000.0);
        conversionFactor.put("feet", 0.092903);
        conversionFactor.put("centimetres", 0.0001);
    }

}
