#include "PFdebug.hpp"

void PFdebug::NOP() { *out << "NOP\n"; }
void PFdebug::INT(int val) { *out << "INT " << val << "\n"; }
void PFdebug::ADD() { *out << "ADD\n"; }
void PFdebug::SUB() { *out << "SUB\n"; }
void PFdebug::MUL() { *out << "MUL\n"; }
void PFdebug::DIV() { *out << "DIV\n"; }
void PFdebug::MOD() { *out << "MOD\n"; }
void PFdebug::NEG() { *out << "NEG\n"; }
void PFdebug::INCR(int val) { *out << "INCR " << val << "\n"; }
void PFdebug::DECR(int val) { *out << "DECR " << val << "\n"; }
void PFdebug::GT() { *out << "GT\n"; }
void PFdebug::GE() { *out << "GE\n"; }
void PFdebug::LT() { *out << "LT\n"; }
void PFdebug::LE() { *out << "LE\n"; }
void PFdebug::EQ() { *out << "EQ\n"; }
void PFdebug::NE() { *out << "NE\n"; }
void PFdebug::AND() { *out << "AND\n"; }
void PFdebug::OR() { *out << "OR\n"; }
void PFdebug::XOR() { *out << "XOR\n"; }
void PFdebug::NOT() { *out << "NOT\n"; }
void PFdebug::ROTL() { *out << "ROTL\n"; }
void PFdebug::ROTR() { *out << "ROTR\n"; }
void PFdebug::SHTL() { *out << "SHTL\n"; }
void PFdebug::SHTRU() { *out << "SHTRU\n"; }
void PFdebug::SHTRS() { *out << "SHTRS\n"; }
void PFdebug::LOCAL(int val) { *out << "LOCAL " << val << "\n"; }
void PFdebug::ADDR(String sym) { *out << "ADDR " << sym << "\n"; }
void PFdebug::LOCV(int val) { *out << "LOCV " << val << "\n"; }
void PFdebug::ADDRV(String sym) { *out << "ADDRV " << sym << "\n"; }
void PFdebug::LOCA(int val) { *out << "LOCA " << val << "\n"; }
void PFdebug::ADDRA(String sym) { *out << "ADDRA " << sym << "\n"; }
void PFdebug::LOAD() { *out << "LOAD\n"; }
void PFdebug::STORE() { *out << "STORE\n"; }
void PFdebug::LDCHR() { *out << "LDCHR\n"; }
void PFdebug::STCHR() { *out << "STCHR\n"; }
void PFdebug::LD16() { *out << "LD16\n"; }
void PFdebug::ST16() { *out << "ST16\n"; }
void PFdebug::ENTER(int val) { *out << "ENTER " << val << "\n"; }
void PFdebug::START() { *out << "START\n"; }
void PFdebug::ALLOC() { *out << "ALLOC\n"; }
void PFdebug::LEAVE() { *out << "LEAVE\n"; }
void PFdebug::TRASH(int val) { *out << "TRASH " << val << "\n"; }
void PFdebug::CALL(String sym) { *out << "CALL " << sym << "\n"; }
void PFdebug::RET() { *out << "RET\n"; }
void PFdebug::RETN(int val) { *out << "RETN " << val << "\n"; }
void PFdebug::BRANCH() { *out << "BRANCH\n"; }
void PFdebug::LEAP() { *out << "LEAP\n"; }
void PFdebug::JMP(String sym) { *out << "JMP " << sym << "\n"; }
void PFdebug::JZ(String sym) { *out << "JZ " << sym << "\n"; }
void PFdebug::JNZ(String sym) { *out << "JNZ " << sym << "\n"; }
void PFdebug::DUP() { *out << "DUP\n"; }
void PFdebug::SWAP() { *out << "SWAP\n"; }
void PFdebug::SP() { *out << "SP\n"; }
void PFdebug::PUSH() { *out << "PUSH\n"; }
void PFdebug::POP() { *out << "POP\n"; }
void PFdebug::I2D() { *out << "I2D\n"; }
void PFdebug::F2D() { *out << "F2D\n"; }
void PFdebug::D2I() { *out << "D2I\n"; }
void PFdebug::D2F() { *out << "D2F\n"; }
void PFdebug::DADD() { *out << "DADD\n"; }
void PFdebug::DSUB() { *out << "DSUB\n"; }
void PFdebug::DMUL() { *out << "DMUL\n"; }
void PFdebug::DDIV() { *out << "DDIV\n"; }
void PFdebug::DCMP() { *out << "DCMP\n"; }
void PFdebug::DNEG() { *out << "DNEG\n"; }
void PFdebug::LOAD2() { *out << "LOAD2\n"; }
void PFdebug::STORE2() { *out << "STORE2\n"; }
void PFdebug::DUP2() { *out << "DUP2\n"; }
void PFdebug::DPUSH() { *out << "DPUSH\n"; }
void PFdebug::DPOP() { *out << "DPOP\n"; }
void PFdebug::NIL() { *out << "NIL\n"; }
void PFdebug::TEXT() { *out << "TEXT\n"; }
void PFdebug::RODATA() { *out << "RODATA\n"; }
void PFdebug::DATA() { *out << "DATA\n"; }
void PFdebug::BSS() { *out << "BSS\n"; }
void PFdebug::ALIGN() { *out << "ALIGN\n"; }
void PFdebug::EXTRN(String sym) { *out << "EXTRN " << sym << "\n"; }
void PFdebug::COMMON(int val) { *out << "COMMON " << val << "\n"; }
void PFdebug::GLOBL(String sym) { *out << "GLOBL " << sym << "\n"; }
void PFdebug::GLOBLfunc(String sym) { *out << "GLOBL " << sym << " :function\n"; }
void PFdebug::GLOBLdata(String sym) { *out << "GLOBL " << sym << " :object\n"; }
void PFdebug::LABEL(String sym) { *out << "LABEL " << sym << "\n"; }
void PFdebug::CONST(int val) { *out << "CONST " << val << "\n"; }
void PFdebug::STR(String val) { *out << "STR \"" << val << "\"\n"; }
void PFdebug::CHAR(char val) { *out << "CHAR " << ((int)val) << "\n"; }
void PFdebug::ID(String sym) { *out << "ID " << sym << "\n"; }
void PFdebug::BYTE(int val) { *out << "BYTE " << val << "\n"; }
void PFdebug::FLOAT(float val) { *out << "FLOAT " << val << "\n"; }
void PFdebug::DOUBLE(double val) { *out << "DOUBLE " << val << "\n"; }
void PFdebug::ULDCHR() { *out << "ULDCHR\n"; }
void PFdebug::ULD16() { *out << "ULD16\n"; }
void PFdebug::UDIV() { *out << "UDIV\n"; }
void PFdebug::UMOD() { *out << "UMOD\n"; }
void PFdebug::UGT() { *out << "UGT\n"; }
void PFdebug::UGE() { *out << "UGE\n"; }
void PFdebug::ULT() { *out << "ULT\n"; }
void PFdebug::ULE() { *out << "ULE\n"; }
void PFdebug::JEQ(String sym) { *out << "JEQ " << sym << "\n"; }
void PFdebug::JNE(String sym) { *out << "JNE " << sym << "\n"; }
void PFdebug::JGT(String sym) { *out << "JGT " << sym << "\n"; }
void PFdebug::JGE(String sym) { *out << "JGE " << sym << "\n"; }
void PFdebug::JLT(String sym) { *out << "JLT " << sym << "\n"; }
void PFdebug::JLE(String sym) { *out << "JLE " << sym << "\n"; }
void PFdebug::JUGT(String sym) { *out << "JUGT " << sym << "\n"; }
void PFdebug::JUGE(String sym) { *out << "JUGE " << sym << "\n"; }
void PFdebug::JULT(String sym) { *out << "JULT " << sym << "\n"; }
void PFdebug::JULE(String sym) { *out << "JULE " << sym << "\n"; }
