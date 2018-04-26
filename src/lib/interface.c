#include "../../include/interface.h"
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "../../include/parser.h"
#include <string.h>
#include "../../include/users.h"
#include "../../include/pergunta.h"
#include "../../include/resposta.h"
#include "../../include/date.h"
#include "../../include/common.h"
#include "../../include/datetime.h"
#include "../../include/pair.h"
#include "../../include/list.h"



struct TCD_community{
    GHashTable* perguntas;
    GHashTable* respostas;
    GHashTable* users;
};

/*
typedef struct querytres{
	DatePair par;
	long tot_perg;
	long tot_resp;
}QueryTres;
*/

TAD_community init(){
	TAD_community comunidade = malloc(sizeof(struct TCD_community));
	comunidade->perguntas = g_hash_table_new(g_direct_hash, g_direct_equal);
	//comunidade->perguntas = g_hashtable_new_full(g_direct_hash, g_direct_equal, (GDestroyNotify)free, (GDestroyNotify)freePergunta);
	//função free para fazer free da key (não sei se é necessário) função sameID verifica se dois ID's são iguais

	comunidade->respostas = g_hash_table_new(g_direct_hash, g_direct_equal);
	//comunidade->respostas = g_hastable_new_full(g_direct_hash, g_direct_equal, (GDestroyNotify)free, (GDestroyNotify)freeResposta);

	comunidade->users = g_hash_table_new(g_direct_hash, g_direct_equal);
	//comunidade->users = g_hashtable_new_full(g_direct_hash, g_direct_equal, (GDestroyNotify)free, (GDestroyNotify)free_user);
	return comunidade;
}

static gboolean iterateRate(Date d, Resposta r, Resposta best);
static void swap(Resposta a[],int o,int s);
static void ordena(Resposta a[],int N);
static void ordenaUser(User a[],int N);
static void swapUser(User a[],int o,int s);
static void ordenaPdata(Pergunta p[], int N);
static void swapPergunta(Pergunta a[],int o,int s);

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
		printf("Title:%s\n", title);
		printf("User Name: %s\n", name);
		pair = create_str_pair( title , name );
	}else{
		Resposta r = g_hash_table_lookup(com->respostas, GSIZE_TO_POINTER(id));
		Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(getParentID(r)));
		User u = g_hash_table_lookup(com->users, GSIZE_TO_POINTER(getOwnerUserID(r)));
		title = getTitle( p );
		name = getName( u );
		printf("Title: %s\n", title );
		printf("User Name: %s\n", name );
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

/*
//query 3 total posts entre duas datas: perguntas e respostas separadamente

DateTime date_to_datetime(Date d){
	DateTime temp = initDateTime(get_day(d), get_month(d), get_year(d), 0, 0, 0);

	return temp;
}


gboolean iter_perguntas_para_total(long id, Pergunta p, QueryTres* resp){
	if(pergunta_entre_datas(p, getBegin(resp->par), getEnd(resp->par)) == 1){
		resp->tot_perg++;
	}
	return FALSE;
}

gboolean iter_respostas_para_total(long id, Resposta r, QueryTres* resp){
	if(resposta_entre_datas(r, getBegin(resp->par), getEnd(resp->par)) == 1){
		resp->tot_resp++;
	}
	return FALSE;
}

LONG_pair total_posts(TAD_community com, Date begin, Date end){
	DateTime b = date_to_datetime(begin);
	DateTime e = date_to_datetime(end);

	QueryTres* resp = malloc(sizeof(struct querytres));
	resp->par = initDatePair(b, e);
	resp->tot_perg = 0;
	resp->tot_resp = 0;

	LONG_pair par = NULL;
	
	g_hash_table_foreach(com->perguntas, (GHFunc)iter_perguntas_para_total, resp);
	
	g_hash_table_foreach(com->respostas, (GHFunc)iter_respostas_para_total, resp);

	par = create_long_pair(resp->tot_perg, resp->tot_resp);

	freeDatePair(resp->par);
	free(resp);
	freeDateTime(b);
	freeDateTime(e);

	return par;
}

//end of query 3
*/


//query 4
LONG_list questions_with_tag(TAD_community com, char* tag, Date begin, Date end){
	int i = 0, j,f;
	long id = 0;

	Pergunta p = genPergunta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateTp(d,p);
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
    					Pergunta *aux = realloc(ar,sizeof(Pergunta)*20);
    					if(aux) ar = aux;
    					reallocLlist(list);
    					ar[i] = p;
    					ordenaPdata(ar,i);
    					i++;
    				}

    			}

    		}
    	}
    }

    for(f=0;f<i;f++){
    	set_list(list,f,getIdp(ar[f]));	
    }
    printf("Query 6-Top answers\n");
   	for(f=0;f<i;f++){
   		printf("%ld\n",get_list(list,f));
   	}
   	printf("%d\n",i);
 	return list;
}




//query 5 FALTA ACABAR
USER get_user_info(TAD_community com, long id){
	
	char* bio;
	USER user = NULL;
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(id));
	bio = getBio(u);
	printf("%s\n", bio);
	
	//g_hash_table_foreach(com->perguntas,(GHFunc) iterTable,ids);
	//g_hash_table_foreach(com->respostas,(GHFunc) iterTable,ids);

return user;


}


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
    				ordena(ar,i);
    				i++;
    			}
    			else if(getScore(r)>getScore(ar[i-1])){
    				ar[i-1] = r;
    				ordena(ar,i-1);

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

static void swap(Resposta a[],int o,int s) {
	Resposta tmp = malloc(sizeof(Resposta));
	tmp=a[o];
	a[o]=a[s];
	a[s]=tmp;
}

static void swapUser(User a[],int o,int s) {
	User tmp = malloc(sizeof(User));
	tmp=a[o];
	a[o]=a[s];
	a[s]=tmp;
}
static void swapPergunta(Pergunta a[],int o,int s) {
	Pergunta tmp = malloc(sizeof(Pergunta));
	tmp=a[o];
	a[o]=a[s];
	a[s]=tmp;
}

static void ordena(Resposta a[],int N) {
	int i=N;
	while (i>0 && getScore(a[i])>getScore(a[i-1])) {
		swap(a,i,i-1);
		i--;
	}
}

static void ordenaUser(User a[],int N){
	int i=N;
	while (i>0 && getNrPosts(a[i])>getNrPosts(a[i-1])) {
		swapUser(a,i,i-1);
		i--;
	}
}

static void ordenaPdata(Pergunta p[], int N){
	int i = N;
	while (i>0 && compareDateTime(getDatep(p[i]),getDatep(p[i-1])) == 1){
		swapPergunta(p,i,i-1);
		i--;
	}
}

//query 10
long better_answer(TAD_community com, long id){
	Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(id));
	GTree* t = getTree(p);
	Resposta r = genResposta();
	g_tree_foreach(t,(GTraverseFunc)iterateRate,r);
	long idResp = getIdr(r);
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
	g_hash_table_destroy(com-> perguntas);
	g_hash_table_destroy(com-> respostas);
	g_hash_table_destroy(com-> users);
	free(com);
	return  NULL;

}

