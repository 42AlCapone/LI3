#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

#include"datetime.h"
#include <stdio.h>
#include <glib.h>

typedef struct resposta *Resposta;

Resposta initResposta(long mainID, long parentID, char* date, int scr, long userID, int cmmtcount, float rt);

Resposta genResposta();

void swapResp(Resposta a[],int o,int s);

void ordenaByScore(Resposta a[],int N);

void ordenaRdata(Resposta r[], int N);

long getIdr(Resposta r);

long getParentID(Resposta r);

DateTime getDateT(Resposta r);

int getScore(Resposta r);

long getOwnerUserID(Resposta r);

int getCommentCount(Resposta r);

float getRate(Resposta r);

void setId(long id, Resposta r);

void setRate(float rate, Resposta r);

int compareRespostas(Resposta r1, Resposta r2);

void setDateT(DateTime d, Resposta r);

Resposta cloneR(Resposta r);

int resposta_entre_datas(Resposta r, DateTime b, DateTime e);

void freeResposta(Resposta r);

#endif
