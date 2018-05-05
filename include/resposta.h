#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_

#include"datetime.h"
#include <stdio.h>
#include <glib.h>

// Estrutura de uma Resposta
typedef struct resposta *Resposta;

// Função que inicializa uma Resposta
Resposta initResposta(long mainID, long parentID, char* date, int scr, long userID, int cmmtcount, float rt);

// Função que cria uma Resposta vazia
Resposta genResposta();

// Função que faz a troca da posição de duas respostas num array
void swapResp(Resposta a[],int o,int s);

// Função que ordena um array de Respostas por score
void ordenaByScore(Resposta a[],int N);

// Função que ordena um array de Respostas por cronologia inversa
void ordenaRdata(Resposta r[], int N);

// Função que compara duas respostas e verifica qual a mais recente
int compareRespostas(Resposta r1, Resposta r2);

// Funçao que verifica se uma pergunta esta entre 2 datas
int resposta_entre_datas(Resposta r, DateTime b, DateTime e);

// Função que da free a uma resposta
void freeResposta(Resposta r);

// GETs
long getIdr(Resposta r);

long getParentID(Resposta r);

DateTime getDateT(Resposta r);

int getScore(Resposta r);

long getOwnerUserID(Resposta r);

int getCommentCount(Resposta r);

float getRate(Resposta r);

// SETs
void setId(long id, Resposta r);

void setRate(float rate, Resposta r);

void setDateT(DateTime d, Resposta r);


#endif
