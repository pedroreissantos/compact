import java.util.*;

public class NodeInteger extends Node {
	protected int value; // value of literal integer
	NodeInteger(int val) { super(); value = val; }
	NodeInteger(int val, int lineno) { super(lineno); value = val; }
	public int integer() { return value; }
	public void accept(Compiler c) { c.nodeInteger(this); }
}
