package com.jieyouxu.lexer.comment;

import com.jieyouxu.lexer.LexerAssertions;
import org.junit.jupiter.api.Test;

public class MultiLineCommentTest {
    @Test
    public void assertMultiLineCommentShouldProduceNoSymbols() {
        LexerAssertions.assertEmptyLex("/##/");
        LexerAssertions.assertEmptyLex("/#\n#/");
        LexerAssertions.assertEmptyLex("/###/");
        LexerAssertions.assertEmptyLex("/#/##/");
        LexerAssertions.assertEmptyLex("/#abc123!@#$%^&*()#1#/");
        LexerAssertions.assertEmptyLex("/# # ##/");
        LexerAssertions.assertEmptyLex("/#\n dajd \r\n juxha# \n#/");
        LexerAssertions.assertEmptyLex("\n/#\n dajd \r\n juxha# \n#/\r\n");
    }

    @Test
    public void assertMultiLineCommentWithContentBeforeAndAfterShouldNotBeEmpty() {
        LexerAssertions.assertNonEmptyLex("l/#\n\n #/");
        LexerAssertions.assertNonEmptyLex("/#\r\n##/l");
    }
}
