#ifndef _PARSER_H_
#define _PARSER_H_
#include "usersTable.h"
#include "postTable.h"

/*Parsa o dump do tipo Users.xml e insere os dados extraidos na estrutura*/
void parseUser(UserTable structUsers,char *docname);

/*Parsa o dump do tipo Posts.xml e insere os dados extraidos na estrutura */
void parsePost(PostTable structPosts,char *docname);

#endif
