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
	public abstract void evaluate(Node n);
}
