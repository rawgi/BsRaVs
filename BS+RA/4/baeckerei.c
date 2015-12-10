#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define BLAECHE 10

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;



void* eier(){
//liefern
//warten
	return NULL;
}

void* mehl(){
//liefern
//warten
	return NULL;
}

void* milch(){
//liefern
//warten
	return NULL;
}

void* baecker(){
//fehlt noch was?
//ja: jammern
//nein: backen
	return NULL;
}

int main(int argc, char *argv[]){
	printf("Plaetzchenbaeckerei ...\n\n");
	int i;
	for(i = 0; i<BLAECHE; i++){
		
	}
	return 0;
}
