#include "NodeMod.hpp"

void NodeMod::accept(Compiler& c) { c.nodeMod(*this); }
void NodeMod::print(std::ostream *out) {
	if (out) (*out) << "NodeMod: ";
	NodeBinary::print(out);
}
