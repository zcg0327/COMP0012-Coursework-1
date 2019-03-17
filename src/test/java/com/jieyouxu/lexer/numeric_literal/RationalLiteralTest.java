package com.jieyouxu.lexer.numeric_literal;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

// NB: rational literals are handled as integer / integer.
public class RationalLiteralTest {
    @Test
    public void testLegalRationalLiterals() {
        var expectedSymbols = List.of(sym.INT_LITERAL, sym.DIV, sym.INT_LITERAL);
        LexerAssertions.assertSymbolsMatch("0/0", expectedSymbols);
        LexerAssertions.assertSymbolsMatch("1/3", expectedSymbols);
        LexerAssertions.assertSymbolsMatch("123/456", expectedSymbols);
        LexerAssertions.assertSymbolsMatch("345_11/3", expectedSymbols);
        LexerAssertions.assertSymbolsMatch("3_45_11/3_2", expectedSymbols);
    }

    @Test
    public void testIllegalRationalLiterals() {
        LexerAssertions.assertLexingFails("1_/3");
    }
}
