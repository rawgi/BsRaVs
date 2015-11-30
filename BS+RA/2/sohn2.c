#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_COUNT 250000000

int main(int argc, char *argv[]){
	int i, j;
	printf("Sohn_2 PID:%d vaterPID:%d\n",getpid(),getppid());
	system("date");
	for(i = 0; i<10; i++){
		for(j = 0; j<MAX_COUNT; j++){
		}
		printf(".\n");
	}
	return 0;
}
