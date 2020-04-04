#include "NodeBinary.hpp"

void NodeBinary::accept(Compiler& c) { c.nodeBinary(*this); }
NodeBinary::~NodeBinary() { delete arg; }
void NodeBinary::print(std::ostream *out) {
	if (out) (*out) << "NodeBinary: ";
	NodeUnary::print(out);
	arg->print(out);
}
