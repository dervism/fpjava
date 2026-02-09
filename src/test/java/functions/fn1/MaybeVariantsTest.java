package functions.fn1;

import monads.maybe.Just;
import monads.maybe.Maybe;
import monads.maybe.Nothing;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaybeVariantsTest {

    @Test
    void maybeHead_returns_just_on_non_empty() {
        Maybe<Integer> result = MaybeHead.maybeHead(List.of(1, 2, 3));

        assertInstanceOf(Just.class, result);
        assertEquals(1, result.value());
    }

    @Test
    void maybeHead_returns_nothing_on_empty() {
        Maybe<Integer> result = MaybeHead.maybeHead(List.of());

        assertInstanceOf(Nothing.class, result);
        assertTrue(result.isNothing());
    }

    @Test
    void maybeTail_returns_just_on_non_empty() {
        Maybe<Iterable<Integer>> result = MaybeTail.maybeTail(List.of(1, 2, 3));

        assertInstanceOf(Just.class, result);
        assertEquals(List.of(2, 3), result.value());
    }

    @Test
    void maybeTail_returns_nothing_on_empty() {
        Maybe<Iterable<Integer>> result = MaybeTail.maybeTail(List.of());

        assertInstanceOf(Nothing.class, result);
        assertTrue(result.isNothing());
    }

    @Test
    void maybeLast_returns_just_on_non_empty() {
        Maybe<Integer> result = MaybeLast.maybeLast(List.of(1, 2, 3));

        assertInstanceOf(Just.class, result);
        assertEquals(3, result.value());
    }

    @Test
    void maybeLast_returns_nothing_on_empty() {
        Maybe<Integer> result = MaybeLast.maybeLast(List.of());

        assertInstanceOf(Nothing.class, result);
        assertTrue(result.isNothing());
    }

    @Test
    void maybeInit_returns_just_on_non_empty() {
        Maybe<Iterable<Integer>> result = MaybeInit.maybeInit(List.of(1, 2, 3));

        assertInstanceOf(Just.class, result);
        assertEquals(List.of(1, 2), result.value());
    }

    @Test
    void maybeInit_returns_nothing_on_empty() {
        Maybe<Iterable<Integer>> result = MaybeInit.maybeInit(List.of());

        assertInstanceOf(Nothing.class, result);
        assertTrue(result.isNothing());
    }
}
