package examples.example_6_monads.functions_dsl;

import java.util.List;
import java.util.function.Function;

import static functions.fn.Fn.pipe;
import static functions.fn1.Distinct.distinct;
import static functions.fn2.Filter.filter;
import static functions.fn2.MapFn.map;

public class Functions {

    static void main() {
        var numbers = List.of(2, 5, 7, 2, 5, 8, 2, 9, 3, 6);

        // Haskell-like implementation
        // fn :: [Int] -> [Int]
        // fn = distinct . filter even . map (\x -> x + 1)
        Function<Iterable<Integer>, Iterable<Integer>> distinctIntFilterFn =
                map((Integer i) -> i + 1)
                        .andThen(filter(n -> n % 2 == 0))
                        .andThen(distinct());

        Function<Iterable<Integer>, Iterable<Integer>> distinctIntFilterFnPipe =
                pipe(
                        map(i -> i + 1),
                        filter(n -> n % 2 == 0),
                        distinct()
                );
        System.out.println(
                distinctIntFilterFn.apply(numbers)
        );

        System.out.println(
                distinctIntFilterFnPipe.apply(numbers)
        );

        // The equaling Java version using Streams.
        System.out.println(
                numbers.stream()
                        .map(i -> i + 1)
                        .filter(i -> i % 2 == 0)
                        .distinct()
                        .toList()
        );
    }

}
