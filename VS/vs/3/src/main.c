#include <vfilesystem_api.h>
#include <vfilesystem_server_messages.h>
#include <vfilesystem_server_marshaller.h>
#include <stdio.h>
#include <string.h>
#include <strings.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <fs_to_server.h>
#include <pthread.h>

static void* thread_handle(void* clientSocket){
	int* clientSocketDesc = (int*)clientSocket;
	FileServerMessage* fsm;
	uint32_t msgLength;
	uint8_t* buffer;
	int error;
	while(1){
		recv(*clientSocketDesc, &msgLength, 4, 0);
		buffer = calloc(msgLength, sizeof(uint32_t));
		recv(*clientSocketDesc, buffer, msgLength, 0);
		error = unmarshall(buffer, msgLength, &fsm);
		if(error < 0){
			printf("error on unmarshall");
			error = -1;
		}
printf("payloud_type: %d\n", fsm->payload_type);
		switch(fsm->payload_type){
			case NEW_FILE_REQUEST:
				msgLength = (uint32_t)mk_NewFileResponse(&fsm);
				msgLength++;
				buffer = calloc(1,msgLength+4);
				marshall(buffer, msgLength, fsm);
				send(*clientSocketDesc, buffer, msgLength+4, 0);
				break;
			case NEW_FOLDER_REQUEST:
				msgLength = (uint32_t)mk_NewFolderResponse(&fsm);
				msgLength++;
				buffer = calloc(1,msgLength+4);
				marshall(buffer, msgLength, fsm);
				send(*clientSocketDesc, buffer, msgLength+4, 0);
				break;
			case DELETE_FILE_REQUEST:
				mk_DeleteFileResponse(&fsm);
				break;
			case DELETE_FOLDER_REQUEST:
				mk_DeleteFolderResponse(&fsm);
				break;
			case FILE_INFO_REQUEST:
				msgLength = (uint32_t)mk_FileInfoResponse(&fsm);
				msgLength++;
				buffer = calloc(1,msgLength+4);
				marshall(buffer, msgLength, fsm);
				send(*clientSocketDesc, buffer, msgLength+4, 0);
				break;
			case FOLDER_INFO_REQUEST:
				msgLength = (uint32_t)mk_FolderInfoResponse(&fsm);
				msgLength++;
				buffer = calloc(1,msgLength+4);
				marshall(buffer, msgLength, fsm);
				send(*clientSocketDesc, buffer, msgLength+4, 0);
				break;
			case WRITE_FILE_REQUEST:
				mk_WriteFileResponse(&fsm);
				break;
			case READ_FILE_REQUEST:
				msgLength = (uint32_t)mk_ReadFileResponse(&fsm);
				msgLength++;
				buffer = calloc(1,msgLength+4);
				marshall(buffer, msgLength, fsm);
				send(*clientSocketDesc, buffer, msgLength+4, 0);
				break;
			default: error = -1;
		}
	}
	return NULL;
}

int main(int argc, char **args) {
	if (argc != 2 || (strncmp(args[1], "-h", 2) == 0) || (strncmp(args[1], "--help", 6) == 0)) {
		printf("Benutzung: %s <port>\n", args[0]);
		exit(-1);
	}
	pthread_t pthreads[10];
	int* clientSocketDesc = calloc(1,sizeof(int));
	int pthreadCount = 0, socketDesc, clientAddLength, error, port = atoi(args[1]);
	struct sockaddr_in socketAdd, clientAdd;
	
	printf("Starting VDiskServer\n");
	
	fs_init();

	bzero((char *) &socketAdd, sizeof(socketAdd));
	socketAdd.sin_family = AF_INET;
	socketAdd.sin_port = htons(port);
	socketAdd.sin_addr.s_addr = INADDR_ANY;

	socketDesc = socket(socketAdd.sin_family, SOCK_STREAM, 0);
	if(socket < 0){
		printf("error creating socket.\n");
		return -1;
	}

	error = bind(socketDesc, (struct sockaddr*) &socketAdd, sizeof(socketAdd));
	if(error < 0){
		printf("error binding socket.\n");
		return -1;
	}
	listen(socketDesc,5);
	clientAddLength = sizeof(clientAdd);
	while(1){
		*clientSocketDesc = accept(socketDesc, (struct sockaddr*) &clientAdd, (socklen_t *) &clientAddLength);
		if(pthreadCount<10){
			pthread_create(pthreads+pthreadCount, NULL, thread_handle, (void*)clientSocketDesc);
			pthreadCount++;
		}else{
			printf("too many clients connected.\n");
		}
	}

	return 0;
}
