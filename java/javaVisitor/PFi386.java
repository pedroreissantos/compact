import java.io.*;

public class PFi386 extends Postfix {
	private PrintWriter out;
	PFi386() { // write stdout
		Writer outp = new PrintWriter(System.out);
		out = new PrintWriter(outp, true);
	}
	PFi386(String outfile) { // write to named file
		Writer outp = new PrintWriter(System.out);
		try {
			outp = new FileWriter(outfile);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
		out = new PrintWriter(outp, true);
	}
	public void NOP() {
		out.println("; NOP\n\tnop"); }
	public void INT(int val) {
		out.println("; INT\n\tpush\tdword " + val); }
	public void ADD() {
		out.println("; ADD\n\tpop\teax\n\tadd\tdword [esp], eax"); }
	public void SUB() {
		out.println("; SUB\n\tpop\teax\n\tsub\tdword [esp], eax"); }
	public void MUL() {
		out.println("; MUL\n\tpop\teax\n\timul\tdword eax, [esp]\n\tmov\t[esp], eax"); }
	public void DIV() {
		out.println("; DIV\n\tpop\tecx\n\tpop\teax\n\tcdq\n\tidiv\tecx\n\tpush\teax"); }
	public void MOD() {
		out.println("; MOD\n\tpop\tecx\n\tpop\teax\n\tcdq\n\tidiv\tecx\n\tpush\tedx"); }
	public void NEG() {
		out.println("; NEG\n\tneg\tdword [esp]"); }
	public void INCR(int val) {
		out.println("; INCR\n\tpop\teax\n\tadd\tdword [eax], " + val); }
	public void DECR(int val) {
		out.println("; DECR\n\tpop\teax\n\tsub\tdword [eax], " + val); }
	public void GT() {
		out.println("; GT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetg\tcl\n\tmov\t[esp], ecx"); }
	public void GE() {
		out.println("; GE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetge\tcl\n\tmov\t[esp], ecx"); }
	public void LT() {
		out.println("; LT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetl\tcl\n\tmov\t[esp], ecx"); }
	public void LE() {
		out.println("; LE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetle\tcl\n\tmov\t[esp], ecx"); }
	public void EQ() {
		out.println("; EQ\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsete\tcl\n\tmov\t[esp], ecx"); }
	public void NE() {
		out.println("; NE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetne\tcl\n\tmov\t[esp], ecx"); }
	public void AND() {
		out.println("; AND\n\tpop\teax\n\tand\tdword [esp], eax"); }
	public void OR() {
		out.println("; OR\n\tpop\teax\n\tor\tdword [esp], eax"); }
	public void XOR() {
		out.println("; XOR\n\tpop\teax\n\txor\tdword [esp], eax"); }
	public void NOT() {
		out.println("; NOT\n\tnot\tdword [esp]"); }
	public void ROTL() {
		out.println("; ROTL\n\tpop\tecx\n\trol\tdword [esp], cl"); }
	public void ROTR() {
		out.println("; ROTR\n\tpop\tecx\n\tror\tdword [esp], cl"); }
	public void SHTL() {
		out.println("; SHTL\n\tpop\tecx\n\tsal\tdword [esp], cl"); }
	public void SHTRU() {
		out.println("; SHTRU\n\tpop\tecx\n\tshr\tdword [esp], cl"); }
	public void SHTRS() {
		out.println("; SHTRS\n\tpop\tecx\n\tsar\tdword [esp], cl"); }
	public void LOCAL(int val) {
		out.println("; LOCAL\n\tlea\teax, [ebp+" + val + "]\n\tpush\teax"); }
	public void ADDR(String sym) {
		out.println("; ADDR\n\tpush\tdword $" + sym); }
	public void LOCV(int val) {
		out.println("; LOCV\n\tpush\tdword [ebp+" + val + "]"); }
	public void ADDRV(String sym) {
		out.println("; ADDRV\n\tpush\tdword [$" + sym + "]"); }
	public void LOCA(int val) {
		out.println("; LOCA\n\tpop\teax\n\tmov\t[ebp+" + val + "], eax"); }
	public void ADDRA(String sym) {
		out.println("; ADDRA\n\tpop\teax\n\tmov\t[$" + sym + "], eax"); }
	public void LOAD() {
		out.println("; LOAD\n\tpop\teax\n\tpush\tdword [eax]"); }
	public void STORE() {
		out.println("; STORE\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], eax"); }
	public void LDCHR() {
		out.println("; LDCHR\n\tpop\tecx\n\tmovsx\teax,byte [ecx]\n\tpush\teax"); }
	public void STCHR() {
		out.println("; STCHR\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], al"); }
	public void LD16() {
		out.println("; LD16\n\tpop\tecx\n\tmovsx\teax,word [ecx]\n\tpush\teax"); }
	public void ST16() {
		out.println("; ST16\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], ax"); }
	public void ENTER(int val) {
		out.println("; ENTER\n\tpush\tebp\n\tmov\tebp, esp\n\tsub\tesp, " + val); }
	public void START() {
		out.println("; START\n\tpush\tebp\n\tmov\tebp, esp"); }
	public void ALLOC() {
		out.println("; ALLOC\n\tpop\teax\n\tsub\tesp, eax"); }
	public void LEAVE() {
		out.println("; LEAVE\n\tleave"); }
	public void TRASH(int val) {
		out.println("; TRASH\n\tadd\tesp, " + val); }
	public void CALL(String sym) {
		out.println("; CALL\n\tcall\t" + sym); }
	public void RET() {
		out.println("; RET\n\tret"); }
	public void RETN(int val) {
		out.println("; RETN\n\tret\t" + val); }
	public void BRANCH() {
		out.println("; BRANCH\n\tpop\teax\n\tcall\teax"); }
	public void LEAP() {
		out.println("; LEAP\n\tpop\teax\n\tjmp\teax"); }
	public void JMP(String sym) {
		out.println("; JMP\n\tjmp\tdword " + sym); }
	public void JZ(String sym) {
		out.println("; JZ\n\tpop\teax\n\tcmp\teax, byte 0\n\tje\tnear " + sym); }
	public void JNZ(String sym) {
		out.println("; JNZ\n\tpop\teax\n\tcmp\teax, byte 0\n\tjne\tnear " + sym); }
	public void DUP() {
		out.println("; DUP\n\tpush\tdword [esp]"); }
	public void SWAP() {
		out.println("; SWAP\n\tpop\teax\n\tpop\tecx\n\tpush\teax\n\tmov\teax, ecx\n\tpush\teax"); }
	public void SP() {
		out.println("; SP\n\tpush\tesp"); }
	public void PUSH() {
		out.println("; PUSH\n\tpush\teax"); }
	public void POP() {
		out.println("; POP\n\tpop\teax"); }
	public void I2D() {
		out.println("; I2D\n\tfild\tdword [esp]\n\tsub\tesp, byte 4\n\tfstp\tqword [esp]"); }
	public void F2D() {
		out.println("; F2D\n\tfld\tdword [esp]\n\tsub\tesp, byte 4\n\tfstp\tqword [esp]"); }
	public void D2I() {
		out.println("; D2I\n\tfld\tqword [esp]\n\tadd\tesp, byte 4\n\tfistp\tdword [esp]"); }
	public void D2F() {
		out.println("; D2F\n\tfld\tqword [esp]\n\tadd\tesp, byte 4\n\tfstp\tdword [esp]"); }
	public void DADD() {
		out.println("; DADD\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfaddp\tst1\n\tfstp\tqword [esp]"); }
	public void DSUB() {
		out.println("; DSUB\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfsubrp\tst1\n\tfstp\tqword [esp]"); }
	public void DMUL() {
		out.println("; DMUL\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfmulp\tst1\n\tfstp\tqword [esp]"); }
	public void DDIV() {
		out.println("; DDIV\n\tfld\tqword [esp]\n\tadd\tesp, byte 8\n\tfld\tqword [esp]\n\tfdivrp\tst1\n\tfstp\tqword [esp]"); }
	public void DCMP() {
		out.println("; DCMP\n\tfld\tqword [esp+8]\n\tfld\tqword [esp]\n\tadd\tesp, byte 12\n\tfsubp\tst1\n\tfxtract\n\tffree\tst1\n\tfistp\tdword [esp]"); }
	public void DNEG() {
		out.println("; DNEG\n\tfld\tqword [esp]\n\tfchs\n\tfstp\tqword [esp]"); }
	public void LOAD2() {
		out.println("; LOAD2\n\tpop\teax\n\tpush\tdword [eax+4]\n\tpush\tdword [eax]"); }
	public void STORE2() {
		out.println("; STORE2\n\tpop\tecx\n\tpop\teax\n\tmov\t[ecx], eax\n\tpop\teax\n\tmov\t[ecx+4], eax"); }
	public void DUP2() {
		out.println("; DUP2\n\tpush\tdword [esp+4]\n\tpush\tdword [esp+4]\n"); }
	public void DPUSH() {
		out.println("; DPUSH\n\tsub\tesp, byte 8\n\tfstp\tqword [esp]"); }
	public void DPOP() {
		out.println("; DPOP\n\tfld\tqword [esp]\n\tadd\tesp, byte 8"); }
	public void NIL() {
		out.println("; NIL"); }
	public void TEXT() {
		out.println("; TEXT\nsegment\t.text"); }
	public void RODATA() {
		out.println("; RODATA\nsegment\t.rodata"); }
	public void DATA() {
		out.println("; DATA\nsegment\t.data"); }
	public void BSS() {
		out.println("; BSS\nsegment\t.bss"); }
	public void ALIGN() {
		out.println("; ALIGN\nalign\t4"); }
	public void EXTRN(String sym) {
		out.println("; EXTRN\nextern\t" + sym); }
	public void COMMON(int val) {
		out.println("; COMMON\ncommon\t" + val); }
	public void GLOBL(String sym) {
		out.println("; GLOBL\nglobal\t" + sym); }
	public void GLOBLfunc(String sym) {
		out.println("; GLOBL\nglobal\t" + sym + ":function"); }
	public void GLOBLdata(String sym) {
		out.println("; GLOBL\nglobal\t" + sym + ":object"); }
	public void LABEL(String sym) {
		out.println("; LABEL\n" + sym + ":"); }
	public void CONST(int val) {
		out.println("; CONST\n\tdd\t" + val); }
	public void STR(String val) {
		out.println("; STR\n\tdb\t'" + val + "', 0"); }
	public void CHAR(char val) {
		out.println("; CHAR\n\tdb\t" + (int)(val)); }
	public void ID(String sym) {
		out.println("; ID\n\tdd\t" + sym); }
	public void BYTE(int val) {
		out.println("; BYTE\n\tresb\t" + val); }
	public void FLOAT(float val) {
		out.println("; FLOAT\n\tdd\t" + val); }
	public void DOUBLE(double val) {
		out.println("; DOUBLE\n\tdq\t" + val); }
	public void ULDCHR() {
		out.println("; ULDCHR\n\tmovsx\teax,byte [ecx]"); }
	public void ULD16() {
		out.println("; ULD16\n\tmovsx\teax,word [ecx]"); }
	public void UDIV() {
		out.println("; UDIV\n\tpop\tecx\n\tpop\teax\n\txor\tedx, edx\n\tidiv\tecx\n\tpush\teax"); }
	public void UMOD() {
		out.println("; UMOD\n\tpop\tecx\n\tpop\teax\n\txor\tedx, edx\n\tidiv\tecx\n\tpush\tedx"); }
	public void UGT() {
		out.println("; UGT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tseta\tcl\n\tmov\t[esp], ecx"); }
	public void UGE() {
		out.println("; UGE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetae\tcl\n\tmov\t[esp], ecx"); }
	public void ULT() {
		out.println("; ULT\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetb\tcl\n\tmov\t[esp], ecx"); }
	public void ULE() {
		out.println("; ULE\n\tpop\teax\n\txor\tecx, ecx\n\tcmp\t[esp], eax\n\tsetbe\tcl\n\tmov\t[esp], ecx"); }
	public void JEQ(String sym) {
		out.println("; JEQ\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tje near " + sym); }
	public void JNE(String sym) {
		out.println("; JNE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjne near " + sym); }
	public void JGT(String sym) {
		out.println("; JGT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjg near " + sym); }
	public void JGE(String sym) {
		out.println("; JGE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjge near " + sym); }
	public void JLT(String sym) {
		out.println("; JLT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjl near " + sym); }
	public void JLE(String sym) {
		out.println("; JLE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjle near " + sym); }
	public void JUGT(String sym) {
		out.println("; JUGT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tja near " + sym); }
	public void JUGE(String sym) {
		out.println("; JUGE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjae near " + sym); }
	public void JULT(String sym) {
		out.println("; JULT\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjbe near " + sym); }
	public void JULE(String sym) {
		out.println("; JULE\n\tpop eax\n\tpop ecx\n\tcmp eax, ecx\n\tjb near " + sym); }
}
