package examples.example_3.either;

public interface DagpengeSoknad {
    record Avslag(String aarsak) {}
    record Dagpenger(Double belop) {}
}
