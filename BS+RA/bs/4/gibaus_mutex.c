#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <pthread.h>

#define NUM_THREADS 6
#define ITERATIONS 5
#define ANZ_ZEICHEN 10

pthread_mutex_t mutex;

void msleep(unsigned int ms){
	struct timespec ts;

	assert(ms > 0);
	assert(ms < 1000);

	ts.tv_sec = 0;
	ts.tv_nsec = ms * 1000 * 1000;
	nanosleep(&ts, NULL);
}

void* thread_routine(void* arg){
	char zeichen  = *((char*)arg);
	int i, j;cond_wait

	for(j = 0; j<ITERATIONS; j++){
		pthread_mutex_lock(&mutex);
		for(i = 0; i<ANZ_ZEICHEN; i++){
			printf("%c",zeichen);
			fflush(stdout);
			msleep((rand()%400)+100);
		}
		printf("\n");
		fflush(stdout);
		pthread_mutex_unlock(&mutex);
		msleep((rand()%400)+100);
	}
	return NULL;
}

int main(int argc, char *argv[]){
	pthread_t threads[NUM_THREADS];
	pthread_mutex_init(&mutex, NULL);
	int error, i;

	char zeichen[] = {'A','B','C','D','E','F'};

	srand(time(NULL));

	for(i = 0; i<NUM_THREADS; i++){
		error = pthread_create(threads+i, NULL, thread_routine, (void*)(zeichen+i));
		if(error != 0){
			printf("\nError creating thread %d. Error: %d\n", i, error);
		}
	}

	for(i = 0; i<NUM_THREADS; i++){
		pthread_join(threads[i], NULL);
	}
	
	pthread_mutex_destroy(&mutex);
	
	return 0;
}
