#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "../../include/heap.h"
#include "../../include/users.h"

#define PAI(i) (i-1)/2
#define ESQUERDO(i) 2*i + 1
#define DIREITO(i) 2*i + 2

#define PAI_NR(i) heap->nrPosts[(i-1)/2]
#define PAI_ID(i) heap->idUser[(i-1)/2]

#define FILHO_E_NR(i) heap->nrPosts[2*i+1]
#define FILHO_E_ID(i) heap->idUser[2*i+1]


#define FILHO_D_NR(i) heap->nrPosts[2*i+2]
#define FILHO_D_ID(i) heap->idUser[2*i+2]


#define NODO_NR(i) heap->nrPosts[i]
#define NODO_ID(i) heap->idUser[i]



static Heap swap(Heap heap,int n1,int n2);
static Heap bubbleDown(Heap heap,int n);
static Heap bubbleUp(Heap heap,int i);
static int existe_heap(Heap heap, long idUser);


struct heap {
    int tamanho;
    int pos; 
    int *nrPosts;
    long *idUser;
};


Heap initHeap(){
    Heap heap = malloc(sizeof(struct heap));
    heap->tamanho = 5;
    heap->pos = 0;
    heap->nrPosts = (int*) malloc(5 *sizeof(int));
    heap->idUser = (long*)malloc(5 *sizeof(long));
    return heap;
}


Heap heap_push(long idUser , int nrPosts , Heap heap){
    int index; 
    index = existe_heap(heap,idUser);
    if(heap->tamanho-1 == heap->pos) {
        heap->tamanho *= 2; 
        heap->nrPosts = realloc(heap->nrPosts,heap->tamanho *sizeof(int));
        heap->idUser = realloc(heap->idUser,heap->tamanho *sizeof(long));
        
    }

    if(index != -1) {
        heap->nrPosts[index] += nrPosts;
        heap->idUser[index] += idUser;
        heap = bubbleUp(heap,index);
    } else {
        NODO_NR(heap->pos) = nrPosts;
        NODO_ID(heap->pos) = idUser;
        heap = bubbleUp(heap,heap->pos);
        heap->pos++;
    }
    return heap;
}


int existe_heap(Heap heap, long idUser) {
    int i;
    int resultado = -1;
    for(i = 0; i < heap->pos; i++) {
        if(heap-> idUser[i] == idUser) return i;
    }
    return resultado;
}


long heap_pop(Heap heap) {
    if(heap->pos==0) return 0;
    long idTop= NODO_NR(0);
    heap->pos--;
    NODO_NR(0)=NODO_NR(heap->pos);
    NODO_ID(0)=NODO_ID(heap->pos);

    heap = bubbleDown(heap,heap->pos);
    
    return idTop;
}


static Heap bubbleDown(Heap heap,int n) {
    int i,m;
    i=0;
    
    while(ESQUERDO(i) < n) {
        if(DIREITO(i) < n) m = FILHO_E_NR(i) > FILHO_D_NR(i) ? ESQUERDO(i) : DIREITO(i);
        else m = ESQUERDO(i);

        if(NODO_NR(i) < NODO_NR(m)) { 
            heap = swap(heap,i,m);
            i = m;
        }else return heap;
    }
    return heap;
}


static Heap bubbleUp(Heap heap, int i){
    while(i > 0 && PAI_NR(i) < NODO_NR(i)){
        heap=swap(heap,i,PAI(i));
        i = PAI(i);
    }
    return heap;
}


static Heap swap(Heap heap,int n1,int n2) {
    int n = NODO_NR(n1);
    long x = NODO_ID(n1);
       
    NODO_NR(n1) = NODO_NR(n2);
    NODO_ID(n1) = NODO_ID(n2);

    NODO_NR(n2) = n;
    NODO_ID(n2) = x;

    return heap;
}


int heap_count(Heap heap){
    return heap->pos;
}

void heap_free(Heap heap){
    free(heap->nrPosts);
    free(heap->idUser);
    free(heap);
}