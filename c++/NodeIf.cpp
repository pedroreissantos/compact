#include "NodeIf.hpp"

void NodeIf::accept(Compiler& c) { c.nodeIf(*this); }
void NodeIf::print(std::ostream *out) {
	if (out) (*out) << "NodeIf: ";
	NodeBinary::print(out);
}
