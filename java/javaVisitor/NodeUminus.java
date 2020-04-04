import java.util.*;

public class NodeUminus extends NodeUnary {
	NodeUminus(Node arg1) { super(arg1); }
	NodeUminus(Node arg1, int lineno) { super(arg1, lineno); }
	public void accept(Compiler c) { c.nodeUminus(this); }
}
