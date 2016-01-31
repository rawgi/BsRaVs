#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h> 
#include "shared.h"

int main(int argc, char *argv[]){
	int shmfd, stop = 0, i;
	char c;

	printf("leser.c\n");

	if(argc != 2){
		printf("usage: ./leser [SHM-Name]\n");
		return -1;
	}

	shmfd = shm_open(argv[1], O_RDONLY | O_EXCL, 0666);
	if(shmfd < 1){
		printf("error opening Shared Memory Segment\n");
		return -1;
	}

	shared* myShared = (shared*)mmap(NULL, sizeof(shared), PROT_READ, MAP_SHARED, shmfd, 0);	
	if(myShared == MAP_FAILED){
		printf("error mapping Shared Memory Segment\n");
	}
	do{
		printf("Taste druecken. (q == quit)\n");
		c = getchar();
		if(c == 'q'){
			stop = 1;
		}else{
			printf("Inhalt Shared Memory Segment:\n");
			printf("pid: %d, ", myShared->pid);
			printf("i: %d, ", myShared->i);
			for(i = 0; i<myShared->length; i++){
				printf("%c", myShared->payload[i]);
			}
			printf("\n");
		}
	}while(!stop);
	printf("STOOOOP AAAAAHHHHHH!!!!!!\n");
	return 0;
}
