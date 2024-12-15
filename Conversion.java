import java.util.HashMap;

// Base class for conversions could create new conversion hashmaps

public abstract class Conversion {
    protected HashMap<String, Double> conversionFactor;

    public Conversion() {
        conversionFactor = new HashMap<>();
        initializeConversionFactors();
    }

    // Method to be implemented by subclasses to define conversion factors
    protected abstract void initializeConversionFactors();

    // Perform the conversion
    public double convert(double quantity, String fromUnit, String targetUnit) {



        // From Unit error handling
        if (!conversionFactor.containsKey(fromUnit)) {
            throw new IllegalArgumentException("Unsupported 'from' unit: " + fromUnit + "\n" + "Please Try Again");
        }


        // target unit error handling
        if (!conversionFactor.containsKey(targetUnit)) {
            throw new IllegalArgumentException("Unsupported 'to' unit: " + targetUnit + "\n" + "Please Try Again" );
        }






        // Convert to square metres first
        double inSquareMetres = quantity * conversionFactor.get(fromUnit);




        // Convert to the target unit
        return inSquareMetres / conversionFactor.get(targetUnit);




    }
}


