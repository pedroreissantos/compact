#include "NodeAssign.hpp"

void NodeAssign::accept(Compiler& c) { c.nodeAssign(*this); }
void NodeAssign::print(std::ostream *out) {
	if (out) (*out) << "NodeAssign: ";
	NodeBinary::print(out);
}
