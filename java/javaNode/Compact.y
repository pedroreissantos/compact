%{
import java.io.*;
%}
%token INTEGER
%token VARIABLE STRING
%token WHILE IF PRINT READ PROGRAM END
%nonassoc IFX
%nonassoc ELSE

%left GE LE EQ NE '>' '<'
%left '+' '-'
%left '*' '/' '%'
%nonassoc UMINUS

%token GT LT ADD SUB MUL DIV MOD ASSIGN STMT NONE

%%

program	: PROGRAM list END		  { tree = $2; }
	;

stmt	: ';'				  { $$ = new Node(Parser.NONE); }
	| PRINT STRING ';'		  { $$ = $2; }
	| PRINT expr ';'		  { $$ = new NodeList(Parser.PRINT, $2); }
	| READ VARIABLE ';'		  { $$ = new NodeList(Parser.READ, $2); }
	| VARIABLE '=' expr ';'		  { $$ = new NodeList(Parser.ASSIGN, $1, $3);}
	| WHILE '(' expr ')' stmt	  { $$ = new NodeList(Parser.WHILE, $3, $5); }
	| IF '(' expr ')' stmt %prec IFX  { $$ = new NodeList(Parser.IF, $3, $5); }
	| IF '(' expr ')' stmt ELSE stmt  { $$ = new NodeList(Parser.ELSE, $3, $5, $7); }
	| '{' list '}'			  { $$ = $2; }
	;

list	: stmt				  { NodeList n = new NodeList(Parser.STMT);
					    n.append($1); $$ = n; }
	| list stmt			  { ((NodeList)$1).append($2);
					    $$ = $1; }
	;

expr	: INTEGER			  { $$ = $1; }
	| VARIABLE			  { $$ = $1; }
	| '-' expr %prec UMINUS		  { $$ = new NodeList(Parser.UMINUS, $2); }
	| expr '+' expr			  { $$ = new NodeList(Parser.ADD, $1, $3); }
	| expr '-' expr			  { $$ = new NodeList(Parser.SUB, $1, $3); }
	| expr '*' expr			  { $$ = new NodeList(Parser.MUL, $1, $3); }
	| expr '/' expr			  { $$ = new NodeList(Parser.DIV, $1, $3); }
	| expr '%' expr			  { $$ = new NodeList(Parser.MOD, $1, $3); }
	| expr '<' expr			  { $$ = new NodeList(Parser.LT, $1, $3); }
	| expr '>' expr			  { $$ = new NodeList(Parser.GT, $1, $3); }
	| expr GE expr			  { $$ = new NodeList(Parser.GE, $1, $3); }
	| expr LE expr			  { $$ = new NodeList(Parser.LE, $1, $3); }
	| expr NE expr			  { $$ = new NodeList(Parser.NE, $1, $3); }
	| expr EQ expr			  { $$ = new NodeList(Parser.EQ, $1, $3); }
	| '(' expr ')'			  { $$ = $2; }
	;

%%
private Node tree; // top level node of the syntax tree
private String filename; // just for error printing
public  Node syntax() { return tree; }

private Yylex lexer; // interface with JFlex lexical analyser
private int yylex() {
	int ret = -1;
	try {
		ret = lexer.yylex();
	} catch (IOException e) {
		System.err.println("Erro: " + e);
	}
	return ret;
}
private int nerrs;
public void yyerror(String msg) {
	nerrs++;
	if (filename == null)
		System.err.println(msg + ": line " + lexer.line() +
				" at or before " + lexer.yytext());
	else
		System.err.println(filename + ": line " + lexer.line() + ": " +
				msg + " at or before " + lexer.yytext());
}
public int errors() { return nerrs; }
public Parser(Reader r) { nerrs = 0; lexer = new Yylex(r, this); }
public Parser(Reader r, boolean _debug)
{ this(r); yydebug = _debug; filename = null; }
public Parser(Reader r, boolean _debug, String infile)
{ this(r, _debug); filename = infile; }
// use this 'main' to test the lexical analyser (with 'yylval' and 'return')
// but without a proper grammar
public static void main(String args[]) throws IOException {
        Parser parser = new Parser(new FileReader(args[0]));
	while (parser.yylex() != 0) ;
}
