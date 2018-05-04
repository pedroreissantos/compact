#include "NodeMul.hpp"

void NodeMul::accept(Compiler& c) { c.nodeMul(*this); }
void NodeMul::print(std::ostream *out) {
	if (out) (*out) << "NodeMul: ";
	NodeBinary::print(out);
}
