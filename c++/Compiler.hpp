#ifndef _COMPILER_HH_
#define _COMPILER_HH_

#include <string>
#define String std::string

// forward declarations
class Node;
class NodeInteger;
class NodeReal;
class NodeString;
class NodeData;
class NodeUnary;
class NodeBinary;
class NodeTernary;
class NodeList;
class NodePrint;
class NodeRead;
class NodeVariable;
class NodeAssign;
class NodeIf;
class NodeIfelse;
class NodeWhile;
class NodeAdd;
class NodeSub;
class NodeMul;
class NodeDiv;
class NodeMod;
class NodeEq;
class NodeNe;
class NodeGt;
class NodeGe;
class NodeLt;
class NodeLe;
class NodeUminus;

class Compiler {
	Node *syntax;
protected:
	inline void tree(Node *t) { syntax = t; }
public:
	static inline String
		extension(String filename, String ext) {
		int pos = filename.find_last_of('.');
		if (pos == String::npos) return filename + "." + ext;
		else return filename.substr(0, pos) + ext;
	}
	inline Node *tree() { return syntax; }

	virtual bool parse() = 0;
	virtual bool generate() = 0;

	// Visitor functions called by the tree nodes
	virtual void node(Node& n) = 0;
	virtual void nodeInteger(NodeInteger& n) = 0;
	virtual void nodeReal(NodeReal& n) = 0;
	virtual void nodeString(NodeString& n) = 0;
	virtual void nodeData(NodeData& n) = 0;
	virtual void nodeUnary(NodeUnary& n) = 0;
	virtual void nodeBinary(NodeBinary& n) = 0;
	virtual void nodeTernary(NodeTernary& n) = 0;
	virtual void nodeList(NodeList& n) = 0;

	virtual void nodePrint(NodePrint& n) = 0;
	virtual void nodeRead(NodeRead& n) = 0;
	virtual void nodeVariable(NodeVariable& n) = 0;
	virtual void nodeAssign(NodeAssign& n) = 0;
	virtual void nodeIf(NodeIf& n) = 0;
	virtual void nodeIfelse(NodeIfelse& n) = 0;
	virtual void nodeWhile(NodeWhile& n) = 0;
	virtual void nodeAdd(NodeAdd& n) = 0;
	virtual void nodeSub(NodeSub& n) = 0;
	virtual void nodeMul(NodeMul& n) = 0;
	virtual void nodeDiv(NodeDiv& n) = 0;
	virtual void nodeMod(NodeMod& n) = 0;
	virtual void nodeEq(NodeEq& n) = 0;
	virtual void nodeNe(NodeNe& n) = 0;
	virtual void nodeGt(NodeGt& n) = 0;
	virtual void nodeGe(NodeGe& n) = 0;
	virtual void nodeLt(NodeLt& n) = 0;
	virtual void nodeLe(NodeLe& n) = 0;
	virtual void nodeUminus(NodeUminus& n) = 0;
};
#endif /* _COMPILER_HH_ */
