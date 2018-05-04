import java.io.*;

public class Compact extends Compiler {
	private Parser parser;
	private Postfix pf;
	private Tabid<Integer> vars;

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
		vars = new Tabid<Integer>();
	}
	Compact(String in) throws IOException { this(in, false); }
	Compact(String in, boolean debug) throws IOException {
		parser = new Parser(new FileReader(in), debug);
		pf = new PFi386(extension(in, ".asm"));
		vars = new Tabid<Integer>();
	}
	public boolean parse() { return parser.yyparse() == 0; }
	public boolean generate() {
		if (parser.errors() > 0) return false;
		pf.TEXT();
		pf.ALIGN();
		pf.GLOBLfunc("_main");
		pf.LABEL("_main");
		pf.START();
		evaluate(parser.syntax());
		pf.INT(0);
		pf.POP();
		pf.LEAVE();
		pf.RET();
		// import library functions
		pf.EXTRN("_readi");
		pf.EXTRN("_printi");
		pf.EXTRN("_prints");
		pf.EXTRN("_println");
		return true;
	}
	public void evaluate(Node n) {
	  NodeList l;
	  String name, lbl1, lbl2;

	  switch (n.attrib()) {
	    case Parser.INTEGER: pf.INT(((NodeInteger)n).integer()); break;
	    case Parser.STRING:
		lbl1 = pf.label();
		pf.RODATA();			// strings are DATA readonly
		pf.ALIGN();			// make sure we are aligned
		pf.LABEL(lbl1);			// give the string a name
		pf.STR(((NodeString)n).text());		// output string characters
		// make the call
		pf.TEXT();			// return to the TEXT segment
		pf.ADDR(lbl1);			// the string to be printed
		pf.CALL("_prints");		// call the print rotine
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// remove the string argument
		break;
	  case Parser.STMT:
		l = (NodeList)n;
		for (int i = 0; i < l.size(); i++)
		  evaluate(l.get(i));
		break;
	  case Parser.VARIABLE:
		if (vars.containsKey(((NodeString)n).text())) {
	  		pf.ADDR(((NodeString)n).text());
	  		pf.LOAD();
		}
		break;
	  case Parser.WHILE:
		lbl1 = pf.label();
		lbl2 = pf.label();
		l = (NodeList)n;
		pf.LABEL(lbl1);
		evaluate(l.first());
		pf.JZ(lbl2);
		evaluate(l.second());
		pf.JMP(lbl1);
		pf.LABEL(lbl2);
		break;
	  case Parser.IF:
		lbl1 = pf.label();
		l = (NodeList)n;
		evaluate(l.first());
		pf.JZ(lbl1);
		evaluate(l.second());
		pf.LABEL(lbl1);
		break;
	  case Parser.ELSE:
		lbl1 = pf.label();
		lbl2 = pf.label();
		l = (NodeList)n;
		evaluate(l.first());
		pf.JZ(lbl1);
		evaluate(l.second());
		pf.JMP(lbl2);
		pf.LABEL(lbl1);
		evaluate(l.third());
		pf.LABEL(lbl2);
		break;
	  case Parser.READ:
		l = (NodeList)n;
		name = (((NodeString)l.first())).text();
		if (vars.containsKey(name)) {
			pf.CALL("_readi");
			pf.PUSH();
			pf.ADDR(name);
			pf.STORE();
		}
		else
			System.err.println(name + ": variable not found");
		break;
	  case Parser.PRINT:
		l = (NodeList)n;
		evaluate(l.first());
		pf.CALL("_printi");		// call the print function
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// delete the printed value
		break;
	  case Parser.ASSIGN:
		l = (NodeList)n;
		name = (((NodeString)l.first())).text();
		if (!vars.containsKey(name)) {	// variable not found ?
			pf.DATA();		// variables are DATA
			pf.ALIGN();		// make sure we are aligned
			pf.LABEL(name);		// name variable location
			pf.CONST(0);		// initialize it to 0 (zero)
			pf.TEXT();		// return to the TEXT segment
			vars.put(name, new Integer(0));	// create the var
		}
		evaluate(l.second());	// determine the new value
		pf.ADDR(name);			// where to store the value
		pf.STORE();			// store the value at address
		break;
	  case Parser.UMINUS:
		l = (NodeList)n;
		evaluate(l.first());		// determine the new value
		pf.NEG();			// make the 2-compliment
		break;
	  case Parser.ADD:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.ADD();
		break;
	  case Parser.SUB:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.SUB();
		break;
	  case Parser.MUL:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.MUL();
		break;
	  case Parser.DIV:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.DIV();
		break;
	  case Parser.MOD:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.MOD();
		break;
	  case Parser.EQ:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.EQ();
		break;
	  case Parser.NE:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.NE();
		break;
	  case Parser.GE:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.GE();
		break;
	  case Parser.GT:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.GT();
		break;
	  case Parser.LE:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.LE();
		break;
	  case Parser.LT:
		l = (NodeList)n;
		evaluate(l.first());
		evaluate(l.second());
		pf.LT();
		break;
	}
      }
}
