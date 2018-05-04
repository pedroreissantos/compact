#include "NodeNe.hpp"

void NodeNe::accept(Compiler& c) { c.nodeNe(*this); }
void NodeNe::print(std::ostream *out) {
	if (out) (*out) << "NodeNe: ";
	NodeBinary::print(out);
}
