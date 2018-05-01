//#include <date.h>
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "../../include/interface.h"
#include "../../include/datetime.h"




int main(int argc, char **argv){
  	
  long id =796430;
 	TAD_community com = init();
 	load(com,argv[1]);

  //best = better_answer( com , id );
 
  
  char tag[30] = "package-management";

  int N = 10;
  Date d1 = createDate(1,3,2013);
  Date d2 = createDate(31,3,2013);
  
  questions_with_tag(com,tag,d1,d2);


 	STR_pair r = info_from_post(com, id);
  char *title = get_fst_str(r);
  char *name = get_snd_str(r);
  printf("%s\n",title);
  printf("%s\n",name);
 	free_str_pair(r);

  top_most_active(com,N);
printf("--------------------\n");

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
