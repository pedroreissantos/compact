LDLIBS=librun.a
LDOBJ=run.o
OBJ=arm.o lib.o sys.o stk.o dbl.o soft.o

all: $(LDLIBS) $(LDOBJ)

$(LDLIBS): $(OBJ)
	ar crv $@ $?

$(LDOBJ): $(OBJ)
	ld -r -o $@ $?
	
soft.o:
	cd soft; $(MAKE) $(MFLAGS)
	cp soft/soft.o .

dbl.o: dbl.c
	$(CC) $(CFLAGS) -c -DPOSTFIX $<

%:%.s
	as $< -o $*.o
	ld -o $@ $*.o $(LDLIBS)

%:%.o
	ld -o $@ $< $(LDLIBS)

.s.o:
	as $< -o $@

install:: $(LDLIBS)
	-cp $(LDLIBS) ..

clean::
	-rm -f *.o
	-rm -f $(LDLIBS) $(LDOBJ)
	cd soft; $(MAKE) $(MFLAGS) clean
