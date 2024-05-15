package monads.examples.either;

public interface DagpengeSoknad {
    record Avslag(String aarsak) {}
    record Dagpenger(Double belop) {}
}
