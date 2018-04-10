#include<stdlib.h>
#include<string.h>
#include<stdio.h>
#include"resposta.h"

typedef struct avlrespostas{
  struct resposta* r;
  int height;
  struct avlrespostas *left, *right;
} AVLRespostas;
//___________________________________________________________
AVLRespostas* init(){
  AVLRespostas* t = malloc(sizeof(struct avlrespostas));
  t->r = NULL;
  t->height = 0;
  t->left = NULL;
  t->right = NULL;

  return t;
}

int totalHeight(AVLRespostas* t)
{
    int lh,rh;
    if(t==NULL)
        return(0);

    if(t->left==NULL)
        lh=0;
    else
        lh=1+t->left->height;

    if(t->right==NULL)
        rh=0;
    else
        rh=1+t->right->height;

    if(lh>rh)
        return(lh);

    return(rh);
}

AVLRespostas* rotateright(AVLRespostas* x)
{
    AVLRespostas* y;
    y=x->left;
    x->left=y->right;
    y->right=x;
    x->height = totalHeight(x);
    y->height = totalHeight(y);
    return(y);
}

AVLRespostas* rotateleft(AVLRespostas* x)
{
    AVLRespostas *y;
    y = x->right;
    x->right = y->left;
    y->left = x;
    x->height = totalHeight(x);
    y->height = totalHeight(y);

    return(y);
}

AVLRespostas* rr(AVLRespostas* t)
{
    t = rotateleft(t);
    return(t);
}

AVLRespostas* ll(AVLRespostas* t)
{
    t = rotateright(t);
    return(t);
}

AVLRespostas* lr(AVLRespostas* t)
{
    t->left = rotateleft(t->left);
    t = rotateright(t);

    return(t);
}

AVLRespostas* rl(AVLRespostas* t)
{
    t->right = rotateright(t->right);
    t = rotateleft(t);
    return(t);
}

int bf(AVLRespostas* t)
{
    int lh,rh;
    if(t == NULL)
        return(0);

    if(t->left==NULL)
        lh=0;
    else
        lh=1+t->left->height;

    if(t->right==NULL)
        rh=0;
    else
        rh=1+t->right->height;

    return(lh-rh);
}

AVLRespostas* insert(AVLRespostas* t, Resposta* r1)
{
    if(t==NULL)
    {
        t=(AVLRespostas*)malloc(sizeof(struct avlrespostas));
        t->r = copyResposta(r1);
        t->left=NULL;
        t->right=NULL;
    }
    else if(compareRespostas(t->r, r1) == 2)        // insert in right subtree
        {
            t->right=insert(t->right, r1);
            if(bf(t)==-2){
                if(compareRespostas(r1, t->right->r) == 2)
                    t = rr(t);
                else
                    t = rl(t);
	    }
        }
        else if(compareRespostas(r1, t->r) == 1)
        {
             t->left=insert(t->left, r1);
             if(bf(t) == 2){
                    if(compareRespostas(r1, t->left->r) == 1)
                        t = ll(t);
                    else
                        t = lr(t);
	     }
        }

        t->height=totalHeight(t);

        return(t);
}

AVLRespostas* delete(AVLRespostas* t, Resposta* r1)
{
    AVLRespostas* paux;

    if(t==NULL)
    {
        return NULL;
    }
    else if(compareRespostas(r1, t->r) == 2)        // delete in right subtree
        {
            t->right = delete(t->right, r1);
            if(bf(t) == 2){
                if(bf(t->left) >= 0)
                    t= ll(t);
                else
                    t = lr(t);
	    }
        }
        else if(compareRespostas(r1, t->r) == 1)
        {
                t->left = delete(t->left, r1);
	     if(bf(t) == -2){    //Rebalance during windup
                  if(bf(t->right)<=0)
                       t = rr(t);
                   else
                       t = rl(t);
	     }
         }
         else
            {
                //data to be deleted is found
                if(t->right!=NULL)
                {    //delete its inorder succesor
                    paux = t->right;

                    while(paux->left!= NULL)
                        paux = paux->left;

                    t->r = copyResposta(paux->r);
                    t->right = delete(t->right, paux->r);

                    if(bf(t) == 2){//Rebalance during windup
                        if(bf(t->left) >= 0)
                            t = ll(t);
                        else
                            t = lr(t);
		    }
                }
                else
                    return(t->left);
            }
    t->height=totalHeight(t);
    return(t);
}

AVLRespostas* copyAVL(AVLRespostas* t){
    AVLRespostas* temp;

    if(t == NULL){
        return NULL;
    }

    temp = init();
    temp->r = copyResposta(t->r);
    temp->left = copyAVL(t->left);
    temp->right = copyAVL(t->right);

    return temp;
}

void freeAVL(AVLRespostas* t){
  if(!t){
	return;
  }

  freeAVL(t->left);
  freeAVL(t->right);
  freeResposta(t->r);
  free(t);
}


//___________________________________________________________
/*
int main(){

	Post* p1 = initPost('p', 1, ABSZERO, 9, 1, 1990, 21, 35, 100, 1, "sergio", "birthday", "the one", 2, 0);

	Post* p2 = initPost('r', 2, 1, 6, 1, 1994, 17, 20, 15, 2, "ricardo", "booo", "looser", 0, 2);

	Post* p3 = initPost('r', 3, 1, 6, 1, 1993, 4, 20, 15, 2, "dude", "ahah", "muahha", 0, 2);

	PostTree* t = NULL;

	t = insert(t, p1);

	t = insert(t, p2);

	t = insert(t, p3);

	freeTree(t);
	freePost(p1);
	freePost(p2);
	freePost(p3);


	return 0;
}
*/
