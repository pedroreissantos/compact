#ifndef _POSTFIX_HH_
#define _POSTFIX_HH_

#include <string>
#define String std::string

class Postfix {
	int lbl;
public:
	inline Postfix() { lbl = 0; }
	String label();
	void text(String str);
	virtual void flush() {}

	virtual void NOP() = 0;
	virtual void INT(int val) = 0;
	virtual void ADD() = 0;
	virtual void SUB() = 0;
	virtual void MUL() = 0;
	virtual void DIV() = 0;
	virtual void MOD() = 0;
	virtual void NEG() = 0;
	virtual void INCR(int val) = 0;
	virtual void DECR(int val) = 0;
	virtual void GT() = 0;
	virtual void GE() = 0;
	virtual void LT() = 0;
	virtual void LE() = 0;
	virtual void EQ() = 0;
	virtual void NE() = 0;
	virtual void AND() = 0;
	virtual void OR() = 0;
	virtual void XOR() = 0;
	virtual void NOT() = 0;
	virtual void ROTL() = 0;
	virtual void ROTR() = 0;
	virtual void SHTL() = 0;
	virtual void SHTRU() = 0;
	virtual void SHTRS() = 0;
	virtual void LOCAL(int val) = 0;
	virtual void ADDR(String sym) = 0;
	virtual void LOCV(int val) = 0;
	virtual void ADDRV(String sym) = 0;
	virtual void LOCA(int val) = 0;
	virtual void ADDRA(String sym) = 0;
	virtual void LOAD() = 0;
	virtual void STORE() = 0;
	virtual void LDCHR() = 0;
	virtual void STCHR() = 0;
	virtual void LD16() = 0;
	virtual void ST16() = 0;
	virtual void ENTER(int val) = 0;
	virtual void START() = 0;
	virtual void ALLOC() = 0;
	virtual void LEAVE() = 0;
	virtual void TRASH(int val) = 0;
	virtual void CALL(String sym) = 0;
	virtual void RET() = 0;
	virtual void RETN(int val) = 0;
	virtual void BRANCH() = 0;
	virtual void LEAP() = 0;
	virtual void JMP(String sym) = 0;
	virtual void JZ(String sym) = 0;
	virtual void JNZ(String sym) = 0;
	virtual void DUP() = 0;
	virtual void SWAP() = 0;
	virtual void SP() = 0;
	virtual void PUSH() = 0;
	virtual void POP() = 0;
	virtual void I2D() = 0;
	virtual void F2D() = 0;
	virtual void D2I() = 0;
	virtual void D2F() = 0;
	virtual void DADD() = 0;
	virtual void DSUB() = 0;
	virtual void DMUL() = 0;
	virtual void DDIV() = 0;
	virtual void DCMP() = 0;
	virtual void DNEG() = 0;
	virtual void LOAD2() = 0;
	virtual void STORE2() = 0;
	virtual void DUP2() = 0;
	virtual void DPUSH() = 0;
	virtual void DPOP() = 0;
	virtual void NIL() = 0;
	virtual void TEXT() = 0;
	virtual void RODATA() = 0;
	virtual void DATA() = 0;
	virtual void BSS() = 0;
	virtual void ALIGN() = 0;
	virtual void EXTRN(String sym) = 0;
	virtual void COMMON(int val) = 0;
	virtual void GLOBL(String sym) = 0;
	virtual void GLOBLfunc(String sym) = 0;
	virtual void GLOBLdata(String sym) = 0;
	virtual void LABEL(String sym) = 0;
	virtual void CONST(int val) = 0;
	virtual void STR(String val) = 0;
	virtual void CHAR(char val) = 0;
	virtual void ID(String sym) = 0;
	virtual void BYTE(int val) = 0;
	virtual void FLOAT(float val) = 0;
	virtual void DOUBLE(double val) = 0;
	virtual void ULDCHR() = 0;
	virtual void ULD16() = 0;
	virtual void UDIV() = 0;
	virtual void UMOD() = 0;
	virtual void UGT() = 0;
	virtual void UGE() = 0;
	virtual void ULT() = 0;
	virtual void ULE() = 0;
	virtual void JEQ(String sym) = 0;
	virtual void JNE(String sym) = 0;
	virtual void JGT(String sym) = 0;
	virtual void JGE(String sym) = 0;
	virtual void JLT(String sym) = 0;
	virtual void JLE(String sym) = 0;
	virtual void JUGT(String sym) = 0;
	virtual void JUGE(String sym) = 0;
	virtual void JULT(String sym) = 0;
	virtual void JULE(String sym) = 0;
};
#endif /* _POSTFIX_HH_ */
