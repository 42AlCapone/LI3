//#include <date.h>
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "../../include/interface.h"
#include "../../include/datetime.h"




int main(int argc, char **argv){
  	
  	long id =1, best;
 	TAD_community com = init();
 	load(com,argv[1]);
 	STR_pair r = info_from_post(com, id);
 	best = better_answer( com , id );
 	printf("%ld\n", best);
 	get_user_info(com, 51);
 	free_str_pair(r);
	
  	clean(com); 
  
  //DateTime a = 
  //printf("%d\n",getYear(a));
  //freeDateTime(a);

  return 0;




  /*Date d = createDate(1,2,2017);
  printf("Dia: %i\n", get_day(d));
  return 0;*/
} 
