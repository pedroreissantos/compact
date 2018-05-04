#ifndef _NODEDATA_HH_
#define _NODEDATA_HH_

#include "Node.hpp"

class NodeData : public Node { // not null-terminated array of bytes
protected:
	char *_value; // value of literal array of bytes
	int _size;
public:
	inline NodeData(char *val, int sz, int lineno = 0) :
		Node(lineno) { _value = val; _size = sz; }
	inline char *data() { return _value; }
	inline int size() { return _size; }
	void accept(Compiler& c);
	void print(std::ostream *out = 0);
};
#endif /* _NODEDATA_HH_ */
