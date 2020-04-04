import java.io.*;
import java.util.*;

public class Interp extends Compiler {
	private Parser parser;
	private PrintWriter out;
	private StreamTokenizer in;
	private Stack<NodeInteger> stk;
	private Tabid<NodeInteger> vars;

	public static void main(String args[]) {
		Interp interp = new Interp(args[0]);
		if (interp.parse())
			interp.generate();
	}
	Interp(String infile) {
		try {
			parser = new Parser(new FileReader(infile));
		} catch (FileNotFoundException e) {
			System.err.println(infile + e);
			parser = new Parser(new InputStreamReader(System.in));
		}
		Writer outp = new PrintWriter(System.out);
		out = new PrintWriter(outp, true);
		Reader inp = new InputStreamReader(System.in);
		in = new StreamTokenizer(inp);
		in.eolIsSignificant(true);
		stk = new Stack<NodeInteger>();
		vars = new Tabid<NodeInteger>();
	}
	public boolean parse() { return parser.yyparse() == 0; }
	public boolean generate() {
		if (parser.errors() > 0) return false;
		parser.syntax().accept(this);
		return true;
	}
	public void node(Node n) {}
	public void nodeInteger(NodeInteger n) { stk.push(n); }
	public void nodeReal(NodeReal n) {} // No reals
	public void nodeString(NodeString n) { out.println(n.text()); }
	public void nodeData(NodeData n) { } // No opaque data literals
	public void nodeUnary(NodeUnary n) { } // Use uminus operator
	public void nodeBinary(NodeBinary n) { } // Use specific operators
	public void nodeTernary(NodeTernary n) { } // Use specific operators
	public void nodeList(NodeList n) {
	  for (int i = 0; i < n.size(); i++)
	    n.elementAt(i).accept(this);
	}
	public void nodeVariable(NodeVariable n) {
		stk.push(vars.get(n.text()));
	}
	public void nodeWhile(NodeWhile n) {
		n.first().accept(this); // eval condition to TOS
		while (((NodeInteger)stk.pop()).integer() != 0) {
			n.second().accept(this); // eval instruction
			n.first().accept(this); // eval condition, again...
		}
	}
	public void nodeIf(NodeIf n) {
		n.first().accept(this); // eval condition to TOS
		if (((NodeInteger)stk.pop()).integer() != 0)
			n.second().accept(this); // eval instruction
	}
	public void nodeIfelse(NodeIfelse n) {
		n.first().accept(this); // eval condition to TOS
		if (((NodeInteger)stk.pop()).integer() != 0)
			n.second().accept(this); // eval then
		else
			n.third().accept(this); // eval else
	}
	public void nodeRead(NodeRead n) {
		String name = ((NodeString)n.first()).text();
		if (vars.containsKey(name)) {
		    try {
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
			    if (in.ttype == StreamTokenizer.TT_NUMBER) {
				vars.put(name, new NodeInteger((int)in.nval));
				break;
			    }
			    if (in.ttype != StreamTokenizer.TT_EOL)
				System.err.println("Parse error at line "+in.lineno());
		        }
		    } catch (IOException e) {
		    	System.err.println("IO error: " + e);
			System.exit(2);
		    }
		}
		else
		    System.err.println(name + ": variable not found");
	}
	public void nodePrint(NodePrint n) {
		n.first().accept(this); // eval condition to TOS
		out.println(((NodeInteger)stk.pop()).integer());
	}
	public void nodeAssign(NodeAssign n) {
		n.second().accept(this); // eval expression to TOS
		vars.put(((NodeString)n.first()).text(), stk.pop());
	}
	public void nodeUminus(NodeUminus n) {
		n.first().accept(this); // eval condition to TOS
		stk.push(new NodeInteger(-((NodeInteger)stk.pop()).integer()));
	}
	public void nodeAdd(NodeAdd n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 + arg2));
	}
	public void nodeSub(NodeSub n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 - arg2));
	}
	public void nodeMul(NodeMul n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 * arg2));
	}
	public void nodeDiv(NodeDiv n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 / arg2));
	}
	public void nodeMod(NodeMod n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 % arg2));
	}
	public void nodeEq(NodeEq n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 == arg2 ? 1 : 0));
	}
	public void nodeNe(NodeNe n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 != arg2 ? 1 : 0));
	}
	public void nodeGe(NodeGe n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 >= arg2 ? 1 : 0));
	}
	public void nodeGt(NodeGt n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 > arg2 ? 1 : 0));
	}
	public void nodeLe(NodeLe n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 <= arg2 ? 1 : 0));
	}
	public void nodeLt(NodeLt n) {
		n.first().accept(this); // eval condition to TOS
		int arg1 = ((NodeInteger)stk.pop()).integer();
		n.second().accept(this); // eval expression to TOS
		int arg2 = ((NodeInteger)stk.pop()).integer();
		stk.push(new NodeInteger(arg1 < arg2 ? 1 : 0));
	}
}
