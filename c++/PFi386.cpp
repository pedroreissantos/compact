#include "PFi386.hpp"
#include <iomanip>
using namespace std;

void PFi386::NOP() {
	*out << "; NOP\n\tnop\n"; }
void PFi386::INT(int val) {
	*out << "; INT\n\tpush\tdword " << val << "\n"; }
void PFi386::ADD() {
	*out << "; ADD\n\tpop\teax\n\tadd\tdword [esp], eax\n"; }
void PFi386::SUB() {
	*out << "; SUB\n\tpop\teax\n\tsub\tdword [esp], eax\n"; }
void PFi386::MUL() {
	*out << "; MUL\n\tpop\teax\n\timul\tdword eax, [esp]\n\tmov\t[esp], eax\n"; }
void PFi386::DIV() {
	*out << "; DIV\n\tpop\tecx\n\tpop\teax\n\tcdq\n\tidiv\tecx\n\tpush\teax\n"; }
void PFi386::MOD() {
	*out << "; MOD\n\tpop\tecx\n\tpop\teax\n\tcdq\n\tidiv\tecx\n\tpush\tedx\n"; }
void PFi386::NEG() {
	*out << "; NEG\n\tneg\tdword [esp]\n"; }
void PFi386::INCR(int val) {
	*out << "; INCR\n\tpop\teax\n\tadd\tdword [eax], " << val << "\n"; }
void PFi386::DECR(int val) {
	*out << "; DECR\n\tpop\teax\n\tsub\tdword [eax], " << val << "\n"; }
void PFi386::GT() {
	*out << "; GT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetg\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::GE() {
	*out << "; GE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetge\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::LT() {
	*out << "; LT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetl\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::LE() {
	*out << "; LE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetle\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::EQ() {
	*out << "; EQ\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsete\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::NE() {
	*out << "; NE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetne\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::AND() {
	*out << "; AND\n\tpop\teax\n\tand\tdword [esp], eax\n"; }
void PFi386::OR() {
	*out << "; OR\n\tpop\teax\n\tor\tdword [esp], eax\n"; }
void PFi386::XOR() {
	*out << "; XOR\n\tpop\teax\n\txor\tdword [esp], eax\n"; }
void PFi386::NOT() {
	*out << "; NOT\n\tnot\tdword [esp]\n"; }
void PFi386::ROTL() {
	*out << "; ROTL\n\tpop\tecx\n\trol\tdword [esp], cl\n"; }
void PFi386::ROTR() {
	*out << "; ROTR\n\tpop\tecx\n\tror\tdword [esp], cl\n"; }
void PFi386::SHTL() {
	*out << "; SHTL\n\tpop\tecx\n\tsal\tdword [esp], cl\n"; }
void PFi386::SHTRU() {
	*out << "; SHTRU\n\tpop\tecx\n\tshr\tdword [esp], cl\n"; }
void PFi386::SHTRS() {
	*out << "; SHTRS\n\tpop\tecx\n\tsar\tdword [esp], cl\n"; }
void PFi386::LOCAL(int val) {
	*out << "; LOCAL\n\tlea\teax, [ebp+" << val << "]\n\tpush\teax\n"; }
void PFi386::ADDR(String sym) {
	*out << "; ADDR\n\tpush\tdword $" << sym << "\n"; }
void PFi386::LOCV(int val) {
	*out << "; LOCV\n\tpush\tdword [ebp+" << val << "]\n"; }
void PFi386::ADDRV(String sym) {
	*out << "; ADDRV\n\tpush\tdword [$" << sym << "]\n"; }
void PFi386::LOCA(int val) {
	*out << "; LOCA\n\tpop\teax\n\tmov\t[ebp+" << val << "], eax\n"; }
void PFi386::ADDRA(String sym) {
	*out << "; ADDRA\n\tpop\teax\n\tmov\t[$" << sym << "], eax\n"; }
void PFi386::LOAD() {
	*out << "; LOAD\n\tpop\teax\n\tpush\tdword [eax]\n"; }
void PFi386::STORE() {
	*out << "; STORE\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], eax\n"; }
void PFi386::LDCHR() {
	*out << "; LDCHR\n\tpop\tecx\n\tmovsx\teax,byte [ecx]\n\tpush\teax\n"; }
void PFi386::STCHR() {
	*out << "; STCHR\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], al\n"; }
void PFi386::LD16() {
	*out << "; LD16\n\tpop\tecx\n\tmovsx\teax,word [ecx]\n\tpush\teax\n"; }
void PFi386::ST16() {
	*out << "; ST16\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], ax\n"; }
void PFi386::ENTER(int val) {
	*out << "; ENTER\n\tpush\tebp\n\tmov\tebp, esp\n\tsub\tesp, " << val << "\n"; }
void PFi386::START() {
	*out << "; START\n\tpush\tebp\n\tmov\tebp, esp\n"; }
void PFi386::ALLOC() {
	*out << "; ALLOC\n\tpop\teax\n\tsub\tesp, eax\n"; }
void PFi386::LEAVE() {
	*out << "; LEAVE\n\tleave\n"; }
void PFi386::TRASH(int val) {
	*out << "; TRASH\n\tadd\tesp, " << val << "\n"; }
void PFi386::CALL(String sym) {
	*out << "; CALL\n\tcall\t$" << sym << "\n"; }
void PFi386::RET() {
	*out << "; RET\n\tret\n"; }
void PFi386::RETN(int val) {
	*out << "; RETN\n\tret\t" << val << "\n"; }
void PFi386::BRANCH() {
	*out << "; BRANCH\n\tpop\teax\n\tcall\teax\n"; }
void PFi386::LEAP() {
	*out << "; LEAP\n\tpop\teax\n\tjmp\teax\n"; }
void PFi386::JMP(String sym) {
	*out << "; JMP\n\tjmp\tdword $" << sym << "\n"; }
void PFi386::JZ(String sym) {
	*out << "; JZ\n\tpop\teax\n\tcmp\teax, byte 0\n\tje\tnear $" << sym << "\n"; }
void PFi386::JNZ(String sym) {
	*out << "; JNZ\n\tpop\teax\n\tcmp\teax, byte 0\n\tjne\tnear $" << sym << "\n"; }
void PFi386::DUP() {
	*out << "; DUP\n\tpush\tdword [esp]\n"; }
void PFi386::SWAP() {
	*out << "; SWAP\n\tpop\teax\n\tpop\tecx\n\tpush\teax\n\tmov\teax, ecx\n\tpush\teax\n"; }
void PFi386::SP() {
	*out << "; SP\n\tpush\tesp\n"; }
void PFi386::PUSH() {
	*out << "; PUSH\n\tpush\teax\n"; }
void PFi386::POP() {
	*out << "; POP\n\tpop\teax\n"; }
void PFi386::I2D() {
	*out << "; I2D\n\tfild\tdword [esp]\n\tsub\tesp, byte 4\n\tfstp\tqword [esp]\n"; }
void PFi386::F2D() {
	*out << "; F2D\n\tfld\tdword [esp]\n\tsub\tesp, byte 4\n\tfstp\tqword [esp]\n"; }
void PFi386::D2I() {
	*out << "; D2I\n\tfld\tqword [esp]\n\tadd\tesp, byte 4\n\tfistp\tdword [esp]\n"; }
void PFi386::D2F() {
	*out << "; D2F\n\tfld\tqword [esp]\n\tadd\tesp, byte 4\n\tfstp\tdword [esp]\n"; }
void PFi386::DADD() {
	*out << "; DADD\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfaddp\tst1\n\tfstp\tqword [esp]\n"; }
void PFi386::DSUB() {
	*out << "; DSUB\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfsubrp\tst1\n\tfstp\tqword [esp]\n"; }
void PFi386::DMUL() {
	*out << "; DMUL\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfmulp\tst1\n\tfstp\tqword [esp]\n"; }
void PFi386::DDIV() {
	*out << "; DDIV\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfdivrp\tst1\n\tfstp\tqword [esp]\n"; }
void PFi386::DCMP() {
	*out << "; DCMP\n\tfld\tqword [esp+8]\n\tfld\tqword [esp]\n\tadd\tesp, byte 12\n\tfsubp\tst1\n\tfxtract\n\tffree\tst1\n\tfistp\tdword [esp]\n"; }
void PFi386::DNEG() {
	*out << "; DNEG\n\tfld\tqword [esp]\n\tfchs\n\tfstp\tqword [esp]\n"; }
void PFi386::LOAD2() {
	*out << "; LOAD2\n\tpop\teax\n\tpush\tdword [eax+4]\n\tpush\tdword [eax]\n"; }
void PFi386::STORE2() {
	*out << "; STORE2\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], eax\n\tpop\teax\n\tmov\t[ecx+4], eax\n"; }
void PFi386::DUP2() {
	*out << "; DUP2\n\tpush\tdword [esp+4]\n\tpush\tdword [esp+4]\n"; }
void PFi386::DPUSH() {
	*out << "; DPUSH\n\tsub\tesp, byte 8\n\tfstp\tqword [esp]\n"; }
void PFi386::DPOP() {
	*out << "; DPOP\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n"; }
void PFi386::NIL() {
	*out << "; NIL\n"; }
void PFi386::TEXT() {
	*out << "; TEXT\nsegment\t.text\n"; }
void PFi386::RODATA() {
	*out << "; RODATA\nsegment\t.rodata\n"; }
void PFi386::DATA() {
	*out << "; DATA\nsegment\t.data\n"; }
void PFi386::BSS() {
	*out << "; BSS\nsegment\t.bss\n"; }
void PFi386::ALIGN() {
	*out << "; ALIGN\nalign\t4\n"; }
void PFi386::EXTRN(String sym) {
	*out << "; EXTRN\nextern\t$" << sym << "\n"; }
void PFi386::COMMON(int val) {
	*out << "; COMMON\ncommon\t" << val << "\n"; }
void PFi386::GLOBL(String sym) {
	*out << "; GLOBL\nglobal\t$" << sym << "\n"; }
#ifdef _WIN32
void PFi386::GLOBLfunc(String sym) {
	*out << "; GLOBL\nglobal\t$" << sym << "\n"; }
void PFi386::GLOBLdata(String sym) {
	*out << "; GLOBL\nglobal\t$" << sym << "\n"; }
#else
void PFi386::GLOBLfunc(String sym) {
	*out << "; GLOBL\nglobal\t$" << sym << ":function\n"; }
void PFi386::GLOBLdata(String sym) {
	*out << "; GLOBL\nglobal\t$" << sym << ":object\n"; }
#endif
void PFi386::LABEL(String sym) {
	*out << "; LABEL\n$" << sym << ":\n"; }
void PFi386::CONST(int val) {
	*out << "; CONST\n\tdd\t" << val << "\n"; }
void PFi386::STR(String val) {
	*out << "; STR\n\tdb\t'" << val << "', 0\n"; }
void PFi386::CHAR(char val) {
	*out << "; CHAR\n\tdb\t" << ((int)val) << "\n"; }
void PFi386::ID(String sym) {
	*out << "; ID\n\tdd\t$" << sym << "\n"; }
void PFi386::BYTE(int val) {
	*out << "; BYTE\n\tresb\t" << val << "\n"; }
void PFi386::FLOAT(float val) {
	*out << "; FLOAT\n\tdd\t" << setiosflags(ios::fixed) << setprecision(1) << val << "\n"; }
void PFi386::DOUBLE(double val) {
	*out << "; DOUBLE\n\tdq\t" << setiosflags(ios::fixed) << setprecision(1) << val << "\n"; }
void PFi386::ULDCHR() {
	*out << "; ULDCHR\n\tmovsx\teax,byte [ecx]\n"; }
void PFi386::ULD16() {
	*out << "; ULD16\n\tmovsx\teax,word [ecx]\n"; }
void PFi386::UDIV() {
	*out << "; UDIV\n\tpop\tecx\n\tpop\teax\n\txor\tedx, edx\n\tidiv\tecx\n\tpush\teax\n"; }
void PFi386::UMOD() {
	*out << "; UMOD\n\tpop\tecx\n\tpop\teax\n\txor\tedx, edx\n\tidiv\tecx\n\tpush\tedx\n"; }
void PFi386::UGT() {
	*out << "; UGT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tseta\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::UGE() {
	*out << "; UGE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetae\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::ULT() {
	*out << "; ULT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetb\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::ULE() {
	*out << "; ULE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetbe\tcl\n\tmov\t[esp], ecx\n"; }
void PFi386::JEQ(String sym) {
	*out << "; JEQ\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tje near $" << sym << "\n"; }
void PFi386::JNE(String sym) {
	*out << "; JNE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjne near $" << sym << "\n"; }
void PFi386::JGT(String sym) {
	*out << "; JGT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjg near $" << sym << "\n"; }
void PFi386::JGE(String sym) {
	*out << "; JGE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjge near $" << sym << "\n"; }
void PFi386::JLT(String sym) {
	*out << "; JLT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjl near $" << sym << "\n"; }
void PFi386::JLE(String sym) {
	*out << "; JLE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjle near $" << sym << "\n"; }
void PFi386::JUGT(String sym) {
	*out << "; JUGT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tja near $" << sym << "\n"; }
void PFi386::JUGE(String sym) {
	*out << "; JUGE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjae near $" << sym << "\n"; }
void PFi386::JULT(String sym) {
	*out << "; JULT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjbe near $" << sym << "\n"; }
void PFi386::JULE(String sym) {
	*out << "; JULE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjb near $" << sym << "\n"; }
