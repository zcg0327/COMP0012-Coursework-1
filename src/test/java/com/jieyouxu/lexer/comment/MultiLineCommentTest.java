package com.jieyouxu.lexer.comment;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void assertMultiLineCommentFailsIfUnmatched() {
        LexerAssertions.assertSymbolsMatch("/#", List.of(sym.DIV));
        LexerAssertions.assertSymbolsMatch("/##//", List.of(sym.DIV));
    }
}
