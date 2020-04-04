#include "Compact.hpp"
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

bool Compact::parse() {
	if (parser->parse() == 0)
		tree(parser->syntax());
	return parser->errors() == 0;
}
bool Compact::generate() {
	if (parser->errors() > 0) return false;
	pf->TEXT();
	pf->ALIGN();
	pf->GLOBLfunc("_main");
	pf->LABEL("_main");
	pf->START();
	tree()->accept(*this);
	pf->INT(0);
	pf->POP();
	pf->LEAVE();
	pf->RET();
	// import library functions
	pf->EXTRN("_readi");
	pf->EXTRN("_printi");
	pf->EXTRN("_prints");
	pf->EXTRN("_println");
	pf->flush();
	return true;
}
void Compact::node(Node& n) {}
void Compact::nodeInteger(NodeInteger& n) { pf->INT(n.integer()); }
void Compact::nodeReal(NodeReal& n) {} // No reals
void Compact::nodeString(NodeString& n) {
	String lbl = pf->label();
	pf->RODATA();			// strings are DATA readonly
	pf->ALIGN();			// make sure we are aligned
	pf->LABEL(lbl);			// give the string a name
	pf->STR(n.text());		// output string characters
	// make the call
	pf->TEXT();			// return to the TEXT segment
	pf->ADDR(lbl);			// the string to be printed
	pf->CALL("_prints");		// call the print rotine
	pf->CALL("_println");		// print a newline
	pf->TRASH(4);			// remove the string argument
}
void Compact::nodeData(NodeData& n) { } // No opaque data literals
void Compact::nodeUnary(NodeUnary& n) { } // Use uminus operator
void Compact::nodeBinary(NodeBinary& n) { } // Use specific operators
void Compact::nodeTernary(NodeTernary& n) { } // Use specific operators
void Compact::nodeList(NodeList& n) {
  for (int i = 0; i < n.size(); i++)
    n.elementAt(i).accept(*this);
}
void Compact::nodeVariable(NodeVariable& n) {
	if (vars.containsKey(n.text())) {
		pf->ADDR(n.text());
		pf->LOAD();
	}
}
void Compact::nodeWhile(NodeWhile& n) {
	String lbl1 = pf->label(), lbl2 = pf->label();
	pf->LABEL(lbl1);
	n.first().accept(*this);
	pf->JZ(lbl2);
	n.second().accept(*this);
	pf->JMP(lbl1);
	pf->LABEL(lbl2);
}
void Compact::nodeIf(NodeIf& n) {
	String lbl1 = pf->label();
	n.first().accept(*this);
	pf->JZ(lbl1);
	n.second().accept(*this);
	pf->LABEL(lbl1);
}
void Compact::nodeIfelse(NodeIfelse& n) {
	String lbl1 = pf->label(), lbl2 = pf->label();
	n.first().accept(*this);
	pf->JZ(lbl1);
	n.second().accept(*this);
	pf->JMP(lbl2);
	pf->LABEL(lbl1);
	n.third().accept(*this);
	pf->LABEL(lbl2);
}
void Compact::nodeRead(NodeRead& n) {
	String name = ((NodeString*)&n.first())->text();
	if (vars.containsKey(name)) {
		pf->CALL("_readi");
		pf->PUSH();
		pf->ADDR(name);
		pf->STORE();
	}
	else
		std::cerr << name + ": variable not found";
}
void Compact::nodePrint(NodePrint& n) {
	n.first().accept(*this);
	pf->CALL("_printi");		// call the print function
	pf->CALL("_println");		// print a newline
	pf->TRASH(4);			// delete the printed value
}
void Compact::nodeAssign(NodeAssign& n) {
	String name = ((NodeString*)&n.first())->text();
	if (!vars.containsKey(name)) {	// variable not found ?
		pf->DATA();		// variables are DATA
		pf->ALIGN();		// make sure we are aligned
		pf->LABEL(name);		// name variable location
		pf->CONST(0);		// initialize it to 0 (zero)
		pf->TEXT();		// return to the TEXT segment
		vars.put(name, new SymbolNode());	// create the var
	}
	n.second().accept(*this);	// determine the new value
	pf->ADDR(name);			// where to store the value
	pf->STORE();			// store the value at address
}
void Compact::nodeUminus(NodeUminus& n) {
	n.first().accept(*this);		// determine the new value
	pf->NEG();			// make the 2-compliment
}
void Compact::nodeAdd(NodeAdd& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->ADD();
}
void Compact::nodeSub(NodeSub& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->SUB();
}
void Compact::nodeMul(NodeMul& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->MUL();
}
void Compact::nodeDiv(NodeDiv& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->DIV();
}
void Compact::nodeMod(NodeMod& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->MOD();
}
void Compact::nodeEq(NodeEq& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->EQ();
}
void Compact::nodeNe(NodeNe& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->NE();
}
void Compact::nodeGe(NodeGe& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->GE();
}
void Compact::nodeGt(NodeGt& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->GT();
}
void Compact::nodeLe(NodeLe& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->LE();
}
void Compact::nodeLt(NodeLt& n) {
	n.first().accept(*this);
	n.second().accept(*this);
	pf->LT();
}
