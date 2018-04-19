#include"chainPerguntas.c"

ChainPerguntas* initChain(Pergunta*);

ChainPerguntas* addPergunta(ChainPerguntas*, Pergunta*);

ChainPerguntas* addResposta(ChainPerguntas*, Resposta*);

void freeChain(ChainPerguntas*); 
