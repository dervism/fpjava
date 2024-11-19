package monads.either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LeftTest {

    @Test
    void value() {
        var l = new Left<Integer, String>(1);
        assertEquals(1, l.leftValue());
        assertNull(l.rightValue());
    }
}