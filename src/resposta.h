#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

#include"datetime.h"


typedef struct resposta *Resposta;

Resposta initResposta(long, long, int, int, int, int, int, int, long, int, int);

long getId(Resposta);

long getParentID(Resposta);

DateTime getTime(Resposta);

int getScore(Resposta);

long getOwnerUserID(Resposta);

int getCommentCount(Resposta);

int getRate(Resposta);

int compareRespostas(Resposta, Resposta);

Resposta copyResposta(Resposta);

void freeResposta(Resposta);

#endif

