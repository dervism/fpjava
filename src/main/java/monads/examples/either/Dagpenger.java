package monads.examples.either;

import java.util.List;

public class Dagpenger {
    public static void main(String[] args) {
        var dagpengerResponse = DagpengerFnEither.dagpengerFn.apply(List.of(500000d, 450000d, 400000d));

        String response = dagpengerResponse.either(
                avslag -> "Avslag: " + avslag.aarsak(),
                dagpenger -> "Dagpenger: " + dagpenger.belop()
        );

        System.out.println(response);
    }
}
