#include <stdlib.h>
#include <string.h>
#include <glib.h>
#include <stdbool.h>
#include "pergunta.h"
#include "resposta.h"
#include "common.h"
#include "datetime.h"

struct pergunta{
  long id;
  DateTime creationTime;
  int score;
  long ownerUserID;
  char* title;
  char** tags;
  int nTags;
  int commentCount;
  GTree* resp;      //avl com respetivas respostas
};


Pergunta genPergunta(){
  Pergunta temp = (Pergunta) malloc(sizeof(struct pergunta));

  temp->id = 0;
  temp->creationTime = NULL;
  temp->score = 0;
  temp->ownerUserID = 0;
  temp->title = NULL;
  temp->tags = NULL;
  temp->nTags = 0;
  temp->commentCount = 0;
  temp->resp = NULL;

  return temp;
}


Pergunta initPergunta(long mainID, char* date, int scr, long userID, char* ttl, char** tgs, int nTags, int cCount){

  Pergunta temp = malloc(sizeof(struct pergunta));

  temp->title = ttl;
  temp->tags = tgs;
  temp->nTags = nTags;
  temp->creationTime = stringToDateT(date);
  temp->id = mainID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->commentCount = cCount;
  temp->resp = g_tree_new((GCompareFunc)compareDateTime);
  
  return temp;
}


void insertResposta(Pergunta p, Resposta r){
  DateTime d = getDateT(r);
  GTree* t= getTree(p);
  g_tree_insert(t,d, r);
}


void swapPergunta(Pergunta a[],int o,int s) {
  Pergunta tmp;
  tmp=a[o];
  a[o]=a[s];
  a[s]=tmp;
}


void ordenaPdata(Pergunta p[], int N){
  int i = N;
  while (i>0 && compareDateTime(getDatep(p[i]),getDatep(p[i-1])) == 1){
    swapPergunta(p,i,i-1);
    i--;
  }
}


void ordenaPerg(Pergunta a[], int N){
  int i=N;
  while (i>0 && getSize(a[i])>getSize(a[i-1])){
    swapPergunta(a,i,i-1);
    i--;
  }
}

int searchTitle(char* word, char* title){
  if (strstr(title,word)!=NULL) return 1;
  else return 0;
}

int comparePerguntas(Pergunta p1, Pergunta p2){
  /*
  Return values:
    - return -1 if date 1 happened before date 2;
    - return 1 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  int i = compareDateTime(p1->creationTime, p2->creationTime);
  return i;
}


int pergunta_entre_datas(Pergunta p, DateTime b, DateTime e){
	// 1 = TRUE && 0 = FALSE
	if(compareDateTime(p->creationTime, b) == 1 && compareDateTime(p->creationTime, e) == -1){
		return 1;
	}

	else if(compareDateTime(p->creationTime, b) == 0 || compareDateTime(p->creationTime, e) == 0){
    return 1;
  }

  return 0;
}

void freePergunta(Pergunta p1){
    g_tree_destroy(p1->resp);
    free(p1->title);
    free(p1->tags);
    freeDateTime(p1->creationTime);
    free(p1);
}

// GETs
GTree* getTree(Pergunta p){
  if(p){
  GTree* tree = (GTree*) p->resp;
  return tree;  
  }
  return NULL;
}

Resposta getResp(Pergunta p, Date d){
  GTree* t = getTree(p);
  Resposta r = g_tree_lookup(t,d);
  return r;
}

long getIdp(Pergunta p){
  if(p){
    return p-> id;
  }
  return 0;
}

DateTime getDatep(Pergunta p){
  return p-> creationTime;
}

int getSize (Pergunta p){
  return (int) g_tree_nnodes(p->resp);
}

int getScorep(Pergunta p){
  return p-> score;
}

long getOwnerUserIDp(Pergunta p){
  return p-> ownerUserID;
}

char* getTitle(Pergunta p){
  if(p){
  return p-> title;  
  }
  return NULL;
}

char** getTags(Pergunta p){
  return p-> tags;
}

int getNTags(Pergunta p){
  return p->nTags;
}

// SETs
void setDateP(DateTime d, Pergunta p){
  p->creationTime = d;
}

void setTitle (char* title, Pergunta p){
  p->title = title;
}
