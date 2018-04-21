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


struct TCD_community{
    GHashTable* perguntas;
    GHashTable* respostas;
    GHashTable* users;
};


TAD_community init(){
	TAD_community comunidade = malloc(sizeof(struct TCD_community));
	comunidade->perguntas = g_hash_table_new(g_direct_hash, g_direct_equal);
	comunidade->respostas = g_hash_table_new(g_direct_hash, g_direct_equal);
	comunidade->users = g_hash_table_new(g_direct_hash, g_direct_equal);
	return comunidade;
}



TAD_community load(TAD_community com, char* dump_path){
	int l = strlen(dump_path);
	char* postFile = malloc(10+l);
	char* userFile = malloc(10+l);


	postFile = strcpy(postFile,dump_path);
	userFile = strcpy(userFile, dump_path);

	postFile = strcat(postFile, "/Posts.xml");
	userFile = strcat(userFile, "/Users.xml");

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

TAD_community clean(TAD_community com){
	if(!com)
		return com;
	g_hash_table_destroy(com-> perguntas);
	g_hash_table_destroy(com-> respostas);
	g_hash_table_destroy(com-> users);
	free(com);
	return NULL;

}

