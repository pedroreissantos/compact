#ifndef _TABID_HH_
#define _TABID_HH_

#include <vector>
#include <stack>
#include <map>
#include <string>
#define String std::string
#include "Symbol.hpp"

class Tabid {
	typedef std::map<String,Symbol*> Hashtable;
	typedef std::vector<Hashtable*> Stack;
	std::vector<Stack*> tables; // stacks for root swap
	Stack *symbols; // one Hashtable for each bucket
public:
	inline Tabid() { this->namearea(); } // create the first namearea
	inline int nameareas()	{ return tables.size(); }
	int namearea();
	inline void namearea(int tab) { // set current namearea by number
		symbols = tables[tab];
	}
	inline void push()	{ symbols->push_back(new Hashtable); }
	void pop();
	inline int depth()	{ return symbols->size(); }
	Symbol *put(String name, Symbol *value); // add or replace
	bool containsKey(String name) { return symbols->back()->count(name) > 0; }
	Symbol *get(String name);
};
#endif /* _TABID_HH_ */
