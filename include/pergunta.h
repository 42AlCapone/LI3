#ifndef _PERGUNTA_H_
#define _PERGUNTA_H_
#include "date.h"
#include "resposta.h"
#include <glib.h>

typedef struct pergunta *Pergunta;



Pergunta initPergunta(long mainID, char* date, int scr, long userID, char* ttl, char** tgs, int nTags, int cCount);

Pergunta genPergunta();

void insertResposta(Pergunta p, Resposta r);

void swapPergunta(Pergunta a[],int o,int s);

void ordenaPdata(Pergunta p[], int N);

void ordenaPerg(Pergunta a[], int N);

GTree* getTree(Pergunta p);

Resposta getResp(Pergunta p, Date d);

long getIdp(Pergunta p);

DateTime getDatep(Pergunta p);

int getSize (Pergunta p);

Date getCreationDate(Pergunta p);


int getScorep(Pergunta p);


long getOwnerUserIDp(Pergunta p);


char* getTitle(Pergunta p);


char** getTags(Pergunta p);

int getNTags(Pergunta p);

int getFavoriteCount(Pergunta p);


int getAnswerCount(Pergunta p);


int getcommentCount(Pergunta p);

void setDateP(DateTime d, Pergunta p);

int comparePerguntas(Pergunta p1, Pergunta p2);

int pergunta_entre_datas(Pergunta p, DateTime b, DateTime e);

void setDateTp(DateTime d, Pergunta p);

//Pergunta copyPergunta(Pergunta p);

//Pergunta insertResposta(Pergunta p1, Resposta r1);

int searchTitle(char* word, char* title);

void setTitle (char* title, Pergunta p);

void freePergunta(Pergunta p);

gboolean freeAVL(Date d, Resposta r,gpointer data);

#endif
