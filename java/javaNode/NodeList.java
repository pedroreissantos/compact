import java.util.*;

public class NodeList extends Node {
	private List<Node> list;
	private Object state;

	NodeList(int attrib) { super(attrib); list = new ArrayList<Node>(); }
	NodeList(int attrib, Node n) { super(attrib); list = new ArrayList<Node>(); list.add(n); }
	NodeList(int attrib, Node n1, Node n2) { this(attrib, n1); list.add(n2); }
	NodeList(int attrib, Node n1, Node n2, Node n3) { this(attrib, n1); list.add(n2); }
	NodeList(int attrib, Node n1, Node n2, Node n3, Node n4) { this(attrib, n1, n2, n3); list.add(n4); }
	NodeList(int attrib, Node n1, Node n2, Node n3, Node n4, Node n5) { this(attrib, n1, n2, n3, n4); list.add(n5); }
	NodeList(int attrib, Node n1, Node n2, Node n3, Node n4, Node n5, Node n6) { this(attrib, n1, n2, n3, n4, n5); list.add(n6); }
	public void append(Node n) { list.add(n); }
	public int size() { return list.size(); }
	public Node get(int i) { return list.get(i); }
	public Node first() { return list.get(0); }
	public Node second() { return list.get(1); }
	public Node third() { return list.get(2); }

	public NodeList left() { return (NodeList)list.get(0); }
	public NodeList right() { return (NodeList)list.get(1); }
	Object state() { return state; }
	void state(Object st) { state = st; }
	public int label() { return super.attrib(); }
}
