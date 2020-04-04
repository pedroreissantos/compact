%{
#include "Node.hpp"
#include "NodeInteger.hpp"
#include "NodeString.hpp"
#include "NodeList.hpp"
#include "NodeIf.hpp"
#include "NodeIfelse.hpp"
#include "NodeWhile.hpp"
#include "NodePrint.hpp"
#include "NodeRead.hpp"
#include "NodeAssign.hpp"
#include "NodeUminus.hpp"
#include "NodeAdd.hpp"
#include "NodeSub.hpp"
#include "NodeMul.hpp"
#include "NodeDiv.hpp"
#include "NodeMod.hpp"
#include "NodeEq.hpp"
#include "NodeNe.hpp"
#include "NodeGt.hpp"
#include "NodeGe.hpp"
#include "NodeLt.hpp"
#include "NodeLe.hpp"

#include "Parser.hpp"
Parser *parser;
void yyerror(const char *msg);
%}
%token<n> INTEGER
%token<n> VARIABLE STRING
%token WHILE IF PRINT READ PROGRAM END
%nonassoc IFX
%nonassoc ELSE

%left GE LE EQ NE '>' '<'
%left '+' '-'
%left '*' '/' '%'
%nonassoc UMINUS

%union{ Node *n; }
%type<n> stmt list expr
%%

program	: PROGRAM list END		  { parser->syntax($2); }
	;

stmt	: ';'				  { $$ = new Node(); }
	| PRINT STRING ';'		  { $$ = $2; }
	| PRINT expr ';'		  { $$ = new NodePrint(*$2); }
	| READ VARIABLE ';'		  { $$ = new NodeRead(*$2); }
	| VARIABLE '=' expr ';'		  { $$ = new NodeAssign(*$1, *$3);}
	| WHILE '(' expr ')' stmt	  { $$ = new NodeWhile(*$3, *$5); }
	| IF '(' expr ')' stmt %prec IFX  { $$ = new NodeIf(*$3, *$5); }
	| IF '(' expr ')' stmt ELSE stmt  { $$ = new NodeIfelse(*$3, *$5, *$7); }
	| '{' list '}'			  { $$ = $2; }
	;

list	: stmt				  { $$ = new NodeList();
					    ((NodeList*)$$)->append(*$1); }
	| list stmt			  { ((NodeList*)($$ = $1))->append(*$2); }
	;

expr	: INTEGER			  { $$ = $1; }
	| VARIABLE			  { $$ = $1; }
	| '-' expr %prec UMINUS		  { $$ = new NodeUminus(*$2); }
	| expr '+' expr			  { $$ = new NodeAdd(*$1, *$3); }
	| expr '-' expr			  { $$ = new NodeSub(*$1, *$3); }
	| expr '*' expr			  { $$ = new NodeMul(*$1, *$3); }
	| expr '/' expr			  { $$ = new NodeDiv(*$1, *$3); }
	| expr '%' expr			  { $$ = new NodeMod(*$1, *$3); }
	| expr '<' expr			  { $$ = new NodeLt(*$1, *$3); }
	| expr '>' expr			  { $$ = new NodeGt(*$1, *$3); }
	| expr GE expr			  { $$ = new NodeGe(*$1, *$3); }
	| expr LE expr			  { $$ = new NodeLe(*$1, *$3); }
	| expr NE expr			  { $$ = new NodeNe(*$1, *$3); }
	| expr EQ expr			  { $$ = new NodeEq(*$1, *$3); }
	| '(' expr ')'			  { $$ = $2; }
	;

%%
void yyerror(const char *msg) { parser->error(msg); }
int yylex() { return parser->lex(); }
