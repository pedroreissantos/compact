#ifndef _NODEUNARY_HH_
#define _NODEUNARY_HH_

#include "Node.hpp"

class NodeUnary : public Node {
protected:
	Node *arg;
public:
	inline NodeUnary(Node& arg1, int lineno = 0) : Node(lineno)
		{ arg = &arg1; }
	~NodeUnary();
	inline Node& first() { return *arg; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEUNARY_HH_ */
