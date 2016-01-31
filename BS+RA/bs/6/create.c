#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <semaphore.h>
#include <unistd.h>
#include "shared.h"

int main(int argc, char *argv[]){
	int shmfd, error;
	printf("create.c\n\n");
	if(argc != 2){
		printf("usage: ./create [SHM-Name]\n");
		return -1;
	}

	shmfd = shm_open(argv[1], O_CREAT | O_RDWR | O_EXCL, 0666);
	if(shmfd < 1){
		printf("error creating Shared Memory Segment\n");
		return -1;
	}

	error = ftruncate(shmfd, sizeof(shared));
	if(error < 0){
		printf("error setting size of SHM \"%s\"\n",argv[1]);
		return -1;
	}

	shared* myShared = (shared*)mmap(NULL, sizeof(shared), PROT_WRITE, MAP_SHARED, shmfd, 0);	
	if(myShared == MAP_FAILED){
		printf("error mapping Shared Memory Segment\n");
	}

	error = sem_init(&(myShared->sem), 1, 1);
	if(error != 0){
		printf("\nError initializing semaphore. Error: %d\n", error);
	}

	error = munmap(myShared, sizeof(shared));
	if(error < 0){
		printf("error closing Shared Memory Segment\n");
		return -1;
	}

	return 0;
}
