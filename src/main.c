//#include <date.h>
#include "../include/interface.h"





int main(int argc, char **argv){
  
  TAD_community com = init();
  load(com,argv[1]);

  clean(com);
  //parseUser("Users.xml");
  //parsePost("Posts.xml");
  return 0;




  /*Date d = createDate(1,2,2017);
  printf("Dia: %i\n", get_day(d));
  return 0;*/
} 

