#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define BLAECHE 10
#define EIER_NEEDED 5
#define MEHL_NEEDED 2
#define MILCH_NEEDED 1
#define ZWERGE 4

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t aufgefuellt;
pthread_cond_t aufgebraucht;

int eier_count = 0;
int mehl_count = 0;
int milch_count = 0;
int gebacken = 0;

void* eier(){
	int error;
	while(gebacken < BLAECHE){
		error = pthread_mutex_lock(&mutex);
		if(error != 0){
			printf("Error locking mutex eier. Error:%d\n", error);
		}
		if(eier_count < EIER_NEEDED){
			printf("Eier: Auslieferung\n");
			eier_count++;
			printf("Eier: es sind %d Ei(er) in der Backstube\n", eier_count);
			printf("Eier: Backstube verlassen\n");
		}
		if(eier_count == EIER_NEEDED){
			printf("Eier: waecke Baecker\n");
			error = pthread_cond_signal(&aufgefuellt);
			if(error != 0){
				printf("Error signaling eier\n");
			}
			error = pthread_cond_wait(&aufgebraucht,&mutex);
			if(error != 0){
				printf("Error waiting eier\n");
			}
		}
		error = pthread_mutex_unlock(&mutex);
		if(error != 0){
			printf("Error locking unmutex eier. Error:%d\n", error);
		}
		sleep(1);
	}
	return NULL;
}

void* mehl(){
	int error;
	while(gebacken < BLAECHE){
		error = pthread_mutex_lock(&mutex);
		if(error != 0){
			printf("Error locking mutex mehl. Error:%d\n", error);
		}
		if(mehl_count < MEHL_NEEDED){
			printf("\tMehl: Auslieferung\n");
			mehl_count++;
			printf("\tMehl: es sind %dkg Mehl in der Backstube\n", mehl_count);
			printf("\tMehl: Backstube verlassen\n");
		}
		if(mehl_count == MEHL_NEEDED){
			printf("\tMehl: waecke Baecker\n");
			error = pthread_cond_signal(&aufgefuellt);
			if(error != 0){
				printf("Error signaling mehl\n");
			}
			error = pthread_cond_wait(&aufgebraucht,&mutex);
			if(error != 0){
				printf("Error waiting mehl\n");
			}
		}
		error = pthread_mutex_unlock(&mutex);
		if(error != 0){
			printf("Error locking unmutex mehl. Error:%d\n", error);
		}
		sleep(3);
	}
	return NULL;
}

void* milch(){
	int error;
	while(gebacken < BLAECHE){
		error = pthread_mutex_lock(&mutex);
		if(error != 0){
			printf("Error locking mutex milch. Error:%d\n", error);
		}
		if(milch_count < MILCH_NEEDED){
			printf("\t\tMilch: Auslieferung\n");
			milch_count++;
			printf("\t\tMilch: es sind %dL Milch in der Backstube\n", milch_count);
			printf("\t\tMilch: Backstube verlassen\n");
		}
		if(milch_count == MILCH_NEEDED){
			printf("\t\tMilch: waecke Baecker\n");
			error = pthread_cond_signal(&aufgefuellt);
			if(error != 0){
				printf("Error signaling milch\n");
			}
			error = pthread_cond_wait(&aufgebraucht,&mutex);
			if(error != 0){
				printf("Error waiting milch\n");
			}
		}
		error = pthread_mutex_unlock(&mutex);
		if(error != 0){
			printf("Error locking unmutex milch. Error:%d\n", error);
		}
		sleep(2);
	}
	return NULL;
}

void* baecker(){
	int error;
	while(gebacken < BLAECHE){
		error = pthread_mutex_lock(&mutex);
		if(error != 0){
			printf("Error locking mutex baecker Error:%d\n", error);
		}
		error = pthread_cond_wait(&aufgefuellt, &mutex);
		if(error != 0){
			printf("Error waiting baecker\n");
		}
		if(eier_count < EIER_NEEDED || mehl_count < MEHL_NEEDED || milch_count < MILCH_NEEDED){
			printf("\t\t\tBaecker: es fehlen noch Zutaten, warte...\n");
		}else{
			printf("\t\t\tBaecker: backe...\n");
			sleep(5);
			eier_count = 0;
			mehl_count = 0;
			milch_count = 0;
			gebacken++;
			printf("\t\t\tBaecker: bringe Blech herrlich duftender Plaetzchen raus.\n");
			error = pthread_cond_broadcast(&aufgebraucht);
			if(error != 0){
				printf("Error signaling Lieferzwerge\n");
			}
		}
		error = pthread_mutex_unlock(&mutex);
		if(error != 0){
			printf("Error unlocking mutex baecker. Error:%d\n", error);
		}
	}
	return NULL;
}

int main(int argc, char *argv[]){
	printf("Plaetzchenbaeckerei ...\n\n");
	int i, error;
	pthread_t threads[ZWERGE];
	error = pthread_create(threads+0, NULL, baecker, NULL);
	if(error != 0){
		printf("\nError creating thread %d. Error: %d\n", 0, error);
	}
	error = pthread_create(threads+1, NULL, eier, NULL);
	if(error != 0){
		printf("\nError creating thread %d. Error: %d\n", 1, error);
	}
	error = pthread_create(threads+2, NULL, mehl, NULL);
	if(error != 0){
		printf("\nError creating thread %d. Error: %d\n", 2, error);
	}
	error = pthread_create(threads+3, NULL, milch, NULL);
	if(error != 0){
		printf("\nError creating thread %d. Error: %d\n", 3, error);
	}
	for(i = 0; i<ZWERGE; i++){
		error = pthread_join(threads[i], NULL);
		if(error != 0){
			printf("Error joining thread %d.\n",i);
		}
	}
	return 0;
}
