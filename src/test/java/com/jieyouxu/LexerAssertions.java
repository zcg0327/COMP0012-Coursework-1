package com.jieyouxu;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class LexerAssertions {
    private static Lexer lexer;
    private static Iterator<Integer> expectedSymbolsIterator;

    public static void assertLexing(String input, List<Integer> expectedSymbols) {
        setupLexer(input);
        setupIterator(expectedSymbols);

        try {
            assertSymbolsMatchInOrder();
            assertEndOfInput();
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    private static void setupLexer(String input) {
        Objects.requireNonNull(input);
        lexer = new Lexer(new StringReader(input));
    }

    private static void setupIterator(List<Integer> expectedSymbols) {
        Objects.requireNonNull(expectedSymbols);
        expectedSymbolsIterator = expectedSymbols.iterator();
    }

    private static void assertSymbolsMatchInOrder() throws IOException {
        while (expectedSymbolsIterator.hasNext()) {
            int nextActualSymbol = lexer.next_token().sym;
            int nextExpectedSymbol = expectedSymbolsIterator.next();
            assertEquals(nextExpectedSymbol, nextActualSymbol);
        }
    }

    private static void assertEndOfInput() throws IOException {
        assertEquals(sym.EOF, lexer.next_token().sym);
    }

    public static void assertEmptyLex(String input) {
        setupLexer(input);

        try {
            assertEquals(sym.EOF, lexer.next_token().sym, "not an empty parse");
        } catch (IOException e) {
            fail(e.toString());
        }
    }
}
