#include "headers/users.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

struct user{
	int id;
	int rep;
	char *name;
	int views;
	int voteDif;
	int nrPosts;
};


User initUser(int id, int rep, char* name, int views, int voteDif, int nrPosts){
	User u = malloc(sizeof(struct user));
	u-> id = id;
	u-> rep = rep;
	u-> name = malloc(sizeof(name));
	u-> views = views;
	u-> voteDif= voteDif;
	u-> nrPosts = nrPosts;
	return u;
}

User copyUser(User u){
  User u1 = initUser(u->id,u->rep,u->name,u->views,u->voteDif,u->nrPosts);

  return u1;
}
	
void freeUser(User u){
	free(u->name);
	free(u);
}



/*
Cria e insere user
User newUser(int nId, int nRep, char* nName, int nViews, int nVoteDif, int nPosts){
	User u = initUser();
	u->id = nId;
	u->rep = nRep;
	u->name = strdup(nName);
	u->views = nViews;
	u->voteDif = nVoteDif;
	u->nrPosts = nPosts;
	return u;
}
*/



void getName(User u, char *new){
	strcpy(new, u-> name);
}

int getNrPosts(User u){
	return u-> nrPosts;
}

void incrNrPosts(User u){
	u-> nrPosts++;
}
int getId(User u){
 	return u->id;
 }