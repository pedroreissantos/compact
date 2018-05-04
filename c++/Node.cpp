#include "Node.hpp"

void Node::accept(Compiler& c) { c.node(*this); }
Node::~Node() { }
void Node::print(std::ostream *out) { if (out) (*out) << "Node.\n"; }
