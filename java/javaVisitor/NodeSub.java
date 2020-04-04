import java.util.*;

public class NodeSub extends NodeBinary {
	NodeSub(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeSub(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeSub(this); }
}
