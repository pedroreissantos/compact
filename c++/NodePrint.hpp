#ifndef _NODEPRINT_HH_
#define _NODEPRINT_HH_

#include "NodeUnary.hpp"

class NodePrint : public NodeUnary {
public:
	inline NodePrint(Node& arg1, int lineno = 0) :
		NodeUnary(arg1, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEPRINT_HH_ */
