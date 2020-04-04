#ifndef _PARSER_HH_
#define _PARSER_HH_

#include <iostream>
#include <fstream>
#include <string>
#define String std::string
#include <FlexLexer.h>
#include "Node.hpp"
class Parser; // Forward declaration

extern int yyparse();
extern Parser *parser;

class Parser {
	yyFlexLexer lexer;
	int nerrs;
	Node *tree;
	String filename;
public:
	inline Parser(std::istream *in = 0) {
		lexer.switch_streams(in, 0);
		filename = String("");
	}
	inline Parser(String infile) {
		filename = infile;
		lexer.switch_streams(new std::ifstream(filename.c_str()), 0);
	}
	inline int errors() { return nerrs; }
	inline int parse() { 
		nerrs = 0;
		parser = this;
		return yyparse();
	}
	inline Node *syntax() { return tree; }

	inline void error(const char *msg, int line, const char *text = 0) {
		nerrs++;
		if (filename == "")
			std::cerr << msg << ": line " << line;
		else
			std::cerr << filename << ": line " << line << ": " << msg;
		if (text != 0) std::cerr << " at or before " << text;
		std::cerr << "\n";
	}
	// The following routines are only to called by the lexer
	inline void syntax(Node *t) { tree = t; }
	inline int lex() { return lexer.yylex(); }
	inline void error(const char *msg) {
		error(msg, lexer.lineno(), lexer.YYText()); }
};

#endif /* _PARSER_HH_ */
