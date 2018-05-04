#include "NodeWhile.hpp"

void NodeWhile::accept(Compiler& c) { c.nodeWhile(*this); }
void NodeWhile::print(std::ostream *out) {
	if (out) (*out) << "NodeWhile: ";
	NodeBinary::print(out);
}
