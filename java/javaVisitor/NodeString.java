import java.util.*;

public class NodeString extends Node {
	protected String value; // value of literal string
	NodeString(String val) { super(); value = val; }
	NodeString(String val, int lineno) { super(lineno); value = val; }
	public String text() { return value; }
	public void accept(Compiler c) { c.nodeString(this); }
}
