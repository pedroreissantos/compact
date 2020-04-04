#ifndef _NODEMUL_HH_
#define _NODEMUL_HH_

#include "NodeBinary.hpp"

class NodeMul : public NodeBinary {
public:
	inline NodeMul(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEMUL_HH_ */
