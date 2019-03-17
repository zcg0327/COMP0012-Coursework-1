package com.jieyouxu.lexer.character_literal;

import com.jieyouxu.lexer.LexerAssertions;
import org.junit.jupiter.api.Test;

public class CharacterLiteralTest {
    @Test
    public void testLegalCharacterLiterals() {
        CharacterLiteralAssertions.assertLiteralEquals("'a'", 'a');
        CharacterLiteralAssertions.assertLiteralEquals("'0'", '0');
        CharacterLiteralAssertions.assertLiteralEquals("','", ',');
        CharacterLiteralAssertions.assertLiteralEquals("';'", ';');
        CharacterLiteralAssertions.assertLiteralEquals("'!'", '!');
        CharacterLiteralAssertions.assertLiteralEquals("'`'", '`');
        CharacterLiteralAssertions.assertLiteralEquals("'@'", '@');
        CharacterLiteralAssertions.assertLiteralEquals("'#'", '#');
        CharacterLiteralAssertions.assertLiteralEquals("'+'", '+');
        CharacterLiteralAssertions.assertLiteralEquals("'/'", '/');
        CharacterLiteralAssertions.assertLiteralEquals("'['", '[');
        CharacterLiteralAssertions.assertLiteralEquals("']'", ']');
        CharacterLiteralAssertions.assertLiteralEquals("'{'", '{');
        CharacterLiteralAssertions.assertLiteralEquals("'}'", '}');
        CharacterLiteralAssertions.assertLiteralEquals("'<'", '<');
        CharacterLiteralAssertions.assertLiteralEquals("'>'", '>');
        CharacterLiteralAssertions.assertLiteralEquals("'('", '(');
        CharacterLiteralAssertions.assertLiteralEquals("')'", ')');
        CharacterLiteralAssertions.assertLiteralEquals("'_'", '_');
        CharacterLiteralAssertions.assertLiteralEquals("' '", ' ');
        CharacterLiteralAssertions.assertLiteralEquals("'\"'", '"');
        CharacterLiteralAssertions.assertLiteralEquals("'\\0'", '\0');
        CharacterLiteralAssertions.assertLiteralEquals("'\\n'", '\n');
        CharacterLiteralAssertions.assertLiteralEquals("'\\\\'", '\\');
        CharacterLiteralAssertions.assertLiteralEquals("'\t'", '\t');
        CharacterLiteralAssertions.assertLiteralEquals("'\f'", '\f');
        CharacterLiteralAssertions.assertLiteralEquals("'\b'", '\b');
        CharacterLiteralAssertions.assertLiteralEquals("'\r'", '\r');
        CharacterLiteralAssertions.assertLiteralEquals("'\n'", '\n');
    }

    @Test
    public void testIllegalCharacterLiterals() {
        LexerAssertions.assertLexingFails("''");
        LexerAssertions.assertLexingFails("'id");
        LexerAssertions.assertLexingFails("' ");
        LexerAssertions.assertLexingFails("'");
        LexerAssertions.assertLexingFails("'ab'");
        LexerAssertions.assertLexingFails("'\n'");
        LexerAssertions.assertLexingFails("'\\'");
    }
}
