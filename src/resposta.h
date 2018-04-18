#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

//#include"datetime.h"
#include "../include/date.h"

typedef struct resposta *Resposta;

Resposta initResposta(long mainID, long parentID, int dy, int mnth, int yr, int scr, long userID, int cmmtcount, int rt);

long getId(Resposta);

long getParentID(Resposta);

//DateTime getTime(Resposta);

int getScore(Resposta);

long getOwnerUserID(Resposta);

int getCommentCount(Resposta);

int getRate(Resposta);

int compareRespostas(Resposta, Resposta);


Resposta copyResposta(Resposta);

void freeResposta(Resposta);

#endif

