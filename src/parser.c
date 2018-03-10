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