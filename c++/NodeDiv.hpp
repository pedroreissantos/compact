#ifndef _NODEDIV_HH_
#define _NODEDIV_HH_

#include "NodeBinary.hpp"

class NodeDiv : public NodeBinary {
public:
	inline NodeDiv(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEDIV_HH_ */
