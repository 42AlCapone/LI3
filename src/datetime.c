#include<stdlib.h>
#include "datetime.h"

struct datetime{
  int day;
  int month;
  int year;
  int hour;
  int minute;
};

DateTime initDateTime(int d, int m, int y, int h, int min){
  DateTime temp = malloc(sizeof(struct datetime));
  temp->day = d;
  temp->month = m;
  temp->year = y;
  temp->hour = h;
  temp->minute = min;

  return temp;
}
int getDay(DateTime d){
    return d->day;
}

int getMonth(DateTime d){
    return d->month;
}

int getYear(DateTime d){
    return d->year;
}

int getHour(DateTime d){
    return d->hour;
}

int getMinute(DateTime d){
    return d->minute;
}

void freeDateTime(DateTime d){
  free(d);
}

int compareDateTimes(DateTime d1, DateTime  d2){
  /*
  Return values:
    - return -1 if date 1 happened before date 2;
    - return 1 if date 2 happened before date 1;
    - return 0 if they are the same;
  */

  if(d1->year < d2->year){
    return -1;
  }
  if(d2->year < d1->year){
    return 1;
  }
  if(d1->month < d2->month){
    return -1;
  }
  if(d2->month < d1->month){
    return 1;
  }
  if(d1->day < d2->day){
    return -1;
  }
  if(d2->day < d1->day){
    return 1;
  }
  if(d1->hour < d2->hour){
    return -1;
  }
  if(d2->hour < d1->hour){
    return 1;
  }
  if(d1->minute < d2->minute){
    return -1;
  }
  if(d2->minute < d1->minute){
    return 1;
  }

  return 0;
}

/*
int main(){
	int d1 = 9;
	int m1 = 1;
	int y1 = 1994;
	int h1 = 21;
	int min1 = 35;

	int d2 = 6;
	int m2 = 1;
	int y2 = 1990;
	int h2 = 4;
	int min2 = 20;

	Time* t1 = initTime(d1, m1, y1, h1, min1);

	Time* t2 = initTime(d2, m2, y2, h2, min2);

	printf("%d\n", compareTimes(t1, t2));

	freeTime(t1);
	freeTime(t2);

	return 0;
}
*/