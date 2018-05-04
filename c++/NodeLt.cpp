#include "NodeLt.hpp"

void NodeLt::accept(Compiler& c) { c.nodeLt(*this); }
void NodeLt::print(std::ostream *out) {
	if (out) (*out) << "NodeLt: ";
	NodeBinary::print(out);
}
