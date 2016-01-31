#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define ITERATIONS 20
#define SLEEP_TIME 2

int main(int argc, char *argv[]){
	int sent, pipefd, pid = getpid(), i;
	char* msg = malloc(255);

	printf("erzeug1.c\n");

	pipefd = open("myfifo", O_WRONLY);
	
	for(i = 0; i<ITERATIONS; i++){
		sprintf(msg, "%d:%d\n", pid, i);
		sent = write(pipefd, msg, strlen(msg));
		if(!sent) printf("error writing msg to pipe\n");
		sleep(SLEEP_TIME);
	}

	return 0;
}
