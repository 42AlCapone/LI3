//#include <date.h>
#include "../../include/interface.h"





int main(int argc, char **argv){
  	long id = 7;
 	TAD_community com = init();
 	load(com,argv[1]);
 	STR_pair r = info_from_post(com, id);
 	free_str_pair(r);

  	clean(com);
  //parseUser("Users.xml");
  //parsePost("Posts.xml");
  return 0;




  /*Date d = createDate(1,2,2017);
  printf("Dia: %i\n", get_day(d));
  return 0;*/
} 
