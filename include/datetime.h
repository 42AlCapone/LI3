#ifndef _DATETIME_H_
#define _DATETIME_H_
 
typedef struct datetime *DateTime;

typedef struct datepair *DatePair;


DateTime initDateTime(int d, int m, int y, int h, int min, int seg);

DatePair initDatePair(DateTime b, DateTime e);

int compareDateTime(DateTime d1, DateTime  d2);

DateTime stringToDateT(char* date);

int getDay(DateTime d);


int getMonth(DateTime d);


int getYear(DateTime d);


int getHour(DateTime d);


int getMinute(DateTime d);


int getSegundos(DateTime d);

DateTime getBegin(DatePair p);

DateTime getEnd(DatePair p);

void freeDateTime(DateTime d);

void freeDatePair(DatePair p);


#endif
