#include "NodeRead.hpp"

void NodeRead::accept(Compiler& c) { c.nodeRead(*this); }
void NodeRead::print(std::ostream *out) {
	if (out) (*out) << "NodeRead: ";
	NodeUnary::print(out);
}
