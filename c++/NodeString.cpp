#include "NodeString.hpp"

void NodeString::accept(Compiler& c) { c.nodeString(*this); }
void NodeString::print(std::ostream *out) {
	if (out) (*out) << "NodeString: \"" << value << "\" ";
	Node::print(out);
}
