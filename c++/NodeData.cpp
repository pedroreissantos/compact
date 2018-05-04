#include "NodeData.hpp"

void NodeData::accept(Compiler& c) { c.nodeData(*this); }
void NodeData::print(std::ostream *out) {
	if (out) (*out) << "NodeData: size = " << size();
	Node::print(out);
}
