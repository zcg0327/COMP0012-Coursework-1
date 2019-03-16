package com.jieyouxu.lexer.identifier;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IdentifierTest {
    @Test
    public void testLegalIdentifiers() {
        LexerAssertions.assertSymbolsMatch("a", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch(" b", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch(" c ", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("abcdef", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("A", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("AbCdEf", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a_1", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a_1b", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a_a1", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a1", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a123", List.of(sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a1c3", List.of(sym.IDENTIFIER));
    }

    @Test
    public void testIllegalIdentifiers() {
        LexerAssertions.assertParsingFails("_abc");
        LexerAssertions.assertParsingFails("_1");
        LexerAssertions.assertParsingFails("$");
        LexerAssertions.assertParsingFails("@");
        LexerAssertions.assertParsingFails("`");
        LexerAssertions.assertSymbolsMatch("a$b",
                                           List.of(sym.IDENTIFIER, sym.BADCHAR, sym.IDENTIFIER));
        LexerAssertions.assertSymbolsMatch("a&b",
                                           List.of(sym.IDENTIFIER, sym.BADCHAR, sym.IDENTIFIER));
        LexerAssertions.assertSymbolDoNotMatch("1", sym.IDENTIFIER);
        LexerAssertions.assertSymbolDoNotMatch("1_", sym.IDENTIFIER);
    }
}
