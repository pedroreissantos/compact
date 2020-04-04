#include "NodeTernary.hpp"

void NodeTernary::accept(Compiler& c) { c.nodeTernary(*this); }
NodeTernary::~NodeTernary() { delete arg; }
void NodeTernary::print(std::ostream *out) {
	if (out) (*out) << "NodeTernary: ";
	NodeBinary::print(out);
	arg->print(out);;
}
