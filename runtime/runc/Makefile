AR=ar crl
LD=ld -r -o
CFLAGS=-g -Dunderscore # -fno-stack-protector # quando __stack_chk_fail em Ubunto

LIB=librun.a
OUT=run.o
OBJ= boot.o lib.o dbl.o stub.o

all: $(LIB) $(OUT)

$(LIB): $(OBJ)
	$(AR) $@ $(OBJ)

$(OUT): $(OBJ)
	$(LD) $@ $(OBJ)

clean:
	$(RM) $(LIB) $(OUT) $(OBJ)
