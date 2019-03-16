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
    private StringBuffer stringLiteral = new StringBuffer();

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


Comment = {SingleLineComment} | {MultiLineComment}

MultiLineComment = "/#" {MultiLineCommentContent}* [#]+ "/"
MultiLineCommentContent = ( [^#] | ( [#]+ [^\/]* ) )*

SingleLineComment = "#" {InputCharacter}* {LineTerminator}?


Identifier = {Letter} {LetterDigitUnderscore}*
Letter = [a-zA-Z]
Digit = [0-9]
LetterDigit = {Letter} | {Digit}
LetterDigitUnderscore = {LetterDigit} | "_"

%state CHARACTER_LITERAL
%state STRING_LITERAL

%%

// Keywords
<YYINITIAL> "main"                  { return symbol(sym.MAIN); }
<YYINITIAL> "L"                     { return symbol(sym.SECURITY_LOW); }
<YYINITIAL> "H"                     { return symbol(sym.SECURITY_HIGH); }
<YYINITIAL> "char"                  { return symbol(sym.CHAR); }
<YYINITIAL> "bool"                  { return symbol(sym.BOOL); }
<YYINITIAL> "int"                   { return symbol(sym.INT); }
<YYINITIAL> "rat"                   { return symbol(sym.RAT); }
<YYINITIAL> "float"                 { return symbol(sym.FLOAT); }
<YYINITIAL> "seq"                   { return symbol(sym.SEQ); }
<YYINITIAL> "top"                   { return symbol(sym.TOP); }
<YYINITIAL> "len"                   { return symbol(sym.LEN); }
<YYINITIAL> "in"                    { return symbol(sym.IN); }
<YYINITIAL> "tdef"                  { return symbol(sym.TDEF); }
<YYINITIAL> "alias"                 { return symbol(sym.ALIAS); }
<YYINITIAL> "fdef"                  { return symbol(sym.FDEF); }
<YYINITIAL> "read"                  { return symbol(sym.READ); }
<YYINITIAL> "print"                 { return symbol(sym.PRINT); }
<YYINITIAL> "if"                    { return symbol(sym.IF); }
<YYINITIAL> "then"                  { return symbol(sym.THEN); }
<YYINITIAL> "else"                  { return symbol(sym.ELSE); }
<YYINITIAL> "fi"                    { return symbol(sym.FI); }
<YYINITIAL> "loop"                  { return symbol(sym.LOOP); }
<YYINITIAL> "pool"                  { return symbol(sym.POOL); }
<YYINITIAL> "break"                 { return symbol(sym.BREAK); }
<YYINITIAL> "return"                { return symbol(sym.RETURN); }
<YYINITIAL> "T"                     { return symbol(sym.TRUE); }
<YYINITIAL> "F"                     { return symbol(sym.FALSE); }


<YYINITIAL> {

    /* Literals */
    // Character Literal
    \'                              { yybegin(CHARACTER_LITERAL); }

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

    \\.                             {  return symbol(sym.BADCHAR, yycharat(0)); }
    {LineTerminator}                {  return symbol(sym.BADCHAR, yycharat(0)); }
}


// Error
[^]                                 { return symbol(sym.BADCHAR, yytext()); }
