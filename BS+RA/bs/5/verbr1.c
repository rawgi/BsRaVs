#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[]){
	int pipefd;
	char latter;
	printf("verbr1.c\n");
	pipefd = open("myfifo", O_RDONLY);

	while(read(pipefd, &latter, 1)){
		printf("%c", latter);
	}

	return 0;
}
