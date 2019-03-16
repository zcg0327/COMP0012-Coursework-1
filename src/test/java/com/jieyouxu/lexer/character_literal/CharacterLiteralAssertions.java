package com.jieyouxu.lexer.character_literal;

import com.jieyouxu.Lexer;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CharacterLiteralAssertions {
    private static Lexer lexer;

    public static void assertLiteralEquals(String literal, char expectedCharacter) {
        lexer = new Lexer(new StringReader(literal));

        try {
            assertEquals(expectedCharacter, lexer.next_token().value.toString().charAt(0));
        } catch (IOException e) {
            fail(e.toString());
        }
    }
}
