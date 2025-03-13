package org.kata.foobarquix.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FooBarQuixTransformer {

    public static String transform(int number) {
        StringBuilder result = new StringBuilder();

        if (number % 3 == 0) result.append("FOO");
        if (number % 5 == 0) result.append("BAR");

        String numStr = String.valueOf(number);
        for (char digit : numStr.toCharArray()) {
            if (digit == '3') result.append("FOO");
            if (digit == '5') result.append("BAR");
            if (digit == '7') result.append("QUIX");
        }

        return result.isEmpty() ? numStr : result.toString();
    }
}