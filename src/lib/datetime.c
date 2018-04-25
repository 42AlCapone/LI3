#include <stdlib.h>
#include "datetime.h"
#include <stdio.h>
#include <string.h>
#include "../../include/common.h"


struct datetime{
  int day;
  int month;
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

//compara Date com DateTime final
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


//compara Date com DateTime begin
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
/*
  token = strtok(NULL,"T");

  //data year,month,day
  token = strtok(data,"-");
  token = strtok(NULL,"-");
  token = strtok(NULL,"-");
  
  //time hour,min,seg
  token = strtok(time,":");
  token = strtok(NULL,":");
  token = strtok(NULL,":");

  *(date + 19) = '\0';
  char* seg = date+17;
  *(date + 16) = '\0';
  char* min = date + 14;
  *(date + 13) = '\0';
  char* hr = date+11;
  *(date + 10) = '\0';
  char* d = date+8;
  *(date +7) = '\0';
  char* m = date+5;
  *(date +4) = '\0';
  char* y = date;
*/


  DateTime a = initDateTime(atoi(d),atoi(m),atoi(y),atoi(hr),atoi(min),atoi(seg));
  return a;
  
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

int getSegundos(DateTime d){
  return d->seg;
}

void freeDateTime(DateTime d){
  free(d);
}

/*
int main(){
	int d1 = 9;
	int m1 = 1;
	int y1 = 1994;
	int h1 = 21;
	int min1 = 35;
  int seg1 = 20;

	int d2 = 6;
	int m2 = 1;
	int y2 = 1990;
	int h2 = 4;
	int min2 = 20;
  int seg2 = 20;

	DateTime t1 = initDateTime(d1, m1, y1, h1, min1, seg1);

	DateTime t2 = initDateTime(d2, m2, y2, h2, min2, seg2);

	printf("%d\n", compareDateTime(t1, t2));

	freeDateTime(t1);
	freeDateTime(t2);

	return 0;
}
*/
