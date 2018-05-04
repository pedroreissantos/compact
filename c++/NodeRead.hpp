#ifndef _NODEREAD_HH_
#define _NODEREAD_HH_

#include "NodeUnary.hpp"

class NodeRead : public NodeUnary {
public:
	inline NodeRead(Node& arg1, int lineno = 0) :
		NodeUnary(arg1, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEREAD_HH_ */
