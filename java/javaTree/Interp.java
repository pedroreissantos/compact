import java.io.*;
import java.util.*;

public class Interp extends Compiler {
	private Parser parser;
	private PrintWriter out;
	private StreamTokenizer in;
	private Stack<Integer> stk;

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
		stk = new Stack<Integer>();
	}
	public boolean parse() { return parser.yyparse() == 0; }
	public boolean generate() {
		if (parser.errors() > 0) return false;
		evaluate(parser.syntax());
		return true;
	}
	public void evaluate(Tree n) {
	  String name;
	  int arg1, arg2;

	  switch (n.label()) {
	    case Parser.INTEGER: stk.push(n.value()); break;
	    case Parser.STRING: out.println(n.text()); break;
	  case Parser.PROGRAM:
		evaluate(n.left());
		if (n.right() != null) evaluate(n.right());
		break;
	  case Parser.END:
	  case Parser.START:
		break;
	  case Parser.VARIABLE: stk.push(parser.symbols().get(n.text())); break;
	  case Parser.WHILE:
		evaluate(n.right()); // eval condition to TOS
		while (stk.pop() != 0) {
			evaluate(n.left().right()); // eval instruction
			evaluate(n.right()); // eval condition, again...
		}
		break;
	  case Parser.IF:
		evaluate(n.left()); // eval condition to TOS
		if (stk.pop() != 0)
			evaluate(n.right()); // eval instruction
		break;
	  case Parser.ELSE:
		evaluate(n.left().left()); // eval condition to TOS
		if (stk.pop() != 0)
			evaluate(n.left().right()); // eval then
		else
			evaluate(n.right()); // eval else
		break;
	  case Parser.READ:
		name = n.left().text();
		try {
		    while (in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_NUMBER) {
			    parser.symbols().put(name, (int)in.nval);
			    break;
			}
			if (in.ttype != StreamTokenizer.TT_EOL)
			    System.err.println("Parse error at line "+in.lineno());
		    }
		} catch (IOException e) {
		    System.err.println("IO error: " + e);
		    System.exit(2);
		}
		break;
	  case Parser.PRINT:
		evaluate(n.left()); // eval condition to TOS
		out.println(stk.pop());
		break;
	  case Parser.ASSIGN:
		evaluate(n.right()); // eval expression to TOS
		parser.symbols().put(n.left().text(), stk.pop());
		break;
	  case Parser.UMINUS:
		evaluate(n.left()); // eval condition to TOS
		stk.push(new Integer(-stk.pop()));
		break;
	  case Parser.ADD:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 + arg2));
		break;
	  case Parser.SUB:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 - arg2));
		break;
	  case Parser.MUL:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 * arg2));
		break;
	  case Parser.DIV:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 / arg2));
		break;
	  case Parser.MOD:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 % arg2));
		break;
	  case Parser.EQ:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 == arg2 ? 1 : 0));
		break;
	  case Parser.NE:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 != arg2 ? 1 : 0));
		break;
	  case Parser.GE:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 >= arg2 ? 1 : 0));
		break;
	  case Parser.GT:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 > arg2 ? 1 : 0));
		break;
	  case Parser.LE:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 <= arg2 ? 1 : 0));
		break;
	  case Parser.LT:
		evaluate(n.left()); // eval condition to TOS
		arg1 = stk.pop();
		evaluate(n.right()); // eval expression to TOS
		arg2 = stk.pop();
		stk.push(new Integer(arg1 < arg2 ? 1 : 0));
		break;
      }
    }
}
