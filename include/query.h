#ifndef _QUERY_H_
#define _QUERY_H_
#include <glib.h>
#include "pergunta.h"


//Declaração da estrutura User
typedef struct query *Query;

Query initQuery(int id1, int id2, int N, GHashTable* structPerguntas);


int getId1Q(Query t);

int getId2Q(Query t);

int getAux1(Query t);

int getAux2(Query t);

void setAux1(Query t);

void setAux2(Query t);

int getIndex(Query t);

void setQuery(Query t);

Pergunta* getList(Query t);

long getIDlist(Query q, int index);

void insertList(Query t, long parentID);

void freeQuery(Query t);

#endif
