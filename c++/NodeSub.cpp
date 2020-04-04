#include "NodeSub.hpp"

void NodeSub::accept(Compiler& c) { c.nodeSub(*this); }
void NodeSub::print(std::ostream *out) {
	if (out) (*out) << "NodeSub: ";
	NodeBinary::print(out);
}
