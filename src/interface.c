#include "interface.h"
#include <glib.h>


struct TCD_community{
    GHashTable* posts;
    GHashTable* users;
};


TAD_istruct init(){
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
	parseUser(com-> users, pathPosts);
	parsePost(com-> posts, pathUsers);
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