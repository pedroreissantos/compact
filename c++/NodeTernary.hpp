#ifndef __NODETERNARY_HH_
#define __NODETERNARY_HH_

#include "NodeBinary.hpp"

class NodeTernary : public NodeBinary {
protected:
	Node *arg;
public:
	inline NodeTernary(Node& arg1, Node& arg2, Node& arg3, int lineno = 0)
		: NodeBinary(arg1, arg2, lineno) { arg = &arg3; }
	~NodeTernary();
	inline Node& third() { return *arg; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* __NODETERNARY_HH_ */
