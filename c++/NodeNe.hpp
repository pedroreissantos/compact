#ifndef _NODENE_HH_
#define _NODENE_HH_

#include "NodeBinary.hpp"

class NodeNe : public NodeBinary {
public:
	inline NodeNe(Node& arg1, Node& arg2, int lineno = 0) :
		NodeBinary(arg1, arg2, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODENE_HH_ */
