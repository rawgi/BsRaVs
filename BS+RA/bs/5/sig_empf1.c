#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>

#define ALARM_PERIOD 4

void sigusr1Func(int sig){
	if(sig != SIGUSR1){
		printf("function sigusr1Func called withoud signal SIGUSR1!");
	}else{
		printf("Signal SIGUSR1 empfangen");
	}
}

void sigusr2Func(int sig){
	if(sig != SIGUSR2){
		printf("function sigusr2Func called withoud signal SIGUSR2!\n");
	}else{
		printf("Signal SIGUSR2 empfangen\n");
	}
}

void terminate(int sig){
	if(sig != SIGTERM){
		printf("function terminate called withoud signal SIGTERM!\n");
	}else{
		printf("Programm wird terminiert.\n");
		exit(EXIT_SUCCESS);
	}
}

int main(int argc, char *argv[]){

	printf("sig_empf1.c\n");
	printf("pid: %d\n", getpid());

	//Einrichten der signal handler
	signal(SIGUSR1, sigusr1Func);
	signal(SIGUSR2, sigusr2Func);
	signal(SIGTERM, terminate);

	while(1){
		sleep(ALARM_PERIOD);
		printf("Timer abgelaufen\n");
	}

	return 0;
}
