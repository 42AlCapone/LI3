#ifndef _PARSER_H_
#define _PARSER_H_
#include<glib.h>

// Função que faz o parsing da string de tags e separa-a em tags individuais
char** parseTags(char* tags, int *nTags);

// Parsa o dump do tipo Users.xml e insere os dados extraidos na estrutura respetiva
void parseUser(GHashTable* structUsers,char *docname);

// Função que calcula o rate necessario para a query 10
float calcRate(GHashTable* structUsers, long oid, int score, int cCount);

// Parsa o dump do tipo Posts.xml e insere os dados extraidos na estrutura respetiva
void parsePost(GHashTable* structPerguntas, GHashTable* structRespostas ,GHashTable* structUsers, char *docname);


#endif
