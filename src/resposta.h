#ifndef _RESPOSTA_H_
#define _RESPOSTA_H_


typedef struct resposta *Resposta;

Resposta initResposta(long, long, int, int, int, int, int, int, long, int, int);

int compareResposta(Resposta, Resposta);

Resposta copyResposta(Resposta);

void freeResposta(Resposta);

#endif

