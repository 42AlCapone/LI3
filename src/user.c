#include "./headers/user.h"


User initUser(int newId, int newRep, char *newName, int newViews, int newVoteDif, int newNrPosts){
	User new = (User)malloc(sizeof(struct User));
	u-> id = newId;
	u-> reputation = newRep;
	u-> name = strdup(newName);
	u-> vews = newViews;
	u-> voteDif= newVoteDif;
	u-> nrPosts = newNrPosts;
}

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

