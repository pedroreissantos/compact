%option c++ outfile="yyFlexLexer.cpp"
%option stack noyywrap nounput noyymore
%option yylineno 8bit
%{
#include <string>
#define String std::string
#include "NodeVariable.hpp"
#include "NodeString.hpp"
#include "NodeInteger.hpp"
#include "y.tab.h"
using namespace std;
void yyerror(const char *);
%}
%% 
"#".*			{ ; /* comment */ }
">="			{ return GE; }
"<="			{ return LE; }
"=="			{ return EQ; }
"!="			{ return NE; }
"while"			{ return WHILE; }
"if"			{ return IF; }
"else"			{ return ELSE; }
"print"			{ return PRINT; }
"read"			{ return READ; }
"program"		{ return PROGRAM; }
"end"			{ return END; }

[A-Za-z][A-Za-z0-9_]*	{ yylval.n = new NodeVariable(String(yytext));
			  return VARIABLE; } 
\'[^']*\'		{ yytext[yyleng-1] = 0;
			  yylval.n = new NodeString(String(yytext+1));
			  return STRING; }
[0-9]+			{ yylval.n = new NodeInteger(atoi(yytext));
			  return INTEGER; } 
[-()<>=+*/%;{}.]	{ return yytext[0]; }
 
[ \t\n]+		{ ; /* ignore whitespace */ }
 
.			{ yyerror("Unknown character"); }
