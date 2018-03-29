#include "./headers/user.h"
#include <libxml/parser.h>
#include <libxml/xmlmemory.h>
#include <stdio.h>

int parseXml(char *docname){

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

	xmlNodePtr cur2 = cur->xmlChildrenNode;
	xmlAttr *atr;
	for(;cur2;cur2=cur2->next){
		atr = cur2->properties;
		if (!xmlStrcmp(cur2->name, (const xmlChar *)"row")){
			for(;atr;atr=atr->next){
				xmlChar* value = xmlNodeListGetString(doc,atr->children, 1);
				printf("%s|%s\n",atr->name,value);
				xmlFree(value); 

			}
		}
	}
	
	xmlFreeDoc(doc);
	return 0;

}

int parsePost(char *docname){
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

	xmlChar *id, /*type,*/ *title, *pid, *date, *score, *oid, *tags, *aCount, *cCount, *fCount;
	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
			if((!xmlStrcmp(xmlGetProp(cur , "PostTypeId"), (const xmlChar *) "1"))){
				id = xmlGetProp(cur, "Id");
				date = xmlGetProp(cur, "CreationDate");
				score = xmlGetProp(cur, "Score");
				oid = xmlGetProp(cur, "OwnerUserId");
				title = xmlGetProp(cur, "Title");
				tags = xmlGetProp(cur, "Tags");
				aCount = xmlGetProp(cur, "AnswerCount");
				fCount = xmlGetProp(cur, "FavoriteCount");
				printf("--------Question----------\n");
				printf("ID: %s\n", id);
				printf("Date: %s\n", date);
				printf("Score: %s\n", score);
				printf("OwnerUserId: %s\n", oid);
				printf("Title : %s\n", title);
				printf("Tags: %s\n", tags);
				printf("AnswerCount: %s\n", aCount);
				xmlFree(id);
				xmlFree(date);
				xmlFree(score);
				xmlFree(oid);
				xmlFree(title);
				xmlFree(tags);
				xmlFree(aCount);
			}else{
				id = xmlGetProp(cur, "Id");
				pid = xmlGetProp(cur, "ParentId");
				date = xmlGetProp(cur, "CreationDate");
				score = xmlGetProp(cur, "Score");
				oid = xmlGetProp(cur, "OwnerUserId");
				cCount = xmlGetProp(cur, "CommentCount");
				printf("---------Response--------\n");
				printf("ID: %s\n", id);
				printf("ParentId: %s\n", pid);
				printf("Date: %s\n", date);
				printf("Score: %s\n", score);
				printf("OwnerUserId: %s\n", oid);
				printf("CommentCount: %s\n", cCount);
				xmlFree(id);
				xmlFree(pid);
				xmlFree(date);
				xmlFree(score);
				xmlFree(oid);
				xmlFree(cCount);
			}	
		}
	cur = cur -> next;
	}	
}


// esta função ainda está em testes
int parseUser(char *docname){

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

	int id, rep, views, up, down, votDif, nrPosts = 0;
	char *name;
	xmlChar *idx, *repx, *namex, *viewsx, *upx, *downx;
	cur = cur -> xmlChildrenNode;
	while(cur != NULL){
		if ((!xmlStrcmp(cur->name, (const xmlChar *)"row"))) {
		   	idx = xmlGetProp(cur, "Id");
		   	repx = xmlGetProp(cur, "Reputation");
		   	namex = xmlGetProp(cur , "DisplayName");
		   	viewsx = xmlGetProp(cur, "Views");
		   	upx = xmlGetProp(cur, "UpVotes");
		   	downx = xmlGetProp(cur, "DownVotes");
		   	//em baixo meto os tipos certos
		   	id = atoi((char*) idx);
		   	rep = atoi((char*) repx);
		   	name = (char*) namex;
		   	views = atoi((char*) viewsx);
		   	up = atoi((char*) upx);
		   	down = atoi((char*) downx);
		   	votDif = up - down;
		   	User u = initUser(id, rep, name, views,votDif, nrPosts);
		   	// função para inserir user na hash
		    printf("ID: %d\n", u-> id);
		    printf("Reputation: %d\n", u-> reputation);
		    printf("Name: %s\n", u-> name);
		    printf("Views: %d\n", u-> views);
		    printf("votDif: %d\n", u-> voteDif);
		    freeUser(u);
		    xmlFree(idx);
		    xmlFree(repx);
		    xmlFree(namex);
		    xmlFree(viewsx);
		    xmlFree(upx);
		    xmlFree(downx);
	    }
	    cur = cur->next;
	}
 }
