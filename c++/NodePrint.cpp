#include "NodePrint.hpp"

void NodePrint::accept(Compiler& c) { c.nodePrint(*this); }
void NodePrint::print(std::ostream *out) {
	if (out) (*out) << "NodePrint: ";
	NodeUnary::print(out);
}
