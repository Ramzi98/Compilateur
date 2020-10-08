parser grammar ParserJajaCode;

options {
    tokenVocab=LexerJajaCode;
}

s : Identifier ASSIGN NumberLitteral;