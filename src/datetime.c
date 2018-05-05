#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "date.h"
#include "datetime.h"
#include "common.h"
#include <glib.h>

 
struct datetime{
  int day;       
  int month;      // Estrutura DateTime até ao seg
  int year;
  int hour;
  int minute;
  int seg;
};



DateTime initDateTime(int d, int m, int y, int h, int min, int seg){
  DateTime temp = malloc(sizeof(struct datetime));
  temp->day = d;
  temp->month = m;
  temp->year = y;
  temp->hour = h;
  temp->minute = min;
  temp->seg = seg;

  return temp;
}


int compareDateTime(DateTime d1, DateTime  d2){
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
  if (d1->seg < d2->seg){
    return -1;
  }
  if (d2->seg < d1->seg){
    return 1;
  }
  return 0;
}



int compare_date_time_begin (Date d1, DateTime d2)
{
    if (get_year(d1) > d2->year)
       return 0;

    else if (get_year(d1) <= d2->year){

        if (get_month(d1) > d2->month)
              return 0;
         else if (get_month(d1) <= d2->month){

              if (get_day(d1) > d2->day)
              return 0;
         else if(get_day(d1) <= d2->day)
              return 1;
         else
              return 0;


         }
    }

    return 0;
}



int compare_date_time_final (Date d1, DateTime d2)
{
    if (get_year(d1) < d2->year)
       return 0;

    else if (get_year(d1) >= d2->year){

        if (get_month(d1) < d2->month)
              return 0;
         
         else if (get_month(d1) >= d2->month){

              if (get_day(d1) < d2->day)
              return 0;
         else if(get_day(d1) >= d2->day)
              return 1;
         else
              return 0;
         
         }
    }
    return 0;
}


DateTime stringToDateT(char* date){
  int i = 0;
  char* token;
  char *y;
  char *m;
  char *d;
  char *hr;
  char* min;
  char* seg;

  token = strtok(date,"-");
  while (token != NULL && i<7){
    switch (i){
    case 0:
      y = mystrdup(token);
      break;
    case 1:
      m = mystrdup(token);
      break;
    case 2:
      d = mystrdup(token);
      break;
    case 4:
      hr = mystrdup(token);
      break;
    case 5:
      min = mystrdup(token);
      break;
    case 6:
      seg = mystrdup(token);
      break;
    }
    token = strtok(NULL, "-T:.");
    i++;
  }
  
  DateTime a = initDateTime(atoi(d),atoi(m),atoi(y),atoi(hr),atoi(min),atoi(seg));
  free(y);
  free(m);
  free(d);
  free(hr);
  free(min);
  free(seg);
  return a;
}

void freeDateTime(DateTime d){
  free(d);
}

// GETs
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

int getSegundos(DateTime d){
  return d->seg;
}