#include "users.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <string.h>
#include "../../include/common.h"

struct user{ 
	long id;
	char* bio;
	int rep;
	char *name;
	int views;
	int voteDif;
	int nrPosts;
};

User genUser(){
	User u = malloc(sizeof(struct user));
	u-> id = 0;
	u-> bio = NULL; 
	u-> rep = 0;
	u-> name = NULL;
	u-> views = 0;
	u-> voteDif= 0;
	u-> nrPosts = 0;
	return u;
}

User initUser(long id, char* bio, int rep, char* name, int views, int voteDif, int nrPosts){
	User u = malloc(sizeof(struct user));
	u-> id = id;
	u-> bio = mystrdup(bio); 
	u-> rep = rep;
	u-> name = mystrdup(name);
	u-> views = views;
	u-> voteDif= voteDif;
	u-> nrPosts = nrPosts;
	return u;
}

User copyUser(User u){
  	User new = initUser(getId(u),getBio(u),getRep(u),getName(u),getViews(u),getVoteDif(u),getNrPosts(u));

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

char* getBio(User u){
	return u->bio;
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
