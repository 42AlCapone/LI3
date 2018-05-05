#ifndef _PERGUNTA_H_
#define _PERGUNTA_H_

#include "date.h"
#include "resposta.h"
#include <glib.h>

// Estrutura da Pergunta
typedef struct pergunta *Pergunta;

// Função que inicializa uma Pergunta
Pergunta initPergunta(long mainID, char* date, int scr, long userID, char* ttl, char** tgs, int nTags, int cCount);

// Função que cria uma Pergunta vazia
Pergunta genPergunta();

// Função que insere uma resposta na AVL de respostas da respetiva pergunta mãe
void insertResposta(Pergunta p, Resposta r);

// Função que faz a troca da posição de duas perguntas num array
void swapPergunta(Pergunta a[],int o,int s);

// Função que ordena um array de perguntas por cronologia inversa
void ordenaPdata(Pergunta p[], int N);

// Função que ordena um array de perguntas pelo nº de respostas de cada pergunta
void ordenaPerg(Pergunta a[], int N);

// Funçao que verifica se uma pergunta esta entre 2 datas
int pergunta_entre_datas(Pergunta p, DateTime b, DateTime e);

// Funçao que verifica se existe uma palavra num titulo
int searchTitle(char* word, char* title);

// Função que compara duas perguntas e verifica qual a mais recente
int comparePerguntas(Pergunta p1, Pergunta p2);

// Função que da free a uma pergunta
void freePergunta(Pergunta p);

// GETs
GTree* getTree(Pergunta p);

Resposta getResp(Pergunta p, Date d);

long getIdp(Pergunta p);

DateTime getDatep(Pergunta p);

int getSize (Pergunta p);

int getScorep(Pergunta p);

long getOwnerUserIDp(Pergunta p);

char* getTitle(Pergunta p);

char** getTags(Pergunta p);

int getNTags(Pergunta p);

int getcommentCount(Pergunta p);


//SETs
void setDateP(DateTime d, Pergunta p);

void setTitle (char* title, Pergunta p);


#endif
