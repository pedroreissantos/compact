#include "NodeGe.hpp"

void NodeGe::accept(Compiler& c) { c.nodeGe(*this); }
void NodeGe::print(std::ostream *out) {
	if (out) (*out) << "NodeGe: ";
	NodeBinary::print(out);
}
