#include "NodeReal.hpp"

void NodeReal::accept(Compiler& c) { c.nodeReal(*this); }
void NodeReal::print(std::ostream *out) {
	if (out) (*out) << "NodeReal: " << value << " ";
	Node::print(out);
}
