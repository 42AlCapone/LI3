#include "parser.h"
#include "users.h"
//#include "resposta.h"
//#include "pergunta.h"
#include <libxml/parser.h>
#include <libxml/xmlmemory.h>
#include <stdio.h>
#include <glib.h>
#include "../include/interface.h"

/*
void parsePost(GHashTable* structPosts ,char *docname){
	xmlDocPtr doc = xmlParseFile(docname);
	if (doc == NULL) {
		printf("Document not parsed. \n");
	}
	xmlNodePtr cur = xmlDocGetRootElement(doc);
	if(!cur){
		printf("empty document\n");
		xmlFreeDoc(doc);
	}

	int score , aCount, cCount, fCount;
	long id , pid , oid;
	char *title, *tags, *date;
	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
			if((!xmlStrcmp(xmlGetProp(cur , "PostTypeId"), (const xmlChar *) "1"))){
				id = atol((char*)xmlGetProp(cur, "Id"));
				date = (char*)xmlGetProp(cur, "CreationDate");
				score = atoi((char*)xmlGetProp(cur, "Score"));
				oid = atol((char*)xmlGetProp(cur, "OwnerUserId"));
				title = (char*)xmlGetProp(cur, "Title");
				tags = (char*)xmlGetProp(cur, "Tags");
				aCount = atoi((char*)xmlGetProp(cur, "AnswerCount"));
				cCount = atoi((char*)xmlGetProp(cur, "CommentCount"));
				fCount = atoi((char*)xmlGetProp(cur, "FavoriteCount"));
				printf("--------Question----------\n");
				printf("ID: %ld\n", id);
				printf("Date: %s\n", date);
				printf("Score: %d\n", score);
				printf("OwnerUserId: %ld\n", oid);
				printf("Title : %s\n", title);
				printf("Tags: %s\n", tags);
				printf("AnswerCount: %d\n", aCount);
				User u = initUser(id, rep, name, views, votDif,0);
		    	g_hash_table_insert(structUsers, GSIZE_TO_POINTER(id), u);			
			}else{
				id = atol((char*)xmlGetProp(cur, "Id"));
				pid = atol((char*)xmlGetProp(cur, "ParentId"));
				date = (char*)xmlGetProp(cur, "CreationDate");
				score = atoi((char*)xmlGetProp(cur, "Score"));
				oid = atol((char*)xmlGetProp(cur, "OwnerUserId"));
				cCount = atoi((char*)xmlGetProp(cur, "CommentCount"));
				printf("---------Response--------\n");
				printf("ID: %ld\n", id);
				printf("ParentId: %ld\n", pid);
				printf("Date: %s\n", date);
				printf("Score: %i\n", score);
				printf("OwnerUserId: %ld\n", oid);
				printf("CommentCount: %d\n", cCount);
				User u = initUser(id, rep, name, views, votDif,0);
		    	g_hash_table_insert(structUsers, GSIZE_TO_POINTER(id), u);
			}	
		}
	cur = cur -> next;
	}	
}
*/

void parseUser(GHashTable* structUsers,char *docname){

	xmlDocPtr doc = xmlParseFile(docname);
	if (doc == NULL) {
		printf("Document not parsed. \n");
	}
	xmlNodePtr cur = xmlDocGetRootElement(doc);
	if(!cur){
		printf("empty document\n");
		xmlFreeDoc(doc);
	}

	int rep, views, up, down, votDif, nrPosts = 0;
	long id;
	char *name;

	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
		   	id = atol((char*) xmlGetProp(cur, "Id"));
		   	rep = atoi((char*) xmlGetProp(cur, "Reputation"));
		   	name = (char*) xmlGetProp(cur , "DisplayName");
		   	views = atoi((char*) xmlGetProp(cur, "Views"));
		   	up = atoi((char*) xmlGetProp(cur, "UpVotes"));
		   	down = atoi((char*) xmlGetProp(cur, "DownVotes"));
		   	votDif = up - down;
		   	User u = initUser(id, rep, name, views, votDif,0);
		    g_hash_table_insert(structUsers, GSIZE_TO_POINTER(id), u);
	    }
	    cur = cur->next;
	}
	printf("Users inseridos\n");
 }
