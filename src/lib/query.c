#include "query.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <string.h>
#include <glib.h>
#include "pergunta.h"
#include "datetime.h"

struct query{
	int id1;
	int aux1;
	int id2;
	int aux2;
	int size;
	int index;
	Pergunta *list;
	GHashTable* structPerguntas;
};

Query initQuery(int id1, int id2, int N, GHashTable* structPerguntas){
	Query t = malloc(sizeof(struct query));
	t-> id1 = id1;
	t-> aux1 = 0;
	t-> id2 = id2;
	t-> aux2 = 0;
	t-> size = N;
	t-> index = -1;
	t-> list = malloc(sizeof(Pergunta)* N);
	for(int i = 0; i < N; i++){
		t->list[i] = NULL;
	}
	t-> structPerguntas = structPerguntas;
	return t;
}


int getId1Q(Query t){
	return t->id1;
}

int getId2Q(Query t){
	return t->id2;
}


int getAux1(Query t){
	return t-> aux1;
}

int getAux2(Query t){
	return t-> aux2;
}

void setAux1(Query t){
	t-> aux1 = 1;
}

void setAux2(Query t){
	t-> aux2 = 1;
}

int getSizeq(Query t){
	return t-> size;
}

int getIndex(Query t){
	return t-> index;
}

void setQuery(Query t){
	t-> aux1 = 0;
	t-> aux2 = 0;
}

Pergunta* getList(Query t){
	return t->list;
}

long getIDlist(Query q, int index){
	printf("id de idlist = %ld\n", getIdp(q->list[index]));
	return getIdp(q->list[index]);
}

void insertList(Query t, long pid){
	Pergunta p = g_hash_table_lookup(t->structPerguntas,GSIZE_TO_POINTER(pid));
	if(t->index == t->size-1){
		if(compareDateTime(getDatep(p), getDatep(t->list[t->index]))){
			t-> list[t->index] = p;
			ordenaPdata(t->list, t-> size);
			printf("inserção feita em %d\n", t->index);
		}
	}
	else if(t->index == -1 ){
		t->list[0] = p;
		t->index++;
		printf("index incrementado para %d\n", t->index);
		printf("id escrito = %ld\n", getIdp(t->list[0]));

	}
	else{
		t-> index++;
		printf("index incrementado para %d\n", t->index);
		t-> list[t->index] = p;
		ordenaPdata(t->list, t-> index);
	}
}

void freeQuery(Query t){
	if(!t) return;

	free(t->list);
	free(t);

	return;
}
