#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "interface.h"
#include "parser.h"
#include "users.h"
#include "pergunta.h"
#include "resposta.h"
#include "date.h"
#include "common.h"
#include "datetime.h"
#include "pair.h"
#include "list.h"


static gboolean iterateRate(Date d, Resposta r, Resposta best);

struct TCD_community{
    GHashTable* perguntas;
    GHashTable* respostas;
    GHashTable* users;
};


TAD_community init(){
	TAD_community comunidade = malloc(sizeof(struct TCD_community));
	comunidade->perguntas = g_hash_table_new(g_direct_hash, g_direct_equal);
	//comunidade->perguntas = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freePergunta);

	//comunidade->respostas = g_hash_table_new(g_direct_hash, g_direct_equal);
	comunidade->respostas = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freeResposta);

	//comunidade->users = g_hash_table_new(g_direct_hash, g_direct_equal);
	comunidade->users = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freeUser);
	return comunidade;
}




TAD_community load(TAD_community com, char* dump_path){
	int l = strlen(dump_path);
	char* postFile = malloc(10+l);
	char* userFile = malloc(10+l);


	postFile = strcpy(postFile,dump_path);
	userFile = strcpy(userFile, dump_path);

	postFile = strcat(postFile, "Posts.xml");
	userFile = strcat(userFile, "Users.xml");

	parseUser(com-> users, userFile);
	parsePost(com-> perguntas, com-> respostas, com-> users, postFile);

	return com;
}


//query 1
STR_pair info_from_post(TAD_community com, long id){

	char *title , *name;
	STR_pair pair = NULL;
	Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(id));

	if(p != NULL){
		User u = g_hash_table_lookup (com->users, GSIZE_TO_POINTER(getOwnerUserIDp(p)));
		title = getTitle( p );
		name = getName( u );
		pair = create_str_pair( title , name );
	}else{
		Resposta r = g_hash_table_lookup(com->respostas, GSIZE_TO_POINTER(id));
		Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(getParentID(r)));
		User u = g_hash_table_lookup(com->users, GSIZE_TO_POINTER(getOwnerUserID(r)));
		title = getTitle( p );
		name = getName( u );
		pair = create_str_pair( title, name );
	}
	return pair;
}

//query 2
LONG_list top_most_active(TAD_community com, int N){

	User u = genUser();
	long id=0;
	int i=0;

	User *ar = malloc(sizeof(User)*N);
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer u1 = &u;
	LONG_list list = create_list(N);

	g_hash_table_iter_init (&iter, com->users);
	while (g_hash_table_iter_next (&iter,id1,u1)){

    		if (i==0){
    			ar[0] = u;
    			i++;
    		}
    			if(i<N){
    				ar[i] = u;
    				ordenaUser(ar,i);
    				i++;
    			}
    			else if(getNrPosts(u)>getNrPosts(ar[i-1])){
    				ar[i-1] = u;
    				ordenaUser(ar,i-1);

    			}
 			}

 	for(i=0;i<N;i++){
    	set_list(list,i,getId(ar[i]));
    }
    printf("Query 2-Top Users posts\n");
   	for(i=0;i<N;i++){
   		printf("%ld\n",get_list(list,i));
   	}
 	return list;


}

//query 3
LONG_pair total_posts(TAD_community com, Date begin, Date end){

	long rCount, pCount;
	rCount=pCount=0;

	long id;
	id=0;
	Pergunta p = genPergunta();
	Resposta r = genResposta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateT(d,r);

	LONG_pair pair;
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer p1 = &p;
	gpointer r1 = &r;


	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,p1))
    	if(compare_date_time_begin(begin,getDatep(p)) && compare_date_time_final(end,getDatep(p)))
			pCount++;


    g_hash_table_iter_init (&iter, com->respostas);
	while (g_hash_table_iter_next (&iter,id1,r1))
    	if(compare_date_time_begin(begin,getDateT(r)) && compare_date_time_final(end,getDateT(r)))
    		rCount++;

    pair = create_long_pair(pCount,rCount);

    printf("Query 3\n");
    printf("%ld---%ld\n",get_fst_long(pair),get_snd_long(pair));

 	return pair;
}


//query 4
LONG_list questions_with_tag(TAD_community com, char* tag, Date begin, Date end){
	int i = 0, j,f,realoc=2;
	long id = 0;

	Pergunta p = genPergunta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateP(d,p);
	Pergunta *ar = malloc(sizeof(Pergunta)*20);

	char **tags;
	LONG_list list = create_list(20);
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,p1)){
    	if(compare_date_time_begin(begin,getDatep(p)) && compare_date_time_final(end,getDatep(p))){
    		tags = getTags(p);
    		for(j=0;j<getNTags(p);j++){
    			if(strcmp(tag,tags[j])==0){
    				if(i<getlSize(list)){
    					ar[i] = p;
    					ordenaPdata(ar,i);
    					i++;
    				}
    				else{
    					ar = realloc(ar,20*realoc*sizeof(Pergunta));

    					reallocLlist(list);
    					ar[i] = p;
    					ordenaPdata(ar,i);
    					i++;
    					realoc++;
    				}

    			}

    		}
    	}
    }


    for(f=0;f<i;f++){
    	set_list(list,f,getIdp(ar[f]));
    }

    printf("Query 4-Top answers\n");
   	for(f=0;f<i;f++){
   		printf("%ld\n",get_list(list,f));
   	}
 	return list;
}





//query 5

void insert_perg_by_oldest(Pergunta p, long last_posts[], int day[], int month[], int year[]){
	for(int i = 0; i < 9; i++){
		last_posts[i] = last_posts[i+1];
		day[i] = day[i+1];
		month[i] = month[i+1];
		year[i] = year[i+1];
	}

	last_posts[9] = getIdp(p);
	day[9] = getDay(getDatep(p));
	month[9] = getMonth(getDatep(p));
	year[9] = getYear(getDatep(p));
}

int cmp_dates(int d1, int m1, int y1, int d2, int m2, int y2){
	if(y1 > y2) return 1;
	if(m1 > m2) return 1;
	if(d1 > d2) return 1;
	return 0;
}

void insert_resp_by_oldest(Resposta r, long last_posts[], int day[], int month[], int year[]){
	int d = getDay(getDateT(r));
	int m = getMonth(getDateT(r));
	int y = getYear(getDateT(r));
	int i = 0;
	int pf = 0;
	while(i < 10 && pf == 0){
		if(cmp_dates(d, m, y, day[i], month[i], year[i]) == 0){
			pf = 1;
		}
		i++;
	}
	i--;
	for(int j = 0; j < i-1; j++){
		last_posts[j] = last_posts[j+1];
		day[j] = day[j+1];
		month[j] = month[j+1];
		year[j] = year[j+1];
	}
	if(i > 0){
		last_posts[i-1] = getIdr(r);
		day[i-1] = d;
		month[i-1] = m;
		year[i-1] = y;
	}

}


USER get_user_info(TAD_community com, long id){

	char* bio;
	long idp;
	long idr;
	long last_posts[10];
	int day[10];
	int month[10];
	int year[10];
	int fillup = 0;

	for(int i = 0; i < 10; i++){
		day[i] = 0;
		month[i] = 0;
		year[i] = 0;
	}

	USER user = NULL;
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(id));
	bio = getBio(u);
	printf("%s\n", bio);

	GHashTableIter iter_perg;
	Pergunta p = genPergunta();
	gpointer id_perg = &idp;
	gpointer p1 = &p;

	GHashTableIter iter_resp;
	Resposta r = genResposta();
	gpointer id_resp = &idr;
	gpointer r1 = &r;

	g_hash_table_iter_init(&iter_perg, com->perguntas);
	g_hash_table_iter_init(&iter_resp, com->respostas);

	while(g_hash_table_iter_next(&iter_perg, id_perg, p1)){
		if(fillup < 10 && getOwnerUserIDp(p) == id){
			last_posts[fillup] = getIdp(p);
			day[fillup] = getDay(getDatep(p));
			month[fillup] = getMonth(getDatep(p));
			year[fillup] = getYear(getDatep(p));
			fillup++;
		}
		else if(getOwnerUserIDp(p) == id){
			insert_perg_by_oldest(p, last_posts, day, month, year);
		}
	}

	while(g_hash_table_iter_next(&iter_resp, id_resp, r1)){
		if(getOwnerUserID(r) == id){
			insert_resp_by_oldest(r, last_posts, day, month, year);
		}
	}

	for(int j = 0; j < 10; j++){
		printf("%ld:", last_posts[j]);
	}

	printf("\nBio: %s\n", bio);

	user = create_user(bio, last_posts);
	freeResposta(r);
	freeUser(u);
	return user;
}


/*
USER get_user_info(TAD_community com, long id) {


	char* bio;
	USER user = NULL;
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(id));
	bio = getBio(u);

	int i = 0;
	long id = 0;

	Resposta r = genResposta();
	Pergunta p = genPergunta();

	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateT(d,r);
	setDateP(d,p);
	Resposta *arR = malloc(sizeof(Resposta)*10);
	Pergunta *arP = malloc(sizeof(Pergunta)*10);


	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer r1 = &r;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->respostas);
	while (g_hash_table_iter_next (&iter,id1,r1)){
    	if(compare_date_time_begin(begin,getDateT(r)) && compare_date_time_final(end,getDateT(r))){
    		if (i==0){
    			ar[0] = r;
    			i++;
    		}
    			if(i<N){
    				ar[i] = r;
    				ordenaByScore(ar,i);
    				i++;
    			}
    			else if(getScore(r)>getScore(ar[i-1])){
    				ar[i-1] = r;
    				ordenaByScore(ar,i-1);

    			}
 			}
    	}


	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,r1)){
    	if(compare_date_time_begin(begin,getDateT(r)) && compare_date_time_final(end,getDateT(r))){
    		if (i==0){
    			ar[0] = r;
    			i++;
    		}
    			if(i<N){
    				ar[i] = r;
    				ordenaByScore(ar,i);
    				i++;
    			}
    			else if(getScore(r)>getScore(ar[i-1])){
    				ar[i-1] = r;
    				ordenaByScore(ar,i-1);

    			}
 			}
    	}


 	return list;

}
*/

//query 6
LONG_list most_voted_answers(TAD_community com, int N, Date begin, Date end){

	int i = 0;
	long id = 0;

	Resposta r = genResposta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateT(d,r);
	Resposta *ar = malloc(sizeof(Resposta)*N);

	LONG_list list = create_list(N);
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer r1 = &r;

	g_hash_table_iter_init (&iter, com->respostas);
	while (g_hash_table_iter_next (&iter,id1,r1)){
    	if(compare_date_time_begin(begin,getDateT(r)) && compare_date_time_final(end,getDateT(r))){
    		if (i==0){
    			ar[0] = r;
    			i++;
    		}
    			if(i<N){
    				ar[i] = r;
    				ordenaByScore(ar,i);
    				i++;
    			}
    			else if(getScore(r)>getScore(ar[i-1])){
    				ar[i-1] = r;
    				ordenaByScore(ar,i-1);

    			}
 			}
    	}

    for(i=0;i<N;i++){
    	set_list(list,i,getIdr(ar[i]));
    }
    printf("Query 6-Top answers\n");
   	for(i=0;i<N;i++){
   		printf("%ld\n",get_list(list,i));
   	}
 	return list;
}

//query 7
LONG_list most_answered_questions(TAD_community com, int N, Date begin, Date end){

	int i = 0;
	long id = 0;

	Pergunta p = genPergunta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateP(d,p);
	Pergunta *ar = malloc(sizeof(Pergunta)*N);

	LONG_list list = create_list(N);
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,p1)){
    	if(compare_date_time_begin(begin,getDatep(p)) && compare_date_time_final(end,getDatep(p))){
    		if (i==0){
    			ar[0] = p;
    			i++;
    		}
    			if(i<N){
    				ar[i] = p;
    				ordenaPerg(ar,i);
    				i++;
    			}
    			else if(getSize(p)>getSize(ar[i-1])){
    				ar[i-1] = p;
    				ordenaPerg(ar,i-1);

    			}
 			}
    	}

    for(i=0;i<N;i++){
    	set_list(list,i,getIdp(ar[i]));
    }
    printf("Query 7-Top resp/pergunta\n");

   	for(i=0;i<N;i++){
   		printf("%ld\n",get_list(list,i));
   	}

   	return list;
}


//query 8

LONG_list contains_word(TAD_community com, char* word, int N){

	int i = 0;
	long id = 0;

	Pergunta p = genPergunta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateP(d,p);
	setTitle("",p);
	Pergunta *ar = malloc(sizeof(Pergunta)*N);

	LONG_list list = create_list(N);
	GHashTableIter iter;
	gpointer id1 = &id;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,p1)){
    	if(searchTitle(word,getTitle(p))==1){
    		if (i==0){
    			ar[0] = p;
    			i++;
    		}
    			if(i<N){
    				ar[i] = p;
    				ordenaPdata(ar,i);
    				i++;
    			}
    			else if(compareDateTime(getDatep(p),getDatep(ar[i-1])) == 1){
    				ar[i-1] = p;
    				ordenaPdata(ar,i-1);

    			}
 			}
    	}

    for(i=0;i<N;i++){
    	set_list(list,i,getIdp(ar[i]));
    }
    printf("Query 8\n");

   	for(i=0;i<N;i++){
   		printf("%ld\n",get_list(list,i));
   	}

   	return list;

}


//query 10
long better_answer(TAD_community com, long id){
	Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(id));
	GTree* t = getTree(p);
	Resposta r = genResposta();
	g_tree_foreach(t,(GTraverseFunc)iterateRate,r);
	long idResp = getIdr(r);
    freePergunta(p);
	return idResp;
}

gboolean iterateRate(Date d, Resposta r, Resposta best){
	float rateR, rateBest;
	rateR = getRate(r);
	rateBest = getRate(best);
	if (rateR>rateBest){
		setId(getIdr(r),best);
		setRate(rateR,best);
	}
	return FALSE;
}


TAD_community clean(TAD_community com){
	if(!com)
		return com;
    //g_hash_table_foreach(com->perguntas, (GHFunc)freePergunta, NULL);
	g_hash_table_destroy(com-> perguntas);
	g_hash_table_destroy(com-> respostas);
	g_hash_table_destroy(com-> users);
	free(com);
	return  NULL;
}
/*
TAD_community clean(TAD_community com){
	if(!com)
		return com;

	long idp, idr, idu;

	Pergunta p = genPergunta();
	GHashTableIter iter1;
	gpointer id1 = &idp;
	gpointer p1 = &p;
	g_hash_table_iter_init (&iter1, com->perguntas);
	while (g_hash_table_iter_next (&iter1,id1,p1)){
		freePergunta(p);
	}

	Resposta r = genResposta();
	GHashTableIter iter2;
	gpointer id2 = &idr;
	gpointer r1 = &r;
	g_hash_table_iter_init (&iter2, com->perguntas);
	while (g_hash_table_iter_next (&iter2,id2,r1)){
		freeResposta(r);
	}

	User u = genUser();
	GHashTableIter iter3;
	gpointer id3 = &idu;
	gpointer u1 = &u;
	g_hash_table_iter_init (&iter3, com->perguntas);
	while (g_hash_table_iter_next (&iter3,id3,u1)){
		freeUser(u);
	}

	g_hash_table_destroy(com-> perguntas);
	g_hash_table_destroy(com-> respostas);
	g_hash_table_destroy(com-> users);
	free(com);
	return  NULL;

}
*/
