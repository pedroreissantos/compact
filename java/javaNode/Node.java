import java.util.*;

public class Node {
	private int	attrib;
	private int	place;

	Node() { this.attrib = -1; }
	Node(int attrib) { this.attrib = attrib; }
	public int attrib() { return attrib; }
	protected void attrib(int attrib) { this.attrib = attrib; }

	public int place() { return place; }
	public int place(int plc) { return place = plc; }
}
