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

%token GT LT ADD SUB MUL DIV MOD ASSIGN DO START

%%

program	: PROGRAM list END		  { tree = $2; }
	;

stmt	: ';'				  { $$ = new Tree(Parser.END); }
	| PRINT STRING ';'		  { $$ = $2; }
	| PRINT expr ';'		  { $$ = new Tree(Parser.PRINT, $2); }
	| READ VARIABLE ';'		  { $$ = new Tree(Parser.READ, $2); symbol($2.text()); }
	| VARIABLE '=' expr ';'		  { $$ = new Tree(Parser.ASSIGN, $1, $3); vars.put($1.text(), 0); }
	| WHILE '(' expr ')' stmt	  { $$ = new Tree(Parser.WHILE, new Tree(Parser.DO, new Tree(Parser.START), $5), $3); }
	| IF '(' expr ')' stmt %prec IFX  { $$ = new Tree(Parser.IF, $3, $5); }
	| IF '(' expr ')' stmt ELSE stmt  { $$ = new Tree(Parser.ELSE, new Tree(Parser.IF, $3, $5), $7); }
	| '{' list '}'			  { $$ = $2; }
	;

list	: stmt				  { $$ = new Tree(Parser.PROGRAM, new Tree(Parser.END), $1); }
	| list stmt			  { $$ = new Tree(Parser.PROGRAM, $1, $2); }
	;

expr	: INTEGER			  { $$ = $1; }
	| VARIABLE			  { $$ = $1; symbol($1.text()); }
	| '-' expr %prec UMINUS		  { $$ = new Tree(Parser.UMINUS, $2); }
	| expr '+' expr			  { $$ = new Tree(Parser.ADD, $1, $3); }
	| expr '-' expr			  { $$ = new Tree(Parser.SUB, $1, $3); }
	| expr '*' expr			  { $$ = new Tree(Parser.MUL, $1, $3); }
	| expr '/' expr			  { $$ = new Tree(Parser.DIV, $1, $3); }
	| expr '%' expr			  { $$ = new Tree(Parser.MOD, $1, $3); }
	| expr '<' expr			  { $$ = new Tree(Parser.LT, $1, $3); }
	| expr '>' expr			  { $$ = new Tree(Parser.GT, $1, $3); }
	| expr GE expr			  { $$ = new Tree(Parser.GE, $1, $3); }
	| expr LE expr			  { $$ = new Tree(Parser.LE, $1, $3); }
	| expr NE expr			  { $$ = new Tree(Parser.NE, $1, $3); }
	| expr EQ expr			  { $$ = new Tree(Parser.EQ, $1, $3); }
	| '(' expr ')'			  { $$ = $2; }
	;

%%
private Tree tree; // top level node of the syntax tree
private String filename; // just for error printing
public  Tree syntax() { return tree; }

private Yylex lexer; // interface with JFlex lexical analyser
private int line() { return 1 + lexer.line(); } // bug in JFlex
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
		System.err.println(msg + ": line " + line() +
				" at or before " + lexer.yytext());
	else
		System.err.println(filename + ": line " + line() + ": " +
				msg + " at or before " + lexer.yytext());
}
public int errors() { return nerrs; }

private Tabid<Integer> vars;
public Tabid<Integer> symbols() { return vars; }
public void symbol(String name)
{ if (!vars.containsKey(name)) yyerror(name + ": variable not found."); }

public Parser(Reader r)
{ nerrs = 0; vars = new Tabid<Integer>(); lexer = new Yylex(r, this); }
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
