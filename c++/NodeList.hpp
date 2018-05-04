#ifndef _NODELIST_HH_
#define _NODELIST_HH_

#include <vector>
#include "Node.hpp"

class NodeList : public Node {
protected:
	std::vector<Node*> list; // subNode: list of nodes
public:
	inline NodeList(int lineno = 0) : Node(lineno) { }
	NodeList(int lineno, int nops, ...);
	~NodeList();
	inline int size() { return list.size(); }
	inline std::vector<Node*>& nodes() { return list; }
	inline Node& elementAt(int i) { return *list[i]; }
	inline void prepend(Node& n) { list.insert(list.begin(), &n); }
	inline void append(Node& n) { list.push_back(&n); }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODELIST_HH_ */
