import java.util.*;

public class Tabid<T> {
	private Vector<Stack<Hashtable<String,T>>> tables; // stacks for root swap
	private Stack<Hashtable<String,T>> symbols; // one Hashtable for each bucket
	Tabid() {
		tables = new Vector<Stack<Hashtable<String,T>>>();
		namearea(); // create the first namearea
	}
	public int nameareas()	{ return tables.size(); }
	public int namearea() { // create name area and return its number
		tables.addElement(symbols = new Stack<Hashtable<String,T>>());
		push(); // insert globals bucket into the namearea
		return nameareas();
	}
	public void namearea(int tab) { // set current namearea by number
		symbols = tables.elementAt(tab);
	}
	public void push()	{ symbols.push(new Hashtable<String,T>()); }
	public void pop()	{ symbols.pop(); }
	public int depth()	{ return symbols.size(); }
	public T put(String name, T value) { // add or replace
		return symbols.peek().put(name, value);
	}
	public boolean containsKey(String name) { return get(name) != null; }
	public Collection<String> keys() {
		List<String> lst = new ArrayList<String>();
		Enumeration<String> e = symbols.peek().keys();
		while (e.hasMoreElements())
			lst.add(e.nextElement());
		return lst;
	}
	public Collection<T> values() { return symbols.peek().values(); }
	public T get(String name) {
		Hashtable<String,T> h;
		Enumeration<Hashtable<String,T>> e = symbols.elements();
		while (e.hasMoreElements())
			if ((h = e.nextElement()).containsKey(name))
				return h.get(name);
		return null;
	}
	public String toString()	{
		return "Tabid has " + tables.size() + " nameareas (" +
			tables.indexOf(symbols) + " is current) " +
			tables.toString();
	}
}
