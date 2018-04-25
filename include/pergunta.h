#ifndef _PERGUNTA_H_
#define _PERGUNTA_H_
#include "date.h"
#include "resposta.h"

typedef struct pergunta *Pergunta;



Pergunta initPergunta(long mainID, char* date, int scr, long userID, char* ttl, char* tgs, int cCount);

Pergunta genPergunta();

void insertResposta(Pergunta p, Resposta r);

GTree* getTree(Pergunta p);

Resposta getResp(Pergunta p, Date d);

long getIdp(Pergunta p);

DateTime getDatep(Pergunta p);

Date getCreationDate(Pergunta p);


int getScorep(Pergunta p);


long getOwnerUserIDp(Pergunta p);


char* getTitle(Pergunta p);


void getTags(Pergunta p, char* new);

int getFavoriteCount(Pergunta p);


int getAnswerCount(Pergunta p);


int getcommentCount(Pergunta p);


int comparePerguntas(Pergunta p1, Pergunta p2);

int pergunta_entre_datas(Pergunta p, DateTime b, DateTime e);

//Pergunta copyPergunta(Pergunta p);

//Pergunta insertResposta(Pergunta p1, Resposta r1);

void freePergunta(Pergunta p);

#endif

