#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

//#include"datetime.h"
#include "../include/date.h"

typedef struct resposta *Resposta;

Resposta initResposta(long mainID, long parentID, Date date, int scr, long userID, int cmmtcount, int rt);

long getId(Resposta);

long getParentID(Resposta);

Date getTime(Resposta);

int getScore(Resposta);

long getOwnerUserID(Resposta);

int getCommentCount(Resposta);

int getRate(Resposta);

int compareRespostas(Resposta, Resposta);

Resposta copyResposta(Resposta);

void freeResposta(Resposta);

#endif
