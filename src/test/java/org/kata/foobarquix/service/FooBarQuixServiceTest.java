package org.kata.foobarquix.service;

import org.junit.jupiter.api.Test;
import org.kata.foobarquix.util.FooBarQuixTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooBarQuixServiceTest {
    private final FooBarQuixService service = new FooBarQuixService();

    @Test
    void testServiceDelegatesToTransformer() {
        assertEquals(FooBarQuixTransformer.transform(3), service.transform(3));
        assertEquals(FooBarQuixTransformer.transform(15), service.transform(15));
    }
}
