#include "NodeUminus.hpp"

void NodeUminus::accept(Compiler& c) { c.nodeUminus(*this); }
void NodeUminus::print(std::ostream *out) {
	if (out) (*out) << "NodeUminus: ";
	NodeUnary::print(out);
}
