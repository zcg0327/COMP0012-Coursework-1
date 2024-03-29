package com.jieyouxu.lexer;

import com.jieyouxu.Lexer;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class LexerAssertions {
    private static Lexer lexer;
    private static Iterator<Integer> symbolIterator;

    public static void assertSymbolMatches(String input, int expectedSymbol) {
        setupLexer(input);

        try {
            assertEquals(expectedSymbol, lexer.next_token().sym);
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public static void assertSymbolsMatch(String input, List<Integer> expectedSymbols) {
        setupLexer(input);
        setupIterator(expectedSymbols);

        checkSymbols();
    }

    private static void checkSymbols() {
        try {
            assertSymbolsMatchInOrder();
            assertEndOfInput();
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public static void assertSymbolsMatch(Reader reader, List<Integer> expectedSymbols) {
        setupLexer(reader);
        setupIterator(expectedSymbols);

        checkSymbols();
    }

    private static void setupLexer(String input) {
        Objects.requireNonNull(input);
        lexer = new Lexer(new StringReader(input));
    }

    private static void setupLexer(Reader reader) {
        Objects.requireNonNull(reader);
        lexer = new Lexer(reader);
    }

    private static void setupIterator(List<Integer> symbols) {
        Objects.requireNonNull(symbols);
        symbolIterator = symbols.iterator();
    }

    private static void assertSymbolsMatchInOrder() throws IOException {
        while (symbolIterator.hasNext()) {
            int nextActualSymbol = lexer.next_token().sym;
            int nextExpectedSymbol = symbolIterator.next();

            if (nextExpectedSymbol != nextActualSymbol) {
                fail(
                    "\n"
                    + "Expected: " + sym.terminalNames[nextExpectedSymbol] + "\n"
                    + "Actual: " + sym.terminalNames[nextActualSymbol] + "\n"
                );
            }
        }
    }

    private static void assertEndOfInput() throws IOException {
        assertEquals(sym.EOF, lexer.next_token().sym);
    }

    public static void assertSymbolDoNotMatch(String input, int unexpectedSymbol) {
        setupLexer(input);

        try {
            assertNotEquals(unexpectedSymbol, lexer.next_token().sym);
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public static void assertEmptyLex(String input) {
        setupLexer(input);

        try {
            assertEquals(sym.EOF, lexer.next_token().sym);
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public static void assertNonEmptyLex(String input) {
        setupLexer(input);

        try {
            assertNotEquals(sym.EOF, lexer.next_token().sym);
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public static void assertLexingFails(String input) {
        setupLexer(input);

        try {
            int nextActualToken = lexer.next_token().sym;
            if (nextActualToken != sym.BADCHAR) {
                fail("Actual Symbol: " + sym.terminalNames[nextActualToken]);
            }
        } catch (IOException e) {
            fail(e.toString());
        }
    }
}
