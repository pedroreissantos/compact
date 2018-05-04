#include "NodeEq.hpp"

void NodeEq::accept(Compiler& c) { c.nodeEq(*this); }
void NodeEq::print(std::ostream *out) {
	if (out) (*out) << "NodeEq: ";
	NodeBinary::print(out);
}
