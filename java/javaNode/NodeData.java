import java.util.*;

public class NodeData extends Node {
	private byte[]	d;

	NodeData(int attrib, byte[] d) { super(attrib); this.d = d; }
	public byte[] data() { return d; }
}
