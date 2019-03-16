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
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r | \n | \r\n
InputCharacter = [^\r\n]
Whitespace = {LineTerminator} | " " | \t | \f

// Comments
Comment = {SingleLineComment} | {MultiLineComment}

SingleLineComment = "#" {InputCharacter}* {LineTerminator}

MultiLineComment = "/#" {MultiLineCommentContent}* [#]+ "/"
MultiLineCommentContent = ( [^#] | ( [#]+ [^\/]* ) )*

// Identifier
Identifier = {Letter} {LetterDigitUnderscore}*
Letter = [a-zA-Z]
Digit = [0-9]
LetterDigit = {Letter} | {Digit}
LetterDigitUnderscore = {LetterDigit} | "_"

// Security label
SecurityLabel = {Low} | {High}
Low = "L"
High = "H"

// TODO

%%

// TODO

<YYINITIAL> {
    // Comments
    {Comment}                       { /* ignore */ }

    // Whitespace
    {Whitespace}                    { /* ignore */ }
}

// Error fallback
[^]                                 { throw new IllegalArgumentException(
                                        "Illegal character < "
                                        + yytext()
                                        + " >"); }
