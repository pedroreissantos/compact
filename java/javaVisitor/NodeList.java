import java.util.*;

public class NodeList extends Node {
	protected List<Node> list; // subNode: list of nodes
	NodeList() { super(); list = new ArrayList<Node>(); }
	NodeList(int lineno) { super(lineno); list = new ArrayList<Node>(); }
	public int size() { return list.size(); }
	public Iterator<Node> nodes() { return list.iterator(); }
	public Node elementAt(int i) { return list.get(i); }
	public void prepend(Node n) { list.add(0, n); }
	public void append(Node n) { list.add(n); }
	public void accept(Compiler c) { c.nodeList(this); }
}
