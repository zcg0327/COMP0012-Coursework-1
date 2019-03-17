package com.jieyouxu.lexer.identifier;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IdentifierTest {
    @Test
    public void testLegalIdentifiers() {
        LexerAssertions.assertSymbolMatches("a", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches(" b", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches(" c ", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("abcdef", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("A", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("AbCdEf", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a_1", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a_1b", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a_a1", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a1", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a123", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("a1c3", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("l", sym.IDENTIFIER);
        LexerAssertions.assertSymbolMatches("h", sym.IDENTIFIER);
    }

    @Test
    public void testIllegalIdentifiers() {
        LexerAssertions.assertLexingFails("_abc");
        LexerAssertions.assertLexingFails("_1");
        LexerAssertions.assertLexingFails("$");
        LexerAssertions.assertLexingFails("@");
        LexerAssertions.assertLexingFails("`");
        LexerAssertions.assertSymbolsMatch("a$b",
                                           List.of(sym.IDENTIFIER, sym.BADCHAR, sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a&b",
                                           List.of(sym.IDENTIFIER, sym.BADCHAR, sym.IDENTIFIER));
        LexerAssertions.assertSymbolDoNotMatch("1", sym.IDENTIFIER);
        LexerAssertions.assertSymbolDoNotMatch("1_", sym.IDENTIFIER);
        LexerAssertions.assertSymbolDoNotMatch("L", sym.IDENTIFIER);
        LexerAssertions.assertSymbolDoNotMatch("H", sym.IDENTIFIER);
    }
}
