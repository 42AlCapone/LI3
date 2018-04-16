#ifndef _PERGUNTA_H_
#define _PERGUNTA_H_
#include "datetime.h"

typedef struct pergunta *Pergunta;



Pergunta initPergunta(long mainID, int dy, int mnth, int yr, int hr, int min, int scr, long userID, char* ttl, char* tgs, int favcnt, int anscnt, int cmmtCnt);


long getId(Pergunta p);


DateTime getCreationDate(Pergunta p);


int getScore(Pergunta p);


long getOwnerUserID(Pergunta p);


void getTitle(Pergunta p, char* new);


void getTags(Pergunta p, char* new);


int getFavoriteCount(Pergunta p);


int getAnswerCount(Pergunta p);


int getcommentCount(Pergunta p);


int comparePerguntas(Pergunta p1, Pergunta p2);


Pergunta copyPergunta(Pergunta p);


void freePergunta(Pergunta p);

#endif

