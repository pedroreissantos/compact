import java.util.*;

public class NodeNe extends NodeBinary {
	NodeNe(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeNe(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeNe(this); }
}
