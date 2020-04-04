import java.util.*;

public class NodeIf extends NodeBinary {
	NodeIf(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeIf(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeIf(this); }
}
