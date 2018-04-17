#include<stdlib.h>
#include<string.h>
#include"resposta.h"
#include"datetime.c"

struct resposta{
  long id;
  long parentID;
  DateTime creationTime;
  int score;
  long ownerUserID;
  int commentCount;
  int rate;
};

Resposta initResposta(long mainID, long parentID, int dy, int mnth, int yr, int hr, int min, int scr, long userID, int cmmtcount, int rt){
  Resposta temp = malloc(sizeof(struct resposta));

  temp->creationTime = initDateTime(dy, mnth, yr, hr, min);

  temp->id = mainID;
  temp->parentID = parentID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->commentCount = cmmtcount;
  temp->rate = rt;

  return temp;
}

long getId(Resposta r){
	return r->id;
}

long getParentID(Resposta r){
	return r->parentID;
}

DateTime getTime(Resposta r){
	return r->creationTime;
}

int getScore(Resposta r){
	return r->score;
}

long getOwnerUserID(Resposta r){
	return r->ownerUserID;
}

int getCommentCount(Resposta r){
	return r->commentCount;
}

int getRate(Resposta r){
	return r->rate;
}

int compareRespostas(Resposta p1, Resposta p2){
  /*
  Return values:
    - return -1 if date 1 happened before date 2;
    - return 1 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  return compareDateTimes(p1->creationTime, p2->creationTime);
}

Resposta copyResposta(Resposta r){
    Resposta a = initResposta(r->id, r->parentID, getDay(r->creationTime), getMonth(r->creationTime), getYear(r->creationTime), getHour(r->creationTime), getMinute(r->creationTime), r->score, r->ownerUserID, r->commentCount, r->rate);

  return a;
}

void freeResposta(Resposta r){
  freeDateTime(r->creationTime);
  free(r);
}
/*
int main(){

	Resposta r1 = initResposta(1, 1, 9, 1, 1994, 21, 35, 100, 1, 10, 5);
	Resposta r2 = initResposta(2, 1, 6, 1, 1990, 12, 20, 43, 2, 6, 1);
	Resposta r3 = copyResposta(r1);

	printf("compareRespostas = %d\n", compareRespostas(r1, r2));
	printf("compareRespostas = %d\n", compareRespostas(r1, r3));
	printf("compareRespostas = %d\n", compareRespostas(r2, r1));

	freeResposta(r1);
	freeResposta(r2);
	freeResposta(r3);

	return 0;
}
*/
