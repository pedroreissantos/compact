#ifndef _NODEGT_HH_
#define _NODEGT_HH_

#include "NodeBinary.hpp"

class NodeGt : public NodeBinary {
public:
	inline NodeGt(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEGT_HH_ */
