#ifndef _USERS_H_
#define _USERS_H_


//Declaração da estrutura User
typedef struct user *User;

/* Função que inicializa toda uma nova estrutura User com todas a variveis preenchidas*/
User initUser(long id, char* bio, int rep, char* name, int views, int voteDif, int nrPosts);

// Função que copia um user e retorna-o
User copyUser(User u);

void swapUser(User a[],int o,int s);

void ordenaUser(User a[],int N);

/* recebe um User e liberta toda a memória utilizada pelo mesmo*/
void freeUser(User u);

/* recebe um User e devolve o seu Id*/
long getId(User u);

User genUser();

char* getBio(User u);

// recebe um User e retorna a sua rep
int getRep(User u);

/* recebe um User e retorna o name do User*/ 
char* getName(User u);

// recebe um User e retorna as views
int getViews(User u);

// recebe um User e retorna a sua diferença de votos
int getVoteDif(User u);

/* recebe um User e retorna o numero de posts desse mesmo User*/
int getNrPosts(User u);

/* recebe um User e incrementa dos seus posts*/
void incrNrPosts(User u);



#endif