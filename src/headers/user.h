#ifndef _user_h_
#define _user_h_


//Declaração da estrutura User
typedef struct user *User;

/* Função que inicializa toda uma nova estrutura User com todas a variveis preenchidas*/
User initUser();

User newUser(int nId, int nRep, char* nName, int nViews, int nVoteDif, int nPosts);

/* recebe um User e um char* new e copia o name do User para o new*/ 
void getName(User u,char* new);

/* recebe um User e retorna o numero de posts desse mesmo User*/
int getNrPosts(User u);

/* recebe um User e incrementa dos seus posts*/
void incrUser(User u);

/* recebe um User e liberta toda a memória utilizada pelo mesmo*/
void freeUser(User u);

#endif