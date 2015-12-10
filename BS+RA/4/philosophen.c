#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define N 8		/* Anzahl der Philosophen            */
#define LEFT  (i-1)%N	/* Nummer des linken Nachbarn von i  */
#define RIGHT (i+1)%N	/* Nummer des rechten Nachbarn von i */
#define THINKING    0	/* Zustand: Denkend  */
#define HUNGRY      1	/* Zust: Versucht, Gabeln zu bekommen*/     
#define EATING      2	/* Zustand: Essend  */
#define RUNDEN     10

/* gemeinsame Variablen    */     
int state[N];   	/* Zustaende aller Philosophen       */
int eaten[N];		/* wie oft jeder Philosoph gegessen hat*/
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t conds[N];

void test(int i) {		/* i:0..N-1, welcher Philosoph       */
	if (state[i]== HUNGRY && state[LEFT]!=EATING && state[RIGHT]!=EATING){
		state[i]=EATING;/* jetzt kann Phil i essen !         */
		pthread_cond_signal(&conds[i]);	/* "sage es ihm"  */
	}
}

void take_forks(int i) {	/* i:0..N-1, welcher Philosoph       */
	pthread_mutex_lock(&mutex);/* tritt in krit. Bereich ein        */
	int error;
	state[i] = HUNGRY;	/* zeige, dass du hungrig bist       */
	test(i);		/* versuche,beide Gabeln zu bekommen*/
	while(state[i] == HUNGRY){
		error = pthread_cond_wait(&conds[i], &mutex); /* bockiere, falls Gabeln nicht frei */
		if(error != 0){
			printf("Error waiting for forks in philosopher %d.",i);
		}
	}
	pthread_mutex_unlock(&mutex);/* verlasse krit. Bereich            */
}

void put_forks(int i) {		/* i:0..N-1, welcher Philosoph       */
	pthread_mutex_lock(&mutex);/* tritt in krit. Bereich ein        */
	state[i] = THINKING;	/* zeige, dass du fertig bist */
	test(LEFT);		/* kann linker Nachbar jetzt essen ? */
	test(RIGHT);		/* kann rechter Nachbar jetzt essen ?*/
	pthread_mutex_unlock(&mutex);/* verlasse krit. Bereich            */
}

void eat(int i){
	printf("%d am essen\n",i);
	sleep((rand()%9)+1);
}

void think(int i){
	printf("%d am denken\n",i);
	sleep((rand()%9)+1);
}

void* philosopher(void* arg){	/* i:0..N-1, welcher Philosoph       */	
	int i = *((int*)arg);
	while (eaten[i] < RUNDEN) {
		think(i);	/* Denken */
		take_forks(i);	/* Greife beide Gabeln oder blockiere*/
		eaten[i]++;
		eat(i);		/* Essen */
		put_forks(i);	/* Ablegen beider Gabeln  */
	}
	return NULL;
}

int main(int argc, char *argv[]){
	printf("Dinierende Philosophen ...\n\n");
	pthread_t threads[N];
	int i, error;
	int philos[N];
	srand(time(NULL));

	for(i = 0; i<N; i++){
		eaten[i] = 0;
		philos[i] = i;
		pthread_cond_init(&conds[i],NULL);
		error = pthread_create(threads+i, NULL, philosopher, (void*)&philos[i]);
		if(error != 0){
			printf("\nError creating thread %d. Error: %d\n", i, error);
		}
	}

	for(i = 0; i<N; i++){
		pthread_join(threads[i], NULL);
	}

	pthread_mutex_destroy(&mutex);

	printf("\nAbspuelen nicht vergessen!\n");
	return 0;
}
