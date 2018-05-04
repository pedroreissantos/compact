%{
/* compile with: byaccj -J -Jsemantic=Tree -Jclass=ParserOpt */
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

%token GT LT ADD SUB MUL DIV MOD ASSIGN JMP JZ JNZ LABEL ETIQ

%%

program	: PROGRAM list END		  { tree = $2; }
	;

stmt	: ';'				  { $$ = new Tree(ParserOpt.END); }
	| PRINT STRING ';'		  { $$ = $2; }
	| PRINT expr ';'		  { $$ = new Tree(ParserOpt.PRINT, $2); }
	| READ VARIABLE ';'		  { $$ = new Tree(ParserOpt.READ, $2); symbol($2.text()); }
	| VARIABLE '=' expr ';'		  { $$ = new Tree(ParserOpt.ASSIGN, $1, $3); vars.put($1.text(), 0); }
	| WHILE '(' expr ')' stmt	  { int lbl1 = ++lbl, lbl2 = ++lbl;
	      $$ = new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.JMP, mklbl(lbl1)),
		    new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.LABEL, mklbl(lbl2)),
		     new Tree(ParserOpt.PROGRAM, $5,
		      new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.LABEL, mklbl(lbl1)),
		   	new Tree(ParserOpt.JNZ, $3, new Tree(ParserOpt.ETIQ, mklbl(lbl2))))))); }
	| IF '(' expr ')' stmt %prec IFX  { int lbl1 = ++lbl, lbl2 = ++lbl;
	      $$ = new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.JZ, $3,
	      		new Tree(ParserOpt.ETIQ, mklbl(lbl1))),
		    new Tree(ParserOpt.PROGRAM, $5,
		     new Tree(ParserOpt.LABEL, mklbl(lbl1)))); }
	| IF '(' expr ')' stmt ELSE stmt  { int lbl1 = ++lbl, lbl2 = ++lbl;
	      $$ = new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.JZ, $3,
	      		new Tree(ParserOpt.ETIQ, mklbl(lbl1))),
		    new Tree(ParserOpt.PROGRAM, $5,
		     new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.JMP, mklbl(lbl2)),
		      new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.LABEL, mklbl(lbl1)),
		       new Tree(ParserOpt.PROGRAM, $7,
		   	new Tree(ParserOpt.LABEL, mklbl(lbl2))))))); }
	| '{' list '}'			  { $$ = $2; }
	;

list	: stmt				  { $$ = new Tree(ParserOpt.PROGRAM, new Tree(ParserOpt.END), $1); }
	| list stmt			  { $$ = new Tree(ParserOpt.PROGRAM, $1, $2); }
	;

expr	: INTEGER			  { $$ = $1; }
	| VARIABLE			  { $$ = $1; symbol($1.text()); }
	| '-' expr %prec UMINUS		  { $$ = new Tree(ParserOpt.UMINUS, $2); }
	| expr '+' expr			  { $$ = new Tree(ParserOpt.ADD, $1, $3); }
	| expr '-' expr			  { $$ = new Tree(ParserOpt.SUB, $1, $3); }
	| expr '*' expr			  { $$ = new Tree(ParserOpt.MUL, $1, $3); }
	| expr '/' expr			  { $$ = new Tree(ParserOpt.DIV, $1, $3); }
	| expr '%' expr			  { $$ = new Tree(ParserOpt.MOD, $1, $3); }
	| expr '<' expr			  { $$ = new Tree(ParserOpt.LT, $1, $3); }
	| expr '>' expr			  { $$ = new Tree(ParserOpt.GT, $1, $3); }
	| expr GE expr			  { $$ = new Tree(ParserOpt.GE, $1, $3); }
	| expr LE expr			  { $$ = new Tree(ParserOpt.LE, $1, $3); }
	| expr NE expr			  { $$ = new Tree(ParserOpt.NE, $1, $3); }
	| expr EQ expr			  { $$ = new Tree(ParserOpt.EQ, $1, $3); }
	| '(' expr ')'			  { $$ = $2; }
	;

%%
private int lbl;
String mklbl(int n) { return "_L"+n; }
int newlbl() { return ++lbl; }

private Tree tree; // top level node of the syntax tree
private String filename; // just for error printing
public  Tree syntax() { return tree; }

private LexerOpt lexer; // interface with JFlex lexical analyser
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

public ParserOpt(Reader r)
{ nerrs = 0; vars = new Tabid<Integer>(); lexer = new LexerOpt(r, this); }
public ParserOpt(Reader r, boolean _debug)
{ this(r); yydebug = _debug; filename = null; }
public ParserOpt(Reader r, boolean _debug, String infile)
{ this(r, _debug); filename = infile; }
// use this 'main' to test the lexical analyser (with 'yylval' and 'return')
// but without a proper grammar
public static void main(String args[]) throws IOException {
        ParserOpt parser = new ParserOpt(new FileReader(args[0]));
	while (parser.yylex() != 0) ;
}
