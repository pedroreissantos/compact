#include "NodeAdd.hpp"

void NodeAdd::accept(Compiler& c) { c.nodeAdd(*this); }
void NodeAdd::print(std::ostream *out) {
	if (out) (*out) << "NodeAdd: ";
	NodeBinary::print(out);
}
