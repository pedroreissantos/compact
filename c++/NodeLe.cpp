#include "NodeLe.hpp"

void NodeLe::accept(Compiler& c) { c.nodeLe(*this); }
void NodeLe::print(std::ostream *out) {
	if (out) (*out) << "NodeLe: ";
	NodeBinary::print(out);
}
