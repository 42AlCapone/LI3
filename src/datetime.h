#ifndef _DATETIME_H_
#define _DATETIME_H_

typedef struct datetime *DateTime;


DateTime initDateTime();


void inserDateTime(DateTime, int, int, int, int, int);


void deleteDateTime(DateTime);


void freeDateTime(DateTime);


int compareDateTimes(DateTime, DateTime);

#endif
