ID=Compact
EXT=.cpt
VER=1a

.SUFFIXES: $(EXT) .asm
CC=g++ # for y.tab.c that does not have a .cpp extension!
CXXFLAGS=-g
LEX=flex
YACC=byacc
YFLAGS=-dv
NASM=nasm -felf
MV=mv

BID=$(ID)-$(VER)
BIN=$(ID)
RUN=../runtime/
RTL=$(RUN)/librun.a

OBJ = Compact.o Compiler.o Interp.o NodeAdd.o NodeAssign.o NodeBinary.o \
	Node.o NodeData.o NodeDiv.o NodeEq.o NodeGe.o NodeGt.o NodeIf.o \
	NodeIfelse.o NodeInteger.o NodeLe.o NodeList.o NodeLt.o NodeMod.o \
	NodeMul.o NodeNe.o NodePrint.o NodeRead.o NodeReal.o NodeString.o \
	NodeSub.o NodeTernary.o NodeUminus.o NodeUnary.o NodeVariable.o \
	NodeWhile.o PFdebug.o PFi386.o Postfix.o SymbolNode.o Symbol.o \
	Tabid.o y.tab.o yyFlexLexer.o main.o

all: $(BIN) $(RTL)

%:%.asm
	$(NASM) $*.asm
	$(LD) $*.o -l$(ID) -o $@

$(EXT).asm:
	$(ID) $<

$(ID): $(OBJ)
	$(LINK.c) -o $@ $^

$(RTL):
	$(MAKE) -C $(RUN) $(MFLAGS)

ver.c: Makefile
	echo "char *version = \"" $(VER) "\";" > $@

yyFlexLexer.cpp: $(ID).l y.tab.cpp
	$(LEX) $(LFLAGS) $<

y.tab.cpp: $(ID).y
	$(YACC) $(YFLAGS) $<
	$(MV) y.tab.c $@

examples: $(ID) $(RTL)
	$(MAKE) -C ../examples $(MFLAGS) BIN=../c++/Compact

clean:
	rm -f *.o core a.out $(BIN) y.tab.* lex.yy.* y.output *.asm $(OBJ)

cleanall: clean
	$(MAKE) -C ../runtime $(MFLAGS) clean
	$(MAKE) -C ../examples $(MFLAGS) clean

backup: clean
	PWD=`pwd`; CWD=`basename $$PWD`; cd ..; tar cf - $$CWD | gzip -9 > ../$@/$(BID).tgz

depend:
	g++ -MM $(OBJ:.o=.cpp) >> Makefile
	makedepend $(ID).y $(ID).l

.PHONY: all clean cleanall examples backup
