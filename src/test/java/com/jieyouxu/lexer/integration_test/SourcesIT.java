package com.jieyouxu.lexer.integration_test;

import com.jieyouxu.sym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SourcesIT {
    private static final String baseDirectory = "src/main/resources/open-test-cases/";

    private IntegrationTestRunner runner;

    private List<Integer> expectedSymbols;

    @BeforeEach
    public void setUp() {
        runner = new IntegrationTestRunner(baseDirectory);
    }

    private void expectSymbols(Integer... expectedSymbols) {
        this.expectedSymbols = Arrays.asList(expectedSymbols);
    }

    private void assertSuccessfulLex(String fileName) {
        runner.runTest(fileName, expectedSymbols);
    }

    @Test
    public void testAbstractDataType() {
        expectSymbols(
            sym.TDEF,           // tdef
            sym.IDENTIFIER,     // person
            sym.LBRACE,         // {
            sym.IDENTIFIER,     // name
            sym.COLON,          // :
            sym.IDENTIFIER,     // string
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // surname
            sym.COLON,          // :
            sym.IDENTIFIER,     // string
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // age
            sym.COLON,          // :
            sym.INT,            // int
            sym.SECURITY_LOW,   // L
            sym.RBRACE,         // }
            sym.SEMICOLON,      // ;
            sym.TDEF,           // tdef
            sym.IDENTIFIER,     // family
            sym.LBRACE,         // {
            sym.IDENTIFIER,     // mother
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // father
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // children
            sym.COLON,          // :
            sym.SEQ,            // seq
            sym.LANGLE,         // <
            sym.IDENTIFIER,     // person
            sym.RANGLE,         // >
            sym.RBRACE,         // }
            sym.SEMICOLON,      // ;
            sym.MAIN,           // main
            sym.LBRACE,         // {
            sym.IDENTIFIER,     // m
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.ASSIGN,         // :=
            sym.STRING_LITERAL, // "aaaaAAA"
            sym.COMMA,          // ,
            sym.STRING_LITERAL, // "bbBB0_i"
            sym.COMMA,          // ,
            sym.INT_LITERAL,    // 40
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // p
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.ASSIGN,         // :=
            sym.STRING_LITERAL, // "aaabAAA"
            sym.COMMA,          // ,
            sym.STRING_LITERAL, // "bbBB0_i"
            sym.COMMA,          // ,
            sym.INT_LITERAL,    // 35
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // c1
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.ASSIGN,         // :=
            sym.STRING_LITERAL, // "aaabAAA"
            sym.COMMA,          // ,
            sym.STRING_LITERAL, // "bbBB0_i"
            sym.COMMA,          // ,
            sym.INT_LITERAL,    // 1
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // c2
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.ASSIGN,         // :=
            sym.STRING_LITERAL, // "aaadAAA"
            sym.COMMA,          // ,
            sym.STRING_LITERAL, // "bbBB0_i"
            sym.COMMA,          // ,
            sym.INT_LITERAL,    // 2
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // c3
            sym.COLON,          // :
            sym.IDENTIFIER,     // person
            sym.ASSIGN,         // :=
            sym.STRING_LITERAL, // "aaaeAAA"
            sym.COMMA,          // ,
            sym.STRING_LITERAL, // "bbBB0_i"
            sym.COMMA,          // ,
            sym.INT_LITERAL,    // 3
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // f
            sym.COLON,          // :
            sym.IDENTIFIER,     // family
            sym.ASSIGN,         // :=
            sym.IDENTIFIER,     // m
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // p
            sym.COMMA,          // ,
            sym.LBRACKET,       // [
            sym.IDENTIFIER,     // c1
            sym.COMMA,          // ,
            sym.IDENTIFIER,     // c2
            sym.RBRACKET,       // ]
            sym.SEMICOLON,      // ;
            sym.IDENTIFIER,     // f
            sym.DOT,            // .
            sym.IDENTIFIER,     // children
            sym.ASSIGN,         // :=
            sym.IDENTIFIER,     // f
            sym.DOT,            // .
            sym.IDENTIFIER,     // children
            sym.CONCAT,         // ::
            sym.LBRACKET,       // [
            sym.IDENTIFIER,     // c3
            sym.RBRACKET,       // ]
            sym.SEMICOLON,      // ;
            sym.RETURN,         // return
            sym.SEMICOLON,      // ;
            sym.RBRACE,         // }
            sym.SEMICOLON,      // ;
            sym.FDEF,           // fdef
            sym.IDENTIFIER,     // bar
            sym.LPAREN,         // (
            sym.RPAREN,         // )
            sym.LBRACE,         // {
            sym.PRINT,          // print
            sym.STRING_LITERAL, // "Another function after main."
            sym.SEMICOLON,      // ;
            sym.RBRACE,         // }
            sym.SEMICOLON       // ;
        );
        assertSuccessfulLex("p-adt.s");
    }
}
