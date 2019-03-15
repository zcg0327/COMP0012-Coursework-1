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

// Error fallback
[^]                                   { throw new Error("Illegal character" +
                                        "< " + yytext() + " >"); }
