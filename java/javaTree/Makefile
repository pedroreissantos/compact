# make Compact.jar and then run the examples with the interpreter:
# java -cp Compact.jar Interp ../examples/tri.cpt
#
# to create i386 executables:
# Compile:	java -cp Compact.jar Compact tri.cpt
#      or:      java -jar Compact.jar tri.cpt
#      or for instruction selection with postfix use:
#      		java -cp Compact Select tri.cpt
#      or for optimized code use i386j.y and i386j.brg
#      		(instead of Compact.y and postfix.brg)
# Assemble:	nasm -felf tri.asm
#      or in windows:	nasm -fwin32 tri.asm
# Link:		ld -o tri 
#      or in windows:	link /NOLOGO tri.o ../runtime/run.lib ../runtime/KERNEL32.lib
# and run:	./tri
ID=Compact
EXT=.cpt
VER=0a
BID=$(ID)-$(VER)
#E=.exe # for windows

.SUFFIXES: $(EXT) .asm .java .class

JAVAC=javac
JFLEX=java -jar ../../tools/JFlex.jar
BYACCJ=byaccj$E $(BJFLAGS)
BJFLAGS=-J -Jsemantic=Tree
PBURG=tools/pburg$E $(PBFLAGS)
PBFLAGS=-J # -T
JAR=jar
RUN=../runtime/librun.a
RM=rm -f	# del /F
RMI=rm -fi	# del /F /P

all: $(ID).jar $(RUN)

$(ID).jar: Parser.class $(ID).class Interp.class Selector.class \
		ParserOpt.class Optimizer.class
	$(JAR) -cmf MANIFEST $@ *.class
#	$(JAR) cmf MANIFEST $@ $(JAVA:.java=.class)

# optmized version with greedy register allocation and instruction selection
opt:
	$(BYACCJ) i386j.y
	$(PBURG) i386j.brg
	$(JAVAC) Parser.java Selector.java


Parser.class: Yylex.class Parser.java

Yylex.java: $(ID).l $(ID).y
	$(JFLEX) $<

Parser.java: $(ID).y
	$(BYACCJ) $<

Selector.java: postfix.brg $(ID).y
	$(PBURG) $<

ParserOpt.class: LexerOpt.class ParserOpt.java

LexerOpt.java: i386j.l i386j.y
	$(JFLEX) $<

ParserOpt.java: i386j.y
	$(BYACCJ) -Jclass=ParserOpt $<

Optimizer.java: i386j.brg i386j.y
	$(PBURG) -Jclass=Optimizer $<

$(RUN):
	$(MAKE) -C ../../runtime $(MFLAGS)

examples: $(ID).jar $(RUN)
	$(MAKE) -C ../../examples $(MFLAGS) 'BIN=java -jar ../java/javaTree/Compact.jar'
	@echo "NOTA: Os exemplos encontram-se em ISO-8859-15, mas o java usa UTF-8"

clean:
	$(RM) *.class *~ Yylex.java Parser.java Selector.java \
		ParserOpt.java LexerOpt.java Optimizer.java
	-$(RMI) $(ID).jar

cleanall: clean
	$(MAKE) -C ../../runtime $(MFLAGS) clean
	$(MAKE) -C ../../examples $(MFLAGS) clean

backup: clean
	PWD=`pwd`; CWD=`basename $$PWD`; cd ..; tar cf - $$CWD | gzip -9 > ../../$@/$(BID).tgz

.java.class:
	$(JAVAC) $<

%:%.asm
	$(NASM) $*.asm
	$(LD) $*.o -l$(ID) -o $@

$(EXT).asm:
	$(ID) $<

.PHONY: all clean cleanall examples backup
