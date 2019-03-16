package com.jieyouxu.lexer.keywords;

import com.jieyouxu.Lexer;
import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

public class KeywordsTest {
    @Test
    public void assertKeywordsAreMatched() {
        LexerAssertions.assertSymbolMatches("main", sym.MAIN);
        LexerAssertions.assertSymbolMatches("L", sym.SECURITY_LOW);
        LexerAssertions.assertSymbolMatches("H", sym.SECURITY_HIGH);
        LexerAssertions.assertSymbolMatches("char", sym.CHAR);
        LexerAssertions.assertSymbolMatches("bool", sym.BOOL);
        LexerAssertions.assertSymbolMatches("int", sym.INT);
        LexerAssertions.assertSymbolMatches("rat", sym.RAT);
        LexerAssertions.assertSymbolMatches("float", sym.FLOAT);
        LexerAssertions.assertSymbolMatches("seq", sym.SEQ);
        LexerAssertions.assertSymbolMatches("top", sym.TOP);
        LexerAssertions.assertSymbolMatches("len", sym.LEN);
        LexerAssertions.assertSymbolMatches("in", sym.IN);
        LexerAssertions.assertSymbolMatches("tdef", sym.TDEF);
        LexerAssertions.assertSymbolMatches("alias", sym.ALIAS);
        LexerAssertions.assertSymbolMatches("fdef", sym.FDEF);
        LexerAssertions.assertSymbolMatches("read", sym.READ);
        LexerAssertions.assertSymbolMatches("print", sym.PRINT);
        LexerAssertions.assertSymbolMatches("if", sym.IF);
        LexerAssertions.assertSymbolMatches("then", sym.THEN);
        LexerAssertions.assertSymbolMatches("else", sym.ELSE);
        LexerAssertions.assertSymbolMatches("fi", sym.FI);
        LexerAssertions.assertSymbolMatches("loop", sym.LOOP);
        LexerAssertions.assertSymbolMatches("pool", sym.POOL);
        LexerAssertions.assertSymbolMatches("break", sym.BREAK);
        LexerAssertions.assertSymbolMatches("return", sym.RETURN);
        LexerAssertions.assertSymbolMatches("T", sym.TRUE);
        LexerAssertions.assertSymbolMatches("F", sym.FALSE);
    }
}
