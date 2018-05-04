#include "NodeUnary.hpp"

void NodeUnary::accept(Compiler& c) { c.nodeUnary(*this); }
NodeUnary::~NodeUnary() { delete arg; }
void NodeUnary::print(std::ostream *out) {
	if (out) (*out) << "NodeUnary: ";
	arg->print(out);
	Node::print(out);
}
