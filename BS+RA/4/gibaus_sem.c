#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <pthread.h>

#define NUM_THREADS 6
#define ITERATIONS 5
#define ANZ_ZEICHEN 10

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
	int i, j;

	for(j = 0; j<ITERATIONS; j++){
	//anfang krit absch
		for(i = 0; i<ANZ_ZEICHEN; i++){
			printf("%s",&zeichen);
			fflush(stdout);
			msleep((rand()%400)+100);
		}
		printf("\n");
		fflush(stdout);
		msleep((rand()%400)+100);
	//ende krit absch
	}
	return NULL;
}

int main(int argc, char *argv[]){
	pthread_t threads[NUM_THREADS];
	char zeichen[] = {'A','B','C','D','E','F'};
	int error, i;

	srand(time(NULL));

	for(i = 0; i<NUM_THREADS; i++){
		error = pthread_create(threads+i, NULL, thread_routine, (void*)zeichen+i);
		if(error != 0){
			printf("\nError creating thred %d. Error: %d\n", i, error);
		}
	}

	return 0;
}
