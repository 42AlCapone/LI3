#include<stdlib.h>
#include<string.h>
#include<glib.h>
//#include "datetime.h"
#include "pergunta.h"
#include "resposta.h"
#include "../include/date.h"

struct pergunta{
  long id;
  Date creationTime;
  int score;
  long ownerUserID;
  char* title;
  char* tags;
  int favoriteCount;
  int answerCount;
  int commentCount;
  GTree* resp;
};

/*
Pergunta insertResposta(Pergunta p1, Resposta r1){
    p1->resp = g_tree_insert(p1->resp, GSIZE_TO_POINTER(), r1);
    return p1;
}
*/

Pergunta initPergunta(long mainID, int dy, int mnth, int yr, int scr, 
  long userID, char* ttl, char* tgs, int favcnt, int anscnt, int cmmtCnt, GTree* resp){

  Pergunta temp = malloc(sizeof(struct pergunta));

  temp->title = strdup(ttl);

  temp->tags = strdup(tgs);
  temp->creationTime = createDate(dy, mnth, yr);

  temp->id = mainID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->favoriteCount = favcnt;
  temp->answerCount = anscnt;
  temp->commentCount = cmmtCnt;

  temp->resp = g_tree_new((GCompareFunc)compare_dates);

  return temp;
}


long getIdp(Pergunta p){
  return p-> id;
}

/*
DateTime getCreationDate(Pergunta p){
  return p-> creationTime;
}
*/

int getScorep(Pergunta p){
  return p-> score;
}

long getOwnerUserIDp(Pergunta p){
  return p-> ownerUserID;
}

void getTitle(Pergunta p, char* new){
  strcpy(new , p-> title);
}

void getTags(Pergunta p, char* new){
  strcpy(new , p-> tags);
}

int getFavoriteCount(Pergunta p){
  return p-> favoriteCount;
}

int getAnswerCount(Pergunta p){
  return p-> answerCount ;
}

int getcommentCount(Pergunta p){
  return p-> commentCount;
}

int comparePerguntas(Pergunta p1, Pergunta p2){
  /*
  Return values:
    - return 1 if date 1 happened before date 2;
    - return 2 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  int i = compare_dates(p1->creationTime, p2->creationTime);
  return i;
}

/*
Pergunta copyPergunta(Pergunta p){
  Pergunta a = initPergunta(p->id, p->creationTime->day, p->creationTime->month, p->creationTime->year, p->creationTime->hour, p->creationTime->minute, p->score, p->ownerUserID, p->title, p->tags, p->favoriteCount, p->answerCount, p->commentCount);
  a->respostas = copyAVL(p->respostas);

  return a;
}
*/


void freePergunta(Pergunta p1){
    g_tree_destroy(p1->resp);
    free(p1->title);
    free(p1->tags);
    free_date(p1->creationTime);
    free(p1);
}

/*
int main(){

	Post* p1 = initPost('p', 1, ABSZERO, 9, 1, 1994, 21, 35, 100, 1, "sergio", "birthday", "the one", 2, 0);

	Post* p2 = initPost('r', 2, 1, 6, 1, 1990, 4, 20, 15, 2, "ricardo", "booo", "looser", 0, 2);

	printf("%d\n", comparePosts(p1, p2));
	Post* p3 = copyPost(p1);

	freePost(p1);
	freePost(p2);
	freePost(p3);

	return 0;
}
*/
