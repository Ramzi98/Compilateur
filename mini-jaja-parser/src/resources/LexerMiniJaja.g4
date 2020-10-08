/** MiniJaja Lexems from VM Project at UBFC 2020 as read in the given spec
https://moodle.univ-fcomte.fr/mod/page/view.php?id=190601
*/
lexer grammar LexerMiniJaja;

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
INT : 'entier' ;
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