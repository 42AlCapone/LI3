#include "users.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <string.h>
#include "../../include/common.h"

struct user{
	long id;
	int rep;
	char *name;
	int views;
	int voteDif;
	int nrPosts;
};


User initUser(long id, int rep, char* name, int views, int voteDif, int nrPosts){
	User u = malloc(sizeof(struct user));
	u-> id = id;
	u-> rep = rep;
	u-> name = mystrdup(name);
	u-> views = views;
	u-> voteDif= voteDif;
	u-> nrPosts = nrPosts;
	return u;
}

User copyUser(User u){
  	User new = initUser(getId(u),getRep(u),getName(u),getViews(u),getVoteDif(u),getNrPosts(u));

  	return new;	
}
	
void freeUser(User u){
	free(u->name);
	free(u);
}

//gets
long getId(User u){
	return u->id;
}

int getRep(User u){
	return u->rep;
}

char* getName(User u){
	return u->name;
}

int getViews(User u){
	return u->views;
}

int getVoteDif(User u){
	return u->voteDif;
}

int getNrPosts(User u){
	return u->nrPosts;
}

void incrNrPosts(User u){
	u->nrPosts++;
}
