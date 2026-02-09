package examples.example_6_monads.functors;

public record Meter(Number<Integer> meters) {

    public Number<Double> convertToFeet() {
        return meters.fmap(m -> m * 3.28084);
    }

    public Number<Double> convertToMiles() {
        // meters to miles
        return meters.fmap(m -> m * 0.000621371);
    }

    public Number<Double> convertToKilometers() {
        // meters to kilometers
        return meters.fmap(m -> m * 0.001);
    }

    public Number<Double> convertKmToMiles() {
        return meters
                .fmap(m -> m * 0.001)
                .fmap(km -> km * 0.621371);
    }

    public Number<String> convertKmToMilesStr() {
        return meters
                .fmap(m -> m * 0.001)
                .fmap(km -> km * 0.621371)
                .fmap(miles -> "Km -> Miles: " + miles);
    }

}
