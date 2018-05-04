#include "NodeDiv.hpp"

void NodeDiv::accept(Compiler& c) { c.nodeDiv(*this); }
void NodeDiv::print(std::ostream *out) {
	if (out) (*out) << "NodeDiv: ";
	NodeBinary::print(out);
}
