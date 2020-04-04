#ifndef _NODEGE_HH_
#define _NODEGE_HH_

#include "NodeBinary.hpp"

class NodeGe : public NodeBinary {
public:
	inline NodeGe(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEGE_HH_ */
