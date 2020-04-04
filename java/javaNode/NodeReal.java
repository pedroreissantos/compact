import java.util.*;

public class NodeReal extends Node {
	private double	d;

	NodeReal(int attrib, double d) { super(attrib); this.d = d; }
	public double real() { return d; }
}
