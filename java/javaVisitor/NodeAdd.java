import java.util.*;

public class NodeAdd extends NodeBinary {
	NodeAdd(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeAdd(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeAdd(this); }
}
