#ifndef _DATETIME_H_
#define _DATETIME_H_
 
#include "date.h"

typedef struct datetime *DateTime;


DateTime initDateTime(int d, int m, int y, int h, int min, int seg);

int compareDateTime(DateTime d1, DateTime  d2);

int compare_date_time_final (Date d1, DateTime d2);

int compare_date_time_begin (Date d1, DateTime d2);

DateTime stringToDateT(char* date);

int getDay(DateTime d);


int getMonth(DateTime d);


int getYear(DateTime d);


int getHour(DateTime d);


int getMinute(DateTime d);


int getSegundos(DateTime d);


void freeDateTime(DateTime d);


#endif
