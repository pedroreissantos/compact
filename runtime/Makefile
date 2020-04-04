RUNTIME=librun.a
SYS=linux32
DIRS=linux32 linux64 win32 arm runc

all: $(RUNTIME)

$(RUNTIME):
	$(MAKE) $(MFLAGS) -C $(SYS) $(RUNTIME)

dirs::
	for i in $(DIRS); do $(MAKE) $(MFLAGS) -C $$i $(RUNTIME); done

install::
	$(MAKE) $(MFLAGS) -C $(SYS) install
	cp $(RUNTIME) ..

clean::
	for i in $(DIRS); do $(MAKE) $(MFLAGS) -C $$i clean; done
