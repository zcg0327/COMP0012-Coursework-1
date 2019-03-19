package com.jieyouxu.lexer.string_literal;

import com.jieyouxu.Lexer;
import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class StringLiteralTest {
    @Test
    public void testLegalStringLiterals() {
        LexerAssertions.assertSymbolMatches("\"\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"abc\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"123\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"$%\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\\"\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\t\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\f\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\b\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\0\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\" \"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\n\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"\\r\\n\"", sym.STRING_LITERAL);
        LexerAssertions.assertSymbolMatches("\"'\"", sym.STRING_LITERAL);
    }

    @Test
    public void testIllegalStringLiterals() {
        LexerAssertions.assertLexingFails("\"\\\"");
        LexerAssertions.assertLexingFails("\"\b\"");
        LexerAssertions.assertLexingFails("\"\t\"");
        LexerAssertions.assertLexingFails("\"\f\"");
        LexerAssertions.assertLexingFails("\"\n\"");
        LexerAssertions.assertLexingFails("\"\r\"");
        LexerAssertions.assertLexingFails("\"\0\"");

        // NB: The extreme case where the file ends with a double quote, without a newline
        // is unlikely. In this case, the rest of unterminated string is discarded and the parser
        // should be able to pick up unexpected EOF. Regex is insufficient for matching paired
        // delimiters; context-free grammar is required.
        LexerAssertions.assertSymbolsMatch("id\"", List.of(sym.IDENTIFIER, sym.BADCHAR));
        LexerAssertions.assertSymbolsMatch("\"id\n", List.of(sym.BADCHAR, sym.BADCHAR));
        LexerAssertions.assertSymbolsMatch("\"\n", List.of(sym.BADCHAR, sym.BADCHAR));
    }
}
