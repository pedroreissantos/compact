#include <cstring>
#include <string>
#define String std::string
#include "Compiler.hpp"
#include "Compact.hpp"
#include "Interp.hpp"

int main(int argc, char *argv[]) {
  Compiler *compiler;

  if (argc > 1 && strcmp(argv[1], "-I") == 0)
	if (argc > 2)
  		compiler = new Interp(String(argv[2]));
	else
		compiler = new Interp(&std::cin);
  else
	if (argc > 1)
  		compiler = new Compact(String(argv[1]));
	else
  		compiler = new Compact();

  if (compiler->parse() == false) return 2;
  int ret = compiler->generate() == false;

  delete compiler;
  return ret;
}
