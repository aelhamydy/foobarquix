package org.kata.foobarquix.service;

import org.kata.foobarquix.util.FooBarQuixTransformer;
import org.springframework.stereotype.Service;

@Service
public class FooBarQuixService {
    public String transform(int number) {
        return FooBarQuixTransformer.transform(number);
    }
}