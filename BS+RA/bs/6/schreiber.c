#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include "shared.h"

#define ITERATIONS 10

void msleep(unsigned int ms){
	struct timespec ts;

	assert(ms > 0);
	assert(ms < 1000);

	ts.tv_sec = 0;
	ts.tv_nsec = ms * 1000 * 1000;
	nanosleep(&ts, NULL);
}

int main(int argc, char *argv[]){
	int i, j, sleep_time, length, shmfd, pid, error;
	char zeichen;

	printf("schreiber.c\n");

	if(argc != 5){
		printf("usage: ./schreiber [SHM-Name] [Zeichen] [Nachrichtenlaenge] [Wartezeit]\n");
		return -1;
	}

	shmfd = shm_open(argv[1], O_RDWR | O_EXCL, 0666);
	if(shmfd < 1){
		printf("error opening Shared Memory Segment\n");
		return -1;
	}

	zeichen = *argv[2];
	length = atoi(argv[3]);
	sleep_time = atoi(argv[4]);
	pid = getpid();
	shared* myShared = (shared*)mmap(NULL, sizeof(shared), PROT_WRITE, MAP_SHARED, shmfd, 0);	
	if(myShared == MAP_FAILED){
		printf("error mapping Shared Memory Segment\n");
	}

	for(i = 0; i<ITERATIONS; i++){
		sem_wait(&(myShared->sem));
		myShared->pid = pid;
		myShared->i = i;
		myShared->length = length;
		for(j = 0; j< length; j++){
			myShared->payload[j] = zeichen;
			msleep(sleep_time);
		}
		sem_post(&(myShared->sem));
		msleep(sleep_time);
	}

	error = munmap(myShared, sizeof(shared));
	if(error < 0){
		printf("error closing Shared Memory Segment\n");
		return -1;
	}
	return 0;
}
