/* $Id: postfix.c,v 1.8 2011/06/19 17:25:13 prs Exp $ */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "node.h"
#include "y.tab.h"
#include "tabid.h"
#ifndef PF
#include "postfix.h"
#else
/*
#define pfI386GAS
#define pfAMD64
#define pfDEBUG
*/
#include "pf.h"
#endif

static int lbl;
static void eval(Node *p);
static FILE *out;
static char *mklbl(int);

static int data(int type, char *name, int attrib, int user) {
  if (name == 0) return;
  fprintf(out, pfLABEL, name);		/* name variable location     */
#ifdef _64bits_
  fprintf(out, pfLONG, 0LL);		/* initialize it to 0 (zero)  */
#else
  fprintf(out, pfINTEGER, 0);		/* initialize it to 0 (zero)  */
#endif
}

void evaluate(Node *p) {
  extern char *infile, *outfile, **yynames;
  extern int errors, tree;

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
  fprintf(out, pfGLOBL, "_main", pfFUNC);
  fprintf(out, pfLABEL, "_main");
  fprintf(out, pfENTER, 0);
  eval(p);
  fprintf(out, pfIMM, 0);
  fprintf(out, pfPOP);
  fprintf(out, pfLEAVE);
  fprintf(out, pfRET);
  /* import library functions */
  fprintf(out, pfEXTRN, "_readi");
  fprintf(out, pfEXTRN, "_printi");
  fprintf(out, pfEXTRN, "_prints");
  fprintf(out, pfEXTRN, "_println");
  fprintf(out, pfEND);
  fclose(out);
}


static void eval(Node *p) {
    int i, lbl1, lbl2;
    char *name;

    if (p == 0) return;
    switch(p->attrib) {
    case INTEGER:
        fprintf(out, pfIMM, p->value.i);	/* push an integer immediate */
        break;
    case STRING:
	/* generate the string */
	fprintf(out, pfRODATA);			/* strings are DATA readonly  */
	fprintf(out, pfALIGN);			/* make sure we are aligned   */
	fprintf(out, pfLABEL, mklbl(lbl1 = ++lbl));	/* name the string    */
	fprintf(out, pfSTR, p->value.s);	/* output string characters   */
	/* make the call */
	fprintf(out, pfTEXT);			/* return to the TEXT segment */
	fprintf(out, pfADDR, mklbl(lbl1));	/* the string to be printed   */
	fprintf(out, pfCALL, "_prints");	/* call the print rotine      */
	fprintf(out, pfCALL, "_println");	/* print a newline	      */
	fprintf(out, pfTRASH, sizeof(regint));		/* remove the string address: 4/8 bytes    */
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
	fprintf(out, pfCALL, "_printi");	/* call the print function    */
	fprintf(out, pfCALL, "_println");	/* print a newline	      */
	fprintf(out, pfTRASH, sizeof(regint));		/* remove the printed value: 4/8 bytes   */
	break;
    case ';':
	for (i = 0; i < p->value.sub.num; i++)
	  eval(p->SUB(i));
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
	    default:    printf("unknown %d\n", p->attrib);
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
