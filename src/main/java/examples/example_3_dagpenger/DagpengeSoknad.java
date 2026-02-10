package examples.example_3_dagpenger;

public interface DagpengeSoknad {
    record Avslag(String aarsak) {}
    record Dagpenger(Double belop) {}
}
