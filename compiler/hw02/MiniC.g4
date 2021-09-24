grammar MiniC;

program	: decl+	   {System.out.println("201702052 Rule 0");} ;
decl		: var_decl
		| fun_decl	 {System.out.println("201702052 Rule 1-2");};
var_decl	:  type_spec IDENT ';'       {System.out.println("201702052 Rule 2-1");}
		| type_spec IDENT '=' LITERAL ';'  {System.out.println("201702052 Rule 2-2");}
		| type_spec IDENT '[' LITERAL ']' ';'	 {System.out.println("201702052 Rule 2-3");};
type_spec	: VOID   {System.out.println("201702052 Rule 3-1");}
		| INT {System.out.println("201702052 Rule 3-2");}			;
fun_decl	: type_spec IDENT '(' params ')' compound_stmt  {System.out.println("201702052 Rule 4");};
params		: param (',' param)* {System.out.println("201702052 Rule 5-1");}
		| VOID	{System.out.println("201702052 Rule 5-2");}
		|				{System.out.println("201702052 Rule 5-3");};
param		: type_spec IDENT {System.out.println("201702052 Rule 6-1");}
		| type_spec IDENT '[' ']'	{System.out.println("201702052 Rule 6-2");};
stmt		: expr_stmt {System.out.println("201702052 Rule 7-1");}
		| compound_stmt {System.out.println("201702052 Rule 7-2");}
		| if_stmt {System.out.println("201702052 Rule 7-3");}
		| while_stmt {System.out.println("201702052 Rule 7-4");}
		| return_stmt {System.out.println("201702052 Rule 7-5");}			;
expr_stmt	: expr ';'	{System.out.println("201702052 Rule 8");}	;
while_stmt	: WHILE '(' expr ')' stmt	{System.out.println("201702052 Rule 9");};
compound_stmt: '{' local_decl* stmt* '}' {System.out.println("201702052 Rule 10");};
local_decl	: type_spec IDENT ';'
		| type_spec IDENT '=' LITERAL ';'
		| type_spec IDENT '[' LITERAL ']' ';'	;
if_stmt		: IF '(' expr ')' stmt {System.out.println("201702052 Rule 11-1 if");}
		| IF '(' expr ')' stmt ELSE stmt 		{System.out.println("201702052 Rule 11-2 if else");};
return_stmt	: RETURN ';'
		| RETURN expr ';'				;
expr	:  LITERAL
	| '(' expr ')' {System.out.println("201702052 Rule 12-1");}
	| IDENT
	| IDENT '[' expr ']'
	| IDENT '(' args ')' {System.out.println("201702052 Rule 12-2");}
	| '-' expr
	| '+' expr {System.out.println("201702052 Rule 12-3");}
	| '--' expr
	| '++' expr
	| expr '*' expr {System.out.println("201702052 Rule 12-4");}
	| expr '/' expr				 
	| expr '%' expr				 
	| expr '+' expr {System.out.println("201702052 Rule 12-5");}
	| expr '-' expr
	| expr EQ expr				
	| expr NE expr				 
	| expr LE expr				 
	| expr '<' expr
	| expr GE expr				 
	| expr '>' expr				 
	| '!' expr					 
	| expr AND expr				 
	| expr OR expr				
	| IDENT '=' expr			{System.out.println("201702052 Rule 12-6");}
	| IDENT '[' expr ']' '=' expr		;
args	: expr (',' expr)*
	|				;

VOID: 'void' ;
INT: 'int' {System.out.println("201702052 Rule 13");};

WHILE: 'while'{System.out.println("201702052 Rule 14");};
IF: 'if' ;
ELSE: 'else' ;
RETURN: 'return' {System.out.println("201702052 Rule 15");};
OR: 'or';
AND: 'and';
LE: '<=';
GE: '>=';
EQ: '==';
NE: '!=';

IDENT  : [a-zA-Z_]
        (   [a-zA-Z_]
        |  [0-9]
        )*;


LITERAL:   DecimalConstant    |   OctalConstant    |   HexadecimalConstant    ;


DecimalConstant
    :   '0'
	|   [1-9] [0-9]*
    ;

OctalConstant
    :   '0'[0-7]*
    ;

HexadecimalConstant
    :   '0' [xX] [0-9a-fA-F] +
    ;

WS  :   (   ' '
        |   '\t'
        |   '\r'
        |   '\n'
        )+
	-> channel(HIDDEN)	 
    ;

