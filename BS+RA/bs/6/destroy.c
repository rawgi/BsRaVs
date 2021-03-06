#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "shared.h"

int main(int argc, char *argv[]){
	int error;
	printf("destroy.c\n");
	if(argc != 2){
		printf("usage: ./create [SHM-Name]\n");
		return -1;
	}

	error = shm_unlink(argv[1]);
	if(error < 0){
		printf("error deleting SHM \"%s\"", argv[1]);
		return -1;
	}
	return 0;
}
