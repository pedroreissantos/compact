import java.util.*;

public class NodeIfelse extends NodeTernary {
	NodeIfelse(Node arg1, Node arg2, Node arg3) {
		super(arg1, arg2, arg3); }
	NodeIfelse(Node arg1, Node arg2, Node arg3, int lineno) {
		super(arg1, arg2, arg3, lineno); }
	public void accept(Compiler c) { c.nodeIfelse(this); }
}
