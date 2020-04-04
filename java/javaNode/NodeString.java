import java.util.*;

public class NodeString extends Node {
	private String	s;

	NodeString(int attrib, String text) { super(attrib); this.s = text; }
	public String text() { return s; }
}
