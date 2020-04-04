import java.util.*;

public class NodeBinary extends NodeUnary {
	protected Node arg;
	NodeBinary(Node arg1, Node arg2) { super(arg1); arg = arg2; }
	NodeBinary(Node arg1, Node arg2, int lineno) {
		super(arg1, lineno); arg = arg2;
	}
	public Node second() { return arg; }
	public void accept(Compiler c) { c.nodeBinary(this); }
}
