// FIXME reminder to remove package statement when done
package com.jieyouxu;

import java_cup.runtime.*;

%%

%class Lexer
%public
%unicode
%cup
%line
%column

%{
    private StringBuffer characters = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r | \n | \r\n
Whitespace = {LineTerminator} | " " | \t | \f
InputCharacter = [^\r\n]

Comment = {MultiLineComment} | {SingleLineComment}

MultiLineComment = "/#" [^#]* ~"#/"

SingleLineComment = "#" {InputCharacter}* {LineTerminator}?

Identifier = {Letter} {LetterDigitUnderscore}*
Letter = [a-zA-Z]
Digit = [0-9]
LetterDigit = {Letter} | {Digit}
Underscore = "_"
LetterDigitUnderscore = {LetterDigit} | {Underscore}

IntegerLiteral = {Digit}+({Underscore}*{Digit}+)*
IllegalIntegerLiteral = {Underscore}*{IntegerLiteral}{Underscore}+

FloatLiteral = {IntegerLiteral} "." {IntegerLiteral}
IllegalFloatLiteral = {IllegalIntegerLiteral} "." {IllegalIntegerLiteral}

RationalLiteral = {IntegerLiteral} "/" {IntegerLiteral}
IllegalFloatLiteral = {IllegalIntegerLiteral} "/" {IllegalIntegerLiteral}

%state CHARACTER_LITERAL
%state STRING_LITERAL

%%


<YYINITIAL> {
    /* Keywords */
    "main"                          { return symbol(sym.MAIN); }
    "char"                          { return symbol(sym.CHAR); }
    "bool"                          { return symbol(sym.BOOL); }
    "int"                           { return symbol(sym.INT); }
    "rat"                           { return symbol(sym.RAT); }
    "float"                         { return symbol(sym.FLOAT); }
    "seq"                           { return symbol(sym.SEQ); }
    "top"                           { return symbol(sym.TOP); }
    "in"                            { return symbol(sym.IN); }
    "tdef"                          { return symbol(sym.TDEF); }
    "alias"                         { return symbol(sym.ALIAS); }
    "fdef"                          { return symbol(sym.FDEF); }
    "read"                          { return symbol(sym.READ); }
    "print"                         { return symbol(sym.PRINT); }
    "if"                            { return symbol(sym.IF); }
    "then"                          { return symbol(sym.THEN); }
    "else"                          { return symbol(sym.ELSE); }
    "fi"                            { return symbol(sym.FI); }
    "loop"                          { return symbol(sym.LOOP); }
    "pool"                          { return symbol(sym.POOL); }
    "break"                         { return symbol(sym.BREAK); }
    "return"                        { return symbol(sym.RETURN); }

    /* Security Label */
    "L"                             { return symbol(sym.SECURITY_LOW); }
    "H"                             { return symbol(sym.SECURITY_HIGH); }

    /* Boolean Literals */
    "T"                             { return symbol(sym.TRUE); }
    "F"                             { return symbol(sym.FALSE); }

    /* Numeric Literals */
    {IntegerLiteral}                { return symbol(sym.INT_LITERAL, yytext()); }
    {IllegalIntegerLiteral}         { return symbol(sym.BADCHAR, yytext()); }

    // NB: Negative integers handled via unary minus
    // NB: Rationals handled via division

    {FloatLiteral}                  { return symbol(sym.FLOAT_LITERAL, yytext()); }
    {IllegalFloatLiteral}           { return symbol(sym.BADCHAR, yytext()); }

    {RationalLiteral}               { return symbol(sym.RAT_LITERAL, yytext()); }
    {IllegalFloatLiteral}           { return symbol(sym.BADCHAR, yytext()); }

    /* Operators */

    // Grouping
    "("                             { return symbol(sym.LPAREN); }
    ")"                             { return symbol(sym.RPAREN); }
    "["                             { return symbol(sym.LBRACKET); }
    "]"                             { return symbol(sym.RBRACKET); }
    "{"                             { return symbol(sym.LBRACE); }
    "}"                             { return symbol(sym.RBRACE); }
    "<"                             { return symbol(sym.LANGLE); }
    ">"                             { return symbol(sym.RANGLE); }

    // Separators
    ";"                             { return symbol(sym.SEMICOLON); }
    ","                             { return symbol(sym.COMMA); }

    // Assignment
    ":="                            { return symbol(sym.ASSIGN); }

    // Type Annotation
    ":"                             { return symbol(sym.COLON); }

    // Arithmetic
    "+"                             { return symbol(sym.PLUS); }
    "-"                             { return symbol(sym.MINUS); }
    "*"                             { return symbol(sym.MULT); }
    "/"                             { return symbol(sym.DIV); }
    "^"                             { return symbol(sym.EXP); }

    // Logic
    "!"                             { return symbol(sym.NOT); }
    "="                             { return symbol(sym.EQ); }
    "!="                            { return symbol(sym.NEQ); }
    "||"                            { return symbol(sym.OR); }
    "&&"                            { return symbol(sym.AND); }
    // NB: Less than sign '<' matched by LANGLE
    "<="                            { return symbol(sym.LESS_EQ); }

    // Concatenation
    "::"                            { return symbol(sym.CONCAT); }

    // Field access
    "."                             { return symbol(sym.DOT); }

    /* Character Literal */
    \'                              { yybegin(CHARACTER_LITERAL); }

    /* String Literal */
    \"                              { yybegin(STRING_LITERAL); }
    \"                              { return symbol(sym.BADCHAR, yytext()); }

    /* Comments */
    {Comment}                       { /* ignore */ }

    /* Whitespace */
    {Whitespace}                    { /* ignore */ }

    /* Identifier */
    {Identifier}                    { return symbol(sym.IDENTIFIER, yytext()); }
}


<CHARACTER_LITERAL> {
    // Single ASCII character which do not need escape
    [^\r\n\\\']\'                   { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, yycharat(0)); }

    // Escape characters begin with \
    "\\0"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\0'); }
    "\\\\"\'                        { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\\'); }
    "\\t"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\t'); }
    "\\n"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\n'); }
    "\\r"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\r'); }
    "\\'"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\''); }
    "\\b"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\b'); }
    "\\f"\'                         { yybegin(YYINITIAL); return symbol(sym.CHAR_LITERAL, '\f'); }

    <<EOF>>                         { yybegin(YYINITIAL); return symbol(sym.BADCHAR, yytext()); }
    \\.                             { return symbol(sym.BADCHAR, yycharat(0)); }
    {LineTerminator}                { return symbol(sym.BADCHAR, yycharat(0)); }
}


<STRING_LITERAL> {
    // End-of-string
    \"                              { yybegin(YYINITIAL); return symbol(
                                        sym.STRING_LITERAL, characters.toString()); }

    // Non-escape characters (1 or more)
    [^\r\n\t\b\f\0\\\"]+            { characters.append(yytext()); }

    // Escape characters begin with \
    "\\0"                           { characters.append('\0'); }
    "\\\\"                          { characters.append('\\'); }
    "\\t"                           { characters.append('\t'); }
    "\\n"                           { characters.append('\n'); }
    "\\r"                           { characters.append('\r'); }
    "\\\""                          { characters.append('\"'); }
    "\\b"                           { characters.append('\b'); }
    "\\f"                           { characters.append('\f'); }

    <<EOF>>                         { yybegin(YYINITIAL); return symbol(sym.BADCHAR, yytext()); }
    \\.                             { return symbol(sym.BADCHAR, yytext()); }
    {LineTerminator}                { return symbol(sym.BADCHAR, yytext()); }
}


// Error
[^]                                 { return symbol(sym.BADCHAR, yytext()); }
