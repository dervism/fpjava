package monads.types;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FunctorTest {

    @Test
    void fmap() {
        Function<String, String> reverse = s -> new StringBuilder(s).reverse().toString();

        TestFunctor<List<String>> listFunctor = new TestFunctor<>(List.of("hello", "world"));

        var fmap = listFunctor
                .fmap(x -> x.stream().map(String::toUpperCase).toList())
                .fmap(x -> Stream.of(x, x.stream().map(reverse).toList()))
                .fmap(x -> x.flatMap(Collection::stream))
                .fmap(Stream::toList);

        System.out.println(fmap);
    }
}