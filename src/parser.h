#ifndef _PARSER_H_
#define _PARSER_H_
#include<glib.h>

/*Parsa o dump do tipo Users.xml e insere os dados extraidos na estrutura*/
void parseUser(GHashTable* structUsers,char *docname);

/*Parsa o dump do tipo Posts.xml e insere os dados extraidos na estrutura */
void parsePost(GHashTable* structPosts,char *docname);

#endif
