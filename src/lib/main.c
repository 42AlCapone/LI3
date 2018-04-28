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
 	//char tag[30] = "package-management";

 	int N = 10;
 	Date d1 = createDate(1,7,2016);
 	Date d2 = createDate(31,7,2016);
 	
 	
  top_most_active(com,N);

  most_answered_questions(com,N,d1,d2);
  
  //questions_with_tag(com,tag,d1,d2);
  printf("--------------------------------\n");
  get_user_info(com, 15811);
  most_voted_answers(com, N, d1, d2);

  total_posts(com,d1,d2);
  contains_word(com,"glib",N);

  clean(com); 


  return 0;
} 
