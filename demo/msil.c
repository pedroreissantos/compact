/* $Id: msil.c,v 1.1 2008/11/11 16:24:30 prs Exp $ */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include "node.h"
#include "y.tab.h"
#include "tabid.h"

static int lbl;
static void eval(Node *p);
static FILE *out;
static char *mklbl(int);
extern char *dupstr(const char *s);

static int depth(Node *p) {
    int left, right;
    char *name;

    if (p == 0) return 0;
    switch(p->attrib) {
    case INTEGER: return 1;
    case STRING: return 2;
    case VARIABLE: return 1;
    case WHILE:
	left = 2 + depth(p->SUB(0));
	right = depth(p->SUB(1));
	return left > right ? left : right;
    case IF:
	left = 2 + depth(p->SUB(0));
	right = depth(p->SUB(1));
	if (right > left) left = right;
	if (p->value.sub.num > 2) { /* if else */
	    right = depth(p->SUB(2));
	    if (right > left) left = right;
	}
	return left;
    case READ:
	return 1; /* ? */
    case PRINT: return 1+depth(p->SUB(0));
    case ';':
	left = depth(p->SUB(0));
	right = depth(p->SUB(1));
	return left > right ? left : right;
    case '=':
	name = p->SUB(0)->value.s;
	if (IDfind(name, (int*)IDtest) == -1) { /* variable not found ?       */
	  IDnew(INTEGER, name, 0);		/* put in the symbol table    */
	}
    	return depth(p->SUB(1));
    case UMINUS: return depth(p->SUB(0));
    default:
	left = depth(p->SUB(0));
	right = depth(p->SUB(1));
	if (right > left) left = right;
	break; /* return follows below */
    }
    return left + 2;
}

static int cnt;
static int args(int t, char *n, int a, int user) {
  fprintf(out, ".locals ([%d] int32 '%s')\n", cnt++, n);
  return 1;
}

void evaluate(Node *p) {
  extern char *outfile;
  extern int errors;
  char *outclass;

  if (errors > 0) return;
  if (outfile == 0) outfile = "out.il";
  else
    if (strlen(outfile) > 4 && outfile[strlen(outfile) -4] == '.')
      strcpy(outfile+strlen(outfile)-4, ".il");
  outclass = dupstr(outfile);
  if (strrchr(outclass, '.') != 0) *strrchr(outclass, '.') = 0;

  if ((out = fopen(outfile, "w")) == 0) {
    perror(outfile);
    exit(2);
  }

  fprintf(out,	".assembly _%s {}\n"
      ".class public _%s {\n" /* extends [mscorlib]System.Object {\n" */
      ".method public static void Main(string[] args) {\n"
      ".entrypoint\n.maxstack %d\n", outclass, outclass, depth(p));
  IDevery(args, 0);
  eval(p);
  fprintf(out, "ret\n} }\n");
  fclose(out);
}

static void cprintf(FILE *out, char *cond) {
    char lbl1[20], lbl2[20];
    strcpy(lbl1, mklbl(++lbl));
    strcpy(lbl2, mklbl(++lbl));
    fprintf(out, "%s %s\nldc.i4.0\nbr %s\n%s:\nldc.i4.1\n%s:\n",
    		  cond, lbl1, lbl2, lbl1, lbl2);
}

static void eval(Node *p) {
    int lbl1, lbl2;

    if (p == 0) return;
    switch(p->attrib) {
    case INTEGER: /* push an integer	      */
        fprintf(out, "ldc.i4 %d\n", p->value.i);
        break;
    case STRING:
	/* generate the string */
	fprintf(out, "ldstr \"%s\"\n", p->value.s);
	fprintf(out, "call void [mscorlib]System.Console::WriteLine(string)\n");
        break;
    case VARIABLE:
	fprintf(out, "ldloc %s\n", p->value.s);
	break;
    case WHILE:
	fprintf(out, "%s:\n", mklbl(lbl1 = ++lbl));
	eval(p->SUB(0));
	fprintf(out, "brfalse %s\n", mklbl(lbl2 = ++lbl));
	eval(p->SUB(1));
	fprintf(out, "br %s\n", mklbl(lbl1));
	fprintf(out, "%s:\n", mklbl(lbl2));
	break;
    case IF:
	eval(p->SUB(0));
	fprintf(out, "brfalse %s\n", mklbl(lbl1 = ++lbl));
	eval(p->SUB(1));
	if (p->value.sub.num > 2) { /* if else */
	    fprintf(out, "br %s\n", mklbl(lbl2 = ++lbl));
	    fprintf(out, "%s:\n", mklbl(lbl1));
	    eval(p->SUB(2));
	    lbl1 = lbl2;
	}
	fprintf(out, "%s:\n", mklbl(lbl1));
	break;
    case READ:
	fprintf(out, "call string [mscorlib]System.Console::ReadLine()\n"
		     "call int32 [mscorlib]System.Int32::Parse(string)\n"
		     "stloc %s\n", p->value.s);
	break;
    case PRINT:
	eval(p->SUB(0));			/* determine the value        */
	fprintf(out, "call void [mscorlib]System.Console::WriteLine(int32)\n");
	break;
    case ';':
	eval(p->SUB(0));
	eval(p->SUB(1));
	break;
    case '=':
	eval(p->SUB(1));			/* determine the new value    */
	fprintf(out, "stloc %s\n", p->SUB(0)->value.s);	/* store the value at address */
	break;
    case UMINUS:
	eval(p->SUB(0));			/* determine the value	      */
	fprintf(out, "neg\n");			/* make the 2-compliment      */
	break;
    default:
	eval(p->SUB(0));			/* evaluate first argument    */
	eval(p->SUB(1));			/* evaluate second argument   */
	switch(p->attrib) {			/* make the operation ...     */
	    case '+':   fprintf(out, "add\n"); break;
	    case '-':   fprintf(out, "sub\n"); break;
	    case '*':   fprintf(out, "mul\n"); break;
	    case '/':   fprintf(out, "div\n"); break;
	    case '%':   fprintf(out, "rem\n"); break;
	    case '<':   fprintf(out, "clt\n"); break;
	    case '>':   fprintf(out, "cgt\n"); break;
	    case EQ:    fprintf(out, "ceq\n"); break;
	    case GE:    cprintf(out, "bge"); break;
	    case LE:    cprintf(out, "ble"); break;
	    case NE:    cprintf(out, "bne"); break;
	    default:    printf("unknown %d ('%c')\n", p->attrib, p->attrib);
	}
    }
}

static char *mklbl(int n) {
  static char str[20];
  if (n < 0)
    sprintf(str, ".L%d", -n);
  else
    sprintf(str, "$L%d", n);
  return str;
}
