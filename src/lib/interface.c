#include "../../include/interface.h"
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "../../include/parser.h"
#include <string.h>
#include "../../include/users.h"


struct TCD_community{
    GHashTable* posts;
    GHashTable* users;
};


TAD_community init(){
	TAD_community comunidade = malloc(sizeof(struct TCD_community));
	comunidade->posts = g_hash_table_new(g_direct_hash, g_direct_equal);
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
	parsePost(com-> posts,com-> users, postFile);
	
	User u = g_hash_table_lookup(com->users,GSIZE_TO_POINTER(21));
	char *nome = getName(u);
	printf("%s\n",nome); 
	
	return com;
}


TAD_community clean(TAD_community com){
	if(!com)
		return com;
	g_hash_table_destroy(com-> posts);
	g_hash_table_destroy(com-> users);
	free(com);
	return NULL;

}

