#include "chainUsers.h"
#include <stdlib.h>

struct chainusers{
    User u;
    ChainUsers* next;
};

ChainUsers initChain(User u1){
    ChainUsers temp = malloc(sizeof(struct chainusers));
    temp->u = copyUser(u1);
    temp->next = NULL;

    return temp;
}

ChainUsers addUser(ChainUsers c, User u1){
    User temp = copyUser(u1);
    ChainUsers begin = c;
    while(c->next != NULL){
        c = c->next;
    }
    c->u = temp;

    c = begin;

    return c;
}

ChainUsers copyChain(ChainUsers c){
    ChainUsers temp = initChain(c->u);
    ChainUsers tbegin = temp;
    ChainUsers cbegin = c;
    temp = temp->next;
    c = c->next;

    while(c != NULL){
        temp = initChain(c->u);
        temp = temp->next;
        c = c->next;
    }

    c = cbegin;
    temp = tbegin;

    return temp;
}

void freeChain(ChainUsers c){
    if(c == NULL){
        return;
    }
    freeUser(c->u);
    freeChain(c->next);
    free(c);
}
