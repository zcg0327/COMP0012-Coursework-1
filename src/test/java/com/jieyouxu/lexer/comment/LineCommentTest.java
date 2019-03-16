package com.jieyouxu.lexer.comment;

import com.jieyouxu.LexerAssertions;
import org.junit.jupiter.api.*;

public class LineCommentTest {

    @DisplayName("Positive Tests")
    @Nested
    class PositiveTests {

        @Test
        public void lineCommentShouldProduceNoSymbols() {
            LexerAssertions.assertEmptyLex("#\n");
            LexerAssertions.assertEmptyLex(" #\n");
            LexerAssertions.assertEmptyLex(" #\t\n");
            LexerAssertions.assertEmptyLex("\n#\n");
            LexerAssertions.assertEmptyLex("\n#\n");
            LexerAssertions.assertEmptyLex("\r\n#\r\n");
            LexerAssertions.assertEmptyLex("#\r\n");
            LexerAssertions.assertEmptyLex("#a123\\c*xf(()####\n");
            LexerAssertions.assertEmptyLex("##\n");
            LexerAssertions.assertEmptyLex("##\r\n");
        }
    }
}
