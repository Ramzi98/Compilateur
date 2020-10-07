/** JajaCode Lexems from VM Project at UBFC 2020 as read in the given spec
https://moodle.univ-fcomte.fr/mod/page/view.php?id=190601
*/
lexer grammar LexerJajaCode;

/* ====================== KEYWORDS ===================== */

INIT     : 'init' ;
STOP     : 'jcstop' ;
SWAP     : 'swap' ;
NEW      : 'new' ;
NEWARRAY : 'newarray' ;
INVOKE   : 'invoke' ;
RETURN   : 'return' ;
PUSH     : 'push' ;
POP      : 'pop' ;
LOAD     : 'load' ;
ALOAD    : 'aload' ;
STORE    : 'store' ;
ASTORE   : 'astore' ;
GOTO     : 'goto' ;
WRITE    : 'write' ;
WRITELN  : 'writeln' ;
IF       : 'if' ;
INC      : 'inc' ;
AINC     : 'ainc' ;
NOP      : 'nop' ;
SUP      : 'sup' ;
ADD      : 'add' ;
MUL      : 'mul' ;
SUB      : 'sub' ;
DIV      : 'div' ;
OR       : 'or' ;
AND      : 'and' ;
NEG      : 'neg' ;
CMP      : 'cmp';

/* ==================== SEPARATORS ==================== */

LPAR   : '(' ;
RPAR   : ')' ;

/* ======================= TYPES ======================= */

VOID : 'void' ;
INT : 'entier' ;
BOOLEAN : 'boolean';

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
Identifier : NonDigit ( NonDigit | Digit ) * ;

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