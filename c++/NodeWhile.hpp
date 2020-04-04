#ifndef _NODEWHILE_HH_
#define _NODEWHILE_HH_

#include "NodeBinary.hpp"

class NodeWhile : public NodeBinary {
public:
	inline NodeWhile(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEWHILE_HH_ */
