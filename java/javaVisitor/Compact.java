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
		parser.syntax().accept(this);
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
	public void node(Node n) {}
	public void nodeInteger(NodeInteger n) { pf.INT(n.integer()); }
	public void nodeReal(NodeReal n) {} // No reals
	public void nodeString(NodeString n) {
		String lbl = pf.label();
		pf.RODATA();			// strings are DATA readonly
		pf.ALIGN();			// make sure we are aligned
		pf.LABEL(lbl);			// give the string a name
		pf.STR(n.text());		// output string characters
		// make the call
		pf.TEXT();			// return to the TEXT segment
		pf.ADDR(lbl);			// the string to be printed
		pf.CALL("_prints");		// call the print rotine
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// remove the string argument
	}
	public void nodeData(NodeData n) { } // No opaque data literals
	public void nodeUnary(NodeUnary n) { } // Use uminus operator
	public void nodeBinary(NodeBinary n) { } // Use specific operators
	public void nodeTernary(NodeTernary n) { } // Use specific operators
	public void nodeList(NodeList n) {
	  for (int i = 0; i < n.size(); i++)
	    n.elementAt(i).accept(this);
	}
	public void nodeVariable(NodeVariable n) {
		if (vars.containsKey(n.text())) {
	  		pf.ADDR(n.text());
	  		pf.LOAD();
		}
	}
	public void nodeWhile(NodeWhile n) {
		String lbl1 = pf.label(), lbl2 = pf.label();
		pf.LABEL(lbl1);
		n.first().accept(this);
		pf.JZ(lbl2);
		n.second().accept(this);
		pf.JMP(lbl1);
		pf.LABEL(lbl2);
	}
	public void nodeIf(NodeIf n) {
		String lbl1 = pf.label();
		n.first().accept(this);
		pf.JZ(lbl1);
		n.second().accept(this);
		pf.LABEL(lbl1);
	}
	public void nodeIfelse(NodeIfelse n) {
		String lbl1 = pf.label(), lbl2 = pf.label();
		n.first().accept(this);
		pf.JZ(lbl1);
		n.second().accept(this);
		pf.JMP(lbl2);
		pf.LABEL(lbl1);
		n.third().accept(this);
		pf.LABEL(lbl2);
	}
	public void nodeRead(NodeRead n) {
		String name = ((NodeString)n.first()).text();
		if (vars.containsKey(name)) {
			pf.CALL("_readi");
			pf.PUSH();
			pf.ADDR(name);
			pf.STORE();
		}
		else
			System.err.println(name + ": variable not found");
	}
	public void nodePrint(NodePrint n) {
		n.first().accept(this);
		pf.CALL("_printi");		// call the print function
		pf.CALL("_println");		// print a newline
		pf.TRASH(4);			// delete the printed value
	}
	public void nodeAssign(NodeAssign n) {
		String name = ((NodeString)n.first()).text();
		if (!vars.containsKey(name)) {	// variable not found ?
			pf.DATA();		// variables are DATA
			pf.ALIGN();		// make sure we are aligned
			pf.LABEL(name);		// name variable location
			pf.CONST(0);		// initialize it to 0 (zero)
			pf.TEXT();		// return to the TEXT segment
			vars.put(name, new Integer(0));	// create the var
		}
		n.second().accept(this);	// determine the new value
		pf.ADDR(name);			// where to store the value
		pf.STORE();			// store the value at address
	}
	public void nodeUminus(NodeUminus n) {
		n.first().accept(this);		// determine the new value
		pf.NEG();			// make the 2-compliment
	}
	public void nodeAdd(NodeAdd n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.ADD();
	}
	public void nodeSub(NodeSub n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.SUB();
	}
	public void nodeMul(NodeMul n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.MUL();
	}
	public void nodeDiv(NodeDiv n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.DIV();
	}
	public void nodeMod(NodeMod n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.MOD();
	}
	public void nodeEq(NodeEq n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.EQ();
	}
	public void nodeNe(NodeNe n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.NE();
	}
	public void nodeGe(NodeGe n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.GE();
	}
	public void nodeGt(NodeGt n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.GT();
	}
	public void nodeLe(NodeLe n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.LE();
	}
	public void nodeLt(NodeLt n) {
		n.first().accept(this);
		n.second().accept(this);
		pf.LT();
	}
}
