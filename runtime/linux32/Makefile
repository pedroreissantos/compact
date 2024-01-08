LIB=run
OS=$(ELF32)
SYS=elf32
CFLAGS=-g -Dunderscore # -fno-stack-protector # quando __stack_chk_fail em Ubunto
CC=gcc -m32
BOOT=$(LIB).o # bootstrap runtime for a C compiler
RUNTIME=lib$(LIB).a # stand-alone runtime

ELF32=linux.o lib.o sys.o
ELF64=linux64.o lib64.o sys64.o
WIN32=win.obj lib.obj

NASM=nasm -f$(SYS)

all: $(RUNTIME) $(BOOT)

$(RUNTIME): dbl.o $(OS)
	ar crv $@ $?

$(BOOT): dbl.o $(OS)
	ld -melf_i386 -r -o  $@ $^

dbl.o: dbl.c
	$(CC) $(CFLAGS) -DPOSTFIX -c $<

.SUFFIXES: .asm .obj
.asm.obj:
	$(NASM) $<
.asm.o:
	$(NASM) $<
install:: $(RUNTIME)
	cp $(RUNTIME) ..
clean::
	rm -f $(RUNTIME) $(BOOT) *.o a.out core
