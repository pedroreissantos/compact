#include "Interp.hpp"
#include "Node.hpp"
#include "NodeInteger.hpp"
#include "NodeReal.hpp"
#include "NodeString.hpp"
#include "NodeData.hpp"
#include "NodeUnary.hpp"
#include "NodeBinary.hpp"
#include "NodeTernary.hpp"
#include "NodeList.hpp"

#include "NodePrint.hpp"
#include "NodeRead.hpp"
#include "NodeVariable.hpp"
#include "NodeAssign.hpp"
#include "NodeIf.hpp"
#include "NodeIfelse.hpp"
#include "NodeWhile.hpp"
#include "NodeAdd.hpp"
#include "NodeSub.hpp"
#include "NodeMul.hpp"
#include "NodeDiv.hpp"
#include "NodeMod.hpp"
#include "NodeEq.hpp"
#include "NodeNe.hpp"
#include "NodeGt.hpp"
#include "NodeGe.hpp"
#include "NodeLt.hpp"
#include "NodeLe.hpp"
#include "NodeUminus.hpp"

#include "SymbolNode.hpp"

bool Interp::parse() {
	if (parser->parse() == 0)
		tree(parser->syntax());
	// tree()->print(&cout);
	return parser->errors() == 0;
}
bool Interp::generate() {
	if (parser->errors() > 0) return false;
	tree()->accept(*this);
	return true;
}

void Interp::node(Node& n) {}
void Interp::nodeInteger(NodeInteger& n) { stk.push(n.integer()); }
void Interp::nodeReal(NodeReal& n) {} // No reals
void Interp::nodeString(NodeString& n) { std::cout << n.text() << "\n"; }
void Interp::nodeData(NodeData& n) { } // No opaque data literals
void Interp::nodeUnary(NodeUnary& n) { } // Use uminus operator
void Interp::nodeBinary(NodeBinary& n) { } // Use specific operators
void Interp::nodeTernary(NodeTernary& n) { } // Use specific operators
void Interp::nodeList(NodeList& n) {
  for (int i = 0; i < n.size(); i++)
    n.elementAt(i).accept(*this);
}
void Interp::nodeVariable(NodeVariable& n) {
	stk.push(((SymbolNode*)vars.get(n.text()))->integer());
}
void Interp::nodeWhile(NodeWhile& n) {
	n.first().accept(*this); // eval condition to TOS
	while (pop() != 0) {
		n.second().accept(*this); // eval instruction
		n.first().accept(*this); // eval condition, again...
	}
}
void Interp::nodeIf(NodeIf& n) {
	n.first().accept(*this); // eval condition to TOS
	if (pop() != 0)
		n.second().accept(*this); // eval instruction
}
void Interp::nodeIfelse(NodeIfelse& n) {
	n.first().accept(*this); // eval condition to TOS
	if (pop() != 0)
		n.second().accept(*this); // eval then
	else
		n.third().accept(*this); // eval else
}
void Interp::nodeRead(NodeRead& n) {
	String name = ((NodeString*)&n.first())->text();
	if (vars.containsKey(name)) {
	    int val;
	    std::cin >> val;
	    vars.put(name, new SymbolNode(val));
	}
	else
	    std::cerr << name + ": variable not found";
}
void Interp::nodePrint(NodePrint& n) {
	n.first().accept(*this); // eval condition to TOS
	std::cout << pop() << "\n";
}
void Interp::nodeAssign(NodeAssign& n) {
	n.second().accept(*this); // eval expression to TOS
	vars.put(((NodeString*)&n.first())->text(), new SymbolNode(pop()));
}
void Interp::nodeUminus(NodeUminus& n) {
	n.first().accept(*this); // eval condition to TOS
	stk.push(-pop());
}
void Interp::nodeAdd(NodeAdd& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 + arg2);
}
void Interp::nodeSub(NodeSub& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 - arg2);
}
void Interp::nodeMul(NodeMul& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 * arg2);
}
void Interp::nodeDiv(NodeDiv& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 / arg2);
}
void Interp::nodeMod(NodeMod& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 % arg2);
}
void Interp::nodeEq(NodeEq& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 == arg2);
}
void Interp::nodeNe(NodeNe& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 != arg2);
}
void Interp::nodeGe(NodeGe& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 >= arg2);
}
void Interp::nodeGt(NodeGt& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 > arg2);
}
void Interp::nodeLe(NodeLe& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 <= arg2);
}
void Interp::nodeLt(NodeLt& n) {
	n.first().accept(*this); // eval condition to TOS
	int arg1 = pop();
	n.second().accept(*this); // eval expression to TOS
	int arg2 = pop();
	stk.push(arg1 < arg2);
}
