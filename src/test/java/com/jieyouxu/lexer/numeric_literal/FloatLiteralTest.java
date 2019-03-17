package com.jieyouxu.lexer.numeric_literal;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FloatLiteralTest {
    @Test
    public void testLegalFloatLiterals() {
        LexerAssertions.assertSymbolMatches("0.0", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("0.1", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("0.12345678", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("3.14", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("319389.1", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("319389.1128336", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("1_2.3_4", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("1__2.3__4", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("1_2_3.4_5_6_7", sym.FLOAT_LITERAL);
        LexerAssertions.assertSymbolMatches("1_2___3.4_5__6_7", sym.FLOAT_LITERAL);
    }

    @Test
    public void testIllegalFloatLiterals() {
        LexerAssertions.assertSymbolsMatch(".0", List.of(sym.DOT, sym.INT_LITERAL));
        LexerAssertions.assertSymbolsMatch("0.", List.of(sym.INT_LITERAL, sym.DOT));
        LexerAssertions.assertSymbolMatches(".", sym.DOT);
        LexerAssertions.assertLexingFails("_0.");
        LexerAssertions.assertLexingFails("0_.");
        LexerAssertions.assertLexingFails("0_._");
        LexerAssertions.assertLexingFails("_0_._");
        LexerAssertions.assertLexingFails("_0_.0_");
        LexerAssertions.assertLexingFails("_0_._0_");
    }
}
