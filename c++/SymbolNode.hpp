#ifndef _SYMBOLNODE_HH_
#define _SYMBOLNODE_HH_

#include "Symbol.hpp"

class SymbolNode : public Symbol {
	int val;
public:
	inline SymbolNode(int v = 0) { val = v; }
	char *name();
	int integer();
};

#endif /* _SYMBOL_HH_ */
