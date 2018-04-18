#include <stdlib.h>
#include <string.h>
#include "resposta.h"
//#include "datetime.c"
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

Resposta initResposta(long mainID, long parentID, int dy, int mnth, int yr, int scr, long userID, int cmmtcount, int rt){
  Resposta temp = malloc(sizeof(struct resposta));

  temp->creationTime = createDate(dy, mnth, yr);

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

Date getTime(Resposta r){
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
  int i = compare_dates(p1->creationTime, p2->creationTime);
  return i;
}

Resposta copyResposta(Resposta r){
    Resposta a = initResposta(r->id, r->parentID, get_day(r->creationTime), get_month(r->creationTime), get_year(r->creationTime), r->score, r->ownerUserID, r->commentCount, r->rate);

  return a;
}




void freeResposta(Resposta r){
  free_date(r->creationTime);
  free(r);
}
