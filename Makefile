ID=compact

TAR=tar czvf $(ID).tgz
RM=rm -f

all:
	$(MAKE) -C lib
	$(MAKE) -C demo
	$(MAKE) -C java
	$(MAKE) -C c++
	$(MAKE) -C runtime
	$(MAKE) -C examples

backup: clean
	cd ..; $(TAR) $(ID)

clean:
	$(MAKE) -C lib clean
	$(MAKE) -C demo clean
	$(MAKE) -C java clean
	$(MAKE) -C c++ clean
	$(MAKE) -C runtime clean
	$(MAKE) -C examples clean
