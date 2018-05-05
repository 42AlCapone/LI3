//#include <date.h>
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "interface.h"
#include "datetime.h"
#include "resposta.h"
#include "users.h"
#include <time.h>


int main(int argc, char **argv){


 	TAD_community com = init();
 	load(com,argv[1]);


  //query 1 - done
  printf("\nQuery 1--------------\n");
  long id_q1 = 801049;
  STR_pair r = info_from_post(com, id_q1);

  if(r){
  printf("%s\n",get_fst_str(r));
  printf("%s\n",get_snd_str(r));
  free_str_pair(r);
  }
  else printf("Não existe!");
  //-------------------------------------


  //query 2
  printf("\nQuery 2--------------\n");
  int N_q2 = 10;
  int i;
  LONG_list q2 = top_most_active(com,N_q2);

  for(i=0;i<N_q2;i++){
    printf("%ld\n",get_list(q2,i));
  }

  free_list(q2);


  //query 3
  printf("\nQuery 3--------------\n");
  Date q3_d1 = createDate(1,1,2014);
  Date q3_d2 = createDate(31,12,2014);

  LONG_pair q3 = total_posts(com,q3_d1,q3_d2);
  printf("Perguntas: %ld----------Respostas: %ld\n",get_fst_long(q3),get_snd_long(q3));
  free_long_pair(q3);
  free_date(q3_d1);
  free_date(q3_d2);

  //query 4
  printf("\nQuery 4-----------------------\n");
  char *tag = "nautilus";
  Date q4_d1 = createDate(1,1,2014);
  Date q4_d2 = createDate(31,1,2014);

  LONG_list q4 = questions_with_tag(com,tag,q4_d1,q4_d2);
  int f;
  int size4 = getlSize(q4);
  for(f=0;f<size4;f++){
    printf("%ld\n",get_list(q4,f));
  }
  free_list(q4);
  free_date(q4_d1);
  free_date(q4_d2);


  //query 5
  printf("\nQuery 5-----------------------\n");

  USER q5 = get_user_info(com, 15811);
  if(q5){
    long *posts = get_10_latest_posts(q5);
    printf("%s\n",get_bio(q5));
    for(f=0;f<10;f++){
      printf("%ld\n",posts[f]);
    }
  }
  else printf("Não existe!\n");
  free_user(q5);


  //query 6
  printf("\nQuery 6-----------------------\n");
  int q6_N = 5;
  Date q6_d1 = createDate(1,11,2015);
  Date q6_d2 = createDate(30,11,2015);

  LONG_list q6 = most_voted_answers(com, q6_N, q6_d1, q6_d2);
  int size6 = getlSize(q6);

  for(f=0;f<size6;f++){
    printf("%ld\n",get_list(q6,f));
  }
  free_list(q6);
  free_date(q6_d1);
  free_date(q6_d2);

  //query 7
  printf("\nQuery 7-----------------------\n");

  int q7_N = 10;
  Date q7_d1 = createDate(1,8,2014);
  Date q7_d2 = createDate(11,8,2014);

  LONG_list q7 = most_answered_questions(com,q7_N,q7_d1,q7_d2);
  int size7 = getlSize(q7);

  for(f=0;f<size7;f++){
    printf("%ld\n",get_list(q7,f));
  }
  free_list(q7);
  free_date(q7_d1);
  free_date(q7_d2);


  //query 8
  printf("\nQuery 8-----------------------\n");
  char *word = "kde";
  int q8_N=10;
  LONG_list q8 = contains_word(com,word,q8_N);

  if(q8){
    int size8 = getlSize(q8);
    for(f=0;f<size8;f++){
    printf("%ld\n",get_list(q8,f));
    }
  }
  else printf("Não existe!\n");
  free_list(q8);

  //query9
  printf("\nQuery 9-----------------------\n");
  long u1_q9 = 253;
  long u2_q9 = 455;
  int n_q9 = 5;
  LONG_list q9 = both_participated(com, u1_q9, u2_q9, n_q9);
  if(q9){
      int size9 = getlSize(q9);
      for(int z = 0; z < size9; z++){
          printf("%ld\n", get_list(q9, z));
      }
  }
  else printf("Não existe!\n");


  //query10
  printf("\nQuery 10-----------------------\n");
  long id_q10 = 5942;
  long best = better_answer(com,id_q10);
  if (best==0) printf("Não existe!\n");
  else printf("%ld\n",best);

  clean(com);


  return 0;
}
