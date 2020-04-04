/*
generated at Wed Oct 03 18:26:37 2012
by $Id: pburg.c,v 2.3 2012/10/03 15:02:11 prs Exp $
*/


public class Selector {

public static final String PBURG_PREFIX="Selector";
public static final String PBURG_VERSION="2.3";
public static final int MAX_COST=0x7fff;
public void panic(String rot, String msg, int val) {
	System.err.println("Internal Error in "+rot+": "+msg+" "+val+"\nexiting...");
	System.exit(2);
}
private static final String ntname[] = {
	"",
	"stat",
	"reg",
	"mem",
	""
};

private static final short stat_NT=1;
private static final short reg_NT=2;
private static final short mem_NT=3;

private static final String termname[] = {
		"",
	/* 2 */ "JZ",
	/* 3 */ "JNZ",
	/* 4 */ "JMP",
	/* 5 */ "ETIQ",
	/* 6 */ "LABEL",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
		"",		"",		"",		"",		"",		"",		"",		"",		"",		"",
	/* 257 */ "YYERRCODE",
	/* 258 */ "INTEGER",
	/* 259 */ "VARIABLE",
	/* 260 */ "STRING",
	/* 261 */ "WHILE",
	/* 262 */ "IF",
	/* 263 */ "PRINT",
	/* 264 */ "READ",
	/* 265 */ "PROGRAM",
	/* 266 */ "END",
	/* 267 */ "IFX",
	/* 268 */ "ELSE",
	/* 269 */ "GE",
	/* 270 */ "LE",
	/* 271 */ "EQ",
	/* 272 */ "NE",
	/* 273 */ "UMINUS",
	/* 274 */ "GT",
	/* 275 */ "LT",
	/* 276 */ "ADD",
	/* 277 */ "SUB",
	/* 278 */ "MUL",
	/* 279 */ "DIV",
	/* 280 */ "MOD",
	/* 281 */ "ASSIGN",
	/* 282 */ "STMT",
	/* 283 */ "NONE",
		""
};

private class state {
	int cost[];
	int stat;
	int reg;
	int mem;
	state() { cost = new int[4]; }
};

private static final short nts_[] = { 0 };
private static final short nts_0[] = { stat_NT, stat_NT, 0 };
private static final short nts_1[] = { 0 };
private static final short nts_2[] = { reg_NT, 0 };
private static final short nts_3[] = { reg_NT, reg_NT, 0 };
private static final short nts_4[] = { mem_NT, 0 };
private static final short nts_5[] = { reg_NT, mem_NT, 0 };
private static final short nts_6[] = { mem_NT, reg_NT, 0 };

private static final short nts[][] = {
	nts_,	/* 0 */
	nts_0,	/* 1 */
	nts_1,	/* 2 */
	nts_2,	/* 3 */
	nts_1,	/* 4 */
	nts_2,	/* 5 */
	nts_2,	/* 6 */
	nts_1,	/* 7 */
	nts_1,	/* 8 */
	nts_2,	/* 9 */
	nts_1,	/* 10 */
	nts_3,	/* 11 */
	nts_3,	/* 12 */
	nts_3,	/* 13 */
	nts_3,	/* 14 */
	nts_3,	/* 15 */
	nts_3,	/* 16 */
	nts_3,	/* 17 */
	nts_3,	/* 18 */
	nts_3,	/* 19 */
	nts_3,	/* 20 */
	nts_3,	/* 21 */
	nts_3,	/* 22 */
	nts_2,	/* 23 */
	nts_4,	/* 24 */
	nts_1,	/* 25 */
	nts_1,	/* 26 */
	nts_3,	/* 27 */
	nts_2,	/* 28 */
	nts_5,	/* 29 */
	nts_3,	/* 30 */
	nts_2,	/* 31 */
	nts_5,	/* 32 */
	nts_2,	/* 33 */
	nts_3,	/* 34 */
	nts_3,	/* 35 */
	nts_3,	/* 36 */
	nts_3,	/* 37 */
	nts_3,	/* 38 */
	nts_3,	/* 39 */
	nts_3,	/* 40 */
	nts_3,	/* 41 */
	nts_3,	/* 42 */
	nts_2,	/* 43 */
	nts_1,	/* 44 */
	nts_6,	/* 45 */
	nts_4,	/* 46 */
	nts_6,	/* 47 */
	nts_4,	/* 48 */
	nts_4,	/* 49 */
	nts_1,	/* 50 */
	nts_2,	/* 51 */
	nts_2,	/* 52 */
};


private static final String string[] = {
/* 0 */	"",
/* 1 */	"stat: STMT(stat,stat)",
/* 2 */	"stat: STRING",
/* 3 */	"stat: PRINT(reg)",
/* 4 */	"stat: READ",
/* 5 */	"stat: JZ(reg,ETIQ)",
/* 6 */	"stat: JNZ(reg,ETIQ)",
/* 7 */	"stat: JMP",
/* 8 */	"stat: LABEL",
/* 9 */	"stat: ASSIGN(VARIABLE,reg)",
/* 10 */	"stat: ASSIGN(VARIABLE,INTEGER)",
/* 11 */	"stat: JZ(LT(reg,reg),ETIQ)",
/* 12 */	"stat: JZ(LE(reg,reg),ETIQ)",
/* 13 */	"stat: JZ(GT(reg,reg),ETIQ)",
/* 14 */	"stat: JZ(GE(reg,reg),ETIQ)",
/* 15 */	"stat: JZ(EQ(reg,reg),ETIQ)",
/* 16 */	"stat: JZ(NE(reg,reg),ETIQ)",
/* 17 */	"stat: JNZ(LT(reg,reg),ETIQ)",
/* 18 */	"stat: JNZ(LE(reg,reg),ETIQ)",
/* 19 */	"stat: JNZ(GT(reg,reg),ETIQ)",
/* 20 */	"stat: JNZ(GE(reg,reg),ETIQ)",
/* 21 */	"stat: JNZ(EQ(reg,reg),ETIQ)",
/* 22 */	"stat: JNZ(NE(reg,reg),ETIQ)",
/* 23 */	"stat: reg",
/* 24 */	"reg: mem",
/* 25 */	"reg: VARIABLE",
/* 26 */	"reg: INTEGER",
/* 27 */	"reg: ADD(reg,reg)",
/* 28 */	"reg: ADD(reg,INTEGER)",
/* 29 */	"reg: ADD(reg,mem)",
/* 30 */	"reg: SUB(reg,reg)",
/* 31 */	"reg: SUB(reg,INTEGER)",
/* 32 */	"reg: SUB(reg,mem)",
/* 33 */	"reg: UMINUS(reg)",
/* 34 */	"reg: MUL(reg,reg)",
/* 35 */	"reg: DIV(reg,reg)",
/* 36 */	"reg: MOD(reg,reg)",
/* 37 */	"reg: EQ(reg,reg)",
/* 38 */	"reg: NE(reg,reg)",
/* 39 */	"reg: LT(reg,reg)",
/* 40 */	"reg: LE(reg,reg)",
/* 41 */	"reg: GE(reg,reg)",
/* 42 */	"reg: GT(reg,reg)",
/* 43 */	"mem: reg",
/* 44 */	"mem: INTEGER",
/* 45 */	"mem: ADD(mem,reg)",
/* 46 */	"mem: ADD(mem,INTEGER)",
/* 47 */	"mem: SUB(mem,reg)",
/* 48 */	"mem: SUB(mem,INTEGER)",
/* 49 */	"mem: UMINUS(mem)",
/* 50 */	"stat: ASSIGN(VARIABLE,ADD(VARIABLE,INTEGER))",
/* 51 */	"stat: JZ(EQ(reg,INTEGER),ETIQ)",
/* 52 */	"stat: JZ(GT(reg,INTEGER),ETIQ)",
};

public void trace(NodeList p, int eruleno, int cost, int bestcost)
{
	int op = p.label();
	String tname = termname[op] != null ? termname[op] : "?";
	System.err.println(p.hashCode()+":"+tname+" matched "+string[eruleno]+" with cost "+cost+" vs. "+bestcost);
}

private static final short decode_stat[] = {
	0,
	1,
	2,
	3,
	4,
	5,
	6,
	7,
	8,
	9,
	10,
	11,
	12,
	13,
	14,
	15,
	16,
	17,
	18,
	19,
	20,
	21,
	22,
	23,
	50,
	51,
	52,
};

private static final short decode_reg[] = {
	0,
	24,
	25,
	26,
	27,
	28,
	29,
	30,
	31,
	32,
	33,
	34,
	35,
	36,
	37,
	38,
	39,
	40,
	41,
	42,
};

private static final short decode_mem[] = {
	0,
	43,
	44,
	45,
	46,
	47,
	48,
	49,
};

private int rule(state st, int goalnt) {
	if (goalnt < 1 || goalnt > 3)
		panic("rule", "Bad goal nonterminal", goalnt);
	if (st == null)
		return 0;
	switch (goalnt) {
	case stat_NT:	return decode_stat[st.stat];
	case reg_NT:	return decode_reg[st.reg];
	case mem_NT:	return decode_mem[st.mem];
	default:
		panic("rule", "Bad goal nonterminal", goalnt);
		return 0;
	}
}

private void closure_reg(NodeList a, int c) {
	state p = (state)a.state();
	trace(a, 43, c + 19, p.cost[mem_NT]);
	if (c + 19 < p.cost[mem_NT]) {
		p.cost[mem_NT] = (short) c + 19;
		p.mem = 1;
		closure_mem(a, c + 19);
	}
	trace(a, 23, c + 0, p.cost[stat_NT]);
	if (c + 0 < p.cost[stat_NT]) {
		p.cost[stat_NT] = (short) c + 0;
		p.stat = 23;
	}
}

private void closure_mem(NodeList a, int c) {
	state p = (state)a.state();
	trace(a, 24, c + 18, p.cost[reg_NT]);
	if (c + 18 < p.cost[reg_NT]) {
		p.cost[reg_NT] = (short) c + 18;
		p.reg = 1;
		closure_reg(a, c + 18);
	}
}

public void label(NodeList a, NodeList u) {
	int c;
	state p;

	if (a == null)
		panic("label", "Null tree in", u.label());
	a.state(p = new state());
	p.cost[1] =
	p.cost[2] =
	p.cost[3] =
		0x7fff;
	switch (a.label()) {
	case 1: // JZ
		label(a.left(),a);
		label(a.right(),a);
		if (	// stat: JZ(reg,ETIQ)
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().state()).cost[reg_NT] + 1;
			trace(a, 5, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 5;
			}
		}
		if (	// stat: JZ(LT(reg,reg),ETIQ)
			a.left().label() == 274 && // LT
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 11, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 11;
			}
		}
		if (	// stat: JZ(LE(reg,reg),ETIQ)
			a.left().label() == 269 && // LE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 12, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 12;
			}
		}
		if (	// stat: JZ(GT(reg,reg),ETIQ)
			a.left().label() == 273 && // GT
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 13, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 13;
			}
		}
		if (	// stat: JZ(GE(reg,reg),ETIQ)
			a.left().label() == 268 && // GE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 14, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 14;
			}
		}
		if (	// stat: JZ(EQ(reg,reg),ETIQ)
			a.left().label() == 270 && // EQ
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 15, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 15;
			}
		}
		if (	// stat: JZ(NE(reg,reg),ETIQ)
			a.left().label() == 271 && // NE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 16, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 16;
			}
		}
		if (	// stat: JZ(EQ(reg,INTEGER),ETIQ)
			a.left().label() == 270 && // EQ
			a.left().right().label() == 257 && // INTEGER
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + 2;
			trace(a, 51, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 25;
			}
		}
		if (	// stat: JZ(GT(reg,INTEGER),ETIQ)
			a.left().label() == 273 && // GT
			a.left().right().label() == 257 && // INTEGER
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + 2;
			trace(a, 52, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 26;
			}
		}
		break;
	case 2: // JNZ
		label(a.left(),a);
		label(a.right(),a);
		if (	// stat: JNZ(reg,ETIQ)
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().state()).cost[reg_NT] + 1;
			trace(a, 6, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 6;
			}
		}
		if (	// stat: JNZ(LT(reg,reg),ETIQ)
			a.left().label() == 274 && // LT
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 17, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 17;
			}
		}
		if (	// stat: JNZ(LE(reg,reg),ETIQ)
			a.left().label() == 269 && // LE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 18, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 18;
			}
		}
		if (	// stat: JNZ(GT(reg,reg),ETIQ)
			a.left().label() == 273 && // GT
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 19, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 19;
			}
		}
		if (	// stat: JNZ(GE(reg,reg),ETIQ)
			a.left().label() == 268 && // GE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 20, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 20;
			}
		}
		if (	// stat: JNZ(EQ(reg,reg),ETIQ)
			a.left().label() == 270 && // EQ
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 21, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 21;
			}
		}
		if (	// stat: JNZ(NE(reg,reg),ETIQ)
			a.left().label() == 271 && // NE
			a.right().label() == 4 // ETIQ
		) {
			c = ((state)a.left().left().state()).cost[reg_NT] + ((state)a.left().right().state()).cost[reg_NT] + 2;
			trace(a, 22, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 22;
			}
		}
		break;
	case 3: // JMP
		// stat: JMP
		trace(a, 7, 1 + 0, p.cost[stat_NT]);
		if (1 + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) 1 + 0;
			p.stat = 7;
		}
		break;
	case 4: // ETIQ
		break;
	case 5: // LABEL
		// stat: LABEL
		trace(a, 8, 0 + 0, p.cost[stat_NT]);
		if (0 + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) 0 + 0;
			p.stat = 8;
		}
		break;
	case 256: // YYERRCODE
		break;
	case 257: // INTEGER
		// reg: INTEGER
		trace(a, 26, 4 + 0, p.cost[reg_NT]);
		if (4 + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) 4 + 0;
			p.reg = 3;
			closure_reg(a, 4 + 0);
		}
		// mem: INTEGER
		trace(a, 44, 20 + 0, p.cost[mem_NT]);
		if (20 + 0 < p.cost[mem_NT]) {
			p.cost[mem_NT] = (short) 20 + 0;
			p.mem = 2;
			closure_mem(a, 20 + 0);
		}
		break;
	case 258: // VARIABLE
		// reg: VARIABLE
		trace(a, 25, 18 + 0, p.cost[reg_NT]);
		if (18 + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) 18 + 0;
			p.reg = 2;
			closure_reg(a, 18 + 0);
		}
		break;
	case 259: // STRING
		// stat: STRING
		trace(a, 2, 1 + 0, p.cost[stat_NT]);
		if (1 + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) 1 + 0;
			p.stat = 2;
		}
		break;
	case 260: // WHILE
		break;
	case 261: // IF
		break;
	case 262: // PRINT
		label(a.left(),a);
		// stat: PRINT(reg)
		c = ((state)a.left().state()).cost[reg_NT] + 1;
		trace(a, 3, c + 0, p.cost[stat_NT]);
		if (c + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) c + 0;
			p.stat = 3;
		}
		break;
	case 263: // READ
		// stat: READ
		trace(a, 4, 1 + 0, p.cost[stat_NT]);
		if (1 + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) 1 + 0;
			p.stat = 4;
		}
		break;
	case 264: // PROGRAM
		break;
	case 265: // END
		break;
	case 266: // IFX
		break;
	case 267: // ELSE
		break;
	case 268: // GE
		label(a.left(),a);
		label(a.right(),a);
		// reg: GE(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 41, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 18;
			closure_reg(a, c + 0);
		}
		break;
	case 269: // LE
		label(a.left(),a);
		label(a.right(),a);
		// reg: LE(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 40, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 17;
			closure_reg(a, c + 0);
		}
		break;
	case 270: // EQ
		label(a.left(),a);
		label(a.right(),a);
		// reg: EQ(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 37, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 14;
			closure_reg(a, c + 0);
		}
		break;
	case 271: // NE
		label(a.left(),a);
		label(a.right(),a);
		// reg: NE(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 38, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 15;
			closure_reg(a, c + 0);
		}
		break;
	case 272: // UMINUS
		label(a.left(),a);
		// reg: UMINUS(reg)
		c = ((state)a.left().state()).cost[reg_NT] + 3;
		trace(a, 33, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 10;
			closure_reg(a, c + 0);
		}
		// mem: UMINUS(mem)
		c = ((state)a.left().state()).cost[mem_NT] + 30;
		trace(a, 49, c + 0, p.cost[mem_NT]);
		if (c + 0 < p.cost[mem_NT]) {
			p.cost[mem_NT] = (short) c + 0;
			p.mem = 7;
			closure_mem(a, c + 0);
		}
		break;
	case 273: // GT
		label(a.left(),a);
		label(a.right(),a);
		// reg: GT(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 42, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 19;
			closure_reg(a, c + 0);
		}
		break;
	case 274: // LT
		label(a.left(),a);
		label(a.right(),a);
		// reg: LT(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 39, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 16;
			closure_reg(a, c + 0);
		}
		break;
	case 275: // ADD
		label(a.left(),a);
		label(a.right(),a);
		// reg: ADD(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 27, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 4;
			closure_reg(a, c + 0);
		}
		if (	// reg: ADD(reg,INTEGER)
			a.right().label() == 257 // INTEGER
		) {
			c = ((state)a.left().state()).cost[reg_NT] + 4;
			trace(a, 28, c + 0, p.cost[reg_NT]);
			if (c + 0 < p.cost[reg_NT]) {
				p.cost[reg_NT] = (short) c + 0;
				p.reg = 5;
				closure_reg(a, c + 0);
			}
		}
		// reg: ADD(reg,mem)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[mem_NT] + 19;
		trace(a, 29, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 6;
			closure_reg(a, c + 0);
		}
		// mem: ADD(mem,reg)
		c = ((state)a.left().state()).cost[mem_NT] + ((state)a.right().state()).cost[reg_NT] + 30;
		trace(a, 45, c + 0, p.cost[mem_NT]);
		if (c + 0 < p.cost[mem_NT]) {
			p.cost[mem_NT] = (short) c + 0;
			p.mem = 3;
			closure_mem(a, c + 0);
		}
		if (	// mem: ADD(mem,INTEGER)
			a.right().label() == 257 // INTEGER
		) {
			c = ((state)a.left().state()).cost[mem_NT] + 31;
			trace(a, 46, c + 0, p.cost[mem_NT]);
			if (c + 0 < p.cost[mem_NT]) {
				p.cost[mem_NT] = (short) c + 0;
				p.mem = 4;
				closure_mem(a, c + 0);
			}
		}
		break;
	case 276: // SUB
		label(a.left(),a);
		label(a.right(),a);
		// reg: SUB(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 30, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 7;
			closure_reg(a, c + 0);
		}
		if (	// reg: SUB(reg,INTEGER)
			a.right().label() == 257 // INTEGER
		) {
			c = ((state)a.left().state()).cost[reg_NT] + 4;
			trace(a, 31, c + 0, p.cost[reg_NT]);
			if (c + 0 < p.cost[reg_NT]) {
				p.cost[reg_NT] = (short) c + 0;
				p.reg = 8;
				closure_reg(a, c + 0);
			}
		}
		// reg: SUB(reg,mem)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[mem_NT] + 19;
		trace(a, 32, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 9;
			closure_reg(a, c + 0);
		}
		// mem: SUB(mem,reg)
		c = ((state)a.left().state()).cost[mem_NT] + ((state)a.right().state()).cost[reg_NT] + 30;
		trace(a, 47, c + 0, p.cost[mem_NT]);
		if (c + 0 < p.cost[mem_NT]) {
			p.cost[mem_NT] = (short) c + 0;
			p.mem = 5;
			closure_mem(a, c + 0);
		}
		if (	// mem: SUB(mem,INTEGER)
			a.right().label() == 257 // INTEGER
		) {
			c = ((state)a.left().state()).cost[mem_NT] + 31;
			trace(a, 48, c + 0, p.cost[mem_NT]);
			if (c + 0 < p.cost[mem_NT]) {
				p.cost[mem_NT] = (short) c + 0;
				p.mem = 6;
				closure_mem(a, c + 0);
			}
		}
		break;
	case 277: // MUL
		label(a.left(),a);
		label(a.right(),a);
		// reg: MUL(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 34, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 11;
			closure_reg(a, c + 0);
		}
		break;
	case 278: // DIV
		label(a.left(),a);
		label(a.right(),a);
		// reg: DIV(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 35, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 12;
			closure_reg(a, c + 0);
		}
		break;
	case 279: // MOD
		label(a.left(),a);
		label(a.right(),a);
		// reg: MOD(reg,reg)
		c = ((state)a.left().state()).cost[reg_NT] + ((state)a.right().state()).cost[reg_NT] + 3;
		trace(a, 36, c + 0, p.cost[reg_NT]);
		if (c + 0 < p.cost[reg_NT]) {
			p.cost[reg_NT] = (short) c + 0;
			p.reg = 13;
			closure_reg(a, c + 0);
		}
		break;
	case 280: // ASSIGN
		label(a.left(),a);
		label(a.right(),a);
		if (	// stat: ASSIGN(VARIABLE,reg)
			a.left().label() == 258 // VARIABLE
		) {
			c = ((state)a.right().state()).cost[reg_NT] + 19;
			trace(a, 9, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 9;
			}
		}
		if (	// stat: ASSIGN(VARIABLE,INTEGER)
			a.left().label() == 258 && // VARIABLE
			a.right().label() == 257 // INTEGER
		) {
			c = 20;
			trace(a, 10, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 10;
			}
		}
		if (	// stat: ASSIGN(VARIABLE,ADD(VARIABLE,INTEGER))
			a.left().label() == 258 && // VARIABLE
			a.right().label() == 275 && // ADD
			a.right().left().label() == 258 && // VARIABLE
			a.right().right().label() == 257 // INTEGER
		) {
			c = 1;
			trace(a, 50, c + 0, p.cost[stat_NT]);
			if (c + 0 < p.cost[stat_NT]) {
				p.cost[stat_NT] = (short) c + 0;
				p.stat = 24;
			}
		}
		break;
	case 281: // STMT
		label(a.left(),a);
		label(a.right(),a);
		// stat: STMT(stat,stat)
		c = ((state)a.left().state()).cost[stat_NT] + ((state)a.right().state()).cost[stat_NT] + 0;
		trace(a, 1, c + 0, p.cost[stat_NT]);
		if (c + 0 < p.cost[stat_NT]) {
			p.cost[stat_NT] = (short) c + 0;
			p.stat = 1;
		}
		break;
	case 282: // NONE
		break;
	default:
		panic("label", "Bad terminal", a.label());
	}
}

private void kids(NodeList p, int eruleno, NodeList kids[]) {
	if (p == null)
		panic("kids", "Null tree in rule", eruleno);
	if (kids == null)
		panic("kids", "Null kids in", p.label());
	switch (eruleno) {
	case 47: // mem: SUB(mem,reg)
	case 45: // mem: ADD(mem,reg)
	case 42: // reg: GT(reg,reg)
	case 41: // reg: GE(reg,reg)
	case 40: // reg: LE(reg,reg)
	case 39: // reg: LT(reg,reg)
	case 38: // reg: NE(reg,reg)
	case 37: // reg: EQ(reg,reg)
	case 36: // reg: MOD(reg,reg)
	case 35: // reg: DIV(reg,reg)
	case 34: // reg: MUL(reg,reg)
	case 32: // reg: SUB(reg,mem)
	case 30: // reg: SUB(reg,reg)
	case 29: // reg: ADD(reg,mem)
	case 27: // reg: ADD(reg,reg)
	case 1: // stat: STMT(stat,stat)
		kids[0] = p.left();
		kids[1] = p.right();
		break;
	case 50: // stat: ASSIGN(VARIABLE,ADD(VARIABLE,INTEGER))
	case 44: // mem: INTEGER
	case 26: // reg: INTEGER
	case 25: // reg: VARIABLE
	case 10: // stat: ASSIGN(VARIABLE,INTEGER)
	case 8: // stat: LABEL
	case 7: // stat: JMP
	case 4: // stat: READ
	case 2: // stat: STRING
		break;
	case 49: // mem: UMINUS(mem)
	case 48: // mem: SUB(mem,INTEGER)
	case 46: // mem: ADD(mem,INTEGER)
	case 33: // reg: UMINUS(reg)
	case 31: // reg: SUB(reg,INTEGER)
	case 28: // reg: ADD(reg,INTEGER)
	case 6: // stat: JNZ(reg,ETIQ)
	case 5: // stat: JZ(reg,ETIQ)
	case 3: // stat: PRINT(reg)
		kids[0] = p.left();
		break;
	case 9: // stat: ASSIGN(VARIABLE,reg)
		kids[0] = p.right();
		break;
	case 22: // stat: JNZ(NE(reg,reg),ETIQ)
	case 21: // stat: JNZ(EQ(reg,reg),ETIQ)
	case 20: // stat: JNZ(GE(reg,reg),ETIQ)
	case 19: // stat: JNZ(GT(reg,reg),ETIQ)
	case 18: // stat: JNZ(LE(reg,reg),ETIQ)
	case 17: // stat: JNZ(LT(reg,reg),ETIQ)
	case 16: // stat: JZ(NE(reg,reg),ETIQ)
	case 15: // stat: JZ(EQ(reg,reg),ETIQ)
	case 14: // stat: JZ(GE(reg,reg),ETIQ)
	case 13: // stat: JZ(GT(reg,reg),ETIQ)
	case 12: // stat: JZ(LE(reg,reg),ETIQ)
	case 11: // stat: JZ(LT(reg,reg),ETIQ)
		kids[0] = p.left().left();
		kids[1] = p.left().right();
		break;
	case 43: // mem: reg
	case 24: // reg: mem
	case 23: // stat: reg
		kids[0] = p;
		break;
	case 52: // stat: JZ(GT(reg,INTEGER),ETIQ)
	case 51: // stat: JZ(EQ(reg,INTEGER),ETIQ)
		kids[0] = p.left().left();
		break;
	default:
		panic("kids", "Bad rule number", eruleno);
	}
}

public void reduce(NodeList p, int goalnt)
{
  int eruleno = rule((state)p.state(), goalnt);
  short nt[] = nts[eruleno];
  NodeList kids[] = new NodeList[10];
  int i;

  for (kids(p, eruleno, kids), i = 0; nt[i] != 0; i++)
    reduce(kids[i], nt[i]);

  switch(eruleno) {
	case 1: // stat: STMT(stat,stat)
// line 6 "i386j.brg"

		break;
	case 2: // stat: STRING
// line 7 "i386j.brg"
{ String l = mklbl(++lbl); System.out.println(" segment .rodata\n align 4\n"+l+": db '"+((NodeString)(Node)p).text()+"', 10, 0\n segment .text\n push dword $"+l+"\n call _prints\n add esp,4"); }
		break;
	case 3: // stat: PRINT(reg)
// line 8 "i386j.brg"
{ System.out.println(" push dword "+name[p.place(p.left().place())]+"\n call _printi\n call _println\n add esp, 4"); busy[p.left().place()]=0; }
		break;
	case 4: // stat: READ
// line 9 "i386j.brg"
{ System.out.println(" call _readi\n mov ["+((NodeString)(Node)p).text()+"], eax"); }
		break;
	case 5: // stat: JZ(reg,ETIQ)
// line 10 "i386j.brg"
{
		System.out.println(" jz "+name[p.place(p.left().place())]+", "+((NodeString)(Node)p.right()).text());
                busy[p.left().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 6: // stat: JNZ(reg,ETIQ)
// line 13 "i386j.brg"
{
		System.out.println(" jnz "+name[p.place(p.left().place())]+", "+((NodeString)(Node)p.right()).text());
                busy[p.left().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 7: // stat: JMP
// line 16 "i386j.brg"
{ System.out.println(" jmp "+((NodeString)(Node)p).text()); }
		break;
	case 8: // stat: LABEL
// line 17 "i386j.brg"
{ System.out.println(((NodeString)(Node)p).text()+":\n"); }
		break;
	case 9: // stat: ASSIGN(VARIABLE,reg)
// line 18 "i386j.brg"
{
                System.out.println(" mov ["+((NodeString)(Node)p.left()).text()+"], "+name[p.right().place()]);
                busy[p.right().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 10: // stat: ASSIGN(VARIABLE,INTEGER)
// line 21 "i386j.brg"
{
		System.out.println(" mov ["+((NodeString)(Node)p.left()).text()+"], dword "+((NodeInteger)(Node)p.right()).value()); }
		break;
	case 11: // stat: JZ(LT(reg,reg),ETIQ)
// line 23 "i386j.brg"
{ jcond(p, "jge"); }
		break;
	case 12: // stat: JZ(LE(reg,reg),ETIQ)
// line 24 "i386j.brg"
{ jcond(p, "jg"); }
		break;
	case 13: // stat: JZ(GT(reg,reg),ETIQ)
// line 25 "i386j.brg"
{ jcond(p, "jle"); }
		break;
	case 14: // stat: JZ(GE(reg,reg),ETIQ)
// line 26 "i386j.brg"
{ jcond(p, "jl"); }
		break;
	case 15: // stat: JZ(EQ(reg,reg),ETIQ)
// line 27 "i386j.brg"
{ jcond(p, "jne"); }
		break;
	case 16: // stat: JZ(NE(reg,reg),ETIQ)
// line 28 "i386j.brg"
{ jcond(p, "jeq"); }
		break;
	case 17: // stat: JNZ(LT(reg,reg),ETIQ)
// line 29 "i386j.brg"
{ jcond(p, "jl"); }
		break;
	case 18: // stat: JNZ(LE(reg,reg),ETIQ)
// line 30 "i386j.brg"
{ jcond(p, "jle"); }
		break;
	case 19: // stat: JNZ(GT(reg,reg),ETIQ)
// line 31 "i386j.brg"
{ jcond(p, "jg"); }
		break;
	case 20: // stat: JNZ(GE(reg,reg),ETIQ)
// line 32 "i386j.brg"
{ jcond(p, "jge"); }
		break;
	case 21: // stat: JNZ(EQ(reg,reg),ETIQ)
// line 33 "i386j.brg"
{ jcond(p, "jeq"); }
		break;
	case 22: // stat: JNZ(NE(reg,reg),ETIQ)
// line 34 "i386j.brg"
{ jcond(p, "jne"); }
		break;
	case 23: // stat: reg
// line 36 "i386j.brg"
{ busy[p.place()] = 0; /* free reg */ }
		break;
	case 24: // reg: mem
// line 37 "i386j.brg"
{ System.out.println(" mov "+name[p.place(getReg())]+", mem"); }
		break;
	case 25: // reg: VARIABLE
// line 38 "i386j.brg"
{ System.out.println(" mov "+name[p.place(getReg())]+", ["+((NodeString)(Node)p).text()+"]"); }
		break;
	case 26: // reg: INTEGER
// line 39 "i386j.brg"
{ System.out.println(" mov "+name[p.place(getReg())]+", "+((NodeInteger)(Node)p).value()); }
		break;
	case 27: // reg: ADD(reg,reg)
// line 40 "i386j.brg"
{ binop("add", p); }
		break;
	case 28: // reg: ADD(reg,INTEGER)
// line 41 "i386j.brg"
{
                System.out.println(" add "+name[p.place(p.left().place())]+", "+((NodeInteger)(Node)p.right()).value()); }
		break;
	case 29: // reg: ADD(reg,mem)
// line 43 "i386j.brg"
{
                System.out.println(" add "+name[p.place(p.left().place())]+", mem"); }
		break;
	case 30: // reg: SUB(reg,reg)
// line 45 "i386j.brg"
{ binop("sub", p); }
		break;
	case 31: // reg: SUB(reg,INTEGER)
// line 46 "i386j.brg"
{
                System.out.println(" sub "+name[p.place(p.left().place())]+", "+((NodeInteger)(Node)p.right()).value()); }
		break;
	case 32: // reg: SUB(reg,mem)
// line 48 "i386j.brg"
{
                System.out.println(" sub "+name[p.place(p.left().place())]+", mem"); }
		break;
	case 33: // reg: UMINUS(reg)
// line 50 "i386j.brg"
{
                System.out.println(" neg "+name[p.place(p.left().place())]); }
		break;
	case 34: // reg: MUL(reg,reg)
// line 53 "i386j.brg"
{ binop("imul", p); }
		break;
	case 35: // reg: DIV(reg,reg)
// line 54 "i386j.brg"
{ idiv(0, p); }
		break;
	case 36: // reg: MOD(reg,reg)
// line 55 "i386j.brg"
{ idiv(1, p); }
		break;
	case 37: // reg: EQ(reg,reg)
// line 56 "i386j.brg"
{ setcond(p, "e"); }
		break;
	case 38: // reg: NE(reg,reg)
// line 57 "i386j.brg"
{ setcond(p, "ne"); }
		break;
	case 39: // reg: LT(reg,reg)
// line 58 "i386j.brg"
{ setcond(p, "l"); }
		break;
	case 40: // reg: LE(reg,reg)
// line 59 "i386j.brg"
{ setcond(p, "le"); }
		break;
	case 41: // reg: GE(reg,reg)
// line 60 "i386j.brg"
{ setcond(p, "ge"); }
		break;
	case 42: // reg: GT(reg,reg)
// line 61 "i386j.brg"
{ setcond(p, "g"); }
		break;
	case 43: // mem: reg
// line 62 "i386j.brg"
{
                System.out.println(" mov mem, "+name[p.left().place()]);
                busy[p.left().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 44: // mem: INTEGER
// line 65 "i386j.brg"
{ System.out.println(" mov mem, "+((NodeInteger)(Node)p).value()); }
		break;
	case 45: // mem: ADD(mem,reg)
// line 66 "i386j.brg"
{
                System.out.println(" add mem, "+name[p.right().place()]);
                busy[p.right().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 46: // mem: ADD(mem,INTEGER)
// line 69 "i386j.brg"
{ System.out.println(" add mem, "+((NodeInteger)(Node)p.right()).value()); }
		break;
	case 47: // mem: SUB(mem,reg)
// line 70 "i386j.brg"
{
                System.out.println(" sub mem, "+name[p.right().place()]);
                busy[p.right().place()]=0; /* liberta registo filho 1 */ }
		break;
	case 48: // mem: SUB(mem,INTEGER)
// line 73 "i386j.brg"
{ System.out.println(" sub mem, "+((NodeInteger)(Node)p.right()).value()); }
		break;
	case 49: // mem: UMINUS(mem)
// line 74 "i386j.brg"
{ System.out.println(" neg mem\n"); }
		break;
	case 50: // stat: ASSIGN(VARIABLE,ADD(VARIABLE,INTEGER))
// line 76 "i386j.brg"
{
		if (((NodeString)(Node)p.left()).text().compareTo(((NodeString)(Node)p.right().left()).text()) != 0)
			System.out.println(" add ["+((NodeString)(Node)p.left()).text()+"], dword "+((NodeInteger)(Node)p.right().right()).value()+" ; incr");
		else {
			int r = getReg();
			System.out.println(" mov "+name[r]+", ["+((NodeString)(Node)p.right().left()).text()+"]");
			System.out.println(" add "+name[r]+", "+((NodeInteger)(Node)p.right().right()).value());
			System.out.println(" mov ["+((NodeString)(Node)p.left()).text()+"], "+name[r]);
			busy[r] = 0;
		}
	  }
		break;
	case 51: // stat: JZ(EQ(reg,INTEGER),ETIQ)
// line 87 "i386j.brg"
{
  System.out.println(" cmp "+name[p.left().left().place()]+", "+((NodeInteger)(Node)p.left().right()).value()+"\n jne "+((NodeString)(Node)p.right()).text());
  busy[p.left().left().place()] = 0;
}
		break;
	case 52: // stat: JZ(GT(reg,INTEGER),ETIQ)
// line 91 "i386j.brg"
{
  System.out.println(" cmp "+name[p.left().left().place()]+", "+((NodeInteger)(Node)p.left().right()).value()+"\n jle "+((NodeString)(Node)p.right()).text());
  busy[p.left().left().place()] = 0;
}
		break;
	default: break;
  }
}

public int select(NodeList p)
{
	label(p,p);
	if (((state)p.state()).stat == 0) {
		System.err.println("No match for start symbol ("+ntname[1]+").\n");
		return 1;
	}
	reduce(p, 1);
	return 0;
}



/*
 * reserva de registos greedy sem spilling.
 * call não faz save do 'eax' porque são sempre instruções e não expressões.
 * idiv spills and moves registers
 * (only IMUL r/m32  is EDX:EAX := EAX * r/m dword; all others truncate)
 */

private static final String name[] = { "eax", "ecx", "edx", "ebx", "esi", "edi", "no-reg" };
private static final String small[] = { "al", "cl", "dl", "bl", "al", "al", "no-reg" };
private int busy[] = new int[7];

private int lbl;
private String mklbl(int n) { return "_L"+n; }

private void setcond(NodeList p, String cond) {
  p.place(p.left().place());
  System.out.println(" cmp "+name[p.place()]+", "+name[p.right().place()]+"\n mov "+name[p.place()]+", dword 0\n set"+cond+" "+small[p.place()]);
  busy[p.right().place()]=0;
}

private void jcond(NodeList p, String cond) {
  System.out.println(" cmp "+ name[p.left().left().place()]+", "+
	  name[p.left().right().place()]+"\n "+ cond+" "+
	  ((NodeString)(Node)p.right()).text());
  busy[p.left().left().place()] = 0;
  busy[p.left().right().place()] = 0;
}

/* IMUL r32,r/m32 ; IMUL r32,imm32 ; IMUL r32,r/m32,imm32 (r32 = r_m32 * imm32) */
private void binop(String op, NodeList p) {
  System.out.println(" "+op+" "+name[p.place(p.left().place())]
			  +", "+name[p.right().place()]);
  busy[p.right().place()]=0;
}

private void idiv(int op, NodeList p) {
  int spilleax = 0, spillecx = 0, spilledx = 0;
  /* idiv r/m32 (uses edx:eax + r/m32 and gives quo=eax rem=edx) */
  if (p.left().place() != 0) { /* dividend not in 'eax' */
    if (p.right().place() == 0) {
      System.out.println(" xchg eax, "+name[p.place(p.left().place())]);
      p.right().place(p.left().place());
      p.left().place(0);
    }
    else {
      if (busy[0] != 0) { /* 'eax' is busy */
	spilleax = 1;
	System.out.println(" push eax ; spill\n");
      }
      System.out.println(" mov eax, "+name[p.left().place()]);
    }
  }
  if (p.right().place() == 2) { /* divisor in 'edx', move it out */
    int r = getReg();
    if (r < 6) { /* move to a free reg */
      busy[p.right().place()]=0;
      p.right().place(r);
      System.out.println(" mov "+name[p.right().place()]+", edx");
    } else { /* spill ecx (or any other) and move divisor to it */
      spillecx = 1;
      System.out.println(" push ecx ; spill\n mov ecx, "+name[p.right().place()]);
      busy[p.right().place()]=0;
      p.right().place(2);
    }
  } else
    if (busy[2] != 0) { /* 'edx' is busy */
      spilledx = 1;
      System.out.println(" push edx ; spill\n");
    }
  System.out.println(" cdq\n idiv "+name[p.right().place()]);
  busy[p.left().place()]=0;
  busy[p.right().place()]=0;
  p.place(getReg());
  System.out.println(" mov "+name[p.place()]+", "+(op == 0 ? "eax" : "edx"));
  if (spillecx == 1) System.out.println(" pop ecx ; unspill\n");
  if (spilledx == 1) System.out.println(" pop edx ; unspill\n");
  if (spilleax == 1) System.out.println(" pop eax ; unspill\n");
}

private int getReg()
{
  int i;

  for(i = 0; i < busy.length; i++)
    if (busy[i] == 0) {
      busy[i] = 1;
      return i;
    }
  System.err.println("Registos esgotados\n");
  return busy.length + 1;
}

private int data(int t, String s, int a, int user) {
  if (s != null) System.out.println(" "+s+" dd 0\n");
  return 1;
}

public void evaluate(NodeList p) {

  //if (errors) return;
  //if (trace) printNode(p, stdout, yynames);
  System.out.println(" segment .text\n align 4\n global _main\n_main:\n");
  //System.out.println(" segment .text\n align 4\n global _main:function\n_main:\n");
  Selector s = new Selector();
  s.select(p);
  System.out.println(" mov eax, 0\n ret\n");
  System.out.println(" segment .data\n");
  // IDevery(data,0);
  System.out.println(" extern _prints\n extern _printi\n extern _println\n extern _readi\n");
}

}
