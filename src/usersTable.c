#include "usersTable.h"
#include <stdlib.h>
#include <stdio.h>


struct usertable{
	ChainUsers* table;
	int usage;
	int ocup;
	int max;
};

UserTable initTable(int size){
	UserTable t = malloc(sizeof(struct usertable));
	t->table = malloc(sizeof(struct chainusers*)*size);
	t->usage = 0;
	t->ocup = 0;
	t->max = size;
	int i;
	for(i = 0; i < size; i++){
		t->table[i] = NULL;
	}

	return t;
}

int hashUser(User u, int m){
		return getId(u)%m;
	}


int hash(long id, int size){
	return (int)id % size;
}

UserTable buffup (UserTable tab, int size){
	UserTable temp = initTable(size);
	int i;
	for(i = 0; i < tab->max; i++){
		if(tab->table[i] != NULL){
			temp->table[hashUser(tab->table[i]->u, temp->max)] = tab->table[i];
		}

	}
	free(tab);
	return temp;
}


UserTable insertUser(UserTable tab, User u){
	if(tab->usage > 80){
		tab = buffup(tab, tab->max*2);
	}
	if(tab->table[hashUser(u, tab->max)] == NULL){
		tab->table[hashUser(u, tab->max)] = initChain(copyUser(u));
		tab->ocup++;
		tab->usage = (tab->ocup / tab->max)*100;
	}
	else{
		tab->table[hashUser(u, tab->max)] = addUser(tab->table[hashUser(u, tab->max)], u);
	}

	return tab;
}

/*
userTable* deleteUser(PostTable* t, User* u){
	if(hash(u) > t->max){
		return t;
	}
	if(t->table[hash(u)] == NULL){
		return t;
	}
	t->table[hash(u)] = delete(t->table[hash(u)], u);

	return t;
}
*/

void freeTable(UserTable t){
	int i;
	for(i = 0; i < t->max; i++){
		if(t->table[i] != NULL){
			freeChain(t->table[i]);
		}
	}
	free(t);
}
