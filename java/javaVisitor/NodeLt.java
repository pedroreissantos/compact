import java.util.*;

public class NodeLt extends NodeBinary {
	NodeLt(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeLt(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeLt(this); }
}
