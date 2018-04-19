#ifndef _usersTable_h_
#define _usersTable_h_

#include "headers/users.h"
#include "chainUsers.h"

typedef struct usertable *UserTable;

UserTable initTable(int size);

int hashUser(User u, int m);

int hash(long id, int size);

UserTable buffup (UserTable tab, int size);

UserTable insertUser(UserTable tab, User u);

void freeTable(UserTable t);

#endif

