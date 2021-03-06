#include <stdio.h>
#include <sys/types.h>
#include <vfilesystem_api.h>
#include <vfilesystem_server_messages.h>
#include <vfilesystem_server_marshaller.h>
#include <string.h>
#include <strings.h>

int32_t mk_NewFileResponse(FileServerMessage** fsm){
	NewFileRequest* nfRequest = (NewFileRequest*)((*fsm)->payload);
	NewFileResponse* nfResponse = calloc(1, sizeof(NewFileResponse));
	char* name = calloc(1,nfRequest->name_size+1);
	memcpy(name,nfRequest->name,nfRequest->name_size);
	name[nfRequest->name_size] = '\0';

	nfResponse->handle = fs_new_file(name, nfRequest->parent);
	

	(*fsm)->payload_type = NEW_FILE_RESPONSE;
	(*fsm)->payload = (uint8_t*)nfResponse;
	return 4;
}

int32_t mk_NewFolderResponse(FileServerMessage** fsm){
	NewFolderRequest* nfRequest = (NewFolderRequest*)((*fsm)->payload);
	NewFolderResponse* nfResponse = calloc(1, sizeof(NewFolderResponse));

	char* name = calloc(1,nfRequest->name_size+1);
	memcpy(name,nfRequest->name,nfRequest->name_size);
	name[nfRequest->name_size] = '\0';

	nfResponse->handle = fs_new_file(name, nfRequest->parent);
	

	(*fsm)->payload_type = NEW_FOLDER_RESPONSE;
	(*fsm)->payload = (uint8_t*)nfResponse;
	return 4;
}

int32_t mk_FileInfoResponse(FileServerMessage** fsm){
	FileInfoRequest* fiRequest = (FileInfoRequest*)((*fsm)->payload);
	FileInfoResponse* fiResponse = calloc(1, sizeof(FileInfoResponse));
	char* name;

	fiResponse->parent = fs_get_file_parent(fiRequest->handle);

	fiResponse->size = fs_get_file_size(fiRequest->handle);

	fiResponse->name_length = fs_get_file_name_length(fiRequest->handle);

	fiResponse->name = calloc(1, fiResponse->name_length);
	name = calloc(1, fiResponse->name_length);
	fs_get_file_name(fiRequest->handle, name, fiResponse->name_length);
	memcpy(fiResponse->name, name, fiResponse->name_length);

	(*fsm)->payload_type = FILE_INFO_RESPONSE;
	(*fsm)->payload = (uint8_t*)fiResponse;

	return 9+fiResponse->name_length;
}

int32_t mk_FolderInfoResponse(FileServerMessage** fsm){
	FolderInfoRequest* fiRequest = (FolderInfoRequest*)((*fsm)->payload);
	FolderInfoResponse* fiResponse = calloc(1, sizeof(FolderInfoResponse));
	char* name;

	fiResponse->parent = fs_get_folder_parent(fiRequest->handle);
	fiResponse->name_length = fs_get_folder_name_length(fiRequest->handle);

	fiResponse->name = calloc(1, fiResponse->name_length);
	name = calloc(1, fiResponse->name_length);
	fs_get_folder_name(fiRequest->handle, name, fiResponse->name_length);
	memcpy(fiResponse->name, name, fiResponse->name_length);

	fiResponse->file_count = fs_get_folder_file_count(fiRequest->handle);
	fiResponse->files = calloc(fiResponse->file_count, 4);
	fs_get_folder_files(fiRequest->handle, (int32_t*)fiResponse->files, fiResponse->file_count);

	fiResponse->folder_count = fs_get_folder_folder_count(fiRequest->handle);
	fiResponse->folders = calloc(fiResponse->folder_count, 4);
	fs_get_folder_folders(fiRequest->handle, (int32_t*)fiResponse->folders, fiResponse->folder_count);

	(*fsm)->payload_type = FOLDER_INFO_RESPONSE;
	(*fsm)->payload = (uint8_t*)fiResponse;

	return 13+fiResponse->name_length+fiResponse->file_count*4+fiResponse->folder_count*4;
}

int32_t mk_ReadFileResponse(FileServerMessage** fsm){
	ReadFileRequest* rfRequest = (ReadFileRequest*)((*fsm)->payload);
	ReadFileResponse* rfResponse = calloc(1, sizeof(ReadFileResponse));

	rfResponse->size = rfRequest->length;
	rfResponse->data = calloc(1,rfRequest->length);
	fs_read_file(rfRequest->handle, rfRequest->offset, rfRequest->length, rfResponse->data);

	(*fsm)->payload_type = READ_FILE_RESPONSE;
	(*fsm)->payload = (uint8_t*)rfResponse;

	return 4+rfResponse->size;
}

int32_t mk_DeleteFileResponse(FileServerMessage** fsm){
	DeleteFileRequest* dfr = (DeleteFileRequest*)((*fsm)->payload);

	fs_delete_file(dfr->handle);

	 /*keine Informationen über Response-Nachricht*/
	return 0;
}

int32_t mk_DeleteFolderResponse(FileServerMessage** fsm){
	DeleteFolderRequest* dfr = (DeleteFolderRequest*)((*fsm)->payload);

	fs_delete_file(dfr->handle);

	 /*keine Informationen über Response-Nachricht*/
	return 0;
}

int32_t mk_WriteFileResponse(FileServerMessage** fsm){
	WriteFileRequest* wfr = (WriteFileRequest*)((*fsm)->payload);

	fs_write_file(wfr->handle, wfr->offset, wfr->length, wfr->data);

	/*keine Informationen über Response-Nachricht*/
	return 0;
}

int32_t mk_ErrorResponse(FileServerMessage** fsm){
	ErrorResponse* er = calloc(1, sizeof(ErrorResponse));
	char* error_msg = "error";

	er->error_code = 1;
	er->msg_length = 5;
	memcpy(er->msg, &error_msg, er->msg_length);

	(*fsm)->payload_type = ERROR_RESPONSE;
	(*fsm)->payload = (uint8_t*)er;

	return 5+er->msg_length;
}






