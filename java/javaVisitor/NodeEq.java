import java.util.*;

public class NodeEq extends NodeBinary {
	NodeEq(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeEq(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeEq(this); }
}
