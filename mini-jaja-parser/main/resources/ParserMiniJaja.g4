parser grammar ParserMiniJaja;

options {
    tokenVocab=LexerMiniJaja;
}



classe              : CLASS ident LBRACE decls methmain RBRACE
                    ;

ident               :Identifier
                    ;

decls               : decl SEMI decls
                    | WS
                    ;

decl               : var
                    | methode
                    ;

vars                : var SEMI vars
                    | WS
                    ;

var                 : typemeth ident vexp
                    | typemeth ident LBRACK exp RBRACK
                    | FINAL type ident vexp
                    ;

vexp                : ASSIGN exp
                    | WS
                    ;

methode             : typemeth ident LPAR entetes RPAR LBRACE vars instrs RBRACE
                    ;

methmain            : MAIN LBRACE vars instrs RBRACE
                    ;

entetes             : entete COMMA entetes
                    | entete
                    | WS
                    ;

entete              : type ident
                    ;

instrs              : instr SEMI instrs
                    | WS
                    ;

instr               : ident1 EQUAL exp
                    | ident1 ADD_ASSIGN exp
                    | ident1 INC exp1
                    | ident '(' listexp ')'
                    | RETURN exp
                    | WRITE '(' ( ident | STRING ) ')'
                    | WRITELN '(' ( ident | STRING ) ')'
                    | IF exp LBRACE instrs RBRACE ELSE LBRACE instrs RBRACE
                    | IF exp LBRACE instrs RBRACE
                    | WHILE LPAR exp RPAR LBRACE instrs RBRACE
                    ;

listexp             : exp COMMA listexp
                    | exp
                    | WS
                    ;

exp                 : BANG exp1 e1
                    | exp1 e1
                    ;

e1                  : AND exp1
                    | OR exp1
                    | WS
                    ;

exp1                : exp2 e2
                    ;


e2                  : EQUAL exp2
                    | GT exp2
                    | WS
                    ;

exp2                : SUB terme e3
                    | terme e3
                    ;

e3                  : ADD terme
                    | SUB terme
                    | WS
                    ;

terme               : fact e4
                    ;

e4                  : MUL fact
                    | DIV fact
                    | WS
                    ;

fact                : ident1
                    | ident LPAR listexp RPAR
                    | BoolLitteral
                    | NumberLitteral
                    | LPAR exp RPAR
                    ;

ident1              : ident
                    | ident LBRACK exp RBRACK
                    ;

typemeth            : VOID
                    | type
                    ;


type                : INT
                    | BOOLEAN
                    ;

