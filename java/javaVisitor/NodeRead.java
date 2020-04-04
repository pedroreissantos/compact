import java.util.*;

public class NodeRead extends NodeUnary {
	NodeRead(Node arg1) { super(arg1); }
	NodeRead(Node arg1, int lineno) { super(arg1, lineno); }
	public void accept(Compiler c) { c.nodeRead(this); }
}
