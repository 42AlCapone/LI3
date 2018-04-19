#include<stdlib.h>
#include<stdio.h>
#include"chainPerguntas.h"


typedef struct posttable{
	struct chainperguntas** table;
	int usage;
	int ocup;
	int max;
} PostTable;

PostTable* initTable(int size){
	PostTable* t = malloc(sizeof(struct posttable));
	t->table = malloc(sizeof(ChainPerguntas*)*size);
	t->usage = 0;
	t->ocup = 0;
	t->max = size;

	for(int i = 0; i < size; i++){
		t->table[i] = NULL;
	}

	return t;
}

int hashPergunta(Pergunta* p, int m){
	return p->id%m;
}

int hashResposta(Resposta* r, int m){
	return r->parentID%m;
}

int hash(long id, int size){
	return (int)id % size;
}

PostTable* buffup(PostTable* tab, int size){
	PostTable* temp = initTable(size);

	for(int i = 0; i < tab->max; i++){
		if(tab->table[i] != NULL){
			temp->table[hashPergunta(tab->table[i]->p, temp->max)] = tab->table[i];
		}

	}
	free(tab);
	return temp;
}


PostTable* insertPergunta(PostTable* tab, Pergunta* p){
	if(tab->usage > 80){
		tab = buffup(tab, tab->max*2);
	}
	if(tab->table[hashPergunta(p, tab->max)] == NULL){
		tab->table[hashPergunta(p, tab->max)] = initChain(copyPergunta(p));
		tab->ocup++;
		tab->usage = (tab->ocup / tab->max)*100;
	}
	else{
		tab->table[hashPergunta(p, tab->max)] = addPergunta(tab->table[hashPergunta(p, tab->max)], p);
	}

	return tab;
}

/*
PostTable* deletePergunta(PostTable* t, Pergunta* p){
	if(hash(p) > t->max){
		return t;
	}
	if(t->table[hash(p)] == NULL){
		return t;
	}
	t->table[hash(p)] = delete(t->table[hash(p)], p);

	return t;
}
*/

PostTable* insertResposta(PostTable* tab, Resposta* r){
	tab->table[hashResposta(r, tab->max)] = addResposta(tab->table[hashResposta(r, tab->max)], r);
	return tab;
}
void freeTable(PostTable* t){
	for(int i = 0; i < t->max; i++){
		if(t->table[i] != NULL){
			freeChain(t->table[i]);
		}
	}
	free(t);
}
