import java.util.*;

public class NodeInteger extends Node {
	private int	i;

	NodeInteger(int attrib, int i) { super(attrib); this.i = i; }
	public int integer() { return i; }
	public int value() { return i; }
}
