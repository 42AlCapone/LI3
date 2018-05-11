#ifndef _USERS_H_
#define _USERS_H_


// Declaração da estrutura User
typedef struct user *User;

// Função que cria um User vazio
User genUser();

// Função que inicializa toda uma nova estrutura User com todas a variveis preenchidas
User initUser(long id, char* bio, int rep, char* name, int views, int voteDif, int nrPosts);

// Função que copia um user e retorna-o
User copyUser(User u);

// Função que faz a troca da posição de dois users num array
void swapUser(User a[],int o,int s);

// Função que ordena um array de Users por nº de posts
void ordenaUser(User a[],int N);

// Função recebe um User e incrementa dos seus posts 
void incrNrPosts(User u);

// Função que recebe um User e liberta toda a memória utilizada pelo mesmo
void freeUser(User u);


// GETs
long getId(User u);

char* getBio(User u);

int getRep(User u);
 
char* getName(User u);

int getViews(User u);

int getVoteDif(User u);

int getNrPosts(User u);

#endif
