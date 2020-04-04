import java.util.*;

public class NodeDiv extends NodeBinary {
	NodeDiv(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeDiv(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeDiv(this); }
}
