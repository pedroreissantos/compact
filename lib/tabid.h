/* $Id: tabid.h,v 1.1 2005/10/17 12:51:02 prs Exp $ */
#ifndef _TABID_H_
#define _TABID_H_

typedef int (*IDfunc)(int,char*,int,int);
void   *IDroot(void *swap);
void	IDpop(void),
	IDpush(void),
	IDclear(void),
	IDprint(int,int);
int	IDnew(int,char*,int),
	IDreplace(int,char*,int),
	IDinsert(int,int,char*,int),
	IDfind(char*,int*),
	IDserach(char*,int*,int,int),
	IDforall(IDfunc,int,int,int),
	IDlevel(void);

extern int IDdebug; /* set to 1 to get debug message in stdout */

#define IDtest (-1) /* probe IDfind and IDnew without error messages */

#define IDevery(x,y) IDforall(x,y,0,0)

#endif /* _TABID_H_ */
