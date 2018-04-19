#include"pergunta.h"
//#include"avlRespostas.h"
#include<stdlib.h>

typedef struct chainperguntas{
    struct pergunta* p;
    struct avlrespostas* t;
    struct chainperguntas* next;
} ChainPerguntas;

ChainPerguntas* initChain(Pergunta* p1){
    ChainPerguntas* temp = malloc(sizeof(struct chainperguntas));
    temp->p = copyPergunta(p1);
    temp->t = NULL;
    temp->next = NULL;

    return temp;
}

ChainPerguntas* addPergunta(ChainPerguntas* c, Pergunta* p1){
    Pergunta* temp = copyPergunta(p1);
    ChainPerguntas* begin = c;
    while(c->next != NULL){
        c = c->next;
    }
    c->p = temp;

    c = begin;

    return c;
}

ChainPerguntas* addResposta(ChainPerguntas* c, Resposta* r1){
    ChainPerguntas* begin = c;
    while(r1->parentID != c->p->id && c != NULL){
        c = c->next;
    }
    if(c == NULL){
        return c;
    }
    else{
        c->t = insert(c->t, copyResposta(r1));
    }

    c = begin;

    return c;
}

ChainPerguntas* copyChain(ChainPerguntas* c){
    ChainPerguntas* temp = initChain(c->p);
    ChainPerguntas* tbegin = temp;
    ChainPerguntas* cbegin = c;
    temp = temp->next;
    c = c->next;

    while(c != NULL){
        temp = initChain(c->p);
        temp->t = copyAVL(c->t);
        temp = temp->next;
        c = c->next;
    }

    c = cbegin;
    temp = tbegin;

    return temp;
}

void freeChain(ChainPerguntas* c){
    if(c == NULL){
        return;
    }
    freePergunta(c->p);
    freeAVL(c->t);
    freeChain(c->next);
    free(c);
}
