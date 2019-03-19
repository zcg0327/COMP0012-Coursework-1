package com.jieyouxu.lexer.numeric_literal;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

// NB: rational literals are handled as integer / integer.
public class RationalLiteralTest {
    @Test
    public void testLegalRationalLiterals() {
        var expectedSymbols = sym.RAT_LITERAL;
        LexerAssertions.assertSymbolMatches("0/0", expectedSymbols);
        LexerAssertions.assertSymbolMatches("1/3", expectedSymbols);
        LexerAssertions.assertSymbolMatches("123/456", expectedSymbols);
        LexerAssertions.assertSymbolMatches("345_11/3", expectedSymbols);
        LexerAssertions.assertSymbolMatches("3_45_11/3_2", expectedSymbols);
    }

    @Test
    public void testRationalVersusDivision() {
        LexerAssertions.assertSymbolDoNotMatch("123 / 456", sym.RAT_LITERAL);
    }

    @Test
    public void testIllegalRationalLiterals() {
        LexerAssertions.assertLexingFails("1_/3");
    }
}
