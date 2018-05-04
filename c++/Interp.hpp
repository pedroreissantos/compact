#include <stack>

#include "Compiler.hpp"
#include "Parser.hpp"
#include "Tabid.hpp"

class Interp : public Compiler {
	Parser *parser;
	std::stack<int> stk;
	Tabid vars;
protected:
	inline int pop() { int val = stk.top(); stk.pop(); return val; }
public:
	inline Interp(std::istream *in) { parser = new Parser(); }
	inline Interp(String infile) {
		parser = new Parser(new std::ifstream(infile.c_str()));
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
