import java.util.*;

public abstract class Compiler {
	private Node syntax; // syntax tree

	protected void tree(Node n) { syntax = n; }

	public static String extension(String filename, String ext) {
		int pos = filename.lastIndexOf('.');
		if (pos == -1) return filename + "." + ext;
		else return filename.substring(0, pos) + ext;
	}

	Compiler() {}
	public Node tree() { return syntax; }

	public abstract boolean parse();
	public abstract boolean generate();

	// Visitor functions called by the tree nodes
	public abstract void node(Node n);
	public abstract void nodeInteger(NodeInteger n);
	public abstract void nodeReal(NodeReal n);
	public abstract void nodeString(NodeString n);
	public abstract void nodeData(NodeData n);
	public abstract void nodeUnary(NodeUnary n);
	public abstract void nodeBinary(NodeBinary n);
	public abstract void nodeTernary(NodeTernary n);
	public abstract void nodeList(NodeList n);

	public abstract void nodePrint(NodePrint n);
	public abstract void nodeRead(NodeRead n);
	public abstract void nodeVariable(NodeVariable n);
	public abstract void nodeAssign(NodeAssign n);
	public abstract void nodeIf(NodeIf n);
	public abstract void nodeIfelse(NodeIfelse n);
	public abstract void nodeWhile(NodeWhile n);
	public abstract void nodeAdd(NodeAdd n);
	public abstract void nodeSub(NodeSub n);
	public abstract void nodeMul(NodeMul n);
	public abstract void nodeDiv(NodeDiv n);
	public abstract void nodeMod(NodeMod n);
	public abstract void nodeEq(NodeEq n);
	public abstract void nodeNe(NodeNe n);
	public abstract void nodeGt(NodeGt n);
	public abstract void nodeGe(NodeGe n);
	public abstract void nodeLt(NodeLt n);
	public abstract void nodeLe(NodeLe n);
	public abstract void nodeUminus(NodeUminus n);
}
