//#include <date.h>
#include <stdio.h>
#include "parser.c"

int main(int argc, char **argv){
  int i;
  //for(i=1;i<argc;i++){
  	//parseXml(argv[i]);
  //}
  //parseUser("Users.xml");
  parsePost("Posts.xml");
  return 0;




  /*Date d = createDate(1,2,2017);
  printf("Dia: %i\n", get_day(d));
  return 0;*/
} 
