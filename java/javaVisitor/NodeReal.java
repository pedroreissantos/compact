import java.util.*;

public class NodeReal extends Node {
	protected double value; // value of literal floating point
	NodeReal(double val) { super(); value = val; }
	NodeReal(double val, int lineno) { super(lineno); value = val; }
	public double real() { return value; }
	public void accept(Compiler c) { c.nodeReal(this); }
}
