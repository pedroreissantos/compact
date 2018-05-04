/* $Id: code.c,v 1.7 2004/12/09 17:25:13 prs Exp $ */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "node.h"
#include "y.tab.h"
#include "tabid.h"
#define pfI386GAS
#include "postfix.h"

#define MAIN "__main"

static int lbl;
static void eval(Node *p);
static FILE *out;
static char *mklbl(int);

void stab(FILE *out, int decl, char *name, int val)
{
  char *lb;
  static char *type[] = {
    /* 0 */		"",
    /* 1 - INT */	"=r1;-2147483648;2147483647;",
    /* 2 - CHAR */	"=r2;-128;127;",
    /* 3 - LONG */	"=r3;-2147483648;2147483647;",
    /* 4 - SHORT */	"=r4;-32768;32767;",
    /* 5 - UCHAR */	"=r5;0;255;",
    /* 6 - USHORT */	"=r6;0;65535;",
    /* 7 - ULONG */	"=r7;0;4294967296;",
    /* 8 - UINT */	"=r8;0;4294967296;",
    /* 9 - FLOAT */	"=r9;1.17549435E-38;3.40282347e+38;",
    /* 10 - DOUBLE */	"=r10;2.2250738585072014E-308;1.7976931348623157E+308;",
    /* ... */		0
  };

  switch (decl) {
    case 32: /* N_GSYM: global variable (val is type) */
	fprintf(out, ".stabs \"%s:G%d\",%d,0,%d,0\n", name, val, decl, val);
	break;
    case 36: /* N_FUN: global function (val is type) */
	if (*(lb = name) == '_') lb++;
	fprintf(out, ".stabs \"%s:F%d\",%d,0,0,%s\n", lb, val, decl /* ,line */, name);
	break;
    case 68: /* N_SLINE: line number in .text (val is line; name is func) */
	lb = mklbl(- ++lbl);
	fprintf(out, ".stabn %d,0,%d,%s-%s\n%s:\n", decl, val, lb, name, lb);
	break;
    case 100: /* N_SO: name of source file */
	lb = mklbl(- ++lbl);
	fprintf(out, ".stabs \"%s\",%d,0,0,%s\n%s:\n", name, decl, lb, lb);
	break;
    case 128: /* N_LSYM: type descriptions (or variable in stack) */
	fprintf(out, ".stabs \"%s:t%d%s\",%d,0,0,0\n", name, val, type[val], decl);
	break;
    default:
	fprintf(stderr, "stab: unknown declaration %d\n", decl);
	break;
  }
}

static int data(int type, char *name, int attrib, int user) {
  if (name == 0) return;
  fprintf(out, pfLABEL, name);		/* name variable location     */
  fprintf(out, pfINTEGER, 0);		/* initialize it to 0 (zero)  */
  stab(out, 32, name, 1);
}

void evaluate(Node *p) {
  extern char *outfile, **yynames, *infile;
  extern int errors, tree, yylineno;

  if (errors > 0) return;
  if (tree > 0) { printNode(p, stdout, yynames); return; }
  if (outfile == 0) outfile = "out.asm";
  if ((out = fopen(outfile, "w")) == 0) {
    perror(outfile);
    exit(2);
  }
  fprintf(out, pfBEGIN, infile);
  fprintf(out, pfDATA);
  fprintf(out, pfALIGN);
  IDevery(data, 0); /* reserve space for variables */
  fprintf(out, pfTEXT);
  fprintf(out, pfALIGN);
  stab(out, 100, infile, 0);
  stab(out, 128, "inteiro", 1);
  fprintf(out, pfGLOBL, MAIN);
  fprintf(out, pfLABEL, MAIN);
  stab(out, 36, MAIN, 1);
  fprintf(out, pfENTER, 0);
  stab(out, 68, MAIN, 1);
  eval(p);
  stab(out, 68, MAIN, yylineno);
  fprintf(out, pfINT, 0);
  fprintf(out, pfPOP);
  fprintf(out, pfLEAVE);
  fprintf(out, pfRET);
  stab(out, 100, "", 0);
  /* import library functions */
  fprintf(out, pfEXTRN, "_readi");
  fprintf(out, pfEXTRN, "_printi");
  fprintf(out, pfEXTRN, "_prints");
  fprintf(out, pfEXTRN, "_println");
  fprintf(out, pfEND);
  fclose(out);
}


static void eval(Node *p) {
    extern char **yynames;
    int i, lbl1, lbl2;
    char *name;

    if (p == 0) return;
    switch(p->attrib) {
    case INTEGER:
        fprintf(out, pfINT, p->value.i);		/* push an integer	      */
        break;
    case STRING:
	/* generate the string */
	fprintf(out, pfRODATA);			/* strings are DATA readonly  */
	fprintf(out, pfALIGN);			/* make sure we are aligned   */
	fprintf(out, pfLABEL, mklbl(lbl1 = ++lbl));	/* give the string a name     */
	fprintf(out, pfSTR, p->value.s);		/* output string characters   */
	/* make the call */
	fprintf(out, pfTEXT);			/* return to the TEXT segment */
	fprintf(out, pfADDR, mklbl(lbl1));		/* the string to be printed   */
	fprintf(out, pfCALL, "_prints");		/* call the print rotine      */
	fprintf(out, pfCALL, "_println");		/* print a newline	      */
	fprintf(out, pfTRASH, 4);			/* remove the string label    */
        break;
    case VARIABLE:
	fprintf(out, pfADDR, p->value.s);
	fprintf(out, pfLOAD);
	break;
    case WHILE:
	fprintf(out, pfLABEL, mklbl(lbl1 = ++lbl));
	eval(p->SUB(0));
	fprintf(out, pfJZ, mklbl(lbl2 = ++lbl));
	eval(p->SUB(1));
	fprintf(out, pfJMP, mklbl(lbl1));
	fprintf(out, pfLABEL, mklbl(lbl2));
	break;
    case IF:
	eval(p->SUB(0));
	fprintf(out, pfJZ, mklbl(lbl1 = ++lbl));
	eval(p->SUB(1));
	if (p->value.sub.num > 2) { /* if else */
	    fprintf(out, pfJMP, mklbl(lbl2 = ++lbl));
	    fprintf(out, pfLABEL, mklbl(lbl1));
	    eval(p->SUB(2));
	    lbl1 = lbl2;
	}
	fprintf(out, pfLABEL, mklbl(lbl1));
	break;
    case READ:
	fprintf(out, pfCALL, "_readi");
	fprintf(out, pfPUSH);
	fprintf(out, pfADDR, p->value.s);
	fprintf(out, pfSTORE);
	break;
    case PRINT:
	eval(p->SUB(0));			/* determine the value        */
	fprintf(out, pfCALL, "_printi");		/* call the print function    */
	fprintf(out, pfCALL, "_println");		/* print a newline	      */
	fprintf(out, pfTRASH, 4);			/* delete the printed value   */
	break;
    case ';':
	for (i = 0; i < p->value.sub.num; i++) {
	  int line = p->SUB(i)->line;
	  if (p->SUB(i)->attrib == IF || p->SUB(i)->attrib == WHILE)
	    line = p->SUB(i)->SUB(0)->line;
	  /* printf("line=%d (%s)\n", line, yynames[p->SUB(i)->attrib]); */
	  stab(out, 68, MAIN, line);
	  eval(p->SUB(i));
	}
	break;
    case '=':
	name = p->SUB(0)->value.s;
	eval(p->SUB(1));			/* determine the new value    */
	fprintf(out, pfADDR, name);		/* where to store the value   */
	fprintf(out, pfSTORE);			/* store the value at address */
	break;
    case UMINUS:
	eval(p->SUB(0));			/* determine the value	      */
	fprintf(out, pfNEG);			/* make the 2-compliment      */
	break;
    default:
	eval(p->SUB(0));			/* evaluate first argument    */
	eval(p->SUB(1));			/* evaluate second argument   */
	switch(p->attrib) {			/* make the operation ...     */
	case '+':   fprintf(out, pfADD); break;
	case '-':   fprintf(out, pfSUB); break;
	case '*':   fprintf(out, pfMUL); break;
	case '/':   fprintf(out, pfDIV); break;
	case '%':   fprintf(out, pfMOD); break;
	case '<':   fprintf(out, pfLT); break;
	case '>':   fprintf(out, pfGT); break;
	case GE:    fprintf(out, pfGE); break;
	case LE:    fprintf(out, pfLE); break;
	case NE:    fprintf(out, pfNE); break;
	case EQ:    fprintf(out, pfEQ); break;
	default:    printf("unknown %d ('%c')\n", p->attrib, p->attrib);
	}
    }
}

static char *mklbl(int n) {
  static char str[20];
  if (n < 0)
    sprintf(str, ".L%d", -n);
  else
    sprintf(str, "_L%d", n);
  return str;
}
