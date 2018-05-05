#ifndef _DATETIME_H_
#define _DATETIME_H_

#include "date.h"
#include <glib.h>

//Estrutura DateTime 
typedef struct datetime *DateTime;

// Função que cria um DateTime
DateTime initDateTime(int d, int m, int y, int h, int min, int seg);

// Função que compara dois DateTime
int compareDateTime(DateTime d1, DateTime  d2);

// Função que verifica se um DateTime d2 ocorre depois de um Date d1
int compare_date_time_final (Date d1, DateTime d2);

// Função que verifica se um DateTime d2 ocorre antes de um Date d1
int compare_date_time_begin (Date d1, DateTime d2);

// Função que faz parse da string date recebida do xml e torna-la num DateTime 
DateTime stringToDateT(char* date);

// Função de free de um DateTime
void freeDateTime(DateTime d);


// GETs
int getDay(DateTime d);

int getMonth(DateTime d);

int getYear(DateTime d);

int getHour(DateTime d);

int getMinute(DateTime d);

int getSegundos(DateTime d);

#endif
