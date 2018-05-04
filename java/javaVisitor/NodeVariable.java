import java.util.*;

public class NodeVariable extends NodeString {
	NodeVariable(String val) { super(val); }
	NodeVariable(String val, int lineno) { super(val, lineno); }
	public void accept(Compiler c) { c.nodeVariable(this); }
}
