#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[]){
	int filedesin, filedesout, error = 0;
	void* buffer = calloc(1,sizeof(char));
	printf("Kopiere Datei byteweise ...\n");
	if(argc != 3){
		printf("\nFEHLER: es werden 2 parameter für die ein- und ausgabedatei erwartet.");
		return 1;
	}
	filedesin = open(argv[1], O_RDONLY);
	if(filedesin < 0){
		printf("\n ERROR opening inputfile: %d",error);
		return 1;		
	}

	filedesout = creat(argv[2], 0600);
	if(filedesout < 0){
		printf("\n ERROR creating outputfile: %d",error);
		return 1;		
	}
	
	do{
		error = write(filedesout, buffer, read(filedesin, buffer, 1));
	}while(error > 0);
	
	if(error < 0){
		printf("\n ERROR writing File %d",error);
	}
	
	error = close(filedesin);
	if(error) printf("\n ERROR closing inputfile: %d",error);
	error = close(filedesout);
	if(error) printf("\n ERROR closing outputfile: %d",error);
	return 0;
}
