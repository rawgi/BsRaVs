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

int32_t unmarshall(uint8_t* data, uint32_t size, FileServerMessage** msg_out){
	NewFileRequest* nfiler;
	NewFolderRequest* nfolderr;
	DeleteFileRequest* dfiler;
	DeleteFolderRequest* dfolderr;
	FileInfoRequest* fileir;
	FolderInfoRequest* folderir;
	WriteFileRequest* wfr;
	ReadFileRequest* rfr;
	uint8_t type;
	int offset = 0;
	size = ntohl(size);
	memcpy(&type, data, 1);
	offset += 1;
	*msg_out = calloc(1, sizeof(FileServerMessage));
	switch(type){
		case NEW_FILE_REQUEST:
			nfiler = calloc(1, sizeof(NewFileRequest));

			memcpy(&nfiler->parent, data+offset, 4);
			offset += 4;
			nfiler->parent = ntohl(nfiler->parent);
			memcpy(&nfiler->name_size, data+offset, 1);
			offset += 1;
			nfiler->name = calloc(1, nfiler->name_size);
			memcpy(nfiler->name, data+offset, nfiler->name_size);
			offset += nfiler->name_size;

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)nfiler;
			break;
		case NEW_FOLDER_REQUEST:
			nfolderr = calloc(1, sizeof(NewFolderRequest));

			memcpy(&nfolderr->parent, data+offset, 4);
			offset+= 4;
			nfolderr->parent = ntohl(nfolderr->parent);
			memcpy(&nfolderr->name_size, data+offset, 1);
			offset += 1;
			nfolderr->name = calloc(1, nfolderr->name_size);
			memcpy(nfolderr->name, data+offset, nfolderr->name_size);
			offset += nfolderr->name_size;
			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)nfolderr;
			break;
		case DELETE_FILE_REQUEST:
			dfiler = calloc(1, sizeof(DeleteFileRequest));
			memcpy(&dfiler->handle, data+offset, 4);
			offset += 4;
			dfiler->handle = ntohl(dfiler->handle);

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)dfiler;
			break;
		case DELETE_FOLDER_REQUEST:
			dfolderr = calloc(1, sizeof(DeleteFolderRequest));
			memcpy(&dfolderr->handle, data+offset, 4);
			offset += 4;
			dfolderr->handle = ntohl(dfolderr->handle);

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)dfolderr;
			break;
		case FILE_INFO_REQUEST:
			fileir = calloc(1, sizeof(FileInfoRequest));
			memcpy(&fileir->handle, data+offset, 4);
			offset += 4;
			fileir->handle = ntohl(fileir->handle);

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)fileir;
			break;
		case FOLDER_INFO_REQUEST:
			folderir = calloc(1, sizeof(FolderInfoRequest));
			memcpy(&folderir->handle, data+offset, 4);
			offset += 4;
			folderir->handle = ntohl(folderir->handle);

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)folderir;
			break;
		case WRITE_FILE_REQUEST:
			wfr = calloc(1, sizeof(WriteFileRequest));
			memcpy(&wfr->handle, data+offset, 4);
			offset += 4;
			wfr->handle = ntohl(wfr->handle);

			memcpy(&wfr->offset, data+offset, 4);
			offset += 4;
			wfr->offset = ntohl(wfr->offset);

			memcpy(&wfr->length, data+offset, 4);
			offset += 4;
			wfr->length = ntohl(wfr->length);

			wfr->data = calloc(1, wfr->length);
			memcpy(wfr->data, data+offset, wfr->length);
			offset += wfr->length;

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)wfr;
			break;
		case READ_FILE_REQUEST:
			rfr = calloc(1, sizeof(ReadFileRequest));
			memcpy(&rfr->handle, data+offset, 4);
			offset += 4;
			rfr->handle = ntohl(rfr->handle);

			memcpy(&rfr->offset, data+offset, 4);
			offset += 4;
			rfr->offset = ntohl(rfr->offset);

			memcpy(&rfr->length, data+offset, 4);
			offset += 4;
			rfr->length = ntohl(rfr->length);

			(*msg_out)->payload_type = type;
			(*msg_out)->payload = (uint8_t*)rfr;
			break;
		default: return -1;
	}
	return 0;
}

void free_message(FileServerMessage* msg){

}

int32_t marshall(uint8_t* data, uint32_t size, FileServerMessage* msg){
	NewFileResponse* nfiler;
	NewFolderResponse* nfolderr;
	FileInfoResponse* fileir;
	FolderInfoResponse* folderir;
	ReadFileResponse* rfr;
	ErrorResponse* er;
	int i, offset = 0, folderCount, fileCount;
	uint32_t msgSize = size;
	msgSize = htonl(msgSize);
	memcpy(data, &msgSize, 4);
	offset += 4;
	memcpy(data+offset, &msg->payload_type, 1);
	offset += 1;
	switch(msg->payload_type){
		case NEW_FILE_RESPONSE:
			nfiler = (NewFileResponse*)(msg->payload);

			nfiler->handle = htonl(nfiler->handle);
			memcpy(data+offset, &nfiler->handle, 4);
			offset += 4;
			break;
		case NEW_FOLDER_RESPONSE: 
			nfolderr = (NewFolderResponse*)(msg->payload);

			nfolderr->handle = htonl(nfolderr->handle);
			memcpy(data+offset, &nfolderr->handle, 4);
			offset += 4;
			break;
		case FILE_INFO_RESPONSE:
			fileir = (FileInfoResponse*)(msg->payload);

			fileir->parent = htonl(fileir->parent);
			memcpy(data+offset, &fileir->parent, 4);
			offset += 4;

			fileir->size = htonl(fileir->size);
			memcpy(data+offset, &fileir->size, 4);
			offset += 4;
			
			memcpy(data+offset, &fileir->name_length, 1);
			offset += 1;
			memcpy(data+offset, fileir->name, fileir->name_length);
			offset += fileir->name_length;
			break;
		case FOLDER_INFO_RESPONSE:
			folderir = (FolderInfoResponse*)(msg->payload);

			folderir->parent = htonl(folderir->parent);

			for(i = 0; i<folderir->file_count; i++){
				folderir->files[i] = htonl(folderir->files[i]);
			}
			fileCount = folderir->file_count;
			folderir->file_count = htonl(folderir->file_count);

			for(i = 0; i<folderir->folder_count; i++){
				folderir->folders[i] = htonl(folderir->folders[i]);
			}
			folderCount = folderir->folder_count;
			folderir->folder_count = htonl(folderir->folder_count);
			
			memcpy(data+offset, &folderir->parent, 4);
			offset += 4;
			memcpy(data+offset, &folderir->name_length, 1);
			offset += 1;
			memcpy(data+offset, folderir->name, folderir->name_length);
			offset += folderir->name_length;
			memcpy(data+offset, &folderir->file_count, 4);
			offset += 4;
			for(i = 0; i<fileCount; i++){
				memcpy(data+offset, folderir->files+i, 4);
				offset += 4;
			}
			memcpy(data+offset, &folderir->folder_count, 4);
			offset += 4;
			for(i = 0; i<folderCount; i++){
				memcpy(data+offset, folderir->folders+i, 4);
				offset += 4;
			}
			break;
		case READ_FILE_RESPONSE:
			rfr = (ReadFileResponse*)(msg->payload);

			rfr->size = htonl(rfr->size);
			memcpy(data+offset, &rfr->size, 4);
			offset += 4;

			memcpy(data+offset, rfr->data, size);
			offset += size;
			break;
		case DELETE_FILE_RESPONSE:
			/*keine Informationen über Response-Nachricht*/
			break;
		case DELETE_FOLDER_RESPONSE:
			/*keine Informationen über Response-Nachricht*/
			break;
		case WRITE_FILE_RESPONSE:
			/*keine Informationen über Response-Nachricht*/
			break;
		case ERROR_RESPONSE:
			er = (ErrorResponse*)(msg->payload);

			memcpy(data+offset, &er->error_code, 1);
			offset += 1;

			er->msg_length = htonl(er->msg_length);
			memcpy(data+offset, &er->msg_length, 4);
			offset += 4;

			memcpy(data+offset, er->msg, er->msg_length);
			offset += er->msg_length;
			break;
		default: return -1;
	}
	
	return 0;
}





