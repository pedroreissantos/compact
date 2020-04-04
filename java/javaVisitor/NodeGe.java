import java.util.*;

public class NodeGe extends NodeBinary {
	NodeGe(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeGe(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeGe(this); }
}
