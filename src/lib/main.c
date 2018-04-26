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
 	free_str_pair(r);
 	char tag[30] = "package-management";

 	int N = 10;
 	Date d1 = createDate(1,8,2014);
 	Date d2 = createDate(11,8,2014);
 	
 	
 	most_voted_answers(com, N, d1, d2);
	total_posts(com,d1,d2);
	top_most_active(com,N);

  questions_with_tag(com,tag,d1,d2);

  printf("--------------------------------\n");
  most_answered_questions(com,N,d1,d2);


  clean(com); 


  return 0;
} 
