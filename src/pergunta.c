#include<stdlib.h>
#include<string.h>
#include<glib.h>
#include "datetime.h"
#include "pergunta.h"
#include "resposta.h"

struct pergunta{
  long id;
  DateTime creationTime;
  int score;
  long ownerUserID;
  char* title;
  char* tags;
  int favoriteCount;
  int answerCount;
  int commentCount;
  GTree* respostas;
};



Pergunta* initPergunta(long mainID, int dy, int mnth, int yr, int hr, int min, int scr, long userID, char* ttl, char* tgs, int favcnt, int anscnt, int cmmtCnt){
  Pergunta* temp = malloc(sizeof(struct pergunta));

  temp->title = malloc(sizeof(ttl));
  strcpy(temp->title, ttl);

  temp->tags = malloc(sizeof(tgs));
  strcpy(temp->tags, tgs);

  temp->creationTime = initTime(dy, mnth, yr, hr, min);

  temp->id = mainID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->favoriteCount = favcnt;
  temp->answerCount = anscnt;
  temp->commentCount = cmmtCnt;

  temp->respostas = g_tree_new_full((GCompareFunc)compareTimes, (GDestroyNotify)freeTime, (GDestroyNotify)freeResposta);

  return temp;
}


long getId(Pergunta p){
  return p-> id;
}

DateTime getCreationDate(Pergunta p){
  return p-> creationTime;
}

int getScore(Pergunta p){
  return p-> score;
}

long getOwnerUserID(Pergunta p){
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
  return compareDateTimes(p1->creationTime, p2->creationTime);
}

/*
Pergunta copyPergunta(Pergunta p){
  Pergunta a = initPergunta(p->id, p->creationTime->day, p->creationTime->month, p->creationTime->year, p->creationTime->hour, p->creationTime->minute, p->score, p->ownerUserID, p->title, p->tags, p->favoriteCount, p->answerCount, p->commentCount);
  a->respostas = copyAVL(p->respostas);

  return a;
}
*/

Pergunta insertResposta(Pergunta p1, Resposta r1){
    p1->respostas = g_tree_insert(p1->respostas, (gpointer)r1->creationTime, r1);
    return p1;
}

void freePergunta(Pergunta p1){
    g_tree_destroy(p1->respostas);
    free(p1->title);
    free(p1->tags);
    freeTime(p1->creationTime);
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
