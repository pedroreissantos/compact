#BIN=../c++/Compact
#BIN=java -jar ../java/javaVisitor/Compact.jar
BIN=../demo/compact
RUN=../runtime/librun.a
EXT=.cpt

.SUFFIXES: $(EXT) .asm
LOADLIBS=$(RUN)
NASM=nasm -felf32
LD=ld -m elf_i386 # for 64-bit linux

all:
	for i in *$(EXT); do $(MAKE) $(MFLAGS) `basename $$i $(EXT)`; done

.asm.o:
	$(NASM) $< -o $@

%:%.o
	$(LD) $< $(RUN) -o $@

$(EXT).asm:
	$(BIN) $< > $@

clean:
	rm -f *.o core a.out *.asm *~
	for i in *$(EXT); do rm -f `basename $$i $(EXT)`; done

.PHONY: all clean
