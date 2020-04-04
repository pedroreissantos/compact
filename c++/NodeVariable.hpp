#ifndef _NODEVARIABLE_HH_
#define _NODEVARIABLE_HH_

#include "NodeString.hpp"

class NodeVariable : public NodeString {
public:
	inline NodeVariable(std::string val, int lineno = 0) :
		NodeString(val, lineno) { }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEVARIABLE_HH_ */
