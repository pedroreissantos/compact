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
		evaluate(parser.syntax());
		return true;
	}
	public void evaluate(Node n) {
	  NodeList l;
	  String name;
	  int arg1, arg2;

	  switch (n.attrib()) {
	    case Parser.INTEGER: stk.push((NodeInteger)n); break;
	    case Parser.STRING: out.println(((NodeString)n).text()); break;
	  case Parser.STMT:
		l = (NodeList)n;
		for (int i = 0; i < l.size(); i++)
		  evaluate(l.get(i));
		break;
	  case Parser.VARIABLE: stk.push(vars.get(((NodeString)n).text())); break;
	  case Parser.WHILE:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		while (stk.pop().integer() != 0) {
			evaluate(l.second()); // eval instruction
			evaluate(l.first()); // eval condition, again...
		}
		break;
	  case Parser.IF:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		if (stk.pop().integer() != 0)
			evaluate(l.second()); // eval instruction
		break;
	  case Parser.ELSE:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		if (stk.pop().integer() != 0)
			evaluate(l.second()); // eval then
		else
			evaluate(l.third()); // eval else
		break;
	  case Parser.READ:
		l = (NodeList)n;
		name = ((NodeString)l.first()).text();
		if (vars.containsKey(name)) {
		    try {
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
			    if (in.ttype == StreamTokenizer.TT_NUMBER) {
				vars.put(name, new NodeInteger(0,(int)in.nval));
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
		break;
	  case Parser.PRINT:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		out.println(stk.pop().integer());
		break;
	  case Parser.ASSIGN:
		l = (NodeList)n;
		evaluate(l.second()); // eval expression to TOS
		vars.put(((NodeString)l.first()).text(), stk.pop());
		break;
	  case Parser.UMINUS:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		stk.push(new NodeInteger(0,-stk.pop().integer()));
		break;
	  case Parser.ADD:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 + arg2));
		break;
	  case Parser.SUB:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 - arg2));
		break;
	  case Parser.MUL:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 * arg2));
		break;
	  case Parser.DIV:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 / arg2));
		break;
	  case Parser.MOD:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 % arg2));
		break;
	  case Parser.EQ:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 == arg2 ? 1 : 0));
		break;
	  case Parser.NE:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 != arg2 ? 1 : 0));
		break;
	  case Parser.GE:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 >= arg2 ? 1 : 0));
		break;
	  case Parser.GT:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 > arg2 ? 1 : 0));
		break;
	  case Parser.LE:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 <= arg2 ? 1 : 0));
		break;
	  case Parser.LT:
		l = (NodeList)n;
		evaluate(l.first()); // eval condition to TOS
		arg1 = stk.pop().integer();
		evaluate(l.second()); // eval expression to TOS
		arg2 = stk.pop().integer();
		stk.push(new NodeInteger(0,arg1 < arg2 ? 1 : 0));
		break;
      }
    }
}
