#ifndef _COMPACT_HH_
#define _COMPACT_HH_

#include <string>
#define String std::string
#include "Compiler.hpp"
#include "Parser.hpp"
#include "PFi386.hpp"
#include "Tabid.hpp"

class Compact : public Compiler {
	Parser *parser;
	Postfix *pf;
	Tabid vars;
public:
	Compact() {
		parser = new Parser();
		pf = new PFi386("out.asm");
	}
	Compact(String in, bool debug = false) {
		parser = new Parser(new std::ifstream(in.c_str()));
		pf = new PFi386(extension(in, ".asm"));
	}
	~Compact() {
	  	delete parser;
		delete pf;
	}
	bool parse();
	bool generate();
	void node(Node& n);
	void nodeInteger(NodeInteger& n);
	void nodeReal(NodeReal& n);
	void nodeString(NodeString& n);
	void nodeData(NodeData& n);
	void nodeUnary(NodeUnary& n);
	void nodeBinary(NodeBinary& n);
	void nodeTernary(NodeTernary& n);
	void nodeList(NodeList& n);

	void nodePrint(NodePrint& n);
	void nodeRead(NodeRead& n);
	void nodeVariable(NodeVariable& n);
	void nodeAssign(NodeAssign& n);
	void nodeIf(NodeIf& n);
	void nodeIfelse(NodeIfelse& n);
	void nodeWhile(NodeWhile& n);
	void nodeAdd(NodeAdd& n);
	void nodeSub(NodeSub& n);
	void nodeMul(NodeMul& n);
	void nodeDiv(NodeDiv& n);
	void nodeMod(NodeMod& n);
	void nodeEq(NodeEq& n);
	void nodeNe(NodeNe& n);
	void nodeGt(NodeGt& n);
	void nodeGe(NodeGe& n);
	void nodeLt(NodeLt& n);
	void nodeLe(NodeLe& n);
	void nodeUminus(NodeUminus& n);
};
#endif /* _COMPACT_HH_ */
