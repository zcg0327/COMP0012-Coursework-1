package com.jieyouxu.lexer.security_label;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SecurityLabelTest {
    @Test
    public void testLowLabel() {
        LexerAssertions.assertSymbolsMatch("L", List.of(sym.SECURITY_LOW));
        LexerAssertions.assertSymbolDoNotMatch("l", sym.SECURITY_LOW);
    }

    @Test
    public void testHighLabel() {
        LexerAssertions.assertSymbolsMatch("H", List.of(sym.SECURITY_HIGH));
        LexerAssertions.assertSymbolDoNotMatch("h", sym.SECURITY_HIGH);
    }
}
