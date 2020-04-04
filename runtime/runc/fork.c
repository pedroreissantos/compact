/* Minimal implementation (for a system without processes) */

#include <errno.h>
#undef errno
extern int errno;
int fork(void) {
  errno = EAGAIN;
  return -1;
}
