#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

//#include"datetime.h"
#include "date.h"

typedef struct resposta *Resposta;

Resposta initResposta(long mainID, long parentID, char* date, int scr, long userID, int cmmtcount, int rt);

long getIdr(Resposta r);

long getParentID(Resposta r);

Date getDate(Resposta r);

int getScore(Resposta r);

long getOwnerUserID(Resposta r);

int getCommentCount(Resposta r);

int getRate(Resposta r);

int compareRespostas(Resposta r1, Resposta r2);

Resposta copyResposta(Resposta r);

void freeResposta(Resposta r);

#endif
