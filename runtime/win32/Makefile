.SUFFIXES: .asm .obj .exe
LIB=run
OS=$(WIN32)
SYS=win32
CFLAGS=-g # -Dunderscore
RUNTIME=$(LIB).lib # stand-alone runtime

ELF32=linux.o lib.o sys.o
ELF64=linux64.o lib64.o sys64.o
WIN32=win.obj lib.obj

NASM=nasm -f$(SYS)
CC=cl

all: $(RUNTIME)

librun.a: $(RUNTIME)

$(RUNTIME): dbl.obj $(OS)
	lib /out:$@ $?

dbl.obj: dbl.c
	$(CC) $(CFLAGS) -DPOSTFIX -c $<

.asm.obj:
	$(NASM) $<
install:: $(RUNTIME)
	copy $? ..
clean::
	del /F /Q $(RUNTIME).lib *.obj
