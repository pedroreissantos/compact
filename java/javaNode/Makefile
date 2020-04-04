# make Compact.jar and then run the examples with the interpreter:
# java -cp Compact.jar Interp ../examples/tri.cpt
#
# to create i386 executables:
# Compile:	java -cp Compact.jar Compact tri.cpt
#      or:      java -jar Compact.jar tri.cpt
# Assemble:	nasm -felf tri.asm
# Link:		ld -o tri tri.o ../runtime/lib.o ../runtime/linux.o
# and run:	./tri
ID=Compact
EXT=.cpt
VER=0a
BID=$(ID)-$(VER)

.SUFFIXES: $(EXT) .asm .java .class

JAVAC=javac
JFLEX=java -jar ../../tools/JFlex.jar
BYACCJ=byaccj $(BJFLAGS)
BJFLAGS=-J -Jsemantic=Node
JAR=jar
RUN=runtime/librun.a

all: $(ID).jar $(RUN)

$(ID).jar: Parser.class $(ID).class Interp.class
	$(JAR) -cmf MANIFEST $@ *.class
#	$(JAR) cmf MANIFEST $@ $(JAVA:.java=.class)

Parser.class: Yylex.class Parser.java

Yylex.java: $(ID).l
	$(JFLEX) $<

Parser.java: $(ID).y
	$(BYACCJ) $<

$(RUN):
	$(MAKE) -C ../../runtime $(MFLAGS)

examples: $(ID).jar $(RUN)
	$(MAKE) -C ../examples $(MFLAGS) 'BIN=java -jar ../java/Compact.jar'
	@echo "NOTA: Os exemplos encontram-se em ISO-8859-15, mas o java usa UTF-8"

clean:
	rm -f *.class Yylex.java Parser.java *~
	-rm -fi $(ID).jar

cleanall: clean
	$(MAKE) -C ../../runtime $(MFLAGS) clean
	$(MAKE) -C ../../examples $(MFLAGS) clean

backup: clean
	PWD=`pwd`; CWD=`basename $$PWD`; cd ..; tar cf - $$CWD | gzip -9 > ../$@/$(BID).tgz

.java.class:
	$(JAVAC) $<

%:%.asm
	$(NASM) $*.asm
	$(LD) $*.o -l$(ID) -o $@

$(EXT).asm:
	$(ID) $<

.PHONY: all clean cleanall examples backup
