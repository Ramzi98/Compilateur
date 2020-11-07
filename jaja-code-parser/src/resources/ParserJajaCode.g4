
parser grammar ParserJajaCode;

options {
    tokenVocab=LexerJajaCode;
}

classe : adresse instr SEMI classe
        | WS
        ;

instr : INIT
      | SWAP
      | NEW LPAR ident COMMA type COMMA sorte COMMA adr RPAR
      | NEWARRAY LPAR ident COMMA type RPAR
      | INVOKE LPAR ident RPAR
      | RETURN
      | WRITE
      | WRITELN
      | PUSH LPAR valeur RPAR
      | POP
      | LOAD LPAR ident RPAR
      | ALOAD LPAR ident RPAR
      | STORE LPAR ident RPAR
      | ASTORE LPAR ident RPAR
      | IF LPAR adresse RPAR
      | GOTO LPAR adresse RPAR
      | INC LPAR ident RPAR
      | AINC LPAR ident RPAR
      | oper
      | NOP
      | STOP
      ;

ident : Identifier;

valeur : NumberLitteral
        | BoolLitteral
        | StringLitteral
        | WS
        ;

adresse : NumberLitteral;

oper : oper2
     | oper1
     ;

oper1 : NEG
      | NOT
      ;

oper2 : ADD
      | SUB
      | MUL
      | DIV
      | CMP
      | SUP
      | OR
      | AND
      ;

type : INT
     | BOOLEAN
     | VOID
     ;

sorte : CST
      | VAR
      | METH
      ;

adr : NumberLitteral
    ;