package com.jieyouxu.lexer.boolean_literal;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

public class BooleanLiteralTest {
    @Test
    public void testLegalBooleanLiteral() {
        LexerAssertions.assertSymbolMatches("T", sym.TRUE);
        LexerAssertions.assertSymbolMatches("F", sym.FALSE);
    }

    @Test
    public void testIllegalBooleanLiteral() {
        LexerAssertions.assertSymbolDoNotMatch("t", sym.TRUE);
        LexerAssertions.assertSymbolDoNotMatch("f", sym.FALSE);
    }
}
