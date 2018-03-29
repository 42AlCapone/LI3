#include "./headers/user.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct User{
	int id;
	int reputation;
	char *name;
	int views;
	int voteDif:
	int nrPosts;
};

User initUser(int newId, int newRep, char *newName, int newViews, int newVoteDif){
	User new = (User)malloc(sizeof(struct User));
	User-> id = newId;
	User-> reputation = newRep;
	User-> name = strdup(newName);
	User-> vews = newViews;
	User-> voteDif= newVoteDif;
	User-> nrPosts = 0;
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

