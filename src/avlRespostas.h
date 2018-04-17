#ifndef _AVLRESPOSTAS_H_
#define _AVLRESPOSTAS_H_

#include"resposta.h"

typedef struct avlrespostas* AVLRespostas;

AVLRespostas init();

Resposta getResposta(AVLRespostas);

int getHeight(AVLRespostas);

int totalHeight(AVLRespostas);

AVLRespostas rotateright(AVLRespostas);

AVLRespostas rotateleft(AVLRespostas);

AVLRespostas rr(AVLRespostas);

AVLRespostas ll(AVLRespostas);

AVLRespostas lr(AVLRespostas);

AVLRespostas rl(AVLRespostas);

int bf(AVLRespostas t);

AVLRespostas insert(AVLRespostas, Resposta);

AVLRespostas delete(AVLRespostas, Resposta);

AVLRespostas copyAVL(AVLRespostas)

void freeAVL(AVLRespostas);

#endif
