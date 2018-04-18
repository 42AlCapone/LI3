#include <stdlib.h>
#include <string.h>
#include "resposta.h"
//#include "datetime.c"
//#include "pergunta.h"
#include <glib.h>
#include "../include/date.h"

struct resposta{
  long id;
  long parentID;
  Date creationTime;
  int score;
  long ownerUserID;
  int commentCount;
  int rate;
};

Resposta initResposta(long mainID, long parentID, char* date, int scr, long userID, int cmmtcount, int rt){
  Resposta temp = malloc(sizeof(struct resposta));

  temp->creationTime = stringToDate(date);

  temp->id = mainID;
  temp->parentID = parentID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->commentCount = cmmtcount;
  temp->rate = rt;

  return temp;
}

long getIdr(Resposta r){
	return r->id;
}

long getParentID(Resposta r){
	return r->parentID;
}

Date getDate(Resposta r){
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

int compareRespostas(Resposta r1, Resposta r2){
  /*
  Return values:
    - return -1 if date 1 happened before date 2;
    - return 1 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  return compare_dates(r1->creationTime, r2->creationTime);
}
/*
Resposta copyResposta(Resposta r){
    Resposta a = initResposta(r->id, r->parentID, , r->score, r->ownerUserID, r->commentCount, r->rate);

  return a;
}*/




void freeResposta(Resposta r){
  free_date(r->creationTime);
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
