#ifndef _NODEIFELSE_HH_
#define _NODEIFELSE_HH_

#include "NodeTernary.hpp"

class NodeIfelse : public NodeTernary {
public:
	inline NodeIfelse(Node& arg1, Node& arg2, Node& arg3, int lineno = 0)
		: NodeTernary(arg1, arg2, arg3, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEIFELSE_HH_ */
