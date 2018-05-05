#ifndef _QUERY_H_
#define _QUERY_H_
#include <glib.h>
#include "pergunta.h"


// Declaração da estrutura da Query 9
typedef struct query *Query;

// Função que inicializa a estrutura
Query initQuery(int id1, int id2, int N, GHashTable* structPerguntas);

// Função que insere um id na estrutura
void insertList(Query t, long parentID);

// Função que liberta a memória alocada
void freeQuery(Query t);

// GETs
int getId1Q(Query t);

int getId2Q(Query t);

int getAux1(Query t);

int getAux2(Query t);

int getIndex(Query t);

Pergunta* getList(Query t);

long getIDlist(Query q, int index);


// SETs
void setAux1(Query t);

void setAux2(Query t);

void setQuery(Query t);

#endif
