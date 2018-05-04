import java.io.*;

public class PFdebug extends Postfix {
	private PrintWriter out;
	PFdebug() { // write stdout
		Writer outp = new PrintWriter(System.out);
		out = new PrintWriter(outp, true);
	}
	PFdebug(String outfile) { // write to named file
		Writer outp = new PrintWriter(System.out);
		try {
			outp = new FileWriter(outfile);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
		out = new PrintWriter(outp, true);
	}
	public void NOP() { out.println("NOP"); }
	public void INT(int val) { out.println("INT " + val); }
	public void ADD() { out.println("ADD"); }
	public void SUB() { out.println("SUB"); }
	public void MUL() { out.println("MUL"); }
	public void DIV() { out.println("DIV"); }
	public void MOD() { out.println("MOD"); }
	public void NEG() { out.println("NEG"); }
	public void INCR(int val) { out.println("INCR " + val); }
	public void DECR(int val) { out.println("DECR " + val); }
	public void GT() { out.println("GT"); }
	public void GE() { out.println("GE"); }
	public void LT() { out.println("LT"); }
	public void LE() { out.println("LE"); }
	public void EQ() { out.println("EQ"); }
	public void NE() { out.println("NE"); }
	public void AND() { out.println("AND"); }
	public void OR() { out.println("OR"); }
	public void XOR() { out.println("XOR"); }
	public void NOT() { out.println("NOT"); }
	public void ROTL() { out.println("ROTL"); }
	public void ROTR() { out.println("ROTR"); }
	public void SHTL() { out.println("SHTL"); }
	public void SHTRU() { out.println("SHTRU"); }
	public void SHTRS() { out.println("SHTRS"); }
	public void LOCAL(int val) { out.println("LOCAL " + val); }
	public void ADDR(String sym) { out.println("ADDR " + sym); }
	public void LOCV(int val) { out.println("LOCV " + val); }
	public void ADDRV(String sym) { out.println("ADDRV " + sym); }
	public void LOCA(int val) { out.println("LOCA " + val); }
	public void ADDRA(String sym) { out.println("ADDRA " + sym); }
	public void LOAD() { out.println("LOAD"); }
	public void STORE() { out.println("STORE"); }
	public void LDCHR() { out.println("LDCHR"); }
	public void STCHR() { out.println("STCHR"); }
	public void LD16() { out.println("LD16"); }
	public void ST16() { out.println("ST16"); }
	public void ENTER(int val) { out.println("ENTER " + val); }
	public void START() { out.println("START"); }
	public void ALLOC() { out.println("ALLOC"); }
	public void LEAVE() { out.println("LEAVE"); }
	public void TRASH(int val) { out.println("TRASH " + val); }
	public void CALL(String sym) { out.println("CALL " + sym); }
	public void RET() { out.println("RET"); }
	public void RETN(int val) { out.println("RETN " + val); }
	public void BRANCH() { out.println("BRANCH"); }
	public void LEAP() { out.println("LEAP"); }
	public void JMP(String sym) { out.println("JMP " + sym); }
	public void JZ(String sym) { out.println("JZ " + sym); }
	public void JNZ(String sym) { out.println("JNZ " + sym); }
	public void DUP() { out.println("DUP"); }
	public void SWAP() { out.println("SWAP"); }
	public void SP() { out.println("SP"); }
	public void PUSH() { out.println("PUSH"); }
	public void POP() { out.println("POP"); }
	public void I2D() { out.println("I2D"); }
	public void F2D() { out.println("F2D"); }
	public void D2I() { out.println("D2I"); }
	public void D2F() { out.println("D2F"); }
	public void DADD() { out.println("DADD"); }
	public void DSUB() { out.println("DSUB"); }
	public void DMUL() { out.println("DMUL"); }
	public void DDIV() { out.println("DDIV"); }
	public void DCMP() { out.println("DCMP"); }
	public void DNEG() { out.println("DNEG"); }
	public void LOAD2() { out.println("LOAD2"); }
	public void STORE2() { out.println("STORE2"); }
	public void DUP2() { out.println("DUP2"); }
	public void DPUSH() { out.println("DPUSH"); }
	public void DPOP() { out.println("DPOP"); }
	public void NIL() { out.println("NIL"); }
	public void TEXT() { out.println("TEXT"); }
	public void RODATA() { out.println("RODATA"); }
	public void DATA() { out.println("DATA"); }
	public void BSS() { out.println("BSS"); }
	public void ALIGN() { out.println("ALIGN"); }
	public void EXTRN(String sym) { out.println("EXTRN " + sym); }
	public void COMMON(int val) { out.println("COMMON " + val); }
	public void GLOBL(String sym) { out.println("GLOBL " + sym); }
	public void GLOBLfunc(String sym) { out.println("GLOBL " + sym + " :function"); }
	public void GLOBLdata(String sym) { out.println("GLOBL " + sym + " :object"); }
	public void LABEL(String sym) { out.println("LABEL " + sym); }
	public void CONST(int val) { out.println("CONST " + val); }
	public void STR(String val) { out.println("STR \"" + val + "\""); }
	public void CHAR(char val) { out.println("CHAR " + Character.getNumericValue(val)); }
	public void ID(String sym) { out.println("ID " + sym); }
	public void BYTE(int val) { out.println("BYTE " + val); }
	public void FLOAT(float val) { out.println("FLOAT " + val); }
	public void DOUBLE(double val) { out.println("DOUBLE " + val); }
	public void ULDCHR() { out.println("ULDCHR"); }
	public void ULD16() { out.println("ULD16"); }
	public void UDIV() { out.println("UDIV"); }
	public void UMOD() { out.println("UMOD"); }
	public void UGT() { out.println("UGT"); }
	public void UGE() { out.println("UGE"); }
	public void ULT() { out.println("ULT"); }
	public void ULE() { out.println("ULE"); }
	public void JEQ(String sym) { out.println("JEQ " + sym); }
	public void JNE(String sym) { out.println("JNE " + sym); }
	public void JGT(String sym) { out.println("JGT " + sym); }
	public void JGE(String sym) { out.println("JGE " + sym); }
	public void JLT(String sym) { out.println("JLT " + sym); }
	public void JLE(String sym) { out.println("JLE " + sym); }
	public void JUGT(String sym) { out.println("JUGT " + sym); }
	public void JUGE(String sym) { out.println("JUGE " + sym); }
	public void JULT(String sym) { out.println("JULT " + sym); }
	public void JULE(String sym) { out.println("JULE " + sym); }
}
