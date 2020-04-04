import java.util.*;

public class NodeData extends Node { // not null-terminated array of bytes
	protected byte[] value; // value of literal array of bytes
	NodeData(byte[] val) { super(); value = val; }
	NodeData(byte[] val, int lineno) { super(lineno); value = val; }
	public byte[] data() { return value; }
	public void accept(Compiler c) { c.nodeData(this); }
}
