%{
/* $Id: gram.y,v 1.4 2004/12/09 17:25:13 prs Exp $ */
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "postfix.h"
#include "tabid.h"
#include "node.h"

void yyerror(char *s);
static int lbl;
static void prtstr(char*), readint(char*), loadvar(char*);
static char *mklbl(int);
static FILE *out;
extern char *outfile;
%}

%union {
	regint i;		/* integer value */
	char *s;		/* symbol name or string literal */
};

%token <i> INTEGER
%token <s> VARIABLE STRING
%token WHILE IF PRINT READ PROGRAM END
%nonassoc IFX
%nonassoc ELSE

%left GE LE EQ NE '>' '<'
%left '+' '-'
%left '*' '/' '%'
%nonassoc UMINUS

%type<i> do done then else
%%

program	: PROGRAM			{ if (outfile == 0) outfile = "out.asm";
					  if ((out = fopen(outfile, "w")) == 0)
					    { perror(outfile); exit(2); }
					  fprintf(out, pfGLOBL, "_main", pfFUNC);
					  fprintf(out, pfLABEL, "_main");
					  fprintf(out, pfSTART); }
	  list END		  	{ fprintf(out, pfIMM, 0);
	  				  fprintf(out, pfPOP);
					  fprintf(out, pfLEAVE);
					  fprintf(out, pfRET);
					  fclose(out);
					  if (yynerrs > 0) unlink(outfile); }
	;

stmt	: ';'
	| PRINT STRING ';'		  { prtstr($2); }
	| READ VARIABLE ';'		  { readint($2); }
	| PRINT expr ';'		  { fprintf(out, pfEXTRN, "_printi");
					    fprintf(out, pfCALL, "_printi");
					    fprintf(out, pfTRASH, 4);
					    fprintf(out, pfEXTRN, "_println");
					    fprintf(out, pfCALL, "_println"); }
	| VARIABLE '=' expr ';'		  { newvar($1);
					    fprintf(out, pfADDR, $1);
					    fprintf(out, pfSTORE); }
	| WHILE '(' do expr ')' done stmt { fprintf(out, pfJMP, mklbl($3));
	  				    fprintf(out, pfLABEL, mklbl($6));}
	| IF '(' expr ')' then stmt fi
	| '{' list '}'
	;

do	:			  { fprintf(out, pfLABEL, mklbl($$ = ++lbl));}
	;

done	:			  { fprintf(out, pfJZ, mklbl($$ = ++lbl)); }
	;

then	:			  { fprintf(out, pfJZ, mklbl($$ = ++lbl)); }
	;

fi	: %prec IFX		  { fprintf(out, pfLABEL, mklbl($<i>-1)); }
	| ELSE else stmt	  { fprintf(out, pfLABEL, mklbl($2)); }
	;

else	:			  { fprintf(out, pfJMP, mklbl($$ = ++lbl));
	  			    fprintf(out, pfLABEL, mklbl($<i>-2)); }
	;

list	: stmt
	| list stmt
	;

expr	: INTEGER			  { fprintf(out, pfIMM, $1); }
	| VARIABLE			  { loadvar($1); }
	| '-' expr %prec UMINUS		  { fprintf(out, pfNEG); }
	| expr '+' expr			  { fprintf(out, pfADD); }
	| expr '-' expr			  { fprintf(out, pfSUB); }
	| expr '*' expr			  { fprintf(out, pfMUL); }
	| expr '/' expr			  { fprintf(out, pfDIV); }
	| expr '%' expr			  { fprintf(out, pfMOD); }
	| expr '<' expr			  { fprintf(out, pfLT); }
	| expr '>' expr			  { fprintf(out, pfGT); }
	| expr GE expr			  { fprintf(out, pfGE); }
	| expr LE expr			  { fprintf(out, pfLE); }
	| expr NE expr			  { fprintf(out, pfNE); }
	| expr EQ expr			  { fprintf(out, pfEQ); }
	| '(' expr ')'
	;

%%
static void prtstr(char *s)
{
  fprintf(out, pfRODATA);
  fprintf(out, pfALIGN);
  fprintf(out, pfLABEL, mklbl(++lbl));
  fprintf(out, pfSTR, s);
  fprintf(out, pfTEXT);
  fprintf(out, pfADDR, mklbl(lbl));
  fprintf(out, pfEXTRN, "_prints");
  fprintf(out, pfCALL, "_prints");
  fprintf(out, pfTRASH, 4);
  fprintf(out, pfEXTRN, "_println");
  fprintf(out, pfCALL, "_println");
}

static void readint(char *s)
{
  if (IDfind(s, 0) >= 0) {
    fprintf(out, pfEXTRN, "_readi");
    fprintf(out, pfCALL, "_readi");
    fprintf(out, pfPUSH);
    fprintf(out, pfADDR, s);
    fprintf(out, pfSTORE);
  }
  else
    yynerrs++;
}

static void newvar(char *s)
{
  if (IDfind(s, (int*)IDtest) < 0) {
    IDnew(0, s, 0);
    fprintf(out, pfDATA);
    fprintf(out, pfALIGN);
    fprintf(out, pfLABEL, s);
#ifdef _64bits_
    fprintf(out, pfLONG, 0LL);
#else
    fprintf(out, pfINTEGER, 0);
#endif
    fprintf(out, pfTEXT);
  }
}

static void loadvar(char *s)
{
  if (IDfind(s, 0) >= 0) {
    fprintf(out, pfADDR, s);
    fprintf(out, pfLOAD);
  }
  else
    yynerrs++;
}

static char *mklbl(int n) {
  static char str[20];
  if (n < 0)
    sprintf(str, ".L%d", -n);
  else
    sprintf(str, "_L%d", n);
  return str;
}
