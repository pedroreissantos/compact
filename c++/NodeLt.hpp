#ifndef _NODELT_HH_
#define _NODELT_HH_

#include "NodeBinary.hpp"

class NodeLt : public NodeBinary {
public:
	inline NodeLt(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODELT_HH_ */
