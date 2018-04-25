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


struct TCD_community{
    GHashTable* perguntas;
    GHashTable* respostas;
    GHashTable* users;
};

typedef struct querytres{
	DatePair par;
	long tot_perg;
	long tot_resp;
}QueryTres;

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
	
	/*
	char* date = mystrdup("2010-09-13T19:22:39.290");
	Pergunta p = g_hash_table_lookup(com->posts,GSIZE_TO_POINTER(1));
	Date d = stringToDate(date);
	Resposta r = getResp(p,d);
	printf("%d\n",getScore(r));
	*/
	/*
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(21));
	char *nome = getName(u);
	printf("%s\n",nome); 
	*/
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

//query 5 (inacabada)
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

/*
void iterTable(long id,Pergunta p, User){

}
*/

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

