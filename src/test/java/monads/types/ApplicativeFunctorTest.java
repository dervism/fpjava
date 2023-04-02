package monads.types;

import monads.examples.applicatives.GenericApplicative;
import monads.examples.applicatives.ListApplicative;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicativeFunctorTest {

    @Test
    void addApplicatives() {
        GenericApplicative<Integer> g1 = new GenericApplicative<>(1);
        GenericApplicative<Integer> g2 = new GenericApplicative<>(2);

        Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

        GenericApplicative<Integer> applicative = g2.apply(g1.fmap(add));

        System.out.println(applicative);
        assertEquals(3, applicative.value());
    }

    @Test
    void apply() {

        Function<Integer, Integer> f = x -> x + 2;
        Function<Integer, Integer> f2 = x -> x + 5;

        ListApplicative<Integer> list = new ListApplicative<>(List.of(1, 2, 3));
        System.out.println(
                list.fmap(f)
        );

        assertEquals("ListApplicativeFunctor{list=[3, 4, 5]}", list.fmap(f).toString());

        ListApplicative<Integer> list2 = new ListApplicative<>(List.of(1, 2, 3));
        ListApplicative<Function<Integer, Integer>> a = new ListApplicative<>(List.of(f, f2));

        System.out.println(
                list2.apply(a)
        );

        assertEquals("ListApplicativeFunctor{list=[3, 4, 5, 6, 7, 8]}", list2.apply(a).toString());
    }
}