#ifndef _NODEADD_HH_
#define _NODEADD_HH_

#include "NodeBinary.hpp"

class NodeAdd : public NodeBinary {
public:
	inline NodeAdd(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary (arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEADD_HH_ */
