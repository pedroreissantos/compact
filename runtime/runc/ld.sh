ld -o `basename $1` $1.o ../runc/runc.o crtbegin_static.o crtend_android.o libgcc.a  libc.a libgcc.a libgcc_eh.a 
