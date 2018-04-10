#include"postTable.c"

PostTable* initTable(int);

int hashPergunta(Pergunta*, int);
int hashResposta(Resposta*, int);
int hash(long, int);
PostTable* buffup(PostTable*, int);
PostTable* instertPergunta(PostTable*, Pergunta*);
PostTable* insertResposta(PostTable*, Resposta*);
void freeTable(PostTable*);
