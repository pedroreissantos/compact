/* $Id: jasmin.c,v 1.1 2008/11/11 00:46:12 prs Exp $ */
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

static int count;
static int cnt(int t, char *n, int a, int user) { IDreplace(t, n, ++count); return 1; }

void evaluate(Node *p) {
  extern char *outfile;
  extern int errors;
  char *outclass;
  int stack, locals;

  if (errors > 0) return;
  if (outfile == 0) outfile = "out.j";
  else
    if (strlen(outfile) > 4 && outfile[strlen(outfile) -4] == '.')
      strcpy(outfile+strlen(outfile)-4, ".j");

  if ((out = fopen(outfile, "w")) == 0) {
    perror(outfile);
    exit(2);
  }
  outclass = dupstr(outfile);
  if (strrchr(outclass, '.') != 0) *strrchr(outclass, '.') = 0;

  stack = depth(p);
  locals = IDevery(cnt, 0)+1;
  fprintf(out,	".source %s\n"
  		".class public %s\n"
		".super java/lang/Object\n"
		".method public static main([Ljava/lang/String;)V\n"
		".limit stack %d\n"
		".limit locals %d\n",
		outfile, outclass, stack, locals);
  eval(p);
  fprintf(out, "return\n.end method\n");
  fclose(out);
}

static void cprintf(FILE *out, char *cond) {
    char lbl1[20], lbl2[20];
    strcpy(lbl1, mklbl(++lbl));
    strcpy(lbl2, mklbl(++lbl));
    fprintf(out, "%s %s\niconst_0\ngoto %s\n%s:\niconst_1\n%s:\n",
    		  cond, lbl1, lbl2, lbl1, lbl2);
}

static void eval(Node *p) {
    int lbl1, lbl2, pos;

    if (p == 0) return;
    switch(p->attrib) {
    case INTEGER: /* push an integer	      */
	if (p->value.i >= 0 && p->value.i < 6)
	  fprintf(out, "iconst_%d\n", p->value.i);
	else if (p->value.i == -1)
	  fprintf(out, "iconst_m1\n");
	else if (p->value.i >= -128 && p->value.i < 128)
	  fprintf(out, "bipush %d\n", p->value.i);
        else fprintf(out, "ldc %d\n", p->value.i);
        break;
    case STRING:
	/* generate the string */
	fprintf(out, "getstatic java/lang/System/out Ljava/io/PrintStream;\n");
	fprintf(out, "ldc \"%s\"\n", p->value.s);
	fprintf(out, "invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");
        break;
    case VARIABLE:
	if (IDfind(p->value.s, &pos) >= 0) fprintf(out, "iload %d\n", pos);
	break;
    case WHILE:
	fprintf(out, "%s:\n", mklbl(lbl1 = ++lbl));
	eval(p->SUB(0));
	fprintf(out, "ifeq %s\n", mklbl(lbl2 = ++lbl));
	eval(p->SUB(1));
	fprintf(out, "goto %s\n", mklbl(lbl1));
	fprintf(out, "%s:\n", mklbl(lbl2));
	break;
    case IF:
	eval(p->SUB(0));
	fprintf(out, "ifeq %s\n", mklbl(lbl1 = ++lbl));
	eval(p->SUB(1));
	if (p->value.sub.num > 2) { /* if else */
	    fprintf(out, "goto %s\n", mklbl(lbl2 = ++lbl));
	    fprintf(out, "%s:\n", mklbl(lbl1));
	    eval(p->SUB(2));
	    lbl1 = lbl2;
	}
	fprintf(out, "%s:\n", mklbl(lbl1));
	break;
    case READ:
	IDfind(p->value.s, &pos);
	fprintf(out, "getstatic java/lang/System/out Ljava/io/PrintStream;\n");
	fprintf(out, "invokestatic Compact/atoi()I\n");
	fprintf(out, "istore %d\n", pos);
	break;
    case PRINT:
	fprintf(out, "getstatic java/lang/System/out Ljava/io/PrintStream;\n");
	eval(p->SUB(0));			/* determine the value        */
	fprintf(out, "invokevirtual java/io/PrintStream/println(I)V\n");
	break;
    case ';':
	eval(p->SUB(0));
	eval(p->SUB(1));
	break;
    case '=':
	eval(p->SUB(1));			/* determine the new value    */
	IDfind(p->SUB(0)->value.s, &pos);	/* where to store the value   */
	fprintf(out, "istore %d\n", pos);	/* store the value at address */
	break;
    case UMINUS:
	eval(p->SUB(0));			/* determine the value	      */
	fprintf(out, "ineg\n");			/* make the 2-compliment      */
	break;
    default:
	eval(p->SUB(0));			/* evaluate first argument    */
	eval(p->SUB(1));			/* evaluate second argument   */
	switch(p->attrib) {			/* make the operation ...     */
	    case '+':   fprintf(out, "iadd\n"); break;
	    case '-':   fprintf(out, "isub\n"); break;
	    case '*':   fprintf(out, "imul\n"); break;
	    case '/':   fprintf(out, "idiv\n"); break;
	    case '%':   fprintf(out, "irem\n"); break;
	    case '<':   cprintf(out, "if_icmplt"); break;
	    case '>':   cprintf(out, "if_icmpgt"); break;
	    case GE:    cprintf(out, "if_icmpge"); break;
	    case LE:    cprintf(out, "if_icmple"); break;
	    case NE:    cprintf(out, "if_icmpne"); break;
	    case EQ:    cprintf(out, "if_icmpeq"); break;
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
