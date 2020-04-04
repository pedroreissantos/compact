#include "NodeInteger.hpp"

void NodeInteger::accept(Compiler& c) { c.nodeInteger(*this); }
void NodeInteger::print(std::ostream *out) {
	if (out) (*out) << "NodeInteger: " << value << " ";
	Node::print(out);
}
