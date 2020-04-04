#include "Tabid.hpp"

int Tabid::namearea() { // create name area and return its number
	tables.push_back(symbols = new Stack);
	push(); // insert globals bucket into the namearea
	return nameareas();
}

void Tabid::pop() {
	if (symbols->size() == 0) return;
	Hashtable *bucket = symbols->back();
	Hashtable::iterator iter = bucket->begin(),
			    end  = bucket->end();
	while (iter != end) {
		delete &iter->first;
		delete iter->second;
		iter++;
	}
	bucket->clear();
	symbols->pop_back();
	delete bucket;
}

Symbol *Tabid::put(String name, Symbol *value) { // add or replace
	Hashtable& bucket = *symbols->back();
	Symbol *old = bucket[name];
	bucket[name] = value;
	return old;
}

Symbol *Tabid::get(String name) {
	int size = symbols->size();
	for (int i = size-1; i >= 0; i--) {
	  Hashtable& bucket = *(*symbols)[i];
	  if (bucket.count(name) > 0)
	    return bucket[name];
	}
	return 0;
}
