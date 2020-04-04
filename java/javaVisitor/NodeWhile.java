import java.util.*;

public class NodeWhile extends NodeBinary {
	NodeWhile(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeWhile(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeWhile(this); }
}
