import java.util.*;

public class NodeLe extends NodeBinary {
	NodeLe(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeLe(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeLe(this); }
}
