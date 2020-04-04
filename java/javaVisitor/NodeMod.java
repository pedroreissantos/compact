import java.util.*;

public class NodeMod extends NodeBinary {
	NodeMod(Node arg1, Node arg2) { super(arg1, arg2); }
	NodeMod(Node arg1, Node arg2, int lineno) { super(arg1, arg2, lineno); }
	public void accept(Compiler c) { c.nodeMod(this); }
}
