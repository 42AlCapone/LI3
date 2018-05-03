#include <glib.h>
#include "parser.h"
#include "users.h"
#include "resposta.h"
#include "pergunta.h"
#include "date.h"
#include <libxml/parser.h>
#include <libxml/xmlmemory.h>
#include <stdio.h>
#include <string.h>
#include "interface.h"



char** parseTags(char* tags, int *nTags){
	char** parsed = (char**) malloc(5*sizeof(char*));
	char* token;
	token = strtok(tags,"><");
	int i = 0;
	while(token != NULL){
		parsed[i] = token;
		token = strtok(NULL,"><");
		i++;
	}
	*nTags = i;

	return parsed;
}


void parsePost(GHashTable* structPerguntas , GHashTable* structRespostas, GHashTable* structUsers ,char *docname){
	xmlDocPtr doc = xmlParseFile(docname);
	if (doc == NULL) {
		printf("Document not parsed. \n");
	}
	xmlNodePtr cur = xmlDocGetRootElement(doc);
	if(!cur){
		printf("empty document\n");
		xmlFreeDoc(doc);
	}

	int score, cCount, nTags;
	float rate; 
	long id , pid , oid;
	char *title, *tags, *date;
	char **tagsR;
	
	cur = cur -> xmlChildrenNode;
	User u;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
			if((!xmlStrcmp(xmlGetProp(cur ,(const xmlChar*) "PostTypeId"), (const xmlChar *) "1"))){
				id = atol((char*)xmlGetProp(cur,(const xmlChar*) "Id"));
				date = (char*)xmlGetProp(cur,(const xmlChar*) "CreationDate");
				score = atoi((char*)xmlGetProp(cur,(const xmlChar*) "Score"));
				oid = atol((char*)xmlGetProp(cur,(const xmlChar*) "OwnerUserId"));
				title = (char*)xmlGetProp(cur,(const xmlChar*) "Title");
				tags = (char*)xmlGetProp(cur,(const xmlChar*) "Tags");
				cCount = atoi((char*)xmlGetProp(cur,(const xmlChar*) "CommentCount"));
				
				tagsR = parseTags(tags, &nTags);
		
				Pergunta p = initPergunta(id, date, score, oid, title, tagsR, nTags, cCount);
		    	g_hash_table_insert(structPerguntas, GSIZE_TO_POINTER(id), p);
		    	u = g_hash_table_lookup(structUsers, GSIZE_TO_POINTER(oid));
		}else{
			if((!xmlStrcmp(xmlGetProp(cur ,(const xmlChar*) "PostTypeId"), (const xmlChar *) "2"))){
				id = atol((char*)xmlGetProp(cur,(const xmlChar *) "Id"));
				pid = atol((char*)xmlGetProp(cur,(const xmlChar *) "ParentId"));
				date = (char*)xmlGetProp(cur,(const xmlChar *) "CreationDate");
				score = atoi((char*)xmlGetProp(cur,(const xmlChar *) "Score"));
				oid = atol((char*)xmlGetProp(cur,(const xmlChar *) "OwnerUserId"));
				cCount = atoi((char*)xmlGetProp(cur,(const xmlChar *) "CommentCount"));
				rate = calcRate(structUsers,oid,score,cCount);
				

				Resposta r = initResposta(id, pid, date, score, oid, cCount, rate);
		   		Pergunta p = g_hash_table_lookup(structPerguntas,GSIZE_TO_POINTER(pid));
		   		g_hash_table_insert(structRespostas, GSIZE_TO_POINTER(id),r);
		    	
		   		if(p!=NULL){
		   			GTree* respostas = getTree(p);
	    			DateTime d = getDateT(r);
	    			g_tree_insert(respostas, d, r);
	    		}

	    		u = g_hash_table_lookup(structUsers, GSIZE_TO_POINTER(oid));
	    		incrNrPosts(u);
		    	}
			}	
		}
	cur = cur -> next;
	}
	xmlFreeDoc(doc);
	printf("Posts inseridos\n");	
}



float calcRate(GHashTable* structUsers, long oid, int score, int cCount){
  User u = g_hash_table_lookup(structUsers,GSIZE_TO_POINTER(oid));
  int repUser = getRep(u);
  return (float) (score*0.45) + (repUser*25) + (score*0.2) + (cCount*0.1);
}


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
	char *name, *bio;

	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
		   	id = atol((char*) xmlGetProp(cur,(const xmlChar*) "Id"));
		   	bio = (char*) xmlGetProp(cur ,(const xmlChar*) "AboutMe");
		   	rep = atoi((char*) xmlGetProp(cur,(const xmlChar*) "Reputation"));
		   	name = (char*) xmlGetProp(cur ,(const xmlChar*) "DisplayName");
		   	views = atoi((char*) xmlGetProp(cur,(const xmlChar *) "Views"));
		   	up = atoi((char*) xmlGetProp(cur,(const xmlChar *) "UpVotes"));
		   	down = atoi((char*) xmlGetProp(cur,(const xmlChar *) "DownVotes"));
		   	votDif = up - down;
		   	User u = initUser(id, bio, rep, name, views, votDif,nrPosts);
		    g_hash_table_insert(structUsers, GSIZE_TO_POINTER(id), u);
	    }
	    cur = cur->next;
	}
	xmlFreeDoc(doc);
	printf("Users inseridos\n");
 }
