import java.io.*;

public class Compact extends Compiler {
	private Parser parser;
	private Postfix pf;

	public static void main(String args[]) throws IOException {
		Compact lang;

		if (args.length > 1)
			lang = new Compact(args);
		else
			lang = new Compact(args[0]);

		if (lang.parse())
			lang.generate();
	}
	Compact(String args[]) throws IOException {
		String in = null, out = null;
		boolean lexdeb = false, syndeb = false, treeprt = false;
		boolean debug = false;
		int files = 0;

		for (int i = 0; i < args.length; i++)
			if (args[i].charAt(0) == '-') {
				if (args[i].equals("-lexical")) lexdeb = true;
				if (args[i].equals("-syntax")) syndeb = true;
				if (args[i].equals("-tree")) treeprt = true;
			        if (args[i].equals("-debug")) debug = true;
			}
			else {
				if (++files == 1) in = args[i];
				if (files == 2) out = args[i];
				if (files > 2)
					System.err.println(args[i] + ": too many arguments.");
			}

		if (in == null) {
			System.err.println("No input file.");
			System.exit(1);
		}
		parser = new Parser(new FileReader(in), syndeb);
		if (debug)
		  	pf = new PFdebug(out == null ? extension(in, ".stk") : out);
		else
		  	pf = new PFi386(out == null ? extension(in, ".asm") : out);
	}
	Compact(String in) throws IOException { this(in, false); }
	Compact(String in, boolean debug) throws IOException {
		parser = new Parser(new FileReader(in), debug);
		pf = new PFi386(extension(in, ".asm"));
	}
	public boolean parse() { return parser.yyparse() == 0; }
	public boolean generate() {
		if (parser.errors() > 0) return false;
		pf.TEXT();		// instructions are TEXT segment
		pf.ALIGN();		// align start of function
		pf.GLOBL("_main");	// 'main' must be global
		pf.LABEL("_main");	// function label
		pf.START();		// frame with no local variables
		evaluate(parser.syntax());	// evaluate instructions
		pf.INT(0);		// return value
		pf.POP();		// save return value in accumulator
		pf.LEAVE();		// undo frame
		pf.RET();		// return
		// declare used variables
		pf.DATA();		// variables are DATA
		pf.ALIGN();		// make sure we are aligned
		for (String s: parser.symbols().keys()) {
			pf.LABEL(s);	// name variable location
			pf.CONST(0);	// initialize it to 0 (zero)
		}
		// import library functions
		pf.EXTRN("_readi");
		pf.EXTRN("_printi");
		pf.EXTRN("_prints");
		pf.EXTRN("_println");
		return true;
	}
	public void evaluate(Tree n) {
	  String name, lbl1, lbl2;

	  switch (n.label()) {
	    case Parser.INTEGER: pf.INT(n.value()); break;
	    case Parser.STRING:
		lbl1 = pf.label();
		pf.RODATA();			// strings are DATA readonly
		pf.ALIGN();			// make sure we are aligned
		pf.LABEL(lbl1);			// give the string a name
		pf.STR(n.text());		// output string characters
		// make the call
		pf.TEXT();			// return to the TEXT segment
		pf.ADDR(lbl1);			// the string to be printed
		pf.CALL("_prints");		// call the print rotine
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// remove the string argument
		break;
	  case Parser.END:
	  case Parser.START:
		break;
	  case Parser.PROGRAM:
		evaluate(n.left());
		if (n.right() != null) evaluate(n.right());
		break;
	  case Parser.VARIABLE:
	  	pf.ADDR(n.text());
	  	pf.LOAD();
		break;
	  case Parser.WHILE:
		lbl1 = pf.label();
		lbl2 = pf.label();
		pf.LABEL(lbl1);
		evaluate(n.right());
		pf.JZ(lbl2);
		evaluate(n.left().right());
		pf.JMP(lbl1);
		pf.LABEL(lbl2);
		break;
	  case Parser.IF:
		lbl1 = pf.label();
		evaluate(n.left());
		pf.JZ(lbl1);
		evaluate(n.right());
		pf.LABEL(lbl1);
		break;
	  case Parser.ELSE:
		lbl1 = pf.label();
		lbl2 = pf.label();
		evaluate(n.left().left());
		pf.JZ(lbl1);
		evaluate(n.left().right());
		pf.JMP(lbl2);
		pf.LABEL(lbl1);
		evaluate(n.right());
		pf.LABEL(lbl2);
		break;
	  case Parser.READ:
		name = n.left().text();
		pf.CALL("_readi");
		pf.PUSH();
		pf.ADDR(name);
		pf.STORE();
		break;
	  case Parser.PRINT:
		evaluate(n.left());
		pf.CALL("_printi");		// call the print function
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// delete the printed value
		break;
	  case Parser.ASSIGN:
		name = n.left().text();
		evaluate(n.right());	// determine the new value
		pf.ADDR(name);			// where to store the value
		pf.STORE();			// store the value at address
		break;
	  case Parser.UMINUS:
		evaluate(n.left());		// determine the new value
		pf.NEG();			// make the 2-compliment
		break;
	  case Parser.ADD:
		evaluate(n.left());
		evaluate(n.right());
		pf.ADD();
		break;
	  case Parser.SUB:
		evaluate(n.left());
		evaluate(n.right());
		pf.SUB();
		break;
	  case Parser.MUL:
		evaluate(n.left());
		evaluate(n.right());
		pf.MUL();
		break;
	  case Parser.DIV:
		evaluate(n.left());
		evaluate(n.right());
		pf.DIV();
		break;
	  case Parser.MOD:
		evaluate(n.left());
		evaluate(n.right());
		pf.MOD();
		break;
	  case Parser.EQ:
		evaluate(n.left());
		evaluate(n.right());
		pf.EQ();
		break;
	  case Parser.NE:
		evaluate(n.left());
		evaluate(n.right());
		pf.NE();
		break;
	  case Parser.GE:
		evaluate(n.left());
		evaluate(n.right());
		pf.GE();
		break;
	  case Parser.GT:
		evaluate(n.left());
		evaluate(n.right());
		pf.GT();
		break;
	  case Parser.LE:
		evaluate(n.left());
		evaluate(n.right());
		pf.LE();
		break;
	  case Parser.LT:
		evaluate(n.left());
		evaluate(n.right());
		pf.LT();
		break;
	}
      }
}
