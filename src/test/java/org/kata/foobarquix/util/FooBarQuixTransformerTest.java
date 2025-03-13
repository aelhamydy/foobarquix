package org.kata.foobarquix.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooBarQuixTransformerTest {

    @Test
    void testDivisibilityAndContainment() {
        assertEquals("FOOFOO", FooBarQuixTransformer.transform(3));
        assertEquals("BARBAR", FooBarQuixTransformer.transform(5));
        assertEquals("QUIX", FooBarQuixTransformer.transform(7));
        assertEquals("FOO", FooBarQuixTransformer.transform(9));
        assertEquals("FOOBAR", FooBarQuixTransformer.transform(51));
        assertEquals("FOOFOOFOO", FooBarQuixTransformer.transform(33));
        assertEquals("FOOBARBAR", FooBarQuixTransformer.transform(15));
        assertEquals("1", FooBarQuixTransformer.transform(1));
    }
}