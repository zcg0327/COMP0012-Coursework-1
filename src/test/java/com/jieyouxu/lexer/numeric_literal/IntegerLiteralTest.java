package com.jieyouxu.lexer.numeric_literal;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

public class IntegerLiteralTest {
    @Test
    public void testLegalIntegerLiterals() {
        LexerAssertions.assertSymbolMatches("0", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("1", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("11", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("123891378", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("1_3", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("1_______3", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("12_24_35_384", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("123_456__789", sym.INT_LITERAL);
        LexerAssertions.assertSymbolMatches("12_3_4", sym.INT_LITERAL);
    }

    @Test
    public void testIllegalIntegerLiterals() {
        LexerAssertions.assertLexingFails("1_");
        LexerAssertions.assertLexingFails("1____");
        LexerAssertions.assertLexingFails("1____3_");
        LexerAssertions.assertLexingFails("_1");
        LexerAssertions.assertLexingFails("_____1");
        LexerAssertions.assertLexingFails("_____1___");
    }
}
