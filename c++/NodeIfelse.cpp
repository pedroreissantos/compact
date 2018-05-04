#include "NodeIfelse.hpp"

void NodeIfelse::accept(Compiler& c) { c.nodeIfelse(*this); }
void NodeIfelse::print(std::ostream *out) {
	if (out) (*out) << "NodeIfelse: ";
	NodeTernary::print(out);
}
