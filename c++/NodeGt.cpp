#include "NodeGt.hpp"

void NodeGt::accept(Compiler& c) { c.nodeGt(*this); }
void NodeGt::print(std::ostream *out) {
	if (out) (*out) << "NodeGt: ";
	NodeBinary::print(out);
}
