#ifndef _NODEINTEGER_HH_
#define _NODEINTEGER_HH_

#include "Node.hpp"

class NodeInteger : public Node {
protected:
	int value; // value of literal integer
public:
	inline NodeInteger(int val, int lineno = 0) : Node(lineno)
		{ value = val; }
	inline int integer() { return value; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEINTEGER_HH_ */
