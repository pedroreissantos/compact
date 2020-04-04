#include "NodeList.hpp"
#include <stdarg.h>

NodeList::NodeList(int lineno, int nops, ...) : Node(lineno) {
  va_list ap;
  va_start(ap, nops);
  for (int i = 0; i < nops; i++)
    list.push_back(va_arg(ap, Node*));
  va_end(ap);
}

NodeList::~NodeList() {
  for (int i = 0; i < list.size(); i++)
    delete list[i];
  list.clear();
}

void NodeList::accept(Compiler& c) { c.nodeList(*this); }
void NodeList::print(std::ostream *out) {
	if (out) (*out) << "NodeList: " << list.size() << " ";
	for (int i = 0; i < list.size(); i++)
		list[i]->print(out);
	Node::print(out);
}
