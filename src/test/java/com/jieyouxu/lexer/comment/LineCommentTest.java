package com.jieyouxu.lexer.comment;

import com.jieyouxu.lexer.LexerAssertions;
import org.junit.jupiter.api.Test;

public class LineCommentTest {
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

    @Test
    public void lineCommentWithContentBeforeShouldNotBeEmpty() {
        LexerAssertions.assertNonEmptyLex("id#\n");
    }
}
