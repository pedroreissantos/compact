import java.util.*;

public class NodePrint extends NodeUnary {
	NodePrint(Node arg1) { super(arg1); }
	NodePrint(Node arg1, int lineno) { super(arg1, lineno); }
	public void accept(Compiler c) { c.nodePrint(this); }
}
