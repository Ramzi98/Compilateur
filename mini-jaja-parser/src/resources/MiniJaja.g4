 /** MiniJaja from VM Project at UBFC 2020 as read in the given spec
 https://moodle.univ-fcomte.fr/mod/page/view.php?id=190601
 */
 grammar MiniJaja;
/* ====================== PARSER ===================== */


 classe              : CLASS ident LBRACE decls methmain RBRACE EOF
                     ;

 ident               :Identifier
                     ;

 decls               : decl SEMI decls                      #MultiDecls
                     |                                      #EmptyDecls
                     ;

 decl               : var
                     | methode
                     ;

 vars                : var SEMI vars                        #MultiVars
                     |                                      #EmptyVars
                     ;

 var                 : typemeth ident vexp                  #VarNode
                     | typemeth ident LBRACK exp RBRACK     #Array
                     | FINAL type ident vexp                #Cst
                     ;

 vexp                : ASSIGN exp                           #VexpAssign
                     |                                      #OmegaAssign
                     ;

 methode             : typemeth ident LPAR entetes RPAR LBRACE vars instrs RBRACE
                     ;

 methmain            : MAIN LBRACE vars instrs RBRACE
                     ;

 entetes             : entete COMMA entetes                 #MultiHeaders
                     | entete                               #UnitHeader
                     |                                      #EmptyHeader
                     ;

 entete              : type ident
                     ;

 instrs              : instr SEMI instrs                    #MultiInstrs
                     |                                      #EmptyInstrs
                     ;

 instr               : ident1 ASSIGN exp                                        #Assign
                     | ident1 ADD_ASSIGN exp                                    #Sum
                     | ident1 INC                                               #Inc
                     | ident '(' listexp ')'                                    #AppelI
                     | RETURN exp                                               #Return
                     | WRITE '('  ident  ')'                                    #WriteIdent
                     | WRITE '('  StringLitteral  ')'                           #WriteString
                     | WRITELN '(' ident  ')'                                   #WriteLnIdent
                     | WRITELN '(' StringLitteral  ')'                          #WriteLnString
                     | IF exp LBRACE ifInstrs=instrs
                       RBRACE ELSE LBRACE elseInstrs=instrs RBRACE              #IfElse
                     | IF exp LBRACE instrs RBRACE                              #If
                     | WHILE LPAR exp RPAR LBRACE instrs RBRACE                 #While
                     ;

 listexp             : exp COMMA listexp                    #MultiListexp
                     | exp                                  #UnitListExp
                     |                                      #EmptyListexp
                     ;

 exp                 : BANG exp                            #Not
                     | exp AND exp                         #And
                     | exp OR exp                          #Or
                     | exp1                                 #ExpIsExp1
                     ;

 exp1                : exp1 EQUAL exp2                      #Equals
                     | exp1 GT exp2                         #GreaterThan
                     | exp2                                 #Exp1IsExp2
                     ;

 exp2                : SUB terme                            #Minus
                     | exp2 ADD terme                       #Plus
                     | exp2 SUB terme                       #Sub
                     | terme                                #Exp2IsTerme
                     ;

 terme               : terme MUL fact                       #Mul
                     | terme DIV fact                       #Div
                     | fact                                 #TermeIsFact
                     ;

 fact                : ident1                               #FactIsIdent1
                     | ident LPAR listexp RPAR              #AppelE
                     | BoolLitteral                         #Boolean
                     | NumberLitteral                       #Number
                     | LPAR exp RPAR                        #RecExp
                     ;

 ident1              : ident                                #Ident1IsIdent
                     | ident LBRACK exp RBRACK              #ArrayItem
                     ;

 typemeth            : VOID                                 #Void
                     | type                                 #TypeMethIsType
                     ;


 type                : INT                                  #TypeIsINT
                     | BOOLEAN                              #TypeIsBoolean
                     ;

 /* ======================= LEXER ======================= */

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
 ADD : '+';
 INC : '++';
 SUB : '-';
 MUL : '*';
 DIV : '/';

 ADD_ASSIGN : '+=';

 /* ======================= TYPES ======================= */

 VOID : 'void' ;
 INT : 'int' ;
 BOOLEAN : 'boolean';

 /* ===================== LITERRALS ===================== */

 BoolLitteral
    :	'true'
    |	'false'
    ;

 // String Literals
 StringLitteral : '"' StringCharacter* '"' ;

 fragment StringCharacter
     : ~["\\\r\n]                       // all chars except escape sequences
    | EscapeSequence
    ;
 fragment EscapeSequence : '\\' [btnfr"'\\] ;

 // Identier
 Identifier : NonDigit ( NonDigit | Digit | '_' ) * ;

 fragment NonDigit : [a-zA-Z_] ;

 // Number Literals
 NumberLitteral :           '0' | NonZeroDigit Digit* ;
 fragment Digit :	        '0' | NonZeroDigit;
 fragment NonZeroDigit :    [1-9] ;

 /* ======================= SKIPS ======================= */

 WS : [ \t\r\n]+ -> skip ;              // skip spaces, tabs, newlines

 /* ====================== COMMENTS ===================== */

 COMMENT : '/*' .*? '*/' -> skip ;      // skip multiline comments
 LINE_COMMENT : '//' ~[\r\n]* -> skip ; // skip line comments


