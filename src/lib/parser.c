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

	
	int nTags;
	float rate; 
	char* id, *pid, *oid;
	char * score, *cCount;
	char *title, *tags, *date;
	char **tagsR;
	xmlChar* strTYPE, *strID, *strPID, *strDATE, *strSCORE, *strOID, *strTITLE, *strTAGS, *strCOUNT;

	cur = cur -> xmlChildrenNode;
	User u;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
			strTYPE=xmlGetProp(cur ,(const xmlChar*) "PostTypeId");

			if((!xmlStrcmp(strTYPE, (const xmlChar *) "1"))){
				strID = xmlGetProp(cur,(const xmlChar*) "Id");
				strDATE = xmlGetProp(cur,(const xmlChar*) "CreationDate");
				strSCORE = xmlGetProp(cur,(const xmlChar*) "Score");
				strOID = xmlGetProp(cur,(const xmlChar*) "OwnerUserId");
				strTITLE = xmlGetProp(cur,(const xmlChar*) "Title");
				strTAGS = xmlGetProp(cur,(const xmlChar*) "Tags");
				strCOUNT = xmlGetProp(cur,(const xmlChar*) "CommentCount");
				

				id = (char*) strID;
				date = (char*) strDATE;
				score = (char*) strSCORE;
				oid = (char*) strOID;
				title = (char*) strTITLE;
				tags = (char*) strTAGS;
				cCount = (char*) strCOUNT;


				tagsR = parseTags(tags, &nTags);
		
				Pergunta p = initPergunta(atol(id), date, atoi(score), atol(oid), title, tagsR, nTags, atoi(cCount));
		    	g_hash_table_insert(structPerguntas, GSIZE_TO_POINTER(atol(id)), p);
		    	u = g_hash_table_lookup(structUsers, GSIZE_TO_POINTER(atol(oid)));
		    	xmlFree(strID);
		    	xmlFree(strTAGS);
		    	xmlFree(strOID);
		    	xmlFree(strSCORE);
		    	xmlFree(strDATE);
		    	xmlFree(strCOUNT);
		    	
		}else{
			if((!xmlStrcmp(strTYPE, (const xmlChar *) "2"))){
				strID = xmlGetProp(cur,(const xmlChar *) "Id");
				strPID = xmlGetProp(cur,(const xmlChar *) "ParentId");
				strDATE = xmlGetProp(cur,(const xmlChar *) "CreationDate");
				strSCORE = xmlGetProp(cur,(const xmlChar *) "Score");
				strOID = xmlGetProp(cur,(const xmlChar *) "OwnerUserId");
				strCOUNT = xmlGetProp(cur,(const xmlChar *) "CommentCount");
				

				id = (char*) strID;
				pid = (char*) strPID;
				date = (char*) strDATE;
				score = (char*) strSCORE;
				oid = (char*) strOID;
				cCount = (char*) strCOUNT;
				rate = calcRate(structUsers,atol(oid),atoi(score),atoi(cCount));


				Resposta r = initResposta(atol(id), atol(pid), date, atoi(score), atol(oid), atoi(cCount), rate);
		   		Pergunta p = g_hash_table_lookup(structPerguntas,GSIZE_TO_POINTER(atol(pid)));
		   		g_hash_table_insert(structRespostas, GSIZE_TO_POINTER(atol(id)),r);
		    	
		   		if(p!=NULL){
		   			GTree* respostas = getTree(p);
	    			DateTime d = getDateT(r);
	    			g_tree_insert(respostas, d, r);
	    		}

	    		u = g_hash_table_lookup(structUsers, GSIZE_TO_POINTER(atol(oid)));
	    		incrNrPosts(u);
		    	xmlFree(strID);
		    	xmlFree(strPID);
		    	xmlFree(strOID);
		    	xmlFree(strSCORE);
		    	xmlFree(strDATE);
		    	xmlFree(strCOUNT);
		    	
		    	}

			}

			
			
		xmlFree(strTYPE);	
			
			
		}
	cur = cur -> next;
	}
	xmlFreeDoc(doc);
	printf("Posts inseridos\n");	
}



float calcRate(GHashTable* structUsers, long oid, int score, int cCount){
  User u = g_hash_table_lookup(structUsers,GSIZE_TO_POINTER(oid));
  if(u){

  int repUser = getRep(u);
  return (float) (score*0.45) + (repUser*25) + (score*0.2) + (cCount*0.1);
  }
  return 0;
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

	char* id, *rep, *views, *up, *down;
	int  votDif, nrPosts = 0;
	char *name, *bio;
	xmlChar* strID, *strBIO, *strREP,*strNAME, *strVIEWS, *strUP, *strDOWN;
	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
		   	strID = xmlGetProp(cur,(const xmlChar*) "Id");
		   	strBIO = xmlGetProp(cur ,(const xmlChar*) "AboutMe");
		   	strREP = xmlGetProp(cur,(const xmlChar*) "Reputation");
		   	strNAME = xmlGetProp(cur ,(const xmlChar*) "DisplayName");
		   	strVIEWS = xmlGetProp(cur,(const xmlChar *) "Views");
		   	strUP = xmlGetProp(cur,(const xmlChar *) "UpVotes");
		   	strDOWN = xmlGetProp(cur,(const xmlChar *) "DownVotes");

		   	id = (char*) strID;
		   	bio = (char*) strBIO;
			rep = (char*) strREP;
			name = (char*) strNAME;
			views = (char*) strVIEWS;
			up = (char*) strUP;
			down = (char*) strDOWN;
		   	votDif = atoi(up) - atoi(down);
		   	
		   	User u = initUser(atol(id), bio, atoi(rep), name, atoi(views), votDif,nrPosts);
		    g_hash_table_insert(structUsers, GSIZE_TO_POINTER(atol(id)), u);

		    xmlFree(strID);
		    xmlFree(strBIO);
		    xmlFree(strREP);
		    xmlFree(strNAME);
		   	xmlFree(strVIEWS);
		   	xmlFree(strUP);
		   	xmlFree(strDOWN);
	    }
	    cur = cur->next;
	}
	xmlFreeDoc(doc);
	printf("Users inseridos\n");
 }
