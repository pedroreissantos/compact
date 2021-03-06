#include <fstream>
#include <iostream>
#include "Postfix.hpp"

class PFi386 : public Postfix {
	std::ostream *out;
public:
	inline PFi386() { out = &std::cout; } // write stdout
	inline PFi386(String outfile) { // write to named file
		out = new std::ofstream(outfile.c_str());
	}
	inline ~PFi386() { flush(); }
	void flush() { out->flush(); }

	void NOP();
	void INT(int val);
	void ADD();
	void SUB();
	void MUL();
	void DIV();
	void MOD();
	void NEG();
	void INCR(int val);
	void DECR(int val);
	void GT();
	void GE();
	void LT();
	void LE();
	void EQ();
	void NE();
	void AND();
	void OR();
	void XOR();
	void NOT();
	void ROTL();
	void ROTR();
	void SHTL();
	void SHTRU();
	void SHTRS();
	void LOCAL(int val);
	void ADDR(String sym);
	void LOCV(int val);
	void ADDRV(String sym);
	void LOCA(int val);
	void ADDRA(String sym);
	void LOAD();
	void STORE();
	void LDCHR();
	void STCHR();
	void LD16();
	void ST16();
	void ENTER(int val);
	void START();
	void ALLOC();
	void LEAVE();
	void TRASH(int val);
	void CALL(String sym);
	void RET();
	void RETN(int val);
	void BRANCH();
	void LEAP();
	void JMP(String sym);
	void JZ(String sym);
	void JNZ(String sym);
	void DUP();
	void SWAP();
	void SP();
	void PUSH();
	void POP();
	void I2D();
	void F2D();
	void D2I();
	void D2F();
	void DADD();
	void DSUB();
	void DMUL();
	void DDIV();
	void DCMP();
	void DNEG();
	void LOAD2();
	void STORE2();
	void DUP2();
	void DPUSH();
	void DPOP();
	void NIL();
	void TEXT();
	void RODATA();
	void DATA();
	void BSS();
	void ALIGN();
	void EXTRN(String sym);
	void COMMON(int val);
	void GLOBL(String sym);
	void GLOBLfunc(String sym);
	void GLOBLdata(String sym);
	void LABEL(String sym);
	void CONST(int val);
	void STR(String val);
	void CHAR(char val);
	void ID(String sym);
	void BYTE(int val);
	void FLOAT(float val);
	void DOUBLE(double val);
	void ULDCHR();
	void ULD16();
	void UDIV();
	void UMOD();
	void UGT();
	void UGE();
	void ULT();
	void ULE();
	void JEQ(String sym);
	void JNE(String sym);
	void JGT(String sym);
	void JGE(String sym);
	void JLT(String sym);
	void JLE(String sym);
	void JUGT(String sym);
	void JUGE(String sym);
	void JULT(String sym);
	void JULE(String sym);
};
