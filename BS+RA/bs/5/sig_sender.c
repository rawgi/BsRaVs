#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <sys/types.h>
#include <signal.h>

void msleep(unsigned int ms){
	struct timespec ts;

	assert(ms > 0);
	assert(ms < 1000);

	ts.tv_sec = 0;
	ts.tv_nsec = ms * 1000 * 1000;
	nanosleep(&ts, NULL);
}

int main(int argc, char *argv[]){
	int pid, sigcount, sig, sig2, sleep, error, sent = 0;
	printf("sig_sender.c\n");

	printf("pid des Ziel-Prozesses angeben:\n");
	scanf("%d",&pid);
	
	do{
		printf("werden 1 oder 2 signale gesendet?\n");
		scanf("%d",&sigcount);
	}while(sigcount != 1 && sigcount != 2);

	if(sigcount == 2){
		printf("Zeit zwischen senden der Signale in ms angeben:\n");
		scanf("%d",&sleep);
	}

	//syscall auswählen
	printf("bitte wählen:\n");
	printf("1: SIGUSR1\n");
	printf("2: SIGUSR2\n");
	printf("3: SIGTERM\n");
	printf("4: SIGKILL\n");
	scanf("%d",&sig);
	if(sigcount == 2){
		printf("bitte 2. wählen:\n");
		printf("1: SIGUSR1\n");
		printf("2: SIGUSR2\n");
		printf("3: SIGTERM\n");
		printf("4: SIGKILL\n");
		scanf("%d",&sig2);
	}

	//gewählte syscalls ausführen
	do{
		switch(sig){
			case 1: printf("sende SIGUSR1 an %d.",pid);
				error = kill(pid, SIGUSR1);
				sent++;
				break;
			case 2: printf("sende SIGUSR2 an %d.",pid);
				error = kill(pid, SIGUSR2);
				sent++;
				break;
			case 3: printf("sende SIGTERM an %d.",pid);
				error = kill(pid, SIGTERM);
				sent++;
				break;
			case 4: printf("sende SIGKILL an %d.",pid);
				error = kill(pid, SIGKILL);
				sent++;
				break;
			default: printf("falsche auswahl:%d",sig);
		}
		if(error < 0){
			printf("Error sending signal");
		}
		if(sigcount == 2){
			msleep(sleep);
			sig = sig2;
		}
	}while(sent < sigcount);
	return 0;
}
