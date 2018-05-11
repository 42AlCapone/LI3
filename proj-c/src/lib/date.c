#include <stdlib.h>
#include <string.h>
#include "date.h"


struct date {
  int day;
  int month;
  int year;
};

Date createDate(int day, int month, int year) {
    Date d = malloc(sizeof(struct date));
    d->day = day;
    d->month = month;
    d->year = year;
    return d;
}

int compare_dates (Date d1, Date d2)
{
    if (d1->year < d2->year)
       return -1;

    else if (d1->year > d2->year)
       return 1;

    if (d1->year == d2->year)
    {
         if (d1->month < d2->month)
              return -1;
         else if (d1->month > d2->month)
              return 1;
         else if (d1->day < d2->day)
              return -1;
         else if(d1->day > d2->day)
              return 1;
         else
              return 0;
    }
    return 0;
}

Date stringToDate(char* date){
  *(date + 10) = '\0';
  char* d = date+8;
  *(date +7) = '\0';
  char* m = date+5;
  *(date +4) = '\0';
  char* y = date;

  Date a = createDate(atoi(d),atoi(m),atoi(y));
  return a;
}

int get_day(Date d) {
    return d->day;
}

int get_month(Date d) {
    return d->month; //change to enum? (Acho que sim. Desta forma já garantimos a limitação necessária)
}

int get_year(Date d) {
    return d->year;
}
/*
Date parse_date(Date d, char* timestamp){

    int day, month, year;
    day = atoi(strtok(timestamp, "/"));
    month = atoi(strtok(timestamp+3, "/"));
    year = atoi(strtok(timestamp+6, "/"));

    d->day = day;
    d->month = month;
    d->year = year;

    return d;
}
*/
void free_date(Date d) {
    free(d);
}
