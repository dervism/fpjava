package examples.example_6_monads.functions_dsl;

import java.util.List;
import java.util.function.Function;

import static functions.fn1.Distinct.distinct;
import static functions.fn2.Filter.filter;
import static functions.fn2.MapFn.map;

public class Functions {

    static void main() {
        var numbers = List.of(2, 5, 7, 2, 5, 8, 2, 9, 3, 6);

        // fn :: [Int] -> [Int]
        // fn = distinct . filter even . map (\x -> x + 1)

        Function<Iterable<Integer>, Iterable<Integer>>
                distinctIntFilterFn =
                    list -> distinct(
                                filter(n -> n % 2 == 0,
                                        map(i -> i + 1, list)));

        System.out.println(
                distinctIntFilterFn.apply(numbers)
        );

        System.out.println(
                numbers.stream()
                        .map(i -> i + 1)
                        .filter(i -> i % 2 == 0)
                        .distinct()
                        .toList()
        );
    }

}
