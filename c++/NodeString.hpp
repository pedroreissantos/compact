#ifndef _NODESTRING_HH_
#define _NODESTRING_HH_

#include <string>
#include "Node.hpp"
#define String std::string

class NodeString : public Node {
protected:
	String value; // value of literal string
public:
	inline NodeString(String val, int lineno = 0) : Node(lineno)
		{ value = val; }
	inline String text() { return value; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODESTRING_HH_ */
