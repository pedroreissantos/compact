#ifndef _NODEBINARY_HH_
#define _NODEBINARY_HH_

#include "NodeUnary.hpp"

class NodeBinary : public NodeUnary {
protected:
	Node *arg;
public:
	inline NodeBinary(Node& arg1, Node& arg2, int lineno = 0) :
		NodeUnary(arg1, lineno) { arg = &arg2; }
	~NodeBinary();
	inline Node& second() { return *arg; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEBINARY_HH_ */
