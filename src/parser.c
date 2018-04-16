#include "parser.h"
#include "user.h"
#include "resposta.h"
#include "pergunta.h"
#include <libxml/parser.h>
#include <libxml/xmlmemory.h>
#include <stdio.h>
#include <glib.h>


void parsePost(GHashTable* structPosts ,char *docname){
	xmlDocPtr doc = xmlParseFile(docname);
	if (doc == NULL) {
		printf("Document not parsed. \n");
		return -1;
	}
	xmlNodePtr cur = xmlDocGetRootElement(doc);
	if(!cur){
		printf("empty document\n");
		xmlFreeDoc(doc);
		return 0;
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
				fCount = atoi((char*)xmlGetProp(cur, "FavoriteCount"));
				printf("--------Question----------\n");
				printf("ID: %s\n", id);
				printf("Date: %s\n", date);
				printf("Score: %s\n", score);
				printf("OwnerUserId: %s\n", oid);
				printf("Title : %s\n", title);
				printf("Tags: %s\n", tags);
				printf("AnswerCount: %s\n", aCount);
			}else{
				id = atol((char*)xmlGetProp(cur, "Id"));
				pid = atol((char*)xmlGetProp(cur, "ParentId"));
				date = (char*)xmlGetProp(cur, "CreationDate");
				score = atoi((char*)xmlGetProp(cur, "Score"));
				oid = atol((char*)xmlGetProp(cur, "OwnerUserId"));
				cCount = atoi((char*)xmlGetProp(cur, "CommentCount"));
				printf("---------Response--------\n");
				printf("ID: %s\n", id);
				printf("ParentId: %s\n", pid);
				printf("Date: %s\n", date);
				printf("Score: %s\n", score);
				printf("OwnerUserId: %s\n", oid);
				printf("CommentCount: %s\n", cCount);
			}	
		}
	cur = cur -> next;
	}	
}


void parseUser(GHashTable* structUsers,char *docname){

	xmlDocPtr doc = xmlParseFile(docname);
	if (doc == NULL) {
		printf("Document not parsed. \n");
		return -1;
	}
	xmlNodePtr cur = xmlDocGetRootElement(doc);
	if(!cur){
		printf("empty document\n");
		xmlFreeDoc(doc);
		return 0;
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
		   	User u = initUser(u, id, rep, name, views, votDif);
		    g_hash_table_insert( structUsers, GSIZE_TO_POINTER(id), u);
	    }
	    cur = cur->next;
	}
 }
