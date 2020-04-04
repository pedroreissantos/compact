import java.util.*;

public class NodeUnary extends Node {
	protected Node arg;
	NodeUnary(Node arg1) { super(); arg = arg1; }
	NodeUnary(Node arg1, int lineno) { super(lineno); arg = arg1; }
	public Node first() { return arg; }
	public void accept(Compiler c) { c.nodeUnary(this); }
}
