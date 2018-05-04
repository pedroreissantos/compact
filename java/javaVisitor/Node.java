import java.util.*;

public class Node {
	private int	line; // user defined attributes

	Node() { line = 0; }
	Node(int lineno) { line = lineno; }
	public int lineno() { return line; }
	public void accept(Compiler c) { c.node(this); }
}
