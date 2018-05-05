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
#include "query.h"


// struct TCD_community com as estruturas do programa
struct TCD_community{
    GHashTable* perguntas; 	// Hashtable de perguntas
    GHashTable* respostas; 	// Hashtable de respostas
    GHashTable* users;		// Hashtable de users
};

// Função que inicializa as estruturas
TAD_community init(){
	TAD_community comunidade = malloc(sizeof(struct TCD_community));
	comunidade->perguntas = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freePergunta);
	comunidade->respostas = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freeResposta);
	comunidade->users = g_hash_table_new_full(g_direct_hash, g_direct_equal, NULL, (GDestroyNotify)freeUser);
	return comunidade;
}



// Função que insere os dados nas estruturas
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

	free(postFile);
	free(userFile);
	return com;
}


//query 1
STR_pair info_from_post(TAD_community com, long id){

	char *title=NULL , *name=NULL;
	STR_pair pair = NULL;
	Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(id));

	if(p != NULL){
		User u = g_hash_table_lookup (com->users, GSIZE_TO_POINTER(getOwnerUserIDp(p)));
		title = getTitle(p);
		name = getName(u);
		pair = create_str_pair(title,name);
	}else{
		Resposta r = g_hash_table_lookup(com->respostas, GSIZE_TO_POINTER(id));
		Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(getParentID(r)));
		User u = g_hash_table_lookup(com->users, GSIZE_TO_POINTER(getOwnerUserID(r)));
		title = getTitle(p);
		name = getName(u);
		if(title==NULL || name==NULL) return NULL;
		pair = create_str_pair(title,name);
	}
	return pair;
}

//query 2
LONG_list top_most_active(TAD_community com, int N){

	long id=0;
	int i=0;

	User u = genUser();
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
  	free(u);
  	free(ar);
 	return list;
}

//query 3
LONG_pair total_posts(TAD_community com, Date begin, Date end){

	long rCount, pCount;
	rCount=pCount=0;

	long id = 0;

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
    free(ar);
 	return list;
}



static long compDate_RespPer(int *flag, int *flagID,Pergunta p,Resposta r);
static int case0 (long idP, long idR);


//query 5
USER get_user_info(TAD_community com, long id) {

	char* bio;
	USER user = NULL;
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(id));
	bio = getBio(u);

	int i = 0, j = 0;
	long id_iter = 0;

	Resposta r = genResposta();
	Pergunta p = genPergunta();

	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateT(d,r);
	setDateP(d,p);
	Resposta *arR = malloc(sizeof(Resposta)*10);
	Pergunta *arP = malloc(sizeof(Pergunta)*10);


	GHashTableIter iter;
	gpointer id1 = &id_iter;
	gpointer r1 = &r;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->respostas);
	while (g_hash_table_iter_next (&iter,id1,r1)){
    	if(getOwnerUserID(r)==id){
    		if (i==0){
    			arR[0] = r;
    			i++;
    		}
    		if(i<10){
    			arR[i] = r;
   				ordenaRdata(arR,i);
   				i++;
   			}
   			else if(compareDateTime(getDateT(r),getDateT(arR[i-1]))){
   				arR[i-1] = r;
   				ordenaRdata(arR,i-1);
   			}
 		}
   	}

	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,id1,p1)){
    	if(getOwnerUserIDp(p)==id){
    		if (j==0){
    			arP[0] = p;
    			j++;
    		}
   			if(j<10){
   				arP[j] = p;
   				ordenaPdata(arP,j);
   				j++;
   			}
   			else if(compareDateTime(getDatep(p),getDatep(arP[j-1]))){
   				arP[j-1] = p;
   				ordenaPdata(arP,j-1);

   			}
 		}
  	}

    long posts[10];
    int c, perg, resp, flag, flagID;
    perg=0;
    resp=0;
    flag=2;
    flagID=2;

    if(i+j<10) return NULL;

    for (c=0;c<10;c++){
    	posts[c]=compDate_RespPer(&flag,&flagID,arP[perg],arR[resp]);

    	switch(flag){
    		case -1:
    			resp++;
    			break;
    		case 1:
    			perg++;
    			break;
    		case 0:
    			if (flagID) perg++;
    			else resp++;
    			break;
    	}

    }

<<<<<<< HEAD


=======
>>>>>>> b486c626e455b9ea8bee408a156dbea84181d646
    user = create_user(bio,posts);
 	return user;
}

// Função que verifica o caso de dois DateTimes iguais
static int case0 (long idP, long idR){
	if (idP>idR) return 1;
	else return 0;
}

// Função que compara os DateTime entre uma pergunta e resposta
static long compDate_RespPer(int *flag, int *flagID,Pergunta p,Resposta r){
	DateTime pd = getDatep(p);
	DateTime rd = getDateT(r);

	switch(*flag=compareDateTime(pd,rd)){
		case -1:
			return getIdr(r);
		case 1:
			return getIdp(p);
		case 0:
			*flagID=case0(getIdp(p),getIdr(r));
			if (*flagID) return getIdp(p);
			else return getIdr(r);
	}
	return 0;
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
    freeDateTime(d);
    free(ar);
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

    if(i<10) return NULL;

    for(i=0;i<N;i++){
    	set_list(list,i,getIdp(ar[i]));
    }
   	return list;
}
//query 9
/*
static gboolean iterateBoth(Date d, Resposta r, Query q);

LONG_list both_participated(TAD_community com, long id1, long id2, int N){
	long idp = 0;
	int i = 0;

	Query aux = initQuery(id1, id2, N, com->perguntas);
	Pergunta p = genPergunta();
	DateTime d = initDateTime(0,0,0,0,0,0);
	setDateP(d,p);
	GTree* t;
	LONG_list list = create_list(N);
	GHashTableIter iter;
	gpointer in = &idp;
	gpointer p1 = &p;

	g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,in,p1)){
		if( id1 == getOwnerUserIDp(p) ){
			setAux1(aux);
			t = getTree(p);
			g_tree_foreach(t , (GTraverseFunc)iterateBoth, aux);
		}else if(id2 == getOwnerUserIDp(p)){
			setAux2(aux);
			t = getTree(p);
			g_tree_foreach(t, (GTraverseFunc)iterateBoth, aux);
		} else{
			t = getTree(p);
			g_tree_foreach(t, (GTraverseFunc)iterateBoth, aux);
		}
		setQuery(aux);
	}
	N = getIndex(aux);
	Pergunta *ar = malloc(sizeof(Pergunta)*N);
	ar = getList(aux);

	for(i=0 ; i< getIndex(aux) ; i++){
		set_list(list,i,getIdp(ar[i]));
	}

   	for(i=0;i< getIndex(aux) ;i++){
   		printf("%ld\n",get_list(list,i));
   	}
   	freeQuery(aux);

   	return list;

}

static gboolean iterateBoth(Date d, Resposta r, Query q){
	if(getAux1(q)){
		if( getId2Q(q) == getOwnerUserID(r)){
			insertList(q , getParentID(r));
		}else return TRUE;
	}else if(getAux2(q)){
		if (getId1Q(q) == getOwnerUserID(r)){
			insertList(q , getParentID(r));
		} else return TRUE;
	}else{
		if(getId1Q(q) == getOwnerUserID(r)) setAux1(q);
		if(getId2Q(q) == getOwnerUserID(r)) setAux2(q);
		return FALSE;
	}
	return FALSE;
}
*/
static gboolean iterateBoth(Date d, Resposta r, Query q);

LONG_list both_participated(TAD_community com, long id1, long id2, int N){
    long idp = 0;
	int i = 0;

    Query aux = initQuery(id1, id2, N, com->perguntas);
    Pergunta p = genPergunta();
    GTree* t;
    GHashTableIter iter;
    gpointer in = &idp;
    gpointer p1 = &p;

    g_hash_table_iter_init (&iter, com->perguntas);
	while (g_hash_table_iter_next (&iter,in,p1)){
        if(id1 == getOwnerUserIDp(p)){
            setAux1(aux);
            t = getTree(p);
			g_tree_foreach(t , (GTraverseFunc)iterateBoth, aux);
        }
        else if(id2 == getOwnerUserIDp(p)){
            setAux2(aux);
            t = getTree(p);
            g_tree_foreach(t , (GTraverseFunc)iterateBoth, aux);
        }
        else{
            t = getTree(p);
            g_tree_foreach(t , (GTraverseFunc)iterateBoth, aux);
        }

        setQuery(aux);
    }

    int s = getIndex(aux);
    printf("index de aux final = %d\n", s);

    LONG_list list = create_list(s+1);

    printf("pergunta índice 0  em aux = %ld\n", getIDlist(aux, i));
	for(int z =0 ; z < s+1 ; z++){
        long temp = getIDlist(aux, z);
        printf("a imprimir id = %ld para list\n", temp);
		set_list(list,i,temp);
	}

   	for(i=0 ; i < s+1 ;i++){
   		printf("%ld\n",get_list(list,i));
   	}

    printf("Index = %d\n", getIndex(aux));
    printf("pergunta índice 0 em list = %ld\n", get_list(list, 0));
   	freeQuery(aux);

   	return list;
}

static gboolean iterateBoth(Date d, Resposta r, Query q){
    if(getAux1(q) == 1 && getOwnerUserID(r) == getId2Q(q)){
        insertList(q, getParentID(r));
        printf("encontrou pergunta valida de id %ld\n", getParentID(r));
        return TRUE;
    }
    else if(getAux2(q) == 1 && getOwnerUserID(r) == getId1Q(q)){
        insertList(q, getParentID(r));
        printf("encontrou pergunta valida de id %ld\n", getParentID(r));
        return TRUE;
    }
    else if(getAux1(q) == 0 && getOwnerUserID(r) == getId1Q(q)){
        setAux1(q);
    }
    else if(getAux2(q) == 0 && getOwnerUserID(r) == getId2Q(q)){
        setAux2(q);
    }
    return FALSE;
}

//query 10
static gboolean iterateRate(Date d, Resposta r, Resposta best);

long better_answer(TAD_community com, long id){
	Pergunta p = g_hash_table_lookup(com->perguntas, GSIZE_TO_POINTER(id));
	long idResp=0;

	if(p){
	GTree* t = getTree(p);
	Resposta r = genResposta();
	g_tree_foreach(t,(GTraverseFunc)iterateRate,r);
	idResp = getIdr(r);
	}
	return idResp;
}

// Função que ordena por rate no iterate da tree
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

// Função que realiza os frees no final do programa
TAD_community clean(TAD_community com){
	if(!com)
		return com;
    
	g_hash_table_destroy(com-> users);
	g_hash_table_destroy(com-> respostas);
	g_hash_table_destroy(com-> perguntas);
	free(com);
	return  NULL;
}
