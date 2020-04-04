import java.util.*;

public abstract class Compiler {
	private Tree syntax; // syntax tree

	protected void tree(Tree n) { syntax = n; }

	public static String extension(String filename, String ext) {
		int pos = filename.lastIndexOf('.');
		if (pos == -1) return filename + "." + ext;
		else return filename.substring(0, pos) + ext;
	}

	Compiler() {}
	public Tree tree() { return syntax; }

	public abstract boolean parse();
	public abstract boolean generate();
	public abstract void evaluate(Tree n);
}
