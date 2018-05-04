import java.util.*;

public class NodeGt extends NodeBinary {
	NodeGt(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeGt(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeGt(this); }
}
