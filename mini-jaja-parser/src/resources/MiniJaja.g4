 /** MiniJaja from VM Project at UBFC 2020 as read in the given spec
 https://moodle.univ-fcomte.fr/mod/page/view.php?id=190601
 */
 grammar MiniJaja;
/* ====================== PARSER ===================== */


 classe              : CLASS ident LBRACE decls methmain RBRACE
                     ;

 ident               :Identifier
                     ;

 decls               : decl SEMI decls
                     | //eps
                     ;

 decl               : var
                     | methode
                     ;

 vars                : var SEMI vars
                     | //eps
                     ;

 var                 : typemeth ident vexp
                     | typemeth ident LBRACK exp RBRACK
                     | FINAL type ident vexp
                     ;

 vexp                : ASSIGN exp
                     | //eps
                     ;

 methode             : typemeth ident LPAR entetes RPAR LBRACE vars instrs RBRACE
                     ;

 methmain            : MAIN LBRACE vars instrs RBRACE
                     ;

 entetes             : entete COMMA entetes
                     | entete
                     | //eps
                     ;

 entete              : type ident
                     ;

 instrs              : instr SEMI instrs
                     | //eps
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
                     | //eps
                     ;

 exp                 : BANG exp1 e1
                     | exp1 e1
                     ;

 e1                  : AND exp1
                     | OR exp1
                     | //eps
                     ;

 exp1                : exp2 e2
                     ;


 e2                  : EQUAL exp2
                     | GT exp2
                     | //eps
                     ;

 exp2                : SUB terme e3
                     | terme e3
                     ;

 e3                  : ADD terme
                     | SUB terme
                     | //eps
                     ;

 terme               : fact e4
                     ;

 e4                  : MUL fact
                     | DIV fact
                     | //eps
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
 /* ====================== KEYWORDS ===================== */

 CLASS : 'class' ;
 FINAL : 'final' ;
 MAIN : 'main' ;

 RETURN : 'return' ;
 WRITE : 'write' ;
 WRITELN : 'writeln' ;
 IF : 'if' ;
 ELSE : 'else' ;
 WHILE : 'while' ;

 /* ==================== SEPARATORS ==================== */

 LBRACK : '[' ;
 RBRACK : ']' ;
 LPAR   : '(' ;
 RPAR   : ')' ;
 LBRACE : '{' ;
 RBRACE : '}' ;

 COMMA  : ',' ;
 SEMI   : ';' ;

 /* ===================== OPERATORS ===================== */

 ASSIGN : '=';
 GT : '>';
 LT : '<';
 BANG : '!';
 EQUAL : '==';
 AND : '&&';
 OR : '||';
 INC : '++';
 DEC : '--';
 ADD : '+';
 SUB : '-';
 MUL : '*';
 DIV : '/';

 ADD_ASSIGN : '+=';
 SUB_ASSIGN : '-=';

 /* ======================= TYPES ======================= */

 VOID : 'void' ;
 INT : 'int' ;
 BOOLEAN : 'boolean';
 STRING : 'string';

 /* ===================== LITERRALS ===================== */

 BoolLitteral
    :	'true'
    |	'false'
    ;

 // String Literals
 StringLitteral : '"' StringCharacter* '"' ;

 fragment StringCharacter
     : ~["\\\r\n]                // all chars except escape sequences
    | EscapeSequence
    ;
 fragment EscapeSequence : '\\' [btnfr"'\\] ;

 // Identier
 Identifier : NonDigit ( NonDigit | Digit | '_' ) * ;

 fragment NonDigit : [a-zA-Z_] ;

 // Number Literals
 NumberLitteral
    :	DigitSequence '.' Digit* ExponentPart?
    |	'.' Digit* ExponentPart?
    |	DigitSequence ExponentPart
    |	DigitSequence
    ;

 fragment ExponentPart : [eE] SignedInteger ;
 fragment SignedInteger : [+-]? DigitSequence ;
 fragment DigitSequence : '0' | NonZeroDigit Digit* ;
 fragment Digit:	'0' | NonZeroDigit;
 fragment NonZeroDigit : [1-9] ;

 /* ======================= SKIPS ======================= */

 WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

 /* ====================== COMMENTS ===================== */

 COMMENT : '/*' .*? '*/' -> skip ; // skip multiline comments
 LINE_COMMENT : '//' ~[\r\n]* -> skip ; // skip line comments


