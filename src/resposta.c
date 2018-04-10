#include<stdlib.h>
#include<string.h>
#include"time.h"


typedef struct resposta{
  long id;
  long parentID;
  struct time* creationTime;
  int score;
  long ownerUserID;
  int commentCount;
  int rate;
}Resposta;

Resposta* initResposta(long mainID, long parentID, int dy, int mnth, int yr, int hr, int min, int scr, long userID, int cmmtcount, int rt){
  Resposta* temp = malloc(sizeof(struct resposta));

  temp->creationTime = initTime(dy, mnth, yr, hr, min);

  temp->id = mainID;
  temp->parentID = parentID;
  temp->score = scr;
  temp->ownerUserID = userID;
  temp->commentCount = cmmtcount;
  temp->rate = rt;

  return temp;
}

int compareRespostas(Resposta* p1, Resposta* p2){
  /*
  Return values:
    - return 1 if date 1 happened before date 2;
    - return 2 if date 2 happened before date 1;
    - return 0 if they are the same;
  */
  return compareTimes(p1->creationTime, p2->creationTime);
}

Resposta* copyResposta(Resposta* r){
    Resposta* a = initResposta(r->id, r->parentID, r->creationTime->day, r->creationTime->month, r->creationTime->year, r->creationTime->hour, r->creationTime->minute, r->score, r->ownerUserID, r->commentCount, r->rate);

  return a;
}

void freeResposta(Resposta* r){
  freeTime(r->creationTime);
  free(r);
}
