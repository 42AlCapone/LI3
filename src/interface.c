#include "../include/interface.h"
#include <glib.h>
#include <stdio.h>
#include <stdlib.h>
#include "parser.h"
#include <string.h>
#include "users.h"


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
	char* postFile = "/Posts.xml";
	char* userFile = "/Users.xml";
	char* pathPosts = strcat(dump_path, postFile);
	char* pathUsers = strcat(dump_path, userFile);
	parseUser(com-> users, pathUsers);
	parsePost(com-> posts, pathPosts);
	
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

