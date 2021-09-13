package maybe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {

    @Test
    void maybe_returns_nothing() {
        Maybe<Object> maybe = Maybe.maybe(null);

        assertTrue(maybe instanceof Nothing);
        assertTrue(maybe.isNothing());
    }
}