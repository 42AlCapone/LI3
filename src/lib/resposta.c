#include <stdlib.h>
#include <string.h>
#include "../../include/resposta.h"
//#include "datetime.c"
//#include "pergunta.h"
#include <glib.h>
#include "../../include/date.h"
#include "../../include/users.h"
#include "../../include/interface.h"
#include <stdio.h>

struct resposta{
  long id;
  long parentID;
  DateTime creationTime;
  int score;
  long ownerUserID;
  int commentCount;
  float rate;
};

Resposta genResposta(){
  Resposta temp = (Resposta) malloc(sizeof(struct resposta));

  temp->creationTime = NULL;

  temp->id = 0;
  temp->parentID = 0;
  temp->score = 0;
  temp->ownerUserID = 0;
  temp->commentCount = 0;
  temp->rate = 0;

  return temp;
}

Resposta initResposta(long mainID, long parentID, char* date, int scr, long userID, int cmmtcount, float rt){
  Resposta temp = malloc(sizeof(struct resposta));

  temp->creationTime = stringToDateT(date);

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

DateTime getDateT(Resposta r){
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

float getRate(Resposta r){
	return r->rate;
}

void setId(long id,Resposta r){
  r->id = id; 
}
void setRate(float rate, Resposta r){
  r->rate = rate;
}

void setDateT(DateTime d, Resposta r){
  r->creationTime = d;
}

Resposta cloneR(Resposta r){
  Resposta ret = initResposta(getIdr(r),getParentID(r),"0000-00-00T00:00:00.000",getScore(r),getOwnerUserID(r),getCommentCount(r),getRate(r));
  setDateT(getDateT(r),ret);
  return ret;
}

int compareRespostas(Resposta r1, Resposta r2){
  /*
  Return values:
    - return -1 if date 1 happened before date 2;
    - return 1 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  return compareDateTime(r1->creationTime, r2->creationTime);
}
/*
Resposta copyResposta(Resposta r){
    Resposta a = initResposta(r->id, r->parentID, , r->score, r->ownerUserID, r->commentCount, r->rate);

  return a;
}*/

int resposta_entre_datas(Resposta r, DateTime b, DateTime e){
	// 1 = TRUE && 0 = FALSE
	if(compareDateTime(r->creationTime, b) == 1 && compareDateTime(r->creationTime, e) == -1){
		return 1;
	}

	else if(compareDateTime(r->creationTime, b) == 0 || compareDateTime(r->creationTime, e) == 0){
		return 1;
	}

	return 0;
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
