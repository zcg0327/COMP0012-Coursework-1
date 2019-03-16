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
    private StringBuffer string = new StringBuffer();

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

SingleLineComment = "#" {InputCharacter}* {LineTerminator}

MultiLineComment = "/#" {MultiLineCommentContent}* [#]+ "/"
MultiLineCommentContent = ( [^#] | ( [#]+ [^\/]* ) )*


Identifier = {Letter} {LetterDigitUnderscore}*
Letter = [a-zA-Z]
Digit = [0-9]
LetterDigit = {Letter} | {Digit}
LetterDigitUnderscore = {LetterDigit} | "_"

%state IDENTIFIER
%state STRING

%%

// Keywords
<YYINITIAL> "L"                     { return symbol(sym.SECURITY_LOW); }
<YYINITIAL> "H"                     { return symbol(sym.SECURITY_HIGH); }

<YYINITIAL> {
    // Comments
    {Comment}                       { /* ignore */ }

    // Whitespace
    {Whitespace}                    { /* ignore */ }

    // Identifier
    {Identifier}                    { return symbol(sym.IDENTIFIER, yytext()); }
}


// Error fallback
[^]                                 { throw new IllegalArgumentException(
                                        "Illegal character < "
                                        + yytext()
                                        + " >"); }
