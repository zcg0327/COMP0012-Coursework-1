package com.jieyouxu.lexer.operators;

import com.jieyouxu.lexer.LexerAssertions;
import com.jieyouxu.sym;
import org.junit.jupiter.api.Test;

public class OperatorsTest {
    @Test
    public void testLegalOperators() {
        testGroupingOperators();
        testSeparators();
        testAssignmentOperator();
        testTypeAnnotationOperator();
        testArithmeticOperators();
        testLogicOperators();
        testConcatenationOperator();
        testFieldAccessOperator();
    }

    private void testGroupingOperators() {
        LexerAssertions.assertSymbolMatches("(", sym.LPAREN);
        LexerAssertions.assertSymbolMatches(")", sym.RPAREN);
        LexerAssertions.assertSymbolMatches("[", sym.LBRACKET);
        LexerAssertions.assertSymbolMatches("]", sym.RBRACKET);
        LexerAssertions.assertSymbolMatches("{", sym.LBRACE);
        LexerAssertions.assertSymbolMatches("}", sym.RBRACE);
        LexerAssertions.assertSymbolMatches("<", sym.LANGLE);
        LexerAssertions.assertSymbolMatches(">", sym.RANGLE);
    }

    private void testSeparators() {
        LexerAssertions.assertSymbolMatches(";", sym.SEMICOLON);
        LexerAssertions.assertSymbolMatches(",", sym.COMMA);
    }

    private void testAssignmentOperator() {
        LexerAssertions.assertSymbolMatches(":=", sym.ASSIGN);
    }

    private void testTypeAnnotationOperator() {
        LexerAssertions.assertSymbolMatches(":", sym.COLON);
    }

    private void testArithmeticOperators() {
        LexerAssertions.assertSymbolMatches("+", sym.PLUS);
        LexerAssertions.assertSymbolMatches("-", sym.MINUS);
        LexerAssertions.assertSymbolMatches("/", sym.DIV);
        LexerAssertions.assertSymbolMatches("*", sym.MULT);
    }

    private void testLogicOperators() {
        LexerAssertions.assertSymbolMatches("!", sym.NOT);
        LexerAssertions.assertSymbolMatches("=", sym.EQ);
        LexerAssertions.assertSymbolMatches("!=", sym.NEQ);
        LexerAssertions.assertSymbolMatches("||", sym.OR);
        LexerAssertions.assertSymbolMatches("&&", sym.AND);
        LexerAssertions.assertSymbolMatches("<=", sym.LESS_EQ);
    }

    private void testConcatenationOperator() {
        LexerAssertions.assertSymbolMatches("::", sym.CONCAT);
    }

    private void testFieldAccessOperator() {
        LexerAssertions.assertSymbolMatches(".", sym.DOT);
    }
}
