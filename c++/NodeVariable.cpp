#include "NodeVariable.hpp"

void NodeVariable::accept(Compiler& c) { c.nodeVariable(*this); }
void NodeVariable::print(std::ostream *out) {
	if (out) (*out) << "NodeVariable: ";
	NodeString::print(out);
}
