import java.util.*;

public class NodeTernary extends NodeBinary {
	protected Node arg;
	NodeTernary(Node arg1, Node arg2, Node arg3) {
		super(arg1, arg2); arg = arg3; }
	NodeTernary(Node arg1, Node arg2, Node arg3, int lineno) {
		super(arg1, arg2, lineno); arg = arg3; }
	public Node third() { return arg; }
	public void accept(Compiler c) { c.nodeTernary(this); }
}
