#include "./headers/user.h"
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


User initUser(){
	User u = malloc(sizeof(struct user));
	u-> id = 0;
	u-> rep = 0;
	u-> name = NULL;
	u-> views = 0;
	u-> voteDif= 0;
	u-> nrPosts = 0;
}

//Cria e insere user
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



/*
void getName(User u, char *new){
	strcpy(new, u-> name);
}

int getNrPosts(User u){
	return u-> nrPosts;
}

void incrNrPosts(User u){
	u-> nrPosts++;
}

void freeUser(User u){
	free(u);
}

*/