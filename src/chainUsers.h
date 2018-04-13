#ifndef _chainUsers_h_
#define _chainUsers_h_

#include "headers/users.h"

typedef struct chainusers *ChainUsers; 

ChainUsers initChain(User u1);

ChainUsers addUser(ChainUsers c, User u1);

ChainUsers copyChain(ChainUsers c);

void freeChain(ChainUsers c);

#endif
