#include"avlRespostas.c"

AVLRespostas* init();
int totalHeight(AVLRespostas*);
AVLRespostas* rotateright(AVLRespostas*);
AVLRespostas* rotateleft(AVLRespostas*);
AVLRespostas* rr(AVLRespostas*);
AVLRespostas* ll(AVLRespostas*);
AVLRespostas* lr(AVLRespostas*);
AVLRespostas* rl(AVLRespostas*);
int bf(AVLRespostas* t);
AVLRespostas* insert(AVLRespostas*, Resposta*);
AVLRespostas* delete(AVLRespostas*, Resposta*);
void freeAVL(AVLRespostas*);
