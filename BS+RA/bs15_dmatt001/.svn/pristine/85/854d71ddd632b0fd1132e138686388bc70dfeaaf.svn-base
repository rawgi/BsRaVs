#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define ITERATIONS 6
#define SLEEP_TIME 2

int main(int argc, char *argv[]){
	pid_t sohn1 = fork();
	int i, s1t = 0, s2t = 0, status;
	char pstree[20];
	pid_t terminated;
	if(!sohn1){
		execve("./sohn1", argv, NULL);
	}

	pid_t sohn2 = fork();
	if(!sohn2){
		execve("./sohn2", argv, NULL);
	}
	
	printf("Vater: %d Sohn1: %d Sohn2: %d\n",getpid(),sohn1,sohn2);
	
	for(i = 0; i<ITERATIONS; i++){
		sleep(SLEEP_TIME);
		system("date");
		system("ps -H");
	}
	sprintf(pstree,"pstree -h %d",getppid());
	system(pstree);

	do{
		terminated = wait(&status);
		if(terminated == sohn1){
			printf("Sohn1 beendet\n");
			system("date");
			s1t = 1;
		}
		if(terminated == sohn2){
			printf("Sohn2 beendet\n");
			system("date");
			s2t = 1;
		}
	}while(s1t == 0 || s2t == 0);

	return 0;
}
