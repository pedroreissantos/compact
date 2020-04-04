import java.util.*;

public class NodeAssign extends NodeBinary {
	NodeAssign(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeAssign(Node arg1, Node arg2, int lineno) {
		super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeAssign(this); }
}
