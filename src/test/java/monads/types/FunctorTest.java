package monads.types;

import examples.example_6_monads.functors.GenericFunctor;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctorTest {

    @Test
    void fmap() {
        Function<String, String> reverse = s -> new StringBuilder(s).reverse().toString();

        GenericFunctor<List<String>> listFunctor = new GenericFunctor<>(List.of("hello", "world"));

        var fmap = listFunctor
                .fmap(x -> x.stream().map(String::toUpperCase).toList())
                .fmap(x -> Stream.of(x, x.stream().map(reverse).toList()))
                .fmap(x -> x.flatMap(Collection::stream))
                .fmap(Stream::toList);

        assertEquals("TestFunctor[a=[HELLO, WORLD, OLLEH, DLROW]]", fmap.toString());
    }
}