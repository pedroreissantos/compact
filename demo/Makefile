# 'make full' generates all possible compilers
ID=compact
EXT=.cpt
VER=1k

BID=$(ID)-$(VER)
COPY="$(BID) (C)prs, 2013"

.SUFFIXES: $(EXT) .brg .asm
LOADLIBS=$(UTIL) # -lfl
CC=gcc
CFLAGS=-g -DYYDEBUG -I$(LIB)
#CFLAGS=-O2 -I$(LIB)
LEX=flex
LFLAGS=-l
YACC=byacc
YFLAGS=-dv
BURG=pburg
BFLAGS= -T
NASM=nasm -felf32

BID=$(ID)-$(VER)
LIB=../lib
UTIL=$(LIB)/libutil.a
BIN=$(ID)
RUN=../runtime/librun.a
FULL=android pfarm pepe postfix trans gram greedy interp stab jasmin msil pfix pfgas pfx64 pfdeb

all: $(UTIL) $(BIN) $(RUN)

.brg.c:
	$(BURG) $(BFLAGS) $< $@

%:%.asm
	$(NASM) $*.asm -o $*.o
	$(LD) $*.o $(RUN) -o $@

$(EXT).asm:
	$(ID) $<

$(ID): scan.o gram.o i386.o $(UTIL)
	$(LINK.c) -o $@ $^

full: all $(FULL)

pfix: scan.o gram.o pfix.o $(UTIL)
	$(LINK.c) -o $@ $^

pfarm.o: pfix.c
	$(COMPILE.c) -o $@ -DpfARM $<

pfarm: scan.o gram.o pfarm.o $(UTIL)
	$(LINK.c) -o $@ $^

pfdeb.o: pfix.c
	$(COMPILE.c) -o $@ -DpfDEBUG $<

pfdeb: scan.o gram.o pfdeb.o $(UTIL)
	$(LINK.c) -o $@ $^

pfgas.o: pfix.c
	$(COMPILE.c) -o $@ -DpfI386GAS $<

pfgas: scan.o gram.o pfgas.o $(UTIL)
	$(LINK.c) -o $@ $^

pfx64.o: pfix.c
	$(COMPILE.c) -o $@ -DpfAMD64 $<

pfx64: scan.o gram.o pfx64.o $(UTIL)
	$(LINK.c) -o $@ $^

android: scan.o gram.o android.o $(UTIL)
	$(LINK.c) -o $@ $^

pepe: scan.o gram.o pepe.o $(UTIL)
	$(LINK.c) -o $@ $^

postfix: scan.o tree.o postfix.o $(UTIL)
	$(LINK.c) -o $@ $^

trans: scan.o tree.o trans.o $(UTIL)
	$(LINK.c) -o $@ $^

gram: scan.o syntax.o $(UTIL)
	$(LINK.c) -o $@ $^

greedy: scan.o tree.o greedy.o $(UTIL)
	$(LINK.c) -o $@ $^

interp: scan.o tree.o interp.o $(UTIL)
	$(LINK.c) -o $@ $^

stab: scan.o tree.o stab.o $(UTIL)
	$(LINK.c) -o $@ $^

jasmin: scan.o tree.o jasmin.o $(UTIL)
	$(LINK.c) -o $@ $^

msil: scan.o tree.o msil.o $(UTIL)
	$(LINK.c) -o $@ $^

scan.o: tree.c syntax.c gram.c

$(UTIL):
	$(MAKE) -C $(LIB) $(MFLAGS) CC=$(CC)

$(RUN):
	$(MAKE) -C ../runtime $(MFLAGS) CC=$(CC)

ver.c: Makefile
	echo "char *version = \"" $(VER) "\";" > $@

lex.yy.c: $(ID).l y.tab.c
	$(LEX) $(LFLAGS) $<

y.tab.c: $(ID).y
	$(YACC) $(YFLAGS) $<

examples: $(RUN) $(ID)
	$(MAKE) -C ../examples $(MFLAGS) BIN=../demo/compact

install: $(BIN)
	cp $(BIN) ..

clean:
	rm -f *~ *.o *.exe core a.out $(BIN) y.tab.* lex.yy.* y.output *.asm tree.c syntax.c gram.c $(FULL)
	for i in *.brg; do rm -f `basename $$i .brg`.c; done

cleanall: clean
	$(MAKE) -C $(LIB) $(MFLAGS) clean
	$(MAKE) -C ../runtime $(MFLAGS) clean
	$(MAKE) -C ../examples $(MFLAGS) clean

backup: clean
	PWD=`pwd`; CWD=`basename $$PWD`; cd ..; tar cf - $$CWD | gzip -9 > $@/$(BID).tgz

.PHONY: all clean cleanall backup examples full install
