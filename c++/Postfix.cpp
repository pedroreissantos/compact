#include "Postfix.hpp"
#include <sstream>

String Postfix::label() {
  	std::stringstream str;
  	lbl++;
	str << "_L" << lbl; 
	return str.str();
}

void Postfix::text(String str) {
	int len = str.size();
	for (int i = 0; i < len; i++)
		CHAR(str[i]);
	CHAR(0);
}
