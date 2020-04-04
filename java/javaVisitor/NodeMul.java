import java.util.*;

public class NodeMul extends NodeBinary {
	NodeMul(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeMul(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeMul(this); }
}
